package cn.lunadeer.reColorfulMap.utils;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Common {

    public static boolean isPaper() {
        try {
            Class.forName("io.papermc.paper.threadedregions.scheduler.ScheduledTask");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static String SerializeLocation(Location location) {
        return location.getWorld().getName() + ":" + location.getBlockX() + ":" + location.getBlockY() + ":" + location.getBlockZ();
    }

    public static Location DeserializeLocation(String locationStr) {
        String[] parts = locationStr.split(":");
        return new Location(JavaPlugin.getPlugin(JavaPlugin.class).getServer().getWorld(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
    }

    /**
     * Delete a folder recursively.
     *
     * @param folder the folder to delete
     * @return true if success, false if failed
     */
    public static boolean DeleteFolderRecursively(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    DeleteFolderRecursively(f);
                } else {
                    if (!f.delete()) {
                        return false;
                    }
                }
            }
        }
        return folder.delete();
    }

}
