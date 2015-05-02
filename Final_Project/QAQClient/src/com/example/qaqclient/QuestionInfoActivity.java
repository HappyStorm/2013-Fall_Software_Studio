package com.example.qaqclient;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class QuestionInfoActivity extends Activity
{
	private String title;
	private String content;
	private String speechTitle;
	private Long id;
	private int like;
	private int ifLike;
	private TextView questionTitle;
	private TextView questionContent;
	private ToggleButton togglebutton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_question_info);
			
			Intent newIntent = getIntent();
			title = newIntent.getStringExtra("title");
			content = newIntent.getStringExtra("content");
			speechTitle = newIntent.getStringExtra("speechTitle");
			like = newIntent.getIntExtra("like", 0);
			id = newIntent.getLongExtra("id", 0);
			System.out.println("QuestionInfo onCreate: " + like);
			//System.out.println(id);
			questionTitle = (TextView) findViewById(R.id.question_info_title_Content);
			questionContent = (TextView) findViewById(R.id.question_info_content_Content);
			
			questionTitle.setText(title);
			questionContent.setText(content);
			
			togglebutton = (ToggleButton) findViewById(R.id.question_info_like_button);  
		    

			togglebutton.setOnClickListener(new OnClickListener() 
				{      
		            public  void  onClick(View v) 
		            	{          
			                // 當按鈕第一次被點擊時候響應的事件        
			                if(togglebutton.isChecked())              
			                	ifLike=1;    
			                // 當按鈕再次被點擊時候響應的事件  
			                else         
			                	ifLike=0;
			                System.out.println("Now in click: " + (like + ifLike));
			            }  
		        });
		}
	
	public void back(View view)
		{
			Intent intent = new Intent(this, SpeechIndexActivity.class);
			int newLike = like + ifLike;
			intent.putExtra("title", title);
			intent.putExtra("content", content);
			intent.putExtra("speechTitle", speechTitle);
			intent.putExtra("like", newLike);
			intent.putExtra("id", id.longValue());
			//System.out.println(like);
			System.out.println("Now in back: " + newLike);
			//System.out.println(id);
			setResult(1, intent);
			finish();
			overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
		{
			// Inflate the menu; this adds items to the action bar if it is
			// present.
			getMenuInflater().inflate(R.menu.question_info, menu);
			return true;
		}
}