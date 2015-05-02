package com.example.qaqclient;

import netdb.softwarestudio.rest.RestManager;
import netdb.softwarestudio.rest.RestToolCallbackDelegate;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class BuildSpeechActivity extends Activity
{
	//private ArrayList<SpeechModel> speechList = new ArrayList<SpeechModel>();
	private EditText topicInput;
	private EditText speakerInput;
	private EditText descriptionInput;
	private double latitude;
	private double longitude;
	private String topic;
	private String speaker;
	private String description;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_build_speech);
			
			topicInput = (EditText) findViewById(R.id.speech_build_TopicEdit);
			speakerInput = (EditText) findViewById(R.id.speech_build_SpeakerEdit);
			descriptionInput = (EditText) findViewById(R.id.speech_build_DescriptionEdit);
			
			Intent newIntent = getIntent();
			latitude = newIntent.getDoubleExtra("latitude", 0.0);
			longitude = newIntent.getDoubleExtra("longitude", 0.0);
			
			
			//String lati = Double.toString(latitude);
			//String longi = Double.toString(longitude);
			//Toast.makeText(getApplicationContext(), lati, Toast.LENGTH_LONG).show();
			//Toast.makeText(getApplicationContext(), longi, Toast.LENGTH_LONG).show();
		}
	
	public void build(View view)
		{
			topic = topicInput.getText().toString();
			speaker = speakerInput.getText().toString();
			description = descriptionInput.getText().toString();
			Intent intent = new Intent(this, SpeechIndexActivity.class);
			intent.putExtra("topic", topic);
			intent.putExtra("speaker", speaker);
			intent.putExtra("description", description);
			intent.putExtra("latitude", latitude);
			intent.putExtra("longitude", longitude);
			intent.putExtra("which", 0);
			//Toast.makeText(getApplicationContext(), "build Method pressd.", Toast.LENGTH_LONG).show();
			buildSpeech(topic, speaker, description, latitude, longitude);
			//System.out.printf("public void build!!!");
			startActivity(intent);
			finish();
			overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
		}	
	
	public void cancel(View view)
		{
			finish();
			overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
		}	
	
	public void reset(View view)
		{
			this.topicInput.setText("");
			this.speakerInput.setText("");
			this.descriptionInput.setText("");
		}
	
	
	public void buildSpeech(String topic, String speaker, String description, 
							double latitude, double longitude) 
		{
			//String url = "http://140.114.86.191:8080/speech/";
			String url = "http://140.114.200.160:8080/speech/";
			//String url = "http://qaq-server.appspot.com/speech/";
			//String url = "http://192.168.1.134:8080/question/";
			SpeechModel spe = new SpeechModel();
			spe.setTopic(topic);
			spe.setSpeaker(speaker);
			spe.setDescription(description);
			spe.setLatitude(latitude);
			spe.setLongitude(longitude);
			//spe.setDate("");
			spe.setLatitude(latitude);
			spe.setStamp(System.currentTimeMillis());
			
			//speechList.add(0, spe);
			//Toast.makeText(getApplicationContext(), url, Toast.LENGTH_LONG).show();
			
			RestManager.postObject(url, spe, SpeechModel.class, null, new RestToolCallbackDelegate()
				{
					@Override
					public void success(Object data) 
						{
							System.out.println("result:" + data);
						}
					
					@Override
					public void fail(String msg) 
						{
							System.out.println(msg);
						}
				});
			//System.out.printf("buildSpeech!!!");
		}
	
	/*@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
		{
			super.onActivityResult(requestCode, resultCode, data);
			if(requestCode==0)
				{
					this.topicInput.setText("");
					this.speakerInput.setText("");
					this.descriptionInput.setText("");
				}
		}*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
		{
			// Inflate the menu; this adds items to the action bar if it is
			// present.
			getMenuInflater().inflate(R.menu.build_speech, menu);
			return true;
		}
}