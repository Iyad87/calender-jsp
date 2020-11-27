package com.iyad.dao;

import java.sql.Timestamp;
import java.util.Date;

public class Event {
	
	private int id;
	private String title;
	private String remark;
	private Timestamp start;
	private Timestamp end;
	private String type;
	private String color;
	private int isOption;
	private int isPublic;

	public Event(){}
	public Event(int id, String title, String remark, Timestamp start, Timestamp end, String type, int isOption, int isPublic) {
		this.id = id;
		this.title = title;
		this.remark = remark;
		this.start = start;
		this.end = end;
		this.type = type;
		this.isOption = isOption;
		this.isPublic = isPublic;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Timestamp getStart() {
		return start;
	}
	public void setStart(Timestamp start) {
		this.start = start;
	}
	public Timestamp getEnd() {
		return end;
	}
	public void setEnd(Timestamp end) {
		this.end = end;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getIsOption() {
		return isOption;
	}
	public void setIsOption(int isOption) {
		this.isOption = isOption;
	}
	public int getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(int isPublic) {
		this.isPublic = isPublic;
	}
	
}

