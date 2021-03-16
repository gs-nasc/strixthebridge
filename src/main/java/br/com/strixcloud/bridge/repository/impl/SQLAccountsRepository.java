package br.com.strixcloud.bridge.repository.impl;

import br.com.strixcloud.bridge.entities.player.PlayerAccount;
import br.com.strixcloud.bridge.entities.player.PlayerStatistics;
import br.com.strixcloud.bridge.repository.IAccountsRepository;
import br.com.strixcloud.bridge.repository.sql.AccountQuery;
import br.com.strixcloud.lib.entities.DatabaseType;
import br.com.strixcloud.lib.sql.IQueryExecutor;
import br.com.strixcloud.lib.util.log.IStrixLogger;
import br.com.strixcloud.lib.util.serializer.Serializer;
import lombok.AllArgsConstructor;
import lombok.var;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class SQLAccountsRepository implements IAccountsRepository {
    private final Serializer<PlayerStatistics, String> statisticsSerializer;
    private final IQueryExecutor queryExecutor;
    private final IStrixLogger logger;

    @Override
    public void initialize() {
        var query = AccountQuery.CREATE_TABLE_MYSQL;
        if (queryExecutor.getDBType() == DatabaseType.SQLITE) {
            query = AccountQuery.CREATE_TABLE_SQLITE;
        }

        try (var ps = query.prepare(queryExecutor)) {
            ps.execute();
        } catch (SQLException e) {
            logger.error("An error occurred when trying to initialize accounts repository");
            e.printStackTrace();
        }
    }

    @Override
    public List<PlayerAccount> find() {
        List<PlayerAccount> data = new ArrayList<>();
        try (
                var ps = AccountQuery.GET.prepare(queryExecutor);
                var rs = ps.executeQuery()) {
            while (rs.next()) {
                var id = rs.getInt("id");
                var uuid = rs.getString("uuid");
                var player = rs.getString("player");
                var amount = rs.getDouble("amount");

                PlayerStatistics statistics = statisticsSerializer.deserialize(rs.getString("statistics"));
                PlayerAccount account = new PlayerAccount(id, uuid, player, amount, statistics, false);
                data.add(account);
            }
        } catch (SQLException e) {
            logger.error("An error occurred when trying to find Accounts data");
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public void save(PlayerAccount account) {
        try (
                var ps = AccountQuery.INSERT.prepare(queryExecutor)
                ) {
            ps.setString(1, account.getUuid());
            ps.setString(2, account.getPlayer());
            ps.setDouble(3, account.getAmount());
            ps.setString(4, statisticsSerializer.serialize(account.getStatistics()));

            ps.execute();
        } catch (SQLException e) {
            logger.error("An error occurred when trying to save Accounts data");
            e.printStackTrace();
        }
    }

    @Override
    public void update(PlayerAccount account) {
        try (
                var ps = AccountQuery.UPDATE.prepare(queryExecutor)
        ) {
            ps.setString(1, account.getPlayer());
            ps.setDouble(2, account.getAmount());
            ps.setString(3, statisticsSerializer.serialize(account.getStatistics()));

            ps.setString(4, account.getUuid());

            ps.execute();
        } catch (SQLException e) {
            logger.error("An error occurred when trying to update Accounts data");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(PlayerAccount account) {
        try (
                var ps = AccountQuery.DELETE.prepare(queryExecutor)
        ) {
            ps.setString(1, account.getUuid());

            ps.execute();
        } catch (SQLException e) {
            logger.error("An error occurred when trying to delete Accounts data");
            e.printStackTrace();
        }
    }
}
