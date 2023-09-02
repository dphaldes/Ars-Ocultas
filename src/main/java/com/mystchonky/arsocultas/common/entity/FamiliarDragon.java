package com.mystchonky.arsocultas.common.entity;

import com.hollingsworth.arsnouveau.api.familiar.IFamiliar;
import com.klikli_dev.occultism.common.entity.familiar.DragonFamiliarEntity;
import com.klikli_dev.occultism.registry.OccultismEntities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class FamiliarDragon extends DragonFamiliarEntity implements IFamiliar {

    public ResourceLocation holderID;

    public FamiliarDragon(EntityType<? extends DragonFamiliarEntity> ent, Level world) {
        super(ent, world);

    }

    @Override
    public EntityType<?> getType() {
        return OccultismEntities.DRAGON_FAMILIAR.get();
    }

    @Override
    public ResourceLocation getHolderID() {
        return holderID;
    }

    @Override
    public void setHolderID(ResourceLocation id) {
        this.holderID = id;
    }


    // Map Ars functions to Occultisms
    @Override
    public UUID getOwnerID() {
        return getOwnerId();
    }

    @Override
    public void setOwnerID(UUID uuid) {
        setFamiliarOwner((LivingEntity) ((ServerLevel) level).getEntity(uuid));
    }
}
