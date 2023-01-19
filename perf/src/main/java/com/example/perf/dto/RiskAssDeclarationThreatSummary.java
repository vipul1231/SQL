package com.example.perf.dto;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data 
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "RISK_ASS_DECL_THREAT_SUMMARY")
public class RiskAssDeclarationThreatSummary implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RISK_ASS_DECL_THREAT_SUM_ID")
	private Integer threatSummaryId;

	private Boolean deductive;
	private String level;
	private Integer score;
	private String name;
}
