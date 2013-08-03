package semanticMarkup.ling.learn;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PopulateSentenceUtilityTest {

	private PopulateSentenceUtility tester;

	@Before
	public void initialize() {
		tester = new PopulateSentenceUtility();
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
	
}
