package com.raimond.startingoverfinalproject.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pages")
public class Page {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id")
	private long id;
//	@Column(name = "title")
	private String title;

	public Page(long id, String title) {
		this.id = id;
		this.title = title;
	}

	public Page() {}

	public Page(String title) {
		this.title = title;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
