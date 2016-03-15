/**
 * 
 */
package eyeofsauron.coreutils.sentimentanalysis;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.carrot2.core.Cluster;
import org.carrot2.core.Document;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;

import org.carrot2.core.*;

import eyeofsauron.domain.utils.Utility;
import eyeofsauron.domain.utils.UtilityIdentity;
import eyeofsauron.helperutils.langguesser.LanguageGuesser;
import uk.ac.wlv.sentistrength.*;
/**
 * @author john
 *
 */
public class SentimentAnalyzerImpl extends Utility<SentimentAnalysisConfig, SentimentAnalysisArgument, SentimentAnalysisResult> {

	/* (non-Javadoc)
	 * @see eyeofsauron.util.sentimentanalysis.SentimentAnalyzer#analyze(org.carrot2.core.Cluster)
	 */
	private SentiStrength classifier;
	protected final static String utilityIdentifier = "Core Sentiment Analyzer";
	protected final static double version= 0.1;
	public static final String descSeparator = "/-//-";
	private LanguageGuesser guesser;
	
//  - powered by SentiStrength
	private SentimentAnalysisConfig currentConfig = null;
	
	private static SentimentAnalysisConfig defaultConfig;
	static{
		defaultConfig = new SentimentAnalysisConfig(SentimentResultTypeEnum.SCALE, 1.2, LanguageCode.ENGLISH);
	}
	
	public SentimentAnalyzerImpl() throws LangDetectException{
		this(defaultConfig);
	}
	
	public SentimentAnalyzerImpl(SentimentAnalysisConfig conf) throws LangDetectException{
		
		classifier = new SentiStrength();
		guesser = new LanguageGuesser();
		
		currentConfig = conf;
		configure(conf);
	}

	@Override
	public boolean configure(SentimentAnalysisConfig config) {


		String[] sentiArgs = config.initArray();
		classifier.initialise(sentiArgs);

		return true;
	}

	@Override
	public SentimentAnalysisResult process(SentimentAnalysisArgument arg) {


		SentimentAnalysisResult result = new SentimentAnalysisResult("Sentiment Analysis Result", "", currentConfig.getType(), getUtilityIdentity().toString());
		SentimentAnalysisData d;

		List<Cluster> clusters = arg.getArgumentsByClass(Cluster.class);
		List<Document> docList= arg.getArgumentsByClass(Document.class);
		List<String> strs= arg.getArgumentsByClass(String.class);
		if (!clusters.isEmpty())
			result.setTitle("Sentiment Analysis of Cluster: "+clusters.get(0).getLabel());
		else if (!docList.isEmpty())
			result.setTitle("Document Sentiment Analysis");
		else if (!strs.isEmpty())
			result.setTitle("String Sentiment Analysis");
		else
			return null;
		
		
		for (Cluster cl : clusters){
			List<Document> docs = cl.getAllDocuments();
			for (Document doc : docs){ 
				d = processDocument(doc, currentConfig.isInDepth());
				result.addData(d);
			}			
		}
		
		for (Document doc: docList){
			d = processDocument(doc, currentConfig.isInDepth());
			result.addData(d);
		}
		
		for (String str : strs){
			d = processString(str);
			result.addData(d);
			
		}
		
		return result;
	}

	private SentimentAnalysisData processDocument(Document doc, boolean inDepth) {

		if (!inDepth)
			return processString(doc.getTitle()+descSeparator+doc.getSummary()+descSeparator+doc.getContentUrl());
		// TODO Auto-generated method stub
		return null;
	}

	private SentimentAnalysisData processString(String str) {

		try {
			String langCode = guessLanguage(str);
			LanguageCode guessed = guesser.proccess(str);
			System.out.println(guessed.toString());
			configure(new SentimentAnalysisConfig(guessed));
		} catch (IOException e) {
//			e.printStackTrace();
			//TODO turn it to english
		} catch (LangDetectException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		String result = classifier.computeSentimentScores(str);
		double [] data = resultToArray(result);

		SentimentAnalysisData sentData = new SentimentAnalysisData(data, str);
		return sentData;
	}

	
	
	
	private double[] resultToArray(String result){
		
		double[] data;
		String[] dataStr = result.split(" ");
		int c = dataStr.length;
		data = new double[c];
		for (int i = 0; i < c; i++){
			data[i] = Double.parseDouble(dataStr[i]);
		}
		return data;
	}

	@Override
	public UtilityIdentity getUtilityIdentity() {
		return new UtilityIdentity(utilityIdentifier, version);
	}


	protected String guessLanguage(String text) throws IOException, LangDetectException{
		
		Detector detector = DetectorFactory.create();
        detector.append(text);
        return detector.detect();
	}
}

	
