package com.beethoven92.twoplayersonehorse.mixin;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(AbstractHorse.class)
public abstract class TwoPlayersOneHorseAbstractHorseMixin extends Animal
{

    @Shadow
    private float standAnimO;  // Keeps track of the horse standing animation

    protected TwoPlayersOneHorseAbstractHorseMixin(EntityType<? extends Animal> entityType, Level level)
    {
        super(entityType, level);
    }

    // Sets the position of the first and the second passenger (in case there is one)
    @Inject(at = @At("HEAD"), method = "getPassengerAttachmentPoint", cancellable = true)
    private void getPassengersAttachmentPoint(Entity entity, EntityDimensions dimensions, float partialTick,
                                              CallbackInfoReturnable<Vec3> returnValue)
    {
        List<Entity> passengers = this.getPassengers();
        int passengerIndex = Math.max(passengers.indexOf(entity), 0);
        double secondPassengerOffset = 0.0;

        if (passengers.size() > 1)
        {
            // If there is more than one passenger, move the first one a bit ahead (+0.2) and place the second one behind (-0.6)
            secondPassengerOffset = passengerIndex == 0 ? 0.2 : -0.6;
        }
        // Just copy the vanilla method for setting passenger position with the addition of an offset value for the second passenger
        Vec3 secondPassengerPos = super.getPassengerAttachmentPoint(entity, dimensions, partialTick)
                                       .add(new Vec3(0.0,
                                                     0.15 * (double)this.standAnimO * (double)partialTick,
                                                     -0.7 * (double)this.standAnimO * (double)partialTick + secondPassengerOffset))
                                       .yRot(-((AbstractHorse)(Object)this).getYRot() * ((float)Math.PI / 180F));
        returnValue.setReturnValue(secondPassengerPos);
    }

    @Inject(at = @At("HEAD"), method = "mobInteract", cancellable = true)
    private void allowSecondPlayerToJoin(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> returnValue)
    {
        // Execute this only if the horse has already 1 (and only 1) passenger.
        // This allows for a second player to join the ride when right-clicking on the horse.
        // In any other case the vanilla right-click behaviour is applied.
        // However, if there is already 1 player on the horse, the other player isn't allowed to put or remove that horse armor and saddle.
        if (this.getPassengers().size() == 1)
        {
            player.startRiding(this);
            returnValue.setReturnValue(InteractionResult.sidedSuccess(this.level().isClientSide));
        }
    }

    // Needed for Entity::startRiding to work for a second passenger
    @Override
    protected boolean canAddPassenger(Entity passenger)
    {
        // Allowing 2 passengers on llamas would be quite useless since the player can't control it
        if ((AbstractHorse)(Object)this instanceof Llama) return super.canAddPassenger(passenger);

        return this.getPassengers().size() < 2;
    }
}
