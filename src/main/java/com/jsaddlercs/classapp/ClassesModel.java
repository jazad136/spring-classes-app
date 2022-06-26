package com.jsaddlercs.classapp;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ClassesModel {
	@Id
	private String entryId;
	private String classesName;
	private List<String> images;
	private List<String> descriptions;
	private List<String> imageCaptions;
	
	public ClassesModel() { } 
	
	public String getEntryId() {
		return entryId;
	}
	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}
	public String getClassesName() {
		return classesName;
	}
	public void setClassesName(String classesName) {
		this.classesName = classesName;
	}
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
	public List<String> getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}
	public List<String> getImageCaptions() {
		return imageCaptions;
	}
	public void setImageCaptions(List<String> imageCaptions) {
		this.imageCaptions = imageCaptions;
	}
	@Override
	public String toString() {
		return "ClassesModel [entryId=" + entryId + ", classesName=" + classesName + ", images=" + images
				+ ", descriptions=" + descriptions + ", imageCaptions=" + imageCaptions + "]";
	}
	
}
