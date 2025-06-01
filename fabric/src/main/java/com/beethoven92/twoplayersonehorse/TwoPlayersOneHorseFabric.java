package com.beethoven92.twoplayersonehorse;

import net.fabricmc.api.ModInitializer;

public class TwoPlayersOneHorseFabric implements ModInitializer
{
    
    @Override
    public void onInitialize()
    {
        TwoPlayersOneHorseCommon.init();
    }
}
