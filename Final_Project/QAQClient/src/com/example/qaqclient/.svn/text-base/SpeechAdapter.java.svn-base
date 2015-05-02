package com.example.qaqclient;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SpeechAdapter extends BaseAdapter
{
	private ArrayList<SpeechModel> list;
	private LayoutInflater myInflater;
	
	public SpeechAdapter(Context c, ArrayList<SpeechModel> list)
		{
			this.list = list;
			myInflater = LayoutInflater.from(c);
		}
	public int getCount() 
		{
			// TODO Auto-generated method stub
			return list.size();
		}
	public Object getItem(int position) 
		{
			// TODO Auto-generated method stub
			return list.get(position);
		}
	public long getItemId(int position) 
		{
			// TODO Auto-generated method stub
			return position;
		}
	public void setList(ArrayList<SpeechModel> list)
		{
			this.list = list;
		}
	public View getView(int position, View convertView, ViewGroup parent) 
		{
			// TODO Auto-generated method stub
			convertView = myInflater.inflate(R.layout.speech_item, null);
			SpeechModel spe = list.get(position);		
			TextView topicView = (TextView) convertView.findViewById(R.id.speech_item_Topic);
			TextView descriptionView = (TextView) convertView.findViewById(R.id.speech_item_Description);
			TextView distance = (TextView) convertView.findViewById(R.id.speech_item_Distance);
			TextView distanceNum = (TextView) convertView.findViewById(R.id.speech_item_distance_num);
			TextView date = (TextView) convertView.findViewById(R.id.speech_item_Date);
			TextView dateNum = (TextView) convertView.findViewById(R.id.speech_item_date_num);
			
			topicView.setText(spe.getTopic());
			descriptionView.setText(spe.getDescription());
			dateNum.setText(spe.getDate());
			if(spe.getDistance()>1000.0)
				{
					double kmDis = spe.getDistance() / 1000.0 ;
					distanceNum.setText(Double.toString(kmDis) + "km");
				}
			else
				distanceNum.setText(Double.toString(spe.getDistance()) + "m");
			
			return convertView;
		}
}