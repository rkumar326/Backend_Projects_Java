package com.infy.entity;


import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


// Owner
@Entity
public class Route {
	@Id
	private Integer id;
	private String source;
	private String destination;
	
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name="trainList")
	@OneToMany(mappedBy = "route")
	private List<Train> trainList;

	public Route() {
		super();
	}

	public Route(Integer id, String source, String destination, List<Train> trainList) {
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

	public List<Train> getTrainList() {
		return trainList;
	}

	public void setTrainList(List<Train> trainList) {
		this.trainList = trainList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(destination, id, source, trainList);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Route other = (Route) obj;
		return Objects.equals(destination, other.destination) && Objects.equals(id, other.id)
				&& Objects.equals(source, other.source) && Objects.equals(trainList, other.trainList);
	}
	
	
	
	
}

