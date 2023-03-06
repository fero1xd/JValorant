package me.fero.api;

import me.fero.entities.match.*;
import me.fero.entities.match.round.Kill;
import me.fero.entities.match.round.Round;
import me.fero.enums.Region;
import me.fero.io.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static me.fero.Utils.*;

public class MatchDataResult extends ApiResponse {

    private final Match match;

    public MatchDataResult(Response response) {
        super(response);

        JSONObject data = (JSONObject) response.getJson().get("data");

        MatchMetadata metadata = this.getMetadata((JSONObject) data.get("metadata"));
        Players players = this.parseAllPlayers((JSONObject) data.get("players"));
        TeamsStatus teamsStatus = this.parseTeamsStatus((JSONObject) data.get("teams"));
        Round[] rounds = this.parseRounds((JSONArray) data.get("rounds"));
        Kill[] kills = this.parseKills((JSONArray) data.get("kills"));

        this.match = new Match(metadata, players, teamsStatus, rounds, kills);
    }

    private MatchMetadata getMetadata(JSONObject metadata) {
        String map = metadata.get("map").toString();
        String gameVersion = metadata.get("game_version").toString();
        Long gameLength = Long.parseLong(metadata.get("game_length").toString());
        Long gameStart = Long.parseLong(metadata.get("game_start").toString());
        String gameStartPatched = metadata.get("game_start_patched").toString();
        Integer roundsPlayed = Integer.parseInt(metadata.get("rounds_played").toString());
        String mode = metadata.get("mode").toString();
        String queue = metadata.get("queue").toString();
        String seasonId = metadata.get("season_id").toString();
        String platform = metadata.get("platform").toString();
        String matchId = metadata.get("matchid").toString();
        Region region = Region.findAny(metadata.get("region").toString());
        String cluster = metadata.get("cluster").toString();
        return new MatchMetadata(map, gameVersion, gameLength, gameStart, gameStartPatched,
                roundsPlayed, mode, queue, seasonId, platform, matchId, region, cluster);
    }

    private Players parseAllPlayers(JSONObject players) {
        JSONArray redTeam = (JSONArray) players.get("red");
        JSONArray blueTeam = (JSONArray) players.get("blue");

        MatchPlayer[] redTeamPlayers = parsePlayers(redTeam);
        MatchPlayer[] blueTeamPlayers = parsePlayers(blueTeam);


        if(redTeam.size() == 0 && blueTeam.size() == 0) {
            return new Players(redTeamPlayers, blueTeamPlayers,
                    parsePlayers((JSONArray) players.get("all_players")));
        }

        return new Players(redTeamPlayers, blueTeamPlayers);
    }

    private TeamsStatus parseTeamsStatus(JSONObject teams) {
        JSONObject red = (JSONObject) teams.get("red");
        JSONObject blue = (JSONObject) teams.get("blue");

        return new TeamsStatus(parseTeamStatus(red), parseTeamStatus(blue));
    }

    private Round[] parseRounds(JSONArray roundsRaw) {
        Round[] rounds = new Round[roundsRaw.size()];

        for(int i = 0; i < roundsRaw.size(); i++) {
            rounds[i] = parseRound((JSONObject) roundsRaw.get(i));
        }

        return rounds;
    }

    private Kill[] parseKills(JSONArray killsRaw) {
        Kill[] kills = new Kill[killsRaw.size()];

        for(int i = 0; i < killsRaw.size(); i++) {
            JSONObject kill = (JSONObject) killsRaw.get(i);
            int round = Integer.parseInt(kill.get("round").toString());
            kills[i] = new Kill(round, parseKillEvent(kill));
        }

        return kills;
    }

    public Match getMatch() {
        return match;
    }
}
