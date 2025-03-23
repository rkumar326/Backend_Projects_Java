package com.infy.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MentorDTO {
	@NotNull(message = "{mentor.mentorid.absent}")
//	@Size(min=4, max=4, message = "{mentor.mentorid.invalid}")
	
	//or
	@Min(value = 1000, message = "{mentor.mentorid.invalid}")
	@Max(value=9999, message = "{mentor.mentorid.invalid}")
	private Integer mentorId;
	private String mentorName;
	private Integer numberOfProjectsMentored;

	public MentorDTO() {
		super();
	}

	public MentorDTO(Integer mentorId, String mentorName, Integer numberOfProjectsMentored) {
		super();
		this.mentorId = mentorId;
		this.mentorName = mentorName;
		this.numberOfProjectsMentored = numberOfProjectsMentored;
	}

	public Integer getMentorId() {
		return mentorId;
	}

	public void setMentorId(Integer mentorId) {
		this.mentorId = mentorId;
	}

	public String getMentorName() {
		return mentorName;
	}

	public void setMentorName(String mentorName) {
		this.mentorName = mentorName;
	}

	public Integer getNumberOfProjectsMentored() {
		return numberOfProjectsMentored;
	}

	public void setNumberOfProjectsMentored(Integer numberOfProjectsMentored) {
		this.numberOfProjectsMentored = numberOfProjectsMentored;
	}

}

