package com.example.perf.dto;

import java.util.stream.Stream;

public enum ErrorCode {
	
	
	RPINVALIDTHRESHOLD("RP-ERR-100", "riskprofile.invalid.threshold"),
	RPDUPLICATEPROFILECODE("RP-ERR-101", "riskprofile.duplicate.profile.code"),
	RPRECORDNOTSAVED("RP-ERR-102", "riskprofile.record.not.save"),
	RPNORECORDFOUND("RP-ERR-103", "riskprofile.record.not.found"),
	RPNORECORDFOUNDWITHGIVENID("RP-ERR-104", "riskprofile.record.not.found.for.id"),
	RPINTERNALSERVERERROR("RP-ERR-105", "riskprofile.internal.server.error"),
	RPPROFILECODENOTGENERATED("RP-ERR-106", "riskprofile.profilecode.generation.error"),
	RPINVALIDINPUT("RP-ERR-107", "riskprofile.invalid.input"),
	RPNOHISTORYRECORDFOUNDWITHGIVENID("RP-ERR-108", "riskprofile.history.record.not.found.for.id"),
	RPNORECORDFOUNDWITHGIVENTREATMENTID("RP-ERR-108", "riskprofile.record.not.found.for.treatmentId"),
	RPDECERROR1("DECERROR1","riskprofile.profileMethod.inductive.invalid.inputs"),
	RPDECERROR2("DECERROR2","riskprofile.profileMethod.deductive.invalid.inputs"),
	RPDECERROR3("DECERROR3","riskprofile.invalid.inputs"),
	RPDECERROR4("DECERROR3","riskprofile.invalid.mimetype"),
	RPDECERROR("DECERROR","riskprofile.des.internal.server.error"),
	//risk assessment error code
	RASRECORDNOTSAVED("RA-ERR-101", "riskassessment.record.not.save"),
	RASINTERNALSERVERERROR("RA-ERR-102", "riskprofile.internal.server.error"),
	RASINVALIDFILEFORMAT("RA-ERR-103", "riskassessment.invalid.file.format"),
	RTRUSTEDTRADERCODENOTGENERATED("TT-ERR-101", "trusted.trader.code.generation.error"),
	AMENDNOTALLOWED("RP-ERR-9065", "riskprofile.amend.not.allowed");
	
	private String shortDescription;
	private String description;
	private String code;
	
	
	private ErrorCode(String code, String shortDesc, String description) {
	    this.code = code;
	    this.description = description;
	    this.shortDescription=shortDesc;
	  }
	
	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private ErrorCode(String code, String shortDesc) {
	    this.code = code;
	    this.shortDescription=shortDesc;
	  }
	
	public static Stream<ErrorCode> stream() {
        return Stream.of(ErrorCode.values()); 
    }
}
