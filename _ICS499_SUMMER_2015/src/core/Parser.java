package core;

import java.util.ArrayList;

public class Parser {

	/**
	 * For TELUGU langauge
	 * 
	 * This method is called from WordProcessor to remove spaces
	 * It checks telugu for Halant before a space
	 * If Halant appears before a space the ZWNJ character is added
	 * then the space is removed;
	 * 
	 * Without this special logic, the logical characters before and after the space
	 * will be combined if the character before the space is a halant.
	 */
	public static ArrayList<String> stripSpaces(ArrayList<String> log_chars){
		//The ZWNJ is encoded in Unicode as U+200C zero width non-joiner
		ArrayList<String> logical_chars = new ArrayList<String>();
		for(int i = 0; i < log_chars.size(); i++){
			if(log_chars.get(i).equals(" ")){
				continue;
			}else{
				logical_chars.add(log_chars.get(i));

				char[] ch = log_chars.get(i).toCharArray();
				if(isHalant(ch) && i + 1 < log_chars.size()){
					if(log_chars.get(i+1).equals(" ")){
						logical_chars.add("1200C");
					}
				}
			}
		}
		
		return logical_chars;
	}
	
	/**
	 * For TELUGU language
	 * Parses a string / a series of unicode code points into
	 * a set of logical characters.
	 * @param ch
	 * @return
	 */
	public static ArrayList<String> parseToLogicalCharacters(String a_word) {
		ArrayList<String> logicalChars = new ArrayList<String>();
		String breakWord = a_word;
		StringBuffer buffer = new StringBuffer();
		int i = 0;
		while (i < breakWord.length()) {

			char[] ch = Character.toChars(breakWord.codePointAt(i++));
			String newChar = new String(ch);
			buffer.append(newChar);
			if (i == breakWord.length()) {
				logicalChars.add(buffer.toString());
				continue;
			}
			char[] nextCh = Character.toChars(breakWord.codePointAt(i));
			String nextChar = new String(nextCh);
			if (isDependent(nextCh)) {
				buffer.append(nextChar);
				i++;
				logicalChars.add(buffer.toString());
				buffer = new StringBuffer();
				continue;
			}
			if (isHalant(ch)) {
				if (isConsonant(nextCh)) {
					if (i < breakWord.length()) {
						continue;
					}
					buffer.append(newChar);
				}
				logicalChars.add(buffer.toString());
				buffer = new StringBuffer();
				continue;
			} else if (isConsonant(ch)) {
				if (isHalant(nextCh) || isDependentVowel(nextCh)) {
					if (i < breakWord.length()) {
						continue;
					}
					buffer.append(newChar);
				}
				logicalChars.add(buffer.toString());
				buffer = new StringBuffer();
				continue;

			} else if (isVowel(ch)) {
				if (isDependentVowel(nextCh)) {
					buffer.append(nextChar);
					i++;
				}
				logicalChars.add(buffer.toString());
				buffer = new StringBuffer();
				continue;

			}
			logicalChars.add(buffer.toString());
			buffer = new StringBuffer();
		}
		return logicalChars;

	}

	/**
	 * For TELUGU language
	 * Returns whether a character is a consonant
	 * @param ch
	 * @return
	 */
	public static boolean isConsonant(char[] ch) {
		String[] consonants = { "10C15", "10C16", "10C17", "10C18", "10C19",
				"10C1A", "10C1B", "10C1C", "10C1D", "10C1E", "10C1F", "10C20",
				"10C21", "10C22", "10C23", "10C24", "10C25", "10C26", "10C27",
				"10C28", "10C2A", "10C2B", "10C2C", "10C2D", "10C2E", "10C2F",
				"10C30", "10C32", "10C35", "10C36", "10C37", "10C38", "10C39", "10C33",
				"10C31" };
		String unicode = Integer.toHexString(ch[0] | 0x10000);
		for (String string : consonants) {
			if (string.compareToIgnoreCase(unicode) == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * For TELUGU language
	 * Returns whether a character is a dependent vowel
	 * @param ch
	 * @return
	 */
	public static boolean isDependentVowel(char[] ch) {
		String[] dependentVowels = { "10C3E", "10C3F", "10C40", "10C41",
				"10C42", "10C43", "10C44", "10C46", "10C47", "10C48", "10C4A",
				"10C4B", "10C4C",

		};
		String unicode = Integer.toHexString(ch[0] | 0x10000);
		for (String string : dependentVowels) {
			if (string.compareToIgnoreCase(unicode) == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * For TELUGU language
	 * Returns whether a character is a dependent character
	 * @param ch
	 * @return
	 */
	public static boolean isDependent(char[] ch) {
		String[] dependents = { "10C01", "10C02", "10C03", };
		String unicode = Integer.toHexString(ch[0] | 0x10000);
		for (String string : dependents) {
			if (string.compareToIgnoreCase(unicode) == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * For TELUGU language
	 * Returns whether a character is a vowel
	 * @param ch
	 * @return
	 */
	public static boolean isVowel(char[] ch) {
		String[] vowels = { "10C05", "10C06", "10C07", "10C08", "10C09",
				"10C0A", "10C0B", "10C0C", "10C0D", "10C0E", "10C0F", "10C10",
				"10C11", "10C12", "10C13", "10C14", };
		String unicode = Integer.toHexString(ch[0] | 0x10000);
		for (String string : vowels) {
			if (string.compareToIgnoreCase(unicode) == 0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * For TELUGU language
	 * Returns whether a character is a halant
	 * @param ch
	 * @return
	 */

	public static boolean isHalant(char[] ch) {
		String halant = "10C4D";
		String unicode = Integer.toHexString(ch[0] | 0x10000);
		if (halant.compareToIgnoreCase(unicode) == 0) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(Parser.parseToLogicalCharacters("రేవెన్స్"));
	}
}
