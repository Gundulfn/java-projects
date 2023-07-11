import java.util.Scanner;

public class GameManager {

    public static void startGame(MineSweeper mineSweeper) {
        Scanner input = new Scanner(System.in);
        int x, y;
        GameState selectionResult;

        System.out.println( "===========================\n" +
                            "Mayın Tarlası Oyuna Hoşgeldiniz !" );

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
