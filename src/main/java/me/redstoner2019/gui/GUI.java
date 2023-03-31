package me.redstoner2019.gui;

import me.redstoner2019.Main;
import me.redstoner2019.util.Description;
import me.redstoner2019.util.Intersection;
import me.redstoner2019.util.Popup;
import me.redstoner2019.util.Portal;
import me.redstoner2019.util.Tunnel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.*;
import java.util.*;
import java.util.List;

import static me.redstoner2019.Main.*;

public class GUI<d> {

    public static java.util.List<Tunnel> tunnelList = new ArrayList<>();
    public static java.util.List<Intersection> intersectionList = new ArrayList<>();
    public static java.util.List<Portal> portalList = new ArrayList<>();
    public static List<Description> descriptionList= new ArrayList<>();

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI window = new GUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GUI() throws IOException, InterruptedException {
        initialize();
    }

    private void initialize() throws IOException, InterruptedException {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setBounds(0, 0, 1920, 1080);
        frame.setTitle("Generator");

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit? All progress will be lost if you havn't saved yet!", "Exit Program", JOptionPane.YES_NO_OPTION);
                if (confirmed == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    System.exit(0);
                }
            }
        });

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        panel.setBackground(Color.DARK_GRAY);

        JLabel renderLabel = new JLabel();
        renderLabel.setBounds(0,0,1080,1080);
        panel.add(renderLabel);

        int xOffsets = 1080;
        int xOffsetTopAdd = 80;

        JLabel explanation = new JLabel("Start x/z:                                                          End x/z:");
        explanation.setBounds(xOffsets+10+xOffsetTopAdd,5,1000,20);
        explanation.setForeground(Color.WHITE);
        panel.add(explanation);

        JLabel tunnelLabel = new JLabel("Tunnel:");
        tunnelLabel.setBounds(xOffsets+10,40,1000,20);
        tunnelLabel.setForeground(Color.WHITE);
        panel.add(tunnelLabel);

        JLabel intersectionLabel = new JLabel("Intersection:");
        intersectionLabel.setBounds(xOffsets+10,100,1000,20);
        intersectionLabel.setForeground(Color.WHITE);
        panel.add(intersectionLabel);

        JLabel portalLabel = new JLabel("Portal:");
        portalLabel.setBounds(xOffsets+10,160,1000,20);
        portalLabel.setForeground(Color.WHITE);
        panel.add(portalLabel);

        JLabel labelLabel = new JLabel("Label:");
        labelLabel.setBounds(xOffsets+10,220,1000,20);
        labelLabel.setForeground(Color.WHITE);
        panel.add(labelLabel);

        JTextField tunnelX1 = new JTextField();
        tunnelX1.setBounds(xOffsets+10+xOffsetTopAdd,40,100,20);
        panel.add(tunnelX1);

        JTextField tunnelY1 = new JTextField();
        tunnelY1.setBounds(xOffsets+120+xOffsetTopAdd,40,100,20);
        panel.add(tunnelY1);

        JTextField tunnelX2 = new JTextField();
        tunnelX2.setBounds(xOffsets+230+xOffsetTopAdd,40,100,20);
        panel.add(tunnelX2);

        JTextField tunnelY2 = new JTextField();
        tunnelY2.setBounds(xOffsets+340+xOffsetTopAdd,40,100,20);
        panel.add(tunnelY2);

        JButton tunnelAdd = new JButton("ADD");
        tunnelAdd.setBounds(xOffsets+620+xOffsetTopAdd,40,100,20);
        tunnelAdd.setBackground(Color.DARK_GRAY);
        tunnelAdd.setForeground(Color.WHITE);
        panel.add(tunnelAdd);

        JButton deselectCurrent = new JButton("DESELECT CURRENT");
        deselectCurrent.setBounds(xOffsets+520,300,300,20);
        deselectCurrent.setBackground(Color.DARK_GRAY);
        deselectCurrent.setForeground(Color.WHITE);
        panel.add(deselectCurrent);

        JCheckBox underConstruction = new JCheckBox("Under Construction");
        underConstruction.setBounds(xOffsets+450+xOffsetTopAdd,40,150,20);
        underConstruction.setBackground(Color.DARK_GRAY);
        underConstruction.setForeground(Color.WHITE);
        panel.add(underConstruction);

        DefaultListModel model = new DefaultListModel();
        final JList[] items = {new JList(model)};
        items[0].setBackground(Color.GRAY);
        items[0].setForeground(Color.WHITE);

        deselectCurrent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(items[0].isSelectionEmpty()){
                    Popup.showErrorPopup("You don't have anything selected!",false);
                } else {
                    items[0].clearSelection();
                }
            }
        });

        List<Object> objects = new ArrayList<>();

        tunnelAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tunnelAdd.getText().equals("ADD")){
                    tunnelList.add(new Tunnel(underConstruction.isSelected(),Integer.parseInt(tunnelX1.getText()),Integer.parseInt(tunnelY1.getText()),Integer.parseInt(tunnelX2.getText()),Integer.parseInt(tunnelY2.getText())));
                } else {
                    try{
                        int selected = items[0].getSelectedIndex();
                        Object o = objects.get(selected);
                        if(o instanceof Tunnel){
                            tunnelList.remove((Tunnel) o);
                        }
                        if(o instanceof Intersection){
                            intersectionList.remove((Intersection) o);
                        }
                        if(o instanceof Portal){
                            portalList.remove((Portal) o);
                        }
                        if(o instanceof Description){
                            descriptionList.remove((Description) o);
                        }
                        tunnelList.add(new Tunnel(underConstruction.isSelected(),Integer.parseInt(tunnelX1.getText()),Integer.parseInt(tunnelY1.getText()),Integer.parseInt(tunnelX2.getText()),Integer.parseInt(tunnelY2.getText())));
                        items[0].clearSelection();
                    }catch (Exception ignored){}
                }
            }
        });

        JTextField intersectionX1 = new JTextField();
        intersectionX1.setBounds(xOffsets+10+xOffsetTopAdd,100,100,20);
        panel.add(intersectionX1);

        JTextField intersectionY1 = new JTextField();
        intersectionY1.setBounds(xOffsets+120+xOffsetTopAdd,100,100,20);
        panel.add(intersectionY1);

        JTextField intersectionX2 = new JTextField();
        intersectionX2.setBounds(xOffsets+230+xOffsetTopAdd,100,100,20);
        panel.add(intersectionX2);

        JTextField intersectionY2 = new JTextField();
        intersectionY2.setBounds(xOffsets+340+xOffsetTopAdd,100,100,20);
        panel.add(intersectionY2);

        JButton intersectionAdd = new JButton("ADD");
        intersectionAdd.setBounds(xOffsets+620+xOffsetTopAdd,100,100,20);
        intersectionAdd.setBackground(Color.DARK_GRAY);
        intersectionAdd.setForeground(Color.WHITE);
        panel.add(intersectionAdd);

        intersectionAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(intersectionAdd.getText().equals("ADD")){
                    intersectionList.add(new Intersection(Integer.parseInt(intersectionX1.getText()),Integer.parseInt(intersectionY1.getText()),Integer.parseInt(intersectionX2.getText()),Integer.parseInt(intersectionY2.getText())));
                } else {
                    try{
                        int selected = items[0].getSelectedIndex();
                        Object o = objects.get(selected);
                        if(o instanceof Tunnel){
                            tunnelList.remove((Tunnel) o);
                        }
                        if(o instanceof Intersection){
                            intersectionList.remove((Intersection) o);
                        }
                        if(o instanceof Portal){
                            portalList.remove((Portal) o);
                        }
                        if(o instanceof Description){
                            descriptionList.remove((Description) o);
                        }
                        intersectionList.add(new Intersection(Integer.parseInt(intersectionX1.getText()),Integer.parseInt(intersectionY1.getText()),Integer.parseInt(intersectionX2.getText()),Integer.parseInt(intersectionY2.getText())));
                        items[0].clearSelection();
                    }catch (Exception ignored){}
                }
            }
        });

        JSeparator separator = new JSeparator();
        separator.setBounds(xOffsets+225+xOffsetTopAdd,10,10,235);
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setBackground(Color.DARK_GRAY);
        separator.setForeground(Color.GRAY);
        panel.add(separator);

        JSeparator separator2 = new JSeparator();
        separator2.setBounds(xOffsets,245,900,10);
        separator2.setBackground(Color.DARK_GRAY);
        separator2.setForeground(Color.GRAY);
        panel.add(separator2);

        JTextField portalX1 = new JTextField();
        portalX1.setBounds(xOffsets+10+xOffsetTopAdd,160,100,20);
        panel.add(portalX1);

        JTextField portalY1 = new JTextField();
        portalY1.setBounds(xOffsets+120+xOffsetTopAdd,160,100,20);
        panel.add(portalY1);

        JButton portalAdd = new JButton("ADD");
        portalAdd.setBounds(xOffsets+620+xOffsetTopAdd,160,100,20);
        portalAdd.setBackground(Color.DARK_GRAY);
        portalAdd.setForeground(Color.WHITE);
        panel.add(portalAdd);

        portalAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(portalAdd.getText().equals("ADD")){
                    portalList.add(new Portal(Integer.parseInt(portalX1.getText()),Integer.parseInt(portalY1.getText()),Integer.parseInt(portalX1.getText())+1,Integer.parseInt(portalY1.getText())+1));
                } else {
                    try{
                        int selected = items[0].getSelectedIndex();
                        Object o = objects.get(selected);
                        if(o instanceof Tunnel){
                            tunnelList.remove((Tunnel) o);
                        }
                        if(o instanceof Intersection){
                            intersectionList.remove((Intersection) o);
                        }
                        if(o instanceof Portal){
                            portalList.remove((Portal) o);
                        }
                        if(o instanceof Description){
                            descriptionList.remove((Description) o);
                        }
                        portalList.add(new Portal(Integer.parseInt(portalX1.getText()),Integer.parseInt(portalY1.getText()),Integer.parseInt(portalX1.getText())+1,Integer.parseInt(portalY1.getText())+1));
                        items[0].clearSelection();
                    }catch (Exception ignored){}
                }
            }
        });

        JTextField descriptionX1 = new JTextField();
        descriptionX1.setBounds(xOffsets+10+xOffsetTopAdd,220,100,20);
        panel.add(descriptionX1);

        JTextField descriptionY1 = new JTextField();
        descriptionY1.setBounds(xOffsets+120+xOffsetTopAdd,220,100,20);
        panel.add(descriptionY1);

        JButton descriptionAdd = new JButton("ADD");
        descriptionAdd.setBounds(xOffsets+620+xOffsetTopAdd,220,100,20);
        descriptionAdd.setBackground(Color.DARK_GRAY);
        descriptionAdd.setForeground(Color.WHITE);
        panel.add(descriptionAdd);

        JTextField descriptionText = new JTextField();
        descriptionText.setBounds(xOffsets+450+xOffsetTopAdd,220,150,20);
        panel.add(descriptionText);

        JLabel descriptionTextLabel = new JLabel("Label Text:");
        descriptionTextLabel.setForeground(Color.WHITE);
        descriptionTextLabel.setBounds(xOffsets+450+xOffsetTopAdd,190,150,20);
        panel.add(descriptionTextLabel);

        descriptionAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(descriptionAdd.getText().equals("ADD")){
                    descriptionList.add(new Description(Integer.parseInt(descriptionX1.getText()),Integer.parseInt(descriptionY1.getText()),descriptionText.getText()));
                } else {
                    try{
                        int selected = items[0].getSelectedIndex();
                        Object o = objects.get(selected);
                        if(o instanceof Tunnel){
                            tunnelList.remove((Tunnel) o);
                        }
                        if(o instanceof Intersection){
                            intersectionList.remove((Intersection) o);
                        }
                        if(o instanceof Portal){
                            portalList.remove((Portal) o);
                        }
                        if(o instanceof Description){
                            descriptionList.remove((Description) o);
                        }
                        descriptionList.add(new Description(Integer.parseInt(descriptionX1.getText()),Integer.parseInt(descriptionY1.getText()),descriptionText.getText()));
                        items[0].clearSelection();
                    }catch (Exception ignored){}
                }
            }
        });

        JLabel removeSection = new JLabel("General Settings");
        removeSection.setBounds(xOffsets+300,250,1000,40);
        removeSection.setForeground(Color.WHITE);
        Font font1 = removeSection.getFont();
        Map attributes1 = font1.getAttributes();
        attributes1.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        attributes1.put(TextAttribute.WEIGHT, true ? TextAttribute.WEIGHT_BOLD : TextAttribute.WEIGHT_REGULAR);
        attributes1.put(TextAttribute.SIZE, 28);
        removeSection.setFont(font1.deriveFont(attributes1));
        panel.add(removeSection);

        JScrollPane scroll = new JScrollPane(items[0]);
        scroll.setBounds(xOffsets+10,300,500,700);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scroll);

        JButton saveButton = new JButton("SAVE");
        saveButton.setBounds(xOffsets+520,360,300,20);
        saveButton.setBackground(Color.DARK_GRAY);
        saveButton.setForeground(Color.WHITE);
        panel.add(saveButton);

        JLabel zoomLabel = new JLabel("Zoom:");
        zoomLabel.setBounds(xOffsets+520,400,300,20);
        zoomLabel.setForeground(Color.WHITE);
        panel.add(zoomLabel);
        JLabel xLabel = new JLabel("X:");
        xLabel.setBounds(xOffsets+520,440,300,20);
        xLabel.setForeground(Color.WHITE);
        panel.add(xLabel);
        JLabel zLabel = new JLabel("Z:");
        zLabel.setForeground(Color.WHITE);
        zLabel.setBounds(xOffsets+520,480,300,20);
        panel.add(zLabel);

        offsetXSlider.setBackground(Color.DARK_GRAY);
        offsetXSlider.setForeground(Color.WHITE);
        offsetXSlider.setBounds(xOffsets+520,500,300,20);
        panel.add(offsetXSlider);

        offsetYSlider.setBackground(Color.DARK_GRAY);
        offsetYSlider.setForeground(Color.WHITE);
        offsetYSlider.setBounds(xOffsets+520,460,300,20);
        panel.add(offsetYSlider);

        zoomSlider.setBackground(Color.DARK_GRAY);
        zoomSlider.setForeground(Color.WHITE);
        zoomSlider.setBounds(xOffsets+520,420,300,20);
        panel.add(zoomSlider);

        JLabel errorLog = new JLabel("Current error: " + Main.latestError);
        errorLog.setBounds(xOffsets+ 10,1010,1000,20);
        errorLog.setForeground(Color.RED);
        panel.add(errorLog);

        JButton generateIntersections = new JButton("GENERATE INTERSECTIONS");
        generateIntersections.setBackground(Color.DARK_GRAY);
        generateIntersections.setForeground(Color.WHITE);
        generateIntersections.setBounds(xOffsets+520,480,300,20);
        generateIntersections.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Tunnel t1 : tunnelList){
                    for(Tunnel t2 : tunnelList){
                        if(t1 != t2){
                            int x1 = t1.getX1();
                            int x2 = t1.getX2();
                            int x3 = t2.getX1();
                            int x4 = t2.getX2();
                            int y1 = t1.getY1();
                            int y2 = t1.getY2();
                            int y3 = t2.getY1();
                            int y4 = t2.getY2();

                            int x5 = Math.max(x1, x3);
                            int y5 = Math.max(y1, y3);
                            int x6 = Math.min(x2, x4);
                            int y6 = Math.min(y2, y4);

                            if (x5 < x6 && y5 < y6) {
                                System.out.println("The rectangles intersect at points (" + x5 + "," + y5 + "), (" + x6 + "," + y5 + "), (" + x6 + "," + y6 + "), (" + x5 + "," + y6 + ")");
                                intersectionList.add(new Intersection(x5,y5,x6,y6));
                            } else {
                                System.out.println("The rectangles do not intersect.");
                            }
                        }
                    }
                }
            }
        });

        JButton DESELECT = new JButton("EDIT SELECTED");
        DESELECT.setBounds(xOffsets+520,540,300,20);
        DESELECT.setBackground(Color.DARK_GRAY);
        DESELECT.setForeground(Color.WHITE);
        panel.add(DESELECT);
        DESELECT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(items[0].isSelectionEmpty()){
                    Popup.showErrorPopup("You don't have anything selected!",false);
                    return;
                }
                Object o = model.get(items[0].getSelectedIndex());
                if(o instanceof Tunnel){
                    Tunnel t = (Tunnel) o;
                    tunnelX1.setText(String.valueOf(t.getX1()));
                    tunnelY1.setText(String.valueOf(t.getY1()));
                    tunnelX2.setText(String.valueOf(t.getX2()));
                    tunnelY2.setText(String.valueOf(t.getY2()));
                    underConstruction.setSelected(t.isUnderConstruction());
                }else if(o instanceof Intersection){
                    Intersection t = (Intersection) o;
                    intersectionX1.setText(String.valueOf(t.getX1()));
                    intersectionY1.setText(String.valueOf(t.getY1()));
                    intersectionX2.setText(String.valueOf(t.getX2()));
                    intersectionY2.setText(String.valueOf(t.getY2()));
                }else if(o instanceof Portal){
                    Portal t = (Portal) o;
                    portalX1.setText(String.valueOf(t.getX1()));
                    portalY1.setText(String.valueOf(t.getY1()));
                }else if(o instanceof Description){
                    Description t = (Description) o;
                    descriptionX1.setText(String.valueOf(t.getX1()));
                    descriptionY1.setText(String.valueOf(t.getY1()));
                    descriptionText.setText(t.getText());
                } else {
                    System.err.println("ERROR OCCURED");
                }
            }
        });
        JPanel previewColorIntersection = new JPanel();
        previewColorIntersection.setBounds(xOffsets+520+280,630,20,20);
        previewColorIntersection.setBackground(Main.intersectionColor);
        panel.add(previewColorIntersection);

        JPanel previewColorTunnelConstruction = new JPanel();
        previewColorTunnelConstruction.setBounds(xOffsets+520+280,600,20,20);
        previewColorTunnelConstruction.setBackground(Main.tunnelColorUnderConstruction);
        panel.add(previewColorTunnelConstruction);

        JPanel previewColorTunnel = new JPanel();
        previewColorTunnel.setBounds(xOffsets+520+280,570,20,20);
        previewColorTunnel.setBackground(Main.tunnelColor);
        panel.add(previewColorTunnel);

        JButton intersectionColor = new JButton("Select intersection Color");
        intersectionColor.setBounds(xOffsets+520,630,270,20);
        intersectionColor.setBackground(Color.DARK_GRAY);
        intersectionColor.setForeground(Color.WHITE);
        panel.add(intersectionColor);
        intersectionColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.intersectionColor = Popup.selectColor(frame,Color.RED,Color.GRAY,Color.RED);
            }
        });
        JButton tunnelColorUnderConstruction = new JButton("Select tunnel construction Color");
        tunnelColorUnderConstruction.setBounds(xOffsets+520,600,270,20);
        tunnelColorUnderConstruction.setBackground(Color.DARK_GRAY);
        tunnelColorUnderConstruction.setForeground(Color.WHITE);
        panel.add(tunnelColorUnderConstruction);
        tunnelColorUnderConstruction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.tunnelColorUnderConstruction = Popup.selectColor(frame,Color.RED,Color.GRAY,Color.RED);
            }
        });
        JButton tunnelColor = new JButton("Select tunnel Color");
        tunnelColor.setBounds(xOffsets+520,570,270,20);
        tunnelColor.setBackground(Color.DARK_GRAY);
        tunnelColor.setForeground(Color.WHITE);
        panel.add(tunnelColor);
        tunnelColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.tunnelColor = Popup.selectColor(frame,Color.RED,Color.GRAY,Color.RED);
            }
        });

        final BufferedImage[] imageREnder = {new BufferedImage(50, 50, 2)};

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ImageIO.write(imageREnder[0],"PNG",new File(System.getProperty("user.dir") + "\\MapEditor\\output.png"));
                    File file = new File(System.getProperty("user.dir") + "\\MapEditor\\settings.set");
                    ObjectOutputStream inputStream = new ObjectOutputStream(new FileOutputStream(file));
                    List<Object> objects = new ArrayList<>();
                    objects.add(tunnelList);
                    objects.add(intersectionList);
                    objects.add(portalList);
                    objects.add(descriptionList);
                    objects.add(zoomSlider.getValue());
                    objects.add(offsetXSlider.getValue());
                    objects.add(offsetYSlider.getValue());
                    objects.add(Main.tunnelColor);
                    objects.add(Main.tunnelColorUnderConstruction);
                    objects.add(Main.intersectionColor);
                    inputStream.writeObject(objects);
                    inputStream.close();
                    Popup.infoPopup("Everything has been saved!","Info");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Popup.showErrorPopup("An error occured! " + ex.getLocalizedMessage(),false);
                }
            }
        });
        items[0].isSelectionEmpty();

        JButton removeButton = new JButton("REMOVE");
        removeButton.setBounds(xOffsets+520,330,300,20);
        removeButton.setBackground(Color.DARK_GRAY);
        removeButton.setForeground(Color.WHITE);
        panel.add(removeButton);

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(items[0].isSelectionEmpty()){
                    Popup.showErrorPopup("Select an item to remove first!",false);
                    return;
                }
                if(Popup.showConfirmPopup("Do you really want to remove this item?")){
                    if(!items[0].isSelectionEmpty()){
                        try{
                            int selected = items[0].getSelectedIndex();
                            System.err.println(selected);
                            Object o = objects.get(selected);
                            if(o instanceof Tunnel){
                                System.err.println(tunnelList.size());
                                tunnelList.remove((Tunnel) o);
                                System.err.println(tunnelList.size());
                            }
                            if(o instanceof Intersection){
                                intersectionList.remove((Intersection) o);
                            }
                            if(o instanceof Portal){
                                portalList.remove((Portal) o);
                            }
                            if(o instanceof Description){
                                descriptionList.remove((Description) o);
                            }
                        }catch (Exception ex){}

                    } else {
                        System.err.println("NOTHING SELECTED");
                        Popup.showErrorPopup("Select an item to remove first!",false);
                    }
                } else {

                }
            }
        });

        Thread frameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    previewColorIntersection.setBackground(Main.intersectionColor);
                    previewColorTunnel.setBackground(Main.tunnelColor);
                    previewColorTunnelConstruction.setBackground(Main.tunnelColorUnderConstruction);

                    objects.clear();
                    objects.addAll(tunnelList);
                    objects.addAll(intersectionList);
                    objects.addAll(portalList);
                    objects.addAll(descriptionList);
                    List<String> names = new ArrayList<>();
                    for(Object o : objects){
                        if(o instanceof Tunnel){
                            Tunnel t = (Tunnel) o;
                            if(!names.contains("Tunnel: (" + t.getX1() + "/" + t.getY1() + ") - (" + t.getX2() + "/" + t.getY2() + ")")){
                                names.add("Tunnel: (" + t.getX1() + "/" + t.getY1() + ") - (" + t.getX2() + "/" + t.getY2() + ")");
                            }
                        }
                        if(o instanceof Intersection){
                            Intersection t = (Intersection) o;
                            if(!names.contains("Intersection: (" + t.getX1() + "/" + t.getY1() + ") - (" + t.getX2() + "/" + t.getY2() + ")")){
                                names.add("Intersection: (" + t.getX1() + "/" + t.getY1() + ") - (" + t.getX2() + "/" + t.getY2() + ")");
                            }
                        }
                        if(o instanceof Portal){
                            Portal t = (Portal) o;
                            if(!names.contains("Portal: (" + t.getX1() + "/" + t.getY1() + ") - (" + t.getX2() + "/" + t.getY2() + ")")){
                                names.add("Portal: (" + t.getX1() + "/" + t.getY1() + ") - (" + t.getX2() + "/" + t.getY2() + ")");
                            }
                        }
                        if(o instanceof Description){
                            Description t = (Description) o;
                            if(!names.contains("Description: (" + t.getX1() + "/" + t.getY1() + ") Text: " + t.getText())){
                                names.add("Description: (" + t.getX1() + "/" + t.getY1() + ") Text: " + t.getText());
                            }
                        }
                    }
                    int i = 0;
                    for(String s : names){
                        if(model.size()<=i){
                            model.add(i,s);
                        } else {
                            model.set(i,s);
                        }
                        i++;
                    }
                    while (model.size()>i){
                        model.removeElementAt(i);
                    }
                    errorLog.setText("Current error: " + Main.latestError);
                }
            }
        });
        frameThread.start();


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedImage image = null;
                try {
                    image = ImageIO.read(new File(System.getProperty("user.dir") + "\\MapEditor\\input.png"));
                } catch (Exception e) {
                    e.printStackTrace();
                    Main.latestError = "Map in " + System.getProperty("user.dir") + "\\MapEditor\\input.png not found!";
                    Popup.showErrorPopup("Map in " + System.getProperty("user.dir") + "\\MapEditor\\input.png not found! \nPlease export the map as a png and put it in the given Folder!",true);
                }
                float[] kernelData = {
                        0.0f, 0.0f, 0.0f,
                        0.0f, 0.5f, 0.0f,
                        0.0f, 0.0f, 0.0f
                };
                Kernel kernel = new Kernel(3, 3, kernelData);
                ConvolveOp op = new ConvolveOp(kernel);
                BufferedImage result = op.filter(image, null);
                image = result;
                while (true){
                    if(items[0].isSelectionEmpty()){
                        tunnelAdd.setText("ADD");
                        descriptionAdd.setText("ADD");
                        intersectionAdd.setText("ADD");
                        portalAdd.setText("ADD");
                    } else {
                        tunnelAdd.setText("REPLACE");
                        descriptionAdd.setText("REPLACE");
                        intersectionAdd.setText("REPLACE");
                        portalAdd.setText("REPLACE");
                    }
                    try {
                        BufferedImage renderImage = new BufferedImage(image.getWidth(),image.getHeight(),2);
                        long renderStart = System.currentTimeMillis();
                        int offsetX = 1024;
                        int offsetY = 1024;
                        double zoom = zoomSlider.getValue()/50.0;
                        renderImage = image;
                        Graphics2D g = (Graphics2D) renderImage.getGraphics();
                        int renders = 0;

                        for(Tunnel t : tunnelList){
                            renders++;
                            g.setColor(Main.tunnelColor);
                            if(t.isUnderConstruction()){
                                g.setColor(Main.tunnelColorUnderConstruction);
                            }
                            g.fillRect(t.getSize().getX()+offsetX,t.getSize().getY()+offsetY,t.getSize().getWidth(),t.getSize().getHeight());
                        }
                        for(Intersection t : intersectionList){
                            renders++;
                            g.setColor(Main.intersectionColor);
                            g.fillRect(t.getSize().getX()+offsetX,t.getSize().getY()+offsetY,t.getSize().getWidth(),t.getSize().getHeight());
                        }
                        for(Portal t : portalList){
                            renders++;
                            g.setColor(Color.BLACK);
                            g.fillRect(t.getSize().getX()+offsetX-2,t.getSize().getY()+offsetY-4,8,10);
                            g.setColor(Color.MAGENTA);
                            g.fillRect(t.getSize().getX()+offsetX,t.getSize().getY()+offsetY-2,4,6);
                        }
                        for(Description t : descriptionList){
                            renders++;
                            g.setColor(Color.DARK_GRAY);
                            g.fillRect(t.getX1()+offsetX-3,t.getY1()+offsetY+3,(int)((t.getText().length()*10)*(20.0/38.0)) + 5,10);
                            g.setColor(Color.WHITE);
                            Font font2 = g.getFont();
                            Map attributes2 = font2.getAttributes();
                            attributes2.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
                            attributes2.put(TextAttribute.SIZE, 10);
                            g.setFont(font2.deriveFont(attributes2));
                            g.drawString(t.getText(),t.getX1()+offsetX-1,t.getY1()+offsetY+11);
                        }
                        BufferedImage cropped = new BufferedImage(1080,1080,2);
                        /*try{
                            int x = offsetXSlider.getValue(); // x-coordinate of the upper-left corner of the region
                            int y = offsetYSlider.getValue(); // y-coordinate of the upper-left corner of the region
                            int width = ((int)(zoom*x))+cropped.getWidth(); // width of the region
                            int height = ((int)(zoom*x))+cropped.getHeight(); // height of the region
                            System.out.println(x + "/" + y + "/" + width + "/" + height + "/" + zoom);
                            cropped = Main.resize(image.getSubimage(x,y,width,height),cropped.getWidth(),cropped.getHeight());
                        }catch (Exception e){
                            cropped = image;
                        }*/
                        for(int y = -cropped.getHeight()/2;y<cropped.getHeight()/2;y++){
                            for(int x = -cropped.getWidth()/2;x<cropped.getWidth()/2;x++){
                                try{
                                    cropped.setRGB(x+(cropped.getWidth()/2),y + (cropped.getHeight()/2),renderImage.getRGB(((int)(zoom*x))+offsetXSlider.getValue(),((int)(zoom*y))+offsetYSlider.getValue()));
                                }catch (Exception ignored){}
                            }
                        }
                        g = (Graphics2D) cropped.getGraphics();
                        g.setColor(Color.DARK_GRAY);
                        g.fillRect(0,0,300,130);
                        g.setColor(Color.BLACK);
                        g.fillRect(0,125,300,5);
                        g.fillRect(295,0,5,125);
                        Font font = g.getFont();
                        Map attributes = font.getAttributes();
                        attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
                        attributes.put(TextAttribute.SIZE, 30);
                        g.setFont(font.deriveFont(attributes));
                        g.setColor(Main.intersectionColor);
                        g.drawString("Intersection",0,30);
                        g.setColor(Main.tunnelColorUnderConstruction);
                        g.drawString("Under Construction",0,60);
                        g.setColor(Main.tunnelColor);
                        g.drawString("Tunnel",0,90);
                        g.setColor(Color.MAGENTA);
                        g.drawString("Portal",0,120);
                        renderLabel.setIcon(new ImageIcon(cropped));
                        imageREnder[0] = cropped;
                        frame.setTitle("Rendered: " + renders + " Render time: " + (System.currentTimeMillis()-renderStart) + " ms");
                    } catch (Exception e) {
                        e.printStackTrace();
                        Main.latestError = "Map in " + System.getProperty("user.dir") + "\\MapEditor\\input.png not found!";
                        Popup.showErrorPopup("Map in " + System.getProperty("user.dir") + "\\MapEditor\\input.png not found! \nPlease export the map as a png and put it in the given Folder!",true);
                    }

                }
            }
        });
        t.start();
    }
}