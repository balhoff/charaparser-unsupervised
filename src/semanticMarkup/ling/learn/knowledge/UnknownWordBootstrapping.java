package semanticMarkup.ling.learn.knowledge;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import semanticMarkup.ling.learn.Constant;
import semanticMarkup.ling.learn.KnownTagCollection;
import semanticMarkup.ling.learn.LearnerUtility;
import semanticMarkup.ling.learn.StringUtility;
import semanticMarkup.ling.learn.dataholder.DataHolder;
import semanticMarkup.ling.learn.dataholder.SentenceStructure;

public class UnknownWordBootstrapping implements IModule {
	private LearnerUtility myLearnerUtility;

	public UnknownWordBootstrapping(LearnerUtility learnerUtility) {
		this.myLearnerUtility = learnerUtility;
	}

	@Override
	public void run(DataHolder dataholderHandler) {
		unknownWordBootstrapping(dataholderHandler);
	}
	
	public void unknownWordBootstrapping(DataHolder dataholderHandler) {
		PropertyConfigurator.configure("conf/log4j.properties");
		Logger myLogger = Logger.getLogger("learn.unknownWordBootstrapping");
		myLogger.trace("[unknownWordBootstrapping]Start");
		
		unknownWordBootstrappingPreprocessing(dataholderHandler);
		unknownWordBootstrappingMain(dataholderHandler);
		unknownWordBootstrappingPostprocessing(dataholderHandler);
		
		myLogger.trace("[unknownWordBootstrapping]End");
	}
	
	public void unknownWordBootstrappingPreprocessing(DataHolder dataholderHandler) {
		this.myLearnerUtility.tagAllSentences(dataholderHandler, "singletag", "sentence");
	}
	
	public void unknownWordBootstrappingMain(DataHolder dataholderHandler) {
		PropertyConfigurator.configure("conf/log4j.properties");
		Logger myLogger = Logger.getLogger("learn.unknownWordBootstrapping.main");
		
		String plMiddle = "(ee)";
		
		int newInt = 0;
		do {
			newInt = 0;
			Set<String> organs = new HashSet<String>();
			Set<String> boundaries = new HashSet<String>();
			Set<String> modifiers = new HashSet<String>();
			Set<String> allWords = new HashSet<String>();
			
			// Part 1
			List<String> words = this.unknownWordBootstrappingGetUnknownWord(
					dataholderHandler, plMiddle);
			for (String word: words){
				if ((StringUtility.isMatchedNullSafe("ium$", word))
						&& (!this.myLearnerUtility.getConstant().singularExceptions
								.contains(word))) {
					dataholderHandler.updateDataHolder(word, "s", "-", "wordpos", 1);
					if (isValidWord(word)) {
						organs.add(word);
						myLogger.debug("find a [s]" + word);
					}
				}
				else {
					if ((dataholderHandler.isExistTaggedSentenceByPattern("(^| )"+word+" (<B>|" + Constant.FORBIDDEN + ")"))
							&& (StringUtils.equals(this.myLearnerUtility.getWordFormUtility().getNumber(word), "p"))
							&& (isVerbEnding(dataholderHandler, word))) {
						dataholderHandler.updateDataHolder(word, "p", "-",
								"wordpos", 1);
						if (isValidWord(word)) {
							organs.add(word);
							myLogger.debug("find a [p]" + word);
						}
					}
				}
			}
			
			// Part 2
			if (organs.size() > 0) {
				String organsPattern = StringUtils.join(organs, "|");
				String pattern2 = "(^| )(" + organsPattern + ") [^<]";
				Set<SentenceStructure> sentences = dataholderHandler
						.getTaggedSentenceByPattern(pattern2);

				for (SentenceStructure sentenceItem : sentences) {
					String sentence = sentenceItem.getSentence();
					if (sentence != null) {
						Pattern p = Pattern.compile("(^|,<\\/b>)([\\w ]*?) ("
								+ organsPattern + ")\\b");
						Matcher m = p.matcher(sentence);
						if (m.find()) {
							String tempWords = m.group(2);
							if (!this.myLearnerUtility.getConstant().forbiddenWords
									.contains(tempWords)) {
								String[] tempWordsArray = tempWords
										.split("\\s+");
								if (tempWordsArray.length <= 2) {
									for (String tempWord : tempWordsArray) {
										dataholderHandler.updateDataHolder(
												tempWord, "m", "", "modifiers",
												1);
										if (this.isValidWord(tempWord)) {
											modifiers.add(tempWord);
											myLogger.debug("find a [m] "
													+ tempWord);
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Part 3
			allWords.addAll(organs);
			allWords.addAll(boundaries);
			allWords.addAll(modifiers);
			
			if ( (newInt>0) && (allWords.size()>0)) {
				String allWordsPattern = StringUtils.join(allWords, "|");
				String pattern3 = "(^| )(" + allWordsPattern + ") ";
				Set<SentenceStructure> sentences = dataholderHandler.getTaggedSentenceByPattern(pattern3);
				for (SentenceStructure sentenceItem: sentences) {
					String sentence = sentenceItem.getSentence();
					KnownTagCollection myKnownTags = new KnownTagCollection(null, organs, null, boundaries, null, null);
					sentence = this.myLearnerUtility.annotateSentence(sentence, myKnownTags, dataholderHandler.BMSWords);
					sentenceItem.setSentence(sentence);
					
				}
			}
			
			
		} while (newInt > 0);
	}

	/**
	 * Determine if a word has verb ending
	 * 
	 * @param dataholderHandler
	 *            the dataholder handler
	 * @param word
	 *            the word to check
	 * @return true if the word has verb ending; false otherwise
	 */
	public boolean isVerbEnding(DataHolder dataholderHandler, String word) {
		String pWord = word;
		String sWord = this.myLearnerUtility.getWordFormUtility().getSingular(
				pWord);

		// case 1
		if (StringUtility.isMatchedNullSafe("e$", sWord)) {
			sWord = StringUtility.chop(sWord);
		}
		// case 2
		else {
			if (sWord == null) {
				;
			} else {
				Matcher m2 = StringUtility.createMatcher("([^aeiou])$", sWord);
				if (m2.find()) {
					sWord = sWord + m2.group(1) + "?";
				}
			}
		}

		sWord = "(^|_)" + sWord + "ing";

		if (dataholderHandler.isWordExistInUnknownWord(sWord + "$", true, null,
				false)) {
			return true;
		}

		return false;
	}

	/**
	 * Helper of unknownWordBootstrapping()
	 * 
	 * @return list of words
	 */
	public List<String> unknownWordBootstrappingGetUnknownWord(DataHolder dataholderHandler, String plMiddle) {
		List<String> words = new LinkedList<String>();
		Iterator<Entry<String, String>> iter = dataholderHandler.getUnknownWordHolderIterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = iter.next();
			String word = entry.getKey();
			String flag = entry.getValue();
			if (word != null) {
				if ((StringUtils.equals(flag, "unknown"))
					&& 		((StringUtility.createMatcher(plMiddle, word).find()) 
							|| (StringUtility.createMatcher("("+ Constant.PLENDINGS + "|ium)$", word).find()))
				)
					words.add(word);
			}
		}

		return words;
	}

	public void unknownWordBootstrappingPostprocessing(DataHolder dataholderHandler) {
		// pistillate_zone
		// get all nouns from wordPOS holder
		Set<String> POSTags = new HashSet<String>();
		POSTags.add("p");
		POSTags.add("s");
		Set<String> nouns = dataholderHandler.getWordsFromWordPOSByPOSs(
				POSTags);
		
		// get boudaries
		Set<String> boundaries = new HashSet<String>();
		Set<String> words = dataholderHandler.getWordsFromUnknownWord("^.*_.*$", true,
						"^unknown$", true);
		Iterator<String> wordIter = words.iterator();
		String pattern = "_(" + StringUtils.join(nouns, "|") + ")$";
		while (wordIter.hasNext()) {
			String word = wordIter.next();
			Pattern p1 = Pattern.compile("^[a-zA-Z0-9_-]+$");
			Matcher m1 = p1.matcher(word);
			Pattern p2 = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
			Matcher m2 = p2.matcher(word);
			if (m1.matches() && (!m2.matches())) {
				if (!StringUtility.createMatcher(
						"\\b(" + Constant.FORBIDDEN + ")\\b", word).find()) {
					boundaries.add(word);
				}
				dataholderHandler.updateDataHolder(word, "b", "", "wordpos", 1);
			}
		}
		
		// if the boundaries is not empty
		if (boundaries.size() > 0) {
			Iterator<SentenceStructure> iter = dataholderHandler
					.getSentenceHolderIterator();
			while (iter.hasNext()) {
				SentenceStructure sentenceItem = iter.next();
				String tag = sentenceItem.getTag();
				String sentence = sentenceItem.getSentence();
				int sentenceID = sentenceItem.getID();

				if ((!(StringUtils.equals(tag, "ignore")) || (tag == null))
						&& (StringUtility.createMatcher(
								"(^| )(" + StringUtils.join(boundaries, "|")
										+ ") ", sentence).find())) {
					KnownTagCollection tags = new KnownTagCollection(null,
							null, null, boundaries, null, null);
					sentence = this.myLearnerUtility.annotateSentence(sentence, tags, dataholderHandler.BMSWords);
					SentenceStructure updatedSentence = dataholderHandler.getSentence(sentenceID);
					updatedSentence.setSentence(sentence);
				}
			}
		}
	}

	private boolean isValidWord(String word) {
		if (!this.myLearnerUtility.getConstant().forbiddenWords.contains(word)) {
			return true;
		} else
			return false;
	}
	
}