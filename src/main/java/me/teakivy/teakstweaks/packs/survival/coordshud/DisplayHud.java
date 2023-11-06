package me.teakivy.teakstweaks.packs.survival.coordshud;

import me.teakivy.teakstweaks.TeaksTweaks;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class DisplayHud {

    private static World world;

    static String hudMessage;

    public static void init() {
        hudMessage = "<gold>XYZ: <white><x> <y> <z>  <gold><direction>      <world_time>";
        world =  Bukkit.getWorld(TeaksTweaks.getInstance().getConfig().getString("packs.coords-hud.time-world"));
        if (world == null) world = Bukkit.getWorlds().get(0);
    }

    public static void showHud(Player player) {
        Bukkit.getScheduler().runTaskAsynchronously(TeaksTweaks.getInstance(), () -> {
            Location loc = player.getLocation().getBlock().getLocation();
            if (TeaksTweaks.getInstance().getConfig().getBoolean("packs.coords-hud.use-player-position")) {
                loc = player.getLocation();
            }

            Location playerLocation = player.getLocation();
            float playerDirection = playerLocation.getYaw();
            String directionAbbr = getDirectionAbbr(playerDirection);
            String worldTime = getWorldTime();

            player.sendActionBar(MiniMessage.miniMessage().deserialize(
                    hudMessage,
                    Placeholder.parsed("x", loc.getBlockX() + ""),
                    Placeholder.parsed("y", loc.getBlockY() + ""),
                    Placeholder.parsed("z", loc.getBlockZ() + ""),
                    Placeholder.parsed("direction", directionAbbr),
                    Placeholder.parsed("world_time", worldTime)));
        });
    }

    public static String getDirectionAbbr(float direction) {
        if (direction >= 135 || direction <= -135) return "N";
        if (direction >= -135 && direction <= -45) return "E";
        if (direction >= -45 && direction <= 45) return "S";
        if (direction >= 45 && direction <= 135) return "W";

        return "X";
    }

    public static String getWorldTime() {
        long ticks = world.getTime();
        int hours = (int) (((ticks / 1000) + 6) % 24);
        int minutes = (int) ((ticks % 1000 / 10) * 0.6);
        return String.format("%02d:%02d", hours, minutes);
    }

    public static String getWorld(Player player) {
        return player.getWorld().getName();
    }
}
