package com.example.perf.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class PredictionResult {

    public enum Status{
        SUSPICIOUS,FALSE_POSITIVE;

        public static Status getStatus(String statusStr){
            for(Status status: Status.values()){
                if(status.name().equalsIgnoreCase(statusStr)){
                    return status;
                }
            }
            return null;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerCode;
    private String customerName;
    private String reason;
    private String detail;
    @Enumerated(EnumType.STRING)
    private Status status;
}
