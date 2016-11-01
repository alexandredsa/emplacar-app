package br.com.fatecpg.emplacar.view.stage;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;

import br.com.fatecpg.emplacar.view.activities.levels.ChooseVehicleActivity;
import br.com.fatecpg.emplacar.view.activities.levels.LessonRegulationActivity;
import br.com.fatecpg.emplacar.view.activities.levels.ViasMapsActivity;
import br.com.fatecpg.emplacar.view.utils.ReflectionUtils;

public class StageManager {
    private static final String TAG = "StageManager";
    private ArrayList<String> allStages = new ArrayList<>();

    public StageManager() {
        allStages.add(ChooseVehicleActivity.class.getName());
        allStages.add(LessonRegulationActivity.class.getName());
        allStages.add(ViasMapsActivity.class.getName());
    }

    public ArrayList<String> getAllStages() {
        return allStages;
    }

    public Intent getNext(Context mContext, StageHolder stageHolder) {
        int nextIndex = stageHolder.getStageCount();
        Intent i = null;
        try {
            i = createIntent(ReflectionUtils.getClass(allStages.get(nextIndex)), mContext);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            Log.e(TAG, "Course completed");
            return null;
        }
        return i;
    }

    private Intent createIntent(Class next, Context mContext) {
        return new Intent(mContext, next);
    }


}
