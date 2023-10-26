package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractRecipe;
import me.teakivy.teakstweaks.utils.Key;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import java.util.ArrayList;
import java.util.List;

public class LogChests extends AbstractRecipe {

    public LogChests() {
        super("log-chests", Material.CHEST);
    }

    @Override
    public void registerRecipes() {
        List<Material> logList = new ArrayList<>();
        logList.add(Material.OAK_LOG);
        logList.add(Material.STRIPPED_OAK_LOG);
        logList.add(Material.BIRCH_LOG);
        logList.add(Material.STRIPPED_BIRCH_LOG);
        logList.add(Material.SPRUCE_LOG);
        logList.add(Material.STRIPPED_SPRUCE_LOG);
        logList.add(Material.DARK_OAK_LOG);
        logList.add(Material.STRIPPED_DARK_OAK_LOG);
        logList.add(Material.JUNGLE_LOG);
        logList.add(Material.STRIPPED_JUNGLE_LOG);
        logList.add(Material.ACACIA_LOG);
        logList.add(Material.STRIPPED_ACACIA_LOG);
        logList.add(Material.CRIMSON_STEM);
        logList.add(Material.STRIPPED_CRIMSON_STEM);
        logList.add(Material.WARPED_STEM);
        logList.add(Material.STRIPPED_WARPED_STEM);
        logList.add(Material.MANGROVE_LOG);
        logList.add(Material.STRIPPED_MANGROVE_LOG);
        logList.add(Material.CHERRY_LOG);
        logList.add(Material.STRIPPED_CHERRY_LOG);
        logList.add(Material.OAK_WOOD);
        logList.add(Material.STRIPPED_OAK_WOOD);
        logList.add(Material.BIRCH_WOOD);
        logList.add(Material.STRIPPED_BIRCH_WOOD);
        logList.add(Material.SPRUCE_WOOD);
        logList.add(Material.STRIPPED_SPRUCE_WOOD);
        logList.add(Material.DARK_OAK_WOOD);
        logList.add(Material.STRIPPED_DARK_OAK_WOOD);
        logList.add(Material.JUNGLE_WOOD);
        logList.add(Material.STRIPPED_JUNGLE_WOOD);
        logList.add(Material.ACACIA_WOOD);
        logList.add(Material.STRIPPED_ACACIA_WOOD);
        logList.add(Material.CRIMSON_HYPHAE);
        logList.add(Material.STRIPPED_CRIMSON_HYPHAE);
        logList.add(Material.WARPED_HYPHAE);
        logList.add(Material.STRIPPED_WARPED_HYPHAE);
        logList.add(Material.MANGROVE_WOOD);
        logList.add(Material.STRIPPED_MANGROVE_WOOD);
        logList.add(Material.CHERRY_WOOD);
        logList.add(Material.STRIPPED_CHERRY_WOOD);



        RecipeChoice logs = new RecipeChoice.MaterialChoice(logList);

        NamespacedKey key = Key.get("log_to_chests");
        ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(Material.CHEST, 4));
        recipe.shape("xxx", "x x", "xxx");
        recipe.setIngredient('x', logs);

        Bukkit.addRecipe(recipe);
    }
}
