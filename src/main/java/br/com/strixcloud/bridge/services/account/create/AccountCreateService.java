package br.com.strixcloud.bridge.services.account.create;

import br.com.strixcloud.bridge.data.AccountsDAO;
import br.com.strixcloud.bridge.entities.player.PlayerAccount;
import br.com.strixcloud.bridge.entities.player.PlayerStatistics;
import br.com.strixcloud.bridge.repository.IAccountsRepository;
import lombok.AllArgsConstructor;
import lombok.var;

import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
public class AccountCreateService {

    private final IAccountsRepository repository;

    public void execute(AccountCreateDTO data) {
        if (AccountsDAO.getInstance().get(data.getUuid()) != null) {
            var acc = new PlayerAccount(data.getUuid(), data.getPlayer(), 0, new PlayerStatistics());

            CompletableFuture.runAsync(() -> {
                repository.save(acc);
            });
            AccountsDAO.getInstance().add(acc);
        }
    }

}
