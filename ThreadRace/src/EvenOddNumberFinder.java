import java.util.ArrayList;

public class EvenOddNumberFinder implements Runnable {
    private ArrayList<Integer> numberList, oddNumberList, evenNumberList;

    public EvenOddNumberFinder() {
        oddNumberList = new ArrayList<>();
        evenNumberList = new ArrayList<>();
    }

    public void setNumberList(ArrayList<Integer> numberList) {
        this.numberList = numberList;
    }

    @Override
    public void run() {
        for (Integer i : numberList) {
            if (i % 2 == 0) {
                evenNumberList.add(i);
            } else {
                oddNumberList.add(i);
            }
        }

        System.out.println(Thread.currentThread().getName() + "\nEven numbers: " + evenNumberList
                + "\nOdd numbers: " + oddNumberList);
    }
}
