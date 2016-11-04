package br.com.fatecpg.emplacar.domain.builder;

import java.util.List;

import br.com.fatecpg.emplacar.domain.vo.Alternative;
import br.com.fatecpg.emplacar.domain.vo.Question;

/**
 * Created by alexandre on 03/11/16.
 */
public final class QuestionBuilder {
    private String text;
    private List<Alternative> alternatives;

    private QuestionBuilder() {
    }

    public static QuestionBuilder aQuestion() {
        return new QuestionBuilder();
    }

    public QuestionBuilder text(String text) {
        this.text = text;
        return this;
    }

    public QuestionBuilder alternatives(List<Alternative> alternatives) {
        this.alternatives = alternatives;
        return this;
    }

    public Question build() {
        Question question = new Question();
        question.setText(text);
        question.setAlternatives(alternatives);
        return question;
    }
}
