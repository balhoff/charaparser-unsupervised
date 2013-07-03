charaparser-unsupervised
========================

Java implementation of unsupervised CharaParser code

* Read Treatment
    * Preprocess
        * First replace all dot marks after abbreviation by "[DOT]". After sentence segmentation, restore them.
    * Sentence segmentation
        * Handle abbreviation. 
    * Get Lead of Sentence
    In most cases, the lead of a sentence is just the first NUM_LEAD_WORDS words in the sentence, where NUM_LEAD_WORDS has been pre-define as 3. However, there are two expections:
    1) When there is any :;.[(] in the sentence, only the words before any ,:;\.\[(] are counted. For example, for the sentence
        word1 word2 . word3
    only
        word1 word2
    are kept.
    2) Whene there is any preposition word in the sentence, only the words before the preposition word and the preposition word are counted. For example, for the sentence
        lepidotrichia of fin webs
    only the word before the preposition word "of" and the preposition word "of"
        lepidotrichia of
    are kept.
    
* Get All Unique Words




The regular expression like "blv?d" would match both "blvd" and "bld". The question mark makes the previous letter optional.


* Add Heuristics Nouns
** Learn Heuristics Nouns



** Character Heuristics

For each noun, if it is a singular, try to find the plural form; if it is a plural, try to find its singular form. Add any singular-plural pair learned in this way into SingularPlural holder.

* Add Stop Words

* Add Characters

Sort all words according to their root. That is, put words with same root together. For each group of words share same root, find any word in the group having a noun ending, then it must be a singular, put it into the noun list with a [s] tag. If there is another word within the same group ends with -s or -es, it is very likely that it is the plural form of the singular word. Put it into the noun list with a [p] tag. Put this singular-plural pair into SingularPlural holder.

* Add Numbers

* Add Cluster Strings

* Add Proper Nouns

* Learn POS By Suffix
For each word that is unknown in the Unknown Word Holder,trying to learn if it is a "b" word, by checking its suffix.

We have a list of common suffix. By checking the remaining part of the word after remove the suffix, decide if the suffix is really a suffix in the word, and therefore determine the POS tag.
  

* Markup Sentence By Pattern
markup the tag of some sentence by "chromosome", "flowerTime", "fruitTime" according to the pattern of the original sentence.

* Markup Ignore
    This one is not used here.
    
* Learning rules with high certainty
    Discover with parameter "start"
    
* discover
	with parameter "start"
	
	For each sentence whose status is "start" and tag is not "ignore" and "null", 

	At the every beginning, only those sentence whose first word is a p, could have a tag of "start", see populateSentece section.
	
	** Build pattern from the lead words of the sentence
        build a pattern with matches to any string containing a word in its first 3 words that is among the lead words
	** Find those sentences which match to this pattern
		For each of those sentences, do rule based learn, all the way to the point where no further new knowledge can be learned. Then stop.
        *** In rule based learn, 
        1) grow NOUNS and BDRY, and confirm tags by applying rules and clues 
        2) create and maintain decision tables
        
        for each marked sentence:
        	doit: 
        	update wordpos table (on certainty) when a sentence is tagged for the first time.
			Note: 	1) this update should not be done when a pos is looked up, because we may lookup a pos for the same example multiple times.
					2) if the tag need to be adjusted (not by doit function), also need to adjust certainty counts.
        		case 1 - case 10
        	tagit

* discover
	Bootstrapping rules
	Discover with parameter "normal"  
	Same to the previous step, except that the parameter is "normal" instead of "start".  
	
* additional bootstrapping
	
In this step, we do addition bootstrapping learning by using clues such as shared subject different boundary and one lead word.


* Manage Data Holder
** Update Table
If the pos is noun, find whether its is a singular or plural.

*** Mark Known Word

**** Precess New Word

Update Unknown Word
Put (word, tag) into UknownWord holder

***** the holder is WordPOS
****** update POS
case 1: the word does not exist, add it. Increase the count by 1.
case 2: the word already exists, update it
    2.1 the old POS is NOT same as the new POS, AND	the old POS is b or the new POS is b
    ??? new POS wins
    change POS. Increase the count by the number of changes made.
    ??? old POS wins
    update the word in WordPOS holder
    2.2 the old POS and the new POS are all [n]
    update role and certaintyU

***** the holder is modifier, add to Modifier holder


Since we know the word, we try to learn new words from this word based on its prefix, by forming some new words and checking if they exist in UnknownWord holder.

Increase the count by 1 whenever a new word has been learned.

For each word, if it is not in the SingularPlural Table, if it is a singular and not in the SingularPlural holder, find its plural form and add the (singular, plural) pair into the SingularPlural holder. Similarly, if it is a plural and not in the SingularPlural holder, find its singular form and add the (singular, plural) pair into the SingularPlural holder.
*** Discount POS
    Given a word, its old POS, its new POS, and the mode,
    1. Find the flag of the word in Unknownword holder, then select all words from Unknownword table who have the same flag including itself
    1. For each of them, 
        1.1. If from WordPOS holder its certaintyU is less than 1, AND mode is "all"
		    1.1.1. Delete the entry from WordPOS holder
		    1.1.1. Update Unknownword holder
		    1.1.1. If the POS is "s" or "p", delete all entries contains word from Singularplural holder as well
        1.1. Else insert (nword, Oldpos, newpos) into discounted table

Apart the updates on the holders themselves, in this step the count of how many updates have been made in this step is returned.

** getPOSptn
given a list of words, chech each of them in the WordPOS holder, find its POS tag, and returns a string with letters representing the POS tags. If the POS tag is not known for a word, use "?" to represent it.


Data Holder

* Sentence Collection
** tag: the subject of this sentence. In most cases it is a noun. Note that the tag could be a collection of more than one word. If it is null, then the sentence has not been tagged yet.

* Word-POS Collection
** role: the role of the word-POS pair. There are several legal values.
    "" - the role is unknown
    "*" - the word has been marked from "unknown" to "known" in the unknown word collection, but the role has not been determined yet.
    "-" - the word in the word-POS pair is a head noun.
    "_" - the word in the word-POS pair is a modifier prior to a head noun.
    "+" - the word has more than one roles, mark it as "*"
    (What is a head noun? in the leading word list, the last noun is the head noun, and the words prior to it are modifiers.)

* Unknown Word Collection
** word: the word
** flag: If the flag is "unknown", then the word is unknown; otherwise, the flag indicates how the word was learned. For example, if the word "unicuspid" is "b", then by removing prefix of unicuspid, the word "bicuspid" is learned as a [b] as well. In this case, the flag of word "bicuspid" is "unicuspid", indicates that the word "bicuspid" was learned from the word "unicuspid".




========
FAQ
========
* What is the difference between checkWN() and getNumber()?
checkWN:
getNumber() uses checkWN. If checkWN() returns "x", change the return that to empty string "". In addition, it handles some more special cases. 

* what is a modifer/boundary word?
the word before a noun is its modifer. The word after a noun is its boundary word.
