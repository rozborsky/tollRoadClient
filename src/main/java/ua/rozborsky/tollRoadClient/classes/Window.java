package ua.rozborsky.tollRoadClient.classes;

import org.springframework.stereotype.Service;
import ua.rozborsky.tollRoadClient.interfaces.View;

import javax.swing.*;
import java.awt.*;
import java.io.File;


/**
 * Created by roman on 18.01.2017.
 */
@Service("window")
public class Window implements View {

    @Override
    public void create() {
        JFrame frame = new JFrame("toll road");
        setWindowParameters(frame);
        setComponents(frame);

        frame.setVisible(true);
    }

    private void setWindowParameters(JFrame frame) {
        setWindowSize(frame);
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setWindowSize(JFrame frame) {
//        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        frame.setUndecorated(true);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
    }

    private void setComponents(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(true);
        panel.setBackground(new Color(145,45,45));
        panel.add(image());

        frame.add(panel);
    }

    private Component image() {
        File logo = new File("src\\main\\resources\\image\\5.png");
        String path = logo.getAbsolutePath();
        JLabel label = new JLabel(new ImageIcon(path));

        return label;
    }
}
