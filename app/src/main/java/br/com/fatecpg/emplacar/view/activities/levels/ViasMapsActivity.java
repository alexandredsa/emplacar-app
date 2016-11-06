package br.com.fatecpg.emplacar.view.activities.levels;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.fatecpg.emplacar.R;
import br.com.fatecpg.emplacar.view.activities.app.levels.MapsActivity;
import br.com.fatecpg.emplacar.view.maps.domain.DialogLesson;
import br.com.fatecpg.emplacar.view.maps.domain.Via;

/**
 * Created by alexa on 11/09/2016.
 */
public class ViasMapsActivity extends MapsActivity {


    private StreetViewPanorama panorama;
    private List<Via> vias = new ArrayList<>();
    private int currentVia = 0;
    private MaterialDialog.OnClickListener clickListener;

    @Override
    protected void onCreate() {
        super.onCreate();
        populateVias();
    }

    private void populateVias() {
        Via transitoRapido = new Via();
        transitoRapido.setPositions(Arrays.asList(
                new LatLng(-23.7945352, -46.5133947),
                new LatLng(-23.7911026, -46.5172131),
                new LatLng(-23.7876946, -46.5210918),
                new LatLng(-23.7851469, -46.5241146),
                new LatLng(-24.014157,-46.4477865),
                new LatLng(-24.0131177,-46.4449439),
                new LatLng(-24.0046166, -46.4230279),
                new LatLng(-24.0039308,-46.4216064),
                new LatLng(-24.0120086,-46.406844),
                new LatLng(-24.010347,-46.4066596),
                new LatLng(-23.4656289,-46.5014596),
                new LatLng(-23.4667912,-46.5038882),
                new LatLng(-23.4673877,-46.9025549),
                new LatLng(-26.2261173,-49.4436627),
                new LatLng(-26.2298162,-49.4432666)
                ));

        transitoRapido.setLessons(
                Arrays.asList(
                        new DialogLesson("Vias", "Usaremos o recurso do StreetView para visualizamos os tipos de vias." +
                                "Navegue pelo mapa enquanto te explicamos sobre essa via.", "Continuar")
                        , new DialogLesson("Vias", "Vamos começar pelas de Trânsito Rápido", "Continuar"),
                        new DialogLesson("Trânsito Rápido", "Segundo o CTB é “aquela caracterizada " +
                                "por acessos especiais com trânsito livre, sem interseções em nível, " +
                                "sem travessia de PEDESTRES em nível, e sem semáforo”.", "Continuar"),
                        new DialogLesson("", "A velocidade permitida nesta via é de no máximo 80km/h.", "Continuar", R.drawable.placa_80km),
                        new DialogLesson("Via Arterial", "Caracterizada por interseções em nível, geralmente controlada por semáforo, " +
                                "com acessibilidade aos lotes lindeiros e às vias secundárias e locais," +
                                " possibilitando o trânsito entre as regiões da cidade.", "Continuar"),
                        new DialogLesson("", "Velocidade permitida de 60km/h.", "Continuar", R.drawable.placa_60km),
                        new DialogLesson("Via Coletora", "Destinada a coletar e distribuir o trânsito que tenha necessidade de entrar ou sair das vias de trânsito rápido " +
                                "ou arteriais, possibilitando o trânsito dentro das regiões da cidade..", "Continuar"),
                        new DialogLesson("", "Velocidade permitida de 40km/h", "Continuar", R.drawable.placa_40km),
                        new DialogLesson("Via Local", "Caracterizada por interseções em nível não semaforizadas, destinada apenas ao acesso local ou a áreas restritas.", "Continuar"),
                        new DialogLesson("", "Velocidade permitida de 30km/h", "Continuar", R.drawable.placa_30km),
                        new DialogLesson("Rodovia", "Via Rural Pavimentada.", "Continuar"),
                        new DialogLesson("", "Velocidade permitida de 110km/h para automóveis, camionetas e motocicletas", "Continuar", R.drawable.placa_110km),
                        new DialogLesson("", "Velocidade permitida de 90km/h para ônibus e microônibus.", "Continuar", R.drawable.placa_90km),
                        new DialogLesson("", "Velocidade permitida de 80km/h para os demais veículos.", "Continuar", R.drawable.placa_80km),
                        new DialogLesson("Estrada", "Via Rural NAO Pavimentada.", "Continuar"),
                        new DialogLesson("", "Velocidade permitida de 60km/h", "Continuar", R.drawable.placa_60km)
                ));

        vias.addAll(Arrays.asList(transitoRapido));
    }

    @Override
    public void onStreetViewReady(StreetViewPanorama panorama) {
        this.panorama = panorama;
        panorama.setOnStreetViewPanoramaClickListener(streetViewPanoramaLocation -> checkLesson());
//        panorama.setUserNavigationEnabled(false);
        setUpLessons();

        // Start lesson
        checkLesson();
    }


    private void setUpLessons() {
        clickListener = (dialog, which) -> dialog.dismiss();
    }

    private void checkLesson() {
        Via via = vias.get(currentVia);

        if (via.hasLessons()) {
            via.nextLesson(ViasMapsActivity.this, clickListener).show();
            panorama.setPosition(via.nextPosition());
            return;
        }


        if (vias.size() >= ++currentVia) {
            finish();
            return;
        }

//        checkLesson();
    }
}
