package WordPyramid;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import core.Config;
import core.WordProcessor;

/**
 * This class manages the collection of Big Words These Words are supplied
 * through an input file. TODO / FUTURE: input file can be substituted with a
 * SQL string
 * 
 * @author srj
 *
 */

public class PyramidWordCollection {
	// FUTURE: We can serialize it for a later/faster retrieval
	// this is the single collection we hold in memory
	private ArrayList<PyramidWord> PyramidWordsList = new ArrayList<PyramidWord>();

	// Another collection - hashtable for a faster retrieval of the PyramidWord
	// based on the key
	private Hashtable<String, PyramidWord> PyramidWordsIDTable = new Hashtable<String, PyramidWord>();

	// Another collection - hashtable for a faster retrieval of the PyramidWord
	// based on the key
	private Hashtable<String, ArrayList<PyramidWord>> PyramidWordsTopicsTable = new Hashtable<String, ArrayList<PyramidWord>>();

	/**
	 * Default constructor calls the first overloaded constructor with the file
	 * path
	 * @throws FileNotFoundException 
	 */
	public PyramidWordCollection(){
		this(Config.INPUT_FILE);
	}

	/**
	 * A constructor that loads test data from a file path that is provided
	 * 
	 * @param a_file_name
	 *            is the file path to the test data file.
	 */
	public PyramidWordCollection(String a_file_name){
		// Create the PyramidWordsList first
		try {
			processPyramidWordsInputFile(a_file_name);
		} catch (IOException e) {

			System.out.println("Could not find file.");
		}

		// sort based on the length and strength
		sortByLengthAndStrength();

		// Then process the PyramidWordsList to create other collections as
		// needed
		makeAllCollections();
	}

	/**
	 * if the array list is provided directly, use this constructor This also
	 * creates all the other collections needed
	 */
	public PyramidWordCollection(ArrayList<PyramidWord> an_array_list) {
		PyramidWordsList = an_array_list;
		// sort based on the length and strength
		sortByLengthAndStrength();
		makeAllCollections();
	}

	/**
	 * Reads lines from a text file one by one and sends them to the
	 * addPyramidWord method. Catches a PyramidWordAdditionException if one is
	 * thrown, and exits the program as per instructions. It is possible
	 * however, to not exit and skip to the next line. This is a one line code
	 * change that involves removing the exit statement. reads UTF-8
	 * 
	 * @param filename
	 *            is a string that represents the path of the file to read
	 * @throws IOException
	 *             is thrown if the file fails to load
	 */
	private void processPyramidWordsInputFile(String filename)
			throws IOException {
		String line_read = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(filename), "UTF-8"));

		// ignore the first line. we don't need the header line
		line_read = reader.readLine();

		// System.out.println(line_read);

		int lineNumber = 0;
		while ((line_read = reader.readLine()) != null) {
			// System.out.println(line_read);

			lineNumber++;
			try {
				addPyramidWord(line_read);
			} catch (PyramidWordAdditionException e) {
				System.out.println(e.getMessage()
						+ "Exiting with error code 0 at test data line # "
						+ lineNumber);
				System.exit(0);
			}
		}
		reader.close();

	}

	/**
	 * Adds a PyramidWord to the collection
	 * 
	 * @param a_big_word
	 *            is added to the collection
	 * */
	public void addPyramidWord(PyramidWord a_big_word) {
		PyramidWordsList.add(a_big_word);
	}

	/**
	 * NEW For getting a Big Word based on index
	 * 
	 * @param a_big_word
	 *            is added to the collection
	 * */
	public PyramidWord getPyramidWord(int index) {
		return PyramidWordsList.get(index);
	}

	/**
	 * Adds a PyramidWord to the collection
	 * 
	 * @param a_line
	 *            is a single line of text with 16 values separated by 15 commas
	 *            with values thirteen and fourteen being space delimited lists
	 * @throws PyramidWordAdditionException
	 *             is thrown if the file is empty, a line has other than 15
	 *             commas, or the file contains duplicate items
	 */
	private void addPyramidWord(String a_line)
			throws PyramidWordAdditionException {
		if (a_line.equals("")) {
			throw new PyramidWordAdditionException(
					"Line is empty. Check the empty lines in the file!");
		}

		String[] list = a_line.split(Config.DELIMETER);
		List<String> tokens = Arrays.asList(list);

		// TODO: For now, this code can not handle unstructured data
		// it is required to have delimiters
		if (tokens.size() != Config.MAX_ITEMS_PER_LINE) {
			System.out.println("ERROR: Not enough separators | " + a_line);
			return;
		}

		// here are the fields in the input text file
		String token1 = tokens.get(0).trim(); // ID
		String token2 = tokens.get(1).trim(); // topic
		String token3 = tokens.get(2).trim(); // telugu
		String token4 = tokens.get(3).trim(); // english

		// Now create the PyramidWord
		PyramidWord new_PyramidWord = new PyramidWord(token1, token2, token3,
				token4);

		// System.out.println(new_PyramidWord);

		// add the PyramidWord to the PyramidWord collection
		// Once it is fully created, create the other tables as needed
		// FUTURE: We can optimize it so that all the collections are created in
		// one pass
		PyramidWordsList.add(new_PyramidWord);

	}

	/**
	 * Returning the Array List / All PyramidWords of a PyramidWordCollection
	 * 
	 * @return
	 */

	public ArrayList<PyramidWord> getAllPyramidWords() {
		return PyramidWordsList;
	}

	/**
	 * This method creates all the needed collections to be used in different
	 * games
	 * 
	 * If any other specific collections are needed, those can be built here.
	 */
	private void makeAllCollections() {
		makeIDHashtable();
		makeTopicHashtable();
	}

	/**
	 * This method makes a hashtable from the array list Key = Topic; value =
	 * Big Word
	 */
	private void makeIDHashtable() {
		// Now create our hashtable;
		// One time upfront cost for faster retrievals later on
		for (int i = 0; i < PyramidWordsList.size(); i++) {
			PyramidWord big_word = PyramidWordsList.get(i);
			String ID_str = big_word.getID();
			PyramidWordsIDTable.put(ID_str, big_word);
		}
	}

	/**
	 * This method makes a hashtable from the array list Key = Topic; value =
	 * Big Word Collection
	 */
	private void makeTopicHashtable() {
		// Now create our hashtable;
		// One time upfront cost for faster retrievals later on
		for (int i = 0; i < PyramidWordsList.size(); i++) {
			PyramidWord big_word = PyramidWordsList.get(i);
			String topic_str = big_word.getTopic();

			// check whether the key exists
			boolean key_exists = PyramidWordsTopicsTable.containsKey(topic_str);

			// if it exists, get the value and add the new Big Word to the
			// collection
			if (key_exists) {
				ArrayList<PyramidWord> temp = PyramidWordsTopicsTable
						.get(topic_str);
				temp.add(big_word);
				PyramidWordsTopicsTable.put(topic_str, temp);
			}
			// if the key doesn't exist, then we need to create a new collection
			// and then add the word to that new collection
			else {
				ArrayList<PyramidWord> temp = new ArrayList<PyramidWord>();
				temp.add(big_word);
				PyramidWordsTopicsTable.put(topic_str, temp);
			} // end else
		} // end for

		// System.out.println("topic hashtable size " +
		// PyramidWordsTopicsTable.size());
	} // end method makeTopicHashtable

	/**
	 * Retrieve all Big Words based on key word search
	 */

	public PyramidWordCollection getPyramidWordCollectionByKeyWord(String a_key) {
		ArrayList<PyramidWord> mini_collection = new ArrayList<PyramidWord>();
		for (int i = 0; i < PyramidWordsList.size(); i++) {
			PyramidWord b = PyramidWordsList.get(i);

			boolean match_found = b.toString().toLowerCase()
					.contains(a_key.toLowerCase());
			if (match_found) {
				mini_collection.add(b);
			}

		}
		return new PyramidWordCollection(mini_collection);
	}

	/**
	 * Retrieve the PyramidWord from hashtable based on the key
	 */

	public PyramidWord getPyramidWordByKey(String an_ID) {
		return PyramidWordsIDTable.get(an_ID);
	}

	/**
	 * get the size of the Big Word Collection
	 */

	public int size() {
		return PyramidWordsList.size();
	}

	/**
	 * Returns whether the Big Word Collection is empty
	 */

	public boolean isEmpty() {
		return (PyramidWordsList.size() == 0);
	}

	/**
	 * For printing the entire collection
	 */

	public String toString() {
		System.out.println("Size of the collection = "
				+ PyramidWordsList.size());
		return "";
	}

	// ==================================================================================================================================
	// ********** To be done by Students for SE Assignment 4 (Group Project)

	/**
	 * @author Kevin Nelson
	 * 
	 *         getPyramidWordCollectionByTopic(String some_topic) Searches
	 *         PyramidWordCollection based on a topic If some_topic is "Any",
	 *         the entire collection is returned If some_topic is null or "",
	 *         then a null collection is returned
	 * @param some_topic
	 *            , topic being searched
	 * @return returns PyramidWordCollection
	 */
	public PyramidWordCollection getPyramidWordCollectionByTopic(
			String some_topic) {

		PyramidWordCollection topicCollection;

		// if some_topic is "Any"
		if (some_topic == "Any") {

			topicCollection = new PyramidWordCollection(PyramidWordsList);

		}

		// if some_topic is null or ""

		else if (some_topic == null || some_topic == "") {

			topicCollection = new PyramidWordCollection(
					new ArrayList<PyramidWord>());

		}

		// else scenario

		else {

			topicCollection = new PyramidWordCollection(
					PyramidWordsTopicsTable.get(some_topic));

		}

		return topicCollection;
	}

	/**
	 * @author Richard Camera Returns the Big Word Collection based on the
	 *         length of the word This method matches the exact length. All
	 *         other words are discarded
	 */
	public PyramidWordCollection getPyramidWordCollectionByWordLength(
			int a_length) {
		ArrayList<PyramidWord> mini_collection = new ArrayList<PyramidWord>();
		WordProcessor wp;
		String str;

		for (PyramidWord a_word : PyramidWordsList) {

			// the length of the Telugu word is the strength of that word
			// the length of the Telugu word tells how many code points made
			// that word

			str = a_word.getEnglish();
			if (Config.LANGUAGE == "Telugu")
				str = a_word.getTelugu();

			wp = new WordProcessor(str);

			if (wp.getLength() == a_length) {
				mini_collection.add(a_word);
			}
		}
		return new PyramidWordCollection(mini_collection);
	}

	/**
	 * @author Richard Camera Returns the Big Word Collection based on the
	 *         length (min and max) of the word This method matches all the
	 *         strings between MIN and MAX (including)
	 */
	public PyramidWordCollection getPyramidWordCollectionByWordLength(int min,
			int max) {
		ArrayList<PyramidWord> mini_collection = new ArrayList<PyramidWord>();
		WordProcessor wp;
		String str;

		if (min != 0 || max != 0) {
			mini_collection = new ArrayList<PyramidWord>();

			for (PyramidWord a_word : PyramidWordsList) {

				// the length of the Telugu word is the strength of that word
				// the length of the Telugu word tells how many code points made
				// that word
				str = a_word.getEnglish();
				if (Config.LANGUAGE == "Telugu")
					str = a_word.getTelugu();

				wp = new WordProcessor(str);
				int word_length = wp.getLength();

				if (min >= 0 && max > 0) {
					if (word_length >= min && word_length <= max) {
						mini_collection.add(a_word);
					}
					continue;
				}
				if (min > 0 && max == 0) {
					if (word_length >= min) {
						mini_collection.add(a_word);
					}
					continue;
				}
			}
		} else
			mini_collection = new ArrayList<PyramidWord>(PyramidWordsList);

		return new PyramidWordCollection(mini_collection);
	}

	/**
	 * @author Surin Assawajaroenkoon
	 * 
	 *         Returns the Big Word Collection based on the strength of the Word
	 *         For English, strength = length For Telugu, different algorithm is
	 *         already provided
	 * */
	public PyramidWordCollection getPyramidWordCollectionByWordStrength(
			int strength) {
		ArrayList<PyramidWord> mini_collection = new ArrayList<PyramidWord>();
		WordProcessor wp;
		String str;

		for (PyramidWord a_word : PyramidWordsList) {

			// the length of the Telugu word is the strength of that word
			// the length of the Telugu word tells how many code points made
			// that word

			str = a_word.getEnglish();
			if (Config.LANGUAGE == "Telugu")
				str = a_word.getTelugu();

			wp = new WordProcessor(str);

			if (wp.getWordStrength() == strength) {
				mini_collection.add(a_word);
			}
		}
		return new PyramidWordCollection(mini_collection);
	}

	/**
	 * @author Surin Assawajaroenkoon
	 * 
	 *         Returns the Big Word Collection based on the strength of the Word
	 *         It returns all the words between min and max strengths For
	 *         English, strength = length For Telugu, different algorithm is
	 *         already provided
	 * */
	public PyramidWordCollection getPyramidWordCollectionByWordStrength(
			int min, int max) {
		ArrayList<PyramidWord> mini_collection = new ArrayList<PyramidWord>();
		WordProcessor wp;
		String str;

		if (min != 0 || max != 0) {
			mini_collection = new ArrayList<PyramidWord>();

			for (PyramidWord a_word : PyramidWordsList) {

				// the length of the Telugu word is the strength of that word
				// the length of the Telugu word tells how many code points made
				// that word
				str = a_word.getEnglish();
				if (Config.LANGUAGE == "Telugu")
					str = a_word.getTelugu();

				wp = new WordProcessor(str);
				int word_strength = wp.getWordStrength();

				if (min >= 0 && max > 0) {
					if (word_strength >= min && word_strength <= max) {
						mini_collection.add(a_word);
					}
					continue;
				}
				if (min > 0 && max == 0) {
					if (word_strength >= min) {
						mini_collection.add(a_word);
					}
					continue;
				}
			}
		} else
			mini_collection = new ArrayList<PyramidWord>(PyramidWordsList);

		return new PyramidWordCollection(mini_collection);
	}

	/**
	 * @author sean.ford Returns the Big Word Collection based on these search
	 *         parameters topic length of the word word types Any of these
	 *         parameters can be null or empty
	 */
	public PyramidWordCollection getPyramidWordCollectionByCriteria(
			String a_topic, int min_len, int max_len, int min_strength,
			int max_strength) {
		ArrayList<PyramidWord> result_collection = new ArrayList<PyramidWord>();
		ArrayList<PyramidWord> topic_collection = this
				.getPyramidWordCollectionByTopic(a_topic).getAllPyramidWords();
		ArrayList<PyramidWord> length_collection = this
				.getPyramidWordCollectionByWordLength(min_len, max_len)
				.getAllPyramidWords();
		ArrayList<PyramidWord> strength_collection = this
				.getPyramidWordCollectionByWordStrength(min_strength,
						max_strength).getAllPyramidWords();

		if (result_collection.size() > 0)
			result_collection.retainAll(topic_collection);
		else
			result_collection.addAll(topic_collection);

		if (result_collection.size() > 0)
			result_collection.retainAll(length_collection);
		else
			result_collection.addAll(length_collection);

		if (result_collection.size() > 0)
			result_collection.retainAll(strength_collection);
		else
			result_collection.addAll(strength_collection);

		return new PyramidWordCollection(result_collection);
	}

	/**
	 * Checks whether Big Word Collection has any duplicate IDs
	 * 
	 * @author ISRAEL Yemer
	 */
	public boolean containsDuplicateIDs() {
		ArrayList<String> id_s = new ArrayList<String>();
		for (PyramidWord PyramidWord : PyramidWordsList) {
			if (!id_s.contains(PyramidWord.getID())) {
				id_s.add(PyramidWord.getID());
				continue;
			}
			return true;
		}
		return false;
	}

	public Hashtable<String, ArrayList<PyramidWord>> getPyramidWordsTopicsTable() {
		return PyramidWordsTopicsTable;
	}

	/*
	 * This method sorts the big word collection first by Length and then by its
	 * Strength
	 */

	public void sortByLengthAndStrength() {
		Collections.sort(PyramidWordsList);
	}

	public PyramidWordCollection getPyramidWordCollectionByCriteria(
			String a_topic, int min_len, int max_len) {
		ArrayList<PyramidWord> result_collection = new ArrayList<PyramidWord>();
		ArrayList<PyramidWord> topic_collection = this
				.getPyramidWordCollectionByTopic(a_topic).getAllPyramidWords();
		ArrayList<PyramidWord> length_collection = this
				.getPyramidWordCollectionByWordLength(min_len, max_len)
				.getAllPyramidWords();

		if (result_collection.size() > 0)
			result_collection.retainAll(topic_collection);
		else
			result_collection.addAll(topic_collection);

		if (result_collection.size() > 0)
			result_collection.retainAll(length_collection);
		else
			result_collection.addAll(length_collection);
		return new PyramidWordCollection(result_collection);
	}

	// TODO Auto-generated method stub

	public PyramidWord getRandomWord(PyramidWordCollection pwc) {
		int i = (int) Math.random() * pwc.size();
		return pwc.getPyramidWord(i);

	}

}
