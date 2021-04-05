package swimlee.programmers.summer_winter_coding;

/**
 * 최대공약수, 최소공배수
 */

public class RightRectangle {

    public static void main(String[] args) {
        RightRectangle rightRectangle = new RightRectangle();
        long solution = rightRectangle.solution(8, 12);
        System.out.println("solution = " + solution);
    }

    public long solution(int w, int h) {
        return (long)w * h - (w + h - gcd(w, h));
    }

    public long gcd(long a, long b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
}
