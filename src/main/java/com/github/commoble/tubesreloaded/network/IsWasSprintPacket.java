package com.github.commoble.tubesreloaded.network;

import java.util.function.Supplier;

import com.github.commoble.tubesreloaded.PlayerData;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class IsWasSprintPacket
{
	private boolean isSprintHeld;
	
	public IsWasSprintPacket(boolean isSprintHeld)
	{
		this.isSprintHeld = isSprintHeld;
	}
	
	public void write(PacketBuffer buf)
	{
		buf.writeByte(this.isSprintHeld ? 1 : 0);
	}
	
	public static IsWasSprintPacket read(PacketBuffer buf)
	{
		return new IsWasSprintPacket(buf.readByte() > 0);
	}
	
	public void handle(Supplier<NetworkEvent.Context> context)
	{
		ServerPlayerEntity player = context.get().getSender();
		if (player != null)
		{
			PlayerData.setSprinting(player.getUniqueID(), this.isSprintHeld);
		}
	}
}
