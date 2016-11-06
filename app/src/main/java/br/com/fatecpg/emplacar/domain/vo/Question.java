package br.com.fatecpg.emplacar.domain.vo;

import java.util.List;

/**
 * Created by alexandre on 03/11/16.
 */
public class Question {
    private String text;
    private List<Alternative> alternatives;

    public Question(String text, List<Alternative> alternatives) {
        this.text = text;
        this.alternatives = alternatives;
    }

    public Question() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Alternative> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<Alternative> alternatives) {
        this.alternatives = alternatives;
    }

    public void setAsSingleSelected(Alternative selectedAlternative) {
        for(Alternative alternative : alternatives)
            alternative.setSelected(false);

        selectedAlternative.setSelected(true);
    }
}
