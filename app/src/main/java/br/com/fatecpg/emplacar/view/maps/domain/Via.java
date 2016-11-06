package br.com.fatecpg.emplacar.view.maps.domain;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by alexa on 11/09/2016.
 */
public class Via {
    private List<LatLng> positions;
    private List<DialogLesson> lessons;
    private int currentLesson = -1;
    private int currentPosition = -1;

    public List<DialogLesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<DialogLesson> lessons) {
        this.lessons = lessons;
    }

    public List<LatLng> getPositions() {
        return positions;
    }

    public void setPositions(List<LatLng> positions) {
        this.positions = positions;
    }

    public boolean hasPositions() {
        return currentPosition + 1 < positions.size();
    }

    public LatLng nextPosition() {
        if (!hasPositions())
            return this.positions.get(this.positions.size() - 1);

        return this.positions.get(++currentPosition);
    }

    public boolean hasLessons() {
        return currentLesson + 1 < lessons.size();
    }

    public MaterialDialog nextLesson(Context mContext, MaterialDialog.OnClickListener clickListener) {
        if (!hasLessons())
            return null;

        return lessons.get(++currentLesson).buildDialog(mContext, clickListener);
    }
}
