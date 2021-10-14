package com.pragmatic.blockchain.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author KhalilKes
 */
public class BlockChain {

    private int difficulty = 3;
    private ArrayList<Transaction> transactions = new ArrayList<>();
    private ArrayList<Block> chain = new ArrayList<Block>();

    public BlockChain() {
        this.chain.add(creatGenesisBlock());
    }

    private Block creatGenesisBlock(){
        return new Block(0, "Genesis Block", new Date(), "0" );
    }

    private Block getLastBlock(){
        return chain.get(chain.size() - 1);
    }

    public void addBlock(Block newBlock){

        newBlock.setPreviousHash(getLastBlock().getHash());
        newBlock.setHash(newBlock.miningTheBlock(this.difficulty));
        chain.add(newBlock);
    }

    public boolean isChainValid(){
        for(int i = 1; i < chain.size(); i++){
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);

            if(currentBlock.getHash() != currentBlock.calculateHash())
                return false;

            if(currentBlock.getPreviousHash() != previousBlock.getHash())
                return false;
        }

        return true;
    }

    public ArrayList<Block> getChain() {
        return chain;
    }

    public void setChain(ArrayList<Block> chain) {
        this.chain = chain;
    }
}
