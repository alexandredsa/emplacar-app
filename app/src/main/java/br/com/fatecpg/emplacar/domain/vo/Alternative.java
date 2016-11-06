package br.com.fatecpg.emplacar.domain.vo;

/**
 * Created by alexandre on 03/11/16.
 */
public class Alternative {
    private String text;
    private boolean isCorrect;
    private boolean isSelected;

    public Alternative() {
    }

    public Alternative(String text, boolean isCorrect) {
        this.text = text;
        this.isCorrect = isCorrect;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
