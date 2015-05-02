package netdb.courses.softwarestudio.qaqserver.mvc.model.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Question
{
	@Id
	Long id;
	
	@Index
	String speechTitle;
	
	String title;
	String content;
	int like;
	long stamp;
	
	public Question() 
	{
		super();
	}

	public Question(String title, String content, String speechTitle) 
	{
		this.title = title;
		this.content = content;
		this.speechTitle = speechTitle;
	}
	public Long getId() 
		{
			return id;
		}
	public void setId(Long id) 
		{
			this.id = id;
		}

	public void setTitle(String title) 
		{
			this.title = title;
		}
	
	public String getTitle() 
		{
			return title;
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