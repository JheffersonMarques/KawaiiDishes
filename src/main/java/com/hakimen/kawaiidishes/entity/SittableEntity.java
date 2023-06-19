package com.hakimen.kawaiidishes.entity;

import com.hakimen.kawaiidishes.registry.EntityRegister;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class SittableEntity extends Entity {

    private BlockState seat;

    public SittableEntity(EntityType<?> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    public SittableEntity(Level worldIn) {
        super(EntityRegister.SEAT.get(), worldIn);
    }

    @Override
    public boolean canCollideWith(Entity entity) {
        return false;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SittableEntity))
            return false;
        return super.equals(object);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
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
    public int hashCode() {
        return super.hashCode() + this.seat.hashCode();
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
    protected void positionRider(Entity pPassenger, MoveFunction pCallback) {
        super.positionRider(pPassenger, pCallback);
    }

    @Override
    public void push(Entity entityIn) {

    }

    @Override
    public void tick() {
        super.tick();
        if (this.seat == null) {
            this.seat = getCommandSenderWorld().getBlockState(blockPosition());
            if (this.seat.isAir()) {
                kill();
            }
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {

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