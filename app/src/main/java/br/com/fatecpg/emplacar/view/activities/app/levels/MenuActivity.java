package br.com.fatecpg.emplacar.view.activities.app.levels;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;

import br.com.fatecpg.emplacar.R;
import br.com.fatecpg.emplacar.view.stage.StageHolder;
import br.com.fatecpg.emplacar.view.stage.StageManager;
import br.com.fatecpg.emplacar.view.user.preferences.StagePreferences;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexa on 11/09/2016.
 */
public class MenuActivity extends Activity {
    private StagePreferences stagePreferences;
    private StageHolder stageHolder;
    private StageManager stageManager;

    @BindView(R.id.rowStart)
    TableRow rowStart;
    @BindView(R.id.rowNextLesson)
    TableRow rowNextLesson;
    @BindView(R.id.rowLessonLearned)
    TableRow rowLessonLearned;
    @BindView(R.id.rowSign)
    TableRow rowSign;
    @BindView(R.id.rowTrafficTicket)
    TableRow rowTrafficTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        ButterKnife.bind(this);
        stageManager = new StageManager();
        stagePreferences = StagePreferences.getInstance(this);
        stageHolder = stagePreferences.getStageHolder();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setUpMenu(getMenuMode(stageHolder.getStageCount()));
    }

    private void setUpMenu(MenuMode menuMode) {
        switch (menuMode){
            case START:
                onlyStartEnable();
                break;
            case FIRST_LESSON:
                onlyNextLessonEnable();
            default:
                onlyStartDisable();
        }
    }

    private void onlyStartDisable() {
        changeRowsVisibility(View.VISIBLE);
        rowStart.setVisibility(View.INVISIBLE);
    }

    private void onlyNextLessonEnable() {
        changeRowsVisibility(View.INVISIBLE);
        rowNextLesson.setVisibility(View.VISIBLE);
    }

    private void onlyStartEnable() {
        changeRowsVisibility(View.INVISIBLE);
        rowStart.setVisibility(View.VISIBLE);
    }

    private void changeRowsVisibility(int visibility) {
        rowStart.setVisibility(visibility);
        rowNextLesson.setVisibility(visibility);
        rowLessonLearned.setVisibility(visibility);
        rowSign.setVisibility(visibility);
        rowTrafficTicket.setVisibility(visibility);
    }

    private MenuMode getMenuMode(int stageCount) {
        switch (stageCount){
            case 0:
                return MenuMode.START;
            case 1:
                return MenuMode.FIRST_LESSON;
            default:
                return MenuMode.REGULAR;
        }
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


    private void callNextActivity() {
        Intent i = stageManager.getNext(this, stageHolder);
        startActivityForResult(i, 123);
    }

    private enum MenuMode{
        START, FIRST_LESSON, REGULAR
    }
}
