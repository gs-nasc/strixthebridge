package br.com.strixcloud.bridge.repository.sql;

import br.com.strixcloud.lib.sql.IQueryExecutor;
import lombok.Getter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public enum AccountQuery {

    /*
        Queries
     */

    CREATE_TABLE_MYSQL("CREATE TABLE IF NOT EXISTS `bridge_accounts` (`id` INT NOT NULL AUTO_INCREMENT, `uuid` VARCHAR(36) NOT NULL, `player` VARCHAR(60) NOT NULL, `amount` DOUBLE NOT NULL, `statistics` TEXT NOT NULL, PRIMARY KEY (`id`))"),
    CREATE_TABLE_SQLITE("CREATE TABLE IF NOT EXISTS bridge_accounts (id INTEGER(11) NOT NULL PRIMARY KEY AUTOINCREMENT, uuid VARCHAR(36) NOT NULL, player VARCHAR(60) NOT NULL, amount DOUBLE NOT NULL, statistics LONGTEXT NOT NULL)"),

    GET("SELECT * FROM bridge_accounts"),
    DELETE("DELETE FROM bridge_accounts WHERE uuid = ?"),
    UPDATE("UPDATE bridge_accounts SET player = ?, amount = ?, statistics = ? WHERE uuid = ?"),
    INSERT("INSERT INTO bridge_accounts (uuid, player, amount, statistics) VALUES (?, ?, ?, ?)");

    /*
        Methods
     */

    @Getter
    private final String query;

    AccountQuery(String query) {
        this.query = query;
    }

    public PreparedStatement prepare(IQueryExecutor executor) throws SQLException {
        return executor.prepare(this.query);
    }
}
