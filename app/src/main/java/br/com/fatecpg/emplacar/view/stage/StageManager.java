package br.com.fatecpg.emplacar.view.stage;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;

import br.com.fatecpg.emplacar.domain.Reward;
import br.com.fatecpg.emplacar.view.activities.levels.ChooseVehicleActivity;
import br.com.fatecpg.emplacar.view.activities.levels.LessonRegulationActivity;
import br.com.fatecpg.emplacar.view.activities.levels.ViasMapsActivity;
import br.com.fatecpg.emplacar.view.utils.ReflectionUtils;

public class StageManager {
    private static final String TAG = "StageManager";
    private ArrayList<Stage> allStages = new ArrayList<>();

    public StageManager() {
        allStages.add(new Stage(ChooseVehicleActivity.class.getName()));
        allStages.add(new Stage(LessonRegulationActivity.class.getName()));
        allStages.add(new Stage(ViasMapsActivity.class.getName()));
    }

    public Intent getNext(Context mContext, StageHolder stageHolder) {
        int nextIndex = stageHolder.getStageCount();
        Intent i = null;
        try {
            Stage stage = allStages.get(nextIndex);

            if (nextIndex > 0)
                updateRewards(allStages.get(nextIndex - 1));

            i = createIntent(ReflectionUtils.getClass(stage.getClassName()), mContext);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            Log.e(TAG, "Course completed");
            return null;
        }
        return i;
    }

    private void updateRewards(Stage stage) {
        for (Reward reward : stage.getRewardList()) {
            reward.setNew(true);
            reward.save();
        }
    }

    private Intent createIntent(Class next, Context mContext) {
        return new Intent(mContext, next);
    }


}
