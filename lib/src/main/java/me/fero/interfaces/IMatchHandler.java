package me.fero.interfaces;

import me.fero.api.GetMatchDataResult;
import me.fero.api.GetMatchHistoryResult;
import me.fero.enums.MatchFilter;
import me.fero.enums.Region;
import me.fero.errors.ApiError;

import java.io.IOException;

public interface IMatchHandler {
    GetMatchDataResult getMatch(String matchId) throws ApiError, IOException;

    GetMatchHistoryResult getMatchHistory(String puuid, Region region, MatchFilter filter) throws ApiError, IOException;
    GetMatchHistoryResult getMatchHistory(String puuid, Region region) throws ApiError, IOException;

    GetMatchHistoryResult getMatchHistory(String name, String tag, Region region, MatchFilter filter) throws ApiError, IOException;
    GetMatchHistoryResult getMatchHistory(String name, String tag, Region region) throws ApiError, IOException;
}
