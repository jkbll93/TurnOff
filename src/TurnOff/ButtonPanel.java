package TurnOff;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

class ButtonPanel extends JPanel {

    ButtonPanel(MyFrame myFrame, TimePanel timePanel, String defaultFontName){
        String sA = "Nastaw czas, a następnie";
        String sB = "kliknij przycisk Ustaw.";

        Font font = new Font(defaultFontName, Font.ITALIC, 12);

        FontMetrics metrics = new JLabel().getFontMetrics(font);
        int w = metrics.stringWidth(sA) + 20;

        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        JLabel na = new JLabel(sA);
        na.setFont(font);
        add(na, c);

        c.gridy = 1;
        JLabel nb = new JLabel(sB);
        nb.setFont(font);
        add(nb, c);

        c.gridy = 2;
        add(new ApplyButton(myFrame, timePanel), c);

        Dimension dim = new Dimension(w, (int) timePanel.getSize().getHeight());

        setMinimumSize(dim);
        setSize(dim);
        setMaximumSize(dim);
    }

    class ApplyButton extends JButton implements ActionListener {

        MyFrame myFrame;
        TimePanel timePanel;

        ApplyButton(MyFrame myFrame, TimePanel timePanel){
            super("Ustaw");
            this.timePanel = timePanel;
            this.myFrame = myFrame;
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if ( getText().equals("Ustaw")) {
                shutdown();
            } else {
                setText("Ustaw");
                stop();
            }
        }

        private int getTime(){
            int h = timePanel.getHours();
            int m = timePanel.getMinutes();
            return h * 3600 + m *60;
        }

        private void shutdown(){
            String system = System.getProperty("os.name");

            int answer = JOptionPane.YES_OPTION;

            int t = getTime();
            if ( t < 1800 ) {
                Object[] options = {"Tak", "Nie"};
                answer = JOptionPane.showOptionDialog(myFrame, "Czy chcesz ustawić czas mniejszy niż 30 minut?",
                        "Czy jesteś pewien", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); //default button title
            }

            if ( answer == JOptionPane.YES_OPTION ) {
                String time = Integer.toString(getTime());
                if (system.contains("Windows")) {
                    runProcess("shutdown.exe -s -t " + time);
                    setText("Anuluj");
                } else if (system.contains("Linux")) {
                    runProcess("shutdown -h -t " + time);
                    setText("Anuluj");
                } else {
                    JOptionPane.showMessageDialog(myFrame, "Nieznany system operacyjny.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        private void stop(){
            String system = System.getProperty("os.name");
            if ( system.contains("Windows") ){
                runProcess("shutdown -a");
            } else if ( system.contains("Linux")){
                runProcess("shutdown -c");
            } else {
                JOptionPane.showMessageDialog(myFrame, "Nieznany system operacyjny.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        void runProcess(String command){
            timePanel.setEnabled();
            if (!command.equals("")) {
                try {
                    Runtime runtime = Runtime.getRuntime();
                    runtime.exec(command);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(myFrame, "Błąd wykonania polecenia" , "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
