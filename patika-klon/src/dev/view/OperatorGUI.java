package dev.view;

import dev.helper.Config;
import dev.helper.Helper;
import dev.helper.Item;
import dev.model.Course;
import dev.model.Lesson;
import dev.model.Operator;
import dev.model.User;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class OperatorGUI extends JFrame {

    private JPanel wrapper;
    private JTabbedPane tab_operator;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JButton btn_logout;
    private JPanel pnl_user_list;
    private JScrollPane scrl_user_list;
    private JTable tbl_user_list;
    private JPanel pnl_user_form;
    private JTextField fld_user_name;
    private JTextField fld_user_username;
    private JTextField fld_user_pass;
    private JComboBox cmb_user_type;
    private JButton btn_user_add;
    private JTextField fld_user_id;
    private JButton btn_user_delete;
    private JTextField fld_sh_user_name;
    private JTextField fld_sh_user_username;
    private JComboBox cmb_sh_user_type;
    private JButton btn_user_sh;
    private JPanel pnl_course_list;
    private JScrollPane scrl_course_list;
    private JTable tbl_course_list;
    private JPanel pnl_course_add;
    private JTextField fld_course_name;
    private JButton btn_course_add;
    private JPanel pnl_user_top;
    private JPanel pnl_lesson_list;
    private JScrollPane scrl_lesson_list;
    private JTable tbl_lesson_list;
    private JPanel pnl_lesson_add;
    private JTextField fld_lesson_name;
    private JTextField fld_lesson_lang;
    private JComboBox cmb_lesson_course;
    private JComboBox cmb_lesson_user;
    private JButton btn_lesson_add;
    private DefaultTableModel mdl_user_list;
    private Object[] row_user_list;
    private final Operator operator;
    private DefaultTableModel mdl_course_list;
    private Object[] row_course_list;
    private JPopupMenu courseMenu;
    private DefaultTableModel mdl_lesson_list;
    private Object[] row_lesson_list;

    public OperatorGUI(Operator operator) {
        this.operator = operator;
        add(wrapper);
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        lbl_welcome.setText("Welcome : " + operator.getName());

        mdl_user_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_user_list = {"ID", "Name Surname", "User Name", "Password", "Type"};
        mdl_user_list.setColumnIdentifiers(col_user_list);
        row_user_list = new Object[col_user_list.length];

        loadUserModel();
        tbl_user_list.setModel(mdl_user_list);
        tbl_user_list.getTableHeader().setReorderingAllowed(false);

        tbl_user_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String selec_user_id = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 0).toString();
                fld_user_id.setText(selec_user_id);
            } catch (Exception exception) {
            }
        });

        tbl_user_list.getModel().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int user_id = Integer.parseInt(tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 0).toString());
                String user_name = (tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 1).toString());
                String user_uname = (tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 2).toString());
                String user_pass = (tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 3).toString());
                String user_type = (tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 4).toString());

                if (User.update(user_id, user_name, user_uname, user_pass, user_type)) {
                    Helper.showMsg("done");
                }

                loadUserModel();
                loadEducatorCombo();
                loadLessonModel();
            }
        });

        courseMenu = new JPopupMenu();
        JMenuItem updateMenu = new JMenuItem("Update");
        JMenuItem deleteMenu = new JMenuItem("Delete");
        courseMenu.add(updateMenu);
        courseMenu.add(deleteMenu);

        updateMenu.addActionListener(e -> {
            int select_id = Integer.parseInt(tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(), 0).toString());

            UpdateCourseGUI updateGUI = new UpdateCourseGUI(Course.getFetch(select_id));
            updateGUI.addWindowListener(new WindowAdapter() {

                @Override
                public void windowClosed(WindowEvent e) {
                    loadCourseModel();
                    loadCourseCombo();
                    loadLessonModel();

                }
            });
        });

        deleteMenu.addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int select_id = Integer.parseInt(tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(), 0).toString());
                if (Course.delete(select_id)) {
                    Helper.showMsg("done");
                    loadCourseModel();
                    loadCourseCombo();
                    loadLessonModel();
                } else {
                    Helper.showMsg("Error");
                }
            }
        });

        mdl_course_list = new DefaultTableModel();


        Object[] col_course_list = {"ID", "Course Name"};
        mdl_course_list.setColumnIdentifiers(col_course_list);
        row_course_list = new Object[col_course_list.length];
        loadCourseModel();


        tbl_course_list.setModel(mdl_course_list);
        tbl_course_list.setComponentPopupMenu(courseMenu);
        tbl_course_list.getTableHeader().setReorderingAllowed(false);
        tbl_course_list.getColumnModel().getColumn(0).setMaxWidth(75);


        tbl_course_list.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int selected_row = tbl_course_list.rowAtPoint(point);
                tbl_course_list.setRowSelectionInterval(selected_row, selected_row);
            }
        });

        mdl_lesson_list = new DefaultTableModel();
        Object[] col_lessonList = {"ID", "Lesson Name", "Program Language", "Lesson", "Instractor"};
        mdl_lesson_list.setColumnIdentifiers(col_lessonList);
        row_lesson_list = new Object[col_lessonList.length];

        loadLessonModel();

        tbl_lesson_list.setModel(mdl_lesson_list);
        tbl_lesson_list.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_lesson_list.getTableHeader().setReorderingAllowed(false);
        loadCourseCombo();
        loadEducatorCombo();

        btn_user_add.addActionListener(e -> {

            if (Helper.isFieldEmpty(fld_user_name) || Helper.isFieldEmpty(fld_user_username) || Helper.isFieldEmpty(fld_user_pass)) {

                Helper.showMsg("fill");
            } else {
                String name = fld_user_name.getText();
                String uname = fld_user_username.getText();
                String pass = fld_user_pass.getText();
                String type = cmb_user_type.getSelectedItem().toString();
                if (User.add(name, uname, pass, type)) {
                    Helper.showMsg("done");
                    loadUserModel();
                    loadEducatorCombo();
                    fld_user_name.setText(null);
                    fld_user_username.setText(null);
                    fld_user_pass.setText(null);
                }
            }
        });

        btn_user_delete.addActionListener(e -> {

            if (Helper.isFieldEmpty(fld_user_id)) {
                Helper.showMsg("fill");
            } else {
                if (Helper.confirm("sure")) {
                    int user_id = Integer.parseInt(fld_user_id.getText());
                    if (User.delete(user_id)) {
                        Helper.showMsg("done");

                        loadUserModel();
                        loadEducatorCombo();
                        loadLessonModel();
                        fld_user_id.setText(null);
                    } else {
                        Helper.showMsg("Error");
                    }
                }
            }
        });

        btn_user_sh.addActionListener(e -> {
            String name = fld_sh_user_name.getText();
            String uname = fld_sh_user_username.getText();
            String type = cmb_sh_user_type.getSelectedItem().toString();
            String query = User.searchQuery(name, uname, type);
            loadUserModel(User.searchUserList(query));
        });


        btn_logout.addActionListener(e -> {
            dispose();
            LoginGUI login = new LoginGUI();
        });

        btn_course_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_course_name)) {
                Helper.showMsg("fill");
            } else {
                if (Course.add(fld_course_name.getText())) {
                    Helper.showMsg("done");
                    loadCourseModel();
                    loadCourseCombo();
                    fld_course_name.setText(null);
                } else {
                    Helper.showMsg("Error");
                }
            }

        });

        btn_lesson_add.addActionListener(e -> {

            Item courseItem = (Item) cmb_lesson_course.getSelectedItem();
            Item userItem = (Item) cmb_lesson_user.getSelectedItem();
            if (Helper.isFieldEmpty(fld_lesson_name) || Helper.isFieldEmpty(fld_lesson_lang)) {
                Helper.showMsg("fill");
            } else {
                if (Lesson.add(userItem.getKey(), courseItem.getKey(), fld_lesson_name.getText(), fld_lesson_lang.getText())) {
                    Helper.showMsg("done");
                    loadLessonModel();
                    fld_lesson_lang.setText(null);
                    fld_lesson_name.setText(null);
                } else {
                    Helper.showMsg("Error");
                }
            }

        });
    }

    private void loadLessonModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_lesson_list.getModel();
        clearModel.setRowCount(0);

        int i = 0;
        for (Lesson obj : Lesson.getList()) {
            i = 0;
            row_lesson_list[i++] = obj.getId();
            row_lesson_list[i++] = obj.getName();
            row_lesson_list[i++] = obj.getLang();
            row_lesson_list[i++] = obj.getCourse().getName();
            row_lesson_list[i++] = obj.getEducator().getName();
            mdl_lesson_list.addRow(row_lesson_list);
        }
    }

    private void loadCourseModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_course_list.getModel();
        clearModel.setRowCount(0);
        int i = 0;
        for (Course obj : Course.getList()) {
            i = 0;
            row_course_list[i++] = obj.getId();
            row_course_list[i++] = obj.getName();
            mdl_course_list.addRow(row_course_list);
        }
    }

    public void loadUserModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);
        int i = 0;
        for (User obj : User.getList()) {
            i = 0;
            row_user_list[i++] = obj.getId();
            row_user_list[i++] = obj.getName();
            row_user_list[i++] = obj.getUsername();
            row_user_list[i++] = obj.getPass();
            row_user_list[i++] = obj.getType();
            mdl_user_list.addRow(row_user_list);
        }
    }

    public void loadUserModel(ArrayList<User> list) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);

        int i = 0;
        for (User obj : list) {
            i = 0;
            row_user_list[i++] = obj.getId();
            row_user_list[i++] = obj.getName();
            row_user_list[i++] = obj.getUsername();
            row_user_list[i++] = obj.getPass();
            row_user_list[i++] = obj.getType();
            mdl_user_list.addRow(row_user_list);
        }

    }

    public void loadCourseCombo() {
        cmb_lesson_course.removeAllItems();
        for (Course obj : Course.getList()) {
            cmb_lesson_course.addItem(new Item(obj.getId(), obj.getName()));
        }
    }

    public void loadEducatorCombo() {
        cmb_lesson_user.removeAllItems();
        for (User obj : User.getOnlyEducator()) {
            cmb_lesson_user.addItem(new Item(obj.getId(), obj.getName()));
        }
    }

    public static void main(String[] args) {
        Operator op = new Operator();
        op.setId(1);
        op.setName("Soner ALCI");
        op.setPass("1234");
        op.setType("operator");
        op.setUsername("soner");

        OperatorGUI opGUI = new OperatorGUI(op);
    }
}