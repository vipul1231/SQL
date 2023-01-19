package com.example.perf.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Gaurav.4.Verma
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class CustomDeclarationDto {
	protected String valuationStatus;
	protected String overallStatus;
	protected Date valuationDate;

	protected String valuationRemarks;

	protected String inspectionStatus;
	protected Date inspectionDate;

	protected String inspectionRemarks;

	protected String clearanceStatus;
	protected Date clearanceDate;

	protected String clearanceRemarks;

	protected String riskAssesmentStatus;

	protected String riskAssementRemarks;
	protected Date riskAssesmentDate;

	protected String paymentStatus;
	protected Date paymentDate;
	protected Date feeCalculationDate;
	protected String feeCalculationStaus;
	protected Date auditDate;

	protected String auditRemarks;

	protected String auditStatus;

	protected String predictionStatus;
	@Data
	@NoArgsConstructor
	@ToString
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class CustomSupportingDocumentInfoDto {
	}

	@Data
	@NoArgsConstructor
	@ToString
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class CustomTransportDocumentInfoDto {
		@Data
		@NoArgsConstructor
		@ToString
		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class CustomContainerInfoDto {
			@Data
			@NoArgsConstructor
			@ToString
			@JsonIgnoreProperties(ignoreUnknown = true)
			public static class CustomSealInfoDto {
			}
		}
	}

	@Data
	@NoArgsConstructor
	@ToString
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class CustomGoodsInfoDto {

		protected BigDecimal importQunatity;
		protected BigDecimal exportQuantity;
		protected BigDecimal balanceQunatity;

		protected String valuationStatus;

		protected String valuationRemarks;

		protected String inspectionStatus;

		protected String inspectionRemarks;
		protected String furtherAction;

		protected String clearanceStatus;

		protected String clearanceRemarks;
		protected String ruleCode;

		@Data
		@NoArgsConstructor
		@ToString
		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class CustomPermitInfoDto {
		}

		@Data
		@NoArgsConstructor
		@ToString
		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class CustomVehicleInfoDto {

		}
	}

	@Data
	@NoArgsConstructor
	@ToString
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class CustomPaymentInfoDto {

	}

	@Data
	@NoArgsConstructor
	@ToString
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class CustomInvoiceInfoDto {
		protected String ruleCode;
	}
}