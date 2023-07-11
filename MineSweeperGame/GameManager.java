import java.util.Scanner;

public class GameManager {

    public static void initializeGame(){
        Scanner input = new Scanner(System.in);

        System.out.println("Mayın Tarlası Oyuna Hoşgeldiniz !");

        System.out.print("Lütfen satır adetini giriniz : ");
        int rowCount = input.nextInt();

        System.out.print("Lütfen sütun adetini giriniz : ");
        int colCount = input.nextInt();

        System.out.println("Mayınların Konumu");
        MineSweeper mineSweeper = new MineSweeper(rowCount, colCount);
        startGame(mineSweeper, input);
    }
    public static void startGame(MineSweeper mineSweeper, Scanner input) {
        int x, y;
        GameState selectionResult;

        System.out.println( "===========================\n" +
                            "Oyun başlamıştır !" );

        while(mineSweeper.isThereHiddenEmptyFieldPart()){
            mineSweeper.printMineField(false);

            System.out.print("Satır Giriniz : ");
            x = input.nextInt();

            System.out.print("Sütun Giriniz : ");
            y = input.nextInt();

            if(mineSweeper.isSelectionInMineField(x, y)){
                selectionResult = mineSweeper.selectFieldPart(x, y);

                if(selectionResult == GameState.WIN_GAME){
                    System.out.println("Oyunu Kazandınız !");
                    mineSweeper.printMineField(false);
                    break;
                }else if(selectionResult == GameState.LOSE_GAME){
                    System.out.println("Game Over!!");
                    break;
                }

                System.out.print("===========================\n");
            }
        }
    }
}
