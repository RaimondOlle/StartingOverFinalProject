package com.raimond.startingoverfinalproject.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pages")
public class Page {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id")
	private long id;
//	@Column(name = "title")
	private String title;

	@OneToMany(mappedBy = "page", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Button> buttonList;

	public List<Button> getButtonList() {
		return buttonList;
	}

	public void setButtonList(List<Button> buttonList) {
		this.buttonList = buttonList;
	}

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
