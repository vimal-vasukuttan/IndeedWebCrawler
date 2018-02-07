package com.crawler.com;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BasicIndeedCrawler {
	private List<JobDetails> jobDetailList;
	private String indeedUrl = "https://de.indeed.com/jobs?q=java&l=Dusseldorf&fromage=last";
	private String viewJob = "https://de.indeed.com/Zeige-Job?jk=";
	private static final int MAX_PAGE_TO_SCRAP = 2;

	public BasicIndeedCrawler() {
		jobDetailList = new ArrayList<JobDetails>();
	}

	/**
	 * This method will do web page Scraping if Indeed
	 * 
	 * @param page
	 */
	private void updateDetailsByPage(int page) {
		try {
			// 2. Fetch the HTML code
			if (page != 0) {
				indeedUrl = indeedUrl + "&start=" + ((page++) * 10);
			}
			System.out.println("Url :" + indeedUrl);
			Document document = Jsoup.connect(indeedUrl).get();
			Elements elements = document.getElementsByClass("  row  result");

			JobDetails jobDetails;
			for (Element eachDiv : elements) {
				Element jobTitle = eachDiv.getElementsByClass("jobtitle").first();
				Element companyName = eachDiv.getElementsByClass("company").first();
				Element location = eachDiv.getElementsByClass("location").first();

				String jobName = jobTitle.select("a[href]").text();
				String jobLink = viewJob + eachDiv.dataset().get("jk");
				String company = companyName.text();
				String jobLocation = location.text();
				String availabiltyFrom = eachDiv.getElementsByClass("date").first().text();

				jobDetails = new JobDetails();
				jobDetails.setJobTitle(jobName);
				jobDetails.setDetailsLink(jobLink);
				jobDetails.setCompanyName(company);
				jobDetails.setLocation(jobLocation);
				jobDetails.setDateOfPostingSince(availabiltyFrom);

				jobDetailList.add(jobDetails);
			}
			System.out.println(" -----");

		} catch (Exception ex) {
			System.err.println("For '" + indeedUrl + "': " + ex.getMessage());
		}
	}

	/**
	 * Use this method for retrieving data as list
	 * 
	 * @return
	 */
	public List<JobDetails> retrivedetailsAsList() {
		int pageNo = 0;
		while (pageNo < MAX_PAGE_TO_SCRAP) {
			updateDetailsByPage(pageNo);
			pageNo++;
		}
		//applyLogicOnDataRetrived();
		return this.jobDetailList;

	}

	/**
	 * Use this method if to retrive data as Json
	 * @return
	 */
	public String retrivedetails() {
		int pageNo = 0;
		while (pageNo < MAX_PAGE_TO_SCRAP) {
			updateDetailsByPage(pageNo);
			pageNo++;
		}
		return getJsonString();

	}

	/**
	 * Method to create Json if already processed
	 * @return
	 */
	public String getJsonString() {
		String v = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			v = mapper.writeValueAsString(this.jobDetailList);
		} catch (Exception ex) {

		}
		return v;
	}

	/**
	 * Method used to add if any business details are to be added.
	 */
	private void applyLogicOnDataRetrived() {

		Predicate<JobDetails> jobsNotLessThan30Tag = jd -> !jd.getDateOfPostingSince().matches(".*30.*Tagen.*");
		this.jobDetailList = this.jobDetailList.stream().filter(jobsNotLessThan30Tag).collect(Collectors.toList());

		// TODO Check other favourable condition

	}
}
