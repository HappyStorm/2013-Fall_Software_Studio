package com.example.qaqclient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import netdb.softwarestudio.rest.RestManager;
import netdb.softwarestudio.rest.RestToolCallbackDelegate;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SpeechIndexActivity extends Activity
{
	private ArrayList<QuestionModel> questionList = new ArrayList<QuestionModel>();
	//private List <HashMap<String, Object>> questionData = new ArrayList<HashMap<String, Object>>();   
	private ListView questionListView;
	private QuestionAdapter adapter;
	//private SimpleAdapter questionAdapter;
	private String topic;
	private String speaker;
	private String description;
	//private Integer like;
	private TextView speechTopic;
	private TextView speechSpeaker;
	private TextView speechDescription;
	private int which = 1;
	
	private double latitude;
	private double longitude;
	private Dialog addQuestionDialog;
	
	static final int RESULT_CHANGE_LIKE = 0;
	static final int RESULT_SPEECH_INFO = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_speech_index);
			
			
			Intent newIntent = getIntent();
			
			questionListView = (ListView) findViewById(R.id.speech_index_QuestionListView);
			topic = newIntent.getStringExtra("topic");
			speaker = newIntent.getStringExtra("speaker");
			description = newIntent.getStringExtra("description");
			latitude = newIntent.getDoubleExtra("latitude", 0.0);
			longitude = newIntent.getDoubleExtra("longitude", 0.0);
			which = newIntent.getIntExtra("which", 1);
			
			speechTopic = (TextView) findViewById(R.id.speech_index_SpeechTopic);
			speechSpeaker = (TextView) findViewById(R.id.speech_index_SpeechSpeaker);
			speechDescription = (TextView) findViewById(R.id.speech_index_SpeechDescription);
			
			//System.out.println("Run here.");
			
			speechTopic.setText(topic);
			speechSpeaker.setText(speaker);
			speechDescription.setText(description);
			
			//questionAdapter = new  SimpleAdapter(this, questionData, R.layout.question_item, new String[]{"Title", "Content", "Like"}, new int[]{R.id.question_item_Title, R.id.question_item_Text, R.id.question_item_like});
			//questionListView.setAdapter(questionAdapter);
			/*ListView.OnItemClickListener questionDataListener = new ListView.OnItemClickListener()
				{
					@Override
		            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		            	{
		            		ListView  questionListView =(ListView) parent;  
		            		HashMap <String, Object> questionData = (HashMap <String, Object>) questionListView.getItemAtPosition(position);
		            		
		            		Intent intent = new Intent(SpeechIndexActivity.this, QuestionInfoActivity.class);
			                //QuestionModel tempQue = questionData.get(position);
			                //Long questionId = tempQue.getId();
			                intent.putExtra("title", questionData.get("title").toString());
			                intent.putExtra("content", questionData.get("content").toString());
			                intent.putExtra("speechTitle", questionData.get("speechTitle").toString());
			                intent.putExtra("like", Integer.parseInt(questionData.get("like").toString()));
			                intent.putExtra("id", Long.parseLong(questionData.get("id").toString()));
			                //System.out.println(questionId);
			                //System.out.println("First click: " + tempQue.getLike());
			                startActivityForResult(intent, RESULT_CHANGE_LIKE);
			                overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
		            	}
				};
				questionListView.setOnItemClickListener(questionDataListener);*/
				
				
			adapter = new QuestionAdapter(this, questionList);
			questionListView.setAdapter(adapter);
			ListView.OnItemClickListener questionListener = new ListView.OnItemClickListener()
				{
					@Override
		            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		            	{
			                Intent intent = new Intent(SpeechIndexActivity.this, QuestionInfoActivity.class);
			                //System.out.println("Run 12here");
			                QuestionModel tempQue = questionList.get(position);
			                //System.out.println("Run 34here");
			                Long questionId = tempQue.getId();
			                System.out.println("Ready to print ID");
			                System.out.println(questionId);
			                //System.out.println(tempQue.getTitle());
			                intent.putExtra("title", tempQue.getTitle());
			                intent.putExtra("content", tempQue.getContent());
			                intent.putExtra("speechTitle", tempQue.getspeechTitle());
			               // System.out.println("Run 56here");
			                intent.putExtra("like", tempQue.getLike());
			                //System.out.println("Run 78here");
			                intent.putExtra("id", questionId.longValue());
			                //System.out.println(questionId);
			                //System.out.println("First click: " + tempQue.getLike());
			                //System.out.println("Run 89here");
			                startActivityForResult(intent, RESULT_CHANGE_LIKE);
			                overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
		            	}
				};
			questionListView.setOnItemClickListener(questionListener);
			//System.out.println("Run here");
		    getTitleQuestions();
		}
	
	
	public void getTitleQuestions() 
		{
			//String url = "http://140.114.86.191:8080/question";
			String url = "http://140.114.200.160:8080/question";
			//String url = "http://qaq-server.appspot.com/question";
			HashMap<String, Object> params = new HashMap<String, Object>(){{put("topic", topic);}};
			//System.out.println("Run here");
	    	RestManager.getObject(url, params, QuestionModel[].class, new RestToolCallbackDelegate()
	    		{
					@Override
					public void success(Object data) 
						{
							QuestionModel[] questions = (QuestionModel[])data;
							questionList.clear();
							//questionData.clear();
							List<QuestionModel> temp = new ArrayList<QuestionModel>();
							for(QuestionModel que : questions)
								{
									temp.add(que);
								}
							Collections.sort(temp, new Comparator<QuestionModel>()
										{
											@Override
											public int compare(QuestionModel que1, QuestionModel que2) 
												{
													return (que2.getLike()-que1.getLike());
									            }
										});
					    	for(QuestionModel que : temp)
					    		{
					    			System.out.println(que.getTitle());
					    			questionList.add(que);
					    		}
									
								
							/*for(QuestionModel que : questions)
								{
									HashMap<String, Object> hashMap = new HashMap<String, Object>();
									hashMap.put("title", que.getTitle());
									hashMap.put("content", que.getContent());
									hashMap.put("speechTitle", que.getspeechTitle());
									hashMap.put("like", que.getLike());
									hashMap.put("id", que.getId());
									questionData.add(hashMap);
								}	*/
							
							SpeechIndexActivity.this.runOnUiThread(new Runnable()
			        			{
				        			@Override
				        			public void run() 
				        				{
				        					adapter.notifyDataSetChanged();
				        					//questionAdapter.notifyDataSetChanged();
					        			}   			
				        		});
						}
					@Override
					public void fail(String msg) 
						{
							System.out.println(msg);
						}
				});
		}
	
	public void postQuestion(String title, String content) 
    	{
    		//String url = "http://140.114.86.191:8080/question";
    		String url = "http://140.114.200.160:8080/question";
    		//String url = "http://qaq-server.appspot.com/question";
			//String url = "http://192.168.1.134:8080/question";
			QuestionModel que = new QuestionModel();
			que.setTitle(title);
			que.setContent(content);
			que.setspeechTitle(topic);
			que.setStamp(System.currentTimeMillis());
			que.setLike(1);
			questionList.add(0, que);
			//System.out.println("Run before postObject.");
			RestManager.postObject(url, que, QuestionModel.class, null, new RestToolCallbackDelegate()
				{
					@Override
					public void success(Object data) 
						{
							//System.out.println("OK");
							System.out.println("result:" + data);
						}
					@Override
					public void fail(String msg) 
						{
							//System.out.println("Not OK");
							System.out.println(msg);
						}
				});
    	}
	public void refreshQuestion(String title, String content, String speechTitle, Long questionid, int like) 
    	{
    		//String url = "http://140.114.86.191:8080/question/refresh";
    		String url = "http://140.114.200.160:8080/question/refresh";
    		//String url = "http://192.168.1.134:8080/question/refresh";
    		//String url = "http://qaq-server.appspot.com/question/refresh";
			// http://192.168.1.134:8080/question/
			QuestionModel que = new QuestionModel();
			que.setTitle(title);
			que.setContent(content);
			que.setspeechTitle(speechTitle);
			System.out.println("Now before set like: " + like);
			que.setLike(like);
			que.setId(questionid.longValue());
			//System.out.println(questionid);
			
			RestManager.postObject(url, que, QuestionModel.class, null, new RestToolCallbackDelegate()
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
			getTitleQuestions();
    	}
	
	
	public void showaddQuestionDialog()
    	{
    		addQuestionDialog = new Dialog(SpeechIndexActivity.this);
	    	addQuestionDialog.setContentView(R.layout.activity_add_question);
	        TextView  askBtn = (TextView) addQuestionDialog.findViewById(R.id.add_question_ask_Button);
	        TextView cancelBtn = (TextView) addQuestionDialog.findViewById(R.id.add_question_cancel_Button);
	        TextView resetBtn = (TextView) addQuestionDialog.findViewById(R.id.add_question_reset_Button);
	        addQuestionDialog.setTitle("Ask your question!!!");
	        final EditText title = (EditText) addQuestionDialog.findViewById(R.id.question_add_TitleEdit);
        	final EditText content = (EditText) addQuestionDialog.findViewById(R.id.question_add_ContentEdit);
        	
	        
	        askBtn.setOnClickListener(new View.OnClickListener() 
	        	{
		            @Override
		            public void onClick(View view) 
		            	{
			            	new Thread()
			            		{
				                	@Override
				                	public void run()
				                		{
				                			String askTitle = title.getText().toString();
				        		            String askContent = content.getText().toString();
				                			//System.out.println(askTitle);
				                			//System.out.println(askContent);
				                			postQuestion(askTitle, askContent);
				                			
					                		SpeechIndexActivity.this.runOnUiThread(new Runnable()
					                			{
						                			@Override
						                			public void run() 
						                				{
						                					adapter.notifyDataSetChanged();
						                					//System.out.println("Run here.");
						                					//questionAdapter.notifyDataSetChanged();
							                			}
					                			});
				                		}
			            		}.start(); 	
			            	addQuestionDialog.dismiss();
		            	}
		        });
	        cancelBtn.setOnClickListener(new View.OnClickListener() 
	        	{
		            @Override
		            public void onClick(View view) 
		            	{
			                // TODO Auto-generated method stub
		            		addQuestionDialog.dismiss();
			            }
		        });
	        resetBtn.setOnClickListener(new View.OnClickListener() 
	        	{
		            @Override
		            public void onClick(View view) 
		            	{
			                // TODO Auto-generated method stub
		            		title.setText("");
		            		content.setText("");
			            }
		        });
	        addQuestionDialog.show();
	    }
	
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) 
		{
			super.onActivityResult(requestCode, resultCode, data);
			if(requestCode==RESULT_CHANGE_LIKE && resultCode==1)
				{
					String questionTitle = data.getStringExtra("title");
					String questionContent = data.getStringExtra("content");
					String questionSpeechTitle = data.getStringExtra("speechTitle");
					Long questionId = data.getLongExtra("id", 0);
					//System.out.println(questionId);
					int like = data.getIntExtra("like", 0);					
					System.out.println("Now onActivityResult: " + like);
					refreshQuestion(questionTitle, questionContent, questionSpeechTitle, questionId, like);			
				}
		}
	
	public void back(View view)
		{
			/*if(which==0)
				{
					Intent intent = new Intent(this, MainActivity.class);
					startActivity(intent);
				}*/
			finish();
			overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
		}
	
	public void info(View view)
		{
			Intent intent = new Intent(SpeechIndexActivity.this, SpeechInfoActivity.class);
			intent.putExtra("topic", topic);
			intent.putExtra("speaker", speaker);
			intent.putExtra("description", description);
			intent.putExtra("latitude", latitude);
			intent.putExtra("longitude", longitude);
			startActivityForResult(intent, RESULT_SPEECH_INFO);
		}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) 
    	{
        // Handle presses on the action bar items
        switch(item.getItemId()) 
	        {
	            case R.id.add_question_icon:
	            	showaddQuestionDialog();
	            	return true;        
	            default:
	                return super.onOptionsItemSelected(item);
	        }
    	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
		{
			// Inflate the menu; this adds items to the action bar if it is
			// present.
			getMenuInflater().inflate(R.menu.speech_index, menu);
			return true;
		}
}