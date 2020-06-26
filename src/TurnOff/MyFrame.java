package TurnOff;

import javax.swing.*;
import java.awt.*;

class MyFrame extends JFrame {

    MyFrame(){
        super("Wyłączenie komputera");

        //get default system font name
        Font defaultFont = new JLabel().getFont();
        String defaultFontName = defaultFont.getName();

        //create font with size 46
        Font font = new Font(defaultFontName, Font.BOLD, 46);
        FontMetrics metrics = new JLabel().getFontMetrics(font);
        int h = metrics.getHeight();

        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(new MyMenu());

        TimePanel timePanel = new TimePanel(font);
        int height = (int) timePanel.getSize().getHeight();

        Dimension dimTime = timePanel.getSize();

        ButtonPanel buttonPanel = new ButtonPanel(this, timePanel, defaultFontName);

        int width = (int) (dimTime.getWidth() + buttonPanel.getSize().getWidth());
        setSize( width ,3 * h);

        setLayout(new BorderLayout());

        setJMenuBar(jMenuBar);
        add(timePanel, BorderLayout.LINE_START);
        add(buttonPanel, BorderLayout.LINE_END);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setVisible(true);
    }
}
