package com.beethoven92.twoplayersonehorse;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(TwoPlayersOneHorseCommon.MOD_ID)
public class TwoPlayersOneHorseNeoForge
{

    public TwoPlayersOneHorseNeoForge(IEventBus eventBus)
    {
        TwoPlayersOneHorseCommon.init();
    }
}