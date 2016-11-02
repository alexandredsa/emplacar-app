package br.com.fatecpg.emplacar.view.stage;

import java.util.ArrayList;
import java.util.List;

import br.com.fatecpg.emplacar.domain.Reward;

/**
 * Created by alexandre on 02/11/16.
 */

public class Stage {
    private String className;
    private List<Reward> rewardList;

    public Stage(String className) {
        this.className = className;
    }


    public Stage(String className, List<Reward> rewardList) {
        this.className = className;
        this.rewardList = rewardList;
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
