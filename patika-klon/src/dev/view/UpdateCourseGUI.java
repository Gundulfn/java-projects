package dev.view;

import dev.helper.Config;
import dev.helper.Helper;
import dev.model.Course;
import javax.swing.*;

public class UpdateCourseGUI extends JFrame {
    private JPanel wrapper;
    private JTextField fld_course_name;
    private JButton btn_update;
    private Course course;

    UpdateCourseGUI(Course course){
        this.course = course;
        add(wrapper);
        setSize(300,150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        fld_course_name.setText(course.getName());

        btn_update.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_course_name)){
                Helper.showMsg("fill");
            }else{
                if (Course.update(course.getId(), fld_course_name.getText())){
                    Helper.showMsg("done");
                }
                dispose();
            }
        });
    }
}