package swimlee.programmers.kakaocode;

import java.util.ArrayList;
import java.util.Collections;

public class LittleFriends {

    char[][] map;
    ArrayList<Tile> alphabets = new ArrayList<>();

    public static void main(String[] args) {
        int m = 3;
        int n = 3;
        String[] board = {"DBA", "C*A", "CDB"};

        LittleFriends littleFriends = new LittleFriends();
        String solution = littleFriends.solution(m, n, board);
        System.out.println("solution = " + solution);
    }

    public String solution(int m, int n, String[] board) {
        map = new char[m][n];

        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = map[i][j];
                if (c >= 'A' && c <= 'Z') {
                    alphabets.add(new Tile(c, i, j));
                }
            }
        }

        Collections.sort(alphabets);

//        for (Tile alphabet : alphabets) {
//            System.out.println(alphabet.c + " ,x: " + alphabet.x + ", y:" + alphabet.y);
//        }

        boolean flag = true;
        StringBuilder answer = new StringBuilder();

        while (flag) {
            flag = false;
            for (int i = 0; i < alphabets.size(); i += 2) {
                Tile first = alphabets.get(i);
                Tile second = alphabets.get(i + 1);

                if (canRemove(first, second)) {
                    alphabets.remove(i);
                    alphabets.remove(i);

                    answer.append(first.c);
                    map[first.x][first.y] = '.';
                    map[second.x][second.y] = '.';

                    flag = true;
                    break;
                }
            }
        }

        if(!alphabets.isEmpty()) return "IMPOSSIBLE";
        return answer.toString();
    }

    private boolean canRemove(Tile first, Tile second) {
        int fx = first.x, fy = first.y;
        int sx = second.x, sy = second.y;

        if (fx == sx) { // 같은 행
            return emptyRow(fx, fy, sy);
        } else if (fy == sy) { // 같은 열
            return emptyColumn(fy, fx, sx);
        } else if (fx < sx) { // 좌상 우하
            return (map[fx][sy] == '.' && emptyRow(fx, fy, sy) && emptyColumn(sy, fx, sx))
                    || (map[sx][fy] == '.' && emptyColumn(fy, fx, sx) && emptyRow(sx, fy, sy));
        } else { // 좌하 우상
            return (map[fx][sy] == '.' && emptyRow(fx, fy, sy) && emptyColumn(sy, sx, fx))
                    || (map[sx][fy] == '.' && emptyColumn(fy, sx, fx) && emptyRow(sx, fy, sy));
        }
    }

    private boolean emptyRow(int x, int startY, int endY) {
        for (int i = startY + 1; i < endY; i++) {
            if(map[x][i] != '.') return false;
        }

        return true;
    }

    private boolean emptyColumn(int y, int startX, int endX) {
        for (int i = startX + 1; i < endX; i++) {
            if(map[i][y] != '.') return false;
        }

        return true;
    }
}

class Tile implements Comparable<Tile> {
    char c;
    int x;
    int y;

    Tile(char c, int x, int y) {
        this.c = c;
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Tile o) {
        if (this.c == o.c) {
            if (this.y == o.y) {
                return this.x - o.x;
            }
            return this.y - o.y;
        }
        return Character.compare(this.c, o.c);
    }
}

