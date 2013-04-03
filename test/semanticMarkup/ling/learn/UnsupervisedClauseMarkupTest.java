package semanticMarkup.ling.learn;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
//import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import semanticMarkup.core.Treatment;
import semanticMarkup.ling.learn.UnsupervisedClauseMarkup;
import semanticMarkup.ling.learn.FileLoader;

public class UnsupervisedClauseMarkupTest {
	

	
	@Before
	public void initialize() {


	}
	
	@Test
	public void testGetAdjNouns(){
		UnsupervisedClauseMarkup tester = new UnsupervisedClauseMarkup("plain",
				"res/WordNet/WordNet-3.0/dict");
		DataHolder myDataHolder = tester.getDataHolder();
		List<Sentence> sentenceTable = myDataHolder.getSentenceTable();
		sentenceTable.add(
				new Sentence("source1", "word1 word2", "", "", "", "tag1", "modifier1",""));
		sentenceTable.add(
				new Sentence("source2", "word2 word3", "", "", "", "[tag2"," modifier2[abc]", ""));
		sentenceTable.add(
				new Sentence("source3", "word3", "", "", "", "[tag3","[abc]modifier2	", ""));
		sentenceTable.add(
				new Sentence("source4", "word1 word3 word4", "", "", "", "[tag4","	mo[123]difier3", ""));

		List<String> resultGetAdjNouns = new ArrayList<String>();
		resultGetAdjNouns.add("modifier3");
		resultGetAdjNouns.add("modifier2");
		
		assertEquals("Method getAdjNouns", resultGetAdjNouns,tester.getAdjNouns());
	}
	
	@Test
	public void testGetAdjNounSent(){
		UnsupervisedClauseMarkup tester = new UnsupervisedClauseMarkup("plain",
				"res/WordNet/WordNet-3.0/dict");
		DataHolder myDataHolder = tester.getDataHolder();
		List<Sentence> sentenceTable = myDataHolder.getSentenceTable();
		sentenceTable.add(
				new Sentence("source1", "word1 word2", "", "", "", "tag1", "modifier1",""));
		sentenceTable.add(
				new Sentence("source2", "word2 word3", "", "", "", "[tag2"," modifier2[abc]", ""));
		sentenceTable.add(
				new Sentence("source3", "word3", "", "", "", "[tag3","[abc]modifier2	", ""));
		sentenceTable.add(
				new Sentence("source4", "word1 word3 word4", "", "", "", "[tag4","	mo[123]difier3", ""));

		Map<String, String> resultGetAdjNounSent = new HashMap<String, String>();
		resultGetAdjNounSent.put("[tag2","modifier2");
		resultGetAdjNounSent.put("[tag3","modifier2");
		resultGetAdjNounSent.put("[tag4","modifier3");
		
		assertEquals("Method getAdjNouns", resultGetAdjNounSent, tester.getAdjNounSent());		
	}
	
	@Test
	public void testGetWordToSoures(){
		UnsupervisedClauseMarkup tester = new UnsupervisedClauseMarkup("plain",
				"res/WordNet/WordNet-3.0/dict");
		DataHolder myDataHolder = tester.getDataHolder();
		List<Sentence> sentenceTable = myDataHolder.getSentenceTable();
		sentenceTable.add(
				new Sentence("source1", "word1 word2", "", "", "", "tag1", "modifier1",""));
		sentenceTable.add(
				new Sentence("source2", "word2 word3", "", "", "", "[tag2"," modifier2[abc]", ""));
		sentenceTable.add(
				new Sentence("source3", "word3", "", "", "", "[tag3","[abc]modifier2	", ""));
		sentenceTable.add(
				new Sentence("source4", "word1 word3 word4", "", "", "", "[tag4","	mo[123]difier3", ""));
		
		// getWordToSources
		Map<String, Set<String>> resultGetWordToSources = new HashMap<String, Set<String>>();
		resultGetWordToSources.put("word1", new HashSet<String>());
		resultGetWordToSources.get("word1").add("source1");
		resultGetWordToSources.get("word1").add("source4");
		
		resultGetWordToSources.put("word2", new HashSet<String>());
		resultGetWordToSources.get("word2").add("source1");
		resultGetWordToSources.get("word2").add("source2");
		
		resultGetWordToSources.put("word3", new HashSet<String>());
		resultGetWordToSources.get("word3").add("source2");
		resultGetWordToSources.get("word3").add("source3");
		resultGetWordToSources.get("word3").add("source4");
		
		resultGetWordToSources.put("word4", new HashSet<String>());
		resultGetWordToSources.get("word4").add("source4");
				
		assertEquals("Method getWordToSources", resultGetWordToSources, tester.getWordToSources());
	}
	
	@Test
	public void testGetHeuristicNouns(){
		UnsupervisedClauseMarkup tester = new UnsupervisedClauseMarkup("plain",
				"res/WordNet/WordNet-3.0/dict");
		DataHolder myDataHolder = tester.getDataHolder();
		Map<String, String> myHeuristicNouns = myDataHolder.getHeuristicNounTable();
		myHeuristicNouns.put("word1", "type1");
		myHeuristicNouns.put("word2", "type2");
		
		Map<String,String> resultGetHeuristicNouns = new HashMap<String, String>();
		resultGetHeuristicNouns.put("word2", "type2");
		resultGetHeuristicNouns.put("word1", "type1");
		
		assertEquals("Method getHeuristicNouns", resultGetHeuristicNouns, tester.getHeuristicNouns());
	}


	
	@Test
	public void testUnsupervisedClauseMarkup() {
		UnsupervisedClauseMarkup tester = new UnsupervisedClauseMarkup("plain",
				"res/WordNet/WordNet-3.0/dict");

		

		
		//String str = "/Users/nescent/Phenoscape/TEST2/target/descriptions";
		//List<Treatment> treatments_l = new ArrayList<Treatment>();
				
		//UnsupervisedClauseMarkup tester = new UnsupervisedClauseMarkup("plain","test","res/WordNet/WordNet-3.0/dict");
		
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
		
		/******** Method populateSent ***************************************************/
		// getType
		assertEquals("PopulateSent Helper - getType: character", 1, tester.getType("Brazeau_2009.xml_states737.txt"));
		assertEquals("PopulateSent Helper - getType: description", 2, tester.getType("Brazeau_2009.xml_states737_state739.txt"));
		assertEquals("PopulateSent Helper - getType: otherwise", 0, tester.getType("saf_saiflkds)dsljf_fls.txt"));
		
		// getFirstNWords
		List<String> nWords = new ArrayList<String>();
		assertEquals("PopulateSent Helper - getFirstNWords: none", nWords, tester.getFirstNWords(null,-1));
		assertEquals("PopulateSent Helper - getFirstNWords: none", nWords, tester.getFirstNWords("",-1));
		assertEquals("PopulateSent Helper - getFirstNWords: none", nWords, tester.getFirstNWords(null, 1));
		assertEquals("PopulateSent Helper - getFirstNWords: none", nWords, tester.getFirstNWords("", 1));
		nWords.add("word1");
		nWords.add("word2");
		assertEquals("PopulateSent Helper - getFirstNWords: none", nWords, tester.getFirstNWords("word1 word2 word3 word4", 2));
		assertEquals("PopulateSent Helper - getFirstNWords: none", nWords, tester.getFirstNWords("word1 word2", 3));
		
		// getAllWords
		Map<String, Integer> wordsBefore = new HashMap<String, Integer>();
		wordsBefore.put("word1", 1);
		wordsBefore.put("word2", 2);
		Map<String, Integer> wordsAfter = new HashMap<String, Integer>();
		wordsAfter.put("word1", 2);
		wordsAfter.put("word2", 4);
		wordsAfter.put("word3", 2);
		wordsAfter.put("word4", 1);
		wordsAfter.put("word5", 1);
		assertEquals("PopulateSent Helper - getAllWords", wordsAfter, tester.getAllWords("word1 word2 word3 word2 word3 word4 word5", wordsBefore));
		
		// addSpace
		// null
		assertEquals("Result", null, tester.addSpace(null,null));
		// ""
		assertEquals("Result", "", tester.addSpace("", ""));
		assertEquals("Result", "word , word ; word : word ! word ? word . ",tester.addSpace("word,word;word:word!word?word.", "\\W"));
		
		// hideMarksInBrackets
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
		
		// restoreMarksInBrackets
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
		
		// handleTest (Fully finished - Dongye 01/08)
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

		// handleString
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
		
		// Nouns rule 0: Taxon name nouns
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
		
		// Nouns rule 0.5: Method getNounsMecklesCartilage
		Set<String> nouns = new HashSet<String>();
		assertEquals("getTaxonNameNouns - not match", nouns, tester.getNounsMecklesCartilage("word word word"));
		nouns.add("meckel#s");
		nouns.add("meckels");
		nouns.add("meckel");
		assertEquals("getTaxonNameNouns - match", nouns, tester.getNounsMecklesCartilage("word Meckel#s word"));
		
		// Method getNounsRule1
		//Set<String> descriptorMap = new HashSet<String>();
		Set<String> nouns1 = new HashSet<String>();
		nouns1.add("term1");
		assertEquals("getNounsRule1", nouns1, tester.getNounsRule1("Chang_2004.xml_ ffa60eb1-4320-4e69-b151-75a2615dca4b_29482156-8083-430c-91f4-e80209b50138.txt-0", "term1", new HashMap<String, Boolean>()));
		
		// Method getNounsRule2
		Set<String> nouns2 = new HashSet<String>();
		assertEquals("getNounsRule2 - not match", nouns2,
				tester.getNounsRule2("word word 	word soe width nea"));	
		nouns2.add("nouna");
		assertEquals("getNounsRule2 - match 1",nouns2,
				tester.getNounsRule2("word word 	word some nouna"));
		nouns2.add("nounb");
		assertEquals("getNounsRule2 - match 2",nouns2,
				tester.getNounsRule2("word some nouna near word some width near word third nounb near end"));
		assertEquals("getNounsRule2 - match 2",nouns2,
				tester.getNounsRule2("word some nouna near word some width near word third nounb near end nounc abction of end"));
		
		// Method getNounsRule3
		Set<String> nouns3 = new HashSet<String>();
		nouns3.add("II");
		nouns3.add("IX");
		assertEquals("getNounsRule3", nouns3, tester.getNounsRule3Helper("posterior and dorsal to foramen for nerve II (i.e. a posterior oblique myodome IX)"));
		nouns3.remove("II");
		nouns3.remove("IX");
		nouns3.add("Meckelian");
		assertEquals("getNounsRule3", nouns3, tester.getNounsRule3Helper("Pronounced dorsal process on Meckelian element"));
		
		
		// Method getNounsRule4
		Set<String> nouns4 = new HashSet<String>();
		assertEquals("getNounsRule4 - not match",nouns4,
				tester.getNounsRule4("word word 	word noun one"));	
		nouns4.add("nouna");
		assertEquals("getNounsRule4 - not match",nouns4,
				tester.getNounsRule4("word word 	word nouna 1"));
		nouns4.remove("nouna");
		nouns4.add("nounb");
		assertEquals("getNounsRule4 - not match",nouns4,
				tester.getNounsRule4("word word 	word page 1 word above 2 word NoUnb 2 end"));
		
		// Method getDescriptorsRule1
		Set<String> descriptors1 = new HashSet<String>();
		descriptors1.add("absent");
		assertEquals("getDescriptorsRule1", descriptors1,
				tester.getDescriptorsRule1("Brazeau_2009.xml_states200_state202.txt-0", "absent",
						new HashSet<String>()));
		descriptors1.remove("absent");
		descriptors1.add("present");
		nouns.add("present");
		assertEquals("getDescriptorsRule1", new HashSet<String>(),
				tester.getDescriptorsRule1("Brazeau_2009.xml_states200_state203.txt-0", "present", nouns));
		assertEquals("getDescriptorsRule1", descriptors1,
				tester.getDescriptorsRule1("Brazeau_2009.xml_states200_state203.txt-0", "present",
						new HashSet<String>()));
		
		// Method getDescriptorsRule2
		// Method isDescriptor
		// Method isMatched
		Map<String, Boolean> descriptorMap = new HashMap<String,Boolean>();
		descriptorMap.put("term1", false);
		assertEquals("isMatched", false, descriptorMap.get("term1"));
		assertEquals("isMatched", true, tester.isMatched("begin word word was term1 word word end", "term1", descriptorMap));
		assertEquals("isMatched", true, descriptorMap.get("term1"));
		
		// Method filterOutDescriptors
		Set<String> rNouns = new HashSet<String>();		
		Set<String> rDescriptors = new HashSet<String>();
		Set<String> results = new HashSet<String>();
		rNouns.add("noun1");
		rNouns.add("descriptor2");
		rNouns.add("noun2");
		rDescriptors.add("descriptor1");
		rDescriptors.add("descriptor2");
		rDescriptors.add("descriptor3");
		results.add("noun1");
		results.add("noun2");
		assertEquals("filterOutDescriptors",results,tester.filterOutDescriptors(rNouns, rDescriptors));

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
		

		
		// Method inSingularPluralPair
		assertEquals ("inSingularPluralPair - null", false, tester.inSingularPluralPair("word"));
		tester.singularPluralTable.add(new SingularPluralPair("word1", ""));
		assertEquals ("inSingularPluralPair - singular match", true, tester.inSingularPluralPair("word1"));
		tester.singularPluralTable.add(new SingularPluralPair("", "word2"));
		assertEquals ("inSingularPluralPair - plural match", true, tester.inSingularPluralPair("word2"));
		tester.singularPluralTable.add(new SingularPluralPair("word3", "word3"));
		assertEquals ("inSingularPluralPair - both match", true, tester.inSingularPluralPair("word3"));

		
		
		/*******************************
		 * Method checkWN
		 ******************************/		
		// Method checkWN
		assertEquals ("checkWN - case 0.0 not word", "", tester.checkWN("()","pos"));
		assertEquals ("checkWN - case 0.2 special case - teeth", "p", tester.checkWN("teeth","pos"));
		assertEquals ("checkWN - case 0.2 special case - NUM", "NUM", tester.checkWN("NUM","singular"));
		assertEquals ("checkWN - case 0.2 concentrically", "", tester.checkWN("concentrically","number"));
		// otherwise, call wn
		// Case 1.1
		// Case 1.2
		assertEquals ("checkWN - case 1.2", "", tester.checkWN("operculi","number"));
		assertEquals ("checkWN - case 1.2", "operculi", tester.checkWN("operculi","singular"));
		// Case 1.3
		assertEquals ("checkWN - case 1.3", "", tester.checkWN("postcleithra","number"));
		assertEquals ("checkWN - case 1.3", "postcleithra", tester.checkWN("postcleithra","singular"));
		// Case 2.1.1
		assertEquals ("checkWN - case 2.1.1", "conical", tester.checkWN("conical","singular"));	
		assertEquals ("checkWN - case 2.1.1", "x", tester.checkWN("conical","number"));
		assertEquals ("checkWN - case 2.1.1", "ossified", tester.checkWN("ossified","singular"));
		assertEquals ("checkWN - case 2.1.1", "x", tester.checkWN("ossified","number"));
		// These two tests not passed!
		//assertEquals ("checkWN - case 2.1.1", "extending", tester.checkWN("extending","singular"));
		//assertEquals ("checkWN - case 2.1.1", "x", tester.checkWN("extending","number"));
		// Case 2.1.2
		assertEquals ("checkWN - case 2.1.2", "stay", tester.checkWN("stays","singular"));
		assertEquals ("checkWN - case 2.1.2", "p", tester.checkWN("stays","number"));
		assertEquals ("checkWN - case 2.1.2", "general", tester.checkWN("general","singular"));
		assertEquals ("checkWN - case 2.1.2", "s", tester.checkWN("general","number"));
		// Case 2.1.3
		assertEquals ("checkWN - case 1.2", "row", tester.checkWN("row","singular"));
		assertEquals ("checkWN - case 1.2", "s", tester.checkWN("row","number"));
		// Case 2.2
		// Need test cases!
		
		/*******************************
		 * Method getNumber
		 ******************************/
		// Method getNumberHelper1
		assertEquals ("getNumberHelp1 - case 1: s or p", "s", tester.getNumberHelper1("s"));
		assertEquals ("getNumberHelp1 - case 2: x", "", tester.getNumberHelper1("x"));	
		assertEquals ("getNumberHelp1 - case 3: null", null, tester.getNumberHelper1("a"));
		// Method getNumberHelper2
		assertEquals ("getNumberHelp2 - end with i", "p", tester.getNumberHelper2("pappi"));
		assertEquals ("getNumberHelp2 - end with ss", "s", tester.getNumberHelper2("wordss"));
		assertEquals ("getNumberHelp2 - end with ia", "p", tester.getNumberHelper2("criteria"));
		assertEquals ("getNumberHelp2 - end with ium", "s", tester.getNumberHelper2("medium"));
		assertEquals ("getNumberHelp2 - end with tum", "s", tester.getNumberHelper2("datum"));
		assertEquals ("getNumberHelp2 - end with ae", "p", tester.getNumberHelper2("alumnae"));
		assertEquals ("getNumberHelp2 - end with ous", "", tester.getNumberHelper2("various"));
		assertEquals ("getNumberHelp2 - word as", "", tester.getNumberHelper2("as"));
		assertEquals ("getNumberHelp2 - word is", "", tester.getNumberHelper2("is"));
		assertEquals ("getNumberHelp2 - word us", "", tester.getNumberHelper2("us"));
		assertEquals ("getNumberHelp2 - end with us", "s", tester.getNumberHelper2("corpus"));
		assertEquals ("getNumberHelp2 - end with es", "p", tester.getNumberHelper2("phases"));
		assertEquals ("getNumberHelp2 - end with s", "p", tester.getNumberHelper2("mouths"));
		assertEquals ("getNumberHelp2 - end with ate", "", tester.getNumberHelper2("differentiate"));
		assertEquals ("getNumberHelp2 - not match", null, tester.getNumberHelper2("jxbz"));
		// Method getNumber
		assertEquals ("getNumber - not match", "s", tester.getNumber("jxbz"));
		assertEquals ("getNumber - case 1", "", tester.getNumber("only"));
		assertEquals ("getNumber - case 3", "s", tester.getNumber("uroneural"));
		
		
		
		/*******************************
		 * Method getSingular
		 ******************************/
		assertEquals("getSingular - non word", "", tester.getSingular("!@#"));
		assertEquals("getSingular - special case", "valve", tester.getSingular("valves"));
		assertEquals("getSingular - special case", "media", tester.getSingular("media"));
		assertEquals("getSingular - special case", "species", tester.getSingular("species"));
		assertEquals("getSingular - special case", "axis", tester.getSingular("axes"));
		assertEquals("getSingular - special case", "calyx", tester.getSingular("calyces"));
		assertEquals("getSingular - special case", "frons", tester.getSingular("frons"));
		assertEquals("getSingular - special case", "groove", tester.getSingular("grooves"));
		assertEquals("getSingular - special case", "nerve", tester.getSingular("nerves"));

		assertEquals("getSingular - case 1 - y", "gallery", tester.getSingular("galleries"));
		assertEquals("getSingular - case 2", "varus", tester.getSingular("vari"));
		assertEquals("getSingular - case 3 - ai", "lepidotrichium", tester.getSingular("lepidotrichia"));
		assertEquals("getSingular - case 4 - (x|ch|sh|ss))es", "process", tester.getSingular("processes"));
		assertEquals("getSingular - case 5 - ves", "leaf", tester.getSingular("leaves"));
		assertEquals("getSingular - case 6 - ices", "index", tester.getSingular("indices"));
		assertEquals("getSingular - case 7.1 - ae", "vertebra", tester.getSingular("vertebrae"));
		assertEquals("getSingular - case 7.2 - s", "hoplia", tester.getSingular("hoplias"));
		assertEquals("getSingular - case 7.2 - s", "branchiostegal", tester.getSingular("branchiostegals"));	
		
		/*******************************
		 * Method getPlural
		 ******************************/
		// method getPluralRuleHelper
		assertEquals ("getPluralRuleHelper - case 2", "ices ixes", tester.getPluralRuleHelper("ix"));
		assertEquals ("getPluralRuleHelper - case 2", "thicknesses", tester.getPluralRuleHelper("thickness"));
		assertEquals ("getPluralRuleHelper - case 4", "leaves", tester.getPluralRuleHelper("leaf"));
		assertEquals ("getPluralRuleHelper - case 4", "knives", tester.getPluralRuleHelper("knife"));		
		assertEquals ("getPluralRuleHelper - case 6", "neurocrania", tester.getPluralRuleHelper("neurocranium"));
		assertEquals ("getPluralRuleHelper - case 9", "premaxillae", tester.getPluralRuleHelper("premaxilla"));
		
		// method getPlural
		List<String> pList = new ArrayList<String>();
		pList.add("ices");
		pList.add("ixes");
		tester.WORDS.put("ices", 1);
		tester.WORDS.put("ixes", 2);
		assertEquals ("getPlural", pList, tester.getPlural("ix"));	
		
		
		
		// Method addSingularPluralPair
		assertEquals("addSigularPluralPair - pair not exist", true, tester.addSingularPluralPair("sword", "pword"));
		tester.singularPluralTable.add(new SingularPluralPair("sword2",""));
		assertEquals("addSigularPluralPair - one word exist", true, tester.addSingularPluralPair("sword2", "pword2"));
		tester.singularPluralTable.add(new SingularPluralPair("sword3","pword3"));
		assertEquals("addSigularPluralPair - pair exist", false, tester.addSingularPluralPair("sword3", "pword3"));	
		
		
		// Method updatePOS
		//assertEquals ("getPluralRuleHelper - ves plural", 0, tester.updatePOS("", "", "", 0));
		
		// Method mergeRole
		assertEquals ("mergeRole - case 1", "new", tester.mergeRole("*", "new"));
		assertEquals ("mergeRole - case 2", "old", tester.mergeRole("old", "*"));
		assertEquals ("mergeRole - case 3", "new", tester.mergeRole("", "new"));
		assertEquals ("mergeRole - case 4", "old", tester.mergeRole("old", ""));
		assertEquals ("mergeRole - case 5", "+", tester.mergeRole("old", "new"));
		assertEquals ("mergeRole - case 0", "same", tester.mergeRole("same", "same"));
		
		// Method getMTFromParentTag
		List<String> pair = new ArrayList<String>();
		pair.add("");
		pair.add("");		
		assertEquals ("getMTFromParentTag - case 0: fail", pair, tester.getMTFromParentTag("[modifier_ta"));
		pair.remove(1);
		pair.remove(0);
		pair.add("modifier");
		pair.add("tag");		
		assertEquals ("getMTFromParentTag - case 1: with []", pair, tester.getMTFromParentTag("[modifier tag]"));
		assertEquals ("getMTFromParentTag - case 2: without []", pair, tester.getMTFromParentTag("modifier tag"));
		
		// TEST of Helpers
		// Method removeAll
		assertEquals("removeAll - begin", "word word ", tester.removeAll("   word word ", "^\\s+"));
		assertEquals("removeAll - end", "word|word", tester.removeAll("word|word|", "\\|+$"));
		assertEquals("removeAll - all", "wordword", tester.removeAll("|word|word|", "\\|"));
		// this test cases is for method tagSentWMT
		assertEquals("removeAll - remove beginning", "word", 
				tester.removeAll("above word","^("+tester.getStopWords()+"|"+tester.getForbiddenWords()+")\\b\\s*"));
		assertEquals("removeAll - remove ending 1", "word1 word2", 
				tester.removeAll("word1 word2 or","\\s*\\b("+tester.getStopWords()+"|"+tester.getForbiddenWords()+"|\\w+ly)$"));
		assertEquals("removeAll - remove ending 2", "word1 word2", 
				tester.removeAll("word1 word2 usually","\\s*\\b("+tester.getStopWords()+"|"+tester.getForbiddenWords()+"|\\w+ly)$"));
		assertEquals("removeAll - remove middle pronouns", "word1  word2", 
				tester.removeAll("word1 each word2","\\b("+tester.getPronounWords()+")\\b"));
		assertEquals("removeAll - remove beginning and ending", "word", 
				tester.removeAll(" 	word	 	","(^\\s*|\\s*$)"));
		
	}
}
