package com.excilys.computerDataBase.entity;

// TODO: Auto-generated Javadoc
/**
 * The Class Company.
 */
public class Company {
	
	/** The id. */
	private Long id;
	
	/** The name. */
	private String name;
	
	/**
	 * Instantiates a new company.
	 *
	 * @param id the id
	 * @param name the name
	 */
	public Company(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}	
}
