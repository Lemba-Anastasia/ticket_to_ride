import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private JButton missionButton;
    private JButton trainButton;
    private JButton stopGameButton;
    private JLabel pointForDoingRoadLabel;
    private int pointForDoingRoad;
    private JLabel budgetLabel;
    private Graphics2D graphics2D;
    private JLabel jLabel;
    private Color color;
    private JScrollPane jsp;
    private int budget;



    public View() {
        frame = new JFrame("Ticket to ride");
        frame.setLayout(null);
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        missionButton = new JButton("Миссии");
        trainButton= new JButton("Ход поездом");
        stopGameButton= new JButton("Стоп игра");
        pointForDoingRoad=0;
        budget=100;
        pointForDoingRoadLabel = new JLabel("Очки за дорогу: "+pointForDoingRoad);
        budgetLabel = new JLabel("Ваш бюджет:"+budget+" у.е.");

        int leftIdent=520;int topIdent=20;int heightOfButton=30;int ident=10; int widthOfButton=110;
        missionButton.setBounds(leftIdent,topIdent,widthOfButton,heightOfButton);
        trainButton.setBounds(leftIdent,(topIdent+=heightOfButton+ident),widthOfButton,heightOfButton);
        stopGameButton.setBounds(leftIdent,(topIdent+=heightOfButton+ident),widthOfButton,heightOfButton);
        pointForDoingRoadLabel.setBounds(leftIdent,(topIdent+=heightOfButton+ident),widthOfButton+30,heightOfButton);
        budgetLabel.setBounds(leftIdent,(topIdent+=heightOfButton+ident),widthOfButton+20,heightOfButton);

        int widthOfImage=913;int heightOfImage=790;

        imag = new BufferedImage(widthOfImage,heightOfImage, BufferedImage.TYPE_INT_RGB);
        graphics2D = (Graphics2D) imag.getGraphics();
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0   , imag.getWidth(), imag.getHeight());

        jLabel = new JLabel(new ImageIcon(imag));
        jLabel.setBounds(0, 0, widthOfImage, heightOfImage);

        color = Color.BLACK;
        graphics2D = (Graphics2D) imag.getGraphics();
        int widthFrame = 690;
        int heightFrame = 550;
        frame.setSize(widthFrame, heightFrame);

        jsp =new JScrollPane(jLabel);
        jsp.setBounds(0, 0, 500, 500);
        frame.add(jsp);
        loadFond();
        creatingToolbar();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
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
        missionButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                if (SwingUtilities.isLeftMouseButton(event)) {
                    //addListnersForMissing();
                }
            }
        });
        frame.add(missionButton);

        trainButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                if (SwingUtilities.isLeftMouseButton(event)) {
                    //addListenersForEraser();
                }
            }
        });
        frame.add(trainButton);

        frame.add(pointForDoingRoadLabel);
        frame.add(budgetLabel);

        stopGameButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                if (SwingUtilities.isLeftMouseButton(event)) {
                    //addListenersForTriangle();
                }
            }
        });
        frame.add(stopGameButton);
    }
}
