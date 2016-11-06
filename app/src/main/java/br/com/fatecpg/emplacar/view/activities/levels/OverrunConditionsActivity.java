package br.com.fatecpg.emplacar.view.activities.levels;

import android.os.Bundle;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;

import br.com.fatecpg.emplacar.R;

/**
 * Created by alexandre on 06/11/16.
 */

public class OverrunConditionsActivity extends IntroActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSkipEnabled(false);
        setFullscreen(true);


        addSlide(new SimpleSlide.Builder()
                .title("Simples Seccionada")
                .description("Ultrapassagem permitida para os dois sentidos.")
                .image(R.drawable.simples_seccionada)
                .background(R.color.slide_overrun_conditions_background_color)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title("Simples Contínua")
                .description("Ultrapassagem proibida para os dois sentidos.")
                .image(R.drawable.simples_continua)
                .background(R.color.slide_overrun_conditions_background_color)
                .build());
        addSlide(new SimpleSlide.Builder()
                .title("Dupla Contínua/Seccionada")
                .description("Ultrapassagem permitida somente no sentido seccionado.")
                .image(R.drawable.dupla_continua_seccionada)
                .background(R.color.slide_overrun_conditions_background_color)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title("Dupla Contínua")
                .description("Ultrapassagem proibida para os dois sentidos.")
                .image(R.drawable.dupla_continua)
                .background(R.color.slide_overrun_conditions_background_color)
                .build());

    }
}
