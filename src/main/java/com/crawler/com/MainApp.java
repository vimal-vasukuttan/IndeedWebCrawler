package com.crawler.com;

import java.util.List;

public class MainApp {

	public static void main(String[] args) {
		BasicIndeedCrawler indeedCrawler = new BasicIndeedCrawler();
		List<JobDetails> jobDetails = indeedCrawler.retrivedetailsAsList();
		//SwingDisplay.display(jobDetails);
		//MailingInformation.sendMail(indeedCrawler.getJsonString());

	}
	
}
