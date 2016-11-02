package br.com.fatecpg.emplacar.view.activities.app.levels;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import br.com.fatecpg.emplacar.view.stage.StageHolder;
import br.com.fatecpg.emplacar.view.stage.StageManager;
import br.com.fatecpg.emplacar.view.user.preferences.StagePreferences;
@Deprecated
public class MainActivity extends Activity {

    private StagePreferences stagePreferences;
    private StageHolder stageHolder;
    private StageManager stageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stageManager = new StageManager();
        stagePreferences = StagePreferences.getInstance(this);
        stageHolder = stagePreferences.getStageHolder();
        callNextActivity();
    }

    private void callNextActivity() {
        Intent i = stageManager.getNext(this, stageHolder);
        startActivityForResult(i, 123);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            callNextActivity();
            return;
        }

        stageHolder.increment();
        stagePreferences.setStageHolder(stageHolder);
        callNextActivity();
    }
}
