package swimlee.programmers.greedy;

public class JoyStick {

    public static void main(String[] args) {
        JoyStick joyStick = new JoyStick();
        int solution = joyStick.solution("AAA");
        System.out.println("solution = " + solution);
    }

    public int solution(String name) {
        int answer = 0;
        int curIdx = 0;

        for (int i = 0; i < name.length(); i++) {
            int move = 0;
            if (name.charAt(i) != 'A') {
                move = Math.min(i - curIdx, name.length() - (i - curIdx));
                curIdx = i;
                move += Math.min(name.charAt(i) - 'A', 26 - (name.charAt(i) - 'A'));
            }
            answer += move;
        }

        boolean[] visited = new boolean[name.length()];


        return answer;
    }
}
