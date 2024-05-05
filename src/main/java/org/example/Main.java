package org.example;

import java.util.List;
import java.util.stream.IntStream;
import org.example.consistent_hash_impl.ConsistentHash;
import org.example.storage_node.StorageNode;

public class Main {

    public static void main(String[] args) throws Exception {
        List<StorageNode> storage_nodes = List.of(new StorageNode("A", "239.67.52.72"),new StorageNode("B", "138.70.131.229")
            ,new StorageNode("C", "98.5.87.182"),new StorageNode("D", "11.225.158.95"),new StorageNode("E", "203.187.116.210"));

        ConsistentHash ch = new ConsistentHash();
        for(StorageNode node:storage_nodes){
            System.out.println("Including node : "+node);
            System.out.println("Hash Space Location : "+ch.addNode(node));
        }

        IntStream.rangeClosed(1,10).forEach(num -> {
            String file = "file"+num+".txt";
            System.out.println("File : "+file);
            StorageNode node = ch.assign(file);
            node.addFile(file);
            System.out.println("Resides on node : "+node);
        });

        storage_nodes.forEach(node -> System.out.println("Node "+node+" Files : "+node.getFiles()));

        System.out.println(ch.getHashSpace());

        ch.removeNode(storage_nodes.get(0));

        storage_nodes.stream().filter(node -> !node.getName().equalsIgnoreCase("A")).forEach(node -> {
            System.out.println("Node "+node+" Files : "+node.getFiles());
        });

        System.out.println(ch.getHashSpace());
    }
}