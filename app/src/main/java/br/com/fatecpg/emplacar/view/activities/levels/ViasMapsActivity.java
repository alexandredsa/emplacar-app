package br.com.fatecpg.emplacar.view.activities.levels;

import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.fatecpg.emplacar.view.activities.app.levels.MapsActivity;
import br.com.fatecpg.emplacar.view.maps.domain.DialogLesson;
import br.com.fatecpg.emplacar.view.maps.domain.Via;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by alexa on 11/09/2016.
 */
public class ViasMapsActivity extends MapsActivity{


    private StreetViewPanorama panorama;
    private List<Via> vias = new ArrayList<>();
    private int currentVia = 0;
    private SweetAlertDialog.OnSweetClickListener clickListener;

    @Override
    protected void onCreate() {
        super.onCreate();
        populateVias();
    }

    private void populateVias() {
        Via transitoRapido = new Via();
        transitoRapido.setPositions(Arrays.asList(new LatLng(-23.7945352, -46.5133947), new LatLng(-23.7911026, -46.5172131)));

        transitoRapido.setLessons(
                Arrays.asList(new DialogLesson("Vias", "Vamos começar pelas de Trânsito Rápido", "Continuar"),
                        new DialogLesson("Trânsito Rápido", "Segundo o CTB é “aquela caracterizada por acessos especiais com trânsito livre, sem interseções em nível, sem acessibilidade direta aos lotes lindeiros e sem travessia de PEDESTRES em nível”.", "Continuar")));

        vias.addAll(Arrays.asList(transitoRapido));
    }

    @Override
    public void onStreetViewReady(StreetViewPanorama panorama) {
        this.panorama = panorama;
        panorama.setUserNavigationEnabled(false);
        setUpLessons();

        // Start lesson
        checkLesson();
    }


    private void setUpLessons() {
        clickListener = sweetAlertDialog -> {
            checkLesson();
            sweetAlertDialog.dismiss();
        };
    }

    private void checkLesson() {
        Via via = vias.get(currentVia);

        if (via.hasLessons()) {
            via.nextLesson(ViasMapsActivity.this, clickListener).show();
            panorama.setPosition(via.nextPosition());
            return;
        }


        if (vias.size() >= ++currentVia) {
//            finish();
            return;
        }

        checkLesson();
    }
}
