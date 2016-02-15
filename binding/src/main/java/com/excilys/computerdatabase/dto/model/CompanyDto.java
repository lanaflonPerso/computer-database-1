package com.excilys.computerdatabase.dto.model;

import java.io.Serializable;

/**
 * @author Vincent Galloy
 *         The Class CompanyDto.
 */
public class CompanyDto implements Serializable {
    private static final long serialVersionUID = -3498505972646793146L;
    private String id;
    private String name;

    /**
     * Instantiates a new company dto.
     */
    public CompanyDto() {
        super();
    }

    /**
     * Instantiates a new company dto.
     *
     * @param id   the id
     * @param name the name
     */
    public CompanyDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        CompanyDto other = (CompanyDto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CompanyDto [id=" + id + ", name=" + name + "]";
    }
}
