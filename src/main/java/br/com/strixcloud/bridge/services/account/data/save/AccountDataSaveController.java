package br.com.strixcloud.bridge.services.account.data.save;

import br.com.strixcloud.bridge.StrixTheBridge;
import lombok.Getter;
import lombok.var;

public class AccountDataSaveController {

    @Getter
    private final static AccountDataSaveController instance = new AccountDataSaveController();

    private final AccountDataSaveService service;

    public AccountDataSaveController() {
        var repository = StrixTheBridge.getInstance().getAccountsRepository();
        var logger = StrixTheBridge.getInstance().getSLogger();
        service = new AccountDataSaveService(repository, logger);
    }

    public void handle() {
        service.execute();
    }

}
