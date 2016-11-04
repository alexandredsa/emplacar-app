package br.com.fatecpg.emplacar.view.stage;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.fatecpg.emplacar.R;
import br.com.fatecpg.emplacar.cards.CardType;
import br.com.fatecpg.emplacar.domain.builder.ExamDataBuilder;
import br.com.fatecpg.emplacar.domain.builder.QuestionBuilder;
import br.com.fatecpg.emplacar.domain.builder.RewardBuilder;
import br.com.fatecpg.emplacar.domain.entity.Reward;
import br.com.fatecpg.emplacar.domain.vo.Alternative;
import br.com.fatecpg.emplacar.view.activities.levels.ChooseVehicleActivity;
import br.com.fatecpg.emplacar.view.activities.levels.LessonRegulationActivity;
import br.com.fatecpg.emplacar.view.activities.levels.ViasMapsActivity;
import br.com.fatecpg.emplacar.view.utils.ReflectionUtils;

public class StageManager {
    private static final String TAG = "StageManager";
    private ArrayList<Stage> allStages = new ArrayList<>();

    public StageManager() {
        allStages.addAll(fillStages());
    }


    public Intent getNext(Context mContext, StageHolder stageHolder) {
        int nextIndex = stageHolder.getStageCount();
        Intent i = null;
        try {
            Stage stage = allStages.get(nextIndex);
            i = createIntent(ReflectionUtils.getClass(stage.getClassName()), mContext);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            Log.e(TAG, "Course completed");
            return null;
        }
        return i;
    }

    public void updateRewards(int index) {
        if (index < 0)
            return;

        Stage stage = allStages.get(index);
        for (Reward reward : stage.getRewardList()) {
            reward.setNew(true);
            reward.save();
        }
    }

    private Intent createIntent(Class next, Context mContext) {
        return new Intent(mContext, next);
    }

    public List<Stage> fillStages() {
        Stage chooseVehicle = StageBuilder.aStage()
                .className(ChooseVehicleActivity.class.getName())
                .rewardList(Arrays.asList(
                        RewardBuilder.aReward()
                                .imgResource(R.drawable.ic_ticket_01)
                                .cardType(CardType.TRAFFIC_TICKET)
                                .build()
                ))
                .build();


        Stage lessonRegulation = StageBuilder.aStage()
                .className(LessonRegulationActivity.class.getName())
                .rewardList(Arrays.asList(
                        RewardBuilder
                                .aReward()
                                .imgResource(R.drawable.ic_ticket_02)
                                .cardType(CardType.TRAFFIC_TICKET)
                                .build(),
                        RewardBuilder
                                .aReward()
                                .imgResource(R.drawable.ic_ticket_03)
                                .cardType(CardType.TRAFFIC_TICKET)
                                .build()
                ))
                .examData(
                        ExamDataBuilder
                                .anExamData()
                                .title("Legislação - Nível I")
                                .questions(Arrays.asList(
                                        QuestionBuilder
                                                .aQuestion()
                                                .text("Primeira Questão")
                                                .alternatives(Arrays.asList(
                                                        new Alternative("Errada I", false),
                                                        new Alternative("Errada II", false),
                                                        new Alternative("Errada III", false),
                                                        new Alternative("Certa", true))).build(),
                                        QuestionBuilder
                                                .aQuestion()
                                                .text("Segunda Questão")
                                                .alternatives(Arrays.asList(
                                                        new Alternative("Errada I", false),
                                                        new Alternative("Errada II", false),
                                                        new Alternative("Errada III", false),
                                                        new Alternative("Certa", true))).build()))
                                .build()).build();


        Stage vias = new Stage(ViasMapsActivity.class.getName());

        return Arrays.asList(chooseVehicle, lessonRegulation, vias);
    }
}
