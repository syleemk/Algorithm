package swimlee.programmers.dfsbfs;

public class WordTrans {
    static int answer = 51;
    boolean[] check;

    public static void main(String[] args) {
        String begin = "hit";
        String target = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log"};

        WordTrans wordTrans = new WordTrans();
        int solution = wordTrans.solution(begin, target, words);

        System.out.println(solution);
    }

    public int solution(String begin, String target, String[] words) {
        check = new boolean[words.length];

        dfs(begin, target, words, 0);

        if(answer == 51) return 0;

        return answer;
    }
    
    public void dfs(String now, String target, String[] words, int num){
        if (target.equals(now)) {
            answer = Math.min(answer, num);
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if(check[i]) continue;
            
            int cnt = 0;
            for (int j = 0; j < words[i].length(); j++) {
                if(now.charAt(j) == words[i].charAt(j)) cnt++;
            }
            
            //현재 가리키는 단어와 한글자만 차이나는 경우
            if (cnt == words[i].length() - 1) {
                check[i] = true;
                dfs(words[i], target, words, num+1);
                check[i] = false;
            }
        }
    }

}
