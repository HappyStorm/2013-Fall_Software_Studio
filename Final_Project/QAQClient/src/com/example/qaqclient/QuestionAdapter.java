package com.example.qaqclient;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class QuestionAdapter extends BaseAdapter
{
	private ArrayList<QuestionModel> list;
	private LayoutInflater myInflater;
	
	public QuestionAdapter(Context c, ArrayList<QuestionModel> list)
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
	public void setList(ArrayList<QuestionModel> list)
		{
			this.list = list;
		}
	public View getView(int position, View convertView, ViewGroup parent) 
		{
			// TODO Auto-generated method stub
			convertView = myInflater.inflate(R.layout.question_item, null);
			QuestionModel que = list.get(position);		
			TextView titleView = (TextView) convertView.findViewById(R.id.question_item_Title);
			TextView contentView = (TextView) convertView.findViewById(R.id.question_item_Content);
			TextView likeView = (TextView) convertView.findViewById(R.id.question_item_like_num);
			TextView likeTitleView = (TextView) convertView.findViewById(R.id.question_item_Like);
			
			titleView.setText(que.getTitle());
			contentView.setText(que.getContent());
			likeView.setText(Integer.toString(que.getLike()));
			
			return convertView;
		}
}