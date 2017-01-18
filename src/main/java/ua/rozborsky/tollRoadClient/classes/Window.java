package ua.rozborsky.tollRoadClient.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.rozborsky.tollRoadClient.interfaces.View;

import javax.swing.*;
import java.awt.*;

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
    }

    private void setWindowSize(JFrame frame) {
//        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        frame.setUndecorated(true);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
    }

    private void setComponents(JFrame frame) {

    }
}
