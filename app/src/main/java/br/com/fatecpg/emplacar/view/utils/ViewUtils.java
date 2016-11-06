package br.com.fatecpg.emplacar.view.utils;

import android.os.Handler;
import android.view.animation.DecelerateInterpolator;

import at.grabner.circleprogress.CircleProgressView;

/**
 * Created by alexandre on 05/11/16.
 */

public class ViewUtils {
    public static void animateCircleView(final CircleProgressView circleProgressView, int finalValue, ColorRangeHolder barColorRange, ColorRangeHolder rimColorRange, UICallback callback) {
        DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator(.8f);
        int initialValue = 0;
        int start = Math.min(initialValue, finalValue);
        int end = Math.max(initialValue, finalValue);
        int difference = Math.abs(finalValue - initialValue);
        Handler handler = new Handler();
        for (int count = start; count <= end; count++) {
            int time = Math.round(decelerateInterpolator.getInterpolation((((float) count) / difference)) * 100) * count;
            final int finalCount = ((initialValue > finalValue) ? initialValue - count : count);
            handler.postDelayed(() -> {
                circleProgressView.setValue(finalCount);
                circleProgressView.setBarColor(barColorRange.getColor(finalCount));
                circleProgressView.setRimColor(rimColorRange.getColor(finalCount));

                if(finalCount == finalValue)
                    callback.finish();
            },
                    time / 2);
        }
    }
}
