package com.example.perf.client;

/**
 * Application wide constants
 * 
 */
public final class ClientConstants {

	public static final String DES = "des";

    public static final String DES_EVALUATE_URI = "/api/v1/decisions/decisionDefinitionkey/%s/evaluate";
    
    public static final String DES_CREATE_DMN = "/api/v1/decisions/create/?autoDeployFlag=%s&dmnName=%s";

    public static final String BPS = "bps";
    
    public static final String DECLARATION = "declaration";
    public static final String UPDATE_STATUS_URL = "/api/v1/riskprofile/";
    public static final String UPDATE_STATUS_API = "/updateProfileStatus";


    public static final String BPS_PROCESS_URI = "/api/v1/processes/key/%s/versionTag/1.0/start?businessKey=%s";
    
    public static final String DECLARATION_PROCESS_URI = "/api/v1/declarations/risk/%s";
    
    public static final String CUSTOM_DECLARATIONS_URI = "/api/v1/declarations/%s";
    
    public static final String DES_UMS_PERSISTANCE_AMMENDE = "umsPersistanceAmend";
    
    public static final String BPS_RISK_PROFILE = "riskProfileCreation";
    
    public static final String GET_DECL = "getDeclaration";
    
    public static final String BASE_URL = "/intelligencemgmt/api/v1/riskprofile/search?profileId=";
    
    public static final String RISK_PROFILE_ROLE = "intelligenceofficer";
    
    public static final String EMAIL_ID_CC = "riyasat.ali@niit-tech.com";
    public static final String EMAIL_ID_TO = "Subject";
    public static final String SUBJECT = "Subject";
    public static final String BODY = "Profile Submitted";
    public static final String RECIEVER = "riyasat.ali@niit-tech.com";
    
    public static final String CLM_USER_PROFILES_BY_USERID = "/api/v1/ums/user/userProfiles?loginIds=%s";
    public static final String CLM = "clm";
    public static final String CLM_USER_VALIDATE_URI = "/api/v1/registrations/validate?companyId=%s&licenseType=%s";
    public static final String RDM_TYPES_FIND_BY_ID_URI = "/api/v1/types/%s";
    public static final String RDM_TYPES_FIND_BY_LABEL_URI = "/api/v1/types/module";
    public static final String RDM = "rdm";
    public static final String RDM_VALUES_FIND_BY_RREFTYPE_URI = "/api/v1/values/refType/%s";
    public static final String RDM_TYPES_VALIDATE_URI = "/api/v1/values/validate";
    public static final String RDM_LABELS_FIND_BY_CODE_URI = "/api/v1/values/refType/labels?refTypes=%s&codes=%s";
    public static final String SAVE_PROFILE_TRUE ="TRUE";
    public static final String SAVE_PROFILE_FALSE ="False";
    public static final int PROFILE_CODE_INITIAL_VALUE = 100000;
    

    public static final String DMS = "dms";

	public static final String DMS_UPLOAD_DATA = "/api/v1/documents/upload/data";
	
	public static final String DMS_UPLOAD_DATA_URI = "/api/v1/documents/upload?encoding=base64";

	public static final String DMS_DOWNLOAD_DATA_URI = "/api/v1/documents/%s/download/data";

	public static final String DMS_DOWNLOAD_URI = "/api/v1/documents/%s/download?encoding=base64";

	public static final String DMS_UPDATE_URI = "/api/v1/documents/%s";

	public static final String DMS_DELETE_URI = "/api/v1/documents?uuids=%s";

	public static final String DMS_FIND_BY_ID_URI = "/api/v1/documents/%s";

	public static final String DMS_FIND_BY_QP_URI = "/api/v1/documents?%s";
	

	public static final String DGM = "dgm";
	
    public static final String MANIFEST = "manifest";
    
    public static final String MANIFEST_URL = "/api/v1/declarations/risk/";

    public static final String MANIFEST_URL_FLAT = "/api/v1/declarations/";

    public static final String MANIFEST_CONTAINER_TRACK_DETAIL = "/v1/transportContractDocuments/containerJourney/%s/%s";

    public static final String DECLARATION_URL_FLAT = "/api/v1/declarations/";
    
    public static final String NSM = "nsm";

    public static final String NSM_EMAIL_SEND_URI = "/api/v1/email/send";

    public static final String NSM_EMAIL_STATUS_URI = "/api/v1/email/%s/status";

    public static final String NSM_WEB_SEND_URI = "/api/v1/web/send";

    public static final String NSM_WEB_STATUS_URI = "/api/v1/web/%s/status";
    
    public static final String NSM_PUSH_SEND_URI = "/api/v1/sns/notification/send";
    
    public static final String AUM = "aum";

    public static final String AUM_USER_SETTINGS= "/api/v1/users/usersettings/%s";
    
    public static final String SANDBOX = "sandboxing";

    public static final String PERFORM_SANDBOX_URI = "/api/v1/riskprofile/performSandBoxing/%s/%s/%s";
    
    public static final String VALUATION = "valuation";
    
    public static final String CUSTOM_DECLARATION_URI = "/api/v1/valuations/customValuationStatus/%s";

    public static final String IRIS_WCOOMD_URI = "/news?language_filtering=true";

    public static final String WCOOMD = "wcoomd";

    public static final String UNODC = "unodc";

    public static final String UNODC_URI = "/unodc/en/search/data.json?criteria=%7B%22filters%22:%5B%7B%22fieldName%22:%22lang%22,%22value%22:%22en%22%7D,%7B%22fieldName%22:%22tag%22,%22value%22:%22Drug+trafficking+%22%7D%5D,%22match%22:%22%22,%22startAt%22:0%7D";
    public static final String CRON = "cron";
    public static final String PROFILE_SCHEDULER_URL = "/api/v1/scheduler/schedule";
    public static final String PROFILE_SCHEDULER_DELETE_URL = "/api/v1/scheduler/delete";
    public static final String FETCHING_DATA = "Fetching Data from for :";
    public static final String DES_RESPONSE = "Des Response :";
    public static final String DES_EVALUATE_RESOURCEEXCEPTION = "ResourceException, while connect the Des for  the evaluate: ";

    public static final String SINGLE_TRACK_URI = "/track-single";

    public static final String CONTAINER_TRACKING = "containertracking";

    public static final String DISTANCE_SEARCH_URI = "/search?type=sea&speed=50&lat_from=%s&lng_from=%s&lat_to=%s&lng_to=%s";

    public static final String DISTANCE = "distance";

    public static final String UNIFIEDSOURCE = "unifiedsource";

    public static final String UNIFIED_MULTI_SEARCH = "/api/v1/dpl/search/multi-field?sort=&size=10000&page=0";

    public static final String PCA = "pca";

    public static final String PCA_SUBMIT = "api/v1/pca/submitForDCCustomAudit/%s";
}

