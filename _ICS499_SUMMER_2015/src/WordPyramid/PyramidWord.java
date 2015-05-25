package WordPyramid;

import core.WordProcessor;


public class PyramidWord implements Comparable<PyramidWord>{

	// for representing the unique identifier of Pyramid Word
	private String ID;

	// for representing the topic
	private String topic;

	// for representing the Telugu String
	private String telugu;

	// for representing the English String
	private String english;

	public PyramidWord(String iD, String topic, String telugu, String english) {
		super();
		ID = iD;
		this.topic = topic;
		this.telugu = telugu;
		this.english = english;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTelugu() {
		return telugu;
	}

	public void setTelugu(String telugu) {
		this.telugu = telugu;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public PyramidWord() {

	}

	
	/**
	 * This method compares two big words default is English
	 */
	public int compareTo(PyramidWord a_word) {
		WordProcessor word_1 = new WordProcessor(this.telugu);
		WordProcessor word_2 = new WordProcessor(a_word.telugu);

		int length_1 = word_1.getLength();
		int length_2 = word_2.getLength();
		int strength_1 = word_1.getWordStrength();
		int strength_2 = word_2.getWordStrength();

		// TBD: Implementing it for Telugu now
		if (length_1 < length_2) {
			return -1;
		}

		if (length_1 > length_2) {
			return 1;
		}

		return 0;

	}
}
