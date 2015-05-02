package com.example.qaqclient;

public class SpeechModel
{
	private String topic;
	private String speaker;
	private String description;
	private String date;
	private double latitude;
	private double longitude;
	private double distance;
	long stamp;
	
	public SpeechModel() 
		{
			super();
		}
	public SpeechModel(String topic, String description) 
		{
			this.topic = topic;
			this.description = description;
		}
	public String getTopic() 
		{
			return topic;
		}
	public void setTopic(String topic) 
		{
			this.topic = topic;
		}
	public String getSpeaker() 
		{
			return speaker;
		}
	public void setSpeaker(String speaker) 
		{
			this.speaker = speaker;
		}
	public String getDescription() 
		{
			return description;
		}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	public String getDate() 
		{
			return date;
		}

	public void setDate(String date) 
		{
			this.date = date;
		}
	public double getLatitude() 
		{
			return latitude;
		}
	public void setLatitude(double latitude) 
		{
			this.latitude = latitude;
		}
	
	public double getLongitude() 
		{
			return longitude;
		}
	public void setLongitude(double longitude) 
		{
			this.longitude = longitude;
		}
	public double getDistance() 
		{
			return distance;
		}
	public void setDistance(double distance) 
		{
			this.distance = distance;
		}
	
	public long getStamp()
		{
			return stamp;
		}

	public void setStamp(long stamp) 
		{
			this.stamp = stamp;
		}
}