package br.com.strixcloud.bridge.services.account.data.save;

import br.com.strixcloud.bridge.data.AccountsDAO;
import br.com.strixcloud.bridge.entities.player.PlayerAccount;
import br.com.strixcloud.bridge.repository.IAccountsRepository;
import br.com.strixcloud.lib.util.log.IStrixLogger;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AccountDataSaveService {

    private final IAccountsRepository repository;
    private final IStrixLogger logger;

    public void execute() {
        List<PlayerAccount> accounts = AccountsDAO.getInstance().get()
                .stream()
                .filter(PlayerAccount::isUpdate)
                .collect(Collectors.toList());
        accounts.forEach(repository::update);
        logger.info(String.format("Successfully updated %s accounts in database", accounts.size()));
    }

}
