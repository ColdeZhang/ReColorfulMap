package cn.lunadeer.reColorfulMap.commands;

import cn.lunadeer.reColorfulMap.ImageMapItem;
import cn.lunadeer.reColorfulMap.Language;
import cn.lunadeer.reColorfulMap.utils.Notification;
import cn.lunadeer.reColorfulMap.utils.Scheduler;
import cn.lunadeer.reColorfulMap.utils.XLogger;
import cn.lunadeer.reColorfulMap.utils.configuration.ConfigurationPart;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ToMap implements TabExecutor {
    public static class ToMapCommandText extends ConfigurationPart {
        public String usage = "Usage /tomap <image-url> [scale (optional, default 1)]";
        public String scaleMustBeNumber = "Scale must be a number.";
        public String generatingMap = "Generating map...";
        public String generatedMap = "Generated map, frame size: %d x %d";
        public String generateMapFailed = "Failed to generate map: %s";
        public String playerOnly = "Only players can use this command.";
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            Notification.warn(sender, Language.toMapCommand.playerOnly);
            return true;
        }
        try {
            if (args.length < 1) {
                XLogger.warn(Language.toMapCommand.usage);
                return true;
            }
            String url = args[0];
            float scale = 1;
            if (args.length == 2) {
                try {
                    scale = Float.parseFloat(args[1]);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException(Language.toMapCommand.scaleMustBeNumber);
                }
            }
            float finalScale = scale;
            Notification.info(player, Language.toMapCommand.generatingMap);
            Scheduler.runTaskAsync(() -> {
                        ImageMapItem mapImage;
                        try {
                            mapImage = new ImageMapItem(player.getWorld(), url, finalScale);
                        } catch (Exception e) {
                            Notification.error(player, Language.toMapCommand.generateMapFailed.formatted(e.getMessage()));
                            return;
                        }
                        Notification.info(player, Language.toMapCommand.generatedMap.formatted(mapImage.getXCount(), mapImage.getYCount()));
                        player.getInventory().addItem(mapImage);
                    }
            );
        } catch (Exception e) {
            Notification.warn(player, Language.toMapCommand.generateMapFailed.formatted(e.getMessage()));
            return true;
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            return List.of("<image-url>");
        } else if (args.length == 2) {
            return List.of("1");
        } else {
            return List.of();
        }
    }
}
