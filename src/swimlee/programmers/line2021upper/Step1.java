package swimlee.programmers.line2021upper;

import java.util.*;

public class Step1 {

    public static void main(String[] args) {
        Step1 step1 = new Step1();

        String program = "line";
        String[] flag_rules = {"-s STRING", "-n NUMBER", "-e NULL"};
        String[] commands = {"line -s 123 -n HI", "line fun"};

        boolean[] solution = step1.solution(program, flag_rules, commands);
        System.out.println(Arrays.toString(solution));
    }

    public boolean[] solution(String program, String[] flag_rules, String[] commands) {
        boolean[] answer = new boolean[commands.length];

        Arrays.fill(answer, true);

        Map<String, String> flagMap = new HashMap<>();

        for (String flag_rule : flag_rules) {
            StringTokenizer st = new StringTokenizer(flag_rule);
            String flag_name = st.nextToken();
            String flag_argument_type = st.nextToken();
            flagMap.put(flag_name, flag_argument_type);
        }

        for (int i = 0; i < commands.length; i++) {
            StringTokenizer st = new StringTokenizer(commands[i]);

            //프로그램이름이 다른 경우 (false)
            String programName = st.nextToken();
            if (!programName.equals(program)) {
                answer[i] = false;
                continue;
            }

            //flag 단위로 tokenizing
            boolean hasToken = st.hasMoreTokens();
            while(st.hasMoreTokens()) {
                String flag_name = st.nextToken();
                String flag_argument_type = null;

                //flag_rule이 명시된 flag_rule인 경우
                if(flagMap.containsKey(flag_name)) {
                    flag_argument_type = flagMap.get(flag_name);
                } else {
                    answer[i] = false;
                    break;
                }

                if(flag_argument_type.equals("NULL")) continue;

                String flag_argument = st.nextToken();

                if(!argTypeCheck(flag_argument_type, flag_argument)) {
                    answer[i] = false;
                    break;
                }
            }
        }

        return answer;
    }

    private boolean argTypeCheck(String flag_argument_type, String flag_argument) {
        if(flag_argument_type.equals("NUMBER")){
            //문자 포함하는 경우
            return !flag_argument.matches(".*[a-zA-Z].*");
        } else if(flag_argument_type.equals("STRING")) { //STRING 타입인 경우
            //숫자 포함하는 경우
            return !flag_argument.matches(".*[0-9].*");
        } else {
            return false;
        }
    }
}
