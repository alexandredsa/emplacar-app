package br.com.fatecpg.emplacar.view.activities.levels;

import android.os.Bundle;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;

import java.util.ArrayList;

import br.com.fatecpg.emplacar.R;
import br.com.fatecpg.emplacar.domain.Reward;

public class ChooseVehicleActivity extends IntroActivity{

    private static final int TOTAL_CONTENTS = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSkipEnabled(false);
        setFullscreen(true);


        addSlide(new SimpleSlide.Builder()
                .title(R.string.slide_title_category_intro)
                .description(R.string.slide_description_category_intro)
                .image(R.drawable.img_question)
                .background(R.color.slide_category_background_color)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title(R.string.slide_title_category_a)
                .description(R.string.slide_description_category_a)
                .image(R.drawable.vehicle_a)
                .background(R.color.slide_category_background_color)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title(R.string.slide_title_category_b)
                .description(R.string.slide_description_category_b)
                .image(R.drawable.vehicle_b)
                .background(R.color.slide_category_background_color)
                .build());
        addSlide(new SimpleSlide.Builder()
                .title(R.string.slide_title_category_c)
                .description(R.string.slide_description_category_c)
                .image(R.drawable.vehicle_c)
                .background(R.color.slide_category_background_color)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title(R.string.slide_title_category_d)
                .description(R.string.slide_description_category_d)
                .image(R.drawable.vehicle_d)
                .background(R.color.slide_category_background_color)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title(R.string.slide_title_category_e)
                .description(R.string.slide_description_category_e)
                .image(R.drawable.vehicle_e)
                .background(R.color.slide_category_background_color)
                .build());
    }
}
