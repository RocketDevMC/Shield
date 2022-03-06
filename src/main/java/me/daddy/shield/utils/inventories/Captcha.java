package me.daddy.shield.utils.inventories;

import me.daddy.shield.Shield;
import me.daddy.shield.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Random;

public class Captcha {

    public static void openCaptcha(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9, ChatColor.RED + "Click the emerald block!");
        Random random = new Random();

        int rand = random.nextInt(9);

        for(int i = 0; i < 9; i++){
            if(!(rand == i)){
                inventory.setItem(i, new ItemBuilder(Material.REDSTONE_BLOCK).create());
            }
        }
        inventory.setItem(rand, new ItemBuilder(Material.EMERALD_BLOCK).create());
        Bukkit.getScheduler().runTask(Shield.getPlugin(), () -> player.openInventory(inventory));
    }
}