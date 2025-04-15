package me.typical.autocraft.util;

import me.typical.autocraft.ACPlugin;
import me.typical.autocraft.config.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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
