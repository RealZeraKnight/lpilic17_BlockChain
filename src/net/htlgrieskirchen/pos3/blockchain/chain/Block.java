package net.htlgrieskirchen.pos3.blockchain.chain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Block {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("hh:mm:ss dd.MM.yyyy");
    
    protected String currentHash;
    protected int magicNumber;
    protected final String previousHash;
    
    private final String data;
    private final long timeStamp;

    public Block(String currentHash, String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.currentHash = currentHash;
    }
    
    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.currentHash = calculateHash(); // make sure you do this after we set all other values
    }

    public String getCurrentHash() {
        return currentHash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public final String calculateHash() {
        return StringUtil.applySha256(previousHash + Long.toString(timeStamp)
                + Integer.toString(magicNumber) + data);
    }

    @Override
    public String toString() {
        return "   > prev : " + previousHash + "\n"
                + "   > data : " + data + "\n"
                + "   > time : " + SDF.format(timeStamp) + "\n"
                + "   > hash : " + currentHash;
    }
    
}
