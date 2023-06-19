package com.hakimen.kawaiidishes.client.block_entity_renderers;

import com.hakimen.kawaiidishes.blocks.CoffeeMachineBlock;
import com.hakimen.kawaiidishes.blocks.block_entities.CoffeeMachineBlockEntity;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import org.joml.Quaternionf;

import java.util.Objects;

public class CoffeeMachineRenderer implements BlockEntityRenderer<CoffeeMachineBlockEntity> {
    BlockEntityRendererProvider.Context context;

    public CoffeeMachineRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public void render(CoffeeMachineBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        pPoseStack.pushPose();
        var itemStack = pBlockEntity.inventory.getStackInSlot(5);
        var pos = pBlockEntity.getBlockPos();
        var state = Objects.requireNonNull(pBlockEntity.getLevel()).getBlockState(pBlockEntity.getBlockPos());
        if(state.getBlock() == BlockRegister.coffeeMachine.get()) {
            var direction = state.getValue(CoffeeMachineBlock.FACING);
            if (direction == Direction.WEST) {
                pPoseStack.translate(0.25, .25f, .25f);
                pPoseStack.scale(0.65f, 0.65f, 0.65f);
                pPoseStack.mulPose(new Quaternionf().rotateXYZ(0, 3.1415f / 5f + 3.1415f / 2f, 0));
            } else if (direction == Direction.SOUTH) {
                pPoseStack.translate(0.25, .25f, .75f);
                pPoseStack.scale(0.65f, 0.65f, 0.65f);
                pPoseStack.mulPose(new Quaternionf().rotateXYZ(0, -3.1415f / 5f - 3.1415f / 2f, 0));
            } else if (direction == Direction.NORTH) {
                pPoseStack.translate(0.75, .25f, .25f);
                pPoseStack.scale(0.65f, 0.65f, 0.65f);
                pPoseStack.mulPose(new Quaternionf().rotateXYZ(0, -3.1415f / 5f + 3.1415f / 2f, 0));
            } else if (direction == Direction.EAST) {
                pPoseStack.translate(0.75, .25f, .75f);
                pPoseStack.scale(0.65f, 0.65f, 0.65f);
                pPoseStack.mulPose(new Quaternionf().rotateXYZ(0, 3.1415f / 5f - 3.1415f / 2f, 0));
            }
        }

        itemRenderer.renderStatic(null,
                itemStack,
                ItemDisplayContext.FIXED,
                false,
                pPoseStack,
                pBufferSource,
                Minecraft.getInstance().level,
                pPackedLight,
                pPackedOverlay,
                0);
        pPoseStack.popPose();
    }
}
