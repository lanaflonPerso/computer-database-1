/**
 * @Author Vincent Galloy
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
 * The Class EditComputerSession.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EditComputerSession implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6254125356111070814L;
	
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AddComputerSession [computerDto=" + computerDto
				+ ", bindingResult=" + bindingResult + "]";
	}
}
