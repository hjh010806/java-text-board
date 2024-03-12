package example;

import java.nio.file.LinkPermission;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class BoardApp {
    ArrayList<Article> articleList = new ArrayList<>(); // 인스턴스 변수

    public void run() {
        Scanner scan = new Scanner(System.in);
        int latestArticleId = 1; // 시작 번호를 1로 지정
        while (true) { // 반복 조건이 true이기 때문에 무한 반복
            System.out.print("명령어 : ");
            String cmd = scan.nextLine();
            if (cmd.equals("exit")) { // 숫자가 아닌 경우 같다라는 표현을 할 때 == 이 아닌 .equals()를 사용해야 한다.
                System.out.println("프로그램을 종료합니다.");
                break; // 반복문 탈출
            } else if (cmd.equals("add")) {
                System.out.print("게시물 제목을 입력해주세요 : ");
                String title = scan.nextLine();
                System.out.print("게시물 내용을 입력해주세요 : ");
                String body = scan.nextLine();

                Article article = new Article(latestArticleId, title, body);
                articleList.add(article);
                System.out.println("게시물이 등록되었습니다.");
                latestArticleId++; // 게시물이 생성될 때마다 번호를 증가
            } else if (cmd.equals("test")) {

                articleList.add(new Article(1, "안녕하세요 반갑습니다. 자바 공부중이에요.", " "));
                articleList.add(new Article(2, "자바 질문좀 할게요~", " "));
                articleList.add(new Article(3, "정처기 따야되나요?", " "));


                System.out.println("세팅 완료");

            } else if (cmd.equals("list")) {
                System.out.println("===================");
                for (int i = 0; i < articleList.size(); i++) {
                    Article article = articleList.get(i);
                    System.out.println("번호 : " + article.getId());
                    System.out.printf("제목 : %s\n", article.getTitle());
                    System.out.println("===================");
                }
            } else if (cmd.equals("update")) {
                System.out.print("수정할 게시물 번호를 입력해주세요 : ");
                int inputId = Integer.parseInt(scan.nextLine());

                int index = findIndexById(inputId);
                if (index == -1) {
                    System.out.println("없는 게시물입니다.");
                    continue;
                }

                System.out.print("새로운 제목을 입력해주세요 : ");
                String newTitle = scan.nextLine();
                System.out.print("새로운 내용을 입력해주세요 : ");
                String newBody = scan.nextLine();
                // 변하지 않는 것에서 변하는 것을 분리
                // 변하는 것에서 변하지 않는 것을 분리

                Article target = articleList.get(index);
                target.setTitle(newTitle); // target은 참조값이므로 직접 객체를 접근하여 수정 가능
                target.setBody(newBody);
                System.out.printf("%d번 게시물이 수정되었습니다.\n", inputId);

            } else if (cmd.equals("delete")) {
                System.out.print("삭제할 게시물 번호를 입력해주세요 : ");
                int inputId = Integer.parseInt(scan.nextLine());

                int index = findIndexById(inputId);

                if (index == -1) {
                    System.out.println("없는 게시물입니다.");
                    continue;
                }

                articleList.remove(index);
                System.out.printf("%d 게시물이 삭제되었습니다.\n", inputId);

            } else if (cmd.equals("detail")) {
                System.out.print("상세보기할 게시물 번호를 입력해주세요 : ");
                int inputId = Integer.parseInt(scan.nextLine());

                int index = findIndexById(inputId);
                if (index == -1) {
                    System.out.println("없는 게시물입니다.");
                    continue;
                }
                System.out.println("===================");
                for (int i = 0; i < articleList.size(); i++) {
                    if (i == index) {
                        Article article = articleList.get(i);
                        System.out.println("번호 : " + article.getId());
                        System.out.printf("제목 : %s\n", article.getTitle());
                        System.out.printf("내용 : %s\n", article.getBody());
                        System.out.printf("등록날짜 : %s\n", article.getFormattedCreationTime());
                        System.out.printf("조회수 : %s\n", article.getViews());
                        System.out.println("===================");

                        article.views();
                    }
                }
                while (true) {
                    System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 추천, 3. 수정, 4. 삭제, 5. 목록으로) : ");
                    if (cmd.equals(1)) {

                    }
                }


            } else if (cmd.equals("search")) {
                System.out.print("검색 키워드를 입력해주세요 : ");
                String target = scan.nextLine();
                boolean found = false;

                System.out.println("===================");
                for(Article article : articleList){
                    if (article.getTitle().contains(target)) {
                        System.out.println("번호 : " + article.getId());
                        System.out.printf("제목 : %s\n", article.getTitle());
                        System.out.println("===================");
                        found = true;
                    }
                }
                if (!found) {

                    System.out.println("검색결과가 없습니다.");

                }
            }

        }
    }

    // 입력 : 찾고자 하는 게시물 번호
// 출력 : 게시물 번호에 해당하는 인덱스
    public int findIndexById(int id) {

        for (int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);

            if (article.getId() == id) {
                return i; // 원하는 것은 찾은 즉시 종료.
            }
        }

        return -1;
    }



}