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
@Table(name = "RISK_ASS_ITEM_DETAILS")
public class RiskAssItemDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RISK_ASS_ITEM_DETAILS_ID")
	private Integer riskAssItemDetailsId;

	private String comodityCode;
	private Long sequenceNo;
	private String type;
	private String description;
	private Integer score;
	
}
