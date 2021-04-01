package swimlee.programmers.line2021upper;

import java.util.*;

public class Prob1 {

    public static void main(String[] args) {
        Prob1 prob1 = new Prob1();

        String[] table = {"SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"};
        String[] languages = {"PYTHON", "C++", "SQL"};
        int[] preference = {7, 5,5};

        String solution = prob1.solution(table, languages, preference);
        System.out.println("solution = " + solution);
    }

    public String solution(String[] table, String[] languages, int[] preference) {
        String answer = "";

        List<Map<String, Integer>> maps = new ArrayList<>(5);
        List<String> jobNameList = new ArrayList<>();
        List<Integer> scoreList = new ArrayList<>();

        // 직업별 언어 점수 배열 초기화
        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(table[i]);
            String name = st.nextToken();
            jobNameList.add(name);
            Map<String, Integer> jobLanguageScore = new HashMap<>();

            for (int j = 0; j < 5; j++) {
                String language = st.nextToken();
                jobLanguageScore.put(language, 5 - j);
            }

            maps.add(jobLanguageScore);
        }

        for (int i = 0; i < 5; i++) {
            Map<String, Integer> job = maps.get(i);
            int sum = 0;

            for (int j = 0; j < languages.length; j++) {
                Integer preferenceScore = job.getOrDefault(languages[j], 0);
                sum += (preferenceScore * preference[j]);
            }

            scoreList.add(sum);
        }

        answer = jobNameList.get(0);
        int maxScore = scoreList.get(0);

        for (int i = 1; i < 5; i++) {
            if (scoreList.get(i) >= maxScore) {
                if (maxScore == scoreList.get(i)) {
                    if (jobNameList.get(i).compareTo(answer) < 0) {
                        answer = jobNameList.get(i);
                    }
                } else {
                    answer = jobNameList.get(i);
                }

                maxScore = scoreList.get(i);
            }
        }

        System.out.println("maxScore = " + maxScore);
        System.out.println("jobNameList = " + jobNameList);
        System.out.println("scoreList = " + scoreList);
        return answer;
    }

//
//    public String solution(String[] table, String[] languages, int[] preference) {
//        String answer = "";
//
//
//
//        return answer;
//    }

}
