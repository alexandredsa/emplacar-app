package br.com.fatecpg.emplacar.domain.vo;

import java.util.List;

/**
 * Created by alexandre on 03/11/16.
 */

public class ExamData {
    private String title;
    private int thumb;
    private List<Question> questions;
    private long id;

    public ExamData(String title, int thumb, List<Question> questions) {
        this.title = title;
        this.thumb = thumb;
        this.questions = questions;
    }

    public ExamData() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getThumb() {
        return thumb;
    }

    public void setThumb(int thumb) {
        this.thumb = thumb;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
