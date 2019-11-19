/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos3.blockchain.mining;

import net.htlgrieskirchen.pos3.blockchain.chain.MiningBlock;
import net.htlgrieskirchen.pos3.blockchain.chain.Block;

/**
 * 
 * @author Torsten Welsch
 */
public class MinerSerial  extends Miner {
 
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Block previous = BLOCKCHAIN.get(0);
        for (int i = 0; i < 100; i++) {
            MiningBlock miningBlock = new MiningBlock(previous);
            int magicNumber = 0;
            while (true) {
                if (miningBlock.mine(magicNumber)) {
                    BLOCKCHAIN.add(miningBlock);
                    long temp = System.currentTimeMillis();
                    System.out.println(DF.format(1000.0 * i / (temp - start)) + " coins/s");
                    break;
                }
                else {
                    magicNumber++;
                }
            }
            previous = miningBlock;
        }
     
        checkBlockchain();
        long end = System.currentTimeMillis();
        System.out.println("\n" + getCoins() + " coins mined in " + DF.format((end - start)/1000.0) + " s");
    }    
    
}
