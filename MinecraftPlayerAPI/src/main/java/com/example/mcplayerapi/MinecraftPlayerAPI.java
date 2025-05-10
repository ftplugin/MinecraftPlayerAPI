package com.example.mcplayerapi;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

import static spark.Spark.*;

public class MinecraftPlayerAPI extends JavaPlugin {
    private String token;

    @Override
    public void onEnable() {
        token = UUID.randomUUID().toString();
        getLogger().info("API token: " + token);

        port(4567); // Change port if needed
        get("/players", (req, res) -> {
            String reqToken = req.queryParams("token");
            if (token.equals(reqToken)) {
                res.type("application/json");
                return "{ \"online\": " + Bukkit.getOnlinePlayers().size() + " }";
            } else {
                res.status(403);
                return "Forbidden";
            }
        });
    }
}
