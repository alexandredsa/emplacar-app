package br.com.fatecpg.emplacar.view.activities.levels;

import android.os.Bundle;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;

import br.com.fatecpg.emplacar.R;

/**
 * Created by alexandre on 06/11/16.
 */

public class TrafficViolationActivity extends IntroActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSkipEnabled(false);
        setFullscreen(true);


        addSlide(new SimpleSlide.Builder()
                .title("Penalidades e Medidas Administrativas")
                .description("")
                .image(R.drawable.traffic_violation_intro)
                .background(R.color.slide_traffic_violation_intro_background_color)
                .build());
        addSlide(new SimpleSlide.Builder()
                .title("Penalidades e Medidas Administrativas")
                .descriptionHtml("<h2>Gravidade - Leve</h2><h3>Valor - R$ 88,38 </h3><h3>Pontos - 3</h3>")
                .background(R.color.slide_traffic_violation_one_background_color)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title("Penalidades e Medidas Administrativas")
                .descriptionHtml("<h2>Gravidade - Média</h2><h3>Valor - R$ 130,16 </h3><h3>Pontos - 4</h3>")
                .background(R.color.slide_traffic_violation_two_background_color)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title("Penalidades e Medidas Administrativas")
                .descriptionHtml("<h2>Gravidade - Grave</h2><h3>Valor - R$ 195,23 </h3><h3>Pontos - 5</h3>")
                .background(R.color.slide_traffic_violation_two_background_color)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title("Penalidades e Medidas Administrativas")
                .descriptionHtml("<h2>Gravidade - Gravíssima</h2><h3>Valor - R$ 293,47 </h3><h3>Pontos - 7</h3>")
                .background(R.color.slide_traffic_violation_two_background_color)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title("Suspensão de CNH")
                .description("A penalidade de suspensão do direito de dirigir será aplicada quando o condutor soma 20 pontos na carteira no período de 12 meses.")
                .image(R.drawable.cnh)
                .background(R.color.slide_traffic_violation_intro_background_color)
                .build());
    }
}