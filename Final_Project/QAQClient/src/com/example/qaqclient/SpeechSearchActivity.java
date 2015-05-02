package com.example.qaqclient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;


public class SpeechSearchActivity extends Activity
{
	private ArrayList<SpeechModel> speechList = new ArrayList<SpeechModel>();
	//private ArrayList<Double> disList = new ArrayList<Double>();
	private ListView speechListView;
	private SpeechAdapter adapter;
	private EditText speechTopicInput;
	
	private String speechTopic;

	private Double latitude;
	private Double longitude;
	private Dialog searchWayDialog;
	
	private RadioGroup sortGroup;
	private RadioGroup listGroup;
	private RadioButton radSortByDistance;
	private RadioButton radSortByDate;
	private RadioButton radSortByTopicName;
	private RadioButton radListByAscend;
	private RadioButton radListByDescend;
	
	private int sortway = 1;
	private int listway = 1;
	private final double EARTH_RADIUS = 6378137.0;
	
	private String lati;
	private String longi;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_speech_search);
			
			
			speechListView = (ListView) findViewById(R.id.speech_search_speechListView);
			speechTopicInput = (EditText) findViewById(R.id.speech_search_speechNameInput);
			Intent newIntent = getIntent();
			latitude = newIntent.getDoubleExtra("latitude", 0.0);
			longitude = newIntent.getDoubleExtra("longitude", 0.0);	
			lati = latitude.toString();
			longi = longitude.toString();
			
			//System.out.println("Run here.");		
	        //System.out.println("Run here.");
			//System.out.println("Run here.");
			System.out.println("Run 12here.");
			adapter = new SpeechAdapter(this, speechList); 
			System.out.println("Run 34here.");
			speechListView.setAdapter(adapter);
			System.out.println("Run 56here.");
			//touchItemClickListener();
			//System.out.println("Run here just after touchItemClickListener");
			
			ListView.OnItemClickListener speechListener = new ListView.OnItemClickListener()
				{
					@Override
		            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		            	{
		            		Intent intent = new Intent(SpeechSearchActivity.this, SpeechIndexActivity.class);
			                SpeechModel tempSpe = speechList.get(position);
			                intent.putExtra("topic", tempSpe.getTopic());
			                intent.putExtra("speaker", tempSpe.getSpeaker());
			                intent.putExtra("description", tempSpe.getDescription());
			                intent.putExtra("latitude", tempSpe.getLatitude());
			                intent.putExtra("longitude", tempSpe.getLongitude());
			                intent.putExtra("which", 1);
			                //intent.putExtra("topic", speechListView.getSelectedItem().toString().toLowerCase());
			                startActivity(intent);
			                overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
		            	}
				};
			speechListView.setOnItemClickListener(speechListener);
			System.out.println("Run here.");
			//Toast.makeText(getBaseContext(), title, Toast.LENGTH_LONG).show();
			getSpeeches();
		}
	
	private void getSpeeches() 
		{
			//String url = "http://140.114.86.191:8080/speech/";
			String url = "http://140.114.200.160:8080/speech/";
			//String url = "http://qaq-server.appspot.com/speech/";
			//My room IP:     http://140.114.200.160:8080/speech/ 
			//My final IP:    http://qaq-server.appspot.com/speech/
			//CS NO.016474 IP:http://140.114.86.191:8080/speech/
			//Jimmy's room IP:http://192.168.1.134:8080/speech/
			
			HashMap<String, Object> params = new HashMap<String, Object>()
												{{
													put("sort", sortway); 
													put("list", listway);
													put("date", System.currentTimeMillis());
													put("lati", lati);
													put("longi", longi);}};
			RestManager.getObject(url, params, SpeechModel[].class, new RestToolCallbackDelegate()
	    		{
					@Override
					public void success(Object data) 
						{
							SpeechModel[] speeches = (SpeechModel[])data;
							speechList.clear();
							
							for(SpeechModel spe : speeches)
								speechList.add(spe);
							
							for(SpeechModel spe : speeches)
								{
									SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
									double dis = gpsDistance(latitude, longitude, spe.getLatitude(), spe.getLongitude());
									spe.setDistance(dis);
									String tempDate = dateFormater.format(spe.getStamp());
									spe.setDate(tempDate);
								}
							SpeechSearchActivity.this.runOnUiThread(new Runnable()
			        			{
				        			@Override
				        			public void run() 
				        				{
				        					adapter.notifyDataSetChanged();
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
	
	public void find(View view)
		{
			speechTopic = speechTopicInput.getText().toString();
			if(speechTopic!=null)
				{
					//System.out.println(speechTopic);
					//String url = "http://140.114.200.160:8080/speech/";
					String url = "http://qaq-server.appspot.com/speech/";
					//			  http://qaq-server.appspot.com/speech/
					HashMap<String, Object> params = new HashMap<String, Object>()
														{{
															put("sort", 0); 
															put("list", 1);
															put("date", System.currentTimeMillis());
															put("topic", speechTopic);
															put("lati", lati);
															put("longi", longi);}};
			    	RestManager.getObject(url, params, SpeechModel[].class, new RestToolCallbackDelegate()
			    		{
							@Override
							public void success(Object data) 
								{
									SpeechModel[] speeches = (SpeechModel[])data;
									speechList.clear();
									
									for(SpeechModel spe : speeches)
										speechList.add(spe);
									
									SpeechSearchActivity.this.runOnUiThread(new Runnable()
					        			{
						        			@Override
						        			public void run() 
						        				{
						        					adapter.notifyDataSetChanged();
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
			else
				{
					speechList.clear();
					SpeechSearchActivity.this.runOnUiThread(new Runnable()
	        			{
		        			@Override
		        			public void run() 
		        				{
		        					adapter.notifyDataSetChanged();
			        			}
		        		});
				}
			
		}
	
	public void showSearchWayDialog()
    	{
    		sortway=1;
    		listway=1;
    		searchWayDialog = new Dialog(SpeechSearchActivity.this);
	    	searchWayDialog.setContentView(R.layout.speech_search_choose);
	    	
	    	TextView searchBtn = (TextView) searchWayDialog.findViewById(R.id.speech_search_choose_Search);
	        TextView cancelBtn = (TextView) searchWayDialog.findViewById(R.id.speech_search_choose_Cancel);
	        sortGroup = (RadioGroup) searchWayDialog.findViewById(R.id.sort_Group);
	        listGroup = (RadioGroup) searchWayDialog.findViewById(R.id.list_Group);
	        
	        radSortByDistance = (RadioButton) searchWayDialog.findViewById(R.id.sort_by_Distance);
	        radSortByDate = (RadioButton) searchWayDialog.findViewById(R.id.sort_by_Date);
	        radSortByTopicName = (RadioButton) searchWayDialog.findViewById(R.id.sort_by_TopicName);
	        radListByAscend = (RadioButton) searchWayDialog.findViewById(R.id.list_by_Ascend);
	        radListByDescend = (RadioButton) searchWayDialog.findViewById(R.id.list_by_Descend);
	        searchWayDialog.setTitle("Chose the way to search");
	        
	        //System.out.printf("Before Listener sortway: %d \n",sortway);
			//System.out.printf("Before Listener listway: %d \n", listway);
	       
	        RadioGroup.OnCheckedChangeListener sortGroupListener = new RadioGroup.OnCheckedChangeListener()
	    		{
	    			@Override
	    			public void onCheckedChanged(RadioGroup group, int checkedId)			
	    				{
	    					//System.out.println("Before switch");
	    					// TODO Auto-generated method stub
	    					int i = group.getCheckedRadioButtonId();
	    					//System.out.printf("Sort'checkedId: %d \n", checkedId);
	    					switch(i)
	    						{
	    							case R.id.sort_by_Distance:
		    						{
		    							sortway = 1;
		    							//System.out.printf("After press, sort: %d\n",sortway);
		    							break;
		    						}
	    							case R.id.sort_by_Date:
	    							{
	    								sortway = 2;
	    								//System.out.printf("After press, sort: %d\n",sortway);
	    								break;
	    							}
	    								
	    							case R.id.sort_by_TopicName:
	    							{
	    								sortway = 3;
	    								//System.out.printf("After press, sort: %d\n",sortway);
	    								break;
	    							}
	    								
	    							default:
	    								break;
	    						}	
	    				}
	    		};
	    		
	    	RadioGroup.OnCheckedChangeListener listGroupListener = new RadioGroup.OnCheckedChangeListener()
	    		{
	    			@Override
	    			public void onCheckedChanged(RadioGroup group, int checkedId)			
	    				{
	    					// TODO Auto-generated method stub
	    					int i = group.getCheckedRadioButtonId();
	    					//System.out.printf("List'checkedId: %d \n",checkedId);
	    					switch(i) 
	    						{
	    							case R.id.list_by_Ascend:
	    							{
		    							listway = 1;
		    							//System.out.printf("After press, list: %d\n", listway);	    							
		    							break;
	    							}
	    							case R.id.list_by_Descend:
	    							{
		    							listway = 2;
		    							//System.out.printf("After press, list: %d\n", listway);	    							
		    							break;
	    							}			
	    						}
	    				}		
	    		};
	        sortGroup.setOnCheckedChangeListener(sortGroupListener);
			listGroup.setOnCheckedChangeListener(listGroupListener);
			
			searchBtn.setOnClickListener(new View.OnClickListener() 
	        	{
		            @Override
		            public void onClick(View view) 
		            	{
			            	new Thread()
			            		{
				                	@Override
				                	public void run()
				                		{
				                			//System.out.printf("Before GET sortway: %d \n",sortway);
					    					//System.out.printf("Before GET listway: %d \n", listway);

				                			getSpeeches();
				                			
					                		SpeechSearchActivity.this.runOnUiThread(new Runnable()
					                			{
						                			@Override
						                			public void run() 
						                				{
						                					adapter.notifyDataSetChanged();
							                			}
					                			});
				                		}
			            		}.start(); 	
			            	searchWayDialog.dismiss();
		            	}
		        });
	        cancelBtn.setOnClickListener(new View.OnClickListener() 
	        	{
		            @Override
		            public void onClick(View view) 
		            	{
			                // TODO Auto-generated method stub
		            		searchWayDialog.dismiss();
			            }
		        });
	        searchWayDialog.show();
	    }
	
	public void back(View view)
		{
			finish();
			overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
		}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
		{
		  // Handle presses on the action bar items
		  switch(item.getItemId()) 
		  		{
		  			case R.id.search_icon:
		  			{
		  				//System.out.println("Run here.");
		  				showSearchWayDialog();
		  			}
		  				
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
			getMenuInflater().inflate(R.menu.speech_search, menu);
			return true;
		}
	
	private double gpsDistance(double lat_a, double long_a, double lat_b, double long_b) 
		{
			double radlat_a = (lat_a * Math.PI / 180.0);
			double radlat_b = (lat_b * Math.PI / 180.0);
			double dif_lat = radlat_a - radlat_b;
			double dif_long = (long_a - long_b) * Math.PI / 180.0;
			double dis = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(dif_lat / 2), 2)+ Math.cos(radlat_a) * Math.cos(radlat_b)
			* Math.pow(Math.sin(dif_long / 2), 2)));
			dis = dis * EARTH_RADIUS;
			dis = Math.round(dis * 10000) / 10000;
			return dis;
		}
}