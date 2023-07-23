import java.io.FileReader;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) {
        try(FileReader printStream = new FileReader("print.txt")){
            int i = printStream.read();
            while(i != -1)
            {
                System.out.print((char) i);
                i = printStream.read();
            }
        }catch (Exception e){

        }
    }
}