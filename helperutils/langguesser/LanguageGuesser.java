package eyeofsauron.helperutils.langguesser;

import java.io.File;
import java.net.URL;

import org.carrot2.core.LanguageCode;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;

public class LanguageGuesser {

	static URL profileResource = null;
	static URL profileSmResource;
	Detector detector;
	
	public LanguageGuesser(){
		
		String profiles = "lang_detect_profiles"+File.separator+"profiles"+File.separator;
		String profilesSm = "lang_detect_profiles"+File.separator+"profiles.sm"+File.separator;
		
//		if (profileResource != null) return;
		
//		profileSmResource = getClass().getClassLoader().getResource(profilesSm);

		try{
			if (profileResource == null){
				profileResource = getClass().getClassLoader().getResource(profiles);
				DetectorFactory.loadProfile(profileResource.getPath());
			}

			System.out.println("Language Guesser Loaded");
//			DetectorFactory.loadProfile(profileSmResource.getPath());
			
		}catch (LangDetectException e){
			e.printStackTrace();
			System.err.println("Could not initialize Language guesser profiles: " +profileResource.getPath());
		}

	}
	public LanguageCode proccess (String text){
		
		try {
			detector = DetectorFactory.create();
		} catch (LangDetectException e1) {
			System.err.println("could not create detector");
			e1.printStackTrace();
		}

		detector.append(text);
        String langCode;
		try {
			langCode = detector.detect();
		} catch (LangDetectException e) {
			e.printStackTrace();
			return LanguageCode.ENGLISH;
		}
//System.out.print(langCode.toUpperCase());
		if (langCode.equalsIgnoreCase("el"))
			return LanguageCode.GREEK;
		LanguageCode guessed = LanguageCode.forISOCode(langCode.toUpperCase());
//		System.out.println(guessed);
        return guessed;
	
	}
}
