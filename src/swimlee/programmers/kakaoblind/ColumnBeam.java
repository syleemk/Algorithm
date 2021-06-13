package swimlee.programmers.kakaoblind;

import java.util.*;

public class ColumnBeam {

    public static void main(String[] args) {
        int n = 5;
        // x,y,a,b
        // a: 0 - 기둥, 1 - 보
        // b: 0 - 삭제, 1 - 설치
        int[][] build_frame = {{0, 0, 0, 1}, {2, 0, 0, 1}, {4, 0, 0, 1}, {0, 1, 1, 1}, {1, 1, 1, 1}, {2, 1, 1, 1}, {3, 1, 1, 1}, {2, 0, 0, 0}, {1, 1, 1, 0}, {2, 2, 0, 1}};

        ColumnBeam columnBeam = new ColumnBeam();
        int[][] solution = columnBeam.solution(n, build_frame);
        for (int[] ints : solution) {
            System.out.println(Arrays.toString(ints));
        }
    }

    HashMap<String, Block> hm = new HashMap<>();

    public int[][] solution(int n, int[][] build_frame) {

        for (int[] oper : build_frame) {
            int x = oper[0];
            int y = oper[1];
            int a = oper[2];
            int b = oper[3];

            if (b == 1) {// 설치하는 경우
                if (a == 0) {// 기둥
                    if (canCreateColumn(x, y)) {// 바닥에 설치하는 경우
                        String key = x + "," + y + "," + a;
                        hm.put(key, new Block(x, y, a));
                    }
                } else {// 보
                    if (canCreateBeam(x, y)) {
                        String key = x + "," + y + "," + a;
                        hm.put(key, new Block(x, y, a));
                    }
                }
            } else {// 삭제하는 경우
                String key = x + "," + y + "," + a;
                Block block = hm.get(key);
                hm.remove(key);

                if (a == 0) {// 기둥
                    if (!canDeleteColumn(x, y)) hm.put(key, block);
                } else {// 보
                    if (!canDeleteBeam(x, y)) hm.put(key, block);
                }
            }
        }

        ArrayList<Block> list = new ArrayList<>(hm.values());
        Collections.sort(list);

        int[][] answer = new int[list.size()][3];
        for (int i = 0; i < list.size(); i++) {
            Block block = list.get(i);
            answer[i][0] = block.x;
            answer[i][1] = block.y;
            answer[i][2] = block.a;
        }

        return answer;
    }

    private boolean canCreateBeam(int x, int y) {
        String leftColumn = x + "," + (y - 1) + "," + 0;
        String rightColumn = (x + 1) + "," + (y - 1) + "," + 0;
        String leftBeam = (x - 1) + "," + y + "," + 1;
        String rightBeam = (x + 1) + "," + y + "," + 1;

        return hm.containsKey(leftColumn) || hm.containsKey(rightColumn) ||
                (hm.containsKey(leftBeam) && hm.containsKey(rightBeam));
    }

    private boolean canCreateColumn(int x, int y) {
        if (y == 0) return true;

        String leftBeam = (x - 1) + "," + y + "," + 1;
        String rightBeam = x + "," + y + "," + 1;
        String belowColumn = x + "," + (y - 1) + "," + 0;

        return hm.containsKey(leftBeam) || hm.containsKey(rightBeam) || hm.containsKey(belowColumn);
    }

    private boolean canDeleteColumn(int x, int y) {
        String leftBeam = (x - 1) + "," + (y + 1) + "," + 1;
        String rightBeam = x + "," + (y + 1) + "," + 1;
        String upperColumn = x + "," + (y + 1) + "," + 0;

        if (hm.containsKey(upperColumn)) {
            if (!canCreateColumn(x, y + 1)) return false;
        }

        if (hm.containsKey(leftBeam)) {
            if (!canCreateBeam(x - 1, y + 1)) return false;
        }

        if (hm.containsKey(rightBeam)) {
            if (!canCreateBeam(x, y + 1)) return false;
        }

        return true;
    }

    private boolean canDeleteBeam(int x, int y) {
        String leftBeam = (x - 1) + "," + y + "," + 1;
        String rightBeam = (x + 1) + "," + y + "," + 1;
        String leftColumn = x + "," + y + "," + 0;
        String rightColumn = (x + 1) + "," + y + "," + 0;

        if (hm.containsKey(leftBeam)) {
            if (!canCreateBeam(x - 1, y)) return false;
        }

        if (hm.containsKey(rightBeam)) {
            if (!canCreateBeam(x + 1, y)) return false;
        }

        if (hm.containsKey(leftColumn)) {
            if (!canCreateColumn(x, y)) return false;
        }

        if (hm.containsKey(rightColumn)) {
            if (!canCreateColumn(x + 1, y)) return false;
        }

        return true;
    }
}

class Block implements Comparable<Block> {
    int x;
    int y;
    int a;

    Block(int x, int y, int a) {
        this.x = x;
        this.y = y;
        this.a = a;
    }

    @Override
    public int compareTo(Block o) {
        if (this.x == o.x) {
            if (this.y == o.y) {
                return this.a - o.a;
            }
            return this.y - o.y;
        }
        return this.x - o.x;
    }
}
