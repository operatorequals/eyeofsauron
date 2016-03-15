package eyeofsauron.servlet.graph.jswrappers;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

public final class ChartKickJsWrapper implements WebDrawer{

	private final String[] colors = {"red","yellow","green"};
	
	public int pixels = 300;

	private static int counter = 0;
	
	public static String importChartKick(){
		return "<script src=\"http://www.google.com/jsapi\"></script>\n" + 
				"    <script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js\"></script>\n" + 
				"<script src=\"chartkick.js\"></script>";
	}

	@Override
	public String  drawPieChart(Triple<String, Double, Color> [] data){
		int chartNum = counter++;
		String chartName = "chart-"+chartNum;

		String html = "<div id=\""+chartName+"\" style=\"height: 300px;\"></div>\n" ;

		html += "<script>\n"+

		"      new Chartkick.PieChart(\""+chartName+"\",[";

		String colorOpt = "{\"colors\":[";
		
		for (Triple<String, Double, Color>  entry : data){

			html += "[\""+entry.getLeft()+"\","+entry.getMiddle();

			Color color = entry.getRight();
			if (color == null)
				switch((entry.getLeft().toString())){
				case "Positive":
					colorOpt+= "\"green\"";
					break;
				case "Negative":
					colorOpt+= "\"red\"";
					break;
				case "Neutral":
					colorOpt+= "\"orange\"";
					break;
				default:
					break;
				}
			else{
				String hex = String.format("#%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
				colorOpt+= "\""+ hex +"\"";
			}
				
			colorOpt += ",";
			html+= "],";
		}
		html = html.substring(0, html.length()-1);	// delete last comma
		colorOpt= colorOpt.substring(0, colorOpt.length()-1);	// delete last comma
		colorOpt+="]}";
		html += "],"+colorOpt+");";
//		+ "\"chart-2\", [[\"Blueberry\",44],[\"Strawberry\",23],[\"Banana\",22],[\"Apple\",21],[\"Grape\",13]]);"
		
		return html+"\n</script>";
		
	}

	
	public String drawMultiSeriesBar(Pair<String, Double>[] data1, Pair<String, Double>[] data2, double max){	//TODO fix colors
		
		int chartNum = counter++;
		String chartName = "chart-"+chartNum;

		String html = "<div id=\""+chartName+"\" style=\"height: "+125*Math.max(data1.length,data2.length)+"px; width: "+3*256+";\"></div>\n" ;

		html += "<script>\n";
		
		html += "new Chartkick.BarChart(\""+chartName+"\", \n";
		html += "[{name: \"Series A\", data: [";
		for (Pair<String, Double> d: data1 ){

			html += "[\""+d.getLeft()+"\", "+Math.abs(d.getRight())+"],";
//			TODO bar graph for results
		}
		html = html.substring(0,html.length()-1);
		html += "]},\n {name: \"Series B\", data: [";
		
		for (Pair<String, Double> d: data2 ){

			html += "[\""+d.getLeft()+"\", "+Math.abs(d.getRight())+"],";
//			TODO bar graph for results
		}
		
		html = html.substring(0,html.length()-1);
		html += "]}],\n {max: \""+ max +"\", colors:[\"green\",\"red\"]});";

		return html+"\n</script>";

	}
//    <h1>Multiple Series Bar Stacked</h1>
//    <div id="chart-104" style="height: 300px;"></div>
//    <script>
//      new Chartkick.BarChart("chart-104", [{name: "Series A", data: [["0",32],["1",46],["2",28],["3",21],["4",20],["5",13],["6",27]]}, {name: "Series B", data: [["0",32],["1",46],["2",28],["3",21],["4",20],["5",13],["6",27]]}], {max: 80, stacked: true});
//    </script>

	@Override
	public String drawChronicityChart(Pair<Date, Double>[] data) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String drawMultiSeriesBar(Pair<String, Double>[] data1, Pair<String, Double>[] data2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String drawMultiSeriesBar(Pair<String, String> headers, Triple<String, Double, Double>[] data) {
		
		Pair<String, Double>[] data1 = new Pair[data.length];
		Pair<String, Double>[] data2 = new Pair[data.length];
		int i = 0;
		double max = data[0].getMiddle();
		for (Triple<String, Double, Double> triple : data){
			data1 [i] = Pair.of(triple.getLeft(), triple.getMiddle());
			data2 [i] = Pair.of(triple.getLeft(), triple.getRight());
			
			if (max < Math.abs(triple.getMiddle()))
				max = Math.abs(triple.getMiddle());
			if (max < Math.abs(triple.getRight()))
				max = Math.abs(triple.getRight());
			i++;

		}
			return drawMultiSeriesBar(data1,data2,(int) max+max*0.2);
	}

	@Override
	public String drawPieChart(Map<String, Long> data) {

		Triple<String,Double,Color> [] triples = new Triple[data.size()];
		int i = 0;
		for (Map.Entry<String, Long> entry : data.entrySet()){
			triples[i++] = Triple.of(entry.getKey(), (double) entry.getValue(), null);
		}
		return drawPieChart(triples);
	}

}
