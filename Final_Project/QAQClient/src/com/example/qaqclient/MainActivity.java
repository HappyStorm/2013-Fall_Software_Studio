package com.example.qaqclient;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity
{
	private double latitude;
	private double longitude;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);		
			
			Intent newIntent = getIntent();
			latitude = newIntent.getDoubleExtra("latitude", 0.0);
			longitude = newIntent.getDoubleExtra("longitude", 0.0);
			//String lati = Double.toString(latitude);
			//String longi = Double.toString(longitude);
			//Toast.makeText(getApplicationContext(), lati, Toast.LENGTH_LONG).show();
			//Toast.makeText(getApplicationContext(), longi, Toast.LENGTH_LONG).show();
		}
	public void search(View view)
		{
			Intent intent = new Intent(this, SpeechSearchActivity.class);
			intent.putExtra("latitude", latitude);
			intent.putExtra("longitude", longitude);
			startActivity(intent);
			overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
		}
	public void build(View view)
		{
			Intent intent = new Intent(this, BuildSpeechActivity.class);
			intent.putExtra("latitude", latitude);
			intent.putExtra("longitude", longitude);
			Bundle bundle = new Bundle();
			startActivity(intent);
			overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
			//overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
		}
	
	public void exit(View view)
		{
			finish();
			overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
		}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
		{
			// Inflate the menu; this adds items to the action bar if it is
			// present.
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}
}