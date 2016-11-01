package br.com.fatecpg.emplacar.view.stage;

import com.google.gson.Gson;

public class StageHolder {
    private int stageCount = 0;


    public int getStageCount() {
        return stageCount;
    }

    public void increment(){
        ++stageCount;
    }

    public void setStageCount(int stageCount) {
        this.stageCount = stageCount;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
