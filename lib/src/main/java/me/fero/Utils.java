package me.fero;

import me.fero.entities.account.Card;
import me.fero.entities.match.*;
import me.fero.entities.match.assets.Agent;
import me.fero.entities.match.assets.Assets;
import me.fero.entities.match.economy.Economy;
import me.fero.entities.match.economy.EconomyStat;
import me.fero.entities.match.economy.weapon.Resource;
import me.fero.entities.match.platform.OS;
import me.fero.entities.match.platform.Platform;
import me.fero.entities.match.round.*;
import me.fero.entities.match.round.events.plant.DoneBy;
import me.fero.entities.match.round.events.plant.PlantEvents;
import me.fero.entities.match.round.events.plant.PlayerLocation;
import me.fero.entities.mmr.Images;
import me.fero.entities.mmr.UserMMR;
import me.fero.entities.mmr.UserMMRHistory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Utils {
    public static boolean isError(int code) {
        return (code >= 400 && code <= 429) || code == 500;
    }

    public static UserMMRHistory parseUserMMRHistory(JSONObject data) {
        int currentTier = Integer.parseInt(data.get("currenttier").toString());
        String currentTierPatched = data.get("currenttierpatched").toString();

        int rankingInTier = Integer.parseInt(data.get("ranking_in_tier").toString());
        int mmrChangeToLastGame = Integer.parseInt(data.get("mmr_change_to_last_game").toString());
        int elo = Integer.parseInt(data.get("elo").toString());

        JSONObject imagesRaw = (JSONObject) data.get("images");
        Images images = new Images(imagesRaw.get("small").toString(), imagesRaw.get("large").toString(),
                imagesRaw.get("triangle_down").toString(), imagesRaw.get("triangle_up").toString());

        String date = data.get("date").toString();
        String dateRaw = data.get("date_raw").toString();

        return new UserMMRHistory(currentTier, currentTierPatched, images, rankingInTier, mmrChangeToLastGame,
                elo, date, dateRaw);
    }

    public static UserMMR parseUserMMR(JSONObject data) {
        int currentTier = Integer.parseInt(data.get("currenttier").toString());
        String currentTierPatched = data.get("currenttierpatched").toString();

        int rankingInTier = Integer.parseInt(data.get("ranking_in_tier").toString());
        int mmrChangeToLastGame = Integer.parseInt(data.get("mmr_change_to_last_game").toString());
        int elo = Integer.parseInt(data.get("elo").toString());

        String name = data.get("name").toString();
        String tag = data.get("tag").toString();
        boolean old = Boolean.parseBoolean(data.get("old").toString());

        JSONObject imagesRaw = (JSONObject) data.get("images");
        Images images = new Images(imagesRaw.get("small").toString(), imagesRaw.get("large").toString(),
                imagesRaw.get("triangle_down").toString(), imagesRaw.get("triangle_up").toString());

        return new UserMMR(currentTier, currentTierPatched, images, rankingInTier, mmrChangeToLastGame,
                elo, name, tag, old);
    }

    public static MatchPlayer[] parsePlayers(JSONArray players) {
        MatchPlayer[] parsedPlayers = new MatchPlayer[players.size()];

        for(int i = 0; i < players.size(); i++) {
            JSONObject player = (JSONObject) players.get(i);

            String puuid = player.get("puuid").toString();
            String team = player.get("team").toString();
            String name = player.get("name").toString();
            String tag = player.get("tag").toString();
            Integer level = Integer.parseInt(player.get("level").toString());
            String character =player.get("character").toString();
            Integer currentTier = Integer.parseInt(player.get("currenttier").toString());
            String currentTierPatched = player.get("currenttier_patched").toString();
            String playerCard = player.get("player_card").toString();
            String playerTitle = player.get("player_title").toString();
            String partyId = player.get("party_id").toString();

            JSONObject sessionPlaytimeRaw = (JSONObject) player.get("session_playtime");
            SessionPlaytime sessionPlaytime = new SessionPlaytime(
                    isNull("minutes", sessionPlaytimeRaw) ? null : Integer.parseInt(sessionPlaytimeRaw.get("minutes").toString()),
                    isNull("seconds", sessionPlaytimeRaw) ? null : Integer.parseInt(sessionPlaytimeRaw.get("seconds").toString()),
                    isNull("milliseconds", sessionPlaytimeRaw) ? null : Long.parseLong(sessionPlaytimeRaw.get("milliseconds").toString())
            );

            JSONObject behaviorRaw = (JSONObject) player.get("behavior");
            JSONObject friendlyFireRaw = (JSONObject) behaviorRaw.get("friendly_fire");

            Behaviour behaviour = new Behaviour(
                    Double.parseDouble(behaviorRaw.get("afk_rounds").toString()),
                    new FriendlyFire(
                            isNull("incoming", friendlyFireRaw) ? null :Integer.parseInt(friendlyFireRaw.get("incoming").toString()),
                            isNull("outgoing", friendlyFireRaw) ? null : Integer.parseInt(friendlyFireRaw.get("outgoing").toString())
                    ),
                    isNull("rounds_in_spawn", behaviorRaw) ? null : Double.parseDouble(behaviorRaw.get("rounds_in_spawn").toString())
            );

            JSONObject platformRaw = (JSONObject) player.get("platform");
            JSONObject osRaw = (JSONObject) platformRaw.get("os");

            Platform platform = new Platform(
                    platformRaw.get("type").toString(),
                    new OS(osRaw.get("name").toString(), osRaw.get("version").toString())
            );

            JSONObject abilityCastsRaw = (JSONObject) player.get("ability_casts");
            AbilityCasts abilityCasts = new AbilityCasts(
                    isNull("c_cast", abilityCastsRaw) ? null : Integer.parseInt(abilityCastsRaw.get("c_cast").toString()),
                    isNull("q_cast", abilityCastsRaw) ? null : Integer.parseInt(abilityCastsRaw.get("q_cast").toString()),
                    isNull("e_cast", abilityCastsRaw) ? null : Integer.parseInt(abilityCastsRaw.get("e_cast").toString()),
                    isNull("x_cast", abilityCastsRaw) ? null : Integer.parseInt(abilityCastsRaw.get("x_cast").toString())
            );

            JSONObject assetsRaw = (JSONObject) player.get("assets");
            JSONObject cardRaw = (JSONObject) assetsRaw.get("card");
            JSONObject agentRaw = (JSONObject) assetsRaw.get("agent");

            Assets assets = new Assets(
                    new Card(cardRaw.get("small").toString(), cardRaw.get("large").toString(),
                            cardRaw.get("wide").toString(), null),
                    new Agent(
                            agentRaw.get("small").toString(),
                            agentRaw.get("bust").toString(),
                            agentRaw.get("full").toString(),
                            agentRaw.get("killfeed").toString()
                    )
            );

            JSONObject statsRaw = (JSONObject) player.get("stats");
            Stats stats = new Stats(
                    Integer.parseInt(statsRaw.get("score").toString()),
                    Integer.parseInt(statsRaw.get("kills").toString()),
                    Integer.parseInt(statsRaw.get("deaths").toString()),
                    Integer.parseInt(statsRaw.get("assists").toString()),
                    Integer.parseInt(statsRaw.get("bodyshots").toString()),
                    Integer.parseInt(statsRaw.get("headshots").toString()),
                    Integer.parseInt(statsRaw.get("legshots").toString())
            );

            JSONObject economyRaw = (JSONObject) player.get("economy");
            JSONObject spent = (JSONObject) economyRaw.get("spent");
            JSONObject loadoutValue = (JSONObject) economyRaw.get("loadout_value");

            MatchPlayerEconomy economy = new MatchPlayerEconomy(
                    new EconomyStat(Integer.parseInt(spent.get("overall").toString()),
                            Integer.parseInt(spent.get("average").toString())),
                    new EconomyStat(Integer.parseInt(loadoutValue.get("overall").toString()),
                            Integer.parseInt(loadoutValue.get("average").toString()))
            );

            Integer damageMade = Integer.parseInt(player.get("damage_made").toString());
            Integer damageReceived = Integer.parseInt(player.get("damage_received").toString());

            parsedPlayers[i] = new MatchPlayer(
                    puuid, name, tag, team, level, character, currentTier, currentTierPatched,
                    playerCard, playerTitle, partyId, sessionPlaytime, behaviour, platform,
                    abilityCasts, assets, stats, economy, damageMade, damageReceived
            );
        }

        return parsedPlayers;
    }

    public static TeamStatus parseTeamStatus(JSONObject team) {

        Boolean hasWon = isNull("has_won", team) ? null : Boolean.parseBoolean(team.get("has_won").toString());
        Integer roundsWon = isNull("rounds_won", team) ? null : Integer.parseInt(team.get("rounds_won").toString());
        Integer roundsLost = isNull("rounds_lost", team) ? null : Integer.parseInt(team.get("rounds_lost").toString());

        return new TeamStatus(hasWon, roundsWon, roundsLost);
    }

    public static boolean isNull(String key, JSONObject ob) {
        return ob.get(key) == null;
    }

    public static Round parseRound(JSONObject round) {
        String winningTeam = round.get("winning_team").toString();
        String endType = round.get("end_type").toString();
        Boolean bombPlanted = Boolean.parseBoolean(round.get("bomb_planted").toString());
        Boolean bombDefused = Boolean.parseBoolean(round.get("bomb_defused").toString());
        PlantEvents plantEvents = parsePlantEvents((JSONObject) round.get("plant_events"));


        JSONArray playerStatsRaw = (JSONArray) round.get("player_stats");
        PlayerStat[] playerRoundStats = new PlayerStat[playerStatsRaw.size()];

        for(int i = 0; i < playerStatsRaw.size(); i++) {
            playerRoundStats[i] = parsePlayerRoundStat((JSONObject) playerStatsRaw.get(i));
        }

        return new Round(winningTeam, endType, bombPlanted, bombDefused, plantEvents, null, playerRoundStats);
    }

    private static PlantEvents parsePlantEvents(JSONObject plantEvents) {
        if(!isNull("plant_location", plantEvents))  {
            JSONObject plantLocationRaw = (JSONObject) plantEvents.get("plant_location");

            Location location = new Location(Integer.parseInt(plantLocationRaw.get("x").toString()),
                    Integer.parseInt(plantLocationRaw.get("y").toString()));

            JSONObject plantedByRaw = (JSONObject) plantEvents.get("planted_by");
            DoneBy plantedBy = new DoneBy(
                    plantedByRaw.get("puuid").toString(),
                    plantedByRaw.get("display_name").toString(),
                    plantedByRaw.get("team").toString()
            );
            String plantSite = plantEvents.get("plant_site").toString();
            Long plantTimeInRound = Long.parseLong(plantEvents.get("plant_time_in_round").toString());

            JSONArray playerLocationsRaw = (JSONArray) plantEvents.get("player_locations_on_plant");
            PlayerLocation[] playerLocations = parsePlayersLocation(playerLocationsRaw);

            return new PlantEvents(location, plantedBy, plantSite, plantTimeInRound, playerLocations);
        }

        return new PlantEvents(null, null, null,
                null, null);
    }

    private static PlayerStat parsePlayerRoundStat(JSONObject player) {
        String puuid = player.get("player_puuid").toString();
        String displayName = player.get("player_display_name").toString();
        String team = player.get("player_team").toString();
        Integer damage = Integer.parseInt(player.get("damage").toString());
        Integer bodyShots = Integer.parseInt(player.get("bodyshots").toString());
        Integer headShots = Integer.parseInt(player.get("headshots").toString());
        Integer legShots = Integer.parseInt(player.get("legshots").toString());
        Integer kills = Integer.parseInt(player.get("kills").toString());
        Integer score = Integer.parseInt(player.get("score").toString());
        Boolean wasAfk = Boolean.parseBoolean(player.get("was_afk").toString());
        Boolean wasPenalized = Boolean.parseBoolean(player.get("was_penalized").toString());
        Boolean stayedInSpawn = Boolean.parseBoolean(player.get("stayed_in_spawn").toString());

        JSONObject abilityCastsRaw = (JSONObject) player.get("ability_casts");
        AbilityCasts abilityCasts = new AbilityCasts(
                isNull("c_cast", abilityCastsRaw) ? null : Integer.parseInt(abilityCastsRaw.get("c_cast").toString()),
                isNull("q_cast", abilityCastsRaw) ? null : Integer.parseInt(abilityCastsRaw.get("q_cast").toString()),
                isNull("e_cast", abilityCastsRaw) ? null : Integer.parseInt(abilityCastsRaw.get("e_cast").toString()),
                isNull("x_cast", abilityCastsRaw) ? null : Integer.parseInt(abilityCastsRaw.get("x_cast").toString())
        );

        JSONArray damageEventsRaw = (JSONArray) player.get("damage_events");
        DamageEvent[] damageEvents = new DamageEvent[damageEventsRaw.size()];

        for(int i = 0; i < damageEventsRaw.size(); i++) {
            JSONObject ob = (JSONObject) damageEventsRaw.get(i);
            damageEvents[i] = parseDamageEvent(ob);
        }

        JSONArray killEventsRaw = (JSONArray) player.get("kill_events");
        KillEvent[] killEvents = new KillEvent[killEventsRaw.size()];

        for(int i = 0; i < killEventsRaw.size(); i++) {
            JSONObject ob = (JSONObject) killEventsRaw.get(i);
            killEvents[i] = parseKillEvent(ob);
        }

        JSONObject economyRaw = (JSONObject) player.get("economy");
        JSONObject weaponRaw = (JSONObject) economyRaw.get("weapon");
        JSONObject armorRaw = (JSONObject) economyRaw.get("armor");

        JSONObject weaponAssets = (JSONObject) weaponRaw.get("assets");
        JSONObject armorAssets = (JSONObject) armorRaw.get("assets");

        Economy economy = new Economy(
                Integer.parseInt(economyRaw.get("loadout_value").toString()),
                Integer.parseInt(economyRaw.get("remaining").toString()),
                Integer.parseInt(economyRaw.get("spent").toString()),
                new Resource(
                        isNull("id", weaponRaw) ? null : weaponRaw.get("id").toString(),
                        isNull("name", weaponRaw) ? null : weaponRaw.get("name").toString(),
                        new me.fero.entities.match.economy.weapon.Assets(
                                isNull("display_icon", weaponAssets) ? null : weaponAssets.get("display_icon").toString(),
                                isNull("killfeed_icon", weaponAssets) ? null : weaponAssets.get("killfeed_icon").toString()
                        )
                ),
                new Resource(
                        isNull("id", armorRaw) ? null : armorRaw.get("id").toString(),
                        isNull("name", armorRaw) ? null : armorRaw.get("name").toString(),
                        new me.fero.entities.match.economy.weapon.Assets(
                                isNull("display_icon", armorAssets) ? null : armorAssets.get("display_icon").toString(),
                                null
                        )
                )
        );

        return new PlayerStat(puuid, displayName, team, damage, bodyShots, headShots, legShots, kills,
                score, wasAfk, wasPenalized, stayedInSpawn, damageEvents, killEvents, abilityCasts, economy);
    }

    private static DamageEvent parseDamageEvent(JSONObject event) {
        String puuid = event.get("receiver_puuid").toString();
        String displayName = event.get("receiver_display_name").toString();
        String team = event.get("receiver_team").toString();

        Integer damage = Integer.parseInt(event.get("damage").toString());
        Integer bodyShots = Integer.parseInt(event.get("bodyshots").toString());
        Integer headShots = Integer.parseInt(event.get("headshots").toString());
        Integer legShots = Integer.parseInt(event.get("legshots").toString());

        return new DamageEvent(puuid, displayName, team, bodyShots, damage, headShots, legShots);
    }

    public static KillEvent parseKillEvent(JSONObject event) {
        Long killTimeInRound = Long.parseLong(event.get("kill_time_in_round").toString());
        Long killTimeInMatch = Long.parseLong(event.get("kill_time_in_match").toString());
        String killerPuuid = event.get("killer_puuid").toString();
        String killerDisplayName = event.get("killer_display_name").toString();
        String killerTeam = event.get("killer_team").toString();
        String victimPuuid = event.get("victim_puuid").toString();
        String victimDisplayName = event.get("victim_display_name").toString();
        String victimTeam = event.get("victim_team").toString();

        String damageWeaponId = event.get("damage_weapon_id").toString();
        String damageWeaponName = isNull("damage_weapon_name", event) ? null :
                event.get("damage_weapon_name").toString();

        JSONObject deathLocRaw = (JSONObject) event.get("victim_death_location");
        Location victimDeathLocation = new Location(
                Integer.parseInt(deathLocRaw.get("x").toString()),
                Integer.parseInt(deathLocRaw.get("y").toString())
        );

        Boolean secondaryFireMode = Boolean.parseBoolean(event.get("secondary_fire_mode").toString());
        PlayerLocation[] playerLocationsOnKill = parsePlayersLocation((JSONArray) event.get("player_locations_on_kill"));

        Assistant[] assistants = parseAssistants((JSONArray) event.get("assistants"));

        return new KillEvent(killTimeInRound, killTimeInMatch, killerPuuid, killerDisplayName, killerTeam, victimPuuid,
                victimDisplayName, victimTeam, victimDeathLocation, damageWeaponId, damageWeaponName, null,
                secondaryFireMode, playerLocationsOnKill, assistants);
    }

    private static PlayerLocation[] parsePlayersLocation(JSONArray players) {
        PlayerLocation[] playerLocations = new PlayerLocation[players.size()];

        for(int i = 0; i < players.size(); i++) {
            JSONObject loc = (JSONObject) players.get(i);

            String uuid = loc.get("player_puuid").toString();
            String displayName = loc.get("player_display_name").toString();
            String team = loc.get("player_team").toString();
            Double viewRadians = Double.parseDouble(loc.get("view_radians").toString());

            JSONObject xyRaw = (JSONObject) loc.get("location");
            Location xy = new Location(Integer.parseInt(xyRaw.get("x").toString()),
                    Integer.parseInt(xyRaw.get("y").toString()));

            playerLocations[i] = new PlayerLocation(uuid, displayName, team, xy, viewRadians);
        }

        return playerLocations;
    }

    private static Assistant[] parseAssistants(JSONArray assistantsRaw) {
        Assistant[] assistants = new Assistant[assistantsRaw.size()];

        for(int i = 0; i < assistantsRaw.size(); i++) {
            JSONObject ass = (JSONObject) assistantsRaw.get(i);
            String puuid = ass.get("assistant_puuid").toString();
            String displayName = ass.get("assistant_display_name").toString();
            String team = ass.get("assistant_team").toString();
            assistants[i] = new Assistant(puuid, displayName, team);
        }

        return assistants;
    }
}

