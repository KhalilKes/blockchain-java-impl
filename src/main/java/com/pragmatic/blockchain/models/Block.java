package com.pragmatic.blockchain.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author  KhalilKes
 */
public class Block {

    private int index;
    private String data;
    private Date timestamp;
    private String previousHash;
    private String hash;
    private int nonce;

    public Block(int index, String data, Date timestamp, String previousHash) {
        this.index = index;
        this.data = data;
        this.timestamp = timestamp;
        this.previousHash = previousHash;
        this.hash = this.calculateHash();
        this.nonce = 0;

    }

    public Block(int index, String data, Date timestamp) {
        this.index = index;
        this.data = data;
        this.timestamp = timestamp;
        this.hash = this.calculateHash();
        this.nonce = 0;
    }

    public String calculateHash(){
        try {
            MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
            String input = this.index + this.timestamp.toString() + this.previousHash + this.data + this.nonce;
            byte[] inputDigest = msgDigest.digest(input.getBytes());
            BigInteger inputDigestBigInt = new BigInteger(1, inputDigest);
            String hashtext = inputDigestBigInt.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    public String miningTheBlock(int difficulty){

        while(!(this.hash.substring(1, difficulty).equals(Arrays.toString(new int[difficulty - 1])
                .replace("[", "")
                .replace("]", "")
                .replace(",", "")
                .replace(" ", "")))){

            this.nonce++;
            this.hash = calculateHash();
        }
        return this.hash;

    }

    //getters & setters
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
