package com.beethoven92.twoplayersonehorse;

import com.beethoven92.twoplayersonehorse.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwoPlayersOneHorseCommon
{
    public static final String MOD_ID = "twoplayersonehorse";
    public static final String MOD_NAME = "TwoPlayersOneHorse";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static final double MAX_PASSENGERS = 2;
    public static final double FIRST_PASSENGER_VERTICAL_OFFSET = 0.0;
    public static final double FIRST_PASSENGER_HORIZONTAL_OFFSET = 0.2;
    public static final double SECOND_PASSENGER_VERTICAL_OFFSET = -0.3;
    public static final double SECOND_PASSENGER_HORIZONTAL_OFFSET = -0.6;

    public static void init()
    {
    }
}