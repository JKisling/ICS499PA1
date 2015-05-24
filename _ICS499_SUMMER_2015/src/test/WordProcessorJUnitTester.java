package test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import core.WordProcessor;


public class WordProcessorJUnitTester {

	@Test
	public final void testParseToLogicalChars() {
		String telugu_str_1 = "ఆనందమకరందము"; //మెట్రోపాలిటన్
		WordProcessor wp = new WordProcessor(telugu_str_1);
		assertEquals(8, wp.getLength());
		
		String telugu_str_2 = "స్ట్రా";
		WordProcessor wp2 = new WordProcessor(telugu_str_2);
		assertEquals(1, wp2.getLength());
		
		String english_str = "program";
		WordProcessor wp3 = new WordProcessor(english_str);
		assertEquals(7, wp3.getLength());
	}

	@Test
	public final void testParseInputString() {
		String telugu_str_1 = "ics"; 
		WordProcessor wp = new WordProcessor(telugu_str_1);
		ArrayList<String> str_array_list = wp.parseInputString("ఆనందమకరందము");
		assertEquals(8, str_array_list.size());
		
		ArrayList<String> str_array_list2 = wp.parseInputString("స్ట్రా");
		assertEquals(1, str_array_list2.size());		
	}
	@Test
	public final void testWordProcessorString() {
		String telugu_str_1 = "మెట్రో పాలిటన్";
		WordProcessor wp = new WordProcessor(telugu_str_1);
		assertEquals(7, wp.getLength());
		assertEquals("మెట్రో పాలిటన్", wp.getWord());
	}

	@Test
	public final void testWordProcessorArrayListOfString() {
        String telugu_str_1 = "ఆనందము";
		
		String tlc1 = "ఆ";
		String tlc2 = "నం";
		String tlc3 = "ద";
		String tlc4 = "ము";
		
		ArrayList<String> tal = new ArrayList<String>();
		tal.add(tlc1);
		tal.add(tlc2);
		tal.add(tlc3);
		tal.add(tlc4);
		
		WordProcessor wp = new WordProcessor(tal);
		assertEquals(telugu_str_1, wp.getWord());
	}

	@Test
	public final void testSetWord() {
		String telugu_str_1 = "ఆనందమకరందము";
		WordProcessor wp = new WordProcessor(telugu_str_1);
		wp.setWord("ఆనందము");
		assertEquals("ఆనందము", wp.getWord());
	}

	@Test
	public final void testSetLogicalChars() {
		String telugu_str_1 = "ఆనందము";
		
		String tlc1 = "ఆ";
		String tlc2 = "నం";
		String tlc3 = "ద";
		String tlc4 = "ము";
		ArrayList<String> tal = new ArrayList<String>();
		tal.add(tlc1);
		tal.add(tlc2);
		tal.add(tlc3);
		tal.add(tlc4);
		
		WordProcessor wp = new WordProcessor(tal);
		assertEquals(telugu_str_1, wp.getWord());
	}

	@Test
	public final void testGetWord() {
		String telugu_str_1 = "ఆనందమకరందము";
		WordProcessor wp = new WordProcessor(telugu_str_1);
		assertEquals(telugu_str_1, wp.getWord());
	}

	@Test
	public final void testIndexOf() {
		String telugu_str_1 = "ఆనందమకరందము";
		WordProcessor wp = new WordProcessor(telugu_str_1);
		assertEquals(3, wp.indexOf("మ"));
		assertEquals(7, wp.indexOf("ము"));
		
	}

	@Test
	public final void testGetLogicalChars() {
		String telugu_str_1 = "మెట్రో స్టేట్";
		WordProcessor wp = new WordProcessor(telugu_str_1);
		ArrayList<String> tal = wp.getLogicalChars();
		assertEquals(5, tal.size());
		assertEquals("ట్రో", tal.get(1));
		
		String telugu_str_2 = "మెట్రోస్టేట్";
		WordProcessor wp2 = new WordProcessor(telugu_str_2);
		ArrayList<String> ta2 = wp2.getLogicalChars();
		assertEquals(4, ta2.size());
		assertEquals("ట్రో", ta2.get(1));
	}

	@Test
	public final void testGetLength() {
		String telugu_str_1 = "ఆనందమకరందము";
		WordProcessor wp = new WordProcessor(telugu_str_1);
		assertEquals(8, wp.getLength());
		
		String telugu_str_2 =  "స్ట్రా";
		WordProcessor wp2 = new WordProcessor(telugu_str_2);
		assertEquals(1, wp2.getLength());
	}

	@Test
	public final void testGetLogicalLength() {
		String telugu_str_1 = "ఆనందమకరందము";
		WordProcessor wp = new WordProcessor(telugu_str_1);
		assertEquals(8, wp.getLogicalLength());
		
		String telugu_str_2 =  "స్ట్రా";
		WordProcessor wp2 = new WordProcessor(telugu_str_2);
		assertEquals(1, wp2.getLogicalLength());
	}
	@Test
	public final void testGetCodePointLength() {
		String telugu_str_1 =  "ఆనందమకరందము";
		WordProcessor wp = new WordProcessor(telugu_str_1);
		assertEquals(11, wp.getCodePointLength());
		
		String telugu_str_2 =  "స్ట్రా";
		WordProcessor wp2 = new WordProcessor(telugu_str_2);
		assertEquals(6, wp2.getCodePointLength());
	}

	@Test
	public final void testStartsWith() {
		String telugu_str_1 = "ఆనందమకరందము";
		WordProcessor wp = new WordProcessor(telugu_str_1);
		assertEquals(true, wp.startsWith("ఆనంద"));
		assertEquals(true, wp.startsWith("ఆనం"));
		assertEquals(false, wp.startsWith("నంద"));
	}

	@Test
	public final void testEndsWith() {
		String telugu_str_1 = "ఆనందమకరందము";
		WordProcessor wp = new WordProcessor(telugu_str_1);
		assertEquals(true, wp.endsWith("రందము"));
		assertEquals(true, wp.endsWith("ము"));
		assertEquals(false, wp.endsWith("ఆ"));
	}

	@Test
	public final void testContainsString() {
		String telugu_str_1 = "ఆనందమకరందము";
		WordProcessor wp = new WordProcessor(telugu_str_1);
		assertEquals(true, wp.containsString("రందము"));
		assertEquals(false, wp.containsString("ఆనంము"));
	}
	
	
	
	

	@Test
	public final void testContainsChar() {
		String telugu_str = "యూనివర్సిటీ";  // university  // యూనివర్సిటీ
		WordProcessor wp = new WordProcessor(telugu_str);
		assertEquals(true, wp.containsChar("యూ")); 
		assertEquals(false, wp.containsChar("x"));
	}

	@Test
	public final void testContainsAllLogicalChars() {
		String tlc1 = "ఆ";
		String tlc2 = "నం";
		String tlc3 = "ద";
		String tlc4 = "ము";
		ArrayList<String> tal = new ArrayList<String>();
		tal.add(tlc1);
		tal.add(tlc2);
		tal.add(tlc3);
		tal.add(tlc4);
		
		WordProcessor wp = new WordProcessor(tal);
		
		ArrayList<String> ta_list_2 = new ArrayList<String>();
		ta_list_2.add(tlc2);
		ta_list_2.add(tlc3);
		assertEquals(true, wp.containsAllLogicalChars(ta_list_2));  
		ta_list_2.add("x");
		assertEquals(false, wp.containsAllLogicalChars(ta_list_2));  	
	}

	@Test
	public final void testContainsLogicalCharSequence() {
		String telugu_str_1 = "మెట్రోపాలిటన్ స్టేట్ యూనివర్సిటీ";
		WordProcessor wp = new WordProcessor(telugu_str_1);
		ArrayList<String> al = new ArrayList<String>();
		al.add("పా");
		al.add("లి");
		al.add("ట");
		al.add("న్");
		assertEquals(true, wp.containsLogicalCharSequence(al));  	
		al.add(" ");
		assertEquals(true, wp.containsLogicalCharSequence(al));
		al.add("x");
		assertEquals(false, wp.containsLogicalCharSequence(al));
	}

	@Test
	public final void testContainsSpace() {
		String telugu_str_1 = "మెట్రోపాలిటన్ స్టేట్ యూనివర్సిటీ";
		WordProcessor wp = new WordProcessor(telugu_str_1);
		assertEquals(true,wp.containsSpace());
		wp.stripSpaces();
		assertEquals(false,wp.containsSpace());
		
		String telugu_str_2 = "మెట్రోపాలిటన్";
		WordProcessor wp2 = new WordProcessor(telugu_str_2);
		assertEquals(false,wp2.containsSpace());
		
		
	}

	@Test
	public final void testIsPalindrome() {
		String telugu_str_1 = "కిటికి";
		WordProcessor wp = new WordProcessor(telugu_str_1);
		assertEquals(true,wp.isPalindrome());
		wp.setWord("కిటికికి");
		assertEquals(false,wp.isPalindrome());
	}

	@Test
	public final void testIsAnagramString() {
		String telugu_str_1 = "కిటికి";
		WordProcessor wp = new WordProcessor(telugu_str_1);
		assertEquals(true,wp.isAnagram("కికిటి"));
		assertEquals(true,wp.isAnagram("టికికి"));
		assertEquals(false,wp.isAnagram("టికిక"));
		
		String telugu_str_2 = "రాత";
		WordProcessor wp2 = new WordProcessor(telugu_str_2);
		assertEquals(true,wp2.isAnagram("తరా"));
		assertEquals(false,wp2.isAnagram("తార "));
	}

	@Test
	public final void testIsAnagramArrayListOfString() {
		String telugu_str_1 = "మెట్రోపాలిటన్";
		WordProcessor wp = new WordProcessor(telugu_str_1);
		ArrayList<String> al = new ArrayList<String>();
		al.add("పా");
		al.add("లి");
		al.add("ట");
		al.add("న్");
		al.add("మె");
		al.add("ట్రో");
		assertEquals(true, wp.isAnagram(al));
		al.add("ట్రో");
		assertEquals(false, wp.isAnagram(al));
	}

	@Test
	public final void testReverse() {
		//యూనివర్సిటీ
		String telugu_str = "యూనివర్సిటీ";  // university  // యూనివర్సిటీ
		WordProcessor wp = new WordProcessor(telugu_str);
		assertEquals("టీర్సివనియూ", wp.reverse()); 
	}

	@Test
	public final void testAddCharacterAt() {
		String telugu_str = "నివర్సి";  // university  // యూనివర్సిటీ
		WordProcessor wp = new WordProcessor(telugu_str);
		assertEquals("యూనివర్సి", wp.addCharacterAt(0, "యూ")); 
		assertEquals("నివర్సిటీ", wp.addCharacterAt(3, "టీ"));
	}

	@Test
	public final void testAddCharacterAtEnd() {
		String telugu_str = "యూనివర్సి";  // university  // యూనివర్సిటీ
		WordProcessor wp = new WordProcessor(telugu_str);
		assertEquals("యూనివర్సిటీ", wp.addCharacterAtEnd("టీ")); 
		//assertEquals("నివర్సిటీ", wp.addCharacterAt(3, "టీ"));
	}

	@Test
	public final void testLogicalCharAt() {
		String telugu_str_1 = "స్ట్రా";  // straw
		WordProcessor wp = new WordProcessor(telugu_str_1);
		assertEquals(telugu_str_1,wp.logicalCharAt(0));
		
		String telugu_str_2 = "యూనివర్సిటీ";  // university
		WordProcessor wp2 = new WordProcessor(telugu_str_2);
		assertEquals("యూ",wp2.logicalCharAt(0)); 
		assertEquals("టీ",wp2.logicalCharAt(4)); 
		assertEquals("వ",wp2.logicalCharAt(2)); 
		
	}

	@Test
	public final void testCodePointAt() {
		
		// http://www.ssec.wisc.edu/~tomw/java/unicode.html#x0C00
		String telugu_str_1 = "స్ట్రా";  // straw
		WordProcessor wp = new WordProcessor(telugu_str_1);
		
		/*System.out.println(wp.getLength());
		System.out.println(wp.getCodePointLength());
		System.out.println(wp.codePointAt(0));
		System.out.println(wp.codePointAt(1));
		System.out.println(wp.codePointAt(2));
		System.out.println(wp.codePointAt(3));
		System.out.println(wp.codePointAt(4));
		System.out.println(wp.codePointAt(5));*/
		assertEquals(3128, wp.codePointAt(0));
		assertEquals(3149, wp.codePointAt(1));
		assertEquals(3134, wp.codePointAt(5));
	}
	
	

	@Test
	public final void testCompareTo() {
		String telugu_str_1 = "ఆనందము";
		WordProcessor wp = new WordProcessor(telugu_str_1);

		assertEquals(0, wp.compareTo("ఆనందము"));
		assertEquals(true, wp.compareTo("అరక") > 0);
		assertEquals(true, wp.compareTo("కడవలు") < 0);
	}
	
	@Test
	public final void testCompareToIgnoreCase() {
		String telugu_str_1 = "hello";
		WordProcessor wp = new WordProcessor(telugu_str_1);
		
		assertEquals(0, wp.compareToIgnoreCase("HeLLo"));
		assertEquals(true, wp.compareToIgnoreCase("eLLo") > 0);
		assertEquals(true, wp.compareToIgnoreCase("Jello") < 0);

		String telugu_str_2 = "ఆనందము";
		WordProcessor wp2 = new WordProcessor(telugu_str_2);

		assertEquals(0, wp2.compareTo("ఆనందము"));
		assertEquals(true, wp2.compareTo("అరక") > 0);
		assertEquals(true, wp2.compareTo("కడవలు") < 0);

	}
	
	@Test
	public final void testGetScrambledChars() {
		String telugu_str_1 = "ఆనందము";
		WordProcessor wp1 = new WordProcessor(telugu_str_1);
		ArrayList<String> scrambled_chars = wp1.getScrambledChars();
		WordProcessor wp2 = new WordProcessor(scrambled_chars);
		assertEquals(true,wp2.isAnagram(wp1.getWord()));
		
		String telugu_str_2 = "hello";
		WordProcessor wp3 = new WordProcessor(telugu_str_2);
		ArrayList<String> scrambled_chars_2 = wp3.getScrambledChars();
		WordProcessor wp4 = new WordProcessor(scrambled_chars_2);
		assertEquals(true,wp4.isAnagram(wp3.getWord()));	
	}
	
	@Test
	public final void testGetScrambledString() {
		String telugu_str_1 = "ఆనందము";
		WordProcessor wp1 = new WordProcessor(telugu_str_1);
		String scrambled_string_1 = wp1.getScrambledString();
		WordProcessor wp2 = new WordProcessor(scrambled_string_1);
		assertEquals(true,wp2.isAnagram(wp1.getWord()));
		
		String telugu_str_2 = "hello";
		WordProcessor wp3 = new WordProcessor(telugu_str_2);
		String scrambled_string_2 = wp3.getScrambledString();
		WordProcessor wp4 = new WordProcessor(scrambled_string_2);
		assertEquals(true,wp4.isAnagram(wp3.getWord()));
	
	}
	
	@Test
	public final void testEquals() {
		String telugu_str_1 = "ఆనందము";
		WordProcessor wp = new WordProcessor(telugu_str_1);
		assertEquals(true, wp.equals("ఆనందము"));
		assertEquals(false, wp.endsWith("ఆముదనం"));
	}

	@Test
	public final void testReverseEquals() {
		String telugu_str_1 = "ఆనందము";
		WordProcessor wp = new WordProcessor(telugu_str_1);
		assertEquals(true, wp.reverseEquals("ముదనంఆ"));
		assertEquals(false, wp.reverseEquals("ఆనందము"));
	}

	@Test
	public final void testCanMakeWord() {
		String telugu_str_1 = "మెట్రోపాలిటన్ స్టేట్ యూనివర్సిటీ";
		WordProcessor wp = new WordProcessor(telugu_str_1);
		
	    assertEquals(true,wp.canMakeWord("పాలి"));
	    assertEquals(true,wp.canMakeWord("స్టేట్"));
	    assertEquals(true,wp.canMakeWord("వనియూర్సిటీ"));
	    assertEquals(false,wp.canMakeWord("మ్మవనియూర్సిటీ"));
	}

	@Test
	public final void testCanMakeAllWords() {
		
		String telugu_str_1 = "మెట్రోపాలిటన్ స్టేట్ యూనివర్సిటీ";
		WordProcessor wp = new WordProcessor(telugu_str_1);
		ArrayList<String> word_list = new ArrayList<String>();

		word_list.add("మెట్రో");
		assertEquals(true,wp.canMakeAllWords(word_list));
	    word_list.add("ట్రోమె");
	    assertEquals(true,wp.canMakeAllWords(word_list));
	    word_list.add("స్టేట్");
	    assertEquals(true,wp.canMakeAllWords(word_list));
	    word_list.add("యూనివర్సిటీ");
	    assertEquals(true,wp.canMakeAllWords(word_list));
	    word_list.add("యూని");
	    assertEquals(true,wp.canMakeAllWords(word_list));
	    word_list.add("మెట్రోపాలిటన్ స్టేట్ యూనివర్సిటీ");
	    assertEquals(true,wp.canMakeAllWords(word_list));
	    word_list.add("మ్మ మెట్రో");
	    assertEquals(false,wp.canMakeAllWords(word_list));   
	}

	@Test
	public final void testTrim() {
		String telugu_str_1 = " మెట్రో";
		WordProcessor wp1 = new WordProcessor(telugu_str_1);
		String telugu_str_2 = " మెట్రో ";
		WordProcessor wp2 = new WordProcessor(telugu_str_2);
		String telugu_str_3 = "మెట్రో  ";
		WordProcessor wp3 = new WordProcessor(telugu_str_3);
		assertEquals("మెట్రో", wp1.trim()); 
		assertEquals("మెట్రో", wp2.trim()); 
		assertEquals("మెట్రో", wp3.trim());
		
		String telugu_str_4 = " మె ట్రో  ";
		WordProcessor wp4 = new WordProcessor(telugu_str_4);
		assertEquals("మె ట్రో", wp4.trim());
	}

	@Test
	public final void testStripSpaces() {
		String telugu_str_1 = " మెట్రో";
		WordProcessor wp1 = new WordProcessor(telugu_str_1);
		String telugu_str_2 = " మెట్రో ";
		WordProcessor wp2 = new WordProcessor(telugu_str_2);
		String telugu_str_3 = "మెట్రో  ";
		WordProcessor wp3 = new WordProcessor(telugu_str_3);
		assertEquals("మెట్రో", wp1.stripSpaces()); 
		assertEquals("మెట్రో", wp2.stripSpaces()); 
		assertEquals("మెట్రో", wp3.stripSpaces());
		
		String telugu_str_4 = " మె ట్రో   మె ట్రో  మె ట్రో";
		WordProcessor wp4 = new WordProcessor(telugu_str_4);
		assertEquals("మెట్రోమెట్రోమెట్రో", wp4.stripSpaces());
		
		String telugu_str_5 = " బర్గర్ కింగ్ ";
		WordProcessor wp5 = new WordProcessor(telugu_str_5);
		assertEquals("బర్గర్" + "కింగ్", wp5.stripSpaces());
	}

	@Test
	public final void testStripAllSymbols() {
		String telugu_str_1 = "!మె@ట్రో#పా$లి%ట^న&స్టే*ట(యూ)ని,వ.ర్సిటీ /\"\\~``";
		WordProcessor wp1 = new WordProcessor(telugu_str_1);
		String telugu_str_2 = "1మెట్రో9";
		WordProcessor wp2 = new WordProcessor(telugu_str_2);
		String telugu_str_3 = "//మెట్రో\"";
		WordProcessor wp3 = new WordProcessor(telugu_str_3);
		assertEquals("మెట్రోపాలిటనస్టేటయూనివర్సిటీ", wp1.stripAllSymbols()); 
		assertEquals("1మెట్రో9", wp2.stripAllSymbols()); 
		assertEquals("మెట్రో", wp3.stripAllSymbols());
	}


	@Test
	public final void testReplace() {
		String telugu_str_1 = "యూనివర్సిటీ";
		WordProcessor wp = new WordProcessor(telugu_str_1);
		assertEquals("యూనివర్సిటీ", wp.replace("y", "x"));
		assertEquals("యూనివర్సిటీ", wp.replace("నియూ", "x"));
		assertEquals("x", wp.replace("యూనివర్సిటీ", "x"));
		assertEquals("యూనియూని", wp.replace("వర్సిటీ", "యూని"));
	}

	@Test
	public final void testIsIntersecting() {
		String telugu_str_1 = "యూనివర్సిటీ";
		WordProcessor wp = new WordProcessor(telugu_str_1);
		assertEquals(true, wp.isIntersecting("యూనియన్"));
		assertEquals(false, wp.isIntersecting("యన్"));
		assertEquals(true, wp.isIntersecting("వ"));
	}

	@Test
	public final void testGetIntersectingRank() {
		String telugu_str_1 = "యూనివర్సిటీ";
		WordProcessor wp = new WordProcessor(telugu_str_1);
		assertEquals(2, wp.getIntersectingRank("యూనియన్"));
		assertEquals(0, wp.getIntersectingRank("యన్"));
		assertEquals(1, wp.getIntersectingRank("వ"));
		assertEquals(5, wp.getIntersectingRank("యూనివర్సిటీ"));
	}
	
	//getUniqueIntersectingRank
	
	@Test
	public final void testGetUniqueIntersectingRank() {
		String telugu_str_1 = "యూనివర్సిటీయూనివర్సిటీ";
		WordProcessor wp = new WordProcessor(telugu_str_1);
		
		assertEquals(2, wp.getUniqueIntersectingRank(new WordProcessor("యూనియన్").getLogicalChars()));
		assertEquals(0, wp.getUniqueIntersectingRank(new WordProcessor("యన్").getLogicalChars())); 
		assertEquals(1, wp.getUniqueIntersectingRank(new WordProcessor("వ").getLogicalChars())); 
		assertEquals(5, wp.getUniqueIntersectingRank(new WordProcessor("యూనివర్సిటీ").getLogicalChars())); 
		
		String english_str = "METRO METRO";
		WordProcessor wp2 = new WordProcessor(english_str);
		assertEquals(2,wp2.getUniqueIntersectingRank(new WordProcessor("MAT").getLogicalChars()));
		assertEquals(2,wp2.getIntersectingRank("MAT"));
		
	}
	
	
	
	@Test
	public final void testGetUniqueIntersectingLogicalChars() {
		String telugu_str_1 = "యూనివర్సిటీ";
		WordProcessor wp1 = new WordProcessor(telugu_str_1);
		String telugu_str_2 = "ఆస్ట్రేలియా యూనివర్సిటీ ";
		WordProcessor wp2 = new WordProcessor(telugu_str_2);
	//	System.out.println(wp1.getUniqueIntersectingLogicalChars(wp2.getLogicalChars()));
		assertEquals(5,wp1.getUniqueIntersectingLogicalChars(wp2.getLogicalChars()).size());
		
		String telugu_str_3 = "ఆస్ట్రేలియా యూని వర్సిటీ వర్సిటీ వర్సిటీ";
		WordProcessor wp3 = new WordProcessor(telugu_str_3);
	//	System.out.println(wp1.getUniqueIntersectingLogicalChars(wp3.getLogicalChars()));
		assertEquals(5,wp1.getUniqueIntersectingLogicalChars(wp3.getLogicalChars()).size());	
	}
	
	@Test
	public final void testSplitWord()
	{
		String telugu_str_1 = "యూనివర్సిటీ";
		WordProcessor wp = new WordProcessor(telugu_str_1);
		String[][] split_array = wp.splitWord(2);
		assertEquals(3,split_array.length);
	}
	
	@Test
	public final void testGetWordStrength()
	{
		String telugu_str_1 = "యూనివర్సిటీ";
		WordProcessor wp = new WordProcessor(telugu_str_1);
		assertEquals(4,wp.getWordStrength());
		
		String telugu_str_2 = "ఆస్ట్రేలియా";
		WordProcessor wp2 = new WordProcessor(telugu_str_2);
		assertEquals(6,wp2.getWordStrength());
		
		String telugu_str_3 = "స్ట్రా";
		WordProcessor wp3 = new WordProcessor(telugu_str_3);
		assertEquals(6,wp3.getWordStrength());
		
		String telugu_str_4 = "metro";
		WordProcessor wp4 = new WordProcessor(telugu_str_4);
		assertEquals(1,wp4.getWordStrength());	
	}
	
	@Test
	public final void testGetWordWeight()
	{
		String telugu_str_1 = "యూనివర్సిటీ";
		WordProcessor wp = new WordProcessor(telugu_str_1);
		assertEquals(11,wp.getWordWeight());
		
		String telugu_str_2 = "ఆస్ట్రేలియా";
		WordProcessor wp2 = new WordProcessor(telugu_str_2);
		assertEquals(11,wp2.getWordWeight());
		
		String telugu_str_3 = "స్ట్రా";
		WordProcessor wp3 = new WordProcessor(telugu_str_3);
		assertEquals(6,wp3.getWordWeight());
		
		String telugu_str_4 = "metro";
		WordProcessor wp4 = new WordProcessor(telugu_str_4);
		assertEquals(5,wp4.getWordWeight());
	
		
	}
}


/*
 * JUNIT method flow
setUpBeforeClass()

  (Test class first instance constructed and the following methods called on it)
    setUp()
    test1()
    tearDown()

  (Test class second instance constructed and the following methods called on it)
    setUp()
    test2()
    tearDown()

  (Test class third instance constructed and the following methods called on it)
    setUp()
    test3()
    tearDown()

tearDownAfterClass()
 */
