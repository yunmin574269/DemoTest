package com.grpc.model;

import org.tdf.crypto.Base58Utility;
import org.tdf.crypto.keystore.SMKeystore;
import org.tdf.sunflower.types.Block;

public class TestDemo {

    public static void main(String[] args) {
        new Block();
        Base58Utility.decode("11111");
        System.out.println(SMKeystore.generateKeyStore("11111"));
    }
}
