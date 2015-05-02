package netdb.courses.softwarestudio.qaqserver.mvc.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import netdb.courses.softwarestudio.qaqserver.mvc.model.domain.Question;
import netdb.courses.softwarestudio.qaqserver.service.json.JsonService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("serial")
public class QuestionController extends ResourceController<Question>
{
	private static final Log log = LogFactory.getLog(QuestionController.class);
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException 
	{
		// invoke business logics
		if(log.isDebugEnabled()) 
			log.debug("Invoking business logics");
		
		include(req, resp, "/model/business/persistence/question-dao");
		
		// dispatch to view
		if(log.isDebugEnabled()) 
			log.debug("Dispatching to view");
		
		if(!req.getHeader("Accept").contains("application/json")) 
		{
			forward(req, resp, "/view/406-not-acceptable-view");
			return;
		}
		forward(req, resp, "/view/question-json-view");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		// setup model in request
		try
			{
				if(log.isDebugEnabled()) 
					log.debug("Setting up model in request");
				
				String body = getRequestBody(req);
				Question que = JsonService.deserialize(body, Question.class);
				//System.out.println("Run here.");
				setModel(req, que);
			} 
		catch(Exception e) 
			{
				forward(req, resp, "/view/400-bad-request-view");
				
				if(log.isInfoEnabled()) 
					log.info("Bad request: " + e.getMessage());
					
				return;
			}
		
		// invoke business logics
		if(log.isDebugEnabled()) 
			log.debug("Invoking business logics");
		
		include(req, resp, "/model/business/persistence/question-dao");
					//		/model/business/persistence/question-dao"
		// dispatch to view
		if(log.isDebugEnabled()) 
			log.debug("Dispatching to view");
		
		forward(req, resp, "/view/question-json-view");
	}
}