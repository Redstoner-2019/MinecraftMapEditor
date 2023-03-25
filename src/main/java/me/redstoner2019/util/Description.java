package me.redstoner2019.util;

import java.io.Serial;
import java.io.Serializable;

public class Description implements Serializable {
    @Serial
    private static final long serialVersionUID = 9176873029745254542L;
    private boolean underConstruction;
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private String text;
    public Description(int x1, int y1, int x2, int y2,String text){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public boolean isUnderConstruction() {
        return underConstruction;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public void setUnderConstruction(boolean underConstruction) {
        this.underConstruction = underConstruction;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }
    public int getWidth(){
        if(x1<x2){
            return x2-x1;
        } else {
            return x1-x2;
        }
    }
    public int getHeight(){
        if(y1<y2){
            return y2-y1;
        } else {
            return y1-y2;
        }
    }
    public Size getSize(){
        int width = 0;
        int height = 0;
        if(x1<x2){
            if(x1<0){
                width = (x2+Math.abs(x1))-(x1+Math.abs(x1));
            } else {
                width = x2-x1;
            }
        } else {
            if(x2<0){
                width = (x1+Math.abs(x2))-(x2+Math.abs(x2));
            } else {
                width = x1-x2;
            }
        }
        if(y1<y2){
            if(y1<0){
                height = (y2+Math.abs(y1))-(y1+Math.abs(y1));
            } else {
                height = y2-y1;
            }
        } else {
            if(y2<0){
                height = (y1+Math.abs(y2))-(y2+Math.abs(y2));
            } else {
                height = y1-y2;
            }
        }
        int x0 = 0;
        int y0 = 0;
        if(x1<x2){
            x0 = x1;
        } else {
            x0 = x2;
        }
        if(y1<y2){
            y0 = y1;
        } else {
            y0 = y2;
        }
        return new Size(x0,y0+1,width+1,height+1);
    }
}
