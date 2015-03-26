/**
 * @Author Vincent Galloy
 */
package com.excilys.computerDataBase.validation;

public class CorrectField {
	private boolean isComputerNameTrue;
	private boolean isIntroducedDateTrue;
	private boolean isDiscontinuedDateTrue;
	private boolean isCompanyIdTrue;

	public CorrectField() {
		super();
		this.isComputerNameTrue = true;
		this.isIntroducedDateTrue = true;
		this.isDiscontinuedDateTrue = true;
		this.isCompanyIdTrue = true;
	}

	public CorrectField(boolean isComputerNameTrue,
			boolean isIntroducedDateTrue, boolean isDiscontinuedDateTrue,
			boolean isCompanyIdTrue) {
		super();
		this.isComputerNameTrue = isComputerNameTrue;
		this.isIntroducedDateTrue = isIntroducedDateTrue;
		this.isDiscontinuedDateTrue = isDiscontinuedDateTrue;
		this.isCompanyIdTrue = isCompanyIdTrue;
	}

	public boolean areAllFieldsOk() {
		return isComputerNameTrue && isIntroducedDateTrue && isDiscontinuedDateTrue && isCompanyIdTrue;
	}
	
	public boolean isComputerNameTrue() {
		return isComputerNameTrue;
	}

	public void setComputerNameTrue(boolean isComputerNameTrue) {
		this.isComputerNameTrue = isComputerNameTrue;
	}

	public boolean isIntroducedDateTrue() {
		return isIntroducedDateTrue;
	}

	public void setIntroducedDateTrue(boolean isIntroducedDateTrue) {
		this.isIntroducedDateTrue = isIntroducedDateTrue;
	}

	public boolean isDiscontinuedDateTrue() {
		return isDiscontinuedDateTrue;
	}

	public void setDiscontinuedDateTrue(boolean isDiscontinuedDateTrue) {
		this.isDiscontinuedDateTrue = isDiscontinuedDateTrue;
	}

	public boolean isCompanyIdTrue() {
		return isCompanyIdTrue;
	}

	public void setCompanyIdTrue(boolean isCompanyIdTrue) {
		this.isCompanyIdTrue = isCompanyIdTrue;
	}
}
