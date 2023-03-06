package me.fero.interfaces;

import me.fero.api.MatchDataResult;
import me.fero.errors.ApiError;

import java.io.IOException;

public interface IMatchHandler {
    MatchDataResult getMatch(String matchId) throws ApiError, IOException;
}
