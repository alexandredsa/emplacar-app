package br.com.fatecpg.emplacar.view.activities.exam;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import at.grabner.circleprogress.CircleProgressView;
import br.com.fatecpg.emplacar.R;
import br.com.fatecpg.emplacar.domain.vo.Alternative;
import br.com.fatecpg.emplacar.domain.vo.ExamData;
import br.com.fatecpg.emplacar.domain.vo.Question;
import br.com.fatecpg.emplacar.domain.vo.TestFeedback;
import br.com.fatecpg.emplacar.view.utils.ColorRange;
import br.com.fatecpg.emplacar.view.utils.ColorRangeHolder;
import br.com.fatecpg.emplacar.view.utils.ExamEnumerationUtils;
import br.com.fatecpg.emplacar.view.utils.ViewUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by alexandre on 05/11/16.
 */

public class QuestionActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.questionProgress)
    TextView questionProgress;
    @BindView(R.id.text)
    TextView text;

    @BindView(R.id.lstQuestions)
    LinearLayout lstQuestions;


    @BindView(R.id.questionLayout)
    LinearLayout questionLayout;

    @BindView(R.id.summaryExamLayout)
    RelativeLayout summaryExamLayout;

    @BindView(R.id.scrollLayout)
    ScrollView scrollLayout;


    @BindView(R.id.btnPrev)
    Button btnPrev;
    @BindView(R.id.btnNext)
    Button btnNext;
    @BindView(R.id.btnFinish)
    Button btnFinish;

    @BindView(R.id.circleView)
    CircleProgressView circleView;

    @BindView(R.id.btnExit)
    Button btnExit;


    public static final String EXAM_JSON_KEY = "exam_json_intent_key";
    private ExamData examData;
    private TestFeedback testFeedback;
    /**
     * Corresponds to index in array. [0 to N]
     */
    private int questionIndex;
    private ColorRangeHolder barColorRange;
    private ColorRangeHolder rimColorRange;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_question_exam);
        ButterKnife.bind(this);
        initColorRanges();
        loadExam();
        circleView.setSeekModeEnabled(false);
        questionIndex = 0;
        setUpQuestionUI(examData.getQuestions().get(questionIndex));

    }

    private void loadExam() {
        Intent i = getIntent();
        String examJson = i.getStringExtra(EXAM_JSON_KEY);
        examData = new Gson().fromJson(examJson, ExamData.class);
        testFeedback = new TestFeedback(examData.getQuestions().size());
        // Setting up toolbar...
        title.setText(examData.getTitle());
    }

    private void setUpQuestionUI(Question currentQuestion) {
        List<Question> questions = examData.getQuestions();
        text.setText(currentQuestion.getText());
        questionProgress.setText(String.format("%s/%s", String.valueOf(questionIndex + 1),
                String.valueOf(questions.size())));

        loadAlternatives(currentQuestion);
        setUpButtons();
    }

    private void setUpButtons() {

        if (questionIndex == 0)
            btnPrev.setVisibility(View.INVISIBLE);
        else
            btnPrev.setVisibility(View.VISIBLE);

        if (questionIndex == examData.getQuestions().size() - 1) {
            btnFinish.setVisibility(View.VISIBLE);
            btnNext.setVisibility(View.GONE);
        } else {
            btnFinish.setVisibility(View.GONE);
            btnNext.setVisibility(View.VISIBLE);
        }
    }

    private void loadAlternatives(Question currentQuestion) {
        lstQuestions.removeAllViews();
        for (Alternative alternative : currentQuestion.getAlternatives()) {
            int alternativeIndex = currentQuestion.getAlternatives().indexOf(alternative);
            LayoutInflater inflater = LayoutInflater.from(this);
            View alternativeView = inflater.inflate(R.layout.item_question_exam, null, false);
            TextView text = (TextView) alternativeView.findViewById(R.id.text);
            String letterIndex = String.valueOf(ExamEnumerationUtils.getLetterIndex(alternativeIndex));
            text.setText(String.format("%s - %s", letterIndex, alternative.getText()));

            text.setBackgroundColor(getResources().getColor(alternativeIndex % 2 == 0 ? R.color.background_question_even : R.color.background_question_odd));

            if (alternative.isSelected())
                text.setBackgroundColor(getResources().getColor(R.color.background_question_selected));

            text.setTag(R.integer.TAG_QUESTION_INDEX, currentQuestion);
            text.setTag(R.integer.TAG_LATERNATIVE_INDEX, alternative);
            text.setOnClickListener(v -> {
                Question question = (Question) text.getTag(R.integer.TAG_QUESTION_INDEX);
                Alternative answeredAlternative = (Alternative) text.getTag(R.integer.TAG_LATERNATIVE_INDEX);
                testFeedback.add(question, answeredAlternative);
                question.setAsSingleSelected(alternative);
                loadAlternatives(question);
            });
            lstQuestions.addView(alternativeView);
        }

    }


    @OnClick(R.id.btnPrev)
    public void previousQuestion() {
        setUpQuestionUI(examData.getQuestions().get(--questionIndex));
    }

    @OnClick(R.id.btnNext)
    public void nextQuestion() {
        setUpQuestionUI(examData.getQuestions().get(++questionIndex));
    }

    @OnClick(R.id.btnFinish)
    public void finishExam() {
        questionLayout.setVisibility(View.GONE);
        summaryExamLayout.setVisibility(View.VISIBLE);
        scrollLayout.setBackground(getResources().getDrawable(R.drawable.card_background));
        loadResult();
    }

    private void loadResult() {
        ViewUtils.animateCircleView(circleView, (int) testFeedback.generateResult(),
                barColorRange, rimColorRange, () -> btnExit.setVisibility(View.VISIBLE));
    }

    @OnClick(R.id.btnExit)
    public void exitExam() {
        super.finish();
    }


    private void initColorRanges() {
        barColorRange = new ColorRangeHolder();
        rimColorRange = new ColorRangeHolder();

        ColorRange barRange1to40 = new ColorRange(1, 40, getResources().getColor(R.color.exam_result_1_40));
        ColorRange rimRange1to40 = new ColorRange(1, 40, getResources().getColor(R.color.exam_result_1_40_rim));
        ColorRange barRange41to70 = new ColorRange(41, 70, getResources().getColor(R.color.exam_result_41_70));
        ColorRange rimRange41to70 = new ColorRange(41, 70, getResources().getColor(R.color.exam_result_41_70_rim));
        ColorRange barRange71to100 = new ColorRange(71, 100, getResources().getColor(R.color.exam_result_71_100));
        ColorRange rimRange71to100 = new ColorRange(71, 100, getResources().getColor(R.color.exam_result_71_100_rim));

        barColorRange.add(barRange1to40);
        barColorRange.add(barRange41to70);
        barColorRange.add(barRange71to100);

        rimColorRange.add(rimRange1to40);
        rimColorRange.add(rimRange41to70);
        rimColorRange.add(rimRange71to100);
    }


}
