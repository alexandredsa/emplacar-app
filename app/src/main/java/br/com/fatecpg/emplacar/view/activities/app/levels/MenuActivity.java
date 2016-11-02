package br.com.fatecpg.emplacar.view.activities.app.levels;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import br.com.fatecpg.emplacar.R;
import br.com.fatecpg.emplacar.domain.Reward;
import br.com.fatecpg.emplacar.view.activities.cards.CardActivity;
import br.com.fatecpg.emplacar.view.stage.StageHolder;
import br.com.fatecpg.emplacar.view.stage.StageManager;
import br.com.fatecpg.emplacar.view.user.preferences.StagePreferences;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by alexa on 11/09/2016.
 */
public class MenuActivity extends Activity {
    private static final int LIST_CARDS_REQ_CODE = 555;
    private StagePreferences stagePreferences;
    private StageHolder stageHolder;
    private StageManager stageManager;

    @BindView(R.id.rowStart)
    TableRow rowStart;
    @BindView(R.id.rowNextLesson)
    TableRow rowNextLesson;
    @BindView(R.id.rowExams)
    TableRow rowExams;
    @BindView(R.id.rowCards)
    TableRow rowCards;

    @BindView(R.id.notificationNewCards)
    View notificationNewCards;
    @BindView(R.id.countNewCards)
    TextView countCards;

    @BindView(R.id.notificationNewExams)
    View notificationNewExams;
    @BindView(R.id.countNewExams)
    TextView countExams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        ButterKnife.bind(this);
        stageManager = new StageManager();
        stagePreferences = StagePreferences.getInstance(this);
        stageHolder = stagePreferences.getStageHolder();

        //TODO
        rowExams.setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setUpMenu(getMenuMode(stageHolder.getStageCount()));
    }

    @OnClick(R.id.rowStart)
    public void start() {
        callNextActivity();
    }

    @OnClick(R.id.rowNextLesson)
    public void nextLesson() {
        callNextActivity();
    }

    @OnClick(R.id.rowExams)
    public void loadExams() {
        //TODO
    }



    @OnClick(R.id.rowCards)
    public void loadCards() {
        Intent i = new Intent(MenuActivity.this, CardActivity.class);
        startActivityForResult(i, LIST_CARDS_REQ_CODE);
    }

    private void setUpMenu(MenuMode menuMode) {
        switch (menuMode) {
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
        checkRewards();
    }

    private void checkRewards() {
        int countNewCards = countNew();

        if (countNewCards > 0) {
            notificationNewCards.setVisibility(View.VISIBLE);
            countCards.setText(String.valueOf(countNewCards));
        } else
            notificationNewCards.setVisibility(View.GONE);
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
//        rowExams.setVisibility(visibility);
        rowCards.setVisibility(visibility);
    }

    private MenuMode getMenuMode(int stageCount) {
        switch (stageCount) {
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

        if (resultCode != RESULT_OK)
            return;

        stageHolder.increment();
        stagePreferences.setStageHolder(stageHolder);
        setUpMenu(getMenuMode(stageHolder.getStageCount()));
    }

    private void callNextActivity() {
        Intent i = stageManager.getNext(this, stageHolder);
        startActivityForResult(i, 123);
    }

    private int countNew() {
        return (int) Reward.count(Reward.class, String.format("isNew = 1"), null);
    }

    private enum MenuMode {
        START, FIRST_LESSON, REGULAR
    }
}
