package me.fero.handlers;

import me.fero.Config;
import me.fero.api.MatchDataResult;
import me.fero.errors.ApiError;
import me.fero.interfaces.IMatchHandler;
import me.fero.io.Request;
import me.fero.io.Response;

import java.io.IOException;

public class MatchHandler implements IMatchHandler {


    @Override
    public MatchDataResult getMatch(String matchId) throws ApiError, IOException {
        Response response = Request.get(Config.unofficialUrlV2 + "/match/" + matchId);
        return new MatchDataResult(response);
    }

}
