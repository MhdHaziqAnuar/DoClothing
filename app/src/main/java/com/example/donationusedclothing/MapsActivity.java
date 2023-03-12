package com.example.donationusedclothing;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.donationusedclothing.databinding.ActivityMapsBinding;

import java.util.Vector;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    LatLng myLocation;

    Vector<MarkerOptions> markerOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        myLocation = new LatLng (6.4521,100.2778);

        markerOptions = new Vector<>();

        markerOptions.add(new MarkerOptions().title("Pusat Zakat Kedah")
                .position(new LatLng(6.1227, 100.3792))
                .snippet("Open 9AM - 4.30PM")
        );

        markerOptions.add(new MarkerOptions().title("Unit Zakat UiTM Perlis")
                .position(new LatLng(6.4477, 100.2777))
                .snippet("Open 9AM - 4.30PM")
        );

        markerOptions.add(new MarkerOptions().title("Projek Sedekah Abd Ghani Haron")
                .position(new LatLng(5.5825, 100.4367))
                .snippet("Open everyday call 013-286 3301")
        );

        markerOptions.add(new MarkerOptions().title("Majlis Agama Islam Perlis")
                .position(new LatLng(6.436955664198951, 100.18850968292084))
                .snippet("04-986 1169")
        );

        markerOptions.add(new MarkerOptions().title("Environment Idaman (Kangar)")
                .position(new LatLng(6.445432914015079, 100.3003482476943))
                .snippet("Open 8AM-5PM Saturday closed")
        );

        markerOptions.add(new MarkerOptions().title("Environment Idaman (Alor Setar)")
                .position(new LatLng(6.124307006137105, 100.34180419615737))
                .snippet("Open 8AM-5PM Friday & Saturday closed")
        );

        markerOptions.add(new MarkerOptions().title("Pertubuhan Kebajikan Anak Yatim Perlis")
                .position(new LatLng(6.423105869385827, 100.28029616303685))
                .snippet("Phone: 04-9763455")
        );

        markerOptions.add(new MarkerOptions().title("Pusat Jagaan Pertubuhan Kebajikan Anak Yatim Mak Teh")
                .position(new LatLng(6.452920793463624, 100.18393387653015))
                .snippet("Phone: 04-9770749")
        );



    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override // Method to add elements to map
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for(MarkerOptions mark : markerOptions) {
            mMap.addMarker(mark);
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 8));
    }

}