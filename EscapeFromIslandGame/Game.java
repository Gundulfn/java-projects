import location.*;
import player.Player;

import java.util.Scanner;

public class Game {
    Player player;
    Zone zone;
    Scanner scan = new Scanner(System.in);

    public void login() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to the Escape From Island Game!");
        System.out.println("Please enter a name: ");
        String playerName = scan.nextLine();
        player = new Player(playerName);
        player.selectCharacter(scan);
        start();
    }

    public void start() {
        while (player.getHealth() > 0) {
            System.out.println();
            System.out.println("=================================================");
            System.out.println();
            System.out.println("Zones: ");
            System.out.println("1. Safe House");
            System.out.println("2. Cave");
            System.out.println("3. Forest");
            System.out.println("4. River");
            System.out.println("5. Mine");
            System.out.println("6. Store");
            System.out.print("Select a zone : ");
            int selectedZoneId = scan.nextInt();
            while (selectedZoneId < 0 || selectedZoneId > 5) {
                System.out.print("Please select one of defined zones : ");
                selectedZoneId = scan.nextInt();
            }
            scan.nextLine();

            switch (selectedZoneId) {
                case 1:
                    zone = new SafeZone();
                    break;
                case 2:
                    zone = new Cave();
                    break;
                case 3:
                    zone = new Forest();
                    break;
                case 4:
                    zone = new River();
                    break;
                case 5:
                    zone = new Mine();
                    break;
                case 6:
                    zone = new StoreZone();
                    break;
                default:
                    zone = new SafeZone();
            }

            zone.activateZone(scan, player);

            if (zone.getName().equals("SafeHouse")) {
                if (player.getInventory().isFirewood()
                        && player.getInventory().isFood()
                        && player.getInventory().isWater()) {
                    System.out.println("You escaped from the island!");
                    return;
                }
            }
        }

        System.out.println("You lost!");
    }
}