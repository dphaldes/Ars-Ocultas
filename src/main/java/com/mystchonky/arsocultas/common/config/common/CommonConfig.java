package com.mystchonky.arsocultas.common.config.common;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {

    public final ForgeConfigSpec.ConfigValue<Integer> CONVERSION_RATE;

    public CommonConfig(ForgeConfigSpec.Builder builder) {
        CONVERSION_RATE = builder.comment("Conversion rate of LP into player mana").define("conversionRate", 10);
    }
}
