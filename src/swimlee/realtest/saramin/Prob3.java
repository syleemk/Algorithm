package swimlee.realtest.saramin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Prob3 {

    static final int MOD = 20090711;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();

        ArrayList<Integer> list = new ArrayList<>(200000);
        ArrayList<Integer> median = new ArrayList<>(200000);

        for (int i = 0; i < len; i++) {
            if(i==0) list.add(1983);
            else {
                list.add((list.get(i - 1) * (a + b)) % MOD);
            }

            Collections.sort(list);
            median.add(list.get((list.size() - 1) / 2));

            /*System.out.println("list" + i);
            for (int k = 0; k < list.size(); k++) {
                System.out.print(list.get(k)+ " ");
            }
            System.out.println();

            System.out.println("median" + i);
            for (int k = 0; k < median.size(); k++) {
                System.out.print(median.get(k)+ " ");
            }
            System.out.println();*/
        }

        /*System.out.println();
        for (int i = 0; i < median.size(); i++) {
            System.out.print(list.get(i)+ " ");
        }
        System.out.println();

        System.out.println();
        for (int i = 0; i < median.size(); i++) {
            System.out.print(median.get(i)+ " ");
        }
        System.out.println();*/

        int sum = 0;
        for (int i = 0; i < median.size(); i++) {
            sum = (sum + median.get(i)) % MOD;
//            System.out.println("sum = " + sum + " ,median.get(i) = " + median.get(i));
        }

        System.out.println(sum);
    }
}
