package org.underpressureapps.unpokemon;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private List<org.underpressureapps.unpokemon.Location> pokeLocation;
    private GoogleMap mMap;

    static Bitmap[] bit = {BitmapFactory.decodeFile(String.valueOf(R.drawable.bidoof)),
            BitmapFactory.decodeFile(String.valueOf(R.drawable.carvanha)), BitmapFactory.decodeFile(String.valueOf(R.drawable.hoothoot)),
            BitmapFactory.decodeFile(String.valueOf(R.drawable.meowth)),BitmapFactory.decodeFile(String.valueOf(R.drawable.oddish)),
            BitmapFactory.decodeFile(String.valueOf(R.drawable.rattata)), BitmapFactory.decodeFile(String.valueOf(R.drawable.sentret)),
            BitmapFactory.decodeFile(String.valueOf(R.drawable.shellos)),BitmapFactory.decodeFile(String.valueOf(R.drawable.shuckle)),
            BitmapFactory.decodeFile(String.valueOf(R.drawable.zubat))};

    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        //
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100,
                1.0f, locationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100,
                1.0f, locationListener);
    }

    public void pokeLocations(List<org.underpressureapps.unpokemon.Location> aVoid){
        pokeLocation=aVoid;
        LatLng pos;
        mMap.clear();
        for (org.underpressureapps.unpokemon.Location Locat : pokeLocation){
            pos=new LatLng(Locat.getLatitude(), Locat.getLongitude());
            mMap.addMarker(new MarkerOptions().position(pos).title("Pokemon").
                    icon(BitmapDescriptorFactory.fromBitmap(bit[pokeLocation.indexOf(Locat)])));
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // fore ActivityCompat#requestPermissions for more details.
            return;
        }

        new LocationData(this).execute();
        mMap.setMyLocationEnabled(true);

    }

    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            LatLng position = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 13));
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

    };
}