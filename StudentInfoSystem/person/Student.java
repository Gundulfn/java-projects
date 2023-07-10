package person;

import model.Course;

public class Student {
    String name, stuNo;
    int classes;
    Course math;
    Course physics;
    Course chemistry;
    double average;
    boolean isPass;

    public Student(String name, int classes, String stuNo, Course math, Course physics, Course chemistry) {
        this.name = name;
        this.classes = classes;
        this.stuNo = stuNo;
        this.math = math;
        this.physics = physics;
        this.chemistry = chemistry;

        calcAverage();
        this.isPass = false;
    }

    public void addBulkExamNote(int math, int physics, int chemistry) {

        if (math >= 0 && math <= 100) {
            this.math.setExamNote(math);
        }

        if (physics >= 0 && physics <= 100) {
            this.physics.setExamNote(physics);
        }

        if (chemistry >= 0 && chemistry <= 100) {
            this.chemistry.setExamNote(chemistry);
        }
    }

    public void addBulkVerbalNote(int math, int physics, int chemistry) {

        if (math >= 0 && math <= 100) {
            this.math.setVerbalNote(math);
        }

        if (physics >= 0 && physics <= 100) {
            this.physics.setVerbalNote(physics);
        }

        if (chemistry >= 0 && chemistry <= 100) {
            this.chemistry.setVerbalNote(chemistry);
        }
    }

    public void isPass() {
        if (this.math.getNote() == 0 || this.physics.getNote() == 0 || this.chemistry.getNote() == 0) {
            System.out.println("Notlar tam olarak girilmemiş");
        } else {
            this.isPass = isCheckPass();
            printNote();
            System.out.println("Ortalama : " + this.average);
            if (this.isPass) {
                System.out.println("Sınıfı Geçti. ");
            } else {
                System.out.println("Sınıfta Kaldı.");
            }
        }
    }

    public void calcAverage() {
        this.average = (this.physics.getNote() + this.chemistry.getNote() + this.math.getNote()) / 3;
    }

    public boolean isCheckPass() {
        calcAverage();
        return this.average > 55;
    }

    public void printNote() {
        System.out.println("=========================");
        System.out.println("Öğrenci : " + this.name);
        System.out.println("Matematik Sınav Notu : " + this.math.getExamNote());
        System.out.println("Fizik Sınav Notu : " + this.physics.getExamNote());
        System.out.println("Kimya Sınav Notu : " + this.chemistry.getExamNote());
        System.out.println("-------------------------");
        System.out.println("Matematik Sözlü Notu : " + this.math.getVerbalNote());
        System.out.println("Fizik Sözlü Notu : " + this.physics.getVerbalNote());
        System.out.println("Kimya Sözlü Notu : " + this.chemistry.getVerbalNote());
        System.out.println("-------------------------");
        System.out.println("Matematik Ders Notu : " + this.math.getNote());
        System.out.println("Fizik Ders Notu : " + this.physics.getNote());
        System.out.println("Kimya Ders Notu : " + this.chemistry.getNote());
    }

}