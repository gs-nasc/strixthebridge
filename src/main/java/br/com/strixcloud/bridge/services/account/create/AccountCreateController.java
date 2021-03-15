package br.com.strixcloud.bridge.services.account.create;

import br.com.strixcloud.bridge.StrixTheBridge;
import lombok.Getter;
import lombok.var;
import org.bukkit.entity.Player;

public class AccountCreateController {

    @Getter
    private final static AccountCreateController instance = new AccountCreateController();

    private final AccountCreateService service;

    public AccountCreateController() {
        var repository = StrixTheBridge.getInstance().getAccountsRepository();
        service = new AccountCreateService(repository);
    }

    public void handle(Player p) {
        var dto = new AccountCreateDTO(p.getUniqueId().toString(), p.getName());
        service.execute(dto);
    }

}
