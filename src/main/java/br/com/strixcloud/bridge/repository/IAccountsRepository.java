package br.com.strixcloud.bridge.repository;

import br.com.strixcloud.bridge.entities.player.PlayerAccount;

import java.util.List;

public interface IAccountsRepository {

    void initialize();

    List<PlayerAccount> find();

    void save(PlayerAccount account);

    void update(PlayerAccount account);

    void delete(PlayerAccount account);

}
