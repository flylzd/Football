package com.derby.football.utils;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class RandomID {

    public static String getRandom32ID() {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            UUID uuid = UUID.randomUUID();
            String guid = uuid.toString();
            md.update(guid.getBytes(),0,guid.length());
            return new BigInteger(1,md.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

//        return String.valueOf(Hex.encodeHex(UUID.randomUUID().toString().getBytes()));
    }
}
