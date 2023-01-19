
package com.example.perf.dto;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 *  DepartmentScore Entity
 * 
 *  @author Gaurav.verma
 *
 */

@Data
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "RISK_ASS_PROFILE_TREATMENT")
public class RiskAssProfileTreatment implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String departmentCode;
	private Long score;// score * number of hit of line items
	private Integer threshold;// given in profile treatment
	private Long finalScore;//Either score or threshold score
}
