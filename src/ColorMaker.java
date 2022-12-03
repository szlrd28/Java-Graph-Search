import javax.swing.*;
import java.awt.*;


public class ColorMaker extends JPanel {
    @Override
    public void paintComponent(Graphics g) {

        if ((LabirWindow.celHelye.sor >= 0 && LabirWindow.celHelye.oszlop>=0 && LabirWindow.celHelye.sor<LabirWindow.sorok && LabirWindow.celHelye.oszlop < LabirWindow.oszlopok  )) {
            LabirWindow.racs[LabirWindow.celHelye.sor][LabirWindow.celHelye.oszlop] = LabirWindow.CEL;

        }else {

        }
        if ((LabirWindow.kezdesHelye.sor >= 0 && LabirWindow.kezdesHelye.oszlop>=0 && LabirWindow.kezdesHelye.sor<LabirWindow.sorok && LabirWindow.kezdesHelye.oszlop < LabirWindow.oszlopok  )) {
            LabirWindow.racs[LabirWindow.kezdesHelye.sor][LabirWindow.kezdesHelye.oszlop] = LabirWindow.KEZDES;

        }


            super.paintComponent(g);

            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(10, 10, LabirWindow.oszlopok * LabirWindow.NEGYZET_MERET, LabirWindow.sorok * LabirWindow.NEGYZET_MERET);

            g.setColor(Color.black);

            for (int i = 0; i <= LabirWindow.sorok; i++) {
                g.drawLine(10, 10 + i * LabirWindow.NEGYZET_MERET, LabirWindow.oszlopok * LabirWindow.NEGYZET_MERET + 10, 10 + i * LabirWindow.NEGYZET_MERET);
            }
            for (int i = 0; i <= LabirWindow.oszlopok; i++) {
                g.drawLine(10 + i * LabirWindow.NEGYZET_MERET, 10, 10 + i * LabirWindow.NEGYZET_MERET, LabirWindow.sorok * LabirWindow.NEGYZET_MERET + 10);
            }
            int r;
            int c;

            for (r = 0; r < LabirWindow.sorok; r++) {
                for (c = 0; c < LabirWindow.oszlopok; c++) {
                    //  System.out.println(room[r][c]==6);
               /*LabirWindow.racs[0][1] = LabirWindow.FAL;
                LabirWindow.racs[2][1] = LabirWindow.FAL;
                LabirWindow.racs[1][3] = LabirWindow.FAL;
                LabirWindow.racs[2][3] = LabirWindow.FAL;*/
               /* LabirWindow.racs[2][1] = LabirWindow.FAL;
                LabirWindow.racs[1][3] = LabirWindow.FAL;
                LabirWindow.racs[2][3] = LabirWindow.FAL;
                LabirWindow.racs[5][1] = LabirWindow.FAL;
                LabirWindow.racs[3][1] = LabirWindow.FAL;
                LabirWindow.racs[2][4] = LabirWindow.FAL;
                LabirWindow.racs[3][2] = LabirWindow.FAL;
                LabirWindow.racs[4][3] = LabirWindow.FAL;
                LabirWindow.racs[4][4] = LabirWindow.FAL;*/


                    switch (LabirWindow.racs[r][c]) {
                        case LabirWindow.URES:
                            g.setColor(Color.LIGHT_GRAY);
                            break;
                        case LabirWindow.KEZDES:
                            g.setColor(LabirWindow.VERY_DARK_GREEN);
                            break;
                        case LabirWindow.CEL:
                            g.setColor(LabirWindow.VERY_LIGHT_RED);
                            break;
                        case LabirWindow.FAL:
                            g.setColor(Color.DARK_GRAY);
                            break;
                        case LabirWindow.NYITOTT:
                            g.setColor(LabirWindow.Very_light_blue);
                            break;
                        case LabirWindow.ZART:
                            g.setColor(Color.GREEN);
                            break;
                        case LabirWindow.UT:
                            g.setColor(Color.PINK);
                            break;
                        default:
                            g.setColor(Color.WHITE);
                            break;

                    }

                    g.fillRect(11 + c * LabirWindow.NEGYZET_MERET, 11 + r * LabirWindow.NEGYZET_MERET, LabirWindow.NEGYZET_MERET - 1, LabirWindow.NEGYZET_MERET - 1);
                }

            }
            for (int i = 0; i < LabirWindow.zartPoz.size(); i++) {
                g.setFont(new Font("Helvetica", Font.PLAIN, 60));
                g.setColor(Color.BLACK);
                g.drawString(String.valueOf(i + 1), LabirWindow.zartPoz.get(i).oszlop * LabirWindow.NEGYZET_MERET + LabirWindow.NEGYZET_MERET / 2 - 11, LabirWindow.zartPoz.get(i).sor * LabirWindow.NEGYZET_MERET + 90);
            }

            updateUI();

    }

    public void fillGrid() {
        if (LabirWindow.kereses || LabirWindow.keresesVege) {
            for (int r = 0; r < LabirWindow.sorok; r++) {
                for (int c = 0; c < LabirWindow.oszlopok; c++) {
                    if (LabirWindow.racs[r][c] == LabirWindow.NYITOTT || LabirWindow.racs[r][c] == LabirWindow.ZART || LabirWindow.racs[r][c] == LabirWindow.UT ) {
                        LabirWindow.racs[r][c] = LabirWindow.URES;
                    }
                    if (LabirWindow.racs[r][c] == LabirWindow.KEZDES) {
                        LabirWindow.kezdesHelye = new Cells(r, c);
                    }
                    if (LabirWindow.racs[r][c] == LabirWindow.CEL) {
                        LabirWindow.celHelye = new Cells(r, c);
                    }
                }
            }
            LabirWindow.kereses = false;
        } else {
            for (int r = 0; r < LabirWindow.sorok; r++) {
                for (int c = 0; c < LabirWindow.oszlopok; c++) {
                    LabirWindow.racs[r][c] = LabirWindow.URES;
                }
            }
            LabirWindow.kezdesHelye = new Cells(0, 0);
            LabirWindow.celHelye = new Cells(3, 3);
        }

        if (LabirWindow.aStar.isSelected()) {
            LabirWindow.kezdesHelye.utvonalKoltseg = 0;
            LabirWindow.kezdesHelye.becsultKoltseg = 0;
            LabirWindow.kezdesHelye.f = 0;
        }
        LabirWindow.kiterjesztett = 0;
        LabirWindow.megtalalt = false;
        LabirWindow.kereses = false;
        LabirWindow.keresesVege = false;

        LabirWindow.nyitottPoz.removeAll(LabirWindow.nyitottPoz);

        LabirWindow.nyitottPoz.add(LabirWindow.kezdesHelye);
        LabirWindow.zartPoz.removeAll(LabirWindow.zartPoz);

        LabirWindow.info.setText("Információ");
        LabirWindow.ido.stop();
        repaint();

    }
}
