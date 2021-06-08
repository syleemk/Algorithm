package swimlee.programmers.KakaoIntern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * dfs 아닌가?
 */

public class BadUser {

    int answer = 0;
    boolean[] userCheck;
    boolean[] bannedCheck;
    HashSet<String> hs = new HashSet<>();

    public static void main(String[] args) {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
//        String[] banned_id = {"fr*d*", "abc1**"};
        String[] banned_id = {"*rodo", "*rodo", "******"};

        BadUser badUser = new BadUser();
        int solution = badUser.solution(user_id, banned_id);
        System.out.println("solution = " + solution);
    }

    public int solution(String[] user_id, String[] banned_id) {

        userCheck = new boolean[user_id.length];
        bannedCheck = new boolean[banned_id.length];

        ArrayList<String> list = new ArrayList<>();
        dfs(user_id, banned_id,0, list);

        return answer;
    }

    private void dfs(String[] user_id, String[] banned_id, int idx, ArrayList<String> list) {

        if(idx >= banned_id.length) {
            String temp = "";
            Collections.sort(list);
            for (String s : list) {
                temp += s;
            }

            if (!hs.contains(temp)) {
                hs.add(temp);
                System.out.println("temp = " + temp);
                answer++;
            }

            return;
        }

        for (int i = 0; i < user_id.length; i++) {
            if(userCheck[i]) continue;
            if (check(user_id[i], banned_id[idx])) {
                userCheck[i] = true;
                bannedCheck[idx] = true;
                list.add(user_id[i]);
                dfs(user_id, banned_id, idx + 1, list);
                userCheck[i] = false;
                bannedCheck[idx] = false;
                list.remove(user_id[i]);
            }
        }
    }

    private boolean check(String userId, String bannedId) {
        if(userId.length() != bannedId.length()) return false;

        for (int i = 0; i < userId.length(); i++) {
            if(userId.charAt(i) != bannedId.charAt(i) && bannedId.charAt(i) != '*')
                return false;
        }

        return true;
    }
}
