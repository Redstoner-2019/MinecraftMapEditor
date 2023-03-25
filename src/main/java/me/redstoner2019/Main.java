package me.redstoner2019;

import me.redstoner2019.gui.GUI;
import me.redstoner2019.util.Description;
import me.redstoner2019.util.Intersection;
import me.redstoner2019.util.Portal;
import me.redstoner2019.util.Tunnel;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class Main {
    public static String latestError = "";
    public static void main(String[] args) throws IOException, ClassNotFoundException {
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
        }catch (Exception e){}

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
}
