//dtav

import java.util.Date;

public class Tweet {
	private String messageContent;
	private Date messageTimestamp;
	
	public Tweet(String messageContent) {
		this.messageContent = messageContent;
		this.messageTimestamp = new Date();
	}
	
	public Tweet(String messageContent, Date messageTimestamp){
		this.messageContent = messageContent;
		this.messageTimestamp = messageTimestamp;
	}
	
	public void showTweet(){
		System.out.println(this.messageContent + "\t\t" + this.messageTimestamp);
	}

	public String getMessageContent() {
		return messageContent;
	}

	public Date getMessageTimestamp() {
		return messageTimestamp;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public void setMessageTimestamp(Date messageTimestamp) {
		this.messageTimestamp = messageTimestamp;
	}

	
	
	
}
