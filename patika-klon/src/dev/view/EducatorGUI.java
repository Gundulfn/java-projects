package dev.view;

import dev.helper.Config;
import javax.swing.*;

public class EducatorGUI extends JFrame {
    private JPanel wrapper;

    public EducatorGUI(){
        add(wrapper);
        setSize(400,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
    }
}
