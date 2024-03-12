package example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Article {
    private int id; // 번호
    private String title; // 제목
    private String body; // 내용
    private int view;
    private LocalDateTime time;



    public Article(){

    }

    public Article(int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.time = LocalDateTime.now();
        this.view = 1;
    }

    public int getViews() {
        return view;
    }

    public void setViews(int views) {
        this.view = view;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return time.format(formatter);
    }
    public void views(){
        view++;
    }

}


