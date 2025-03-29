package com.mystchonky.arsocultas.config;

import com.mystchonky.arsocultas.config.client.ClientConfig;
import com.mystchonky.arsocultas.config.common.CommonConfig;
import com.mystchonky.arsocultas.config.server.ServerConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class BaseConfig {
    public static final CommonConfig COMMON;
    public static final ModConfigSpec COMMON_SPEC;

    public static final ServerConfig SERVER;
    public static final ModConfigSpec SERVER_SPEC;

    public static final ClientConfig CLIENT;
    public static final ModConfigSpec CLIENT_SPEC;

    static {
        Pair<CommonConfig, ModConfigSpec> commonSpecPair = new ModConfigSpec.Builder().configure(CommonConfig::new);
        COMMON = commonSpecPair.getLeft();
        COMMON_SPEC = commonSpecPair.getRight();

        Pair<ServerConfig, ModConfigSpec> serverSpecPair = new ModConfigSpec.Builder().configure(ServerConfig::new);
        SERVER = serverSpecPair.getLeft();
        SERVER_SPEC = serverSpecPair.getRight();

        Pair<ClientConfig, ModConfigSpec> clientSpecPair = new ModConfigSpec.Builder().configure(ClientConfig::new);
        CLIENT = clientSpecPair.getLeft();
        CLIENT_SPEC = clientSpecPair.getRight();
    }
}
