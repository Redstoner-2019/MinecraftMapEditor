package me.redstoner2019.gui;


//import org.bytedeco.javacv.FrameGrabber;

import me.redstoner2019.Main;
import me.redstoner2019.util.Description;
import me.redstoner2019.util.Intersection;
import me.redstoner2019.util.Portal;
import me.redstoner2019.util.Tunnel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;

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

    /**
     * Create the application.
     */
    public GUI() throws IOException, InterruptedException {
        initialize();
    }

    //scrollPane.setBounds(12, 256, 445, 100);

    /**
     * Initialize the contents of the frame.
     * //@param <d>
     */
    private void initialize() throws IOException, InterruptedException {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, 1920, 1080);
        frame.setTitle("Generator");

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        panel.setBackground(Color.DARK_GRAY);

        JLabel renderLabel = new JLabel();
        renderLabel.setBounds(0,0,1080,1080);
        panel.add(renderLabel);

        int xOffsets = 1080;

        JLabel explanation = new JLabel("X1                                Y1                                X2                                Y2                                Add                                 Special Values:");
        explanation.setBounds(xOffsets+10,10,1000,20);
        explanation.setForeground(Color.WHITE);
        panel.add(explanation);

        JTextField tunnelX1 = new JTextField();
        tunnelX1.setBounds(xOffsets+10,40,100,20);
        panel.add(tunnelX1);

        JTextField tunnelY1 = new JTextField();
        tunnelY1.setBounds(xOffsets+120,40,100,20);
        panel.add(tunnelY1);

        JTextField tunnelX2 = new JTextField();
        tunnelX2.setBounds(xOffsets+230,40,100,20);
        panel.add(tunnelX2);

        JTextField tunnelY2 = new JTextField();
        tunnelY2.setBounds(xOffsets+340,40,100,20);
        panel.add(tunnelY2);

        JButton tunnelAdd = new JButton("ADD");
        tunnelAdd.setBounds(xOffsets+450,40,100,20);
        tunnelAdd.setBackground(Color.DARK_GRAY);
        tunnelAdd.setForeground(Color.WHITE);
        panel.add(tunnelAdd);

        JCheckBox underConstruction = new JCheckBox("Under Construction:");
        underConstruction.setBounds(xOffsets+560,40,150,20);
        underConstruction.setBackground(Color.DARK_GRAY);
        underConstruction.setForeground(Color.WHITE);
        panel.add(underConstruction);

        tunnelAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tunnelList.add(new Tunnel(underConstruction.isSelected(),Integer.parseInt(tunnelX1.getText()),Integer.parseInt(tunnelY1.getText()),Integer.parseInt(tunnelX2.getText()),Integer.parseInt(tunnelY2.getText())));
            }
        });

        JTextField intersectionX1 = new JTextField();
        intersectionX1.setBounds(xOffsets+10,100,100,20);
        panel.add(intersectionX1);

        JTextField intersectionY1 = new JTextField();
        intersectionY1.setBounds(xOffsets+120,100,100,20);
        panel.add(intersectionY1);

        JTextField intersectionX2 = new JTextField();
        intersectionX2.setBounds(xOffsets+230,100,100,20);
        panel.add(intersectionX2);

        JTextField intersectionY2 = new JTextField();
        intersectionY2.setBounds(xOffsets+340,100,100,20);
        panel.add(intersectionY2);

        JButton intersectionAdd = new JButton("ADD");
        intersectionAdd.setBounds(xOffsets+450,100,100,20);
        intersectionAdd.setBackground(Color.DARK_GRAY);
        intersectionAdd.setForeground(Color.WHITE);
        panel.add(intersectionAdd);

        intersectionAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                intersectionList.add(new Intersection(Integer.parseInt(intersectionX1.getText()),Integer.parseInt(intersectionY1.getText()),Integer.parseInt(intersectionX2.getText()),Integer.parseInt(intersectionY2.getText())));
            }
        });

        JTextField portalX1 = new JTextField();
        portalX1.setBounds(xOffsets+10,160,100,20);
        panel.add(portalX1);

        JTextField portalY1 = new JTextField();
        portalY1.setBounds(xOffsets+120,160,100,20);
        panel.add(portalY1);

        JTextField portalX2 = new JTextField("0");
        portalX2.setBounds(xOffsets+230,160,100,20);
        //panel.add(portalX2);

        JTextField portalY2 = new JTextField("0");
        portalY2.setBounds(xOffsets+340,160,100,20);
        //panel.add(portalY2);

        JButton portalAdd = new JButton("ADD");
        portalAdd.setBounds(xOffsets+450,160,100,20);
        portalAdd.setBackground(Color.DARK_GRAY);
        portalAdd.setForeground(Color.WHITE);
        panel.add(portalAdd);

        portalAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                portalList.add(new Portal(Integer.parseInt(portalX1.getText()),Integer.parseInt(portalY1.getText()),Integer.parseInt(portalX1.getText())+1,Integer.parseInt(portalY1.getText())+1));
            }
        });

        JTextField descriptionX1 = new JTextField();
        descriptionX1.setBounds(xOffsets+10,220,100,20);
        panel.add(descriptionX1);

        JTextField descriptionY1 = new JTextField();
        descriptionY1.setBounds(xOffsets+120,220,100,20);
        panel.add(descriptionY1);

        JTextField descriptionX2 = new JTextField("0");
        descriptionX2.setBounds(xOffsets+230,220,100,20);
        //panel.add(descriptionX2);

        JTextField descriptionY2 = new JTextField("0");
        descriptionY2.setBounds(xOffsets+340,220,100,20);
        //panel.add(descriptionY2);

        JButton descriptionAdd = new JButton("ADD");
        descriptionAdd.setBounds(xOffsets+450,220,100,20);
        descriptionAdd.setBackground(Color.DARK_GRAY);
        descriptionAdd.setForeground(Color.WHITE);
        panel.add(descriptionAdd);

        JTextField descriptionText = new JTextField();
        descriptionText.setBounds(xOffsets+560,220,150,20);
        panel.add(descriptionText);

        descriptionAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                descriptionList.add(new Description(Integer.parseInt(descriptionX1.getText()),Integer.parseInt(descriptionY1.getText()),Integer.parseInt(descriptionX2.getText()),Integer.parseInt(descriptionY2.getText()),descriptionText.getText()));
            }
        });

        JLabel removeSection = new JLabel("REMOVE SECTION");
        removeSection.setBounds(xOffsets+10,250,1000,40);
        removeSection.setForeground(Color.WHITE);
        Font font1 = removeSection.getFont();
        Map attributes1 = font1.getAttributes();
        attributes1.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        attributes1.put(TextAttribute.WEIGHT, true ? TextAttribute.WEIGHT_BOLD : TextAttribute.WEIGHT_REGULAR);
        attributes1.put(TextAttribute.SIZE, 28);
        removeSection.setFont(font1.deriveFont(attributes1));
        panel.add(removeSection);

        DefaultListModel model = new DefaultListModel();
        final JList[] items = {new JList(model)};
        //items[0].setBounds(xOffsets+10,300,500,700);
        items[0].setBackground(Color.GRAY);
        items[0].setForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(items[0]);
        scroll.setBounds(xOffsets+10,300,500,700);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scroll);

        JCheckBox sureToRemove = new JCheckBox("Are you sure you want to remove this item?");
        sureToRemove.setBounds(xOffsets+520,300,300,20);
        sureToRemove.setBackground(Color.DARK_GRAY);
        sureToRemove.setForeground(Color.WHITE);
        panel.add(sureToRemove);

        JButton saveButton = new JButton("SAVE");
        saveButton.setBounds(xOffsets+520,360,300,20);
        panel.add(saveButton);

        JSlider offsetXSlider = new JSlider();
        offsetXSlider.setMaximum(2048);
        offsetXSlider.setMinimum(0);
        offsetXSlider.setValue(1024);
        offsetXSlider.setBounds(xOffsets+520,450,300,20);
        panel.add(offsetXSlider);

        JSlider offsetYSlider = new JSlider();
        offsetYSlider.setMaximum(2048);
        offsetYSlider.setMinimum(0);
        offsetYSlider.setValue(1174);
        offsetYSlider.setBounds(xOffsets+520,420,300,20);
        panel.add(offsetYSlider);

        JSlider zoomSlider = new JSlider();
        zoomSlider.setMaximum(100);
        zoomSlider.setMinimum(1);
        zoomSlider.setValue(60);
        zoomSlider.setBounds(xOffsets+520,390,300,20);
        panel.add(zoomSlider);

        JLabel errorLog = new JLabel("Current error: " + Main.latestError);
        errorLog.setBounds(xOffsets+ 10,1010,1000,20);
        errorLog.setForeground(Color.RED);
        panel.add(errorLog);

        JButton generateIntersections = new JButton("GENERATE INTERSECTIONS");
        generateIntersections.setBounds(xOffsets+520,480,300,20);
        panel.add(generateIntersections);
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

        JButton DESELECT = new JButton("LOAD");
        DESELECT.setBounds(xOffsets+520,510,300,20);
        panel.add(DESELECT);
        DESELECT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                    portalX2.setText(String.valueOf(t.getX2()));
                    portalY2.setText(String.valueOf(t.getY2()));
                }else if(o instanceof Description){
                    Description t = (Description) o;
                    descriptionX1.setText(String.valueOf(t.getX1()));
                    descriptionY1.setText(String.valueOf(t.getY1()));
                    descriptionX2.setText(String.valueOf(t.getX2()));
                    descriptionY2.setText(String.valueOf(t.getY2()));
                    descriptionText.setText(t.getText());
                } else {
                    System.err.println("ERROR OCCURED");
                }
            }
        });

        final BufferedImage[] imageREnder = {new BufferedImage(50, 50, 2)};

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ImageIO.write(imageREnder[0],"PNG",new File(System.getProperty("user.dir") + "\\MapEditor\\output.png"));
                    File file = new File(System.getProperty("user.dir") + "\\settings.set");
                    ObjectOutputStream inputStream = new ObjectOutputStream(new FileOutputStream(file));
                    List<Object> objects = new ArrayList<>();
                    objects.add(tunnelList);
                    objects.add(intersectionList);
                    objects.add(portalList);
                    objects.add(descriptionList);
                    inputStream.writeObject(objects);
                    inputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        List<Object> objects = new ArrayList<>();

        JButton overrideTunnel = new JButton("OVERRIDE");
        overrideTunnel.setBounds(xOffsets+720,40,100,20);
        panel.add(overrideTunnel);

        JButton overrideIntersection = new JButton("OVERRIDE");
        overrideIntersection.setBounds(xOffsets+720,100,100,20);
        panel.add(overrideIntersection);

        JButton overridePortal = new JButton("OVERRIDE");
        overridePortal.setBounds(xOffsets+720,160,100,20);
        panel.add(overridePortal);

        JButton overrideDescription = new JButton("OVERRIDE");
        overrideDescription.setBounds(xOffsets+720,220,100,20);
        panel.add(overrideDescription);

        overrideTunnel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                        tunnelList.add(new Tunnel(underConstruction.isSelected(),Integer.parseInt(tunnelX1.getText()),Integer.parseInt(tunnelY1.getText()),Integer.parseInt(tunnelX2.getText()),Integer.parseInt(tunnelY2.getText())));
                    }catch (Exception ex){}

                } else {
                    System.err.println("NOTHING SELECTED");
                }
            }
        });
        overrideIntersection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                        intersectionList.add(new Intersection(Integer.parseInt(intersectionX1.getText()),Integer.parseInt(intersectionY1.getText()),Integer.parseInt(intersectionX2.getText()),Integer.parseInt(intersectionY2.getText())));
                    }catch (Exception ex){}

                } else {
                    System.err.println("NOTHING SELECTED");
                }
            }
        });
        overridePortal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                        portalList.add(new Portal(Integer.parseInt(portalX1.getText()),Integer.parseInt(portalY1.getText()),Integer.parseInt(portalX2.getText()),Integer.parseInt(portalY2.getText())));
                    }catch (Exception ex){}

                } else {
                    System.err.println("NOTHING SELECTED");
                }
            }
        });
        overrideDescription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                        descriptionList.add(new Description(Integer.parseInt(descriptionX1.getText()),Integer.parseInt(descriptionY1.getText()),Integer.parseInt(descriptionX2.getText()),Integer.parseInt(descriptionY2.getText()),descriptionText.getText()));
                    }catch (Exception ex){}

                } else {
                    System.err.println("NOTHING SELECTED");
                }
            }
        });

        JButton removeButton = new JButton("REMOVE");
        removeButton.setBounds(xOffsets+520,330,300,20);
        panel.add(removeButton);

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(sureToRemove.isSelected()){
                    sureToRemove.setSelected(false);
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
                    }
                } else {
                    System.err.println("CONFIRM REMOVE");
                }
            }
        });

        Thread frameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
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
                            if(!names.contains("Description: (" + t.getX1() + "/" + t.getY1() + ") - (" + t.getX2() + "/" + t.getY2() + ") Text: " + t.getText())){
                                names.add("Description: (" + t.getX1() + "/" + t.getY1() + ") - (" + t.getX2() + "/" + t.getY2() + ") Text: " + t.getText());
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
                } catch (IOException e) {
                    e.printStackTrace();
                    Main.latestError = "Map in " + System.getProperty("user.dir") + "\\MapEditor\\input.png not found!";
                }
                BufferedImage renderImage = new BufferedImage(image.getWidth(),image.getHeight(),2);
                while (true){
                    long renderStart = System.currentTimeMillis();
                    int offsetX = 1024;
                    int offsetY = 1024;
                    double zoom = zoomSlider.getValue()/100.0;
                    /*for(int y = 0; y< image.getHeight();y++){
                        for(int x = 0; x< image.getWidth();x++){
                            try{
                                Color c = Main.lerp(new Color(image.getRGB(x,y)),Color.BLACK,0.5f);
                                c = new Color(c.getRed()/255.0f,c.getGreen()/255.0f,c.getBlue()/255.0f,0.5f);
                                renderImage.setRGB(x,y,c.getRGB());
                            }catch (Exception ignored){}
                        }
                    }*/
                    /**
                     * Commented out bc it needs performance improvements
                     */
                    renderImage = image;
                    Graphics2D g = (Graphics2D) renderImage.getGraphics();
                    int renders = 0;

                    for(Tunnel t : tunnelList){
                        renders++;
                        g.setColor(Color.RED);
                        if(t.isUnderConstruction()){
                            g.setColor(Color.ORANGE);
                        }
                        g.fillRect(t.getSize().getX()+offsetX,t.getSize().getY()+offsetY,t.getSize().getWidth(),t.getSize().getHeight());
                    }
                    for(Intersection t : intersectionList){
                        renders++;
                        g.setColor(Color.GREEN);
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
                        attributes2.put(TextAttribute.WEIGHT, true ? TextAttribute.WEIGHT_BOLD : TextAttribute.WEIGHT_REGULAR);
                        attributes2.put(TextAttribute.SIZE, 10);
                        g.setFont(font2.deriveFont(attributes2));
                        g.drawString(t.getText(),t.getX1()+offsetX-1,t.getY1()+offsetY+11);
                    }
                    BufferedImage cropped = new BufferedImage(1080,1080,2);
                    for(int y = -cropped.getHeight()/2;y<cropped.getHeight()/2;y++){
                        for(int x = -cropped.getWidth()/2;x<cropped.getWidth()/2;x++){
                            try{
                                cropped.setRGB(x+(cropped.getWidth()/2),y + (cropped.getHeight()/2),renderImage.getRGB(((int)(zoom*x))+offsetXSlider.getValue(),((int)(zoom*y))+offsetYSlider.getValue()));
                            }catch (Exception e){}
                        }
                    }
                    g = (Graphics2D) cropped.getGraphics();
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(0,0,430,130);
                    g.setColor(Color.BLACK);
                    g.fillRect(0,125,430,5);
                    g.fillRect(425,0,5,125);
                    Font font = g.getFont();
                    Map attributes = font.getAttributes();
                    attributes.put(TextAttribute.WEIGHT, true ? TextAttribute.WEIGHT_BOLD : TextAttribute.WEIGHT_REGULAR);
                    attributes.put(TextAttribute.SIZE, 30);
                    g.setFont(font.deriveFont(attributes));
                    g.setColor(Color.GREEN);
                    g.drawString("Green = Intersection",0,30);
                    g.setColor(Color.ORANGE);
                    g.drawString("Orange = Under Construction",0,60);
                    g.setColor(Color.RED);
                    g.drawString("Red = Tunnel",0,90);
                    g.setColor(Color.MAGENTA);
                    g.drawString("Magenta = Portal",0,120);
                    renderLabel.setIcon(new ImageIcon(cropped));
                    imageREnder[0] = cropped;
                    frame.setTitle("Rendered: " + renders + " Render time: " + (System.currentTimeMillis()-renderStart) + " ms");
                }
            }
        });
        t.start();
    }
}