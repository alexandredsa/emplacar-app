package br.com.fatecpg.emplacar.domain.vo;

import java.util.HashMap;

/**
 * Created by alexandre on 05/11/16.
 */
public class TestFeedback {
    private final int questionCount;
    private HashMap<Question, Alternative> answered;

    public TestFeedback(int questionCount) {
        answered = new HashMap<>();
        this.questionCount = questionCount;
    }

    public void add(Question question, Alternative alternative) {
        answered.put(question, alternative);
    }

    public float generateResult() {
        float result = (int) ((float)correctCount()/questionCount * 100);
        return result;
    }

    public int correctCount() {
        int result = 0;

        for (Alternative alternative : answered.values()) {
            if (alternative.isCorrect())
                ++result;
        }
        return result;
    }
}
