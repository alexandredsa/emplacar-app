package br.com.fatecpg.emplacar.view.activities.app.levels;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TableRow;
import android.widget.TextView;

import br.com.fatecpg.emplacar.R;
import br.com.fatecpg.emplacar.domain.entity.Exam;
import br.com.fatecpg.emplacar.domain.entity.Reward;
import br.com.fatecpg.emplacar.view.activities.cards.CardActivity;
import br.com.fatecpg.emplacar.view.activities.exam.ExamActivity;
import br.com.fatecpg.emplacar.view.stage.StageHolder;
import br.com.fatecpg.emplacar.view.stage.StageManager;
import br.com.fatecpg.emplacar.view.user.preferences.StagePreferences;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static br.com.fatecpg.emplacar.R.id.countNewCards;

/**
 * Created by alexa on 11/09/2016.
 */
public class MenuActivity extends Activity {
    private static final int LIST_CARDS_REQ_CODE = 555;
    private static final int LIST_EXAM_REQ_CODE = 743;
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
    @BindView(countNewCards)
    TextView countCards;

    @BindView(R.id.notificationNewExams)
    View notificationNewExams;
    @BindView(R.id.countNewExams)
    TextView countExams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
        Intent i = new Intent(MenuActivity.this, ExamActivity.class);
        startActivityForResult(i, LIST_EXAM_REQ_CODE);
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
        checkExams();
    }

    private void checkRewards() {
        int countNewCards = countNew();

        if (countNewCards > 0) {
            notificationNewCards.setVisibility(View.VISIBLE);
            countCards.setText(String.valueOf(countNewCards));
        } else
            notificationNewCards.setVisibility(View.GONE);
    }

    private void checkExams() {
        int examsCount = countNewExams();

        if (examsCount > 0) {
            notificationNewExams.setVisibility(View.VISIBLE);
            countExams.setText(String.valueOf(countNewCards));
        } else
            notificationNewExams.setVisibility(View.GONE);
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

        if (Exam.count(Exam.class) > 0)
            rowExams.setVisibility(visibility);
        else
            rowExams.setVisibility(View.VISIBLE);

        if (Reward.count(Reward.class) > 0)
            rowCards.setVisibility(visibility);
        else
            rowCards.setVisibility(View.GONE);
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

        stageManager.updateRewards(stageHolder.getStageCount());
        stageHolder.increment();
        stagePreferences.setStageHolder(stageHolder);
        setUpMenu(getMenuMode(stageHolder.getStageCount()));
    }

    private void callNextActivity() {
        Intent i = stageManager.getNext(this, stageHolder);
        startActivityForResult(i, 123);
    }

    private int countNew() {
        return (int) Reward.count(Reward.class, String.format("is_new = 1"), null);
    }

    private int countNewExams() {
        return (int) Exam.count(Exam.class, String.format("is_new = 1"), null);
    }

    private enum MenuMode {
        START, FIRST_LESSON, REGULAR
    }
}
