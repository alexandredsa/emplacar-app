package br.com.fatecpg.emplacar.view.stage;

import java.util.List;

import br.com.fatecpg.emplacar.domain.entity.Reward;
import br.com.fatecpg.emplacar.domain.vo.ExamData;

/**
 * Created by alexandre on 03/11/16.
 */
public final class StageBuilder {
    private String className;
    private List<Reward> rewardList;
    private ExamData examData;

    private StageBuilder() {
    }

    public static StageBuilder aStage() {
        return new StageBuilder();
    }

    public StageBuilder className(String className) {
        this.className = className;
        return this;
    }

    public StageBuilder rewardList(List<Reward> rewardList) {
        this.rewardList = rewardList;
        return this;
    }

    public StageBuilder examData(ExamData examData) {
        this.examData = examData;
        return this;
    }

    public Stage build() {
        Stage stage = new Stage();
        stage.setClassName(className);
        stage.setRewardList(rewardList);
        stage.setExamData(examData);
        return stage;
    }
}
