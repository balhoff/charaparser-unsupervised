package semanticMarkup.ling.learn;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;

import org.junit.Before;
import org.junit.Test;

public class PopulateSentenceUtilityTest {

	private PopulateSentenceUtility tester;

	@Before
	public void initialize() {
		// Get OpenNLP tokenizer
		InputStream tokenModelIn;
		Tokenizer myTokenizer = null;
		try {
			tokenModelIn = new FileInputStream("res/en-token.bin");
			TokenizerModel model = new TokenizerModel(tokenModelIn);
			myTokenizer = new TokenizerME(model);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		tester = new PopulateSentenceUtility(myTokenizer);
	}

	@Test
	public void testGetType() {
		assertEquals("PopulateSent Helper - getType: character", 1,
				tester.getType("Brazeau_2009.xml_states737.txt"));
		assertEquals("PopulateSent Helper - getType: description", 2,
				tester.getType("Brazeau_2009.xml_states737_state739.txt"));
		assertEquals("PopulateSent Helper - getType: otherwise", 0,
				tester.getType("saf_saiflkds)dsljf_fls.txt"));
	}

	@Test
	public void testHideMarksInBrackets() {
		assertEquals("Result", null, tester.hideMarksInBrackets(null));
		assertEquals("Result", "", tester.hideMarksInBrackets(""));
		assertEquals("Result", "before (word[DOT]  word) after",
				tester.hideMarksInBrackets("before (word. word) after"));
		assertEquals("Result", "before (word[QST]  word) after",
				tester.hideMarksInBrackets("before (word? word) after"));
		assertEquals("Result", "before (word[SQL]  word) after",
				tester.hideMarksInBrackets("before (word; word) after"));
		assertEquals("Result", "before (word[QLN]  word) after",
				tester.hideMarksInBrackets("before (word: word) after"));
		assertEquals("Result", "before (word[EXM]  word) after",
				tester.hideMarksInBrackets("before (word! word) after"));
	}

	@Test
	public void testRestoreMarksInBrackets() {
		assertEquals("Result", null, tester.restoreMarksInBrackets(null));
		assertEquals("Result", "", tester.restoreMarksInBrackets(""));
		assertEquals("Result", "before (word.  word) after",
				tester.restoreMarksInBrackets("before (word[DOT]  word) after"));
		assertEquals("Result", "before (word?  word) after",
				tester.restoreMarksInBrackets("before (word[QST]  word) after"));
		assertEquals("Result", "before (word;  word) after",
				tester.restoreMarksInBrackets("before (word[SQL]  word) after"));
		assertEquals("Result", "before (word:  word) after",
				tester.restoreMarksInBrackets("before (word[QLN]  word) after"));
		assertEquals("Result", "before (word!  word) after",
				tester.restoreMarksInBrackets("before (word[EXM]  word) after"));
	}

	@Test
	public void testAddSpace() {
		// null
		assertEquals("Result", null, tester.addSpace(null, null));
		// ""
		assertEquals("Result", "", tester.addSpace("", ""));
		assertEquals("Result", "word , word ; word : word ! word ? word . ",
				tester.addSpace("word,word;word:word!word?word.", "\\W"));
	}

	@Test
	public void testGetFirstNWords() {
		List<String> nWords = new ArrayList<String>();
		assertEquals("PopulateSent Helper - getFirstNWords: none", nWords,
				tester.getFirstNWords(null, -1));
		assertEquals("PopulateSent Helper - getFirstNWords: none", nWords,
				tester.getFirstNWords("", -1));
		assertEquals("PopulateSent Helper - getFirstNWords: none", nWords,
				tester.getFirstNWords(null, 1));
		assertEquals("PopulateSent Helper - getFirstNWords: none", nWords,
				tester.getFirstNWords("", 1));
		nWords.add("word1");
		nWords.add("word2");
		assertEquals("PopulateSent Helper - getFirstNWords: none", nWords,
				tester.getFirstNWords("word1 word2 word3 word4", 2));
		assertEquals("PopulateSent Helper - getFirstNWords: none", nWords,
				tester.getFirstNWords("word1 word2", 3));
	}

	@Test
	public void testGetAllWords() {
		Map<String, Integer> wordsBefore = new HashMap<String, Integer>();
		wordsBefore.put("word1", 1);
		wordsBefore.put("word2", 2);
		Map<String, Integer> wordsAfter = new HashMap<String, Integer>();
		wordsAfter.put("word1", 2);
		wordsAfter.put("word2", 4);
		wordsAfter.put("word3", 2);
		wordsAfter.put("word4", 1);
		wordsAfter.put("word5", 1);
		assertEquals("PopulateSent Helper - getAllWords", wordsAfter,
				tester.getAllWords("word1 word2 word3 word2 word3 word4 word5",
						wordsBefore));
	}

}
