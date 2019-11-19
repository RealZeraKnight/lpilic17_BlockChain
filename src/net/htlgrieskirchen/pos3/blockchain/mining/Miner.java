package net.htlgrieskirchen.pos3.blockchain.mining;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.htlgrieskirchen.pos3.blockchain.chain.Block;
import net.htlgrieskirchen.pos3.blockchain.chain.MiningBlock;

/**
 *
 * @author Torsten Welsch
 */
public class Miner {

    protected static final DecimalFormat DF = new DecimalFormat("0.000");
    protected static final List<Block> BLOCKCHAIN = new ArrayList<Block>(){{
        add(new Block("root", "0"));
    }};   
    
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
                throw new RuntimeException("Illegal block found: invalid block hash!\n" + current + "\n   > calc : " + current.calculateHash());
            }
            previous = current;
        }

        return true;
    }

    public static int getCoins() {
        int result = 0;

        for (Block block : BLOCKCHAIN) {
            if (block instanceof MiningBlock) {
                result += ((MiningBlock) block).getCoins();
            }
        }

        return result;
    }

}
