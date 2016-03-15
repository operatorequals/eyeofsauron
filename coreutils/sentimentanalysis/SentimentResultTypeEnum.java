/**
 * 
 */
package eyeofsauron.coreutils.sentimentanalysis;

/**
 * @author john
 *
 */
public enum SentimentResultTypeEnum {

	SCALE,
	TRINARY,
	BINARY,
	DEFAULT;
	
	public String toString(){
		switch(this){
		case SCALE:
			return "scale";
		case TRINARY:
			return "trinary";
		case BINARY:
			return "binary";
		case DEFAULT:
			return "";		
		}
	return null;
	}
}
