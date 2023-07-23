import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriQueue {
    public static void main(String[] args) {
        // Comparator çift sayılara öncelik veriyor
        PriorityQueue<String> pq = new PriorityQueue<String>(20,
                new Comparator<String>() {
                    public int compare(String i, String j) {
                        return i.compareTo(j);
                    }
                });

        pq.add("Burak");
        pq.add("Lale");
        pq.add("Ada");

        // öğeleri Comparator'un sırayla yazdır
        Iterator<String> itr = pq.iterator();

        while (itr.hasNext())
            System.out.println(itr.next());
    }
}