package com.excilys.computerdatabase.session;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.excilys.computerdatabase.dto.model.ComputerDto;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EditComputerSession implements Serializable {
	private static final long serialVersionUID = -6254125356111070814L;
	private ComputerDto computerDto;
	private BindingResult bindingResult;

	public ComputerDto getComputerDto() {
		return computerDto;
	}

	public void setComputerDto(ComputerDto computerDto) {
		this.computerDto = computerDto;
	}

	public BindingResult getBindingResult() {
		return bindingResult;
	}

	public void setBindingResult(BindingResult bindingResult) {
		this.bindingResult = bindingResult;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bindingResult == null) ? 0 : bindingResult.hashCode());
		result = prime * result
				+ ((computerDto == null) ? 0 : computerDto.hashCode());
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
		EditComputerSession other = (EditComputerSession) obj;
		if (bindingResult == null) {
			if (other.bindingResult != null)
				return false;
		} else if (!bindingResult.equals(other.bindingResult))
			return false;
		if (computerDto == null) {
			if (other.computerDto != null)
				return false;
		} else if (!computerDto.equals(other.computerDto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AddComputerSession [computerDto=" + computerDto
				+ ", bindingResult=" + bindingResult + "]";
	}
}
