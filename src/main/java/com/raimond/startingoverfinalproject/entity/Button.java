package com.raimond.startingoverfinalproject.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "buttons")
public class Button {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id")
	private long id;
	private String name;
	private short locationOnBoardId;

	public Button(String name, short locationOnBoardId) {
		this.name = name;
		this.locationOnBoardId = locationOnBoardId;
	}

	public Button(long id, String name, short locationOnBoardId) {
		this.id = id;
		this.name = name;
		this.locationOnBoardId = locationOnBoardId;
	}

	public Button() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getLocationOnBoardId() {
		return locationOnBoardId;
	}

	public void setLocationOnBoardId(short locationOnBoardId) {
		this.locationOnBoardId = locationOnBoardId;
	}
}
