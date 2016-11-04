package br.com.fatecpg.emplacar.domain.builder;

import java.util.List;

import br.com.fatecpg.emplacar.domain.vo.ExamData;
import br.com.fatecpg.emplacar.domain.vo.Question;

/**
 * Created by alexandre on 03/11/16.
 */
public final class ExamDataBuilder {
    private String title;
    private int thumb;
    private List<Question> questions;

    private ExamDataBuilder() {
    }

    public static ExamDataBuilder anExamData() {
        return new ExamDataBuilder();
    }

    public ExamDataBuilder title(String title) {
        this.title = title;
        return this;
    }

    public ExamDataBuilder thumb(int thumb) {
        this.thumb = thumb;
        return this;
    }

    public ExamDataBuilder questions(List<Question> questions) {
        this.questions = questions;
        return this;
    }

    public ExamData build() {
        ExamData examData = new ExamData();
        examData.setTitle(title);
        examData.setThumb(thumb);
        examData.setQuestions(questions);
        return examData;
    }
}
