//dtav

import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;

public class Server {
	File tweetFile;
	List<Tweet> tweetList;

	public Server(String filename) throws IOException {
		this.tweetFile = new File(filename);
		if (!this.tweetFile.exists()) {
			try {
				this.tweetFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.tweetList = new ArrayList<Tweet>();

		if (!isFileEmpty(this.tweetFile)) {
			translateFileToList();	
		}
		

	}

	public boolean isFileEmpty(File f) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(f));
		if (br.readLine() == null) {
			System.out.println("DB File is empty");
			br.close();
			return true;
		} else {
			br.close();
			return false;
		}

	}

	public void addNewTweetString(String twMsg) {
		Tweet tw = new Tweet(twMsg);
		Logger.Log(tw.getMessageContent() + " " + tw.getMessageTimestamp());
		this.tweetList.add(tw);
	}

	public void addNewTweet(Tweet t) {
		Logger.Log(t.getMessageContent() + " " + t.getMessageTimestamp());
		this.tweetList.add(t);
	}

	public void translateFileToList() {
		BufferedReader filerdr = null;
		try {
			filerdr = new BufferedReader(new FileReader(this.tweetFile));
			boolean stillReading = true;
			while (stillReading) {
				String line = filerdr.readLine();
				if (line == null) {
					stillReading = false;
				} else {
					String[] split = line.split(" <> ");
					String msg = split[0];
					String time = split[1];
					Logger.Log(msg + " <> " + time);
					long ms = Long.parseLong(time);
					Date dt = new Date(ms);
					Tweet entry = new Tweet(msg, dt);
					tweetList.add(entry);
					Logger.Log("tweetList updated");
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				filerdr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void listMessages() {

		Collections.sort(this.tweetList, new Comparator<Tweet>() {
			public int compare(Tweet t1, Tweet t2) {
				return t1.getMessageTimestamp().compareTo(t2.getMessageTimestamp());
			}
		});

		Collections.reverse(tweetList);

		System.out.println();
		System.out.println("########################");
		Iterator it = tweetList.iterator();
		while (it.hasNext()) {
			Tweet nx = (Tweet) it.next();
			nx.showTweet();
		}
		System.out.println("########################");
		System.out.println();

	}

	public void translateListToFile() throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(this.tweetFile));

		Iterator it = tweetList.iterator();
		while (it.hasNext()) {
			Tweet nx = (Tweet) it.next();
			nx.showTweet();
			Logger.Log("Writing to file... ");
			pw.println(nx.getMessageContent() + " <> " + nx.getMessageTimestamp().getTime());
			pw.flush();
		}
	}

	public void closeApplication() {
		Logger.Log("DB File update...");
		try {
			translateListToFile();
		} catch (IOException e) {
			System.out.println("List to file translation failed!");
			e.printStackTrace();
		}
	}

}
