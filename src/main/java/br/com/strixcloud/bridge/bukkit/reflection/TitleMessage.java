package br.com.strixcloud.bridge.bukkit.reflection;

import com.google.gson.JsonObject;
import lombok.var;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class TitleMessage {

    private PacketPlayOutTitle title;
    private PacketPlayOutTitle subtitle;

    public TitleMessage(String text, String subTitle) {
        var titleText = new JsonObject();
        titleText.addProperty("text", text.replace("&", "§"));
        var subtitleText = new JsonObject();
        subtitleText.addProperty("text", subTitle.replace("&", "§"));
        this.title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a(titleText.toString()), 10, 20, 10);
        this.subtitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a(subtitleText.toString()), 10, 20, 10);
    }

    public void send(Player p) {
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(title);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(subtitle);
    }

    public void send() {
        Bukkit.getServer().getOnlinePlayers().forEach(this::send);
    }

}
