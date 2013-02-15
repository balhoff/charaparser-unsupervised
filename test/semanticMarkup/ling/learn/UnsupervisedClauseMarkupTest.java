package semanticMarkup.ling.learn;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
//import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import semanticMarkup.core.Treatment;
import semanticMarkup.ling.learn.UnsupervisedClauseMarkup;
import semanticMarkup.ling.learn.FileLoader;

public class UnsupervisedClauseMarkupTest {
	
	@Test
	public void testUnsupervisedClauseMarkup() {
		//String str = "/Users/nescent/Phenoscape/TEST2/target/descriptions";
		//List<Treatment> treatments_l = new ArrayList<Treatment>();
				
		UnsupervisedClauseMarkup tester = new UnsupervisedClauseMarkup("","biocreative2012","plain","test","res/WordNet/WordNet-3.0/dict");
		
		/*
		assertEquals("Result", null, tester.getAdjNouns());
		assertEquals("Result", null, tester.getAdjNounSent());
		assertEquals("Result", null, tester.getBracketTags());
		assertEquals("Result", null, tester.getHeuristicNouns());
		assertEquals("Result", null, tester.getRoleToWords());
		assertEquals("Result", null, tester.getSentences());
		assertEquals("Result", null, tester.getSentencesForOrganStateMarker());
		assertEquals("Result", null, tester.getSentenceTags());
		assertEquals("Result", null, tester.getTermCategories());
		assertEquals("Result", null, tester.getWordRoleTags());
		assertEquals("Result", null, tester.getWordsToRoles());
		assertEquals("Result", null, tester.getWordToSources());
		assertEquals("Result", true, tester.populatesents());
		*/
		
		//FileLoader sentLoader = new FileLoader(str);
		//sentLoader.load();
		//sentLoader.getUnknownWordList();
		//assertEquals("Result", 1, sentLoader.GetType("Buckup_1998.xml_5c157037-01e4-4d48-8014-b1ebfc9dc120_8210ee00-8026-4fd9-974f-2f4cf6ce389f.txt"));
		//assertEquals("Result", 0, sentLoader.GetType("Buckup_1998.xml_8d819b51-b88a-459e-bcb2-c6137d8b95d7.txt"));
		
		// test method hideMarksInBrackets
		assertEquals("Result", null, 
				tester.hideMarksInBrackets(null));
		assertEquals("Result", "", 
				tester.hideMarksInBrackets(""));
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
		
		// test method restoreMarksInBrackets
		assertEquals("Result", null,
				tester.restoreMarksInBrackets(null));
		assertEquals("Result", "",
				tester.restoreMarksInBrackets(""));	
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
		
		// test method handleTest (Fully finished - Dongye 01/08)
		// null
		assertEquals("Result", null, tester.handleText(null));
		// ""
		assertEquals("Result", "", tester.handleText(""));
		// remove " and '
		assertEquals("Result", "words word", tester.handleText("word's wo\"rd"));
		// plano - to
		assertEquals("Result", "word to word",
				tester.handleText("word -to word"));
		//
		assertEquals("Result", "word -shaped",
				tester.handleText("word ______shaped"));
		// unhide <i>
		assertEquals("Result", "word <i> word.",
				tester.handleText("word &lt;i&gt; word."));
		// unhide </i>
		assertEquals("Result", "word </i> word.",
				tester.handleText("word &lt;/i&gt; word."));
		// remove 2a. (key marks)
		assertEquals("Result", "word", tester.handleText("7b. word"));
		// remove HTML entities
		assertEquals("Result", "word   word", tester.handleText("word &amp; word"));
		// " & " => " and "
		assertEquals("Result", "word and word.",
				tester.handleText("word & word."));
		// "_" => "-"
		assertEquals("Result", "word-word.", 
				tester.handleText("word_word."));
		// absent ; => absent;
		assertEquals("Result", "word; word; word.", 
				tester.handleText("word ;word ;word."));
		// absent;blade => absent; blade
		assertEquals("Result", "word; word; word.", 
				tester.handleText("word;word;word."));
		assertEquals("Result", "word: word. word.", 
				tester.handleText("word:word.word."));
		// 1 . 5 => 1.5
		assertEquals("Result", "word 1.5 word 384739.84 word.", 
				tester.handleText("word 1 . 5 word 384739 . 84 word."));
		// #diam . =>diam.
		assertEquals("Result", "word diam. word diam. word.", 
				tester.handleText("word diam . word diam . word."));
		// ca . =>ca.
		assertEquals("Result", "word ca. word ca. word.", 
				tester.handleText("word ca . word ca . word."));
		// cm|mm|dm|m
		assertEquals("Result", "word 12 cm[DOT] word 376 mm[DOT] word.", 
				tester.handleText("word 12 cm . word 376 mm. word."));		
		
		// test method addSpace
		// null
		assertEquals("Result", null, tester.addSpace(null,null));
		// ""
		assertEquals("Result", "", tester.addSpace("", ""));
		assertEquals("Result", "word , word ; word : word ! word ? word . ",
				tester.addSpace("word,word;word:word!word?word.", "\\W"));

		// test method handleString
		// null
		assertEquals("Result", null, tester.handleSentence(null));
		// ""
		assertEquals("Result", "", tester.handleSentence(""));
		// remove (.a.)
		assertEquals("Result", "word word word word .",
				tester.handleSentence("word (.a.) word (a) word ( a ) word."));
		// remove [.a.]
		assertEquals("Result", "word word word word .",
				tester.handleSentence("word [.a.] word [a] word [ a ] word."));
		// remove {.a.}
		assertEquals("Result", "word word word word .",
				tester.handleSentence("word {.a.} word {a} word { a } word."));
		// to fix basi- and hypobranchial 
		assertEquals("Result", "word cup_ shaped word cup_ shaped word cup_ shaped word .",
				tester.handleSentence("word cup --- shaped word cup-shaped word cup ---------        shaped word."));		
		
		// multiple spaces => 1 space
		assertEquals("Result", "word word word .",
				tester.handleSentence("word  word	 word."));
		// remove multipe spaces at the beginning
		assertEquals("Result", "word word .", tester.handleSentence("  	word word."));
		// remove multipe spaces at the rear
		assertEquals("Result", "word word .", tester.handleSentence("word word.    "));		
	
		// test method containSuffix
		assertEquals("containSuffix less", true, tester.containSuffix("less", "", "less"));
		assertEquals("containSuffix ly", true, tester.containSuffix("slightly", "slight", "ly"));	
		assertEquals("containSuffix er", true, tester.containSuffix("fewer", "few", "er"));
		assertEquals("containSuffix est", true, tester.containSuffix("fastest", "fast", "est"));
		
		assertEquals("containSuffix base is in WN", true, tester.containSuffix("platform", "plat", "form"));
		assertEquals("containSuffix sole adj", true, tester.containSuffix("scalelike", "scale", "like"));
		
		// method addHeuristicsNouns
		// test method getHeuristicsNouns
		// test method handleSpecialCase
		HashSet<String> words = new HashSet<String>();		
		words.add("septa");
		words.add("word1");
		words.add("septum");
		assertEquals("addHeuristicsNouns - handleSpecialCase 1", "septa[p]", tester.addHeuristicsNounsHelper("septa[s]", words));
		
		// Method getPresentAbsentNouns
		assertEquals("getPresentAbsentNouns - no present/absent", "",
				tester.getPresentAbsentNouns("only one pair of abcly presen"));
		assertEquals("getPresentAbsentNouns - and|or|to", "",
				tester.getPresentAbsentNouns("only one pair of and present"));
		assertEquals("getPresentAbsentNouns - STOP words", "",
				tester.getPresentAbsentNouns("only one pair of without absent"));
		assertEquals(
				"getPresentAbsentNoun - always|often|seldom|sometimes|[a-z]+lys",
				"",
				tester.getPresentAbsentNouns("only one pair of abcly present"));
		assertEquals("getPresentAbsentNouns - PENDINGS", "circuli[p]",
				tester.getPresentAbsentNouns("only one pair of circuli absent"));
		assertEquals("getPresentAbsentNouns - end with ss", "glass[s]",
				tester.getPresentAbsentNouns("only one pair of glass absent"));
		assertEquals("getPresentAbsentNouns - end with none ss", "computers[p]",
				tester.getPresentAbsentNouns("only one pair of computers absent"));
		assertEquals("getPresentAbsentNouns - teeth", "teeth[p]",
				tester.getPresentAbsentNouns("only one pair of teeth present"));
		assertEquals("getPresentAbsentNouns - not SENDINGS", "serum[s]",
				tester.getPresentAbsentNouns("only one pair of serum absent"));
		assertEquals("getPresentAbsentNouns - SENDINGS", "computer[s]",
				tester.getPresentAbsentNouns("only one pair of computer absent"));
		
		// Method isWord
		assertEquals("isWord - Length not > 1", false, tester.isWord("a"));
		assertEquals("isWord - not all word characters", false, tester.isWord("%^"));
		assertEquals("isWord - all word characters", true, tester.isWord("ab"));
		assertEquals("isWord - STOP word", false, tester.isWord("state"));
		assertEquals("isWord - STOP word", false, tester.isWord("page"));
		assertEquals("isWord - STOP word", false, tester.isWord("fig"));
		
		// Method getRoot
		assertEquals("getRoot - computer", "comput", tester.getRoot("computer"));
		assertEquals("getRoot - computer", "comput", tester.getRoot("computers"));
		assertEquals("getRoot - computer", "comput", tester.getRoot("computing"));
		
		// Method trimString
		assertEquals("trimString head", "word", tester.trimString("	 	word"));
		assertEquals("trimString tail", "word",
				tester.trimString("word   		 	"));
		assertEquals("trimString head and tail", "word",
				tester.trimString("	 	word	 	 		  "));
		
		Set<String> taxonNames = new HashSet<String>();
		// Method getTaxonNameNouns
		assertEquals("getTaxonNameNouns - not match", taxonNames, tester.getTaxonNameNouns("word word word"));
		assertEquals("getTaxonNameNouns - empty taxon name", taxonNames, tester.getTaxonNameNouns("< i >< / i >"));
		taxonNames.add("word1 word2	word3");
		taxonNames.add("word1");
		taxonNames.add("word2");
		taxonNames.add("word3");
		taxonNames.add("word4 word5");
		taxonNames.add("word4");
		taxonNames.add("word5");
		assertEquals("getTaxonNameNouns - match", taxonNames, tester.getTaxonNameNouns("< i	>word1 word2	word3< /	i>, < i >word4 word5<	/i>"));
		
		// Method getTaxonNameNouns
		Set<String> nouns = new HashSet<String>();
		assertEquals("getTaxonNameNouns - not match", nouns, tester.getNounsMecklesCartilage("word word word"));
		nouns.add("meckel#s");
		nouns.add("meckels");
		nouns.add("meckel");
		assertEquals("getTaxonNameNouns - match", nouns, tester.getNounsMecklesCartilage("word Meckel#s word"));
		
		// Method getNounsEndOfSentence
		Set<String> nouns2 = new HashSet<String>();
		assertEquals(
				"getNounsEndOfSentence - not match",
				nouns2,
				tester.getNounsEndOfSentence("word word 	word soe width nea"));		
		nouns2.add("nouna");
		assertEquals(
				"getNounsEndOfSentence - match 1",
				nouns2,
				tester.getNounsEndOfSentence("word word 	word some nouna"));
		nouns2.add("nounb");
		assertEquals(
				"getNounsEndOfSentence - match 2",
				nouns2,
				tester.getNounsEndOfSentence("word some nouna near word some width near word third nounb near end"));
		
		// Method getNounsRule4
		Set<String> nouns3 = new HashSet<String>();
		assertEquals(
				"getNounsRule4 - not match",
				nouns3,
				tester.getNounsRule4("word word 	word noun one"));	
		nouns3.add("nouna");
		assertEquals(
				"getNounsRule4 - not match",
				nouns3,
				tester.getNounsRule4("word word 	word nouna 1"));
		nouns3.remove("nouna");
		nouns3.add("nounb");
		assertEquals(
				"getNounsRule4 - not match",
				nouns3,
				tester.getNounsRule4("word word 	word page 1 word above 2 word nounb 2 end"));
		
		// Method removePunctuation
		assertEquals("removePunctuation", "word word word wo-rd cant Id end", tester.removePunctuation("word word, word&$% wo-rd can't I'd end.","-"));
		
		// Method updateCheckedWords
		String checkedWords = ":";
		Set<String> list = new HashSet<String>();
		list.add("one");
		list.add("two");
		list.add("three");
		assertEquals("updateCheckedWords", ":two:one:three:", tester.updateCheckedWords(":", checkedWords, list));

		// Method buildPattern
		assertEquals(
				"buildPattern",
				"(?:^\\b(?:one|two|three)\\b|^\\w+\\s\\b(?:one|two|three)\\b|^\\w+\\s\\w+\\s\\b(?:one|two|three)\\b)",
				tester.buildPattern("one two three".split(" ")));
		
		

		// Method updateTable
		assertEquals("updateTable - empty word", 0,
				tester.updateTable("", "", "", "", 0));
		assertEquals("updateTable - forbidden word", 0,
				tester.updateTable("to", "", "", "", 0));
		
		// Method processWord
		String word = "<word>word <\\iword>word word</word2>";
		assertEquals("processWord", "word word word",
				tester.processWord(word));
		assertEquals("processWord", "word word word",
				tester.processWord(" 	 word word word"));
		assertEquals("processWord", "word word word",
				tester.processWord("word word word 	 "));
		//System.out.println(word);
		
		// Method markKnown
		assertEquals("markKnown - forbidden word", 0,
				tester.markKnown("and", "", "", "", 0));
		assertEquals("markKnown - stop word", 0,
				tester.markKnown("page", "", "", "", 0));	
		
		// Method checkWN
		assertEquals ("checkWN - not word", "", tester.checkWN("()","pos"));
		assertEquals ("checkWN - special case - teeth", "p", tester.checkWN("teeth","pos"));
		assertEquals ("checkWN - special case - NUM", "NUM", tester.checkWN("NUM","singular"));
		assertEquals ("checkWN - concentrically", "", tester.checkWN("concentrically","number"));

		
		
		
	}
}
