package com.litf.death.Packeets;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SignEvent extends Event implements Cancellable {

    private final Player player;
    private final IChatBaseComponent[] lines;
    private final BlockPosition bp;
    private boolean isCancelled;
    public static final HandlerList HANDLERS = new HandlerList();
    public SignEvent(Player player, BlockPosition bp, IChatBaseComponent[] lines) {
        this.player = player;
        this.lines = lines;
        this.bp = bp;
    }

    public Player getPlayer(){
        return player;
    }
    public IChatBaseComponent[] getLines(){
        return lines;
    }
    public BlockPosition getPos(){
        return bp;
    }
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
    public static HandlerList getHandlerList(){
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        isCancelled=b;
    }
}
