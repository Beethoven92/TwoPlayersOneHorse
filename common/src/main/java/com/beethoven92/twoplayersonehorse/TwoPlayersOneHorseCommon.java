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

    // Max number of riders allowed on entities that extend AbstractHorse
    public static final double MAX_PASSENGERS = 2;

    // Vertical offsets to adjust player position when the horse is rearing or jumping
    public static final double FIRST_PASSENGER_VERTICAL_OFFSET = 0.0;
    public static final double SECOND_PASSENGER_VERTICAL_OFFSET = -0.3;

    // Horizontal offsets to adjust player position in case there are two players on the horse
    public static final double FIRST_PASSENGER_HORIZONTAL_OFFSET = 0.2;
    public static final double SECOND_PASSENGER_HORIZONTAL_OFFSET = -0.6;

    // Used to move second passenger a bit towards horse's body when the horse is rearing/jumping
    public static final double SECOND_PASSENGER_REARING_HORIZONTAL_ADJUSTMENT = 0.3;


    public static void init()
    {
    }
}