package br.com.fatecpg.emplacar.view.activities.levels;

import android.os.Bundle;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;

import java.util.ArrayList;

import br.com.fatecpg.emplacar.R;
import br.com.fatecpg.emplacar.domain.Reward;

public class LessonRegulationActivity extends IntroActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSkipEnabled(false);
        setFullscreen(true);
        addSlide(new SimpleSlide.Builder()
                .title(R.string.slide_title_regulation_intro)
                .description(R.string.slide_description_regulation_intro)
                .image(R.drawable.img_question)
                .background(R.color.slide_regulation_background_color)
                .build());
    }
}
