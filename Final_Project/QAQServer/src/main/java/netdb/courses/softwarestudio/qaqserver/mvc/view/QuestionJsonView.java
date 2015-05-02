package netdb.courses.softwarestudio.qaqserver.mvc.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import netdb.courses.softwarestudio.qaqserver.mvc.ModelAwareServlet;
import netdb.courses.softwarestudio.qaqserver.mvc.model.domain.Question;
import netdb.courses.softwarestudio.qaqserver.service.json.JsonService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("serial")
public class QuestionJsonView extends ModelAwareServlet<Question>
{
	private static final Log log = LogFactory.getLog(QuestionJsonView.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException 
	{
		if(log.isDebugEnabled())
			log.debug("Responsing 200 OK");
		
		Object que = getModel(req); // que can be a Question object or a list of Question objects
		resp.setContentType("application/json");
		resp.setHeader("Cache-Control", "no-cache"); // make sure no intermediate node caches the result
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().print(JsonService.serialize(que));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		if(log.isDebugEnabled()) 
			log.debug("Responsing 201 Created");
		
		Question que = (Question) getModel(req);
		resp.setStatus(201);
		resp.getWriter().print("{id: " + que.getId() + ",TimeStamp: " + que.getStamp() + "}");
		resp.setHeader("Location", req.getAttribute("javax.servlet.forward.request_uri")
				+ "/" + que.getId());
	}
}