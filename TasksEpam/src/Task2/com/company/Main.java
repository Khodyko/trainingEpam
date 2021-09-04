package Task2.com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static boolean isVoid(String string) {
		if(string.equals("")) {
			return true;
		}
		else {
			return false;
		}
	}
	public static void main(String[] args) {
		List<String> sentences = new ArrayList<>();

		List<String> wordsFirstSentence = new ArrayList<>();

		ArrayList<String> fullWordsList = new ArrayList<>();

		String fullText = "";

		Integer maxCountWordNmbr = 0;

		String maxWord = "";

		String inputFileName = "d://text2.txt";// put name of File here

		try {

			BufferedReader bu = new BufferedReader(new FileReader(inputFileName));
			String buffer;
			while ((buffer = bu.readLine()) != null) {
				fullText = fullText + buffer;
			}
			if(isVoid(fullText)) {
				throw new IOException();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("text is not available, please get right link");
			System.exit(0);
			
		}
		System.out.println("fulltext: " + fullText);
		sentences = Arrays.asList(fullText.split("[.!?]\\s*"));

		wordsFirstSentence = Arrays.asList(sentences.get(0).split("[\\p{Punct}\\s\"«»]+"));

		for (int i = 0; i < sentences.size(); i++) { // ++i or i++??????

			fullWordsList.addAll(Arrays.asList(sentences.get(i).split("[\\p{Punct}\\s\"«»]+")));

		}

		Integer countOfCurrentWord = 0;

		for (int i = 0; i < wordsFirstSentence.size(); i++) {

			countOfCurrentWord = 0;

			for (int j = 0; j < fullWordsList.size(); j++) {

				if (fullWordsList.get(j).equals(wordsFirstSentence.get(i))) {

					countOfCurrentWord = countOfCurrentWord + 1;

				}

			}

			if (countOfCurrentWord > maxCountWordNmbr) {

				maxCountWordNmbr = countOfCurrentWord;

				maxWord = wordsFirstSentence.get(i);

			}

		}

		System.out.println("Word " + maxWord + " is the most popular word of the first sentence in the text");

		System.out.println("It occurs " + maxCountWordNmbr + " times"); // I could take into acccount the words, that

// are used equally count of times, but i didn't do it.
	}
	
}