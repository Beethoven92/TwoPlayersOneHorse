package com.beethoven92.twoplayersonehorse.mixin;


import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(LocalPlayer.class)
public abstract class TwoPlayersOneHorseLocalPlayerMixin extends AbstractClientPlayer
{

    @Shadow
    private Minecraft minecraft;

    protected TwoPlayersOneHorseLocalPlayerMixin(ClientLevel clientLevel, GameProfile gameProfile)
    {
        super(clientLevel, gameProfile);
    }

    // Prevent the second passenger of a mount from opening that mount inventory.
    // That is to avoid saddle/armor/chest slot modifications by the player who is not controlling the mount
    @Inject(at = @At("HEAD"), method = "sendOpenInventory", cancellable = true)
    private void openDefaultPlayerInventoryForSecondPassenger(CallbackInfo info)
    {
        // Check if the player who is opening the inventory is mounted on a vehicle
        if (((LocalPlayer)(Object)this).isPassenger())
        {
            Entity mount = ((LocalPlayer)(Object)this).getVehicle();

            // Check if the player is the controlling passenger.
            // If not, that player will open the default player inventory instead of the mount inventory
            if (mount instanceof AbstractHorse &&
                mount.hasControllingPassenger() &&
                !(mount.getControllingPassenger().is((LocalPlayer)(Object)this)))
            {
                // Open player inventory
                minecraft.setScreen(new InventoryScreen((LocalPlayer)(Object)this));
                info.cancel();
            }
        }
    }
}
