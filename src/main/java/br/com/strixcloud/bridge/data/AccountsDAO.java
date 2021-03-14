package br.com.strixcloud.bridge.data;

import br.com.strixcloud.bridge.entities.player.PlayerAccount;
import br.com.strixcloud.lib.data.DAO;
import lombok.Getter;

public class AccountsDAO extends DAO<String, PlayerAccount> {

    @Getter
    private static final AccountsDAO instance = new AccountsDAO();

    public void add(PlayerAccount playerAccount) {
        load(playerAccount);
    }

    public void load(PlayerAccount playerAccount) {
        add(playerAccount.getUuid(), playerAccount);
    }

    public void remove(String s) {
        delete(s);
    }
}
