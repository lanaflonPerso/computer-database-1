package com.excilys.computerDataBase.entity;

import java.sql.Timestamp;

// TODO: Auto-generated Javadoc
/**
 * The Class Computer.
 */
public class Computer {
	
	/** The id. */
	private Long id;
	
	/** The name. */
	private String name;
	
	/** The introduced. */
	private Timestamp introduced;
	
	/** The discontinued. */
	private Timestamp discontinued;
	
	/** The company_id. */
	private Long company_id;
	
	/**
	 * Instantiates a new computer.
	 */
	public Computer() {
		super();
	}
	
	/**
	 * Instantiates a new computer.
	 *
	 * @param id the id
	 * @param name the name
	 * @param introduced the introduced
	 * @param discontinued the discontinued
	 * @param company_id the company_id
	 */
	public Computer(Long id, String name, Timestamp introduced,
			Timestamp discontinued, Long company_id) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company_id = company_id;
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
	
	/**
	 * Gets the introduced.
	 *
	 * @return the introduced
	 */
	public Timestamp getIntroduced() {
		return introduced;
	}
	
	/**
	 * Sets the introduced.
	 *
	 * @param introduced the new introduced
	 */
	public void setIntroduced(Timestamp introduced) {
		this.introduced = introduced;
	}
	
	/**
	 * Gets the discontinued.
	 *
	 * @return the discontinued
	 */
	public Timestamp getDiscontinued() {
		return discontinued;
	}
	
	/**
	 * Sets the discontinued.
	 *
	 * @param discontinued the new discontinued
	 */
	public void setDiscontinued(Timestamp discontinued) {
		this.discontinued = discontinued;
	}
	
	/**
	 * Gets the company_id.
	 *
	 * @return the company_id
	 */
	public Long getCompany_id() {
		return company_id;
	}
	
	/**
	 * Sets the company_id.
	 *
	 * @param company_id the new company_id
	 */
	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced="
				+ introduced + ", discontinued=" + discontinued
				+ ", company_id=" + company_id + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((company_id == null) ? 0 : company_id.hashCode());
		result = prime * result
				+ ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (company_id == null) {
			if (other.company_id != null)
				return false;
		} else if (!company_id.equals(other.company_id))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
