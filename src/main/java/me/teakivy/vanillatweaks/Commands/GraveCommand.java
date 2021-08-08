package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Packs.Survival.Graves.GraveEvents;
import me.teakivy.vanillatweaks.Utils.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class GraveCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);
    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";


    public GraveCommand() {
        super("grave", "/grave", "Keep Inventory stands no chance!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!main.getConfig().getBoolean("packs.graves.enabled")) {
            sender.sendMessage(vt + ChatColor.RED + "This pack is not enabled!");
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(vt + "This command can only be ran by a Player!");
            return true;
        }
        Player player = (Player) sender;

        if (args.length < 1) {
            if (main.getConfig().getBoolean("packs.graves.locatable")) {
                PersistentDataContainer data = player.getPersistentDataContainer();
                if (data.has(new NamespacedKey(main, "vt_graves_last"), PersistentDataType.STRING)) {
                    player.sendMessage(data.get(new NamespacedKey(main, "vt_graves_last"), PersistentDataType.STRING));
                } else {
                    player.sendMessage(vt + ChatColor.RED + "You don't have a recent grave!");
                }
            } else {
                player.sendMessage(vt + ChatColor.RED + "You are not allowed to locate your grave!");
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("locate")) {
            if (main.getConfig().getBoolean("packs.graves.locatable")) {
                PersistentDataContainer data = player.getPersistentDataContainer();
                if (data.has(new NamespacedKey(main, "vt_graves_last"), PersistentDataType.STRING)) {
                    player.sendMessage(data.get(new NamespacedKey(main, "vt_graves_last"), PersistentDataType.STRING));
                } else {
                    player.sendMessage(vt + ChatColor.RED + "You don't have a recent grave!");
                }
            } else {
                player.sendMessage(vt + ChatColor.RED + "You are not allowed to locate your grave!");
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("key")) {
            if (!player.isOp()) {
                player.sendMessage(vt +ChatColor.RED + "You must be OP to use this command!");
                return true;
            }
            player.getInventory().addItem(GraveEvents.getGraveKey());
            player.sendMessage(vt + ChatColor.GREEN + "Enjoy your key!");
        }

        if (args[0].equalsIgnoreCase("uninstall")) {
            if (!player.isOp()) {
                player.sendMessage(vt +ChatColor.RED + "You must be OP to use this command!");
                return true;
            }
            for (World world : Bukkit.getWorlds()) {
                for (Entity entity : world.getEntities()) {
                    if (entity.getScoreboardTags().contains("vt_grave")) {
                        entity.remove();
                    }
                }
            }
            player.sendMessage(vt + ChatColor.YELLOW + "Removed all loaded Graves!");
        }
        return false;
    }

    List<String> arguments = new ArrayList<String>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (arguments.isEmpty()) {
            arguments.add("locate");
            if (sender.isOp()) {
                arguments.add("key");
                arguments.add("uninstall");
            }
        }

        List<String> result = new ArrayList<String>();
        if (args.length == 1) {
            for (String a : arguments) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        }

        return null;
    }
}
