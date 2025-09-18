package org.moneytransfer.api;

import com.google.gson.Gson;
import org.moneytransfer.transfer.TransferRequest;
import org.moneytransfer.transfer.TransferService;

import static spark.Spark.*;

public class TransferController {
    private final TransferService transferService;
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
        initRoutes();
    }

    public void initRoutes() {
        Gson gson = new Gson();
        post("/transfer", (request, response) -> {
            response.type("application/json");
            transferService.executeTransfer(TransferRequest.parseRequest(request));
            return response.body();
        });
    }
}
