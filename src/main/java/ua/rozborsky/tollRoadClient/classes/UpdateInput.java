package ua.rozborsky.tollRoadClient.classes;

import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;

/**
 * Created by roman on 21.01.2017.
 */
class UpdateInput extends TimerTask {
    CardLayout layout;
    JPanel dialog;

    public UpdateInput(CardLayout layout, JPanel dialog) {
        this.layout = layout;
        this.dialog = dialog;
    }

    public void run() {
        layout.show(dialog, "input");
    }
}
