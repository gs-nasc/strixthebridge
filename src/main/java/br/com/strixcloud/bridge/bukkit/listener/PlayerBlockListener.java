package br.com.strixcloud.bridge.bukkit.listener;

import br.com.strixcloud.bridge.StrixTheBridge;
import br.com.strixcloud.bridge.bukkit.reflection.ActionBarUtils;
import br.com.strixcloud.bridge.services.arena.game.point.ArenaGamePointController;
import lombok.var;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerBlockListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        var arena = StrixTheBridge.getInstance().getArena();
        var p = event.getPlayer();

        if (arena.isPlaying(p)) {
            if (event.getBlock().getType() == Material.STAINED_GLASS) {
                event.setCancelled(true);
                return;
            }

            var firstLoc = arena.getPrimaryTeam().getConfig().getPit();
            var secondLoc = arena.getSecondaryTeam().getConfig().getPit();

            var invalid = firstLoc.distanceSquared(p.getLocation()) <= Math.pow(arena.getConfig().getBaseRadius(), 2) ||
                    secondLoc.distanceSquared(p.getLocation()) <= Math.pow(arena.getConfig().getBaseRadius(), 2);
            if (invalid) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        var arena = StrixTheBridge.getInstance().getArena();
        var p = event.getPlayer();

        if (arena.isPlaying(p)) {
            var firstLoc = arena.getPrimaryTeam().getConfig().getPit();
            var secondLoc = arena.getSecondaryTeam().getConfig().getPit();

            var invalid = firstLoc.distanceSquared(p.getLocation()) <= Math.pow(arena.getConfig().getBaseRadius(), 2) ||
                    secondLoc.distanceSquared(p.getLocation()) <= Math.pow(arena.getConfig().getBaseRadius(), 2);
            if (invalid) {
                event.setCancelled(true);
            }
        }
    }

}
