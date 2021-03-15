package br.com.strixcloud.bridge.provider;

import org.bukkit.Location;

public interface IWorldProvider {

    void load(Location top, Location down);

    void resetBlocks();

}
