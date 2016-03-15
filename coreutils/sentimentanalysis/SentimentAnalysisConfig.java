/**
 * 
 */
package eyeofsauron.coreutils.sentimentanalysis;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.carrot2.core.*;

import eyeofsauron.domain.utils.config.UtilityConfig;

/**
 * @author john
 *
 */
public final class SentimentAnalysisConfig extends UtilityConfig{


	private static String fSep = File.separator;

	protected SentimentResultTypeEnum classResultType;
	private double negativeMultiplier;
	protected LanguageCode language;

	private Set<String> keywords = new HashSet<String>();
	private int wordsBeforeKeyword = -1, wordsAfterKeyword = -1;
	
	private final static String sentidata="sentidata";

	private final String wordsBeforeKeywordStr = "wordsBeforeKeyword";

	private final String wordsAfterKeywordStr="wordsAfterKeyword";
//	private final String sentidataPath="libs"+fSep+"SentiStrength"+fSep;
	private String sentidataPath=
//			"WebContent"+fSep+"WEB-INF"+fSep+"lib"+fSep+
			"SentiStrength"+fSep;

//	WebContent/WEB-INF/lib/SentiStrength	
//	WebContent/WEB-INF/lib/SentiStrength/
	protected boolean inDepth = false;
	
	protected static Set<String> fileSet;
	
	/**
	 * @param classResultType
	 * @param negativeMultiplier
	 * @param language
	 * @param keywords
	 * @param wordsBeforeKeyword
	 * @param wordsAfterKeyword
	 */
	public SentimentAnalysisConfig(SentimentResultTypeEnum classResultType, double negativeMultiplier,
			LanguageCode language,
			Set<String> keywords, int wordsBeforeKeyword, int wordsAfterKeyword) {
		this(classResultType, negativeMultiplier, language);

		if (!keywords.isEmpty() && (wordsBeforeKeyword*wordsAfterKeyword <= 0 ) || wordsBeforeKeyword+wordsAfterKeyword <= 0)
				throw new IllegalArgumentException();
		this.keywords = keywords;
		this.wordsBeforeKeyword = wordsBeforeKeyword;
		this.wordsAfterKeyword = wordsAfterKeyword;
	}

	public SentimentAnalysisConfig(SentimentResultTypeEnum classResultType, double d,
			LanguageCode language) {
		this.classResultType = classResultType;
		this.negativeMultiplier = d;
		this.language = language;

		URL url = SentimentAnalysisConfig.class.getClassLoader().getResource(sentidataPath+"14Languages"+fSep);
		String sentidataFolder = url.getPath();
		
		File folder = new File(sentidataFolder);
		String[] listOfFiles = folder.list();
		
		fileSet = new HashSet<String>(Arrays.asList(listOfFiles));
	}
	
	public SentimentAnalysisConfig(LanguageCode language) {
		this(SentimentResultTypeEnum.SCALE, 1, language);
	}
	
	public SentimentAnalysisConfig(SentimentResultTypeEnum classResultType, double negativeMultiplier,
			LanguageCode language,
			Set<String> keywords) {
		this(classResultType, negativeMultiplier, language, keywords, 4, 4);
	}
	
	protected String[] initArray(){
		
		ArrayList<String> args = new ArrayList<String>();
		//	Setting the Language

		URL url = getClass().getClassLoader().getResource(sentidataPath);
		
		sentidataPath = url.getPath();

//System.out.println(sentidataPath);
		args.add(sentidata);
		args.add(sentidataPath+getLangPath(language));
		String rType = classResultType.toString();
		if (!rType.isEmpty())
			args.add(rType);

		if (negativeMultiplier != 1){
			args.add("negativeMultiplier");
			args.add((new Double (negativeMultiplier)).toString());
		}

		if (!keywords.isEmpty()){
			String kWords = "";
			for (String word: keywords)
				kWords += word+",";
			kWords = kWords.substring(0,kWords.length()-1);	// remove last comma
			args.add("keywords");
			args.add(kWords);
			if (wordsBeforeKeyword > 0){
				args.add(wordsBeforeKeywordStr);
				args.add((new Integer (wordsBeforeKeyword)).toString());
			}
			if (wordsAfterKeyword > 0){
				args.add(wordsAfterKeywordStr);
				args.add((new Integer (wordsAfterKeyword)).toString());
			}
		}
		return  args.toArray(new String[args.size()]);
	}
	
	
	private static String getLangPath(LanguageCode code){
		
		
		if (code == null || code == LanguageCode.ENGLISH)
			return "SentStrength_Data_Sept2011"+fSep;

		

		String langCode = code.toString().toLowerCase();
		
		if (!fileSet.contains(langCode))
			return "SentStrength_Data_Sept2011"+fSep;
		
		
		String lang = "14Languages"+fSep;
		
		if (code == LanguageCode.ARABIC){
			lang += "arabic_english_mixed";
			return lang+fSep;
		}

		return lang+langCode+fSep;
	}

	/**
	 * @return the classResultType
	 */
	public SentimentResultTypeEnum getType() {
		return classResultType;
	}

	/**
	 * @param classResultType the classResultType to set
	 */
	public void setType(SentimentResultTypeEnum classResultType) {
		this.classResultType = classResultType;
	}

	/**
	 * @return the inDepth
	 */
	public boolean isInDepth() {
		return inDepth;
	}

	/**
	 * @param inDepth the inDepth to set
	 */
	public void setInDepth(boolean inDepth) {
		this.inDepth = inDepth;
	}

}
