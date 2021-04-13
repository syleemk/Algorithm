package swimlee.programmers.kakaoblind;

import java.util.HashMap;

/**
 * 그냥 리스트 써서 구현해도 됐을 듯
 * 리스트나 그냥 큐나 차이없고, 더 많은 메서드 지원하니까? FIFO 구조만 갖추면됨
 */

public class Cache {

    public static void main(String[] args) {
        int cacheSize = 3;
        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};

        Cache cache = new Cache();
        int solution = cache.solution(cacheSize, cities);
        System.out.println("solution = " + solution);
    }

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        HashMap<String, Integer> hm = new HashMap<>();

        for (int i = 0; i < cities.length; i++) {
            String city = cities[i].toLowerCase();
            if (hm.containsKey(city)) {
                answer += 1;
                hm.put(city, i);
            } else {
                if(hm.size() < cacheSize) hm.put(city, i);
                else {
                    int min = i;
                    String out = "";
                    for (String key : hm.keySet()) {
                        if(min > hm.get(key)) {
                            out = key;
                            min = hm.get(key);
                        }
                    }
                    hm.remove(out);
                    hm.put(city, i);
                }
                answer += 5;
            }
        }

        return answer;
    }
}
