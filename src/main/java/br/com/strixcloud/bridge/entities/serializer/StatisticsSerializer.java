package br.com.strixcloud.bridge.entities.serializer;

import br.com.strixcloud.bridge.entities.PlayerStatistics;
import br.com.strixcloud.lib.util.serializer.Serializer;
import com.google.gson.Gson;

public class StatisticsSerializer implements Serializer<PlayerStatistics, String> {
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
