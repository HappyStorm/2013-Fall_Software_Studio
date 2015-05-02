package com.example.qaqclient;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class WelcomeActivity extends FragmentActivity implements LocationListener
{
	//private ImageView welcomePhoto;
		
	@Override
	protected void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_welcome);
			
			// Toast.makeText(getApplicationContext(), "Welcome onCreat!!!", Toast.LENGTH_LONG).show();
			// welcomePhoto = (ImageView) findViewById(R.id.welcome_photo);
			// welcomePhoto.setImageResource(R.drawable.logo);
			// Getting Google Play availability status
	        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
	        
	        // Showing status
	        if(status!=ConnectionResult.SUCCESS)
	        	{ 
	        		 int requestCode = 10;
	                 Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
	                 dialog.show();
	            } 
	        else 
	        	{ 
		            /*if(location != null)
		            	onLocationChanged(location); 
		            locationManager.requestLocationUpdates(provider, 20000, 0, this);*/
	        	}
		
		}
	
	public void welcome(View view)
		{
			// Getting LocationManager object from System Service LOCATION_SERVICE
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            
            // Creating a criteria object to retrieve provider
            Criteria criteria = new Criteria();		 
            
            // Getting the name of the best provider
            String provider = locationManager.getBestProvider(criteria, true);
            
            // Getting Current Location
            Location location = locationManager.getLastKnownLocation(provider);
            
            
            // Getting latitude of the current location
	        double latitude = location.getLatitude();
	        
	        // Getting longitude of the current location
	        double longitude = location.getLongitude();
	        
	        Intent intent = new Intent(this, MainActivity.class);
	        intent.putExtra("longitude", longitude);
	        intent.putExtra("latitude", latitude);
	        //Toast.makeText(getApplicationContext(), lati, Toast.LENGTH_LONG).show();
	        startActivity(intent);
	        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
		}
	
	@Override
    public void onLocationChanged(Location location) 
		{
			// TODO Auto-generated method stub
			
			/*// Getting latitude of the current location
	        double latitude = location.getLatitude();
	        // Getting longitude of the current location
	        double longitude = location.getLongitude();
	        
	        Intent intent = new Intent(this, MainActivity.class);
	        intent.putExtra("longitude", longitude);
	        intent.putExtra("latitude", latitude);
	        startActivity(intent);*/
	    }
	@Override
	public void onProviderDisabled(String arg0)
		{
			// TODO Auto-generated method stub		
		}
	@Override
	public void onProviderEnabled(String arg0)
		{
			// TODO Auto-generated method stub		
		}
	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2)
		{
			// TODO Auto-generated method stub	
		}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
		{
			// Inflate the menu; this adds items to the action bar if it is
			// present.
			getMenuInflater().inflate(R.menu.welcome, menu);
			return true;
		}
}