/**
 * 
 */
package com.example.perf.v1.constants;

/**
 * @author Ratnesh.Gupta
 *
 */
public enum RiskInformationStatus {
	DRAFT("draft"), PENDING("pending"), PENDING_FOR_INFORMATION("pending for information"),
	PENDING_FOR_APPROVAL("pending for approval"), APPROVED("approved");
	
	private String status;

	/**
	 * @param status
	 */
	private RiskInformationStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

}
