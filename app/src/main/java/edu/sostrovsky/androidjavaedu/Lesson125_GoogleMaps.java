package edu.sostrovsky.androidjavaedu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentActivity;

import com.sostrovsky.androidjavaedu.R;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by OS1 on 23.12.2016.
 */
public class Lesson125_GoogleMaps extends FragmentActivity implements View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private SupportMapFragment mapFragment;
    private GoogleMap map;

    private final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson125);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson125_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        //////////////////////////////////////////////////////////////////////////////////

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        map = mapFragment.getMap();

        if (map == null) {
            finish();
            return;
        }

        Log.d(LOG_TAG, "GooglePlayServicesAvailable: " + GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this));

        init();
    }

    private void init() {
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson125_ArrowBackWrapLL:
                startActivity(new Intent(Lesson125_GoogleMaps.this, Main_Activity.class));
                break;
            case R.id.Lesson125_Test_Btn:
                map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            default:
                break;
        }
    }
}