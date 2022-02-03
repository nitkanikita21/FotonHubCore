package dev.foton.hubcore.modules.interfaces.input.sign;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.comphenix.protocol.wrappers.WrappedBlockData;
import com.comphenix.protocol.wrappers.nbt.NbtBase;
import com.comphenix.protocol.wrappers.nbt.NbtCompound;
import com.comphenix.protocol.wrappers.nbt.NbtFactory;
import com.comphenix.protocol.wrappers.nbt.NbtType;
import dev.foton.hubcore.Main;
import dev.foton.hubcore.modules.interfaces.input.BaseRequestInput;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class SignMenuInput extends BaseRequestInput {

    protected SignMenuInput(Player target, BiConsumer<Player, String[]> callback) {
        super(target, callback);
    }

    public static void requestInput(Player target, List<String> question, BiConsumer<Player, String[]> callback){
        PacketContainer blockChange = new PacketContainer(PacketType.Play.Server.BLOCK_CHANGE);

        Vector playerPosition = target.getLocation().toVector();

        blockChange.getBlockPositionModifier()
                .write(0, new BlockPosition(playerPosition));
        blockChange.getBlockData().write(0, WrappedBlockData.createData(Material.OAK_SIGN));

        try {
            Main.i.getProtocolManager().sendServerPacket(target, blockChange);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(
                    "Cannot send packet " + blockChange, e);
        }


        PacketContainer signData = new PacketContainer(PacketType.Play.Server.TILE_ENTITY_DATA);
        signData.getBlockPositionModifier()
                .write(0, new BlockPosition(playerPosition));

        NbtCompound signNBT = NbtFactory.ofCompound("");

        for (int line = 0; line < 4; line++) {
            signNBT.put("Text" + (line + 1), "{\"extra\":[{\"text\":\"" + (question.size() > line ? question.get(line) : "") + "\"}],\"text\":\"\"}");
        }

        signNBT.put("x", playerPosition.getX());
        signNBT.put("y", playerPosition.getY());
        signNBT.put("z", playerPosition.getZ());
        signNBT.put("id", "minecraft:sign");

        signData.getIntegers().write(0,9);
        signData.getNbtModifier().write(0, signNBT);



        try {
            Main.i.getProtocolManager().sendServerPacket(target, signData);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(
                    "Cannot send packet " + signData, e);
        }

        PacketContainer openSign = new PacketContainer(PacketType.Play.Server.OPEN_SIGN_EDITOR);
        openSign.getBlockPositionModifier()
                .write(0, new BlockPosition(playerPosition));

        try {
            Main.i.getProtocolManager().sendServerPacket(target, openSign);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(
                    "Cannot send packet " + openSign, e);
        }

        PacketContainer deleteSign = new PacketContainer(PacketType.Play.Server.BLOCK_CHANGE);
        deleteSign.getBlockPositionModifier()
                .write(0, new BlockPosition(playerPosition));
        deleteSign.getBlockData().write(0,
                WrappedBlockData.createData(target.getLocation().getBlock().getBlockData())
                );

        try {
            Main.i.getProtocolManager().sendServerPacket(target, deleteSign);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(
                    "Cannot send packet " + deleteSign, e);
        }
    }
}
