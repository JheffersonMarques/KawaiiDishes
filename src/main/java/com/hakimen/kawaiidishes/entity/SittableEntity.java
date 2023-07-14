package com.hakimen.kawaiidishes.entity;

import com.hakimen.kawaiidishes.registry.EntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

import java.util.List;

public class SittableEntity extends Entity {

    public SittableEntity(EntityType<?> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    public SittableEntity(Level worldIn, BlockPos position) {
        super(EntityRegister.SEAT.get(), worldIn);
        this.setPos(position.getX() + 0.5D, position.getY()+0.125f, position.getZ() + 0.5D);
    }

    public static InteractionResult sitDown(Player player, Level level, BlockPos position) {
        List<SittableEntity> seats = level.getEntitiesOfClass(SittableEntity.class,
                new AABB(position.getX(), position.getY(), position.getZ(),
                        position.getX() + 1.0, position.getY() + 1.0, position.getZ() + 1.0)
        );
        if(seats.isEmpty())
        {
            SittableEntity seat = new SittableEntity(level, position);
            level.addFreshEntity(seat);
            player.startRiding(seat, false);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean canCollideWith(Entity entity) {
        return false;
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public double getPassengersRidingOffset() {
        return 0.25D;
    }

    @Override
    public float getPickRadius() {
        return 0.0f;
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public void move(MoverType typeIn, Vec3 pos) {

    }

    @Override
    public void playerTouch(Player entityIn) {

    }

    @Override
    public void positionRider(Entity passenger) {
        super.positionRider(passenger);
    }

    @Override
    public void push(Entity entityIn) {

    }

    @Override
    public void tick() {
        super.tick();

        if(!this.level.isClientSide)
        {
            if(this.getPassengers().isEmpty() || this.level.isEmptyBlock(this.blockPosition()))
            {
                this.remove(RemovalReason.DISCARDED);
                this.level.updateNeighbourForOutputSignal(blockPosition(), this.level.getBlockState(blockPosition()).getBlock());
            }
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {

    }

    protected void applyYawToEntity(Entity entityToUpdate) {
        entityToUpdate.setYBodyRot(this.yRotO);
        final float rotation = Mth.wrapDegrees(entityToUpdate.yRotO - this.yRotO);
        final float clampedRotation = Mth.clamp(rotation, -75.0F, 75.0F);
        entityToUpdate.yRotO += clampedRotation - rotation;
        entityToUpdate.setYHeadRot(entityToUpdate.yRotO);
    }

    @Override
    protected boolean canRide(Entity entityIn) {
        return entityIn instanceof Player;
    }

    @Override
    protected void checkInsideBlocks() {

    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {

    }
}