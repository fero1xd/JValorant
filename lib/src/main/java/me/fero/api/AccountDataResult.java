package me.fero.api;

import me.fero.entities.account.AccountData;
import me.fero.entities.account.Card;
import me.fero.enums.Region;
import me.fero.io.Response;
import org.json.simple.JSONObject;


public class AccountDataResult extends ApiResponse {

    private final AccountData accountData;

    public AccountDataResult(Response response, JSONObject jsonObject) {
        super(response);

        JSONObject data = (JSONObject) jsonObject.get("data");

        String puuid = data.get("puuid").toString();
        String name = data.get("name").toString();
        String tag = data.get("tag").toString();
        String lastUpdated = data.get("last_update").toString();
        long lastUpdatedRaw = Long.parseLong(data.get("last_update_raw").toString());
        Region region = Region.findAny(data.get("region").toString());

        JSONObject cardJson = (JSONObject) data.get("card");

        Card card = new Card(cardJson.get("small").toString(), cardJson.get("large").toString(),
                cardJson.get("wide").toString(), cardJson.get("id").toString());
        int accountLevel = Integer.parseInt(data.get("account_level").toString());


        this.accountData = new AccountData(puuid, region, accountLevel, name, tag, card, lastUpdated, lastUpdatedRaw);
    }

    public AccountData getAccountData() {
        return accountData;
    }
}
