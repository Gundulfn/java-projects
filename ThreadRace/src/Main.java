import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> integerList = new ArrayList<>();
        for (int i = 1; i <= 10000; i++)
            integerList.add(i);

        Thread[] threads = new Thread[4];
        EvenOddNumberFinder finder = new EvenOddNumberFinder();
        for (int i = 0; i < threads.length; i++) {
            ArrayList<Integer> subList = new ArrayList<>(integerList.subList(2500 * i, 2500 * (i + 1)));
            finder.setNumberList(subList);
            Thread t = new Thread(finder);
            t.start();

            try {
                System.out.println(t.getName() + " joined");
                t.join();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
