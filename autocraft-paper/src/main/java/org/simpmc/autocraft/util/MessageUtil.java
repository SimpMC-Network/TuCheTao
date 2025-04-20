package org.simpmc.autocraft.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.simpmc.autocraft.ACPlugin;
import org.simpmc.autocraft.config.ConfigManager;

import java.util.UUID;

public class MessageUtil {
    public static void sendMessage(Player player, String message) {
        ACPlugin.getInstance().getFoliaLib().getScheduler().runAtEntity(player, task -> {

            String prefix = ConfigManager.getMessageConfig().prefix;
            player.sendMessage(trans(prefix + message));
        });
    }

    public static void sendMessage(UUID playerUuid, String message) {
        Player player = Bukkit.getPlayer(playerUuid);
        if (player == null) {
            return;
        }
        ACPlugin.getInstance().getFoliaLib().getScheduler().runAtEntity(player, task -> {

            String prefix = ConfigManager.getMessageConfig().prefix;
            player.sendMessage(trans(prefix + message));
        });
    }

    public static String trans(String message) {
        return message.replace("&", "§");
    }

    public static void debug(String message) {
        if (ConfigManager.getMainConfig().debug) {
            ACPlugin.getInstance().getLogger().info(message);
        }
    }
}
