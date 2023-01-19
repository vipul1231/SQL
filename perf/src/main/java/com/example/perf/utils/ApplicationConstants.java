package com.example.perf.utils;

import java.util.ArrayList;
import java.util.List;

public final class ApplicationConstants {

    public static final Integer ONE = 1;
    public static final Integer THREE = 3;

    public static final Integer THOUSAND = 1000;
    public static final List<String> ACCEPTED_FILE_TYPES = new ArrayList<>() {

        private static final long serialVersionUID = 3804317633263638674L;

        {
            //file.xls
            add("application/vnd.ms-excel");
            //file.xlsx
            add(XLS_FILE_TYPE);
        }
    };
    public static final String TEMPLATES_FOLDER = "./templates";
    public static final String MM_DD_YYYY = "MM-dd-yyyy";

    public static final String DEDUCTIVE = "deductive";
    public static final String DEFAULT_LOCALE = "en";

    public static final String SUCCESS = "Success";
    public static final String WARNING = "Warning";
    public static final String ERROR = "Error";
    public static final String VALIDATION_ERROR = "Validation failed";
    public static final String NO_DATA_FOUND = "No Data Found";
    public static final String PROFILE_CODE_NOT_GENERATED = "Profilecode is not generated properly";
    public static final String THRESHOLD_SCORE = "Threshold score should be less than total score";
    public static final int THRESHOLD_SCORE_CODE = 1001;
    public static final String THRESHOLD_VALIDATION = "Threshold validation failed";
    public static final String PROFILE_CODE_EXIST = "Profile code already exist";
    public static final String PROFILE_NOT_ABLE_TO_CONNECT_DES = "Could not perform workflow validations, Not able to connect service.";
    public static final String PROFILE_DES_ERROR = "Could not perform workflow validations IN DES, Not able to connect service.";
    public static final int DUPLICATE_PROFILE_CODE = 1002;

    public static final String DECLARATION_DECISION_KEY = "declaration";
    public static final String DECLARATION_DECISION_KEY_RISK = "declaration_risk_asst";
    public static final String DECLARATION_DECISION_KEY_RANDOM = "declaration_random";

    public static final String PROFILE_TARGET_DECLARATION = "Declaration";
    public static final String PROFILE_TARGET_SHIPMENT = "Shipment";
    public static final String PROFILE_TARGET_DOCUMENTS = "Documents";
    public static final String PROFILE_TARGET_SHIPPING = "Shipping";
    public static final String ITEM_DECISION_KEY = "items";
    public static final String ITEM_DECISION_KEY_RISK = "items_risk_asst";
    public static final String INVOICE_DECISION_KEY = "invoice";
    public static final String INVOICE_DECISION_KEY_RISK = "invoice_risk_asst";
    public static final String CREATEDBY = "SYSTEM";
    public static final String RISK_ASS_STATUS = "Pending";
    public static final String RISK_ASS_STATUS_COMPLETED = "Completed";
    public static final String PROFILE_FIRST_VERSION = "1.0";
    public static final String PROFILE_FIRST_APPROVE_VERSION = "1.0";
    public static final String PROFILE_HEADER = "Profile Header";
    public static final String PROFILE_CREATE = "create";
    public static final String PROFILE_AMEND = "amend";
    public static final String PROFILE_UPDATE = "update";
    public static final String SUBMIT_DATE_FORAMAT = "dd-MM-yyyy HH:mm";
    public static final String PROFILE_TYPE_SECRET = "Secret";
    /**Constansts use to Create A DMN**/
    public static final String TARGETED_FIELD_ITEM = "item";
    public static final String TARGETED_FIELD_DECLARATION = "declaration";
    public static final String TARGETED_FIELD_INVOICE = "invoice";

    public static final String TARGETED_FIELD_SHIPPINGINFO = "shippingInfo";
    public static final String TARGETED_FIELD_VESSELINFO = "vesselInfo";
    public static final String TARGETED_FIELD_CONTAINERINFO = "containerInfo";
    public static final String TARGETED_FIELD_TRANSPORTINFO = "transportInfo";
    public static final String DECLARATION_OUTPUT_SCORE = "Score";
    public static final String DECLARATION_OUTPUT_THRESHHOLDSCORE = "thresholdscore";
    public static final String DECLARATION_OUTPUT_RISKRULEOWNER="RiskRuleOwner";
    public static final String DECLARATION_OUTPUT_PROFILECODE="profileCode";
    public static final String DECLARATION_OUTPUT_ITEM="items";
    public static final String DECLARATION_OUTPUT_INVOICE="invoice";
    public static final String DECLARATION_DECISION_ID = "DeclarationDmn";
    public static final String DECLARATION_DECISION_NAME_SUFFIX = "Decision";
    public static final String DMN_HIT_POLICY_COLLECT = "COLLECT";
    public static final String DECISION_TABLE_ID_SUFFIX = "decisionTable";
    public static final String DECLARATION_OUTPUT_PROFILECATEGORY="Category";
    public static final String DECLARATION_OUTPUT_RISKTYPE="RiskType";
    public static final String DECLARATION_OUTPUT_RULECODE="ruleCode";
    public static final String DECLARATION_OUTPUT_RULEID="RuleId";
    public static final String DECLARATION_OUTPUT_RECOMMENDEDACION="recommendedAction";
    public static final String DECLARATION_OUTPUT_TRS="TRS";
    public static final String DECLARATION_OUTPUT_RISKINDICATOR="RiskIndicator";
    public static final String DECLARATION_OUTPUT_INSPECTIONCASEACTION="InspectionCaseAction";
    public static final String DECLARATION_OUTPUT_AEOTRADERSNAME="AeoTradersName";
    public static final String DECLARATION_RISK_ASS_DECISION_ID = "Declaration_Risk_Asst";

    /**Constansts used for risk assessment logic**/
    public static final String INSPECTION_ACTION_NA = "NA";
    public static final String INSPECTION_ACTION_DV = "DV";
    public static final String INSPECTION_ACTION_PI = "PI";
    public static final String INSPECTION_ACTION_SI = "SI";
    public static final String INSPECTION_ACTION_PCA = "PCA";
    public static final String DEDUCTIVE_C = "Deductive";

    public static final String CACHED_POOL_YES = "Yes";
    public static final String CACHED_POOL_NO = "No";




    public static final double FIRST_VERSION = 1.0;
    public static final String RISK_INFORMATION_REQUEST = "Risk Information Request";
    public static final String BPS_RISK_INFO_REQ="RiskInformationRequest";
    public static final String FAILURE = "Failure";
    public static final String PROFILE_METHOD_INDUCTIVE = "Inductive";


    public static final String DECLARATION_KEY = "declaration";
    public static final String INVOICE_KEY = "invoiceInfo";
    public static final String SUPPORTING_DOCUMENT_KEY = "supportingDocumentInfo";
    public static final String TRANSPORT_DOCUMENT_KEY = "transportDocumentInfo";
    public static final String CONTAINER_INFO_KEY = "containerInfo";
    public static final String ITEM_KEY = "goodsInfo";
    public static final String PAYMENT_KEY = "paymentInfo";
    public static final String PERMIT_KEY = "permitInfo";
    public static final String VEHICLE_INFO_KEY = "vehicleInfo";
    public static final String SEAL_INFO_KEY = "sealInfo";


    public static final String DECLARATION_NEW_KEY = "declaration_risk_asst";
    public static final String INVOICE_NEW_KEY = "invoice_risk_asst";
    public static final String SUPPORTING_DOCUMENT_NEW_KEY = "supportingDocument_risk_asst";
    public static final String TRANSPORT_DOCUMENT_NEW_KEY = "transportDocument_risk_asst";
    public static final String CONTAINER_NEW_KEY = "container_risk_asst";
    public static final String ITEM_NEW_KEY = "goods_risk_asst";
    public static final String PAYMENT_NEW_KEY = "payment_risk_asst";
    public static final String PERMIT_NEW_KEY = "permit_risk_asst";
    public static final String VEHICLE_NEW_KEY = "vehicle_risk_asst";
    public static final String SEAL_NEW_KEY = "seal_risk_asst";

    public static final String PROFILE_TARGET_MANIFEST = "Manifest";
    public static final String DECLARATION_RISK_ASS_MANIFEST_ID = "Man_HeaderInfo";

    public static final String HEADER_KEY = "man_headerInfo";
    public static final String PARTY_KEY = "man_party";
    public static final String ROUTING_KEY = "man_routingInfo";
    public static final String TRANS_DOC_KEY = "man_transportDocumentInfo";
    public static final String CONTAINER_KEY = "man_containerInfo";
    public static final String GOODSINFO_KEY = "man_goodsInfo";
    public static final String TRANS_ROUTING_KEY = "man_routingInfo_Transport";
    public static final String TRANS_PARTY_KEY = "man_partyInfo";
    public static final String VEHICLE_KEY = "man_vehicleInfo";
    public static final String SEAL_KEY = "man_sealInfo";

    public static final String MANIFEST_KEY = "manifest";
    public static final String PARTIES_KEY = "parties";
    public static final String MANIFEST_ROUTING_INFOES_KEY = "manifest_routingInfoes";
    public static final String TRANSPORT_DOCUMENT_INFOES_KEY = "transportDocumentInfoes";
    public static final String CONTAINER_INFOES_KEY = "containerInfoes";
    public static final String GOODS_INFOES_KEY = "goodsInfoes";
    public static final String PARTY_INFOES_KEY = "partyInfoes";
    public static final String ROUTING_INFOES_KEY = "routingInfoes";
    public static final String VEHICLE_INFOES_KEY = "vehicleInfoes";
    public static final String SEAL_INFOES_KEY = "sealInfoes";


    public static final String EMAIL_TEMPLATE="email-template";

    public static final String EMAIL_SUBJECT="email-subject";

    public static final String DES_EVALUATE_RESULT_SIZE="Inside the desEvaluate DesEvaluate Result Size= ";

    public static final String DES_KEY="pushNotification";

    public static final String RISK_AREA_RMS = "RMS";
    public static final String CHANNELS_IDENTIFIER = "2";

    public static final String PROFILE_CATAGORY_DECL = "Declaration";
    public static final double RISK_CONFIG_FIRST_VERSION = 1.0;


    public static final String PENDING = "Pending";
    public static final String FAILED = "Failed";
    public static final String COMPLETED = "Completed";
    public static final String INPROGRESS = "IN_PROGRESS";
    public static final String EC_MANIFEST = "EC_MANIFEST";

    //----------------------------------DMN Strategy------------------------
    public static final String DECLARATION_ONLY = "D";
    public static final String MANIFEST_ONLY = "M";
    public static final String DECL_MAN = "DM";
    public static final String REGIME_IMPORT = "1";
    public static final String REGIME_EXPORT = "2";
    public static final String SYSTEM = "SYSTEM";


    public static final String TRUSTED_TRADER_CODE_NOT_GENERATED = "Failed to generated the Trusted Trader code.";
    public static final String MANIFEST_RISK_ASS_ID = "Manifest_Risk_Assessment";
    public static final String MANIFEST_KEY_STANDALONE = "manifest_assessment";
    public static final String MANIFEST = "MANIFEST";

    //Added for DC-242
    public static final String MANIFEST_BL = "manifestBL";
    public static final String MANIFEST_DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";

    // EC Manifest DMN

    public static final String PROFILE_CATEGORY_EC_MANIFEST = "expressCourier";

    public static final String TARGET_FIELD_EC_MANIFEST_CONSOLIDATED_BL = "ecConsolidatedBl";
    public static final String TARGET_FIELD_EC_MANIFEST_MANIFEST_BL = "ecManifestBl";

    public static final String EC_MANIFEST_MANIFEST_BL_ENTITIES = "manifestBLList";

    public static final String EC_MANIFEST_OUTPUT_PROFILECODE = "profileCode";

    public static final String DMN_EC_MANIFEST = "ECManifestDMN";

    public static final String DEFINITION_EC_MANIFEST = "ecManifestDefinition";

    public static final String DECISION_EXPRESS_COURIER = "express_courier_manifest";

    public static final String DECISION_TABLE_SUFFIX = "DecisionTable";

    public static final String EC_BL = "ecManifestBl";

    public static final String EC_MANIFEST_KEY_STANDALONE = "ExpressCourier";

    public static final String IMPORTER_COUNTRY_CODE = "214";

    public static final String RISK_INFO_MEDIA_SOURCE = "Media";

    public static final String RISK_INFO_DEFAULT_PRIORITY = "3";
    public static final String APPROVED = "APPROVED";
    public static String DAILY="Daily";
    public static String WEEKLY="Weekly";
    public static String MONTHLY="Monthly";
    public static String DateRange="DateRange";
    public static String PROFILE="PROFILE";

    public static final String PASSWORD_MGMT_DMN = "password_mgmt";


    public static final String ACTIVE = "Yes";
    public static final String INACTIVE = "No";

    public enum ProfileSchedulerStatus{
        PENDING(RISK_ASS_STATUS),RUNNING("Running"),EXPIRED("Expired");

        String code;
        ProfileSchedulerStatus(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    //-----------DMN-Constants-----------//
    public static final String PROFILECODE = "profileCode";
    public static final String DEFAULT_RULE_CATEGORY = "declaration";
    public static final String OUTPUT = "output";
    public static final String C_DECLARATION = "DECLARATION";
    public static final String C_SYSTEM = "SYSTEM";
    public static final String DEFAULT_REGIME = "IMP";

    public static final String NO_PROFILE_HIT = "No Profile Hit";
    public static final String MAIN = "main";
    public static final String DOM_NOT_VALID = "DOM is not valid";
    public static final String COLLECT = "COLLECT";
    public static final String LIST = "list";
    public static final String STRING = "String";
    public static final String S_TRUE = "true";
    //-----------DMN-Constants-----------//
    public static final String RULE_CATEGORY_DMN_GENERATION = "chief-declaration";

    public static final String FILE_DOWNLOAD_SUCCESSFULLY = "File Downloaded Successfully";

    public static final String ISO_DATE_FORMAT = "yyyy-MM-dd";

    public static final String FILE_UPLOAD_TYPE = "XLS";

    public static final String XLS_FILE_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static final String HEADER_SHEET = "Header Details";

    public static final String ITEM_DETAILS_SHEET = "Item Details";

    public static final String START_RISK_ASSESSMENT_REQUEST = "Start risk Assessment for request is {}";

    public static final String DECL_REQ = "Decl Req - ";

    public static final String RISK_INFO_SEARCH_NOT_FOUND = "Risk information Search Result not found for (pagenumber -";
    public static final String PAGE_SIZE = " pagesize ";
    public static final String TRUSTED_TRADER_CREATION = "Update Trusted Trader Creation starts";

    public static final String EXCEPTION_ERROR = "error {}";
    public static final String UPPER_CASE = "upper case(";

    public static final String CONTAINS = "contains";
    public static final String CONTAINS_FIELDS = "contains(";
    public static final String NOT_IN = "notIn";
    public static final String AND = " and ";
    public static final String PROFILE_CODE = "profileCode";
    public static final String PAYMENT_OUTPUT = "paymentOutput";
    public static final String INVOICE_OUTPUT = "invoiceOutput";
    public static final String SUPPORTING_DOC_OUTPUT = "supportingDocumentOutput";
    public static final String TRANSPORT_DOC_OUTPUT = "transportDocumentOutput";
    public static final String CONTAINER_OUTPUT = "containerOutput";
    public static final String VEHICLE_OUTPUT = "vehicleOutput";
    public static final String SEAL_OUTPUT = "sealOutput";
    public static final String PERMIT_OUTPUT = "permitOutput";
    public static final String ITEM_OUTPUT = "itemOutput";
    public static final String PARTY_OUTPUT = "partyOutput";
    public static final String ROUTING_OUTPUT = "routingOutput";
    public static final String TRANSPORT_DOC_INFOES_OUTPUT = "transportDocumentInfoesOutput";
    public static final String PARTY_INFOES_OUTPUT = "partyInfoesOutput";
    public static final String RISK_ASS_DEC_ID = "riskAssDecId";
    public static final String DATA_ACCESS_EXCEPTION = "Exception occoured while saving data for declaration object in risk assessment :";
    public static final String RISK_ASSESS_EXCEPTION = "Exception occoured while assess the risk assessment:";
    public static final String X_USERID = "X-USERID";
    public static final String URL_GO_HIT = "URL GOint to hit: ";
    public static final String WRONG_SEARCHING_RISK_INFO_REQUEST = "Something went wrong when searching Risk Information Request";
    public static final String WRONG_CREATE_RISK_INFO_REQUEST = "Something went wrong when create or modify Risk Information Request";
    public static final String PROFILE_NUMBER = "Profile Number";
    public static final String DECLARATION = "declaration";
    public static final String REGIME_TYPE = "regimeType";
    public static final String NUMBERS = "1,2,3,4,5";
    public static final String GOODS_INFO = "goodsInfo";
    public static final String ORIGIN_COUNTRY = "countryOfOrigin";
    public static final String PT50_PI = "PT50_PI";
    public static final String RISK_ACTION_150 = "RISK_ACTION_150";
    public static final String ACTIVE_STATUS = "Active";
    public static final String WRONG_CREATE_TRUSTTRADER_REQUEST = "Something went wrong when create or Modify TrustedTrader Request";
    public static final String REST_RESOURCE_APP_DETAILS = "restResourceApp details:";
    public static final String RESPONSE_OBJECT_START = "Response object from start:";
    public static final String BPS_START_RESOURCEEXCEPTION = "ResourceException, while connect the BPS for start: ";
    public static final String BPS_START_JSONMAPPINGEXCEPTION = "JsonMappingException, while connect the BPS for start ";
    public static final String BPS_START_IOEXCEPTION= "IOException, while connect the BPS for start: ";
    public static final String BPS_START_EXCEPTION = "Exception, while connect the BPS for start: ";
    public static final String BPS_STARTED = "BPS Service is started";
    public static final String DEFAULT_COMMENT = "NA";
    public static final String CONTAINER_MODULE_NAME = "Container";
    public static final String DPL_PROFILE_CODE = "202301010000";

    public static final String IMPORT_DPL_PROFILE_NAME = "Import Denied Party List";

    public static final String EXPORT_DPL_PROFILE_NAME = "Export Denied Party List";
    public static final String DPL_PROFILE_CATEGORY = "CustomsDeclaration";

    public static final String EOD_TIME = " 23:59:59";

    public static final String SOD_TIME = " 00:00:01";
}
