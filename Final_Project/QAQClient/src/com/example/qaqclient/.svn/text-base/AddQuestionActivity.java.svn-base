package com.example.qaqclient;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class AddQuestionActivity extends Activity
{
	private EditText titleInput;
	private EditText contentInput;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_add_question);
			
			titleInput = (EditText) findViewById(R.id.question_add_TitleEdit);
			contentInput = (EditText) findViewById(R.id.question_add_ContentEdit);
		}
	
	
	public void ask(View view)
		{
			Intent intent = new Intent(this, SpeechIndexActivity.class);
			intent.putExtra("title", titleInput.getText().toString());
			intent.putExtra("content", contentInput.getText().toString());
			intent.putExtra("like", 1);
			startActivity(intent);
			overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
		}
	public void cancel(View view)
		{
			finish();
			overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
		}	
	public void reset(View view)
		{
			this.titleInput.setText("");
			this.contentInput.setText("");
		}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
		{
			// Inflate the menu; this adds items to the action bar if it is
			// present.
			getMenuInflater().inflate(R.menu.add_question, menu);
			return true;
		}
}