package example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);


        // 반복 횟수 정할 수 없음 => 무한 반복 구조

        while (true) { // 반복 조건이 true이기 때문에 무한 반복
            System.out.println("명령어 : ");
            String cmd = scan.nextLine();
            if(cmd .equals("exit")){ // 숫자가 아닌 경우 같다라는 표현을 할 때 ==가 아닌  .equals()를 사용
                break;
            }
        }

        // 1. 반복문 제어 하던 방법 : 반복 횟수 세서 특정 횟수 지나면 탈출
        // 2. break문을 사용하여 강제 탈출 가능

        for (int i = 1; i <= 10; i++) {
            if (i == 5) {
                break;
            }
            System.out.println("탈출");
        }
    }
}
