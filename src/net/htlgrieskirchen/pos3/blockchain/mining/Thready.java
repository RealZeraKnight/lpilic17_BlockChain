package net.htlgrieskirchen.pos3.blockchain.mining;


import net.htlgrieskirchen.pos3.blockchain.chain.MiningBlock;

import java.util.concurrent.Callable;

public class Thready implements Callable<MiningBlock>
{

    private int von;
    private int abstand;
    private MiningBlock block;
    private static volatile MiningBlock foundBlock;

    public Thready(int von, int abstand) {
        this.von = von;
        this.abstand = abstand;
    }


    @Override
    public MiningBlock call() throws Exception {
        return null;
    }
}
