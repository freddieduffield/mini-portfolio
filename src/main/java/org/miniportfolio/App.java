package org.miniportfolio;

import com.google.gson.Gson;
import org.miniportfolio.config.Database;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.HashMap;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        port(Integer.parseInt(System.getenv().getOrDefault("PORT", "4567")));

        Database.init();

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
