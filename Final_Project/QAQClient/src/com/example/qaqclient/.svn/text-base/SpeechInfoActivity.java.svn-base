package com.example.qaqclient;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class SpeechInfoActivity extends Activity
{
	private String topic;
	private String speaker;
	private String description;
	private double latitude;
	private double longitude;
	private TextView speechTopic;
	private TextView speechSpeaker;
	private TextView speechDescription;
	private TextView speechLatitude;
	private TextView speechLongitude;
	@Override
	protected void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_speech_info);
			
			Intent newIntent = getIntent();
			topic = newIntent.getStringExtra("topic");
			speaker = newIntent.getStringExtra("speaker");
			description = newIntent.getStringExtra("description");
			latitude = newIntent.getDoubleExtra("latitude", 0.0);
			longitude = newIntent.getDoubleExtra("longitude", 0.0);
			
			
			speechTopic = (TextView) findViewById(R.id.speech_info_topic_Content);
			speechSpeaker = (TextView) findViewById(R.id.speech_info_speaker_Content);
			speechDescription = (TextView) findViewById(R.id.speech_info_description_Content);
			speechLatitude = (TextView) findViewById(R.id.speech_info_latitude_Content);
			speechLongitude = (TextView) findViewById(R.id.speech_info_longitude_Content);
			
			speechTopic.setText(topic);
			speechSpeaker.setText(speaker);
			speechDescription.setText(description);
			speechLatitude.setText(Double.toString(latitude));
			speechLongitude.setText(Double.toString(longitude));
		}
	public void back(View view)
		{
			finish();
			overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
		{
			// Inflate the menu; this adds items to the action bar if it is
			// present.
			getMenuInflater().inflate(R.menu.speech_info, menu);
			return true;
		}
}