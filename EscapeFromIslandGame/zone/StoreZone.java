package location;

import player.Player;

import java.util.Scanner;

public class StoreZone extends Zone {

    public StoreZone() {
        super("Store");
    }

    @Override
    public void activateZone(Scanner scanner, Player player) {
        System.out.println("Money : " + player.getMoney());
        System.out.println("1. Weapons");
        System.out.println("2. Armors");
        System.out.println("3. Exit");
        System.out.print("Enter your choice : ");

        int selTool = scanner.nextInt();
        int selItemID;
        switch (selTool) {
            case 1:
                selItemID = weaponMenu(scanner);
                buyWeapon(selItemID, player);
                break;
            case 2:
                selItemID = armorMenu(scanner);
                buyArmor(selItemID, player);
                break;
            default:
                break;
        }
    }

    public int armorMenu(Scanner scanner) {
        System.out.println("1. Light \t <Price : 15 - Damage Block : 1>");
        System.out.println("2. Medium \t <Price : 25 - Damage Block : 3>");
        System.out.println("3. Heavy \t <Price : 40 - Damage Block : 5>");
        System.out.println("4. Exit");
        System.out.print("Choose an armor : ");
        int selArmorID = scanner.nextInt();
        return selArmorID;
    }

    public void buyArmor(int itemID, Player player) {
        int avoid = 0, price = 0;
        String aName = null;
        switch (itemID) {
            case 1:
                avoid = 1;
                aName = "Light Armor";
                price = 15;
                break;
            case 2:
                avoid = 3;
                aName = "Medium Armor";
                price = 15;
                break;
            case 3:
                avoid = 5;
                aName = "Heavy Armor";
                price = 40;
                break;
            case 4:
                System.out.println("Exit");
                break;
            default:
                System.out.println("Non-defined choice");
                break;
        }

        if (price > 0) {
            if (player.getMoney() >= price) {
                player.getInventory().setArmor(avoid);
                player.getInventory().setaName(aName);
                player.setMoney(player.getMoney() - price);
                System.out.println(aName + " is bought, damage block: " + player.getInventory().getArmor());
                System.out.println("Total Money :" + player.getMoney());
            } else {
                System.out.println("Insufficient Money!");
            }
        }
    }

    public int weaponMenu(Scanner scanner) {
        System.out.println("1. Pistol\t<Price : 25 - Damage : 2>");
        System.out.println("2. Sword\t<Price : 35 - Damage : 3>");
        System.out.println("3. Rifle\t<Price : 45 - Damage : 7>");
        System.out.println("4. Exit");
        System.out.print("Choose a weapon : ");
        int selWeaponID = scanner.nextInt();
        return selWeaponID;
    }

    public void buyWeapon(int itemID, Player player) {
        int damage = 0, price = 0;
        String wName = null;
        switch (itemID) {
            case 1:
                damage = 2;
                wName = "Pistol";
                price = 25;
                break;
            case 2:
                damage = 3;
                wName = "Sword";
                price = 35;
                break;
            case 3:
                damage = 7;
                wName = "Rifle";
                price = 45;
                break;
            case 4:
                System.out.println("Exit");
                break;
            default:
                System.out.println("Non-defined choice");
                break;
        }

        if (price > 0) {
            if (player.getMoney() > price) {
                player.getInventory().setDamage(damage);
                player.getInventory().setwName(wName);
                player.setMoney(player.getMoney() - price);
                System.out.println(wName + " is bought, current damage : " + player.getTotalDamage());
                System.out.println("Total Money :" + player.getMoney());
            } else {
                System.out.println("Insufficient Money!");
            }
        }
    }

}
