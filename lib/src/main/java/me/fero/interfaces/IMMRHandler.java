package me.fero.interfaces;

import me.fero.api.UserMMRHistoryResult;
import me.fero.api.UserMMRResult;
import me.fero.entities.account.AccountData;
import me.fero.enums.Region;
import me.fero.errors.ApiError;

import java.io.IOException;

public interface IMMRHandler {
    UserMMRResult getMMRData(String name, String tag, Region region) throws IOException, ApiError;
    UserMMRResult getMMRData(String puuid, Region region) throws ApiError, IOException;
    UserMMRResult getMMRData(AccountData account) throws ApiError, IOException;
    UserMMRHistoryResult getMMRHistory(String name, String tag, Region region) throws ApiError, IOException;
    UserMMRHistoryResult getMMRHistory(String puuid, Region region) throws ApiError, IOException;
}
