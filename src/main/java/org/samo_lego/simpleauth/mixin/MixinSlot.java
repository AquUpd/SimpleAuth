package org.samo_lego.simpleauth.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import org.samo_lego.simpleauth.event.item.TakeItemCallback;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Slot.class)
public abstract class MixinSlot {
    // Denying item moving etc.
    @Inject(method = "canTakeItems(Lnet/minecraft/entity/player/PlayerEntity;)Z", at = @At(value = "HEAD"), cancellable = true)
    private void canTakeItems(PlayerEntity playerEntity, CallbackInfoReturnable<Boolean> cir) {
        ServerPlayerEntity player = (ServerPlayerEntity) playerEntity;
        ActionResult result = TakeItemCallback.EVENT.invoker().onTakeItem(player);

        if (result == ActionResult.FAIL) {
            // Canceling the item taking
            player.networkHandler.sendPacket(
                    new ScreenHandlerSlotUpdateS2CPacket(
                            -2,
                            player.getInventory().selectedSlot,
                            player.getInventory().getStack(player.getInventory().selectedSlot))
            );
            player.networkHandler.sendPacket(new ScreenHandlerSlotUpdateS2CPacket(-1, -1, player.currentScreenHandler.getCursorStack()));
            cir.setReturnValue(false);
        }
    }
}
