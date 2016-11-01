package br.com.fatecpg.emplacar.view.user.preferences;

import android.content.Context;

import br.com.fatecpg.emplacar.view.stage.StageHolder;

public class StagePreferences extends ApplicationPreferences {
    private static StagePreferences instance;
    private final String PREFS_KEY_STAGE = "key_stage_shared_preferences";

    public StagePreferences(Context mContext) {
        super(mContext);
    }


    public static StagePreferences getInstance(Context mContext) {
        if (instance == null)
            instance = new StagePreferences(mContext);

        return instance;
    }

    public StageHolder getStageHolder() {
        StageHolder holder = retrieveObject(PREFS_KEY_STAGE, StageHolder.class);
        return holder == null ? createNew() : holder;
    }

    private StageHolder createNew() {
        StageHolder stageHolder = new StageHolder();
        stageHolder.setStageCount(0);
        setStageHolder(stageHolder);
        return stageHolder;
    }

    public void setStageHolder(StageHolder holder) {
        storeObject(PREFS_KEY_STAGE, holder);
    }


}
