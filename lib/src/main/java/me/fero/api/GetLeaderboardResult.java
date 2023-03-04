package me.fero.api;

import me.fero.entities.LeaderboardUser;
import me.fero.io.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class GetLeaderboardResult extends ApiResponse{

    private final LeaderboardUser[] leaderboardUsers;

    public GetLeaderboardResult(Response response) {
        super(response);

        JSONArray jsonArray = response.getJsonArray();
        this.leaderboardUsers = new LeaderboardUser[jsonArray.size()];

        for(int i = 0; i < jsonArray.size(); i++) {
            JSONObject lbUser = (JSONObject) jsonArray.get(i);

            this.leaderboardUsers[i] = new LeaderboardUser(
                    lbUser.get("PlayerCardID").toString(),
                    lbUser.get("TitleID").toString(),
                    Boolean.parseBoolean(lbUser.get("IsBanned").toString()),
                    Boolean.parseBoolean(lbUser.get("IsAnonymized").toString()),
                    lbUser.get("puuid").toString(),
                    lbUser.get("gameName").toString(),
                    lbUser.get("tagLine").toString(),
                    Integer.parseInt(lbUser.get("leaderboardRank").toString()),
                    Integer.parseInt(lbUser.get("rankedRating").toString()),
                    Integer.parseInt(lbUser.get("numberOfWins").toString()),
                    Integer.parseInt(lbUser.get("competitiveTier").toString())
            );
        }

    }

    public LeaderboardUser[] getLeaderboardUsers() {
        return leaderboardUsers;
    }
}
