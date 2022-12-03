import java.awt.event.ActionEvent;


public class Recolor extends AlgorithmsMentor {

    @Override
    public void actionPerformed(ActionEvent evt) {

        expandNode();
        repaint();
        if (LabirWindow.megtalalt || LabirWindow.nyitottPoz.isEmpty()) {
            LabirWindow.ido.stop();
            LabirWindow.keresesVege = true;

            if (LabirWindow.nyitottPoz.isEmpty()) {
                LabirWindow.info.setText("Nincs út a célhoz!!!");
            } else {
                plotRoute();
            }
        }
        repaint();
    }
}

