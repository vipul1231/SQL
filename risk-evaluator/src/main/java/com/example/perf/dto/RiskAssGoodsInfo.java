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
@Table(name = "RISK_ASS_GOODS_INFO")
public class RiskAssGoodsInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RISK_ASS_GOODS_INFO_ID")
	private Integer riskAssGoodsInfoId;

	private String comodityCode;
	private Long sequenceNo;
	private String type;
	private String description;
	private Integer score;
    protected String transportDocumentNumber;
	
}
