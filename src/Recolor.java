import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class Recolor extends AlgorithmsMentor {

    @Override
    public void actionPerformed(ActionEvent evt) {

        //expandNode();
        //repaint();
        if (LabirWindow.nyitottPoz.isEmpty()) {
            LabirWindow.keresesVege = true;
            LabirWindow.info.setText("Nincs út a célhoz!");
        } else {
            expandNode();

            if (LabirWindow.megtalalt) {

                LabirWindow.keresesVege = true;
                plotRoute();
                LabirWindow.ido.stop();
            }
        }
        /*if (LabirWindow.megtalalt  || LabirWindow.nyitottPoz.isEmpty()) {
            LabirWindow.ido.stop();
            LabirWindow.keresesVege = true;
            //expandNode();
            if (LabirWindow.nyitottPoz.isEmpty()) {
                LabirWindow.info.setText("Nincs út a célhoz!!!");
            } else {
                plotRoute();
                //expandNode();
                //plotRoute();
            }
        }*/
        repaint();
    }
}

