package eyeofsauron.servlet.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;

import com.sun.org.apache.xml.internal.serialize.Printer;

import eyeofsauron.coreutils.searchengine.MetaSearchEngineImpl;
import eyeofsauron.coreutils.searchengine.SearchEngineArgument;
import eyeofsauron.coreutils.searchengine.SearchEngineResult;
import eyeofsauron.coreutils.sentimentanalysis.*;
import eyeofsauron.domain.utils.arguments.UtilityArgumentException;

/**
 * Servlet implementation class SentTestForm
 */
@WebServlet("/SearchTestForm")
public class SearchTestForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MetaSearchEngineImpl searchEngine;
	protected String defaultText;
	protected String htmlForm1 = "<form action=\"SearchTestForm\" method=\"POST\">\n" + 
			"Text to Search:<br>\n" + 
			"<input type=\"text\" name=\"text\" value=\"\">\n" + 
			"<br>\n" + 

			"<input type=\"submit\" value=\"Submit\">\n" + 
			"</form> ";
	/**
     * @see HttpServlet#HttpServlet()
     */
    public SearchTestForm() {
        super();
        searchEngine = new MetaSearchEngineImpl ();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.flush();
		out.append("<head></head><body>");
		out.append(htmlForm1);
		
		out.append("</body>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String text = request.getParameter("text");
		;
		doGet(request, response);
		PrintWriter out = response.getWriter();
		out.append("<p>");
		out.append("<p>");
		out.append("The result is:");
		out.append("<p>");
		out.append("<p>");
		try {
			SearchEngineResult res = searchEngine.process(new SearchEngineArgument(text));
			out.append("<json>");
			String escapedText = StringEscapeUtils.escapeHtml(res.toJson());
			out.println(escapedText );
			System.out.println(res.toJson());
			out.append("</json>");
		} catch (UtilityArgumentException e) {
			e.printStackTrace();
		}
	}

}
