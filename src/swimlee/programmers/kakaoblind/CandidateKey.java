package swimlee.programmers.kakaoblind;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * 완전탐색 - 조합 ?
 * 비트마스크, 다시해보기
 */

public class CandidateKey {

    public static void main(String[] args) {
        String[][] relation = {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}};
        CandidateKey candidateKey = new CandidateKey();
        int solution = candidateKey.solution(relation);
        System.out.println("solution = " + solution);
    }

    public int solution(String[][] relation) {

        int row = relation.length;
        int col = relation[0].length;

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 1; i < (1 << col); i++) {
            if(!checkUniqueness(i,relation, row, col)) continue;
            if(!checkMinimality(i, list)) continue;
            list.add(i);
        }

        return list.size();
    }

    public boolean checkUniqueness(int set, String[][] relation, int row, int col) {
        ArrayList<Integer> s = getSet(set, col);
        HashSet<String> hashSet = new HashSet<>();
        for (int i = 0; i < row; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j : s) {
                sb.append(relation[i][j]).append(" ");
            }
            hashSet.add(sb.toString());
        }
        return hashSet.size() == row;
    }

    public boolean checkMinimality(int set, ArrayList<Integer> list) {
        for (int l : list) {
            if ((l & set) == l) return false;
        }
        return true;
    }

    public ArrayList<Integer> getSet(int set, int col) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < col; i++) {
            if((set & (1<<i)) != 0) result.add(i);
        }
        return result;
    }


}
