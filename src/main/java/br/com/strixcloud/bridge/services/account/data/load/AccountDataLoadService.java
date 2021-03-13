package br.com.strixcloud.bridge.services.account.data.load;

import br.com.strixcloud.bridge.data.AccountsDAO;
import br.com.strixcloud.bridge.repository.IAccountsRepository;
import br.com.strixcloud.lib.util.log.IStrixLogger;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccountDataLoadService {

    private final IAccountsRepository repository;
    private final IStrixLogger logger;

    public void execute() {
        AccountsDAO.getInstance().clear();
        repository.find().forEach(acc -> {
            AccountsDAO.getInstance().load(acc);
        });
        int count = AccountsDAO.getInstance().get().size();
        logger.info(String.format("Successfully loaded %s accounts", count));
    }

}
