package net.htlgrieskirchen.pos3.blockchain.chain;

public final class TransactionBlock extends Block {
    
    public TransactionBlock(String from, String to, Block previous) {
        super("transaction : " + from + " > " + to, previous.currentHash);
    }
    
}
