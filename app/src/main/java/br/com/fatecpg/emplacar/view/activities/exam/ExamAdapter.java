package br.com.fatecpg.emplacar.view.activities.exam;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.fatecpg.emplacar.R;
import br.com.fatecpg.emplacar.domain.entity.Exam;

/**
 * Created by alexandre on 03/11/16.
 */
public class ExamAdapter extends RecyclerView.Adapter<ExamViewHolder> {
    private List<Exam> exams;

    public ExamAdapter(List<Exam> exams) {
        this.exams = exams;
    }

    @Override
    public ExamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exam, parent, false);
        ExamViewHolder rcv = new ExamViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(ExamViewHolder holder, int position) {
        final Exam exam = this.exams.get(position);
        holder.thumb.setImageResource(exam.getThumb());
        holder.bestScore.setText(String.valueOf(exam.getBestScore()) + "%");
        holder.questionCount.setText(String.format("%s Questões",String.valueOf(exam.getQuestionCount())));
        holder.newExam.setVisibility(exam.isNew() ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return exams.size();
    }
}
