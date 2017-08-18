package com.pms.service.user;

import org.springframework.stereotype.Service;

@Service
public class VeriCode {
    private final int charNumber = 4;
    private final String variCodes =
            "0123456789QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";

    public char[] getChars(int charNumber){
        char[] chars = new char[charNumber];
        for (int i = 0 ; i < charNumber ; i ++){
            chars[i] = variCodes.charAt((int)(Math.random()*variCodes.length()));
        }
        return chars;
    }

}
