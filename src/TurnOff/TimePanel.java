package TurnOff;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

class TimePanel extends JPanel {

    private JSpinner hSpinner;
    private JSpinner mSpinner;

    TimePanel(Font font){
        JPanel jPanel = new JPanel();
        jPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "hh:mm",TitledBorder.CENTER, TitledBorder.CENTER));

        SpinnerNumberModel hours = new SpinnerNumberModel(1,0,24,1);
        hSpinner = new JSpinner(hours);
        modSpinner(hSpinner, font);
        jPanel.add(add(hSpinner));

        JLabel sem = new JLabel(":");
        sem.setFont(font);
        jPanel.add(add(sem));

        SpinnerNumberModel minutes = new SpinnerNumberModel(0,0,60,1);
        mSpinner = new JSpinner(minutes);
        modSpinner(mSpinner, font);
        jPanel.add(add(mSpinner));

        add(jPanel);

        Dimension dim = new Dimension((int) getPreferredSize().getWidth(), (int) getPreferredSize().getHeight());
        setMinimumSize(dim);
        setSize(dim);
        setMaximumSize(dim);
    }

    private void modSpinner(JSpinner spinner, Font font){
        Component comp = ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField();
        comp.setFont(font);
    }

    void setEnabled(){
        if ( hSpinner.isEnabled() ) {
            hSpinner.setEnabled(false);
            mSpinner.setEnabled(false);
        } else {
            hSpinner.setEnabled(true);
            mSpinner.setEnabled(true);
        }
    }

    int getHours() {
        return (int) hSpinner.getValue();
    }

    int getMinutes(){
        return (int) mSpinner.getValue();
    }
}

