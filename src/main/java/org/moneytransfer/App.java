package org.miniportfolio;

import org.miniportfolio.config.Database;
import org.miniportfolio.controllers.HealthController;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        port(Integer.parseInt(System.getenv().getOrDefault("PORT", "4567")));

        Database.init();

        new HealthController();

    }
}
