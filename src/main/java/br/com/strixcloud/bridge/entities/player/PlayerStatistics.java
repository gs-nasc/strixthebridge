package br.com.strixcloud.bridge.entities.player;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class PlayerStatistics {

    private int kills;
    private int deaths;
    private double damageDealt;
    private double damageTaken;
    private int blocksPlaced;
    private int blocksBraked;

    public PlayerStatistics() {
        this.kills = 0;
        this.deaths = 0;
        this.damageDealt = 0;
        this.damageTaken = 0;
        this.blocksPlaced = 0;
        this.blocksBraked = 0;
    }

}
