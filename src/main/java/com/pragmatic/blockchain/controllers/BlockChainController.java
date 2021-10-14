package com.pragmatic.blockchain.controllers;

import com.pragmatic.blockchain.models.Block;
import com.pragmatic.blockchain.models.BlockChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;


/**
 * @author KhalilKes
 */
@RestController
public class BlockChainController {


    @GetMapping("rest/add-block")
    public ResponseEntity<List<Block>> addBlock() {
        BlockChain pragmaCoin = new BlockChain();
        Block newBlock = new Block(1, "this is the first transaction", new Date());
        Block newBlock1 = new Block(2, "this is the second transaction", new Date());
        Block newBlock2 = new Block(3, "this is the third transaction", new Date());
        Block newBlock3 = new Block(4, "this is the fourth transaction", new Date());

        System.out.println("mining in process ...");
        pragmaCoin.addBlock(newBlock);
        System.out.println("mining in process ...");
        pragmaCoin.addBlock(newBlock1);
        System.out.println("mining in process ...");
        pragmaCoin.addBlock(newBlock2);
        System.out.println("mining in process ...");
        pragmaCoin.addBlock(newBlock3);

        //newBlock1.setData("this is a tampered data");

        //System.out.println("Is this blockchain valid = " + pragmaCoin.isChainValid());



        return new ResponseEntity(pragmaCoin.getChain(), HttpStatus.OK);
    }


}
