package br.com.strixcloud.bridge.bukkit.reflection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

@RequiredArgsConstructor
public class FastBlockUtils {

    @Getter
    private static final FastBlockUtils instance = new FastBlockUtils();

    public void setBlock(CraftWorld world, int x, int y, int z, int blockId, byte data, boolean applyPhysics) {
        net.minecraft.server.v1_8_R3.World nmsWorld = world.getHandle();
        BlockPosition bp = new BlockPosition(x, y, z);
        IBlockData ibd = net.minecraft.server.v1_8_R3.Block.getByCombinedId(blockId + (data << 12));
        nmsWorld.setTypeAndData(bp, ibd, applyPhysics ? 3 : 2);
    }
}
