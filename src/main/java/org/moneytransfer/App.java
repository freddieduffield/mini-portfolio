package org.moneytransfer;

import org.moneytransfer.api.TransferController;
import org.moneytransfer.config.Database;
import org.moneytransfer.api.HealthController;
import org.moneytransfer.transfer.TransferRepository;
import org.moneytransfer.transfer.TransferService;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        port(Integer.parseInt(System.getenv().getOrDefault("PORT", "4567")));

        TransferRepository transferRepo = new TransferRepository(Database.getDsl());
        TransferService transferService = new TransferService(transferRepo);

        Database.init();

        new HealthController();
        new TransferController(transferService);

    }
}
