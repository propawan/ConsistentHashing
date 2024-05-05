package org.example.consistent_hash_impl;

import org.example.hash.HashFunction;
import org.example.hash.MD5Hash;
import org.example.storage_node.StorageNode;
import java.util.TreeMap;

public class ConsistentHash {
    private final int totalSlots;
    private final HashFunction hashFunction;
    private final TreeMap<Long, StorageNode> hashSpace;

    public ConsistentHash() {
        this.totalSlots = 50;
        this.hashFunction = new MD5Hash();
        this.hashSpace = new TreeMap<>();
    }

    public long addNode(StorageNode node) throws Exception {
        if (hashSpace.size() == totalSlots) {
            throw new Exception("Hash space is full");
        }

        long key = hashFunction.hash(node.getHost()) % this.totalSlots;

        if(!hashSpace.containsKey(key)){
            hashSpace.put(key, node);
        } else{
            throw new Exception("collision occurred");
        }

        return key;
    }

    public long removeNode(StorageNode node) throws Exception {
        if (hashSpace.isEmpty()) {
            throw new Exception("Hash space is empty");
        }

        long key = hashFunction.hash(node.getHost()) % this.totalSlots;

        if(hashSpace.containsKey(key)){
            StorageNode storageNodeToFill = hashSpace.get(findNextGreaterHashSpaceLocation(key));
            storageNodeToFill.addFiles(node.getFiles());
            hashSpace.remove(key);
        } else{
            throw new Exception("node does not exist");
        }

        return key;
    }

    public StorageNode assign(String item) {
        long key = hashFunction.hash(item) % this.totalSlots;
        System.out.println("Hash space for file : "+key);

        return hashSpace.get(findNextGreaterHashSpaceLocation(key));
    }

    public Long findNextGreaterHashSpaceLocation(long key){
        Long nextGreaterKey = hashSpace.higherKey(key);

        if(nextGreaterKey == null){
            nextGreaterKey = hashSpace.firstKey();
        }

        return nextGreaterKey;
    }

    public TreeMap<Long, StorageNode> getHashSpace() {
        return hashSpace;
    }
}

