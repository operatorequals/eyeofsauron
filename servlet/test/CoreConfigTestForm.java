package eyeofsauron.servlet.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;


import eyeofsauron.controllers.CoreArgument;
import eyeofsauron.controllers.CoreResult;
import eyeofsauron.controllers.impl.simple.*;
import eyeofsauron.domain.utils.arguments.UtilityArgumentException;


/**
 * Servlet implementation class SentTestForm
 */
@WebServlet("/CoreConfigTestForm")
public class CoreConfigTestForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SimpleCoreController controller;
	protected String defaultText;
	protected String htmlForm1 = "<form action=\"CoreConfigTestForm\" method=\"GET\">\n" + 
			"Query to Analyze:<br>\n" + 
			"<input type=\"textarea\" name=\"text\" value=\"grexit\">\n<br>\n"+
			"<input type=\"submit\" value=\"Submit\">\n" + 
			"</form> ";
	/**
     * @see HttpServlet#HttpServlet()
     */
    public CoreConfigTestForm() {
        super();
        
        try {
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void init(){
		try {
			controller = new SimpleCoreController();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		out.flush();
		out.append("<head>"+ 
				"</head><body>");
		

		out.append(htmlForm1);
		
		
		String text = request.getParameter("text");
		if (text == null ){
			out.append("</body>");
			return;
		};
//		doGet(request, response);

		out.append("<p>");
		out.append("<p>");
		out.append("The result is:");
		out.append("<p>");
		out.append("<p>");
		try {

			CoreArgument arg = new CoreArgument();
			arg.addArgument(text);
			
			CoreResult res =  controller.process(arg);
			out.append("<json>");
			String escapedText = StringEscapeUtils.escapeHtml(res.toJson());
			out.println(escapedText );
//			System.out.println(res.toJson());
			out.append("</json>");
		} catch (UtilityArgumentException e) {
			e.printStackTrace();
		}
		out.append("</body>");

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String json = request.getParameter("config");
		SimpleCoreConfig conf = new SimpleCoreConfig (json);
		controller.configure(conf);
		System.out.println("configuration: " +json+"\n accepted!");
	}

}
