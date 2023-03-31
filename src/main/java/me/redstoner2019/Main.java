package me.redstoner2019;

import me.redstoner2019.gui.GUI;
import me.redstoner2019.util.Description;
import me.redstoner2019.util.Intersection;
import me.redstoner2019.util.Portal;
import me.redstoner2019.util.Tunnel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class Main {
    public static String latestError = "";
    public static Color tunnelColor = Color.RED;
    public static Color tunnelColorUnderConstruction = Color.ORANGE;
    public static Color intersectionColor = Color.GREEN;
    public static JSlider offsetYSlider = new JSlider();
    public static JSlider offsetXSlider = new JSlider();
    public static JSlider zoomSlider = new JSlider();
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        offsetYSlider.setMaximum(2048);
        offsetYSlider.setMinimum(0);
        offsetYSlider.setValue(1024);
        zoomSlider.setMaximum(100);
        zoomSlider.setMinimum(1);
        zoomSlider.setValue(50);
        offsetXSlider.setMaximum(2048);
        offsetXSlider.setMinimum(0);
        offsetXSlider.setValue(1024);
        File file = new File(System.getProperty("user.dir") + "\\MapEditor\\settings.set");
        if(!file.exists()){
            File dir = new File(System.getProperty("user.dir") + "\\MapEditor");
            dir.mkdir();
            file.createNewFile();
        }
        try{
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
            List<Object> objectList = (List<Object>) inputStream.readObject();
            inputStream.close();
            GUI.tunnelList = (List<Tunnel>) objectList.get(0);
            GUI.intersectionList = (List<Intersection>) objectList.get(1);
            GUI.portalList = (List<Portal>) objectList.get(2);
            GUI.descriptionList = (List<Description>) objectList.get(3);
            zoomSlider.setValue((Integer) objectList.get(4));
            offsetXSlider.setValue((Integer) objectList.get(5));
            offsetYSlider.setValue((Integer) objectList.get(6));
            Main.tunnelColor = (Color) objectList.get(7);
            Main.tunnelColorUnderConstruction = (Color) objectList.get(8);
            Main.intersectionColor = (Color) objectList.get(9);
        }catch (Exception e){
            e.printStackTrace();
        }

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                GUI.main(args);
            }
        });
        t.start();
    }
    public static Color lerp(Color color1, Color color2, float lerp) {
        float invLerp = 1.0f - lerp;
        float red = color1.getRed() * invLerp + color2.getRed() * lerp;
        float green = color1.getGreen() * invLerp + color2.getGreen() * lerp;
        float blue = color1.getBlue() * invLerp + color2.getBlue() * lerp;
        float alpha = color1.getAlpha() * invLerp + color2.getAlpha() * lerp;
        return new Color(red / 255.0f, green / 255.0f, blue / 255.0f, alpha / 255.0f);
    }
    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return dimg;
    }
}
