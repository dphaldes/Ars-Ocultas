package com.mystchonky.arsocultas.config.common;

import net.neoforged.neoforge.common.ModConfigSpec;

public class CommonConfig {

    public final ModConfigSpec.ConfigValue<Integer> CONVERSION_RATE;

    public CommonConfig(ModConfigSpec.Builder builder) {
        CONVERSION_RATE = builder.comment("Conversion rate of LP into player mana").define("conversionRate", 10);
    }
}
