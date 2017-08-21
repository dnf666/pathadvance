package hyx.com.pms.service;

import com.pms.service.user.VeriCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:springconfig.xml"})
public class VeriCodeTest {
    @Autowired
    VeriCode veriCode;
    @Test
    public void getChars() throws Exception {
        System.out.println(veriCode.getChars());
    }

    @Test
    public void getImage() throws Exception {
        char[] chars = veriCode.getChars();
        BufferedImage image = veriCode.getImage(chars);
        OutputStream outputStream = new FileOutputStream(new File("/Users/rhan/code/pms/src/main/test/hyx/com/pms/service/test.jpg"));
        ImageIO.write(image,"jpg",outputStream);
    }

}