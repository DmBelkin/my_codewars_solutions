import java.util.*;

public class BattleFieldShipValidator2_3KYU {

//    DESCRIPTION:
//    Description:
//
//    Write a method that takes a field for well-known board game "Battleship" as an argument and returns true if it has a valid disposition of ships, false otherwise. Argument is guaranteed to be 10*10 two-dimension array. Elements in the array are numbers, 0 if the cell is free and 1 if occupied by ship.
//
//            Battleship (also Battleships or Sea Battle) is a guessing game for two players. Each player has a 10x10 grid containing several "ships" and objective is to destroy enemy's forces by targetting individual cells on his field. The ship occupies one or more cells in the grid. Size and number of ships may differ from version to version. In this kata we will use Soviet/Russian version of the game.
//
//    Before the game begins, players set up the board and place the ships accordingly to the following rules:
//
//    There must be single battleship (size of 4 cells), 2 cruisers (size 3), 3 destroyers (size 2) and 4 submarines (size 1). Any additional ships are not allowed, as well as missing ships.
//    Each ship must be a straight line, except for submarines, which are just single cell.
//    The ship cannot overlap, but can be contact with any other ship.
//    The description likes Battleship field validator Kata, the only difference is the rule 3.
//
//    This is all you need to solve this kata. If you're interested in more information about the game, visit this link.

    private int[][] field;

    private List<int[]> coordinates = new ArrayList<>();

    private List<int[]> combination = new ArrayList<>();

    private List<Set<int[]>> list = new ArrayList<>();


    public BattleFieldShipValidator2_3KYU(int[][] field) {
        this.field = field;
    }

    public boolean validate() {
        for (int[] ary : field) {
            System.out.println(Arrays.toString(ary));
        }
        int count = 0;
        for (int i = 0; i <= field.length - 1; i++) {
            for (int j = 0; j <= field[i].length - 1; j++) {
                int[] coord = new int[]{i, j};
                if (field[i][j] == 1) {
                    count++;
                    coordinates.add(coord);
                }
            }
        }
        if (count != 20) {
            return false;
        }
        Collections.sort(coordinates, Comparator.comparingInt(o -> o[0]));
        StringBuilder builder = new StringBuilder();
        for (int i = 4; i > 1; i--) {
            searchShipsHor(i);
        }
        Collections.sort(coordinates, Comparator.comparingInt(o -> o[1]));
        StringBuilder builder1 = new StringBuilder();
        for (int i = 4; i > 1; i--) {
            searchShipsVert(i);
        }
        Collections.sort(list, Comparator.comparingInt(o -> o.size()));
        Collections.reverse(list);
        return combinationBuilder(list);
    }

    public boolean combinationBuilder(List<Set<int[]>> list) {
        for (Set<int[]> four : list) {
            List<Set<int[]>> fieldCombination = new ArrayList<>();
            combination.clear();
            if (four.size() == 4) {
                fieldCombination.add(four);
                combination.addAll(four);
                for (int i = 0; i <= list.size() - 1; i++) {
                    Set<int[]> firstTriplet = list.get(i);
                    if (firstTriplet.size() == 3 && !contains(firstTriplet, combination)) {
                        fieldCombination.add(firstTriplet);
                        combination.addAll(firstTriplet);
                        for (int j = 0; j <= list.size() - 1; j++) {
                            Set<int[]> secondTriplet = list.get(j);
                            if (secondTriplet.size() == 3 && !contains(secondTriplet, combination)) {
                                fieldCombination.add(secondTriplet);
                                combination.addAll(secondTriplet);
                                for (int k = 0; k <= list.size() - 1; k++) {
                                    Set<int[]> duplex1 = list.get(k);
                                    if (duplex1.size() == 2 && !contains(duplex1, combination)) {
                                        fieldCombination.add(duplex1);
                                        combination.addAll(duplex1);
                                        for (int v = 0; v <= list.size() - 1; v++) {
                                            Set<int[]> duplex2 = list.get(v);
                                            if (duplex2.size() == 2 && !contains(duplex2, combination)) {
                                                fieldCombination.add(duplex2);
                                                combination.addAll(duplex2);
                                                for (int z = 0; z <= list.size() - 1; z++) {
                                                    Set<int[]> duplex3 = list.get(z);
                                                    if (duplex3.size() == 2 && !contains(duplex3, combination)) {
                                                        fieldCombination.add(duplex3);
                                                        combination.addAll(duplex3);
                                                        if (fieldCombination.size() == 6) {
                                                            for (int x = 0; x <= coordinates.size() - 1; x++) {
                                                                int[] coord = coordinates.get(x);
                                                                Set<int[]> single = new HashSet<>() {{
                                                                    add(coord);
                                                                }};
                                                                if (!contains(single, combination)) {
                                                                    fieldCombination.add(single);
                                                                    combination.add(coord);
                                                                }
                                                                if (fieldCombination.size() == 10) {
                                                                    return true;
                                                                }
                                                            }
                                                        }
                                                        fieldCombination.remove(duplex3);
                                                        combination.removeAll(duplex3);
                                                    }
                                                }
                                                fieldCombination.remove(duplex2);
                                                combination.removeAll(duplex2);
                                            }
                                        }
                                        fieldCombination.remove(duplex1);
                                        combination.removeAll(duplex1);
                                    }
                                }
                                fieldCombination.remove(secondTriplet);
                                combination.removeAll(secondTriplet);
                            }
                        }
                        fieldCombination.remove(firstTriplet);
                        combination.removeAll(firstTriplet);
                    }
                }
            }
        }
        return false;
    }

    public void searchShipsHor(int length) {
        for (int i = 0; i <= coordinates.size() - 1; i++) {
            Set<int[]> set = new HashSet<>();
            int size = 1;
            for (int x = i; x <= coordinates.size() - 1; x++) {
                if (coordinates.get(i)[0] == coordinates.get(x)[0] &&
                        Math.abs(coordinates.get(i)[1] - coordinates.get(x)[1]) == size) {
                    set.add(coordinates.get(i));
                    set.add(coordinates.get(x));
                    size++;
                }
                if (set.size() == length) {
                    list.add(new HashSet<>(set));
                    set.clear();
                    size = 1;
                }
                if (coordinates.get(i)[0] != coordinates.get(x)[0]
                        || Math.abs(coordinates.get(i)[1] - coordinates.get(x)[1]) > size) {
                    set.clear();
                    size = 1;
                }
            }
        }
    }

    public void searchShipsVert(int length) {
        for (int i = 0; i <= coordinates.size() - 1; i++) {
            Set<int[]> set = new HashSet<>();
            int size = 1;
            for (int x = i; x <= coordinates.size() - 1; x++) {
                if (coordinates.get(i)[1] == coordinates.get(x)[1] &&
                        Math.abs(coordinates.get(i)[0] - coordinates.get(x)[0]) == size) {
                    set.add(coordinates.get(i));
                    set.add(coordinates.get(x));
                    size++;
                }
                if (set.size() == length) {
                    list.add(new HashSet<>(set));
                    set.clear();
                    size = 1;
                }
                if (coordinates.get(i)[1] != coordinates.get(x)[1]
                        || Math.abs(coordinates.get(i)[0] - coordinates.get(x)[0]) > size) {
                    set.clear();
                    size = 1;
                }
            }
        }
    }

    public boolean contains(Set<int[]> set, List<int[]> list) {
        for (int[] arr : set) {
            for (int[] ary : list)
                if (arr[0] == ary[0] && ary[1] == arr[1]) {
                    return true;
                }
        }
        return false;
    }



}
