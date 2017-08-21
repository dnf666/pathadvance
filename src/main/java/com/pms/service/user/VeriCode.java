package com.pms.service.user;

import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;

@Service
public class VeriCode {
    private final int charNumber = 4;
    private final String variCodes =
            "0123456789QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";

    public char[] getChars(){
        return getChars(charNumber);
    }

    /**
     * 生成随机的字符，默认的大小是4
     * @param charNumber
     * @return
     */
    public char[] getChars(int charNumber){
        char[] chars = new char[charNumber];
        for (int i = 0 ; i < charNumber ; i ++){
            chars[i] = variCodes.charAt((int)(Math.random()*variCodes.length()));
        }
        return chars;
    }

    /**
     * 生辰过一个 BufferedImage 用来向前台页面进行推送
     * @param chars
     * @return
     */
    public BufferedImage getImage(char[] chars){
        BufferedImage image = new BufferedImage(68,22,BufferedImage.TYPE_INT_RGB);
        Color color = new Color(100,150,120);
        Graphics graphics = image.getGraphics();
        graphics.setColor(color);
        graphics.fillRect(0,0,68,22);
        graphics.setColor(new Color(0,0,0));
        graphics.drawChars(chars,0,chars.length,15,18);
        return image;
    }
}
