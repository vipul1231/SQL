package com.example.perf.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
@Table(schema = "cargoes_ro", name = "DECLARATION_PER_EXPORTER")
public class DCDeclarationPerExporter {

    @Id
    public Long personalCustomerId;
    public String personalCustomerCode;
    public String personalCustomerName;
    public String nationalIdNumber;
    public Long identificationDocTypeId;
    public String identificationDocNumber;
    public String identificationDocCountry;
    public String customerAddress;
    public Long customerCityId;
    public String customerCountryUnCode;
    public String mobileNumber;
    public String customerCat;
}
