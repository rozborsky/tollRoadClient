package ua.rozborsky.tollRoadClient.classes;

import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import ua.rozborsky.tollRoadClient.interfaces.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Timer;


/**
 * Created by roman on 18.01.2017.
 */
@Service("window")
public class Window implements View {
    private static final Logger log = Logger.getLogger(Window.class);

    private JPanel dialog;
    private Font FONT = new Font("Courier New", Font.BOLD, 200);
    private Font ERROR_FONT = new Font("Courier New", Font.BOLD, 100);
    private Font TERMINAL_MARKER_FONT = new Font("Courier New", Font.BOLD, 50);
    private Color color = new Color(Properties.r(), Properties.g(), Properties.b());
    private int delay = Properties.delay();
    private static SocketManager socketManager;
    private JPanel mainPanel;


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
        mainPanel = new JPanel(new CardLayout());
        mainPanel.add(normalWork(), "work");
        mainPanel.add(error(), "error");

        CardLayout mainLayout = (CardLayout)(mainPanel.getLayout());
        mainLayout.show(mainPanel, "work");

        frame.add(mainPanel);
    }

    private JPanel normalWork() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(color);

        panel.add(image(), BorderLayout.WEST);
        panel.add(titlePanel(), BorderLayout.EAST);
        panel.add(dialogPanel(), BorderLayout.SOUTH);

        return panel;
    }

    private JPanel error() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(color);

        JLabel error = new JLabel("<html><div style='text-align: center;'>" + Properties.systemError() + "</div></html>");
        error.setFont(ERROR_FONT);
        error.setForeground(Color.WHITE);
        panel.add(error);

        return panel;
    }

    private JLabel image() {
        File logo = new File(Properties.pathToImage());
        String path = logo.getAbsolutePath();
        JLabel label = new JLabel(new ImageIcon(path));

        return label;
    }

    private JPanel titlePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(color);

        JLabel title = new JLabel(Properties.name());
        title.setFont(FONT);
        title.setForeground(Color.WHITE);
        panel.add(title, BorderLayout.NORTH);

        return panel;
    }

    private JPanel dialogPanel() {
        dialog = new JPanel(new CardLayout());
        dialog.setBackground(color);

        dialog.add(inputPanel(), "input");
        dialog.add(notValidId(), "notValidId");
        dialog.add(greetingPanel(), "greeting");
        dialog.add(notExistDriver(), "notExistDriver");

        CardLayout layout = (CardLayout)(dialog.getLayout());
        layout.show(dialog, "input");

        return dialog;
    }

    private JPanel inputPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(color);
        panel.add(terminalMarker(), BorderLayout.WEST);
        panel.add(input(), BorderLayout.EAST);

        return panel;
    }

    private JTextField input() {
        JTextField input = new JTextField(10);
        input.setFont(FONT);
        input.setDocument(new JTextFieldLimit());
        input.addActionListener(inputListener(input));

        return input;
    }

    private JPanel terminalMarker() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(color);

        JLabel marker = new JLabel(Properties.terminalMarker());
        marker.setFont(TERMINAL_MARKER_FONT);
        marker.setForeground(Color.GREEN);
        panel.add(marker, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel notValidId() {
        JPanel panel = new JPanel();
        panel.setBackground(color);

        JLabel error = new JLabel(Properties.notValid());
        error.setForeground(Color.white);
        error.setFont(FONT);
        panel.add(error);

        return panel;
    }

    private JPanel notExistDriver() {
        JPanel panel = new JPanel();
        panel.setBackground(color);

        JLabel error = new JLabel(Properties.error());
        error.setForeground(Color.white);
        error.setFont(FONT);
        panel.add(error);

        return panel;
    }

    private JPanel greetingPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(color);

        JLabel greeting = new JLabel(Properties.ok());
        greeting.setForeground(Color.white);
        greeting.setFont(FONT);
        panel.add(greeting);

        return panel;
    }

    private Action inputListener(final JTextField input) {
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
            CardLayout layout = (CardLayout)(dialog.getLayout());

            try{
                Integer id = Integer.valueOf(input.getText());

                if(accessAllowed(id)) {
                    layout.show(dialog, "greeting");
                } else {
                    layout.show(dialog, "notExistDriver");
                }
            } catch (NumberFormatException ee) {
                layout.show(dialog, "notValidId");
            }

            input.setText("");
            Timer timer = new Timer();
            timer.schedule(new UpdateInput(layout, dialog), delay * 1000);
            }
        };

        return action;
    }

    private boolean accessAllowed(int id) {
        try {
            ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationConfig.xml");
            socketManager = (SocketManager) context.getBean("socketManager");

            return socketManager.checkId(id);
        } catch (IOException e) {
            CardLayout mainLayout = (CardLayout)(mainPanel.getLayout());
            mainLayout.show(mainPanel, "error");
            log.error(e);
            e.printStackTrace();
        }

        return true;
    }
}