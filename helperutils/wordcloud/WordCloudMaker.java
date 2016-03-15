package eyeofsauron.helperutils.wordcloud;

import java.awt.Color;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpServlet;

import wordcloud.CollisionMode;
import wordcloud.WordCloud;
import wordcloud.WordFrequency;
import wordcloud.bg.*;
import wordcloud.font.scale.SqrtFontScalar;
import wordcloud.image.AngleGenerator;
import wordcloud.nlp.FrequencyAnalyzer;
import wordcloud.palette.ColorPalette;

public class WordCloudMaker {

	static protected String wordCloudDirAddress = 	"wordcloud_images";
	
	public static String createCloud(String text, HttpServlet servlet) throws IOException{
		
		String servletBase = servlet.getServletContext().getRealPath("/");
		
		String wordCloudDir = servletBase+File.separator+wordCloudDirAddress;
	
		File dir = new File(wordCloudDir);
		dir.deleteOnExit();
		dir.mkdir();
System.out.println(dir.getAbsolutePath());
	
		final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
		frequencyAnalyzer.setWordFrequencesToReturn(80);
		frequencyAnalyzer.setMinWordLength(4);
		InputStream stream = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
		final List<WordFrequency> wordFrequencies = frequencyAnalyzer.load(stream);
		
		
	//System.out.println(text);
		final WordCloud wordCloud = new WordCloud(640, 320, CollisionMode.PIXEL_PERFECT);
		wordCloud.setPadding(2);
		wordCloud.setBackground(new RectangleBackground(640, 320));
		wordCloud.setColorPalette(new ColorPalette(new Color(0x4055F1), new Color(0x408DF1), new Color(0x40AAF1), new Color(0x40C5F1), new Color(0x40D3F1), new Color(0x555555)));
		wordCloud.setFontScalar(new SqrtFontScalar(10, 40));
		wordCloud.setAngleGenerator(new AngleGenerator(-60,60,120));
		wordCloud.setBackgroundColor(Color.WHITE);
		wordCloud.build(wordFrequencies);
		
		String filename ="wordCloud_"+text.hashCode()+"_"+".png";

		File image = new File(dir, filename);
		
		image.createNewFile();
		image.deleteOnExit();
		wordCloud.writeToFile(image.getAbsolutePath());


		return wordCloudDirAddress+"/"+image.getName();
		
	}
}