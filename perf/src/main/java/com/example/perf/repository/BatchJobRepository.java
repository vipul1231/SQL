package com.example.perf.repository;

import com.example.perf.entity.BatchJobDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Vemula Bojjanna
 */

@Repository
public interface BatchJobRepository extends JpaRepository<BatchJobDetails, Integer>{


    BatchJobDetails findByJobId(Long jobId);

    @Query(value = "SELECT * FROM intelligencemgmt_schema.BATCH_JOB_DETAILS  where profile_id=:profileId", nativeQuery = true)
    List<BatchJobDetails> findJobDetailsByProfileId(Integer profileId);

    Optional<BatchJobDetails> findByBatchId(Integer batchId);

    @Modifying
    @Query(value = "update intelligencemgmt_schema.batch_job_details set total_no_of_hits = (select count(*) from intelligencemgmt_schema.batch_job_result where batch_id = :batchId ) where batch_id=:batchId", nativeQuery = true)
    void updateTotalHitCount(Integer batchId);


    @Modifying
    @Query(value = "update intelligencemgmt_schema.batch_job_details set total_no_of_processed_trasactions  = ((select total_no_of_processed_trasactions from intelligencemgmt_schema.batch_job_details where batch_id = :batchId ) +1) where batch_id=:batchId", nativeQuery = true)
    void updateProcessedCount(Integer batchId);

}