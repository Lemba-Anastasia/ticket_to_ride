package View;

import Controllers.ClavaListener;
import Controllers.Controller;
import Controllers.WorkWithFile;
import ElementsOfGraph.Edge;
import ElementsOfGraph.Node;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

/**
 * Created by Lemba on 07.09.2018.
 */
public class View {
    private JFrame frame;
    private BufferedImage imag;
    private JLabel missionLabel;
    private JButton trainButton;
    private JButton stopGameButton;
    private JLabel pointForDoingRoadLabel;
    private JLabel budgetLabel;
    private Graphics2D graphics2D;
    private JLabel jLabel;
    private Color colorOfBuidRoad;
    private Color colorOfNonBuildRoad;
    private JScrollPane jsp;
    private WorkWithFile workWithFile;
    private Controller controller;
    private Color colorOfCities;
    private Color colorOfActiveCities;
    private JButton mission1;
    private JButton mission2;
    private MissioncCreator missioncCreator;

    public View(Controller controller) {
        frame = new JFrame("Ticket to ride");
        frame.setLayout(null);
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        this.controller = controller;
        missioncCreator = new MissioncCreator(controller);
        missionLabel = new JLabel("Выберите миссию");
        trainButton = new JButton("Ход поездом");
        stopGameButton = new JButton("Стоп игра");

        pointForDoingRoadLabel = new JLabel("Очки за дорогу: " + controller.getPoinsForDoingRoad());
        budgetLabel = new JLabel("Ваш бюджет:" + controller.getBudget() + " у.е.");
        mission1 = missioncCreator.createButton();
        mission2 = missioncCreator.createButton();

        int leftIdent = 520;
        int topIdent = 20;
        int heightOfButton = 30;
        int ident = 10;
        int widthOfButton = 110;
        trainButton.setBounds(leftIdent, (topIdent), widthOfButton, heightOfButton);
        stopGameButton.setBounds(leftIdent, (topIdent += heightOfButton + ident), widthOfButton, heightOfButton);
        pointForDoingRoadLabel.setBounds(leftIdent, (topIdent += heightOfButton + ident), widthOfButton + 30, heightOfButton);
        budgetLabel.setBounds(leftIdent, (topIdent += heightOfButton + ident), widthOfButton + 20, heightOfButton);
        missionLabel.setBounds(leftIdent, (topIdent += heightOfButton + ident), widthOfButton + 50, heightOfButton);
        mission1.setBounds(leftIdent, (topIdent += heightOfButton + ident), widthOfButton + 50, heightOfButton);
        mission2.setBounds(leftIdent, (topIdent += heightOfButton + ident), widthOfButton + 50, heightOfButton);

        int widthOfImage = 913;
        int heightOfImage = 790;

        workWithFile = new WorkWithFile();
        colorOfBuidRoad = Color.black;
        colorOfNonBuildRoad = Color.GRAY;
        colorOfActiveCities = Color.red;
        colorOfCities = Color.BLACK;
        colorOfCities.brighter();

        imag = new BufferedImage(widthOfImage, heightOfImage, BufferedImage.TYPE_INT_RGB);
        graphics2D = (Graphics2D) imag.getGraphics();
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, imag.getWidth(), imag.getHeight());

        jLabel = new JLabel(new ImageIcon(imag));
        jLabel.setBounds(0, 0, widthOfImage, heightOfImage);

        graphics2D = (Graphics2D) imag.getGraphics();
        int widthFrame = 700;
        int heightFrame = 550;
        frame.setSize(widthFrame, heightFrame);

        jsp = new JScrollPane(jLabel);
        jsp.setBounds(0, 0, 500, 500);
        frame.add(jsp);
        loadFond();
        creatingToolbar();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void paintGraph() {
        paintBonds();
        paintCities();
    }

    private void paintBonds() {
        graphics2D.setStroke(new BasicStroke(5.0f));
        for (Edge e : controller.getEdgeList()) {
            if (e.isBuilt()) {
                graphics2D.setColor(colorOfBuidRoad);
            } else {
                graphics2D.setColor(colorOfNonBuildRoad);
            }
            graphics2D.drawImage(copyImage(imag), null, 0, 0);
            graphics2D.drawLine(e.getFirstNode().getPoint().x, e.getFirstNode().getPoint().y,
                    e.getSecondNode().getPoint().x, e.getSecondNode().getPoint().y);
            jLabel.update(graphics2D);
            jLabel.updateUI();
        }
    }

    private void paintCities() {
        for (Node node : controller.getNodeList()) {
            if (node.isActive()) {
                graphics2D.setColor(colorOfActiveCities);
            } else {
                graphics2D.setColor(colorOfCities);
            }
            graphics2D.drawImage(copyImage(imag), null, 0, 0);
            graphics2D.fillOval(node.getPoint().x - 4, node.getPoint().y - 4, 8, 8);
        }

        jLabel.update(graphics2D);
        jLabel.updateUI();
    }

    private BufferedImage copyImage(BufferedImage source) {
        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics g = b.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return b;
    }

    private void loadFond() {
        try {
            String filename = "E:\\ticket_to_ride\\src\\Karta-oblastej-Belarusi.png";
            graphics2D.drawImage(read(new File(filename)), 0, 0, null);
            jLabel.update(graphics2D);
            jLabel.updateUI();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void creatingToolbar() {
        trainButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {

            }
        });
        frame.add(trainButton);

        frame.add(pointForDoingRoadLabel);
        frame.add(budgetLabel);
        frame.add(missionLabel);

        stopGameButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {

            }
        });
        frame.add(stopGameButton);
        mission1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] target1 = mission1.getText().split(" - ");
                addListnersForMissing(target1[0], target1[1]);
                missionLabel.setText("Миссия:" + mission1.getText());
            }
        });
        frame.add(mission1);
        mission2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] target2 = mission2.getText().split(" - ");
                addListnersForMissing(target2[0], target2[1]);
                missionLabel.setText("Миссия:" + mission2.getText());
            }
        });
        frame.add(mission2);
    }

    private void addListnersForMissing(String startNameCity, String endNodeRoute) {
        for (KeyListener keyListener : frame.getKeyListeners()) frame.removeKeyListener(keyListener);
        KeyListener keyListener = new ClavaListener(controller/* controller.getNodeByName(startNameCity),
                budget,pointForDoingRoad, controller.getNodeByName(endNodeRoute)*/);
        controller.setStartPunkt(controller.getNodeByName(startNameCity));
        controller.setEndNodeRoute(controller.getNodeByName(endNodeRoute));
        controller.setMission(startNameCity+" - "+endNodeRoute);
        frame.setFocusable(true);
        frame.requestFocus();
        frame.addKeyListener(keyListener);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void updaitGraphics() {
        jLabel.update(graphics2D);
        jLabel.updateUI();
    }

    public void updaitInfoOfGame() {
        pointForDoingRoadLabel.setText("Очки за дорогу: " + controller.getPoinsForDoingRoad());
        budgetLabel.setText("Ваш бюджет:" + controller.getBudget() + " у.е.");
        pointForDoingRoadLabel.updateUI();
        budgetLabel.updateUI();
    }

    public void updateMission(String s) {
        System.out.println("mission1: "+mission1.getText());
        System.out.println("mission2: "+mission2.getText());
        System.out.println("s: "+s);
        if (mission1.getText().equals(s)) {
            mission1=null;
            mission1 = missioncCreator.createButton();
            mission1.updateUI();
            mission1.update(mission1.getGraphics());
            System.out.println("mission1 after cheng: "+mission1.getText());

        }
        if (mission2.getText().equals(s)) {
            mission2=null;
            mission2 = missioncCreator.createButton();
            mission2.updateUI();
            mission2.update(mission1.getGraphics());
            System.out.println("mission2 after cheng: "+mission2.getText());
        }
    }
}
