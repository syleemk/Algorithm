package swimlee.codility.timecomplexity;

public class FrogJmp {
    public static void main(String[] args) {
        FrogJmp frogJmp = new FrogJmp();
        int solution = frogJmp.solution(10,85,30);
        System.out.println("solution = " + solution);
    }

    public int solution(int X, int Y, int D) {
        int distance = Y - X;
        int cnt = 0;

        if(X == Y) return 0;

        if (distance % D == 0) {
            return distance / D;
        } else {
            return distance / D + 1;
        }
    }
}
