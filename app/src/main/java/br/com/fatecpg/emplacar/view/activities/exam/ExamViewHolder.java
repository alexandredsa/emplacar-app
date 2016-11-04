package br.com.fatecpg.emplacar.view.activities.exam;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.fatecpg.emplacar.R;

/**
 * Created by alexandre on 03/11/16.
 */
public class ExamViewHolder extends RecyclerView.ViewHolder {
    ImageView thumb;
    TextView title, bestScore, questionCount;
    View newExam;

    public ExamViewHolder(View itemView) {
        super(itemView);
        thumb = (ImageView) itemView.findViewById(R.id.thumb);
        title = (TextView) itemView.findViewById(R.id.title);
        bestScore = (TextView) itemView.findViewById(R.id.bestScore);
        questionCount = (TextView) itemView.findViewById(R.id.questionCount);
        newExam = itemView.findViewById(R.id.newExam);
    }
}
