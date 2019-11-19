package net.htlgrieskirchen.pos3.blockchain.mining;

import net.htlgrieskirchen.pos3.blockchain.chain.MiningBlock;

public class Worker implements Runnable {
    private MiningBlock block;
    private final int start;
    private final int end;

    private static volatile MiningBlock foundBlock;
    
    public Worker(int start, int end) {        
        this.start = start;
        this.end = end;
    }
    
    @Override
    public void run() {
        foundBlock = null;
        
        for (int i = start; i < end; ++i) {
            if (block.mine(i)) {
                foundBlock = block;
            }
            
            if (foundBlock != null) {
                break;
            }
        }
    }
    
    public void setBlock(MiningBlock block) {
        this.block = block;
    }
    
    public static MiningBlock getMinedBlock() {
        return foundBlock;
    }
}
