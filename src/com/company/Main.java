package com.company;

import java.util.ArrayList;

public class Main {

    public static ArrayList<Block> blockChain = new ArrayList<>();
    public static int difficulty = 99;

    public static void main(String... args) {
	// write your code here
        System.err.println("block 1: ");
        addBlock(new Block("block 1", "0"));

        System.err.println("block 2: ");
        addBlock(new Block("block 2", blockChain.get(blockChain.size()-1).hash));

        System.err.println("block 3: ");
        addBlock(new Block("block 3", blockChain.get(blockChain.size()-1).hash));

        System.out.println("\n check block:"+ isChainValid());

        System.out.println("\n block:");
        System.out.println(StringUtil.getJson(blockChain));
    }

    public static Boolean isChainValid(){
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0','0');

        for (int i=1; i < blockChain.size(); i++){
            currentBlock = blockChain.get(i);
            previousBlock = blockChain.get(i-1);
            if (!currentBlock.hash.equals(currentBlock.calculateHash())){
                System.err.println("Current hash not equal");
                return false;
            }
            if (!previousBlock.hash.equals(currentBlock.previousHash)){
                System.err.println("Previous hash not equal");
                return false;
            }
            if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)){
                System.err.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }

    public static void addBlock(Block block) {
        block.mineBlock(difficulty);
        blockChain.add(block);
    }
}
