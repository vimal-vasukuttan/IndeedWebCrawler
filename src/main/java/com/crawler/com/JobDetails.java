package com.crawler.com;

public class JobDetails {
	
	private String jobTitle;
	private String detailsLink;
	private String jobDescription;
	private String dateOfPostingSince;
	private String location;
	private String companyName;
	private double distance;
	private boolean suitable;
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getDetailsLink() {
		return detailsLink;
	}
	public void setDetailsLink(String detailsLink) {
		this.detailsLink = detailsLink;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
 
	public String getDateOfPostingSince() {
		return dateOfPostingSince;
	}
	public void setDateOfPostingSince(String dateOfPostingSince) {
		this.dateOfPostingSince = dateOfPostingSince;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public boolean isSuitable() {
		return suitable;
	}
	public void setSuitable(boolean suitable) {
		this.suitable = suitable;
	}
	
	

}
