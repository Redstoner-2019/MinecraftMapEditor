package me.redstoner2019.util;

import java.io.Serial;
import java.io.Serializable;

public class Description implements Serializable {
    @Serial
    private static final long serialVersionUID = 9176873029745254542L;
    private boolean underConstruction;
    private int x1;
    private int y1;
    private String text;
    public Description(int x1, int y1,String text){
        this.x1 = x1;
        this.y1 = y1;
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

    public int getY1() {
        return y1;
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
}
