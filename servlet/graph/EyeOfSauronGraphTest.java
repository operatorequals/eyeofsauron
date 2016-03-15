package eyeofsauron.servlet.graph;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
//import com.google.gson.JsonElement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import eyeofsauron.controllers.impl.simple.SimpleCoreConfig;
import eyeofsauron.controllers.impl.simple.SimpleCoreController;
import eyeofsauron.coreutils.sentimentanalysis.SentimentAnalyzerImpl;
import eyeofsauron.helperutils.wordcloud.WordCloudMaker;
import eyeofsauron.servlet.graph.jswrappers.*;


/**
 * Servlet implementation class EyeOfSauronServlet
 */
@WebServlet("/EyeOfSauronGraphTest")
public class EyeOfSauronGraphTest extends HttpServlet {
	private static final long serialVersionUID = 2L;
	private WebDrawer jsDrawer;
	
	private final String apihost= "http://localhost:8080/EyeOfSauronAPI"; 
	private final String apiURL = "/EyeOfSauronAPI";
	
	protected String htmlForm1 = "<form action=\"EyeOfSauronGraphTest\" method=\"GET\">\n" + 
			"Query to Analyze:<br>\n" + 
			"<input type=\"textarea\" name=\"text\" value=\"grexit\"> </input><br>\n"+
			"<input type=\"submit\" value=\"Submit\"> </input>" + 
			"</form> ";

	protected String htmlForm2 = "<form action=\"EyeOfSauronGraphTest\" method=\"POST\">\n" + 
			"<select  name=\"language\">"+
			"  <option value=\"English\">English</option>\n" + 
			" <option value=\"Greek\">Greek</option>\n" + 
			"  <option value=\"German\">German</option>\n" + 
			"</select><br>"+
			
			"<select  name=\"country\">"+
			"  <option value=\"united states\">USA</option>\n" + 
			" <option value=\"Greece\">Greece</option>\n" + 
			"  <option value=\"Germany\">Germany</option>\n" + 
			"</select><br>"+
			
			"<select  name=\"algorithm\">"+
			" <option value=\"lingo\">Lingo</option>\n" + 
			"  <option value=\"stc\">STC</option>\n" + 
			"  <option value=\"k_means\">K-Means</option>\n" + 
			"</select><br>"+
			"<input type=\"textfield\" name=\"results\" value=\"36\"><br>"+
			"<input type=\"textarea\" name=\"apiKey\" value=\"etTFY9Kp+pkKA/oD/m1aaSkNcHpBwqElGgeM2IaC4+8\"><br>"+
			"<input type=\"submit\" value=\"Submit\"> </input>" + 
			"</form> ";
	
	
	String css = "@media print {\n" + 
			"  table { \n" + 
//			"    page-break-before: always;\n" + 
			"	page-break-inside : avoid\n" +
			"  }"+
//			"  tr { \n" + 
////			"    page-break-before: always;\n" + 
//			"	page-break-inside : avoid\n" +
//			"  }"+
			"visualise { \n"+
			"    page-break-before: always;\n" + 
			"	page-break-inside : avoid\n" +
			"}\n";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EyeOfSauronGraphTest() {
        super();

        jsDrawer = new ChartKickJsWrapper();

    }



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("UTF8");
		PrintWriter out = response.getWriter();
//		<script src=\"https://ssl.google-analytics.com/ga.js\" async=\"\" type=\"text/javascript\"></script>

		out.append("<html><head>"+ChartKickJsWrapper.importChartKick()+"\n");
		out.println("<style>"+css+"</style>\n");
		out.println("</head>\n");
		out.append("<body>");
//System.out.println(response.getCharacterEncoding());
		out.append("<table><tr><td width=\"20%\">"+htmlForm1+"</td>");
		out.append("<td width=\"20%\">"+htmlForm2+"</td></tr></table>");
		
		String text = request.getParameter("text");
		if (text == null ){
			out.append("</body>");
			return;
		}
		String scrape = request.getParameter("scrape");
		boolean scrapeContent = false;
		if (scrape != null)
		scrapeContent = Boolean.parseBoolean(scrape);
		
//		out.println("<a href=\""+apihost+"/EyeOfSauronGraphTest\">Next Query</a>");
		out.println("<visualise>");

		Document doc = Jsoup.connect(apihost+apiURL+"?text="+text).get();
		Elements json = doc.select("json");

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonResults = null;
        try {
			jsonResults = (JSONObject) jsonParser.parse(json.text());
		} catch (ParseException e) {
			e.printStackTrace();
			return;
		}

		out.append("<p>");
		out.append("<p>");

		String coreDescription= (String) jsonResults.get("description");
		String coreSource = (String) jsonResults.get("source");

		out.append("<h2>"+ coreDescription+"</h2>");
		out.append("<h1>\""+ coreSource+"\"</h1>");
		out.append("<p>");

		JSONArray coreData  = (JSONArray) jsonResults.get("data");
		Map<Object,Double>  tempValueMap = new HashMap<Object,Double> ();
//		Set<Map.Entry<String,JsonElement>> tempSetValues;
		
		for (int i = 0; i < coreData.size(); i++){
			
			out.println("<div><table border=\"1\"><tr><td>");
			String scrappedText = "";
			JSONObject temp = (JSONObject) coreData.get(i);
			JSONObject data = (JSONObject) temp.get("data");
			JSONObject desc = (JSONObject) temp.get("desc");
			
			JSONObject values = (JSONObject) data.get("values");
			String classType = (String) data.get("classType");
			String title = (String) data.get("title");
			out.append("<h3>"+title+"</h3>");
			
			if (classType.equals("eyeofsauron.coreutils.sentimentanalysis.SentimentAnalysisResult")){
				out.println("</td><td>");
				out.append(jsDrawer.drawPieChart((HashMap<String,Long>) values.clone()));
				out.println("</td>");
				JSONArray analyticData = (JSONArray) data.get("data");
				JSONObject inAnalyticData;
				String dataDesc;
				String dataDescAn[];
				
				Triple <String, Double, Double> []series =  new Triple [analyticData.size()];

				out.println("</tr><tr><td width=\"50%\">");
				for (int j = 0; j<analyticData.size(); j++){
					inAnalyticData = (JSONObject) analyticData.get(j);
					dataDesc = ((String) inAnalyticData.get("description")); 
					dataDescAn = dataDesc.split(SentimentAnalyzerImpl.descSeparator);
					out.println("<div>");
					out.println("<b>"+dataDescAn[0]+"</b><br>");
					out.println(dataDescAn[1]+"<br>");
					out.println("<a href=\""+dataDescAn[2]+"\">"+dataDescAn[2]+"</a><p>");
					out.println("</div>");

					JSONArray dataNumbersJ = (JSONArray) inAnalyticData.get("data");

					Triple <String, Double, Double> tempTriple = Triple.of(Integer.toString(j), (double) dataNumbersJ.get(0), (double)dataNumbersJ.get(1));
					series[j] = tempTriple;
					if (!scrapeContent)
						scrappedText += dataDescAn[1]+" ";
					else{
						try{
							scrappedText += Jsoup.connect(dataDescAn[2]).get().text()+" ";
						}catch(Exception e){System.err.println(e);}

					}
				}
				
				out.println("</td><td width=\"50%\">");
				out.append(jsDrawer.drawMultiSeriesBar(Pair.of("Positive", "Negative"), series));
				out.println("</td></tr>");

				String wordCloudImageTag = "<img align=\"center\" src=\""+this.getServletContext().getContextPath()+"/"+(WordCloudMaker.createCloud(scrappedText, this))+"\"></img>";
				out.println("<tr><td>");
				out.println(wordCloudImageTag);
				out.println("</td></tr></table><p><p>");

			}
			out.append("</div><p><p>");
		}
		
		out.println("</visualise>");

		out.append("</body></html>");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String language = request.getParameter("language");
		String country = request.getParameter("country");
		String results = request.getParameter("results");
		String algorithm = request.getParameter("algorithm");
		String apikey = request.getParameter("apiKey");
		
		HashMap <String,String> par = new HashMap <String,String> ();

		par.put("language", language);
		par.put("country", country);
		par.put("results", results);
		par.put("algorithm", algorithm);
		par.put("apiKey", apikey);

		JSONObject json = new JSONObject();
		json.putAll(par);
		Document doc = Jsoup.connect(apihost+apiURL).data("config",json.toJSONString()).post();
		doGet(request, response);
	}

	
}
