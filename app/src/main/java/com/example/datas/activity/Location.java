package com.example.datas.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.datas.R;
import com.example.datas.location.CentrumFragment;
import com.example.datas.location.ShopFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class Location extends AppCompatActivity {


    private static final String TAG = "LocationActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
//        loadFragment(new FirstFragment());
        isServiceOK();

    }


    public void handleFragment(View view) {
        if (view == findViewById(R.id.locationCentrum)) {
            loadFragment(new CentrumFragment());
        }
        if (view == findViewById(R.id.locationShop)) {
            loadFragment(new ShopFragment());
        }

    }

    private void loadFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainer2, fragment);
        transaction.commit();
    }




    public boolean isServiceOK(){
        Log.d(TAG, "isServiceOK: Checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(Location.this);

        if (available == ConnectionResult.SUCCESS){
            //everything is fine and user can make request
            Log.d(TAG, "isServiceOK: Google service is working");
        }
        else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occurred but we can resolve it
            Log.d(TAG, "isServiceOK: an error occurred but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(Location.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();

        } else {
            Toast.makeText(this, "You can't make map request", Toast.LENGTH_SHORT).show();
        }
        return false;

    }
}
