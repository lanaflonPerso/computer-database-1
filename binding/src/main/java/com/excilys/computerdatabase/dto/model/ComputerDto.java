package com.excilys.computerdatabase.dto.model;

import com.excilys.computerdatabase.dto.validator.Date;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @author Vincent Galloy
 *         The Class ComputerDto.
 */
public class ComputerDto implements Serializable {
    private static final long serialVersionUID = 7688863162139310127L;
    private String id;
    @NotBlank(message = "{error.invalid.computer.name}")
    private String name;
    @Date(message = "{error.invalid.introduced.date} {date.condition}")
    private String introduced;
    @Date(message = "{error.invalid.discontined.date} {date.condition}")
    private String discontinued;
    private String companyId;
    private String companyName;

    /**
     * Instantiates a new computer dto.
     */
    public ComputerDto() {
        super();
    }

    /**
     * Instantiates a new computer dto.
     *
     * @param id           the id
     * @param name         the name
     * @param introduced   the introduced
     * @param discontinued the discontinued
     * @param companyId    the company id
     * @param companyName  the company name
     */
    public ComputerDto(String id, String name, String introduced,
                       String discontinued, String companyId, String companyName) {
        this.id = id;
        this.name = name;
        this.introduced = introduced;
        this.discontinued = discontinued;
        this.companyId = companyId;
        this.companyName = companyName;
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

    public String getIntroduced() {
        return introduced;
    }

    public void setIntroduced(String introduced) {
        this.introduced = introduced;
    }

    public String getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(String discontinued) {
        this.discontinued = discontinued;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
        result = prime * result
                + ((companyName == null) ? 0 : companyName.hashCode());
        result = prime * result
                + ((discontinued == null) ? 0 : discontinued.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((introduced == null) ? 0 : introduced.hashCode());
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
        ComputerDto other = (ComputerDto) obj;
        if (companyId == null) {
            if (other.companyId != null)
                return false;
        } else if (!companyId.equals(other.companyId))
            return false;
        if (companyName == null) {
            if (other.companyName != null)
                return false;
        } else if (!companyName.equals(other.companyName))
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

    @Override
    public String toString() {
        return "ComputerDto [id=" + id + ", name=" + name + ", introduced="
                + introduced + ", discontinued=" + discontinued + ", companyId="
                + companyId + ", companyName=" + companyName + "]";
    }
}
