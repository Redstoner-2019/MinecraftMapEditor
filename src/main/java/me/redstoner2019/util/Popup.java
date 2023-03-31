package me.redstoner2019.util;

import javax.swing.*;
import java.awt.*;

public class Popup {
    public static void showErrorPopup(String errorMessage,boolean close) {
        JFrame frame = new JFrame("Error");
        if(close){
            /*int confirmed = JOptionPane.showConfirmDialog(frame, errorMessage, "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE,UIManager.getIcon("OptionPane.errorIcon"));
            if (confirmed == JOptionPane.YES_OPTION) {
                frame.dispose();
                System.exit(0);
            }*/
            JOptionPane.showMessageDialog(frame, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            frame.dispose();
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(frame, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void infoPopup(String message,String title) {
        JFrame frame = new JFrame(title);
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    public static boolean showConfirmPopup(String message) {
        JFrame frame = new JFrame("Confirmation");
        int result = JOptionPane.showConfirmDialog(frame, message, "Confirmation", JOptionPane.OK_CANCEL_OPTION);
        return result == JOptionPane.OK_OPTION;
    }
    public static Color selectColor(Component parent, Color defaultColor, Color backgroundColor, Color previewColor) {
        JColorChooser chooser = new JColorChooser(defaultColor);
        chooser.setBackground(backgroundColor);
        chooser.setPreviewPanel(new JPanel());
        chooser.getSelectionModel().addChangeListener(e -> chooser.setColor(chooser.getColor()));

        Color selectedColor = JColorChooser.showDialog(parent, "Select a Color", chooser.getColor());
        if (selectedColor == null) {
            selectedColor = defaultColor;
        }
        return selectedColor;
    }
}
