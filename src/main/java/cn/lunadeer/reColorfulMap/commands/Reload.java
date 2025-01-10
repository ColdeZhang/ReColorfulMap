package cn.lunadeer.reColorfulMap.commands;

import cn.lunadeer.reColorfulMap.Configuration;
import cn.lunadeer.reColorfulMap.Language;
import cn.lunadeer.reColorfulMap.utils.Notification;
import cn.lunadeer.reColorfulMap.utils.configuration.ConfigurationPart;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Reload implements TabExecutor {

    public static class ReloadCommandText extends ConfigurationPart {
        public String reloadingConfig = "Reloading configuration file...";
        public String reloadConfigSuccess = "Configuration file reloaded successfully!";
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Notification.info(sender, Language.reloadCommand.reloadingConfig);
        if (Configuration.load(sender)) {
            Notification.info(sender, Language.reloadCommand.reloadConfigSuccess);
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return List.of();
    }
}
