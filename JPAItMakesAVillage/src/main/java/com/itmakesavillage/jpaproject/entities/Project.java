package com.itmakesavillage.jpaproject.entities;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Project {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	private String title;
	private boolean active;
	@Column(name="owner_id")
	private int ownerId;
	@Column(name="start_date")
	private Date startDate;
	@Column(name="end_date")
	private Date endDate;
	private String description;
	
	private Address address;
	private Time time;
	@Column(name="hours_needed")
	private int hoursNeeded;
	@Column(name="volunteer_needed")
	private int volunteersNeeded;
	private List<Volunteer> volunteers;
	private List<Category> categories;
	
	private List<ProjectVolunteer> projectVolunteer;
	
	
	
	
	public Project() {
	}

	public Project(int id, String title, boolean active, int ownerId, Date startDate, Date endDate, String description,
			Address address, Time time, int hoursNeeded, int volunteersNeeded, List<Volunteer> volunteers,
			List<Category> categories, List<ProjectVolunteer> projectVolunteer) {
		super();
		this.id = id;
		this.title = title;
		this.active = active;
		this.ownerId = ownerId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.address = address;
		this.time = time;
		this.hoursNeeded = hoursNeeded;
		this.volunteersNeeded = volunteersNeeded;
		this.volunteers = volunteers;
		this.categories = categories;
		this.projectVolunteer = projectVolunteer;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", title=" + title + ", active=" + active + ", ownerId=" + ownerId + ", startDate="
				+ startDate + ", endDate=" + endDate + ", description=" + description + ", address=" + address
				+ ", time=" + time + ", hoursNeeded=" + hoursNeeded + ", volunteersNeeded=" + volunteersNeeded + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public List<ProjectVolunteer> getProjectVolunteer() {
		return projectVolunteer;
	}

	public void setProjectVolunteer(List<ProjectVolunteer> projectVolunteer) {
		this.projectVolunteer = projectVolunteer;
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
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public int getHoursNeeded() {
		return hoursNeeded;
	}
	public void setHoursNeeded(int hoursNeeded) {
		this.hoursNeeded = hoursNeeded;
	}
	public int getVolunteersNeeded() {
		return volunteersNeeded;
	}
	public void setVolunteersNeeded(int volunteersNeeded) {
		this.volunteersNeeded = volunteersNeeded;
	}
	public List<Volunteer> getVolunteers() {
		return volunteers;
	}
	public void setVolunteers(List<Volunteer> volunteers) {
		this.volunteers = volunteers;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	
	
	
}
