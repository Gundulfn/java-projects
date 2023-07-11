import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] array = {2, 3, 6, 2, 4, 3, 6, 2, 2};
        Arrays.sort(array);

        System.out.print(Arrays.toString(array) + " Repetitive numbers: ");
        for(int i = 1; i < array.length - 1; i++){
            if(array[i] % 2 == 0){
                if(array[i] == array[i + 1] ||array[i] == array[i - 1]){
                    System.out.print(array[i] + " ");

                    for(int j = i + 1; j < array.length; j++){
                        if(array[i] != array[j]){
                            i = j; // Set index at different array element
                            break;
                        }
                    }
                }
            }
        }
    }
}