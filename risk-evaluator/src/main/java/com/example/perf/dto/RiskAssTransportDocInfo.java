package com.example.perf.dto;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RISK_ASS_TRANSPORT_DOC")
public class RiskAssTransportDocInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RISK_ASS_TRANSPORT_DOC_ID")
	private Integer riskAssTransportDocInfoId;
	private Long sequenceNo;
	private String type;
	private String description;
	private Integer score;
	private String bolNumber;
	private String regimeType;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "RISK_ASS_TRANS_DOC_ID")
	private List<RiskAssGoodsInfo> riskAssGoodsInfo;
	
	
}
