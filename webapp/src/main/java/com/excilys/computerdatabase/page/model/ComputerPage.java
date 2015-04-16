/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.page.model;

import java.util.List;

import com.excilys.computerdatabase.dto.model.CompanyDto;
import com.excilys.computerdatabase.dto.model.ComputerDto;

public class ComputerPage {
	private ComputerDto computerDto;
	private List<CompanyDto> companies;
	
	public ComputerDto getComputerDto() {
		return computerDto;
	}
	public void setComputerDto(ComputerDto computerDto) {
		this.computerDto = computerDto;
	}
	public List<CompanyDto> getCompanies() {
		return companies;
	}
	public void setCompanies(List<CompanyDto> companies) {
		this.companies = companies;
	}
	@Override
	public String toString() {
		return "ComputerPage [computerDto=" + computerDto + ", companies="
				+ companies + "]";
	}
}
