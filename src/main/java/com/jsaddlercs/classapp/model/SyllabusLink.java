package com.jsaddlercs.classapp.model;

public class SyllabusLink {
	
	private String link;
	private String offeringSeason;
	private String offeringYear;
	private LinkTopicExposure topicExposure;
	
	public SyllabusLink() { }

	public String getLink() { return link; } 

	public void setLink(String link) { this.link = link; }

	public String getOfferingSeason() { return offeringSeason; }

	public void setOfferingSeason(String offeringSeason) { this.offeringSeason = offeringSeason; }

	public String getOfferingYear() { return offeringYear; }
	
	public Integer getIntegerOfferingYear() { 
		if(offeringYear == null)
			return null;
		else 
			return Integer.parseInt(offeringYear);
	}
	
	public void setOfferingYear(String offeringYear) { this.offeringYear = offeringYear; }

	public LinkTopicExposure getTopicExposure() { return topicExposure; }
	public void setTopicExposure(LinkTopicExposure topicExposure) { this.topicExposure = topicExposure; } 
	
	
}
