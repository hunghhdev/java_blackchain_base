package com.company;

import java.util.Date;

public class Block {
    public String hash;
    public String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;

    public Block(String data, String previousHash) {
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash(){
        return StringUtil.applySha256(previousHash + timeStamp + nonce + data);
    }

    public void mineBlock(int difficulty){
        String target = StringUtil.getDifficultyString(difficulty);
        while (!hash.substring(0, difficulty).equals(target)){
            nonce++;
            hash = calculateHash();
        }
        System.err.println(hash);
    }

}
