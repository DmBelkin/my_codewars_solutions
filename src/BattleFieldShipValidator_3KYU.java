import java.util.ArrayList;

public class BattleFieldShipValidator_3KYU {

//    DESCRIPTION:
//    Write a method that takes a field for well-known board game "Battleship" as an argument and returns true if it has a valid disposition of ships, false otherwise. Argument is guaranteed to be 10*10 two-dimension array. Elements in the array are numbers, 0 if the cell is free and 1 if occupied by ship.
//
//            Battleship (also Battleships or Sea Battle) is a guessing game for two players. Each player has a 10x10 grid containing several "ships" and objective is to destroy enemy's forces by targetting individual cells on his field. The ship occupies one or more cells in the grid. Size and number of ships may differ from version to version. In this kata we will use Soviet/Russian version of the game.
//
//
//    Before the game begins, players set up the board and place the ships accordingly to the following rules:
//    There must be single battleship (size of 4 cells), 2 cruisers (size 3), 3 destroyers (size 2) and 4 submarines (size 1). Any additional ships are not allowed, as well as missing ships.
//    Each ship must be a straight line, except for submarines, which are just single cell.
//
//    The ship cannot overlap or be in contact with any other ship, neither by edge nor by corner.
//
//    This is all you need to solve this kata. If you're interested in more information about the game, visit this link.


    public static boolean fieldValidator(int[][] field) {
        ArrayList<ArrayList<Integer>> indexMap = new ArrayList<>();
        for (int i = 0; i <= field.length - 1; i++) {
            for (int j = 0; j <= field[i].length - 1; j++) {
                if (field[i][j] == 1) {
                    if (i != 0 && j != 0 && i != 9 && j != 9) {
                        if (field[i + 1][j + 1] == 1 || field[i - 1][j - 1] == 1
                                || field[i + 1][j - 1] == 1 || field[i - 1][j + 1] == 1) {
                            return false;
                        }
                    }
                    if (i > 0 && field[i - 1][j] == 1 && field[i][j] == 1) {
                        continue;
                    }
                    ArrayList<Integer> collectShips = new ArrayList<>();
                    collectShips.add(field[i][j]);
                    for (int k = i + 1; k <= i + 4 && k <= 9; k++) {
                        if (field[k][j] == 1) {
                            collectShips.add(field[k][j]);
                        }
                        if (field[k][j] == 0 || k == 9) {
                            break;
                        }
                    }
                    for (int s = j + 1; s <= j + 4 && s <= 9; s++) {
                        if (field[i][s] == 1) {
                            collectShips.add(field[i][s]);
                            if (s >= j + 1) {
                                j = s;
                            }
                        }
                        if (field[i][s] == 0 || s == 9) {
                            break;
                        }
                    }
                    indexMap.add(collectShips);
                }
            }
        }
        int fourShip = 0;
        int threeShip = 0;
        int doubleShip = 0;
        int singleShip = 0;
        for (ArrayList<Integer> ship : indexMap) {
            if (ship.size() == 4) {
                fourShip++;
            }
            if (ship.size() == 3) {
                threeShip++;
            }
            if (ship.size() == 2) {
                doubleShip++;
            }
            if (ship.size() == 1) {
                singleShip++;
            }
        }
        System.out.println(fourShip + "-" + threeShip + "-" + doubleShip + "-" + singleShip);
        if (fourShip == 1 && threeShip == 2 && doubleShip == 3 && singleShip == 4 && indexMap.size() == 10) {
            return true;
        }
        return false;
    }

}
