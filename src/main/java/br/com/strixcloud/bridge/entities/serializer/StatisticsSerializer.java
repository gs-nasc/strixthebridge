package br.com.strixcloud.bridge.entities.serializer;

import br.com.strixcloud.bridge.entities.player.PlayerStatistics;
import br.com.strixcloud.lib.util.serializer.Serializer;
import com.google.gson.Gson;
import lombok.Getter;

public class StatisticsSerializer implements Serializer<PlayerStatistics, String> {

    @Getter private static final StatisticsSerializer instance = new StatisticsSerializer();

    @Override
    public String serialize(PlayerStatistics playerStatistics) {
        Gson gson = new Gson();
        return gson.toJson(playerStatistics);
    }

    @Override
    public PlayerStatistics deserialize(String s) {
        Gson gson = new Gson();
        return gson.fromJson(s, PlayerStatistics.class);
    }
}
