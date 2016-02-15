/**
 * @author Vincent Galloy
 */
package com.excilys.computerdatabase.page.model;

import java.util.List;

import com.excilys.computerdatabase.dto.model.CompanyDto;
import com.excilys.computerdatabase.dto.model.ComputerDto;


/**
 * The Class ComputerPage.
 */
public class ComputerPage {
	
	/** The computer dto. */
	private ComputerDto computerDto;
	
	/** The companies. */
	private List<CompanyDto> companies;
	
	/**
	 * Gets the computer dto.
	 *
	 * @return the computer dto
	 */
	public ComputerDto getComputerDto() {
		return computerDto;
	}
	
	/**
	 * Sets the computer dto.
	 *
	 * @param computerDto the new computer dto
	 */
	public void setComputerDto(ComputerDto computerDto) {
		this.computerDto = computerDto;
	}
	
	/**
	 * Gets the companies.
	 *
	 * @return the companies
	 */
	public List<CompanyDto> getCompanies() {
		return companies;
	}
	
	/**
	 * Sets the companies.
	 *
	 * @param companies the new companies
	 */
	public void setCompanies(List<CompanyDto> companies) {
		this.companies = companies;
	}

	@Override
	public String toString() {
		return "ComputerPage [computerDto=" + computerDto + ", companies="
				+ companies + "]";
	}
}
