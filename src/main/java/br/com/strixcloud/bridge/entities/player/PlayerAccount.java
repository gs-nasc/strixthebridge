package br.com.strixcloud.bridge.entities.player;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data @RequiredArgsConstructor
public class PlayerAccount {

    private final int id;
    private final String uuid;

    @NonNull private String player;
    @NonNull private double amount;

    @NonNull private PlayerStatistics statistics;

    private boolean update;

    public void setPlayer(String player) {
        this.player = player;
        update = true;
    }

    public void setAmount(double amount) {
        this.amount = amount;
        update = true;
    }
}
