package br.com.fatecpg.emplacar.view.activities.app.levels;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

import br.com.fatecpg.emplacar.R;

public abstract class MapsActivity extends FragmentActivity implements OnStreetViewPanoramaReadyCallback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        StreetViewPanoramaFragment streetViewPanoramaFragment =
                (StreetViewPanoramaFragment) getFragmentManager()
                        .findFragmentById(R.id.streetviewpanorama);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);
        onCreate();
    }

    protected void onCreate() {
    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {
        animateStreetViewPanorama(panorama);
        onStreetViewReady(panorama);
    }

    public abstract void onStreetViewReady(StreetViewPanorama panorama);


    private void animateStreetViewPanorama(StreetViewPanorama panorama) {
        long duration = 500;
        float tilt = 0;
        StreetViewPanoramaCamera camera = new StreetViewPanoramaCamera.Builder()
                .zoom(panorama.getPanoramaCamera().zoom)
                .bearing(panorama.getPanoramaCamera().bearing)
                .tilt(tilt)
                .build();

        panorama.animateTo(camera, duration);
    }


    public void openMenu(View view) {
        Intent i = new Intent(this, MenuActivity.class);
        startActivity(i);
    }
}
