package ru.rdavydov.tokenizier.utils;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
    public static String md5(String hashable) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(hashable.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest);
    }
}
