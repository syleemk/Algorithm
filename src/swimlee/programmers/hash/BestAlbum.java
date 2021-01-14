package swimlee.programmers.hash;

import java.util.*;

/**
 * 해시 맵 값을 기준으로 정렬하는 방법
 * keySet을 따로 꺼낸후, 해당 keySet을 원본 해시맵의 value를 기준으로 정렬하는 comparator를 구현해서 정렬
 */

public class BestAlbum {

    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};

        BestAlbum bestAlbum = new BestAlbum();
        int[] solution = bestAlbum.solution(genres, plays);
        System.out.println("solution = " + Arrays.toString(solution));
    }

    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, PriorityQueue<int[]>> hm = new HashMap<>();
        HashMap<String, Integer> totalcount = new HashMap<>();

        Comparator<int[]> comparator = (a, b) -> {
            // 재생수가 같다면, 고유번호가 작은순으로 정렬
            if(a[0] == b[0]) return a[1] - b[1];
            // 재생수가 큰 순으로 정렬
            else return b[0] - a[0];
        };

        for (int i = 0; i < genres.length; i++) {
            int[] tmp = {plays[i], i};
            totalcount.put(genres[i], totalcount.getOrDefault(genres[i], 0) + plays[i]);
            // 처음으로 삽입되는 장르인 경우 우선순위 큐를 생성해줘야함
            if (!hm.containsKey(genres[i])) {
                PriorityQueue<int[]> pq = new PriorityQueue<>(comparator);
                pq.add(tmp);
                hm.put(genres[i], pq);
            } else { // 이미 우선순위 큐가 생성되어있는 경우
                PriorityQueue<int[]> pq = hm.get(genres[i]);
                pq.add(tmp);
            }
        }

        List<String> keySetList = new ArrayList<>(hm.keySet());
        keySetList.sort((a, b) -> totalcount.get(b).compareTo(totalcount.get(a)));

        List<Integer> answer = new LinkedList<>();

        for (String key : keySetList) {
            PriorityQueue<int[]> pq = hm.get(key);
            if (pq.size() >= 2) {
                int first = pq.remove()[1];
                int second = pq.remove()[1];
                answer.add(first);
                answer.add(second);
            } else {
                answer.add(pq.remove()[1]);
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}
