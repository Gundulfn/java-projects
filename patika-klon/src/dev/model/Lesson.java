package dev.model;

import dev.helper.DBConnector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Lesson {
    private int id;
    private int userId;
    private int lessonId;
    private String name;
    private String lang;
    private Course course;
    private User educator;

    public Lesson(int id, int userId, int lessonId, String name, String lang) {
        this.id = id;
        this.userId = userId;
        this.lessonId = lessonId;
        this.name = name;
        this.lang = lang;
        this.course = Course.getFetch(lessonId);
        this.educator = User.getFetch(userId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getEducator() {
        return educator;
    }

    public void setEducator(User educator) {
        this.educator = educator;
    }

    public static ArrayList<Lesson> getList() {
        ArrayList<Lesson> lessonList = new ArrayList<>();
        Lesson obj;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM lesson");
            while (rs.next()) {
                int id = rs.getInt("id");
                int userId = rs.getInt("userId");
                int lessonId = rs.getInt("lessonId");
                String name = rs.getString("name");
                String lang = rs.getString("lang");
                obj = new Lesson(id, userId, lessonId, name, lang);
                lessonList.add(obj);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return lessonList;
    }

    public static boolean add(int userId, int lessonId, String name, String lang){
        String query = "INSERT INTO lesson (userId, lessonId, name, lang) VALUES (?,?,?,?)";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,userId);
            pr.setInt(2,lessonId);
            pr.setString(3,name);
            pr.setString(4,lang);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return true;
    }
    public static ArrayList<Lesson> getListByUser(int userId) {
        ArrayList<Lesson> lessonList = new ArrayList<>();
        Lesson obj;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM lesson WHERE userId = " + userId);

            while (rs.next()) {
                int id = rs.getInt("id");
                int _userId = rs.getInt("userId");
                int lessonId = rs.getInt("lessonId");
                String name = rs.getString("name");
                String lang = rs.getString("lang");
                obj = new Lesson(id, _userId, lessonId, name, lang);
                lessonList.add(obj);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lessonList;
    }
    public static boolean delete(int id) {
        String query = "DELETE FROM lesson WHERE id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);

            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return true;
    }
}
