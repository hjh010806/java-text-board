package example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Article {
    private int id;
    private String title;
    private String body;
    private int view;
    private LocalDateTime time;
    private List<Comment> comments;
    private int recommend;
    private int commentIdCounter = 1;

    public Article() {
    }

    public Article(int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.time = LocalDateTime.now();
        this.view = 0;
        this.comments = new ArrayList<>();
        this.recommend = 0;
    }

    public int getCommentId1() {
        return commentIdCounter;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public int getViews() {
        return view;
    }

    public void setViews(int views) {
        this.view = views;
    }
    public void increaseCommentId(int latestArticleComment) {
        this.commentIdCounter = latestArticleComment;
    }

    public void increaseView() {
        this.view++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFormattedCreationTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH:mm:ss");
        return time.format(formatter);
    }

    public void increaseRecommend() {
        recommend++;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Comment> getComments() {
        return comments;
    }

    void addComment(String commentContent) {
        Comment newComment = new Comment(commentContent);
        comments.add(newComment);
    }

    public void updateComment(int commentId, String newCommentContent) {
        for (Comment comment : comments) {
            if (comment.getCommentId() == commentId) {
                comment.setContent(newCommentContent);
                comment.setTime(LocalDateTime.now());
                System.out.printf("%d번 댓글이 수정되었습니다.\n", commentId);
                break;
            }
        }
    }

    public List<String> getCommentContents() {
        List<String> commentContents = new ArrayList<>();
        for (Comment comment : comments) {
            String commentTime = comment.getTime().format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH:mm:ss"));
            String commentTimeContents = String.format("%s. 댓글내용 : %s\n작성 시간 : %s", comment.getCommentId(), comment.getContent(), commentTime);
            commentContents.add(commentTimeContents);
        }
        return commentContents;
    }

    public class Comment {
        private String content;
        private LocalDateTime time;
        private int commentId;

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public LocalDateTime getTime() {
            return time;
        }

        public void setTime(LocalDateTime time) {
            this.time = time;
        }

        public Comment(String content) {
            this.content = content;
            this.time = LocalDateTime.now();
            this.commentId = getNewCommentId();
        }
        private int getNewCommentId() {
            return commentIdCounter++;
        }

    }
    public class Login{
        public void Login(String loginID, String password, String name){

        }

    }
}