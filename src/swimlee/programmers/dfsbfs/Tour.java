package swimlee.programmers.dfsbfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Tour {

    boolean[] check;
    List<String> answer;

    public static void main(String[] args) {
        String[][] tickets1 = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        String[][] tickets2 = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};

        Tour tour = new Tour();
        String[] solution = tour.solution(tickets2);
        System.out.println("Arrays.toString(solution) = " + Arrays.toString(solution));
    }

    public String[] solution(String[][] tickets) {

        check = new boolean[tickets.length];
        List<String> arr = new LinkedList<>();

        for (int i = 0; i < tickets.length; i++) {
            if(!tickets[i][0].equals("ICN")) continue;
            check[i] = true;
            arr.add(tickets[i][0]);
            dfs(tickets[i][1], arr, tickets, 1);
            arr.remove(0);
            check[i] = false;
        }

        return answer.toArray(new String[0]);
    }

    public void dfs(String now, List<String> arr, String[][] tickets, int num) {
        if (num == tickets.length) {
            arr.add(now);
            if (answer == null) {
                answer = new LinkedList<>(arr);
            } else {
                for (int i = 0; i < answer.size(); i++) {
                    //두 문자열이 다른 경우
                    if (!answer.get(i).equals(arr.get(i))) {
                        if(answer.get(i).compareTo(arr.get(i)) > 0) {
                            answer = new LinkedList<>(arr);
                        }
                        break;
                    }
                }
            }
            arr.remove(num);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if(check[i]) continue;
            if (tickets[i][0].equals(now)) {
                check[i] = true;
                arr.add(now);
                dfs(tickets[i][1], arr, tickets, num + 1);
                arr.remove(num);
                check[i] = false;
            }
        }
    }
}
