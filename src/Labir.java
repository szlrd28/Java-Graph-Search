import javax.swing.*;

public class Labir {

    public static void main(String[] args) {

        JFrame window = new JFrame("Keresés labirintusban");
        LabirWindow labirWindow =new LabirWindow(900    ,800);
        window.setContentPane(labirWindow);
        window.pack();
        window.setResizable(false);
        window.setLocation(0,0);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }



}
