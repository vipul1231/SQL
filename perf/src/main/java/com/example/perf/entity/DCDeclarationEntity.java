package com.example.perf.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
@Table(schema = "cargoes_ro", name = "DECLARATION_HEADER")
public class DCDeclarationEntity {
    @Id
    public Long declarationId;
    @Column(length = 200)
    public String exitPointName;
    public Double declarationVersion;
    public Long regimeTypeId;
    @Column(length = 30)
    public String regimeTypeName;
    public Long declarationTypeId;
    @Column(length = 70)
    public String declarationTypeName;
    public Long requestTransactionTypeId;
    @Column(length = 100)
    public String requestTransTypeNm;
    public Long requestReasonTypeId;
    @Column(length = 1000)
    public String requestReasonTypeDesc;
    public Long cargoStatusTypeId;
    @Column(length = 20)
    public String cargoStatusNameEn;
    public Long purposeTypeId;
    public String purposeTypeDesc;
    @Column(length = 20)
    public String declarationRefNo;
    @Column(length = 10)
    public String loadingPortCode;
    public String loadingPortName;
    @Column(length = 10)
    public String dischargePortCode;
    public String dischargePortName;
    public Long inboundCargoChannel;
    @Column(length = 20)
    public String inboundCargoChannelName;
    @Column(length = 60)
    public String inboundCarrierNo;
    public String inboundCarrierRegNo;
    public Date dateOfArrival;
    public Long outboundCargoChannel;
    @Column(length = 20)
    public String outboundCargoChannelName;
    @Column(length = 20)
    public String outboundCarrierNo;
    @Column(length = 15)
    public String outboundCarrierRegNo;
    public Date dateOfDeparture;
    public Date decClearDate;
    @Column(length = 4)
    public String paymentStatus;
    public Date paymentDate;
    public String destinationCountryCode;
    public String destinationCountryName;
    @Column(length = 10)
    public String originalLoadingPortCode;
    public String originalLoadingPortName;
    public Long exitPointCode;
    public Long declarationNo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "DECLARATION_ID")
    public List<DeclarationInvoiceDetail> declarationInvoiceDetails;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "DECLARATION_ID")
    public List<DeclarationTransportDocument> declarationTransportDocuments;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "DECLARATION_ID")
    public List<DeclarationPaymentDetail> declarationPaymentDetails;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "DECLARATION_ID")
    public List<DeclarationFacilityDetail> declarationFacilityDetails;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "DECLARATION_ID")
    public List<DeclarationDocumentDetail> declarationDocumentDetails;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "DECLARATION_ID")
    public List<DCDeclarationPerImporter> declarationPerImporters;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "DECLARATION_ID")
    public List<DCDeclarationPerExporter> declarationPerExporters;

    @Data
    @Entity
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Table(schema = "cargoes_ro", name = "DECLARATION_INVOICE_DETAIL")
    @Accessors(chain = true)
    public static class DeclarationInvoiceDetail {
        @Id
        public Long invoiceId;
        @Column(length = 60)
        public String sellerName;
        @Column(length = 25)
        public String invoiceTypeNameEn;
        @Column(length = 25)
        public String paymentInstTypeNameEn;
        public Long valuationMethodId;
        @Column(length = 30)
        public String valuationMethodNameEn;
        public Long incotermId;
        @Column(length = 60)
        public String incotermNameEn;
        @Column(length = 3)
        public String invoiceCurrencyCode;
        @Column(length = 35)
        public String invoiceCurrencyEn;
        public Double invoiceValue;
        @Column(length = 3)
        public String freightCurrencyCode;
        @Column(length = 35)
        public String freightCurrencyEn;
        public Double freightCost;
        @Column(length = 3)
        public String insuranceCurrencyCode;
        @Column(length = 35)
        public String insuranceCurrencyEn;
        public Double insuranceCost;
        @Column(length = 20)
        public String invoiceNumber;
        @Column(length = 7)
        public Date invoiceDate;
        public Long invoicePage;
        public Long invoiceTypeId;
        public Long paymentInstTypeId;
        @JoinColumn(name = "INVOICE_ID")
        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        public List<DeclarationInvoiceLineItem> invoiceLineItems;

        @Data
        @Entity
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        @ToString
        @Table(schema = "cargoes_ro", name = "DECLARATION_INVOICE_LINE_ITEM")
        @Accessors(chain = true)
        public static class DeclarationInvoiceLineItem {
            @Id
            public Long invoiceLineItemId;
            public Long declarationId;
            public Long isRestricted;
            public Long serialNo;
            @Column(length = 16)
            public String commodityCode;
            @Column(length = 1024)
            public String goodsDescription;
            public Long goodsCondition;
            @Column(length = 50)
            public String statisticalQuantityUnit;
            @Column(length = 22)
            public Double statisticalQuantity;
            @Column(length = 50)
            public String weightUnit;
            public Double weight;
            @Column(length = 50)
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
            @Column(length = 30)
            public String exemptionRefNo;
            @JoinColumn(name = "INVOICE_LINE_ITEM_ID")
            @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
            public List<DeclarationVehicleDetail> vehicleDetails;
            @JoinColumn(name = "INVOICE_LINE_ITEM_ID")
            @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
            public List<DeclarationInvoiceItemPermit> declarationInvoiceItemPermits;

            //            @Embeddable
//            @Data
//            @NoArgsConstructor
//            @AllArgsConstructor
//            @ToString
//            public class DetailId implements Serializable {
//                private Long invoiceLineItemId;
//                private String invoiceId;
//            }
            @Data
            @Entity
            @Builder
            @NoArgsConstructor
            @AllArgsConstructor
            @ToString
            @Table(schema = "cargoes_ro", name = "DECLARATION_VEHICLE_DETAIL")
            @Accessors(chain = true)
            public static class DeclarationVehicleDetail {
                @Id
                private Long invoiceId;
                @Column(length = 30)
                public String driveTypeNameEn;
                public Long vehicleDriveTypeId;
                @Column(length = 100)
                public String specificationTypeNameEn;
                public Long vehicleManufacturingYear;
                public Long declarationId;
                @Column(length = 24)
                public String vehicleChassisNumber;
                public Long vehicleBrandId;
                @Column(length = 30)
                public String vehicleBrandNameEn;
                @Column(length = 100)
                public String vehicleModel;
                @Column(length = 30)
                public String vehicleEngineNumber;
                public Float engineCapacityLiters;
                public Long passengerCapacity;
                public Float carriageCapacityTons;
                @Column(length = 30)
                public String vehicleColor;
                public Long vehicleCondition;
                public Long vehicleTypeId;
                @Column(length = 30)
                public String vehicleTypeNameEn;
                public Long vehicleSpecificationTypeId;
            }


            @Data
            @Entity
            @Builder
            @NoArgsConstructor
            @AllArgsConstructor
            @ToString
            @Table(schema = "cargoes_ro", name = "DEC_INVOICE_ITEM_PERMIT")
            @Accessors(chain = true)
            public static class DeclarationInvoiceItemPermit implements Serializable {
                @Id
                public Long declarationId;
                @Id
                public Long invoiceId;
                @Column(length = 50)
                public String permitIssuingAuthorityEn;
                @Column(length = 50)
                public String permitTypeNameEn;
                @Column(length = 30)
                public String permitReferenceNumber;
                public Long permitIssuingAuthority;
                public Long permitTypeId;
            }
        }

    }


    @Data
    @Entity
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Table(schema = "cargoes_ro", name = "DECLARATION_TRANSPORT_DOCUMENT")
    @Accessors(chain = true)
    public static class DeclarationTransportDocument {
        @Id
        public Long transportDocumentId;
        @Column(length = 30)
        public String inboundHouseTransDocNo;
        @Column(length = 30)
        public String inboundMasterTransDocNo;
        @Column(length = 30)
        public String outboundHouseTransDocNo;
        @Column(length = 30)
        public String outboundMasterTransDocNo;
        public Double volume;
        @Column(length = 50)
        public String grossWeightUnit;
        public Double grossWeight;
        @Column(length = 50)
        public String netWeightUnit;
        public Double netWeight;
        @Column(length = 50)
        public String volumeUnit;
        public Long cargoTypeId;
        @JoinColumn(name = "transportDocumentId")
        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        public List<DeclarationPackageDetail> declarationPackageDetails;
        @JoinColumn(name = "transportDocumentId")
        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        public List<DeclarationContainerDetail> declarationContainerDetails;

        @Data
        @Entity
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        @ToString
        @Table(schema = "cargoes_ro", name = "DECLARATION_PACKAGE_DETAIL")
        public static class DeclarationPackageDetail {
            @Id
            public Long declarationPackageDetailId;
            public Long declarationId;
            public Long transportDocumentId;
            @Column(length = 350)
            public String markNumber;
            @Column(length = 30)
            public String packageTypeNameEn;
            public Long packageNumber;
            public Long packageTypeId;
        }
        @Data
        @Entity
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        @ToString
        @Table(schema = "cargoes_ro", name = "DECLARATION_CONTAINER_DETAIL")
        public static class DeclarationContainerDetail {
            @Id
            public Long containerDetailId;
            public Long declarationId;
            public Long transportDocumentId;
            public Long containerSize;
            @Column(length = 15)
            public String containerSerialNo;
            public Long containerTypeId;
            @Column(length = 30)
            public String containerTypeNameEn;
            @Column(length = 15)
            public String containerNumber;
        }
    }

        @Data
        @Entity
        @Getter
        @Setter
        @ToString
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        @Table(schema = "cargoes_ro", name = "DECLARATION_PAYMENT_DETAIL")
        @Accessors(chain = true)
        public static class DeclarationPaymentDetail implements Serializable {
            @Id
            public Long preferredPaymentModeId;
            @Id
            public Long referenceNo;
            public Long paymentReferenceNo;
            @Column(length = 30)
            public String preferredPaymentModeNameEn;

        }


        @Data
        @Entity
        @ToString
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        @Table(schema = "cargoes_ro", name = "DECLARATION_FACILITY_DET")
        @Accessors(chain = true)
        public static class DeclarationFacilityDetail {
            @Id
            @Column(length = 20)
            public String impFacilityCode;
            @Column(length = 20)
            public String impFacilityBusinessCode;
            @Column(length = 100)
            public String exportFacilityName;
            @Column(length = 20)
            public String expFacilityCode;
            @Column(length = 20)
            public String expFacilityBusinessCode;
            @Column(length = 100)
            public String importFacilityName;

        }


        @Data
        @Entity
        @ToString
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        @Table(schema = "cargoes_ro", name = "DECLARATION_DOCUMENT_DET")
        @Accessors(chain = true)
        public static class DeclarationDocumentDetail {
            @Id
            public Long declarationDocumentId;
            public Long documentTypeId;
            @Column(length = 100)
            public String docNonAvailabilityReasonEn;
            public Long docAvailabilityStatusId;
            @Column(length = 50)
            public String availStatusNameEn;
            public Long docNonAvailabilityReasonId;
            @Column(length = 100)
            public String documentTypeNameEn;
        }
    }
