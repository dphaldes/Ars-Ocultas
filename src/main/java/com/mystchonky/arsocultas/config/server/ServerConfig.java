package com.mystchonky.arsocultas.config.server;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ServerConfig {

    public final ModConfigSpec.BooleanValue CONTAINMENT_JARS_SOUL_GEM_PICKUP;
    public final ModConfigSpec.BooleanValue CONTAINMENT_JARS_SOUL_GEM_PLACE;

    public ServerConfig(ModConfigSpec.Builder builder) {
        CONTAINMENT_JARS_SOUL_GEM_PICKUP = builder.comment("Allow empty Soul Gems to be used on filled Containment Jars to pickup the contained mob").define("containment_jar.soul_gem.pickup", true);
        CONTAINMENT_JARS_SOUL_GEM_PLACE = builder.comment("Allow filled Soul Gems to be used on empty Containment Jars to place the mob into the jar").define("containment_jar.soul_gem.place", true);
    }
}
