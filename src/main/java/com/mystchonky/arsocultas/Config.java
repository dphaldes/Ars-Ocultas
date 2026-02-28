package com.mystchonky.arsocultas;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    public static final Server SERVER;
    public static final ModConfigSpec SERVER_SPEC;

    static {
        var serverSpecPair = new ModConfigSpec.Builder().configure(Server::new);
        SERVER = serverSpecPair.getLeft();
        SERVER_SPEC = serverSpecPair.getRight();
    }

    public static class Server {
        public final ModConfigSpec.BooleanValue CONTAINMENT_JARS_SOUL_GEM_PICKUP;
        public final ModConfigSpec.BooleanValue CONTAINMENT_JARS_SOUL_GEM_PLACE;

        public Server(ModConfigSpec.Builder builder) {
            CONTAINMENT_JARS_SOUL_GEM_PICKUP = builder.comment("Allow empty Soul Gems to be used on filled Containment Jars to pickup the contained mob").define("containment_jar.soul_gem.pickup", true);
            CONTAINMENT_JARS_SOUL_GEM_PLACE = builder.comment("Allow filled Soul Gems to be used on empty Containment Jars to place the mob into the jar").define("containment_jar.soul_gem.place", true);
        }

    }

}
