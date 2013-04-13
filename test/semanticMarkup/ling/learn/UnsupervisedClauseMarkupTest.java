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
		
		//FileLoader sentLoader = new FileLoader(str);
		//sentLoader.load();
		//sentLoader.getUnknownWordList();
		//assertEquals("Result", 1, sentLoader.GetType("Buckup_1998.xml_5c157037-01e4-4d48-8014-b1ebfc9dc120_8210ee00-8026-4fd9-974f-2f4cf6ce389f.txt"));
		//assertEquals("Result", 0, sentLoader.GetType("Buckup_1998.xml_8d819b51-b88a-459e-bcb2-c6137d8b95d7.txt"));
		
		
		
		
		
		
	
	

		

		

		
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
		

		
		// Method markKnown
		assertEquals("markKnown - forbidden word", 0,
				tester.markKnown("and", "", "", "", 0));
		assertEquals("markKnown - stop word", 0,
				tester.markKnown("page", "", "", "", 0));	
		

		
		// Method inSingularPluralPair
		assertEquals ("inSingularPluralPair - null", false, tester.inSingularPluralPair("word"));
		tester.myDataHolder.singularPluralTable.add(new SingularPluralPair("word1", ""));
		assertEquals ("inSingularPluralPair - singular match", true, tester.inSingularPluralPair("word1"));
		tester.myDataHolder.singularPluralTable.add(new SingularPluralPair("", "word2"));
		assertEquals ("inSingularPluralPair - plural match", true, tester.inSingularPluralPair("word2"));
		tester.myDataHolder.singularPluralTable.add(new SingularPluralPair("word3", "word3"));
		assertEquals ("inSingularPluralPair - both match", true, tester.inSingularPluralPair("word3"));	
		
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
		
	}
}
