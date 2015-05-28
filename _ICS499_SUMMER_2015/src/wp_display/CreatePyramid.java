package wp_display;

import java.io.IOException;

import WordPyramid.HtmlCreator;
import WordPyramid.PyramidWord;
import WordPyramid.PyramidWordCollection;

public class CreatePyramid {

	//the word that will be printed. 
	PyramidWord word;
	HtmlCreator html;
	/**
	 * This Method initializes the PyramidWordCollectionand it will pass a
	 * random word to renderHTML based on the parameters listed below.
	 * 
	 * @param text
	 * @param text2
	 * @param selectedIndex
	 * @param selectedIndex2
	 */
	public CreatePyramid(String text, String text2, int selectedIndex,
			int selectedIndex2) {
		PyramidWordCollection pwc = new PyramidWordCollection();
		int max_len = Integer.parseInt(text);
		int min_len = Integer.parseInt(text2);
		String a_topic = null;
		switch(selectedIndex2){
					
		case 0: a_topic = "Airlines"; break;
		case 1: a_topic = "American_and_Italian_Foods"; break;
		case 2: a_topic = "Andhra_Sweets_Snacks"; break;
		case 3: a_topic = "Animals"; break;
		case 4: a_topic = "BodyParts"; break;
		case 5: a_topic = "day_by_day"; break;
		case 6: a_topic = "Dresses"; break;
		case 7: a_topic = "Festivals_of_India"; break;
		case 8: a_topic = "Flowers"; break;
		case 9 :a_topic = "Fruits"; break;
		case 10: a_topic = "Hindu_Gods_Goddesses"; break;
		case 11: a_topic = "Indian_Monuments"; break;
		case 12: a_topic = "Indian_Musical_Instruments"; break;
		case 13: a_topic = "Jewelry"; break;
		case 14: a_topic = "Land Forms,"; break;//issue 
		case 15: a_topic = "Misc"; break;
		case 16: a_topic = "Ornaments"; break;
		case 17: a_topic = "Professions"; break;
		case 18: a_topic = "Traditional_Games_of_AP"; break;
		case 19: a_topic = "Transportation"; break;
		case 20: a_topic = "Trees and Plants "; break;//issue
		case 21: a_topic = "US_Presidents"; break;
		case 22: a_topic = "US_State_Birds"; break;
		case 23: a_topic = "US_State_Capitals"; break;
		case 24: a_topic = "US_States"; break;
		case 25: a_topic = "Vegetables"; break;
		case 26: a_topic = "Bapu_Alphabets"; break;
		case 27: a_topic = "Nava_Dhanyalu"; break;
		case 28: a_topic = "Nava_Ratnalu"; break;
		case 29: a_topic = "Nava_Grahalu"; break;
		case 30: a_topic = "Nine_Emotions"; break;
		case 31: a_topic = "Ten_Avatars_of_God"; break;
		case 32: a_topic = "Things_At_Home"; break;
		case 33: a_topic = "Colors"; break;
		case 34: a_topic = "Numbers"; break;
		case 35: a_topic = "Relations"; break;
		case 36: a_topic = "Feelings"; break;
		case 37: a_topic = "Cartoons_Characters"; break;
		case 38: a_topic = "Dog_Breeds"; break;
		case 39: a_topic = "MLB Teams"; break;//issue
		case 40: a_topic = "NBA Teams"; break;//issue 
		case 41: a_topic = "NFL Teams"; break;//issue 
		case 42: a_topic = "NHL Teams"; break;//issue 
		case 43: a_topic = "Company_Logos"; break;
		case 44: a_topic = "US_Airlines"; break;
		case 45: a_topic = "US_Monuments"; break;
		case 46: a_topic = "American_Girl_Dolls"; break;
		case 47: a_topic = "Ivy_Leauge_Universities"; break;
		case 48: a_topic = "Pop_Cans"; break;
		case 49: a_topic = "Classic_Toys"; break;
		case 50: a_topic = "Water_Sports"; break;
		case 51: a_topic = "Chocolate_Bars"; break;
		case 52: a_topic = "Indian_Breakfast_Items"; break;
		case 53: a_topic = "Zodiac_Signs"; break;
		case 54: a_topic = "Chinese_Zodiac_Signs"; break;
		case 55: a_topic = "Countries_and_Flags"; break;
		case 56: a_topic = "Roman_Gods_Goddesses"; break;
		case 57: a_topic = "Greek_Gods_Goddesses"; break;
		case 58: a_topic = "Famous_Scientists"; break;
		case 59: a_topic = "Telugu_60_Years"; break;
		case 60: a_topic = "Days_of_week"; break;
		case 61: a_topic = "English_Months"; break;
		case 62: a_topic = "Eight_forms_of_Lakshmi_Goddess"; break;
		case 63: a_topic = "Telugu_Months"; break;
		case 64: a_topic = "Indian_Currency"; break;
		case 65: a_topic = "US_National_Parks"; break;
		case 66: a_topic = "Telugu_Seasons"; break;
		case 67: a_topic = "interrogatives"; break;
		case 68: a_topic = "Continents"; break;
		case 69: a_topic = "AP_State_Symbols"; break;
		case 70: a_topic = "Official_Languages_Of_India"; break;
		case 71: a_topic = "Orchestra_Brass_Family"; break;
		case 72: a_topic = "Orchestra_Keyboard_and_Harp_Family"; break;
		case 73: a_topic = "Orchestra_Percussion_Family"; break;
		case 74: a_topic = "Orchestra_String_Family"; break;
		case 75: a_topic = "Orchestra_Woodwind_Family"; break;
		case 76: a_topic = "English_Alphabet_Written_in_Telugu"; break;
		case 77: a_topic = "Telugu_Days_of_Fortnight"; break;
		case 78: a_topic = "Solar_System_and_Planets"; break;
		case 79: a_topic = "World_Religions"; break;
		case 80: a_topic = "Dinosaurs"; break;
		case 81: a_topic = "Shapes"; break;
		case 82: a_topic = "List_of_Sciences_New"; break;
		case 83: a_topic = "Minerals_Metals_Rocks"; break;
		case 84: a_topic = "Action_Verbs"; break;
		case 85: a_topic = "Common_Verbs"; break;
		case 86: a_topic = "Telugu_Alphabet_Words"; break;
		case 87: a_topic = "Directions"; break;
		case 88: a_topic = "household_items_bathroom"; break;
		case 89: a_topic = "Pronouns_sarvanaamamulu"; break;
		case 90: a_topic = "Food_Spices"; break;
		case 91: a_topic = "Things_in_Class_Room"; break;
		case 92: a_topic = "Household_Bedroom_Stuff"; break;
		case 93: a_topic = "Consonant_Blend_KA"; break;
		case 94: a_topic = "polyndromes"; break;
		case 95: a_topic = "u_vowel_words"; break;
		case 96: a_topic = "aa_vowel_words"; break;
		case 97: a_topic = "i_vowel_words"; break;
		case 98: a_topic = "Simple_Telugu_Words"; break;
		case 99: a_topic = "ch_cha_ja_jha_word_list"; break;
		case 100: a_topic = "Three_character_consonant_blends"; break;//issue 
		case 101: a_topic = "List_of_Sports"; break;
		case 102: a_topic = "List_of_Sports_Balls"; break;
		case 103: a_topic = "Shapes2"; break;
		case 104: a_topic = "Indian_Prime_Ministers"; break;
		case 105: a_topic = "Indian_Presidents"; break;
		case 106: a_topic = "Chief_Ministers_of_Andhra_Pradesh"; break;
		case 107: a_topic = "India_List_of_States"; break;
		case 108: a_topic = "India_Union_Territories "; break;            
		}   
		try{
	pwc = pwc.getPyramidWordCollectionByCriteria(a_topic, min_len,max_len);
		
	PyramidWord word = pwc.getRandomWord(pwc);
	System.out.println(word.getEnglish());
		}
	catch(Exception e){
		System.out.println("min and max need to be numbers");
	}
	}
	public void renderHTML() {
		
		try {
			html = new HtmlCreator("Money");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
