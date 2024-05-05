package org.example.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Hash implements HashFunction{
    MessageDigest instance;

    public MD5Hash(){
        try{
            this.instance = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e){
            System.out.println("MD5 Algorithm not found");
        }
    }

    @Override
    public long hash(String key) {
        instance.reset();
        instance.update(key.getBytes());
        byte[] digest = instance.digest();

        long h = 0;
        for (int i = 0; i < 4; i++) {
            h <<= 8;
            h |= ((long) digest[i]) & 0xFF;
        }
        return h;
    }
}
