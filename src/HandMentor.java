import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class HandMentor extends ColorMaker implements MouseListener, MouseMotionListener {

    private int jel_sor;
    private int jel_oszl;
    private int sor;
    private int oszlop;


    @Override
    public void mousePressed(MouseEvent evt) {
        sor = (evt.getY() - 10) / LabirWindow.NEGYZET_MERET;
        oszlop = (evt.getX() - 10) / LabirWindow.NEGYZET_MERET;

        if (sor >= 0 && sor < LabirWindow.sorok && oszlop >= 0 && oszlop < LabirWindow.oszlopok && !LabirWindow.kereses && !LabirWindow.megtalalt) {
            jel_sor = sor;
            jel_oszl = oszlop;
            if (LabirWindow.racs[sor][oszlop] == LabirWindow.URES) {

                LabirWindow.racs[sor][oszlop] = LabirWindow.FAL;

            } else if (LabirWindow.racs[sor][oszlop] == LabirWindow.FAL) {
                LabirWindow.racs[sor][oszlop] = LabirWindow.URES;

            }
        }
        repaint();
    }


    @Override
    public void mouseReleased(MouseEvent evt) {
        int megfogott = 0;


        if (LabirWindow.racs[jel_sor][jel_oszl] == LabirWindow.CEL && !LabirWindow.kereses && !LabirWindow.megtalalt) {
            megfogott = LabirWindow.CEL;
        }
        if (LabirWindow.racs[jel_sor][jel_oszl] == LabirWindow.KEZDES && !LabirWindow.kereses && !LabirWindow.megtalalt) {
            megfogott = LabirWindow.KEZDES;
        }

        int kat_jel_sor=jel_sor;
        int kat_jel_oszl=jel_oszl;

        jel_sor = (evt.getY() - 10) / LabirWindow.NEGYZET_MERET;
        jel_oszl = (evt.getX() - 10) / LabirWindow.NEGYZET_MERET;

        if (!(jel_sor >= 0 && jel_oszl>=0 && jel_sor<LabirWindow.sorok && jel_oszl < LabirWindow.oszlopok
        )){
            jel_sor=kat_jel_sor;
            jel_oszl=kat_jel_oszl;
            return;
        }

        if (megfogott == LabirWindow.CEL && !LabirWindow.kereses) {
            LabirWindow.racs[sor][oszlop] = LabirWindow.URES;
            LabirWindow.celHelye.sor = jel_sor;
            LabirWindow.celHelye.oszlop = jel_oszl;

        }
        if (megfogott == LabirWindow.KEZDES && !LabirWindow.kereses) {
            LabirWindow.racs[sor][oszlop] = LabirWindow.URES;
            LabirWindow.kezdesHelye.sor = jel_sor;
            LabirWindow.kezdesHelye.oszlop = jel_oszl;

        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent evt) {
    }

    @Override
    public void mouseExited(MouseEvent evt) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent evt) {
    }

    @Override
    public void mouseClicked(MouseEvent evt) {
    }

}
