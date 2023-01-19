package com.example.perf.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

@Data
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "RISK_ASS_DECLARATION")
public class RiskAssDeclaration implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RISK_ASS_DEC_ID")
	private Integer riskAssDecId;

	@Column(length = 100)
	private String referenceCode;

	private Long referanceId;

	private String importer;

	private String agentCode;

	private String status;

	private String regime;

	private String procedure;

	private String modeOfTransport;

	private Integer noOfLines;

	private Integer noOfContainers;

	private String goodsLocation;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate createdDate;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate updatedDate;
	
	private String createdBy;
	
	private String updatedBy;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dateOfarrival;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "RISK_ASS_DECL_ID")
	private List<RiskAssProfile> riskAssProfileList;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "RISK_ASS_DECL_ID")
	private List<RiskAssDeclarationThreatSummary> riskAssDeclarationThreatSummary;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "RISK_ASS_DECL_ID") 
	private List<RiskMitigation> riskMitigation;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "RISK_ASS_DECL_ID") 
	private List<RiskAssDeclarationTreatment> riskAssDeclarationTreatments;
	 	
	private Integer ipr;
	
	private Integer rev;
	
	private Integer val;
	
	private Integer random;
	
	private String inspectionAction;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = JsonFormat.DEFAULT_TIMEZONE)
	private Date acceptanceDateTime; 
	
	private String referanceType;
	
	private Integer totalScore;
	
	private String movementCode;
	
	private Integer noOfTransportDoc;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dateOfdeparture;
	
    protected String submitterCode;
    
    protected String submitterName;
    
    protected String submissionOffice;
    
    private String messageId;
	
	private String docType;
	
	private String importerCode; 
	
	private String exporterCode;
	
	private String regimeCode;
	
	private String consigneeCode;
	
	private Boolean haveSecretProfile;

	@Override
	public String toString() {
		return new StringJoiner(", ", RiskAssDeclaration.class.getSimpleName() + "[", "]")
				.add("riskAssDecId=" + riskAssDecId)
				.add("referenceCode='" + referenceCode + "'")
				.add("referanceId=" + referanceId)
				.add("status='" + status + "'")
				.add("createdDate=" + createdDate)
				.add("dateOfarrival=" + dateOfarrival)
				.add("referanceType='" + referanceType + "'")
				.add("messageId='" + messageId + "'")
				.add("docType='" + docType + "'")
				.toString();
	}
}
