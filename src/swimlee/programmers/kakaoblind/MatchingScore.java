package swimlee.programmers.kakaoblind;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * regex 사용!
 *
 * regex "group" 개념 !! group지정해주면 group(0)은 전체 패턴, group(1)부터 지정해준 그룹 패턴
 * regex 단어 경계는 [a-zA-Z0-9]에 대해서만 단어 경계를 형성한다 (특수문자는 제외한다) - regex 단어경계로 검색해보기
 *
 * 출처 : https://enterkey.tistory.com/353
 */

public class MatchingScore {

    public static void main(String[] args) {
//        String word = "blind";
//        String[] pages = {
//                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>",
//                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>",
//                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"
//        };

        String word = "Muzi";
        String[] pages = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"};

        MatchingScore matchingScore = new MatchingScore();
        int solution = matchingScore.solution(word, pages);
        System.out.println("solution = " + solution);
    }

    public int solution(String word, String[] pages) {

        Pattern wordPattern = Pattern.compile("\\b" + word + "\\b", Pattern.CASE_INSENSITIVE);
        Pattern metaPattern = Pattern.compile("<meta property=\"og:url\" content=\"https://(\\S+)\"");
        Pattern hrefPattern = Pattern.compile("<a href=\"https://(\\S+)\"");

        HashMap<String, Page> hm = new HashMap<>();
        int index = 0;
        // 해시맵 초기화
        for (String page : pages) {
            Matcher matcher = metaPattern.matcher(page);
            matcher.find();
            String key = matcher.group(1);
            hm.put(key, new Page(index++));
        }

        for (String page : pages) {
            Matcher metaMatcher = metaPattern.matcher(page);
            Matcher hrefMatcher = hrefPattern.matcher(page);

            metaMatcher.find();
            String key = metaMatcher.group(1);
            Page pageInfo = hm.get(key);

            String body = page.split("<body>")[1].split("</body>")[0].replaceAll("[0-9]", " ");
            Matcher wordMatcher = wordPattern.matcher(body);

            while(wordMatcher.find())
                pageInfo.defaultCnt++;


            while (hrefMatcher.find())
                pageInfo.outerlinkCnt++;


            pageInfo.linkScore = (double) pageInfo.defaultCnt / (double) pageInfo.outerlinkCnt;
            pageInfo.matchScore += pageInfo.defaultCnt;
        }

        for (String page : pages) {
            Matcher metaMatcher = metaPattern.matcher(page);
            Matcher hrefMatcher = hrefPattern.matcher(page);

            metaMatcher.find();
            String key = metaMatcher.group(1);

            while (hrefMatcher.find()) {
                String link = hrefMatcher.group(1);
                if(hm.containsKey(link))
                    hm.get(link).matchScore += hm.get(key).linkScore;
            }
        }

        ArrayList<Page> list = new ArrayList<>(hm.values());
        Collections.sort(list);

//        for (String s : hm.keySet()) {
//            System.out.println(s);
//        }

//        for (Page page : list) {
//            System.out.println("linkscore: " + page.linkScore + ", matchscore: " + page.matchScore);
//        }

        return list.get(0).index;
    }
}

class Page implements Comparable<Page>{
    int index;
    int defaultCnt = 0;
    int outerlinkCnt = 0;
    double linkScore = 0;
    double matchScore = 0;

    Page(int index) {
        this.index = index;
    }

    @Override
    public int compareTo(Page o) {
        if (this.matchScore == o.matchScore) {
            return this.index - o.index;
        }
        return -Double.compare(this.matchScore, o.matchScore);
    }
}