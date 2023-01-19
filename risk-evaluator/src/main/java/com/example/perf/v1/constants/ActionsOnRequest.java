/**
 * 
 */
package com.example.perf.v1.constants;

/**
 * @author Ratnesh.Gupta
 *
 */
public enum ActionsOnRequest {
	CREATE("create"),MODIFY("modify"),REVIEW("review"), PREVIEW("preview"), DOWNLOAD("download"), RESPOND("respond");
	private String action;

	/**
	 * @param action
	 */
	private ActionsOnRequest(String action) {
		this.action = action;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

}
