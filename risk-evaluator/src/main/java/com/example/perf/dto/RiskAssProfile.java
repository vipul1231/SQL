package com.example.perf.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "RISK_ASS_PROFILE")
public class RiskAssProfile implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RISK_ASS_PROFILE_ID")
	private Integer riskAssProfileId;

	private String trs;
	private Long ruleId;
	private String category;
	private String categoryCode;
	private String riskIndicator;
	private String ruleDetails;
	private Integer score;
	private String riskType;
	private String recomendedAction;
	private String riskRuleOwner;
	private String inspectionCaseAction;
	private String ruleCode;
	private Integer thresholdScore;
	private String profileCode;
	private String submitterRemarks;
	private String profileName;
	private Integer finalScore;
	private String profileSingificance;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "RISK_ASS_PRO_ID")
	private List<RiskAssTransportDocInfo> riskAssTransportDocInfo;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "RISK_ASS_PRO_ID")
	private List<RiskAssItemDetails> riskAssItemsDetailsList;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "RISK_ASS_PRO_ID")
	private List<RiskAssProfileTreatment> riskAssProfileTreatment;
	
    private String riskSubject;
	
	@Column(length=5000)
	private String recomendedActionLabel;
	private String profileType;

	private String frequency;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate hitDate;


	@Transient
	private boolean blueLaneFlag;

}


