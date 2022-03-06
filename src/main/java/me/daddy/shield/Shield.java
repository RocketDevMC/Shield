package me.daddy.shield;

import lombok.Getter;
import me.daddy.shield.listeners.InventoryClickListener;
import me.daddy.shield.listeners.InventoryCloseListener;
import me.daddy.shield.listeners.PlayerJoinListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Shield extends JavaPlugin {

    @Getter private static Shield plugin;
    @Getter
    private static List<Player> captchaList = new ArrayList<>();

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
		
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), Shield.getPlugin());
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), Shield.getPlugin());
        getServer().getPluginManager().registerEvents(new InventoryCloseListener(), Shield.getPlugin());
    }
}
