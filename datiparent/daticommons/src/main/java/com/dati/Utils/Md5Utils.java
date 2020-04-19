package com.dati.Utils;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Md5Utils {
    public static String toMd5Password(String password, String salt){
        Md5Hash md5Hash = new Md5Hash(password, salt, 2);
        return md5Hash.toString();
    }
}
