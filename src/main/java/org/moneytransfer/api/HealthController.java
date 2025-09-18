package org.moneytransfer.api;

import com.google.gson.Gson;
import org.moneytransfer.config.Database;

import java.util.HashMap;

import static spark.Spark.get;

public class HealthController {
    public HealthController() {
        Gson gson = new Gson();
        get("/health", (req, res) -> {
            res.type("application/json");

            HashMap<String, Object> map = new HashMap<>();

            try {
                Database.getDsl().fetch("SELECT 1");
                map.put("status", "OK");
            } catch (Exception ex) {
                map.put("status", "FAILED");
            }

            return gson.toJson(map);
        });
    }
}
