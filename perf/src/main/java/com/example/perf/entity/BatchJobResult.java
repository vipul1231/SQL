package com.example.perf.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Dhruv Vrat
 *
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "batch_job_result")
@ToString
public class BatchJobResult implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long batchJobResultId;	
	private Long declarationNumber;
	private String regime;
	private String declarationType;
	private Long declarationId;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date submittedDate;
	private String traderName;
	private String agencyName;
	private String version;
//	private Long batchId;
	private Boolean hit;
}
