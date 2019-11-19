/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos3.blockchain.chain;

public final class MiningBlock extends Block {
    
    private static final int COINS = 1;
    private static final int DIFFICULTY = 4;
    private static final String TARGET = new String(new char[DIFFICULTY]).replace('\0', '0');
    
    private boolean valid;
    
    public MiningBlock(Block previous) {
        super("mining : " + COINS + " coin available", previous.currentHash);
        this.valid = false;
    }
    
    public int getCoins() {
        return valid ? COINS : 0;
    }
    
    public boolean mine(int magicNumber) {
        this.magicNumber = magicNumber;
        this.currentHash = calculateHash();
        this.valid = currentHash.substring(0, DIFFICULTY).equals(TARGET);
        
        return valid;
    }

}
