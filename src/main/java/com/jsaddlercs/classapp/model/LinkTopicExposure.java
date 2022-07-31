package com.jsaddlercs.classapp.model;

import java.util.LinkedList;
import java.util.List;

public class LinkTopicExposure {
	private List<String> algorithms;
	private List<String> languages;
	private List<String> devTools;
	
	public LinkTopicExposure() { 
		algorithms = new LinkedList<>();
		languages = new LinkedList<>();
		devTools = new LinkedList<>();
	}
	
	public List<String> getAlgorithms() { return algorithms = (algorithms == null ? new LinkedList<>() : algorithms); }
	public void setAlgorithms(List<String> value) { this.algorithms = (value == null ? new LinkedList<>() : value); }
	
	public List<String> getLanguages() { return languages = (languages == null ? new LinkedList<>() : languages); }
	public void setLanguages(List<String> value) { this.languages = (value == null ? new LinkedList<>() : value); }
	
	public List<String> getDevTools() { return devTools = (devTools == null ? new LinkedList<>() : devTools); }
	public void setDevTools(List<String> value) { this.devTools = (value == null ? new LinkedList<>() : devTools); }
	
	@Override
	public String toString() {
		return "LinkTopicExposure [algorithms=" + algorithms + ", languages=" + languages + ", devTools=" + devTools
				+ "]";
	}
}
