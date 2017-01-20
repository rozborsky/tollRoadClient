package ua.rozborsky.tollRoadClient.classes;

import org.springframework.stereotype.Service;
import ua.rozborsky.tollRoadClient.interfaces.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Timer;


/**
 * Created by roman on 18.01.2017.
 */
@Service("window")
public class Window implements View {
    private JPanel dialog;
    private Font FONT = new Font("Courier New", Font.BOLD, 200);
    private Color color = new Color(145,45,45);
    private int delay = 2;

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
        panel.setBackground(color);

        panel.add(image(), BorderLayout.WEST);
        panel.add(titlePanel(), BorderLayout.EAST);
        panel.add(dialogPanel(), BorderLayout.SOUTH);
        frame.add(panel);
    }

    private JLabel image() {
        File logo = new File("src\\main\\resources\\image\\5.png");
        String path = logo.getAbsolutePath();
        JLabel label = new JLabel(new ImageIcon(path));

        return label;
    }

    private JPanel titlePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setLayout(new BorderLayout());

        JLabel title = new JLabel("tollroad");
        title.setFont(FONT);
        title.setForeground(Color.WHITE);
        panel.add(title, BorderLayout.NORTH);

        return panel;
    }

    private JPanel dialogPanel() {
        dialog = new JPanel();
        dialog.setBackground(color);
        dialog.setLayout(new CardLayout());

        dialog.add(inputPanel(), "input");
        dialog.add(notValidId(), "notValidId");
        dialog.add(greetingPanel(), "greeting");
        dialog.add(notExistDriver(), "notExistDriver");

        CardLayout layout = (CardLayout)(dialog.getLayout());
        layout.show(dialog, "input");

        return dialog;
    }

    private JPanel inputPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setLayout(new BorderLayout());

        JTextField input = new JTextField(10);
        input.setFont(new Font("Courier New", Font.BOLD, 200));
        input.setDocument(new JTextFieldLimit());
        input.addActionListener(inputListener(input));
        panel.add(input, BorderLayout.EAST);

        return panel;
    }

    private JPanel notValidId() {
        JPanel panel = new JPanel();
        panel.setBackground(color);

        JLabel error = new JLabel("not valid id");
        error.setForeground(Color.white);
        error.setFont(FONT);
        panel.add(error);

        return panel;
    }

    private JPanel notExistDriver() {
        JPanel panel = new JPanel();
        panel.setBackground(color);

        JLabel error = new JLabel("Driver not exist");
        error.setForeground(Color.white);
        error.setFont(FONT);
        panel.add(error);

        return panel;
    }

    private JPanel greetingPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(color);

        JLabel greeting = new JLabel("Have a nice trip");
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
                    System.out.println(id);//todo tmp

                    if(checkId(id)) {
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

    private boolean checkId(int id) {
        return true;
    }
}
