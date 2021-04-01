package swimlee.programmers.line2021upper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Step2 {

    public static void main(String[] args) {
        Step2 step2 = new Step2();

        String program = "line";
        String[] flag_rules = {"-s STRING", "-n NUMBER", "-e NULL"};
        String[] commands = {"line -s 123 -n HI", "line fun"};

        boolean[] solution = step2.solution(program, flag_rules, commands);
        System.out.println(Arrays.toString(solution));
    }

    public boolean[] solution(String program, String[] flag_rules, String[] commands) {
        boolean[] answer = new boolean[commands.length];

        Arrays.fill(answer, true);

        Map<String, String> flagMap = new HashMap<>();

        for (String flag_rule : flag_rules) {
            StringTokenizer st = new StringTokenizer(flag_rule);
            String flag_name = st.nextToken();
            flag_name = flag_name.replace("-", "");
            String flag_argument_type = st.nextToken();
            flagMap.put(flag_name, flag_argument_type);
        }



        for (int i = 0; i < commands.length; i++) {
            StringTokenizer st = new StringTokenizer(commands[i]);

            //명령어 단위로 나눔
            String[] split = commands[i].split("-");

            System.out.println("Arrays.toString(split) = " + Arrays.toString(split));

            String programName = split[0].trim();
            if (!programName.equals(program)) {
                answer[i] = false;
                continue;
            }

            for (int j = 1; j < split.length; j++) {
                String[] commandToken = split[j].split(" ");
                String flag_name = commandToken[0];
                String flag_argument_type = flagMap.get(flag_name);

                for (int k = 1; k < commandToken.length; k++) {
                    String flag_argument = commandToken[k];
                    if(!argTypeCheck(flag_argument_type, flag_argument)) {
                        answer[i] = false;
                    }
                }
            }

            //flag 단위로 tokenizing
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
