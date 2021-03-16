package br.com.strixcloud.bridge.services.arena.player.pvp;

import br.com.strixcloud.bridge.bukkit.reflection.ActionBarUtils;
import br.com.strixcloud.bridge.data.AccountsDAO;
import br.com.strixcloud.bridge.services.arena.player.respawn.ArenaPlayerRespawnController;
import lombok.var;

public class ArenaPlayerPVPService {

    public void execute(ArenaPlayerPVPDTO data) {
        var attacker = data.getAttacker();
        var victim = data.getVictim();
        var damage = data.getDamage();

        var accAttacker = AccountsDAO.getInstance().get(attacker.getUniqueId().toString());
        var accVictim = AccountsDAO.getInstance().get(victim.getUniqueId().toString());

        accAttacker.getStatistics().setDamageDealt(accAttacker.getStatistics().getDamageDealt() + damage);
        accVictim.getStatistics().setDamageTaken(accAttacker.getStatistics().getDamageTaken() + damage);

        var attackerMsg = String.format("&8[&5&lBRIDGE&8] &fVida do &d%s &8-> &c%s&8/&c20", victim.getName(), victim.getHealth());
        ActionBarUtils.sendActionBarMessage(attacker, attackerMsg);

        if ((victim.getHealth() - damage) <= 0) {
            accAttacker.getStatistics().setKills(accAttacker.getStatistics().getKills() + 1);
            accVictim.getStatistics().setDeaths(accAttacker.getStatistics().getDeaths() + 1);

            var message = String.format("&8[&5&lBRIDGE&8] %s &fmatou %s", attacker.getDisplayName(), victim.getDisplayName());
            ActionBarUtils.broadcastActionBarMessage(message);
            ArenaPlayerRespawnController.getInstance().handle(victim);
        }
    }

}
