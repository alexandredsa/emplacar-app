package br.com.fatecpg.emplacar.domain.entity;

import com.google.gson.Gson;
import com.orm.SugarRecord;

import br.com.fatecpg.emplacar.domain.vo.ExamData;

/**
 * Created by alexandre on 03/11/16.
 */

public class Exam extends SugarRecord {
    String title;
    int thumb;
    int questionCount;
    float bestScore;
    String jsonExamData;
    boolean isNew;


    public Exam() {
    }

    public Exam(ExamData examData) {
        this.title = examData.getTitle();
        this.thumb = examData.getThumb();
        this.questionCount = examData.getQuestions().size();
        this.jsonExamData = new Gson().toJson(examData);
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public void setBestScore(float bestScore) {
        this.bestScore = bestScore;
    }

    public String getTitle() {
        return title;
    }

    public int getThumb() {
        return thumb;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public float getBestScore() {
        return bestScore;
    }

    public String getJsonExamData() {
        Gson gson = new Gson();
        ExamData examData = gson.fromJson(jsonExamData, ExamData.class);
        examData.setId(this.getId());
        return gson.toJson(examData);
    }
}
