/**
 * @author Vincent Galloy
 * 
 */
package com.excilys.computerdatabase.session;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.excilys.computerdatabase.dto.model.ComputerDto;

/**
 * The Class AddComputerSession.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AddComputerSession implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1774961026585136517L;
	
	/** The computer dto. */
	private ComputerDto computerDto;
	
	/** The binding result. */
	private BindingResult bindingResult;

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
	 * Gets the binding result.
	 *
	 * @return the binding result
	 */
	public BindingResult getBindingResult() {
		return bindingResult;
	}

	/**
	 * Sets the binding result.
	 *
	 * @param bindingResult the new binding result
	 */
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
		AddComputerSession other = (AddComputerSession) obj;
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
