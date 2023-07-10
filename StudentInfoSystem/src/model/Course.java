package model;

import person.*;

public class Course {
    Teacher courseTeacher;
    String name;
    String code;

    String prefix;
    int examNote, verbalNote;
    double examNoteRate, verbalNoteRate;

    public double getExamNoteRate() {
        return examNoteRate;
    }

    public void setExamNoteRate(double examNoteRate) {
        this.examNoteRate = examNoteRate;
    }

    public double getVerbalNoteRate() {
        return verbalNoteRate;
    }

    public void setVerbalNoteRate(double verbalNoteRate) {
        this.verbalNoteRate = verbalNoteRate;
    }

    public Teacher getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(Teacher courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getExamNote() {
        return examNote;
    }

    public void setExamNote(int examNote) {
        this.examNote = examNote;
    }

    public int getVerbalNote() {
        return verbalNote;
    }

    public void setVerbalNote(int verbalNote) {
        this.verbalNote = verbalNote;
    }

    public double getNote(){
        return examNote * examNoteRate + verbalNote * verbalNoteRate;
    }

    public Course(String name, String code, String prefix, double examNoteRate, double verbalNoteRate) {
        this.name = name;
        this.code = code;
        this.prefix = prefix;
        this.examNoteRate = examNoteRate;
        this.verbalNoteRate = verbalNoteRate;
    }

    public void addTeacher(Teacher t) {
        if (this.prefix.equals(t.getBranch())) {
            this.courseTeacher = t;
            System.out.println("İşlem başarılı");
        } else {
            System.out.println(t.getName() + " Akademisyeni bu dersi veremez.");
        }
    }

    public void printTeacher() {
        if (courseTeacher != null) {
            System.out.println(this.name + " dersinin Akademisyeni : " + courseTeacher.getName());
        } else {
            System.out.println(this.name + " dersine Akademisyen atanmamıştır.");
        }
    }
}
