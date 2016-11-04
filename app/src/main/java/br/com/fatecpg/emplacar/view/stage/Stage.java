package br.com.fatecpg.emplacar.view.stage;

import java.util.ArrayList;
import java.util.List;

import br.com.fatecpg.emplacar.domain.entity.Reward;
import br.com.fatecpg.emplacar.domain.vo.ExamData;

/**
 * Created by alexandre on 02/11/16.
 */

public class Stage {
    private String className;
    private List<Reward> rewardList;
    private ExamData examData;

    public Stage(String className) {
        this.className = className;
    }


    public Stage(){}

    public Stage(String className, List<Reward> rewardList) {
        this.className = className;
        this.rewardList = rewardList;
    }

    public ExamData getExamData() {
        return examData;
    }

    public void setExamData(ExamData examData) {
        this.examData = examData;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Reward> getRewardList() {
        if (rewardList == null)
            return new ArrayList<>();

        return rewardList;
    }

    public void setRewardList(List<Reward> rewardList) {
        this.rewardList = rewardList;
    }
}
