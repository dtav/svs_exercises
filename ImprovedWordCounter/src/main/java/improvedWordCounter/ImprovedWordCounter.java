package improvedWordCounter;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class ImprovedWordCounter {

	public static void main(String[] args){

		File f = new File("temp.txt");
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		BufferedReader bfr = null;

		try {

			bfr = new BufferedReader(new FileReader(f));
			boolean readingDone = false;
			while (!readingDone) {
				String line = bfr.readLine();
				String capitalLine = StringUtils.uncapitalize(line);
				if (capitalLine == null) {
					readingDone = true;
				} else {
					String[] wordsInLine = capitalLine.split(" ");
					for (int i = 0; i < wordsInLine.length; i++) {
						
						if (hm.containsKey(wordsInLine[i])) {
							// word has occured already
							int count = hm.get(wordsInLine[i]);
							
							hm.put(wordsInLine[i], ++count);
						} else {
							hm.put(wordsInLine[i], 1);
						}
					}
				}

			}
			System.out.println("Reading is done");
			Iterator i = hm.entrySet().iterator();
			while (i.hasNext()) {
				Map.Entry pair = (Map.Entry) i.next();
				System.out.println(pair.getKey() + " = " + pair.getValue());
				// System.out.println(word);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				bfr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
