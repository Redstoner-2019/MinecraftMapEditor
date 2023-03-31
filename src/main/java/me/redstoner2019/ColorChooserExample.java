package me.redstoner2019;

import javax.swing.*;
import java.awt.*;

public class ColorChooserExample extends JFrame {
    private JPanel previewPanel;

    public ColorChooserExample() {
        super("Color Chooser Example");

        // Create a button that will display the color chooser dialog
        JButton chooseColorButton = new JButton("Choose Color");
        chooseColorButton.addActionListener(e -> {
            // Display the color chooser dialog
            Color selectedColor = JColorChooser.showDialog(this, "Select a color", previewPanel.getBackground());
            if (selectedColor != null) {
                // Update the preview panel with the selected color
                previewPanel.setBackground(selectedColor);
            }
        });

        // Create a preview panel that will display the selected color
        previewPanel = new JPanel();
        previewPanel.setPreferredSize(new Dimension(100, 100));
        previewPanel.setBackground(Color.WHITE);

        // Add the button and preview panel to the frame
        JPanel mainPanel = new JPanel();
        mainPanel.add(chooseColorButton);
        mainPanel.add(previewPanel);
        getContentPane().add(mainPanel);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ColorChooserExample();
    }
}

