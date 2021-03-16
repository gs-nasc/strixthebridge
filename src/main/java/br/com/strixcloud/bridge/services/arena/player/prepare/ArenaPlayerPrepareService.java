package br.com.strixcloud.bridge.services.arena.player.prepare;

import br.com.strixcloud.bridge.entities.arena.Arena;
import lombok.AllArgsConstructor;
import lombok.var;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

@AllArgsConstructor
public class ArenaPlayerPrepareService {

    private final Arena arena;

    public void execute(ArenaPlayerPrepareDTO data) {
        var p = data.getPlayer();

        var team = arena.getTeam(p);
        var config = team.getConfig();

        var inv = p.getInventory();
        var block = new ItemStack(config.getBlockType(), 64, config.getData());
        var color = arena.getTeam(p) == arena.getPrimaryTeam() ? Color.BLUE : Color.RED;

        var pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        pickaxe.addEnchantment(Enchantment.DIG_SPEED, 2);

        inv.clear();

        inv.setItem(0, new ItemStack(Material.IRON_SWORD));
        inv.setItem(1, new ItemStack(Material.BOW));
        inv.setItem(2, pickaxe);
        inv.setItem(3, block);
        inv.setItem(4, block);

        var chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        var chestMeta = (LeatherArmorMeta) chestplate.getItemMeta();
        chestMeta.setColor(color);
        chestplate.setItemMeta(chestMeta);

        var helmet = new ItemStack(Material.LEATHER_CHESTPLATE);
        var helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
        helmetMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);

        inv.setBoots(new ItemStack(Material.IRON_BOOTS));
        inv.setLeggings(new ItemStack(Material.IRON_LEGGINGS));
        inv.setChestplate(chestplate);
        inv.setHelmet(helmet);

        p.setHealth(20);
        p.setGameMode(GameMode.SURVIVAL);
        p.setDisplayName(String.format("%s %s%s", config.getPrefix(), config.getColor(), p.getDisplayName()));

        p.teleport(team.getConfig().getSpawn().add(0, 1, 0));
    }

}
