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
@Table(name = "RISK_ASS_MITIGATION")
public class RiskMitigation implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RISK_MITIGATION_ID")
	private Integer mitigationId;

	private String name;
	private Integer score;
	private Integer cutoffScore;
}
