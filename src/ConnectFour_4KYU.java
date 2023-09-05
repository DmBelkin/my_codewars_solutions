import java.util.*;

public class ConnectFour_4KYU {

//    DESCRIPTION:
//    Connect Four
//    Take a look at wiki description of Connect Four game:
//
//    Wiki Connect Four
//
//    The grid is 6 row by 7 columns, those being named from A to G.
//
//    You will receive a list of strings showing the order of the pieces which dropped in columns:
//
//    List<String> myList = new ArrayList<String>(Arrays.asList(
//            "A_Red",
//            "B_Yellow",
//            "A_Red",
//            "B_Yellow",
//            "A_Red",
//            "B_Yellow",
//            "G_Red",
//            "B_Yellow"
//    ));
//    The list may contain up to 42 moves and shows the order the players are playing.
//
//    The first player who connects four items of the same color is the winner.
//
//    You should return "Yellow", "Red" or "Draw" accordingly.


    public static Map<Integer, String> map = new TreeMap<>();

    public static String whoIsWinner(List<String> piecesPositionList) {
        System.out.println(piecesPositionList);
        System.out.println();
        String[] key = {"A", "B", "C", "D", "E", "F", "G"};
        int[] indexes = {0, 0, 0, 0, 0, 0, 0};
        String[][] field = new String[6][7];
        int[][] fieldIndexes = new int[6][7];
        for (int j = 0; j <= piecesPositionList.size() - 1; j++) {
            String[] arr = piecesPositionList.get(j).split("_");
            for (int i = 0; i <= key.length - 1; i++) {
                if (arr[0].equals(key[i])) {
                    field[indexes[i]][i] = piecesPositionList.get(j);
                    fieldIndexes[indexes[i]][i] = j;
                    indexes[i]++;
                }
            }
        }
        for (int i = field.length -1; i >= 0; i--) {
            for (int j = 0; j <= field[i].length - 1; j++) {
                if (field[i][j] == null) {
                    continue;
                }
                getWinner(field, i, j, field[i][j], fieldIndexes);
            }
        }
        for (Map.Entry<Integer, String> mapped : map.entrySet()) {
            String result = mapped.getValue();
            map.clear();
            return result;
        }
        return "Draw";
    }

    public static void getWinner(String[][] field, int y, int x, String player, int[][] indexes) {
        int count = 0;
        String step[] = player.split("_");
        List<Integer> list = new ArrayList<>();
        for (int i = y; i <= y + 3 && i <= field.length - 1; i++) {
            if (field[i][x] == null) {
                continue;
            }
            String[] nextStep = field[i][x].split("_");
            if (nextStep[1].equals(step[1])) {
                list.add(indexes[i][x]);
                count++;
            } else {
                count = 0;
            }
            if (count == 4) {
                Collections.sort(list);
                map.put(list.get(list.size() - 1), nextStep[1]);
            }
        }
        count = 0;
        List<Integer> list1 = new ArrayList<>();
        for (int j = x; j <= x + 3 && j <= field[0].length - 1; j++) {
            if (field[y][j] == null) {
                continue;
            }
            String[] nextStep = field[y][j].split("_");
            if (nextStep[1].equals(step[1])) {
                list1.add(indexes[y][j]);
                count++;
            } else {
                count = 0;
            }
            if (count == 4) {
                Collections.sort(list1);
                map.put(list1.get(list1.size() - 1), nextStep[1]);
            }
        }
        count = 0;
        List<Integer> list2 = new ArrayList<>();
        int s = x;
        for (int m = y; m <= y + 3 && m <= field.length - 1 && s <= field[0].length - 1; m++) {
            if (field[m][s] == null) {
                continue;
            }
            String[] nextStep = field[m][s].split("_");
            if (nextStep[1].equals(step[1])) {
                list2.add(indexes[m][s]);
                count++;
            } else {
                count = 0;
            }
            if (count == 4) {
                Collections.sort(list2);
                map.put(list2.get(list2.size() - 1), nextStep[1]);
            }
            s++;
        }
        count = 0;
        List<Integer> list3 = new ArrayList<>();
        int z = x;
        for (int m = y; m <= y + 3 && m <= field.length - 1 && z >= 0; m++) {
            if (field[m][z] == null) {
                continue;
            }
            String[] nextStep = field[m][z].split("_");
            if (nextStep[1].equals(step[1])) {
                list3.add(indexes[m][z]);
                count++;
            } else {
                count = 0;
            }
            if (count == 4) {
                Collections.sort(list3);
                map.put(list3.get(list3.size() - 1), nextStep[1]);
            }
            z--;
        }
    }

}
