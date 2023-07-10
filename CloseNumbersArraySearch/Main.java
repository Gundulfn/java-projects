import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int inputNum, lowestCloseNum, highestCloseNum;
        int[] intArray;

        System.out.print("Dizi : ");
        String arrayStr = input.nextLine();

        // To take out curly brackets
        arrayStr = arrayStr.substring(1, arrayStr.length() - 1);
        String[] arrayStrArray = arrayStr.split(",");

        intArray = new int[arrayStrArray.length];
        for(int i = 0; i < intArray.length; i++){
            intArray[i] = Integer.parseInt(arrayStrArray[i]);
        }

        System.out.print("Girilen Sayı : ");
        inputNum = input.nextInt();
        input.nextLine(); // Resetting line

        lowestCloseNum = highestCloseNum = intArray[0];

        for(int num : intArray){
            if(Math.abs(num - inputNum) < Math.abs(inputNum - lowestCloseNum) && num < inputNum){
                lowestCloseNum = num;
            }

            if(Math.abs(num - inputNum) < Math.abs(inputNum - highestCloseNum) && num > inputNum){
                highestCloseNum = num;
            }
        }

        System.out.println("Girilen sayıdan küçük en yakın sayı : " + lowestCloseNum);
        System.out.println("Girilen sayıdan büyük en yakın sayı : " + highestCloseNum);
    }
}
