package org.miniportfolio;

import com.google.gson.Gson;

import java.util.HashMap;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        port(Integer.parseInt(System.getenv().getOrDefault("PORT", "4567")));
        Gson gson = new Gson();
        get("/health", (req, res) -> {
            res.type("application/json");
            HashMap<String, Object> map = new HashMap<>();
            map.put("status", "OK");
            return gson.toJson(map);
        });
    }
}
