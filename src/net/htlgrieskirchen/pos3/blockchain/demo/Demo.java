package net.htlgrieskirchen.pos3.blockchain.demo;

import net.htlgrieskirchen.pos3.blockchain.chain.TransactionBlock;
import net.htlgrieskirchen.pos3.blockchain.chain.MiningBlock;
import net.htlgrieskirchen.pos3.blockchain.chain.Block;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Demo {

    public static final List<Block> BLOCKCHAIN = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Block rootBlock = new Block("Hi I'm the first block", "0");
        System.out.println("Block 1\n" + rootBlock);
        BLOCKCHAIN.add(rootBlock);

        Block secondBlock = new Block("Yo I'm the second block", rootBlock.getCurrentHash());
        System.out.println("Block 2\n" + secondBlock);
        BLOCKCHAIN.add(secondBlock);
        
        if (checkBlockchain()) System.out.println("\nBlockchain is valid!\n");
        
        Block transactionBlock = new TransactionBlock("Jane", "Jon", secondBlock);
        System.out.println("Block 3\n" + transactionBlock);
        BLOCKCHAIN.add(transactionBlock);
        
        // ##### HACKING #####
        
//        Field data;
//        try {
//            data = Block.class.getDeclaredField("data");
//            data.setAccessible(true);
//            data.set(transactionBlock, "transaction : Jon > Jane");
//            System.out.println("Block 4 (hacked)\n" + transactionBlock);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
//        if (checkBlockchain()) System.out.println("\nBlockchain is valid!\n");

        // ##### MINING #####

        MiningBlock miningBlock = new MiningBlock(transactionBlock);
        System.out.println("Block 4\n" + miningBlock);
        System.out.println("Block 4\n" + "   > coin : " + miningBlock.getCoins());
        for (int i = 0; ; ++i) {
           if (miningBlock.mine(i)) break; 
        }
        System.out.println("Block 4\n" + "   > coin : " + miningBlock.getCoins());
        BLOCKCHAIN.add(miningBlock);
        
        if (checkBlockchain()) System.out.println("\nBlockchain is valid!\n");
        
        Block fifthBlock = new Block("Hey im the fifth block", miningBlock.getCurrentHash());
        System.out.println("Block 5\n" + miningBlock);
        BLOCKCHAIN.add(fifthBlock);
        
        if (checkBlockchain()) System.out.println("\nBlockchain is valid!\n");
    }
    
        public static boolean checkBlockchain() {
        Iterator<Block> i = BLOCKCHAIN.iterator();
        
        Block previous = i.next();
        while (i.hasNext()) {
            Block current = i.next();
            if (!previous.getCurrentHash().equals(current.getPreviousHash())) {
                throw new RuntimeException("Illegal block found: invalid previous block!\n"
                        + "Previous block:\n" + previous + "\n" 
                        + "Current block:\n" + current);
            }
            if (!current.getCurrentHash().equals(current.calculateHash())) {
                throw new RuntimeException("Illegal block found: invalid block hash!\n"
                        + "Current block:\n" + current + "\n" 
                        + "Calc hash : " + current.calculateHash());
                        
            }
            previous = current;
        }
        
        return true;
    }

}
