package edu.neu.coe.info6205.union_find;

import java.util.Random;
import java.util.Scanner;

public class HWQUPC_Solution {
    public static int count(int n) {
        UF_HWQUPC uf = new UF_HWQUPC(n, true);
        Random rd = new Random();

        int cnt = 0;
        while(uf.components() > 1) {
            int a = rd.nextInt(n), b = rd.nextInt(n);
            if(uf.connected(a, b)) continue;
            uf.union(a, b);
            cnt++;
        }
        return cnt;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Input Number n: ");
        int n = input.nextInt();
        while(n >= 1) {
            System.out.println("n = " + n + ", Number of connections generated: " + count(n));
            n--;
        }
    }
}
