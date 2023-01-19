package com.example.perf.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Vemula Bojjanna
 *
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "batch_job_details")
//@Audited
@ToString
public class BatchJobDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long batchId;	
	private Long totalNoOfTrasactions;
	private Long totalNoOfProcessedTrasactions;

	private String startDate;
	private String endDate;	
	
	private String status;
	private Integer totalNoOfHits;
	private Integer profileId;	
	private Long jobId;
	private String sandboxRefCode;
	private Long sandBoxDays;
	private LocalDateTime jobStartDate;
	private LocalDateTime jobEndDate;
	private Long executionTime;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "batch_id")
	private List<BatchJobResult> batchJobResults = new ArrayList<>();

}
