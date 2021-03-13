package br.com.strixcloud.bridge.services.account.data.load;

import br.com.strixcloud.bridge.StrixTheBridge;
import lombok.Getter;
import lombok.var;

public class AccountDataLoadController {

    @Getter
    private final static AccountDataLoadController instance = new AccountDataLoadController();

    private final AccountDataLoadService service;

    public AccountDataLoadController() {
        var repository = StrixTheBridge.getInstance().getAccountsRepository();
        var logger = StrixTheBridge.getInstance().getSLogger();
        service = new AccountDataLoadService(repository, logger);
    }

    public void handle() {
        service.execute();
    }

}
