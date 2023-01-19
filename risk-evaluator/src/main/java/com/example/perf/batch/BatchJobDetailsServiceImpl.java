package com.example.perf.batch;

import com.example.perf.entity.BatchJobDetails;
import com.example.perf.repository.BatchJobRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Dhruv Vrat
 */
@Service
@Slf4j
public class BatchJobDetailsServiceImpl implements BatchJobDetailsService {

    @Autowired
    private BatchJobRepository batchJobRepository;


    @Override
    public Optional<BatchJobDetails> fetchDeclarationBatchJobDetailsTxn(Integer id) {
        return batchJobRepository.findByBatchId(id);
    }

}
