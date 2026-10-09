package com.sumirelabs.mixin;

import com.sumirelabs.ExampleMod;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Client-only example. Mixin AP maps the method selector into the generated refmap. */
@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    @Inject(method = "createDisplay", at = @At("HEAD"))
    private void onCreateDisplay(CallbackInfo ci) {
        ExampleMod.LOGGER.info("{} client Mixin is active", com.sumirelabs.Reference.MOD_NAME);
    }
}
