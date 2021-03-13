package br.com.strixcloud.bridge.repository;

import br.com.strixcloud.bridge.entities.PlayerAccount;

import java.util.List;

public interface IAccountsRepository {

    List<PlayerAccount> find();

    void save(PlayerAccount account);

    void update(PlayerAccount account);

    void delete(PlayerAccount account);

}
