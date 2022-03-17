package com.zerotoproduction.firstrest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class BucketListController {

    private List<BucketList> myBucketList = new ArrayList();
    private final AtomicLong counter = new AtomicLong();

    public BucketListController(){
        myBucketList.add(new BucketList(counter.incrementAndGet(), "Visit Colosseum in Rome"));
    }

    @GetMapping(value = "/")
    public ResponseEntity index() {
        return ResponseEntity.ok("a");
    }

    @GetMapping(value = "/bucket")
    public BucketList getBucket(@RequestParam(value="id") Long id) {
        BucketList itemToReturn = null;
        for(BucketList bucket : myBucketList){
            if(bucket.getId() == id)
                itemToReturn = bucket;
        }

        return itemToReturn;

    }

    @PostMapping(value = "/")
    public ResponseEntity addToBucketList(@RequestParam(value="name") String name) {
        myBucketList.add(new BucketList(counter.incrementAndGet(), name));
        return ResponseEntity.ok(myBucketList);
    }

    @PutMapping(value = "/")
    public ResponseEntity updateBucketList(@RequestParam(value="name") String name, @RequestParam(value="id") Long id) {
        myBucketList.forEach(bucketList ->  {
            if(bucketList.getId() == id){
                bucketList.setName(name);
            }
        });
        return ResponseEntity.ok(myBucketList);
    }

    @DeleteMapping(value = "/")
    public ResponseEntity removeBucketList(@RequestParam(value="id") Long id) {
        BucketList itemToRemove = null;
        for(BucketList bucket : myBucketList){
            if(bucket.getId() == id)
                itemToRemove = bucket;
        }

        myBucketList.remove(itemToRemove);
        return ResponseEntity.ok(myBucketList);
    }
}
