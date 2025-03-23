package com.infy.dto;


import java.util.List;


import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;

public class RouteDTO {
	
//	@Size(min = 100, max = 999)
	private Integer id;
	@NotNull
	private String source;
	@NotNull
	private String destination;
	@NotNull
	private List<TrainDTO> trainList;

	public RouteDTO() {
		super();
	}

	public RouteDTO(Integer id, String source, String destination, List<TrainDTO> trainList) {
		super();
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.trainList = trainList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public List<TrainDTO> getTrainList() {
		return trainList;
	}

	public void setTrainList( List<TrainDTO> trainList) {
		this.trainList = trainList;
	}

	@Override
	public String toString() {
		return "RouteDTO [id=" + id + ", source=" + source + ", destination=" + destination + ", trainList=" + trainList
				+ "]";
	}
	
	
}
