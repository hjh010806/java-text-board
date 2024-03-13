package example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.sun.tools.attach.VirtualMachine.list;

public class BoardApp {
    ArrayList<Article> articleList = new ArrayList<>(); // 인스턴스 변수
    Scanner scan = new Scanner(System.in);
    int latestArticleId = 1; // 시작 번호를 1로 지정
    int latestArticleComment = 1;

    public void run() {
        while (true) { // 반복 조건이 true이기 때문에 무한 반복
            System.out.print("명령어 : ");
            String cmd = scan.nextLine();
            if (cmd.equals("exit")) { // 숫자가 아닌 경우 같다라는 표현을 할 때 == 이 아닌 .equals()를 사용해야 한다.
                System.out.println("프로그램을 종료합니다.");
                break; // 반복문 탈출
            } else if (cmd.equals("add")) {
                add();

            } else if (cmd.equals("test")) {
                test();

            } else if (cmd.equals("list")) {
                list();
            } else if (cmd.equals("update")) {
                update();

            } else if (cmd.equals("delete")) {
                delete();

            } else if (cmd.equals("detail")) {
                detail();
            } else if (cmd.equals("search")) {
                serch();
            }
        }
    }

    private void serch() {
        System.out.print("검색 키워드를 입력해주세요 : ");
        String target = scan.nextLine();
        boolean found = false;

        System.out.println("===================");
        for (Article article : articleList) {
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

    private void detail() {
        System.out.print("상세보기할 게시물 번호를 입력해주세요 : ");
        int inputId = Integer.parseInt(scan.nextLine());

        int index = findIndexById(inputId);
        if (index == -1) {
            System.out.println("없는 게시물입니다.");
        } else {
            Article article = articleList.get(index);
            List<Article.Comment> comments = article.getComments();

            // 조회수 증가
            article.increaseView();

            System.out.println("===================");
            System.out.println("번호 : " + article.getId());
            System.out.printf("제목 : %s\n", article.getTitle());
            System.out.printf("내용 : %s\n", article.getBody());
            System.out.printf("등록날짜 : %s\n", article.getFormattedCreationTime());
            System.out.printf("조회수 : %s\n", article.getViews());
            System.out.printf("추천수 : %s\n", article.getRecommend());
            System.out.println("=======댓글========");
            System.out.printf(comments.isEmpty() ? "댓글이 없습니다.\n" : "%s\n", String.join("\n", article.getCommentContents()));
            System.out.println("===================");

            System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 추천, 3. 수정, 4. 삭제, 5. 목록으로) : ");
            String cmd2 = scan.nextLine();
            switch (cmd2) {
                case "1":
                    System.out.print("댓글 내용: ");
                    String commentContent = scan.nextLine();
                    article.addComment(commentContent);
                    System.out.println("댓글이 성공적으로 등록되었습니다.");
                    latestArticleComment++;
                    break;
                case "2":
                    System.out.println("현재 게시물을 추천 하였습니다");
                    article.increaseRecommend();
                    break;
                case "3":
                    System.out.print("수정할 댓글 번호를 입력해주세요 : ");
                    int inputComment = Integer.parseInt(scan.nextLine());

                    int commentIndex = findIndexByCommentId(inputComment);
                    if (commentIndex == -1) {
                        System.out.println("없는 게시물입니다.");
                    }

                    System.out.print("새로운 댓글을 입력해주세요 : ");
                    String newComment = scan.nextLine();

                    articleList.get(index).updateComment(inputComment, newComment);
                    break;  // 추가된 부분
                case "4":
                    System.out.print("삭제할 댓글 번호를 입력해주세요 : ");
                    inputComment = Integer.parseInt(scan.nextLine());

                    commentIndex = findIndexById(inputComment);

                    if (commentIndex != -1) {
                        article.getComments().remove(commentIndex);
                        System.out.printf("%d 댓글이 삭제되었습니다.\n", inputComment);
                    } else {
                        System.out.println("없는 댓글 번호입니다.");
                    }
                    break;

                case "5":
                    System.out.println("목록으로 돌아갑니다.");
                    break;
                // 나머지 case문들은 그대로 유지
            }
        }
    }

    private void delete() {
        System.out.print("삭제할 게시물 번호를 입력해주세요 : ");
        int inputId = Integer.parseInt(scan.nextLine());

        int index = findIndexById(inputId);

        if (index == -1) {
            System.out.println("없는 게시물입니다.");
        }

        articleList.remove(index);
        System.out.printf("%d 게시물이 삭제되었습니다.\n", inputId);
    }

    private void update() {
        System.out.print("수정할 게시물 번호를 입력해주세요 : ");
        int inputId = Integer.parseInt(scan.nextLine());

        int index = findIndexById(inputId);
        if (index == -1) {
            System.out.println("없는 게시물입니다.");
        }

        System.out.print("새로운 제목을 입력해주세요 : ");
        String newTitle = scan.nextLine();
        System.out.print("새로운 내용을 입력해주세요 : ");
        String newBody = scan.nextLine();

        Article target = articleList.get(index);
        target.setTitle(newTitle);
        target.setBody(newBody);
        System.out.printf("%d번 게시물이 수정되었습니다.\n", inputId);
    }

    private void add() {
        System.out.print("게시물 제목을 입력해주세요 : ");
        String title = scan.nextLine();
        System.out.print("게시물 내용을 입력해주세요 : ");
        String body = scan.nextLine();

        Article article = new Article(latestArticleId, title, body);
        articleList.add(article);
        System.out.println("게시물이 등록되었습니다.");
        latestArticleId++; // 게시물이 생성될 때마다 번호를 증가
    }

    private void test() {
        articleList.add(new Article(1, "안녕하세요 반갑습니다. 자바 공부중이에요.", " "));
        articleList.add(new Article(2, "자바 질문좀 할게요~", " "));
        articleList.add(new Article(3, "정처기 따야되나요?", " "));

        System.out.println("세팅 완료");

    }

    private void list() {
        System.out.println("===================");
        for (int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);
            System.out.println("번호 : " + article.getId());
            System.out.printf("제목 : %s\n", article.getTitle());
            System.out.println("===================");
        }
    }

    private void signup() {
        System.out.print("아이디를 입력해주세요 : ");
        String loginId = scan.nextLine();
        System.out.print("비밀번호을 입력해주세요 : ");
        String password = scan.nextLine();
        System.out.print("닉네임을 입력해주세요 : ");
        String name = scan.nextLine();

        Article login = new Article();
        articleList.add(login);
        System.out.println("게시물이 등록되었습니다.");
         // 게시물이 생성될 때마다 번호를 증가
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

    public int findIndexByCommentId(int commentId) {
        for (int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);

            for (Article.Comment comment : article.getComments()) {
                if (comment.getCommentId() == commentId) {
                    return i; // 원하는 것은 찾은 즉시 종료.
                }
            }
        }

        return -1;
    }
}
