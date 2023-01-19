package com.example.perf.dto;

import com.example.perf.entity.PredictionResult;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author Gaurav.4.Verma
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class DeclarationDto extends CustomDeclarationDto {

    public Long id;
    public Long declarationId;
    public String exitPointName;
    public Double declarationVersion;
    public String regimeTypeId;
    public String regimeTypeName;
    public String declarationTypeId;
    public String declarationTypeName;
    public String requestTransactionTypeId;
    public String requestTransTypeNm;
    public String requestReasonTypeId;
    public String requestReasonTypeDesc;
    public String cargoStatusTypeId;
    public String cargoStatusNameEn;
    public Long purposeTypeId;
    public String purposeTypeDesc;
    public String declarationRefNo;
    public String loadingPortCode;
    public String loadingPortName;
    public String dischargePortCode;
    public String dischargePortName;
    public String inboundCargoChannel;
    public String inboundCargoChannelName;
    public String inboundCarrierNo;
    public String inboundCarrierRegNo;
    public Date dateOfArrival;
    public String outboundCargoChannel;
    public String outboundCargoChannelName;
    public String outboundCarrierNo;
    public String outboundCarrierRegNo;
    public Date dateOfDeparture;
    public Date decClearDate;
    public String paymentStatus;
    public Date paymentDate;
    public String destinationCountryCode;
    public String destinationCountryName;
    public String originalLoadingPortCode;
    public String originalLoadingPortName;
    public String exitPointCode;
    public String declarationNo;

    public List<DeclarationInvoiceDetail> declarationInvoiceDetails;
    public List<DeclarationTransportDocument> declarationTransportDocuments;
    public List<DeclarationPaymentDetail> declarationPaymentDetails;
    public List<DeclarationFacilityDetail> declarationFacilityDetails;
    public List<DeclarationDocumentDetail> declarationDocumentDetails;

    public List<DeclarationPerImporter> declarationPerImporters;

    public List<DeclarationPerExporter> declarationPerExporters;

    public  List<PredictionResult> predictionResults;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DeclarationInvoiceDetail {

        public Long invoiceId;
        public Double insuranceCost;
        public String insuranceCurrencyEn;
        public String insuranceCurrencyCode;
        public Double freightCost;
        public String freightCurrencyEn;
        public String freightCurrencyCode;
        public Double invoiceValue;
        public String invoiceCurrencyEn;
        public String invoiceCurrencyCode;
        public String incotermNameEn;
        public String incotermId;
        public String valuationMethodNameEn;
        public String valuationMethodId;
        public String paymentInstTypeNameEn;
        public String paymentInstTypeId;
        public String sellerName;
        public String invoiceTypeNameEn;
        public String invoiceTypeId;
        public Long invoicePage;
        public Date invoiceDate;
        public String invoiceNumber;
        public List<DeclarationInvoiceLineItem> invoiceLineItems;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @ToString
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class DeclarationInvoiceLineItem {
            public Long declarationId;
            public Long isRestricted;
            public Long invoiceLineItemId;
            public Long serialNo;
            public String commodityCode;
            public String goodsDescription;
            public Long goodsCondition;
            public String statisticalQuantityUnit;
            public Double statisticalQuantity;
            public String weightUnit;
            public Double weight;
            public String supplementaryQuantityUnitId;
            public Double supplementaryQuantity;
            public Long vehicleIndicator;
            public Double lineItemValue;
            public String originCountryUnCode;
            public String originCountryEn;
            public Long preDeclaration;
            public Long preDecInvoice;
            public Long preDecInvoiceItem;
            public String exemptionTypeNameEn;
            public String exemptionRefNo;
            public Long invoiceId;
            public List<DeclarationVehicleDetail> vehicleDetails;
            public List<DeclarationInvoiceItemPermit> declarationInvoiceItemPermits;

            @Data
            @NoArgsConstructor
            @AllArgsConstructor
            @ToString
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class DeclarationVehicleDetail {
                public String driveTypeNameEn;
                public Long vehicleDriveTypeId;
                public String specificationTypeNameEn;
                public Long vehicleManufacturingYear;
                public Long declarationId;
                public Long invoiceId;
                public Long invoiceLineItemId;
                public String vehicleChassisNumber;
                public Long vehicleBrandId;
                public String vehicleBrandNameEn;
                public String vehicleModel;
                public String vehicleEngineNumber;
                public Float engineCapacityLiters;
                public Long passengerCapacity;
                public Float carriageCapacityTons;
                public String vehicleColor;
                public Long vehicleCondition;
                public Long vehicleTypeId;
                public String vehicleTypeNameEn;
                public Long vehicleSpecificationTypeId;
            }


            @Data
            @NoArgsConstructor
            @AllArgsConstructor
            @ToString
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class DeclarationInvoiceItemPermit {
                public Long declarationId;
                public Long invoiceId;
                public Long invoiceLineItemId;
                public String permitIssuingAuthorityEn;
                public String permitTypeNameEn;
                public String permitReferenceNumber;
                public Long permitIssuingAuthority;
                public Long permitTypeId;
            }
        }

    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DeclarationTransportDocument {
        public Long transportDocumentId;
        public String inboundHouseTransDocNo;
        public String inboundMasterTransDocNo;
        public String outboundHouseTransDocNo;
        public String outboundMasterTransDocNo;
        public Double volume;
        public String grossWeightUnit;
        public Double grossWeight;
        public String netWeightUnit;
        public Double netWeight;
        public String volumeUnit;
        public Long cargoTypeId;
        public List<DeclarationPackageDetail> declarationPackageDetails;
        public List<DeclarationContainerDetail> declarationContainerDetails;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @ToString
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class DeclarationPackageDetail {
            public Long declarationId;
            public Long declarationPackageDetailId;
            public Long transportDocumentId;
            public String markNumber;
            public String packageTypeNameEn;
            public Long packageNumber;
            public Long packageTypeId;
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @ToString
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class DeclarationContainerDetail {
            public Long declarationId;
            public Long transportDocumentId;
            public Long containerDetailId;
            public Long containerSize;
            public String containerSerialNo;
            public Long containerTypeId;
            public String containerTypeNameEn;
            public String containerLong;
        }
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DeclarationPaymentDetail {
        public Long preferredPaymentModeId;
        public Long referenceNo;
        public Long paymentReferenceNo;
        public String preferredPaymentModeNameEn;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DeclarationFacilityDetail {
        public String impFacilityCode;
        public String impFacilityBusinessCode;
        public String exportFacilityName;
        public String expFacilityCode;
        public String expFacilityBusinessCode;
        public String importFacilityName;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DeclarationDocumentDetail {
        public Long declarationDocumentId;
        public Long documentTypeId;
        public String docNonAvailabilityReasonEn;
        public Long docAvailabilityStatusId;
        public String availStatusNameEn;
        public Long docNonAvailabilityReasonId;
        public String documentTypeNameEn;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DeclarationPerImporter {
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

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DeclarationPerExporter {
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
}