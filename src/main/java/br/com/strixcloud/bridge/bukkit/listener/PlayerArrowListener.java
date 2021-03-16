package br.com.strixcloud.bridge.bukkit.listener;

import br.com.strixcloud.bridge.StrixTheBridge;
import lombok.var;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerArrowListener implements Listener {

    @EventHandler
    public void onShot(EntityShootBowEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        var p = (Player) e.getEntity();
        new BukkitRunnable() {
            @Override
            public void run() {
                p.getInventory().setItem(8, new ItemStack(Material.ARROW));
            }
        }.runTaskLater(StrixTheBridge.getInstance(), 60L);
    }

}
