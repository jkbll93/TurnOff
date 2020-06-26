package TurnOff;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MyMenu extends JMenu {

    MyMenu(){
        super("Menu");

        add(new InfoMenu());

        add(new ItemExitMenu());

    }

    static class ItemExitMenu extends JMenuItem implements ActionListener{

        ItemExitMenu(){
            super("Exit");
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }



    static class InfoMenu extends JMenuItem implements ActionListener{

        InfoMenu(){
            super("Info");
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(this, "Download from: github.com/jkbll93 ", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

