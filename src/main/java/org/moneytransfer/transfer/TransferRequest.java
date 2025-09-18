package org.moneytransfer.transfer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.moneytransfer.transfer.valueobjects.Money;
import spark.Request;

import java.math.BigInteger;
import java.util.Currency;
import java.util.UUID;

public record TransferRequest(
        UUID fromId,
        UUID toId,
        Money amount
) {
    public static TransferRequest parseRequest(Request request) {
        Gson gson = new Gson();
        JsonObject json = gson.fromJson(request.body(), JsonObject.class);

        UUID fromId = UUID.fromString(json.get("fromId").getAsString());
        UUID toId = UUID.fromString(json.get("toId").getAsString());

        Money amount = new Money(BigInteger.valueOf(json.get("amount").getAsLong()), Currency.getInstance("USD"));


        return new TransferRequest(fromId, toId, amount);
    }
}
