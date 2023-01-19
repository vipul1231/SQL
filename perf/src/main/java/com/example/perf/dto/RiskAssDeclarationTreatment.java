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
@Table(name = "RISK_ASS_DECLARATION_TREATMENT")
public class RiskAssDeclarationTreatment implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String locationCode;
	private String locationType;
	private String departmentCode;
	private Long score; // Department wise score
	private Long finalScore; // either score or cutoff score
	private Long cutoffScore;
}
