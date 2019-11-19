/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos3.blockchain.mining;

import net.htlgrieskirchen.pos3.blockchain.chain.MiningBlock;

/**
 * 
 * @author Torsten Welsch
 */
public class MinerParallel extends Miner {
    private static final int NUMBER_OF_THREADS = 4;
    private static final int NUMBER_OF_COINS = 100;
    
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        
        int firstNumber = 0;
        int interval = Integer.MAX_VALUE / NUMBER_OF_THREADS;
        Worker[] worker = new Worker[NUMBER_OF_THREADS];
        for (int i = 0; i < NUMBER_OF_THREADS; ++i) {
            worker[i] = new Worker(firstNumber, firstNumber + interval);
            firstNumber += interval;
        }
        
        for (int i = 0; i < NUMBER_OF_COINS; ++i) {
            for (int j = 0; j < worker.length; ++j) {
                worker[j].setBlock(new MiningBlock(BLOCKCHAIN.get(i)));
            }
            
            Thread[] threads = new Thread[NUMBER_OF_THREADS];
            for (int j = 0; j < threads.length; ++j) {
                threads[j] = new Thread(worker[j]);
                threads[j].start();
            }
        
            for (int j = 0; j < threads.length; ++j) {
                threads[j].join();
            }
            
            BLOCKCHAIN.add(Worker.getMinedBlock());
            long temp = System.currentTimeMillis();
            System.out.println(DF.format(1000.0 * i / (temp - start)) + " coins/s");
        }
        checkBlockchain();
        long end = System.currentTimeMillis();
        System.out.println("\n" + getCoins() + " coins mined in " + DF.format((end - start)/1000.0) + " s");
    }
    
}
