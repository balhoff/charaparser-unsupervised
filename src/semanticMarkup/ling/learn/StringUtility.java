package semanticMarkup.ling.learn;

public class StringUtility {

	public StringUtility() {
		// TODO Auto-generated constructor stub
	}

	public static String strip(String text) {				
		text=text.replaceAll("<(([^ >]|\n)*)>", " ");
		text=text.replaceAll("<\\?[^>]*\\?>", " "); //<? ... ?>
		text=text.replaceAll("&[^ ]{2,5};", " "); //remove &nbsp;
		text=text.replaceAll("\\s+", " ");
		
		return text;
	}
	
	/**
	 * 
	 * @param text
	 *            : string in which all punctuations to remove
	 * @param c
	 *            : a punctuatin to keep
	 * @return: string after puctuations are removed except the one in c
	 */

	public static String removePunctuation(String text, String c) {
		//System.out.println("Old: " + text);
		if (c == null) {
			text = text.replaceAll("[\\p{Punct}]", "");
		} else {
			text = text.replaceAll(c, "aaa");
			text = text.replaceAll("[\\p{Punct}]", "");
			text = text.replaceAll("aaa", c);
		}
		//System.out.println("New: " + text);

		return text;
	}
	
	public static String trimString (String text){
		String myText = text;
		myText = myText.replaceAll("^\\s+|\\s+$", "");
		return myText;
	}
	
	/**
	 * Helper of method updateTable: process word
	 * 
	 * @param w
	 * @return
	 */

	public static String processWord(String word) {
		//$word =~ s#<\S+?>##g; #remove tag from the word
		//$word =~ s#\s+$##;
		//$word =~ s#^\s*##;
		
		word = word.replaceAll("<\\S+?>", "");
		word = word.replaceAll("\\s+$", "");
		word = word.replaceAll("^\\s*", "");
		
		return word;
	}
	
	public static String removeAll(String word, String regex) {
		String newWord = word.replaceAll(regex, ""); 
		return newWord;
	}
	
}