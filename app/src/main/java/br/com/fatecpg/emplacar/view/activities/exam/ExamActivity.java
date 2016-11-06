package br.com.fatecpg.emplacar.view.activities.exam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import java.util.Arrays;
import java.util.List;

import br.com.fatecpg.emplacar.R;
import br.com.fatecpg.emplacar.domain.builder.ExamDataBuilder;
import br.com.fatecpg.emplacar.domain.builder.QuestionBuilder;
import br.com.fatecpg.emplacar.domain.entity.Exam;
import br.com.fatecpg.emplacar.domain.vo.Alternative;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexandre on 03/11/16.
 */

public class ExamActivity extends AppCompatActivity {
    @BindView(R.id.rcvExam)
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_exam);
        ButterKnife.bind(this);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter = new ExamAdapter(mock(), this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<Exam> mock() {
        Exam exam = new Exam(ExamDataBuilder
                .anExamData()
                .thumb(R.drawable.thumb_exam_legislacao)
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
                .build());

        Exam exam2 = new Exam(ExamDataBuilder
                .anExamData()
                .thumb(R.drawable.thumb_exam_legislacao)
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
                                        new Alternative("Certa", true))).build(),
                        QuestionBuilder
                                .aQuestion()
                                .text("Terceira Questão")
                                .alternatives(Arrays.asList(
                                        new Alternative("Errada I", false),
                                        new Alternative("Errada II", false),
                                        new Alternative("Errada III", false),
                                        new Alternative("Certa", true))).build()))
                .build());

        exam2.setNew(true);

        return Arrays.asList(exam, exam2);
    }
}
