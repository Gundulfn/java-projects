import model.Course;
import person.*;

public class Main {
    public static void main(String[] args) {

        Course math = new Course("Matematik", "MAT101", "MAT", .8d, .2d);
        Course physics = new Course("Fizik", "FZK101", "FZK", .6d, .4d);
        Course chemistry = new Course("Kimya", "KMY101", "KMY", .5d, .5d);

        Teacher t1 = new Teacher("Mahmut Hoca", "90550000000", "MAT");
        Teacher t2 = new Teacher("Fatma Ayşe", "90550000001", "FZK");
        Teacher t3 = new Teacher("Ali Veli", "90550000002", "KMY");

        math.addTeacher(t1);
        physics.addTeacher(t2);
        chemistry.addTeacher(t3);

        Student s1 = new Student("İnek Şaban", 4, "140144015", math, physics, chemistry);
        s1.addBulkExamNote(50,20,40);
        s1.addBulkVerbalNote(25, 65, 35);
        s1.isPass();

        Student s2 = new Student("Güdük Necmi", 4, "2211133", math, physics, chemistry);
        s2.addBulkExamNote(100,50,40);
        s2.addBulkVerbalNote(55, 80, 1);
        s2.isPass();

        Student s3 = new Student("Hayta İsmail", 4, "221121312", math, physics, chemistry);
        s3.addBulkExamNote(50,20,40);
        s3.addBulkVerbalNote(90, 23, 17);
        s3.isPass();
    }
}