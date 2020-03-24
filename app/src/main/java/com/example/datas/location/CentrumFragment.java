package com.example.datas.location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.datas.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class CentrumFragment extends Fragment implements OnMapReadyCallback {

    public CentrumFragment() {
        // Required empty public constructor
    }


    private static final String TAG = "LocationCentrumFragment";
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private GoogleMap mMap;

    //vars
    private boolean mLocationPermissionGranted = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getLocationPermission();
        return inflater.inflate(R.layout.centrum_fragment, container, false);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "onMapReady: Map is ready");
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        if (mLocationPermissionGranted) {
            getLocation();
        }

    }

    private void getLocation() {
        Log.d(TAG, "getDeviceLocation: getting centrum  location");

        Log.d(TAG, "onComplete: found centrum location!");

        final LatLng TutorialsPoint = new LatLng(5.827468, -55.157980);

        mMap.addMarker(new MarkerOptions().position(TutorialsPoint).title(""));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(TutorialsPoint));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(TutorialsPoint, 19));

    }

    private void getLocationPermission() {
        Log.d(TAG, "getLocationPermission: Getting location permission");

        String[] permission = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getActivity().getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getActivity().getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;
                initMap();
            } else {
                requestPermissions(permission, LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            requestPermissions(permission, LOCATION_PERMISSION_REQUEST_CODE);
        }


    }


    private void initMap() {
        Log.d(TAG, "initMap: Initializing map ");
        SupportMapFragment mMapFragment = SupportMapFragment.newInstance();
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.map, mMapFragment);
        fragmentTransaction.commit();
        mMapFragment.getMapAsync(this);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: called");
        mLocationPermissionGranted = false;

        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionGranted = false;
                            Log.d(TAG, "onRequestPermissionsResult: permission failed");
                            return;
                        }
                    }
                    Log.d(TAG, "onRequestPermissionsResult: permission granted");
                    mLocationPermissionGranted = true;
                    //initialized our map
                    initMap();

                }
            }
        }
    }
}
