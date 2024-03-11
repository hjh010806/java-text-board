package example;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);


        // 반복 횟수 정할 수 없음 => 무한 반복 구조

//        while (true) { // 반복 조건이 true이기 때문에 무한 반복
//            System.out.println("명령어 : ");
//            String cmd = scan.nextLine();
//
//            if(cmd.equals("exit")){ // 숫자가 아닌 경우 같다라는 표현을 할 때 ==가 아닌  .equals()를 사용
//                break;
//            }
//
//        }
//
//        // 1. 반복문 제어 하던 방법 : 반복 횟수 세서 특정 횟수 지나면 탈출
//        // 2. break문을 사용하여 강제 탈출 가능
//
//        for (int i = 1; i <= 10; i++) {
//            if (i == 5) {
//                break;
//            }
//            System.out.println("탈출");
//        }

//        - 명령어 : add
//                - 설명 : 게시물은 제목, 내용으로 이루어져 있습니다. 제목과 내용을 입력받아 저장
//        - 입출력 예시
//  ```
//        명령어 : add
//        게시물 제목을 입력해주세요 : 제목1
//        게시물 내용을 입력해주세요 : 내용1
//        게시물이 등록되었습니다.
//        명령어 : add
//        게시물 제목을 입력해주세요 : 제목2
//        게시물 내용을 입력해주세요 : 내용2
//        게시물이 등록되었습니다.
//  ```
//        while (true) {
//            System.out.println("명령어 : ");
//            String cmd = scan.nextLine();
//            if(cmd.equals("add")){
//                System.out.println("게시물 제목을 입력해주세요 : ");
//                String title = scan.nextLine();
//                System.out.println("게시물 내용을 입력해주세요 : ");
//                String detail = scan.nextLine();
//            }
//            while(true){
//                add add1 = new add();
//
//
//        }
        // 여러개 저장하려면 배열을 써야한다.
        ArrayList<String> titleList = new ArrayList<>();
        ArrayList<String> bodyList = new ArrayList<>();
        ArrayList<Integer> numberList = new ArrayList<>();


        while (true) {
            System.out.println("명령어 : ");
            String cmd = scan.nextLine();
            if (cmd.equals("exit")) {
                System.out.println("종료합니다");
                break;
            } else if (cmd.equals("add")) {
                System.out.println("게시물 제목을 입력해주세요 : ");
                String title = scan.nextLine();
                titleList.add(title);

                System.out.println("게시물 내용을 입력해주세요 : ");
                String body = scan.nextLine();
                bodyList.add(body);

            } else if (cmd.equals("list")) {
                System.out.println("====================");
                for (Integer i = 0; i < titleList.size(); i++) {
                    int number = i + 1;
                    numberList.add(number);
                    System.out.printf("번호 : %d\n", number);

                    String title = titleList.get(i);
                    System.out.printf("제목 : %s\n", title);
                    System.out.println("====================");
                }
            } else if (cmd.equals("update")) {
                System.out.println("수정할 게시물 번호 : ");
                int number = Integer.parseInt(scan.nextLine());

                if (number > 0 && number <= titleList.size()) {
                    System.out.println("새로운 제목을 입력해주세요 : ");
                    String newTitle = scan.nextLine();
                    System.out.println("새로운 내용을 입력해주세요 : ");
                    String newbody = scan.nextLine();
                    titleList.set(number, newTitle);
                    bodyList.set(number, newbody);
                    System.out.println("게시물이 업데이트되었습니다.");
                } else {
                    System.out.println("없는 게시물 번호입니다.");
                }
            } else if(cmd.equals("delete")){
                System.out.println("삭제할 게시물 번호 : ");
                int number = Integer.parseInt(scan.nextLine());
                if (number > 0 && number <= titleList.size()){
                    titleList.remove(number - 1);
                    bodyList.remove(number - 1);
                    for (int i = 0; i < numberList.size(); i++) {
                        numberList.set(i, i + 1);
                    }
                }else {
                    System.out.println("없는 게시물 번호입니다.");
                }
            }


//                    String body = bodyList.get(i);
//                    System.out.printf("내용 : %s\n", body);
//                System.out.println("====================");
        }


    }
}

