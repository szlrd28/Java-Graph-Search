import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;

/***
 * @author Szilárd
 * @
 */
public class LabirWindow extends ColorMaker {

    public final static int NEGYZET_MERET = 100,



    URES = 0,
            FAL = 1,
            KEZDES = 2,
            CEL = 3,
            NYITOTT = 4,
            ZART = 5,

            UT = 6;

    public static Color VERY_DARK_GREEN = new Color(0, 102, 0);
    public static Color VERY_LIGHT_RED = new Color(153, 0, 0);

    public static Color Very_light_blue = new Color(51, 204, 255);

    static ArrayList<Cells> nyitottPoz = new ArrayList<>();
    static ArrayList<Cells> zartPoz = new ArrayList<>();

    static Cells kezdesHelye;
    static Cells celHelye;
    static JLabel info;
    static JLabel cim;
    static JLabel cim1;
    static JLabel kezdes;
   static JLabel cel;
    static JLabel ut;
   static JLabel nyitott;
    static JLabel zart;
    static JLabel sebesseg;

    static JButton aboutButton;
    static JDialog modelDialog;


    static JRadioButton dfs2, bfs2, aStar2;
    static JRadioButton dfs, bfs, aStar;
    static JSlider csuszka;
    static int sorok;
    static int oszlopok;

    static Integer[][] racs;
    static boolean megtalalt;
    static boolean kereses;
    static boolean keresesVege;
    static int kesleltetes;
    static int kiterjesztett;
    static JFrame f;


    static JCheckBox szamolas;
    static Timer ido;
    Recolor action = new Recolor();


    public LabirWindow(int szelesseg, int magassag) {

        HandMentor listener = new HandMentor();
        addMouseListener(listener);
        addMouseMotionListener(listener);

        setLayout(null);
        setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.lightGray));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double kepernyoSzel = screenSize.getWidth();
        double kepernyoMag = screenSize.getHeight();
        if (szelesseg < 400) {
            szelesseg = 400;
        } else {
            szelesseg = Math.min((int) kepernyoSzel - 20, szelesseg);
        }
        if (magassag < 250) {
            magassag = 250;
        } else {
            magassag = Math.min((int) kepernyoMag - 40, magassag);
        }

        setPreferredSize(new Dimension(szelesseg, magassag));
        sorok = (magassag - 170) / NEGYZET_MERET;
        oszlopok = (szelesseg - 300) / NEGYZET_MERET;

        racs = new Integer[sorok][oszlopok];

        info = new JLabel("asd11'", JLabel.CENTER);
        info.setForeground(Color.blue);
        info.setFont(new Font("Helvetica", Font.PLAIN, 14));

        String number = "1";
        JLabel numLabel = new JLabel(number, JLabel.CENTER);
        numLabel.setFont(new Font("Helvetica", Font.PLAIN, 150));

        kezdes = new JLabel(" Sötét zöld - Kezdés", JLabel.CENTER);
        kezdes.setForeground(LabirWindow.VERY_DARK_GREEN);
        kezdes.setFont(new Font("Helvetica", Font.PLAIN, 15));

        cel = new JLabel("Piros - Cél", JLabel.CENTER);
        cel.setForeground(Color.RED);
        cel.setFont(new Font("Helvetica", Font.PLAIN, 15));

        nyitott = new JLabel("Világos kék - Nyitott állapotok", JLabel.CENTER);
        nyitott.setForeground(Color.blue);
        nyitott.setFont(new Font("Helvetica", Font.PLAIN, 15));

        zart = new JLabel(" Zöld - Bezárt állapotok ", JLabel.CENTER);
        zart.setForeground(Color.green);
        zart.setFont(new Font("Helvetica", Font.PLAIN, 15));

        ut = new JLabel(" Rozsaszin-  Kiértékelt út ", JLabel.CENTER);
        ut.setForeground(Color.pink);
        ut.setFont(new Font("Helvetica", Font.PLAIN, 15));

        JButton clearButton = new JButton("Törlés");
        clearButton.addActionListener(new AlgorithmsMentor());
        clearButton.setToolTipText("Első kattintás: keresés törlése, Második kattintás: akadályok eltávolítása");

        JButton stepButton = new JButton("Lépésenként");
        stepButton.addActionListener(new AlgorithmsMentor());
        stepButton.setToolTipText("A célhoz vezető út minden kattintásnál lépésről lépésre történik.");

        JButton animationButton = new JButton("Automatikus");
        animationButton.addActionListener(new AlgorithmsMentor());
        //animationButton.setBackground(Color.lightGray);
        animationButton.setToolTipText("A célhoz vezető út automatikus.");


        cim = new JLabel("Óramutató járásával \n egyező", JLabel.CENTER);
        cim.setForeground(Color.blue);
        cim.setFont(new Font("Helvetica", Font.PLAIN, 14));

        cim1 = new JLabel("Óramutató járásával ellentétes", JLabel.CENTER);
        cim1.setForeground(Color.blue);
        cim1.setFont(new Font("Helvetica", Font.PLAIN, 14));

        sebesseg = new JLabel("Sebesség", JLabel.CENTER);
        sebesseg.setFont(new Font("Helvetica", Font.PLAIN, 10));

        csuszka = new JSlider(0, 1000, 800);
        csuszka.setToolTipText("Beállítja a késleltetést minden lépésben (0-1 másodperc).");

        kesleltetes = 1000 - csuszka.getValue();
        csuszka.addChangeListener(evt -> {
            JSlider source = (JSlider) evt.getSource();
            if (!source.getValueIsAdjusting()) {
                kesleltetes = 1000 - source.getValue();
            }
        });

        ButtonGroup algorithmsGroup = new ButtonGroup();

        JCheckBox szamolas = new JCheckBox("Számolás");

        dfs = new JRadioButton("Mélységi keresés");
        dfs.setToolTipText("Mélységi keresés");
        algorithmsGroup.add(dfs);
        dfs.addActionListener(new AlgorithmsMentor());
        add(dfs);

        bfs = new JRadioButton("Szélességi keresés");
        bfs.setToolTipText("Szélességi keresés");
        algorithmsGroup.add(bfs);
        bfs.addActionListener(new AlgorithmsMentor());
        add(bfs);

        aStar = new JRadioButton("A* keresési");
        aStar.setToolTipText(" A* keresési algoritmus ");
        algorithmsGroup.add(aStar);
        aStar.addActionListener(new AlgorithmsMentor());
        add(aStar);

        dfs2 = new JRadioButton("Mélységi keresés");
        dfs2.setToolTipText("Mélységi keresés");
        algorithmsGroup.add(dfs2);
        dfs2.addActionListener(new AlgorithmsMentor());
        add(dfs2);

        bfs2 = new JRadioButton("Szélességi keresés");
        bfs2.setToolTipText("Szélességi keresés");
        algorithmsGroup.add(bfs2);
        bfs2.addActionListener(new AlgorithmsMentor());
        add(bfs2);

        aStar2 = new JRadioButton("A* keresési");
        aStar2.setToolTipText(" A* keresési algoritmus ");
        algorithmsGroup.add(aStar2);
        aStar2.addActionListener(new AlgorithmsMentor());
        add(aStar2);





        aboutButton = new JButton("About");
        aboutButton.addActionListener(new AlgorithmsMentor());
        aboutButton.setBackground(Color.lightGray);
        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog d = new JDialog(f, "Készitő");
                JLabel l = new JLabel("Hompoth Szilárd - FQR9QJ " +
                        ", szilardhompoth@gmail.com");
                d.add(l);
                l.setFont(new Font("Helvetica", Font.PLAIN, 20));
                d.setLocation(700,200);
                d.setSize(550, 300);
                d.setVisible(true);
            }
        }) ;


        dfs.setSelected(true);
        add(ut);
        add(info);
        add(kezdes);
        add(cel);
        add(nyitott);
        add(zart);
        add(clearButton);
        add(stepButton);
        add(animationButton);
        add(szamolas);
        add(dfs);
        add(bfs);
        add(aStar);
        add(sebesseg);
        add(csuszka);
        add(dfs2);
        add(bfs2);
        add(aStar2);
        add(cim);
        add(cim1);
        add(aboutButton);


        aboutButton.setBounds(10 + (szelesseg - 160) * 17 / 20, magassag - 725, (szelesseg - 60) * 5 / 20, 28);
        info.setBounds(15, magassag - 155, szelesseg - 30, 23);
       kezdes.setBounds(10 + (szelesseg - 160) * 17 / 20, magassag - 690, (szelesseg - 60) * 5 / 20, 28);
        cel.setBounds(10 + (szelesseg - 160) * 17 / 20, magassag - 670, (szelesseg - 60) * 5 / 20, 28);
        nyitott.setBounds(10 + (szelesseg - 160) * 17 / 20, magassag - 650, (szelesseg - 60) * 5 / 20, 28);
        zart.setBounds(10 + (szelesseg - 160) * 17 / 20, magassag - 630, (szelesseg - 60) * 5 / 20, 28);
        ut.setBounds(10 + (szelesseg - 160) * 17 / 20, magassag - 610, (szelesseg - 60) * 5 / 20, 28);
        clearButton.setBounds(15, magassag - 120, (szelesseg - 60) * 10 / 20, 28);
        stepButton.setBounds(30 + (szelesseg - 60) * 10 / 20, magassag - 120, (szelesseg - 60) * 7 / 20, 28);
        animationButton.setBounds(30 + (szelesseg - 60) * 10 / 20, magassag - 85, (szelesseg - 60) * 7 / 20, 28);
        cim.setBounds(10 + (szelesseg - 160) * 17 / 20, magassag - 470, (szelesseg - 60) * 5 / 20, 28);
        dfs2.setBounds(10 + (szelesseg - 130) * 17 / 20, magassag - 425, (szelesseg - 60) * 3 / 20, 28);
        bfs2.setBounds(10 + (szelesseg - 130) * 17 / 20, magassag - 400, (szelesseg - 60) * 3 / 20, 28);
        aStar2.setBounds(10 + (szelesseg - 130) * 17 / 20, magassag - 375, (szelesseg - 60) * 3 / 20, 28);
        cim1.setBounds(10 + (szelesseg - 160) * 17 / 20, magassag - 340, (szelesseg - 60) * 5 / 20, 28);
        dfs.setBounds(10 + (szelesseg - 130) * 17 / 20, magassag - 300, (szelesseg - 60) * 3 / 20, 28);
        bfs.setBounds(10 + (szelesseg - 130) * 17 / 20, magassag - 275, (szelesseg - 60) * 3 / 20, 28);
        aStar.setBounds(10 + (szelesseg - 130) * 17 / 20, magassag - 250, (szelesseg - 60) * 3 / 20, 28);
        sebesseg.setBounds(30 + (szelesseg - 60) * 10 / 20, magassag - 60, (szelesseg - 60) * 7 / 20, 23);
        csuszka.setBounds(30 + (szelesseg - 60) * 10 / 20, magassag - 42, (szelesseg - 60) * 7 / 20, 23);

        ido = new Timer(kesleltetes, action);
        fillGrid();

    }


    public static String stringMaker() {
        String racsString = "";
        for (int i = 0; i < racs.length; i++) {
            for (int j = 0; j < racs[i].length; j++) {
                racsString += racs[i][j] + " ";
            }
            racsString += "\n";
        }
        return racsString;
    }

    public static class CellComparator implements Comparator<Cells> {
        @Override
        public int compare(Cells cells1, Cells cells2) {
            return cells1.f - cells2.f;
        }
    }

}

