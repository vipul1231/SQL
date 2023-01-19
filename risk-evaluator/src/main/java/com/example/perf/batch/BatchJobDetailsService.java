package com.example.perf.batch;

import com.example.perf.entity.BatchJobDetails;

import java.util.Optional;

/**
 * 
 * @author Vemula Bojjanna
 */
public interface BatchJobDetailsService {
	Optional<BatchJobDetails> fetchDeclarationBatchJobDetailsTxn(Integer id);

}
