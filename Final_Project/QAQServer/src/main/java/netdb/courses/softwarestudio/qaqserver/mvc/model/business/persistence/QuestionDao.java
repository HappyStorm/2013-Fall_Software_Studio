package netdb.courses.softwarestudio.qaqserver.mvc.model.business.persistence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import netdb.courses.softwarestudio.qaqserver.mvc.ModelAwareServlet;
import netdb.courses.softwarestudio.qaqserver.mvc.model.domain.Question;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

/**
 * Provides a way to access the {@link Question} domain objects and hides the
 * implementation details specific to the underlying datastore.
 */
@SuppressWarnings("serial")
public class QuestionDao extends ModelAwareServlet<Question> 
{
	private static final Log log = LogFactory.getLog(QuestionDao.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		if (log.isDebugEnabled()) 
			log.debug("Getting/listing domain object(s)");
	
		String pathInfoLow = req.getPathInfo();
		String topic = req.getParameter("topic").toLowerCase();
		//System.out.println(topic);
		if(pathInfoLow == null)
			{ 
				//System.out.println("Now in doGet before query.");
				//System.out.println(topic);
				//List<Question> qq = ObjectifyService.ofy().load()
					//	  			.type(Question.class).list();
				Query<Question> askQuery = ObjectifyService.ofy().load()
										  .type(Question.class).
										  filter("speechTitle", topic);
				//List<Question> queList = new ArrayList<Question>();
				/*for(int i=0 ; i<qq.size() ; i++)
					{
						//System.out.println("Question: " + qq.get(i).getspeechTitle());
						//System.out.println(topic);
						if(qq.get(i).getspeechTitle()!=topic)
							{
								System.out.println(qq.get(i).getspeechTitle());
								System.out.println(topic);
								System.out.println();
							}*/
						
						/*if(qq.get(i).getspeechTitle().equals(topic))
							{
								System.out.println("-----------");
								System.out.println(qq.get(i).getTitle());
								System.out.println(qq.get(i).getContent());
								queList.add(qq.get(i));
							}
					}*/
				List<Question> queList = askQuery.list();
				
				for(int i=0 ; i<queList.size() ; i++)
					{
						//String deTitle = URLDecoder.decode(queList.get(i).getTitle(), "UTF-8");
						//String deTContent = URLDecoder.decode(queList.get(i).getContent(), "UTF-8");
						//String deSpeechTitle = URLDecoder.decode(queList.get(i).getspeechTitle(), "UTF-8");
						//queList.get(i).setTitle(deTitle);
						//queList.get(i).setContent(deTContent);
						//queList.get(i).setspeechTitle(deSpeechTitle);
						System.out.println(queList.get(i).getTitle());
						System.out.println(queList.get(i).getContent());
					}
				
				//System.out.println("Now in doGet before sort.");
				Collections.sort(queList, new Comparator<Question>()
							{
								@Override
								public int compare(Question que1, Question que2) 
									{
										return (int) (que2.getStamp()-que1.getStamp());
						            }
							});
				setModel(req, queList);
			} 
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		if (log.isDebugEnabled()) 
			log.debug("Creating a domain object");
		
		//System.out.println("At the beginning of doPost.");
		String pathInfoLow = req.getPathInfo();

		System.out.println("Ready to print pathInfo.");
		System.out.println(pathInfoLow);
		System.out.println("After print pathInfo.");
		Question que = getModel(req);

		//System.out.println("Before if.");
		if(pathInfoLow==null)
			{	
				Question newQue = new Question();
				
				String lowSpeechTitle = que.getspeechTitle().toLowerCase();
				//System.out.println("Get speechTitle: " + lowSpeechTitle);
				
				String lowTitle = que.getTitle().toLowerCase();
				//System.out.println("Get questionTitle: " + lowTitle);
				
				String lowContent = que.getContent().toLowerCase();
				//System.out.println("Get questionContent: " + lowContent);
				long stamp = que.getStamp();
				
				//String enSpeechTitle = URLEncoder.encode(lowSpeechTitle, "UTF-8");
				//String endeTitle = URLEncoder.encode(lowTitle, "UTF-8");
				//String enContent = URLEncoder.encode(lowContent, "UTF-8");
	
				//System.out.println("Get speechTitle: " + lowSpeechTitle);
				//System.out.println("Get questionTitle: " + lowTitle);
				//System.out.println("Get questionContent: " + lowContent);

				newQue.setspeechTitle(lowSpeechTitle);
				newQue.setTitle(lowTitle);
				newQue.setContent(lowContent);
				newQue.setStamp(stamp);
				newQue.setLike(1);
				//System.out.println("Before save entity");
				ObjectifyService.ofy().save().entity(newQue).now();
			}
		else
			{
				Long id = que.getId();
				int newLike = que.getLike();
				
				//System.out.println(id);
				System.out.println("Before find entity");
				
				Question reQue = ObjectifyService.ofy().load().type(Question.class)
						.id(id.longValue()).now();
				
				System.out.println("Old like: " + reQue.getLike());
				System.out.println("After assign entity");
				
				reQue.setLike(newLike);
				System.out.println("New like: " + reQue.getLike());
				ObjectifyService.ofy().save().entity(reQue).now();
			}
	}
}