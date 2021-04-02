package swimlee.programmers.Kakao2020Intern;

/**
 * 구현 문제 좋음 (level 1 인데도 카카오는 카카오다)
 */

public class PressKeyPad {

    public static void main(String[] args) {
        int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        String hand = "right";
        PressKeyPad pressKeyPad = new PressKeyPad();
        String solution = pressKeyPad.solution(numbers, hand);
        System.out.println("solution = " + solution);
    }

    public String solution(int[] numbers, String hand) {

        int leftHand = 10; // *을 10으로 치환
        int rightHand = 12; // #을 12로 치환

        StringBuilder sb = new StringBuilder();

        for (int num : numbers) {
            if (num == 1 || num == 4 || num == 7) {// left hand
                sb.append("L");
                leftHand = num;
            } else if (num == 3 || num == 6 || num == 9) {// right hand
                sb.append("R");
                rightHand = num;
            } else {// center
                int leftDistance = getDistance(leftHand, num);
                int rightDistance = getDistance(rightHand, num);

                if (leftDistance < rightDistance) {
                    sb.append("L");
                    leftHand = num;
                } else if (leftDistance > rightDistance) {
                    sb.append("R");
                    rightHand = num;
                } else {
                    if (hand.equals("left")) {
                        sb.append("L");
                        leftHand = num;
                    } else {
                        sb.append("R");
                        rightHand = num;
                    }
                }
            }
        }

        return sb.toString();
    }

    private int getDistance(int index, int number) {
        index = index == 0 ? 11 : index;
        number = number == 0 ? 11 : number;

        int index_x = (index - 1) % 3;
        int index_y = (index - 1) / 3;

        int number_x = (number - 1) % 3;
        int number_y = (number - 1) / 3;

        return Math.abs(index_x - number_x) + Math.abs(index_y - number_y);
    }
}
