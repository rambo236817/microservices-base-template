package com.alti.baseTemplate.entity;

import org.springframework.data.annotation.Id;


public class TutorialDomain {

	@Id
	private Integer id;

	private String title;

	private String description;

	private boolean published;

	public TutorialDomain() {

	}

	public TutorialDomain(String title, String description, boolean published) {
		this.title = title;
		this.description = description;
		this.published = published;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getPublished() {
		return published;
	}

	public void setPublished(boolean isPublished) {
		this.published = isPublished;
	}

	@Override
	public String toString() {
		return "Tutorial [id=" + id + ", title=" + title + ", desc=" + description + ", published=" + published + "]";
	}

}
