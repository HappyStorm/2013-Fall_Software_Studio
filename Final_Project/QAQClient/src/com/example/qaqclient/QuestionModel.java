package com.example.qaqclient;


public class QuestionModel
{
	private Long id;
	private String title;
	private String content;
	private String speechTitle;
	private int like;
	private long stamp;

	public QuestionModel() 
		{
			super();
		}
	public QuestionModel(String title, String content) 
		{
			this.title = title;
			this.content = content;
		}
	public Long getId() 
		{
			return id;
		}
	public void setId(Long id) 
		{
			this.id = id;
		}
	
	public String getTitle() 
		{
			return title;
		}
	public void setTitle(String title) 
		{
			this.title = title;
		}
	public String getContent() 
		{
			return content;
		}
	public void setContent(String content) 
		{
			this.content = content;
		}	
	public void setspeechTitle(String speechTitle)
		{
			this.speechTitle = speechTitle;
		}
	public String getspeechTitle()
		{
			return speechTitle;
		}
	public int getLike() 
		{
			return like;
		}
	public void setLike(Integer like) 
		{
			this.like = like;
		}
	public void setLikeUp() 
		{
			this.like = this.like++;
		}
	public void setLikeDown() 
		{
			this.like = this.like--;
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