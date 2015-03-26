/**
 * @Author Vincent Galloy
 */
package com.excilys.computerDataBase.dto.page;

import java.util.List;

import com.excilys.computerDataBase.dto.CompanyDto;
import com.excilys.computerDataBase.dto.ComputerDto;
import com.excilys.computerDataBase.validation.CorrectField;

public class ComputerPage {
	private CorrectField correctField;
	private ComputerDto computerDto;
	private List<CompanyDto> companies;
	
	public CorrectField getCorrectField() {
		return correctField;
	}
	public void setCorrectField(CorrectField correctField) {
		this.correctField = correctField;
	}
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
		return "AddEditComputerPage [correctField=" + correctField
				+ ", computerDto=" + computerDto + ", companies=" + companies
				+ "]";
	}	
}
