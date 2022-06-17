package com.cisco.josouthe.util;

import java.util.HashMap;
import java.util.Map;
/*
In case we need to generate this again, here is the perl to do it from the header file where these are defined in the MQ samples:
  1 #!/usr/bin/perl
  2
  3 %groups;
  4 %definitions;
  5
  6 while(<>) {
  7     if( $_ =~ /^$/ ) { next; }
  8     chop $_;
  9     chop;
 10     if( $_ !~ /^\s\/\*\s(.*)\s\*\// && $_ !~ /^\s*\#defin/ ) { next; }
 11     if( $_ =~ /^\s\/\*\s(.*)\s\*\// ) { $context=$1; next; }
 12     if( $_ =~ /^\s*\#define\s+([A-Z|_|0-9]+)\s+\(?(\d+)\)?$/ ) {
 13         $name = $1;
 14         $value = $2;
 15         $group = "none";
 16         if( $name =~ /^([A-Z]+)_/ ) { $group=$1; }
 17         $definitions{$group} = $context;
 18
 19         push(@{ $groups{$group} },"ibm${group}Map.put( $value, \"$name\");\n");
 20         #($poundDefine,$reason,$code) = split("\\s+", $_);
 21         #if( $poundDefine == "#define" ) {
 22         #    print "$context.put($code, \"$reason\");\n";
 23         #}
 24     } else {
 25          print "//no match: '$_'\n"; //the first part of the output shows all the things we didn't get
 26     }
 27 }
 28
 29 for $groupName ( keys %groups ) { //second part is the attributes defined for the class
 30     print "\tprivate static Map<Integer, String> ibm${groupName}Map; \/\/$definitions{$groupName} \n"
 31 }
 32
 33 for $key ( keys %groups ) { //third section creates the definitions and map loading for the initializeConstants() method
 34     print "if( ibm${key}Map == null ) {\n\tibm${key}Map = new HashMap<>();\n";
 35     for $line ( @{$groups{$key}} ) {
 36         print "\t$line";
 37     }
 38     print "}\n";
 39 }
 40
 41 for $groupName ( keys %groups ) { //fourth section generates the access methods to use for mapping
 42     print "\tpublic static String get${groupName}String(Integer num) { initializeConstants(); return ibm${groupName}Map.get(num); }\n";
 43 }

 */
public class MQConstants { //may god have mercy on me
    private static Map<String, Integer> ibmConstantAccessorsMap;
    private static Map<Integer, String> ibmMQADPCTXMap; //Authentication Adoption Context
    private static Map<Integer, String> ibmMQMATCHMap; //Match Types
    private static Map<Integer, String> ibmMQCHTABMap; //Channel Table Types
    private static Map<Integer, String> ibmMQASMap; //Asynchronous State Values
    private static Map<Integer, String> ibmMQACTMap; //Action Options
    private static Map<Integer, String> ibmMQCFOMap; //Remove Queues Options
    private static Map<Integer, String> ibmMQFCMap; //Force Options
    private static Map<Integer, String> ibmMQQSUMMap; //Queue Status Uncommitted Messages
    private static Map<Integer, String> ibmMQCHSMap; //Channel Status
    private static Map<Integer, String> ibmMQQSOMap; //Queue Status Open Options for SET, BROWSE, INPUT
    private static Map<Integer, String> ibmMQCACHMap; //Character Channel Parameter Types
    private static Map<Integer, String> ibmMQCFHMap; //Structure Version Number
    private static Map<Integer, String> ibmMQQMDTMap; //Queue Manager Definition Types
    private static Map<Integer, String> ibmMQMCPMap; //Multicast Properties Options
    private static Map<Integer, String> ibmMQRTMap; //Refresh Types
    private static Map<Integer, String> ibmMQOPMODEMap; //Major Release Function
    private static Map<Integer, String> ibmMQCLSTMap; //CLSTATE Clustered Topic Definition State
    private static Map<Integer, String> ibmMQQSIEMap; //Queue Service-Interval Events
    private static Map<Integer, String> ibmMQSCOMap; //Queue Definition Scope
    private static Map<Integer, String> ibmMQUOWSTMap; //UOW States
    private static Map<Integer, String> ibmMQCLROUTEMap; //CLROUTE Topic State
    private static Map<Integer, String> ibmMQLDAPMap; //LDAP Nested Group Policy
    private static Map<Integer, String> ibmMQQMSTAMap; //Queue Manager Status
    private static Map<Integer, String> ibmMQUOWTMap; //UOW Types
    private static Map<Integer, String> ibmMQCHLDMap; //Channel Dispositions
    private static Map<Integer, String> ibmMQDOPTMap; //Display Subscription Types
    private static Map<Integer, String> ibmMQRPMap; //Replace Options
    private static Map<Integer, String> ibmMQUIDSUPPMap; //User ID Support
    private static Map<Integer, String> ibmMQQSGSMap; //QSG Status
    private static Map<Integer, String> ibmMQCFTMap; //Types of Structure
    private static Map<Integer, String> ibmMQPOMap; //Purge Options
    private static Map<Integer, String> ibmnoneMap; //64-bit Integer Monitoring Parameter Types
    private static Map<Integer, String> ibmMQIAMOMap; //Integer Monitoring Parameter Types
    private static Map<Integer, String> ibmMQCHSSTATEMap; //Channel Substates
    private static Map<Integer, String> ibmMQCMDIMap; //Command Information Values
    private static Map<Integer, String> ibmMQQOMap; //Quiesce Options
    private static Map<Integer, String> ibmMQEVRMap; //Event Recording
    private static Map<Integer, String> ibmMQQMFACMap; //Queue Manager Facility
    private static Map<Integer, String> ibmMQSECITEMMap; //Security Items
    private static Map<Integer, String> ibmMQCHKMap; //Authentication Validation Types
    private static Map<Integer, String> ibmMQGACFMap; //Group Parameter Types
    private static Map<Integer, String> ibmMQCHSRMap; //Channel Stop Options
    private static Map<Integer, String> ibmMQSYSOBJMap; //System Objects
    private static Map<Integer, String> ibmMQCHSHMap; //Channel Shared Restart Options
    private static Map<Integer, String> ibmMQSELTYPEMap; //Selector types
    private static Map<Integer, String> ibmMQETMap; //Escape Types
    private static Map<Integer, String> ibmMQBPLOCATIONMap; //Values for MQIACF_BUFFER_POOL_LOCATION.
    private static Map<Integer, String> ibmMQSTDBYMap; //Multi-instance Queue Managers
    private static Map<Integer, String> ibmMQCMDMap; //Command Codes
    private static Map<Integer, String> ibmMQCFSTATUSMap; //CF Status
    private static Map<Integer, String> ibmMQIACFMap; //Integer Parameter Types
    private static Map<Integer, String> ibmMQSYSPMap; //System Parameter Values
    private static Map<Integer, String> ibmMQEXTATTRSMap; //Export Attrs
    private static Map<Integer, String> ibmMQCLRSMap; //Clear Topic String Scope
    private static Map<Integer, String> ibmMQCFINMap; //Structure Length
    private static Map<Integer, String> ibmMQGURMap; //Grouped Units of Recovery
    private static Map<Integer, String> ibmMQEXTMap; //Export Type
    private static Map<Integer, String> ibmMQMULCMap; //Measured usage by API
    private static Map<Integer, String> ibmMQUAMap; //User Attribute Selectors
    private static Map<Integer, String> ibmMQCFBFMap; //Structure Length (Fixed Part)
    private static Map<Integer, String> ibmMQUCIMap; //Use ClientID
    private static Map<Integer, String> ibmMQRARMap; //Remove Authority Record Options
    private static Map<Integer, String> ibmMQCFCMap; //Control Options
    private static Map<Integer, String> ibmMQROUTEMap; //Trace-route Max Activities (MQIACF_MAX_ACTIVITIES)
    private static Map<Integer, String> ibmMQCFGRMap; //Structure Length
    private static Map<Integer, String> ibmMQCHLAMap; //CHLAUTH QMGR State
    private static Map<Integer, String> ibmMQSECCOMMMap; //LDAP SSL/TLS Connection State
    private static Map<Integer, String> ibmMQMLPMap; //Message Level Protection
    private static Map<Integer, String> ibmMQINBDMap; //Inbound Dispositions
    private static Map<Integer, String> ibmMQBTMap; //Bridge Types
    private static Map<Integer, String> ibmMQCFSFMap; //Structure Length (Fixed Part)
    private static Map<Integer, String> ibmMQSECTYPEMap; //Security Types
    private static Map<Integer, String> ibmMQCFSLMap; //Structure Length (Fixed Part)
    private static Map<Integer, String> ibmMQMODEMap; //Mode Options
    private static Map<Integer, String> ibmMQCFACCESSMap; //Access Options
    private static Map<Integer, String> ibmMQCHRRMap; //Channel reset requested
    private static Map<Integer, String> ibmMQPSSTMap; //Pub/Sub Status Type
    private static Map<Integer, String> ibmMQSUBTYPEMap; //Subscription Types
    private static Map<Integer, String> ibmMQSYNCPOINTMap; //Syncpoint values for Pub/Sub migration
    private static Map<Integer, String> ibmMQCACFMap; //Character Parameter Types
    private static Map<Integer, String> ibmMQSMap; //Expandst Options
    private static Map<Integer, String> ibmMQOPERMap; //Activity Operations
    private static Map<Integer, String> ibmMQCFBSMap; //Structure Length (Fixed Part)
    private static Map<Integer, String> ibmMQCHIDSMap; //Indoubt Status
    private static Map<Integer, String> ibmMQQSOTMap; //Queue Status Open Types
    private static Map<Integer, String> ibmMQQMTMap; //Queue Manager Types
    private static Map<Integer, String> ibmMQCFIFMap; //Structure Length
    private static Map<Integer, String> ibmMQNSHMap; //Multicast New Subscriber History Options
    private static Map<Integer, String> ibmMQSUSMap; //Suspend Status
    private static Map<Integer, String> ibmMQPAGECLASMap; //Values for MQIACF_PAGECLAS.
    private static Map<Integer, String> ibmMQEPHMap; //Structure Length
    private static Map<Integer, String> ibmMQMap; //String Lengths
    private static Map<Integer, String> ibmMQCFILMap; //Structure Length (Fixed Part)
    private static Map<Integer, String> ibmMQCLXQMap; //Transmission queue types
    private static Map<Integer, String> ibmMQLDAPCMap; //QMgr LDAP Connection Status
    private static Map<Integer, String> ibmMQRCCFMap; //Reason Codes
    private static Map<Integer, String> ibmMQCLRTMap; //Clear Topic String Type
    private static Map<Integer, String> ibmMQCFTYPEMap; //CF Types
    private static Map<Integer, String> ibmMQRDNSMap; //REVDNS QMGR State
    private static Map<Integer, String> ibmMQCFSTMap; //Structure Length (Fixed Part)
    private static Map<Integer, String> ibmMQEVOMap; //Event Origins
    private static Map<Integer, String> ibmMQIACHMap; //Integer Channel Types
    private static Map<Integer, String> ibmMQCAUTMap; //CHLAUTH Type
    private static Map<Integer, String> ibmMQIDOMap; //Indoubt Options
    private static Map<Integer, String> ibmMQDISCONNECTMap; //Disconnect Types
    private static Map<Integer, String> ibmMQUNDELIVEREDMap; //Undelivered values for Pub/Sub migration
    private static Map<Integer, String> ibmMQTIMEMap; //Time units
    private static Map<Integer, String> ibmMQRQMap; //Reason Qualifiers
    private static Map<Integer, String> ibmMQAUTHMap; //Authority Values
    private static Map<Integer, String> ibmMQBACFMap; //Byte Parameter Types
    private static Map<Integer, String> ibmMQCAMOMap; //Character Monitoring Parameter Types
    private static Map<Integer, String> ibmMQCFOPMap; //Filter Operators
    private static Map<Integer, String> ibmMQHSTATEMap; //Handle States
    private static Map<Integer, String> ibmMQMCASMap; //Message Channel Agent Status
    private static Map<Integer, String> ibmMQPSMap; //Pub/Sub Status
    private static Map<Integer, String> ibmMQSECSWMap; //Security Switch States
    private static Map<Integer, String> ibmMQUSAGEMap; //Data Set Usage Values
    private static Map<String, Integer> ibmCommandMap;
    private static Map<String,Integer> ibmMQIAMap; //Integer Attribute Selectors

    public static Integer getIntFromConstant(String name ) {
        initializeConstants();
        Integer i = null;
        if( name.startsWith("CMQCFC.") ) i = ibmConstantAccessorsMap.get(name);
        if( name.startsWith("CMQC.") ) i = ibmCommandMap.get(name);
        if( name.startsWith("MQIA_") ) i = getMQIAValue(name);
        if( i == null ) i = ibmConstantAccessorsMap.get(String.format("CMQCFC.%s",name));
        return i;
    }

    private static void initializeConstants() {
        init_1();
        init_2();
        init_theLast();
    }

    private static void init_1() {
        if (ibmConstantAccessorsMap == null) {
            ibmConstantAccessorsMap = new HashMap<>();
            ibmConstantAccessorsMap.put("CMQCFC.MQ_ARCHIVE_PFX_LENGTH", 36);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_ARCHIVE_UNIT_LENGTH", 8);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_ASID_LENGTH", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_AUTH_PROFILE_NAME_LENGTH", 48);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_CF_LEID_LENGTH", 12);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_COMMAND_MQSC_LENGTH", 32768);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_DATA_SET_NAME_LENGTH", 44);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_DB2_NAME_LENGTH", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_DSG_NAME_LENGTH", 8);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_ENTITY_NAME_LENGTH", 1024);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_ENV_INFO_LENGTH", 96);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_GROUP_ADDRESS_LENGTH", 264);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_IP_ADDRESS_LENGTH", 48);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_LOG_CORREL_ID_LENGTH", 8);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_LOG_EXTENT_NAME_LENGTH", 24);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_LOG_PATH_LENGTH", 1024);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_LRSN_LENGTH", 12);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_ORIGIN_NAME_LENGTH", 8);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_PSB_NAME_LENGTH", 8);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_PST_ID_LENGTH", 8);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_Q_MGR_CPF_LENGTH", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_RESPONSE_ID_LENGTH", 24);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_RBA_LENGTH", 16);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_SECURITY_PROFILE_LENGTH", 40);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_SERVICE_COMPONENT_LENGTH", 48);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_SUB_NAME_LENGTH", 10240);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_SYSP_SERVICE_LENGTH", 32);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_SYSTEM_NAME_LENGTH", 8);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_TASK_NUMBER_LENGTH", 8);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_TPIPE_PFX_LENGTH", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_UOW_ID_LENGTH", 256);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_USER_DATA_LENGTH", 10240);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_VOLSER_LENGTH", 6);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_REMOTE_PRODUCT_LENGTH", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQ_REMOTE_VERSION_LENGTH", 8);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFOP_LESS", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFOP_EQUAL", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFOP_GREATER", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFOP_NOT_LESS", 6);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFOP_NOT_EQUAL", 5);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFOP_NOT_GREATER", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFOP_LIKE", 18);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFOP_NOT_LIKE", 21);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFOP_CONTAINS", 10);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFOP_EXCLUDES", 13);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFOP_CONTAINS_GEN", 26);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFOP_EXCLUDES_GEN", 29);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFT_NONE", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFT_COMMAND", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFT_RESPONSE", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFT_INTEGER", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFT_STRING", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFT_INTEGER_LIST", 5);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFT_STRING_LIST", 6);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFT_EVENT", 7);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFT_USER", 8);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFT_BYTE_STRING", 9);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFT_TRACE_ROUTE", 10);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFT_REPORT", 12);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFT_INTEGER_FILTER", 13);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFT_STRING_FILTER", 14);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFT_BYTE_STRING_FILTER", 15);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFT_COMMAND_XR", 16);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFT_XR_MSG", 17);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFT_XR_ITEM", 18);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFT_XR_SUMMARY", 19);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFT_GROUP", 20);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFT_STATISTICS", 21);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFT_ACCOUNTING", 22);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFT_INTEGER64", 23);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFT_INTEGER64_LIST", 25);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFT_APP_ACTIVITY", 26);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFT_STATUS", 27);
            ibmConstantAccessorsMap.put("CMQCFC.MQOPMODE_COMPAT", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQOPMODE_NEW_FUNCTION", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_FIRST", 7001);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_EVENT_ACCOUNTING_TOKEN", 7001);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_EVENT_SECURITY_ID", 7002);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_RESPONSE_SET", 7003);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_RESPONSE_ID", 7004);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_EXTERNAL_UOW_ID", 7005);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_CONNECTION_ID", 7006);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_GENERIC_CONNECTION_ID", 7007);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_ORIGIN_UOW_ID", 7008);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_Q_MGR_UOW_ID", 7009);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_ACCOUNTING_TOKEN", 7010);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_CORREL_ID", 7011);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_GROUP_ID", 7012);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_MSG_ID", 7013);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_CF_LEID", 7014);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_DESTINATION_CORREL_ID", 7015);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_SUB_ID", 7016);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_ALTERNATE_SECURITYID", 7019);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_MESSAGE_DATA", 7020);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_MQBO_STRUCT", 7021);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_MQCB_FUNCTION", 7022);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_MQCBC_STRUCT", 7023);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_MQCBD_STRUCT", 7024);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_MQCD_STRUCT", 7025);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_MQCNO_STRUCT", 7026);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_MQGMO_STRUCT", 7027);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_MQMD_STRUCT", 7028);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_MQPMO_STRUCT", 7029);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_MQSD_STRUCT", 7030);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_MQSTS_STRUCT", 7031);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_SUB_CORREL_ID", 7032);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_XA_XID", 7033);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_XQH_CORREL_ID", 7034);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_XQH_MSG_ID", 7035);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_REQUEST_ID", 7036);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_PROPERTIES_DATA", 7037);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_CONN_TAG", 7038);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_MQBNO_STRUCT", 7039);
            ibmConstantAccessorsMap.put("CMQCFC.MQBACF_LAST_USED", 7039);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_FIRST", 701);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_AVG_BATCH_SIZE", 702);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_AVG_Q_TIME", 703);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO64_AVG_Q_TIME", 703);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_BACKOUTS", 704);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_BROWSES", 705);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_BROWSE_MAX_BYTES", 706);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_BROWSE_MIN_BYTES", 707);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_BROWSES_FAILED", 708);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_CLOSES", 709);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_COMMITS", 710);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_COMMITS_FAILED", 711);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_CONNS", 712);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_CONNS_MAX", 713);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_DISCS", 714);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_DISCS_IMPLICIT", 715);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_DISC_TYPE", 716);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_EXIT_TIME_AVG", 717);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_EXIT_TIME_MAX", 718);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_EXIT_TIME_MIN", 719);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_FULL_BATCHES", 720);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_GENERATED_MSGS", 721);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_GETS", 722);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_GET_MAX_BYTES", 723);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_GET_MIN_BYTES", 724);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_GETS_FAILED", 725);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_INCOMPLETE_BATCHES", 726);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_INQS", 727);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_MSGS", 728);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_NET_TIME_AVG", 729);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_NET_TIME_MAX", 730);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_NET_TIME_MIN", 731);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_OBJECT_COUNT", 732);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_OPENS", 733);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_PUT1S", 734);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_PUTS", 735);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_PUT_MAX_BYTES", 736);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_PUT_MIN_BYTES", 737);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_PUT_RETRIES", 738);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_Q_MAX_DEPTH", 739);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_Q_MIN_DEPTH", 740);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_Q_TIME_AVG", 741);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO64_Q_TIME_AVG", 741);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_Q_TIME_MAX", 742);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO64_Q_TIME_MAX", 742);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_Q_TIME_MIN", 743);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO64_Q_TIME_MIN", 743);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_SETS", 744);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO64_BROWSE_BYTES", 745);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO64_BYTES", 746);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO64_GET_BYTES", 747);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO64_PUT_BYTES", 748);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_CONNS_FAILED", 749);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_OPENS_FAILED", 751);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_INQS_FAILED", 752);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_SETS_FAILED", 753);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_PUTS_FAILED", 754);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_PUT1S_FAILED", 755);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_CLOSES_FAILED", 757);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_MSGS_EXPIRED", 758);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_MSGS_NOT_QUEUED", 759);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_MSGS_PURGED", 760);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_SUBS_DUR", 764);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_SUBS_NDUR", 765);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_SUBS_FAILED", 766);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_SUBRQS", 767);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_SUBRQS_FAILED", 768);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_CBS", 769);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_CBS_FAILED", 770);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_CTLS", 771);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_CTLS_FAILED", 772);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_STATS", 773);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_STATS_FAILED", 774);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_SUB_DUR_HIGHWATER", 775);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_SUB_DUR_LOWWATER", 776);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_SUB_NDUR_HIGHWATER", 777);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_SUB_NDUR_LOWWATER", 778);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_TOPIC_PUTS", 779);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_TOPIC_PUTS_FAILED", 780);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_TOPIC_PUT1S", 781);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_TOPIC_PUT1S_FAILED", 782);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO64_TOPIC_PUT_BYTES", 783);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_PUBLISH_MSG_COUNT", 784);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO64_PUBLISH_MSG_BYTES", 785);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_UNSUBS_DUR", 786);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_UNSUBS_NDUR", 787);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_UNSUBS_FAILED", 788);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_INTERVAL", 789);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_MSGS_SENT", 790);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_BYTES_SENT", 791);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_REPAIR_BYTES", 792);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_FEEDBACK_MODE", 793);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_RELIABILITY_TYPE", 794);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_LATE_JOIN_MARK", 795);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_NACKS_RCVD", 796);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_REPAIR_PKTS", 797);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_HISTORY_PKTS", 798);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_PENDING_PKTS", 799);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_PKT_RATE", 800);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_MCAST_XMIT_RATE", 801);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_MCAST_BATCH_TIME", 802);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_MCAST_HEARTBEAT", 803);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_DEST_DATA_PORT", 804);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_DEST_REPAIR_PORT", 805);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_ACKS_RCVD", 806);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_ACTIVE_ACKERS", 807);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_PKTS_SENT", 808);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_TOTAL_REPAIR_PKTS", 809);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_TOTAL_PKTS_SENT", 810);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_TOTAL_MSGS_SENT", 811);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_TOTAL_BYTES_SENT", 812);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_NUM_STREAMS", 813);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_ACK_FEEDBACK", 814);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_NACK_FEEDBACK", 815);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_PKTS_LOST", 816);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_MSGS_RCVD", 817);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_MSG_BYTES_RCVD", 818);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_MSGS_DELIVERED", 819);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_PKTS_PROCESSED", 820);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_PKTS_DELIVERED", 821);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_PKTS_DROPPED", 822);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_PKTS_DUPLICATED", 823);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_NACKS_CREATED", 824);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_NACK_PKTS_SENT", 825);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_REPAIR_PKTS_RQSTD", 826);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_REPAIR_PKTS_RCVD", 827);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_PKTS_REPAIRED", 828);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_TOTAL_MSGS_RCVD", 829);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_TOTAL_MSG_BYTES_RCVD", 830);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_TOTAL_REPAIR_PKTS_RCVD", 831);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_TOTAL_REPAIR_PKTS_RQSTD", 832);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_TOTAL_MSGS_PROCESSED", 833);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_TOTAL_MSGS_SELECTED", 834);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_TOTAL_MSGS_EXPIRED", 835);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_TOTAL_MSGS_DELIVERED", 836);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_TOTAL_MSGS_RETURNED", 837);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO64_HIGHRES_TIME", 838);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_MONITOR_CLASS", 839);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_MONITOR_TYPE", 840);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_MONITOR_ELEMENT", 841);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_MONITOR_DATATYPE", 842);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_MONITOR_FLAGS", 843);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO64_QMGR_OP_DURATION", 844);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO64_MONITOR_INTERVAL", 845);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_LAST_USED", 845);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_MONITOR_FLAGS_NONE", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_MONITOR_FLAGS_OBJNAME", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_MONITOR_UNIT", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_MONITOR_DELTA", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_MONITOR_HUNDREDTHS", 100);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_MONITOR_KB", 1024);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_MONITOR_PERCENT", 10000);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_MONITOR_MICROSEC", 1000000);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_MONITOR_MB", 1048576);
            ibmConstantAccessorsMap.put("CMQCFC.MQIAMO_MONITOR_GB", 100000000);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_FIRST", 1001);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_Q_MGR_ATTRS", 1001);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_Q_ATTRS", 1002);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_PROCESS_ATTRS", 1003);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_NAMELIST_ATTRS", 1004);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_FORCE", 1005);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_REPLACE", 1006);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_PURGE", 1007);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_QUIESCE", 1008);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_MODE", 1008);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_ALL", 1009);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_EVENT_APPL_TYPE", 1010);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_EVENT_ORIGIN", 1011);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_PARAMETER_ID", 1012);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_ERROR_ID", 1013);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_ERROR_IDENTIFIER", 1013);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SELECTOR", 1014);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CHANNEL_ATTRS", 1015);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_OBJECT_TYPE", 1016);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_ESCAPE_TYPE", 1017);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_ERROR_OFFSET", 1018);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_AUTH_INFO_ATTRS", 1019);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_REASON_QUALIFIER", 1020);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_COMMAND", 1021);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_OPEN_OPTIONS", 1022);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_OPEN_TYPE", 1023);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_PROCESS_ID", 1024);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_THREAD_ID", 1025);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_Q_STATUS_ATTRS", 1026);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_UNCOMMITTED_MSGS", 1027);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_HANDLE_STATE", 1028);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_AUX_ERROR_DATA_INT_1", 1070);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_AUX_ERROR_DATA_INT_2", 1071);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CONV_REASON_CODE", 1072);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_BRIDGE_TYPE", 1073);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_INQUIRY", 1074);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_WAIT_INTERVAL", 1075);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_OPTIONS", 1076);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_BROKER_OPTIONS", 1077);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_REFRESH_TYPE", 1078);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SEQUENCE_NUMBER", 1079);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_INTEGER_DATA", 1080);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_REGISTRATION_OPTIONS", 1081);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_PUBLICATION_OPTIONS", 1082);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CLUSTER_INFO", 1083);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_Q_MGR_DEFINITION_TYPE", 1084);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_Q_MGR_TYPE", 1085);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_ACTION", 1086);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SUSPEND", 1087);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_BROKER_COUNT", 1088);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_APPL_COUNT", 1089);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_ANONYMOUS_COUNT", 1090);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_REG_REG_OPTIONS", 1091);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_DELETE_OPTIONS", 1092);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CLUSTER_Q_MGR_ATTRS", 1093);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_REFRESH_INTERVAL", 1094);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_REFRESH_REPOSITORY", 1095);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_REMOVE_QUEUES", 1096);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_OPEN_INPUT_TYPE", 1098);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_OPEN_OUTPUT", 1099);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_OPEN_SET", 1100);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_OPEN_INQUIRE", 1101);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_OPEN_BROWSE", 1102);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_Q_STATUS_TYPE", 1103);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_Q_HANDLE", 1104);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_Q_STATUS", 1105);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SECURITY_TYPE", 1106);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CONNECTION_ATTRS", 1107);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CONNECT_OPTIONS", 1108);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CONN_INFO_TYPE", 1110);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CONN_INFO_CONN", 1111);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CONN_INFO_HANDLE", 1112);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CONN_INFO_ALL", 1113);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_AUTH_PROFILE_ATTRS", 1114);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_AUTHORIZATION_LIST", 1115);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_AUTH_ADD_AUTHS", 1116);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_AUTH_REMOVE_AUTHS", 1117);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_ENTITY_TYPE", 1118);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_COMMAND_INFO", 1120);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CMDSCOPE_Q_MGR_COUNT", 1121);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_Q_MGR_SYSTEM", 1122);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_Q_MGR_EVENT", 1123);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_Q_MGR_DQM", 1124);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_Q_MGR_CLUSTER", 1125);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_QSG_DISPS", 1126);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_UOW_STATE", 1128);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SECURITY_ITEM", 1129);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CF_STRUC_STATUS", 1130);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_UOW_TYPE", 1132);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CF_STRUC_ATTRS", 1133);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_EXCLUDE_INTERVAL", 1134);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CF_STATUS_TYPE", 1135);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CF_STATUS_SUMMARY", 1136);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CF_STATUS_CONNECT", 1137);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CF_STATUS_BACKUP", 1138);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CF_STRUC_TYPE", 1139);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CF_STRUC_SIZE_MAX", 1140);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CF_STRUC_SIZE_USED", 1141);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CF_STRUC_ENTRIES_MAX", 1142);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CF_STRUC_ENTRIES_USED", 1143);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CF_STRUC_BACKUP_SIZE", 1144);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_MOVE_TYPE", 1145);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_MOVE_TYPE_MOVE", 1146);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_MOVE_TYPE_ADD", 1147);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_Q_MGR_NUMBER", 1148);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_Q_MGR_STATUS", 1149);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_DB2_CONN_STATUS", 1150);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SECURITY_ATTRS", 1151);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SECURITY_TIMEOUT", 1152);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SECURITY_INTERVAL", 1153);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SECURITY_SWITCH", 1154);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SECURITY_SETTING", 1155);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_STORAGE_CLASS_ATTRS", 1156);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_USAGE_TYPE", 1157);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_BUFFER_POOL_ID", 1158);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_USAGE_TOTAL_PAGES", 1159);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_USAGE_UNUSED_PAGES", 1160);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_USAGE_PERSIST_PAGES", 1161);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_USAGE_NONPERSIST_PAGES", 1162);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_USAGE_RESTART_EXTENTS", 1163);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_USAGE_EXPAND_COUNT", 1164);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_PAGESET_STATUS", 1165);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_USAGE_TOTAL_BUFFERS", 1166);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_USAGE_DATA_SET_TYPE", 1167);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_USAGE_PAGESET", 1168);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_USAGE_DATA_SET", 1169);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_USAGE_BUFFER_POOL", 1170);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_MOVE_COUNT", 1171);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_EXPIRY_Q_COUNT", 1172);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CONFIGURATION_OBJECTS", 1173);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CONFIGURATION_EVENTS", 1174);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_TYPE", 1175);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_DEALLOC_INTERVAL", 1176);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_MAX_ARCHIVE", 1177);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_MAX_READ_TAPES", 1178);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_IN_BUFFER_SIZE", 1179);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_OUT_BUFFER_SIZE", 1180);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_OUT_BUFFER_COUNT", 1181);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_ARCHIVE", 1182);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_DUAL_ACTIVE", 1183);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_DUAL_ARCHIVE", 1184);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_DUAL_BSDS", 1185);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_MAX_CONNS", 1186);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_MAX_CONNS_FORE", 1187);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_MAX_CONNS_BACK", 1188);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_EXIT_INTERVAL", 1189);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_EXIT_TASKS", 1190);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_CHKPOINT_COUNT", 1191);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_OTMA_INTERVAL", 1192);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_Q_INDEX_DEFER", 1193);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_DB2_TASKS", 1194);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_RESLEVEL_AUDIT", 1195);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_ROUTING_CODE", 1196);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_SMF_ACCOUNTING", 1197);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_SMF_STATS", 1198);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_SMF_INTERVAL", 1199);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_SMF_STAT_TIME_MINS", 1199);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_TRACE_CLASS", 1200);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_TRACE_SIZE", 1201);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_WLM_INTERVAL", 1202);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_ALLOC_UNIT", 1203);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_ARCHIVE_RETAIN", 1204);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_ARCHIVE_WTOR", 1205);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_BLOCK_SIZE", 1206);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_CATALOG", 1207);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_COMPACT", 1208);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_ALLOC_PRIMARY", 1209);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_ALLOC_SECONDARY", 1210);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_PROTECT", 1211);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_QUIESCE_INTERVAL", 1212);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_TIMESTAMP", 1213);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_UNIT_ADDRESS", 1214);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_UNIT_STATUS", 1215);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_LOG_COPY", 1216);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_LOG_USED", 1217);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_LOG_SUSPEND", 1218);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_OFFLOAD_STATUS", 1219);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_TOTAL_LOGS", 1220);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_FULL_LOGS", 1221);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_LISTENER_ATTRS", 1222);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_LISTENER_STATUS_ATTRS", 1223);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SERVICE_ATTRS", 1224);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SERVICE_STATUS_ATTRS", 1225);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_Q_TIME_INDICATOR", 1226);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_OLDEST_MSG_AGE", 1227);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_AUTH_OPTIONS", 1228);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_Q_MGR_STATUS_ATTRS", 1229);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CONNECTION_COUNT", 1230);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_Q_MGR_FACILITY", 1231);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CHINIT_STATUS", 1232);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CMD_SERVER_STATUS", 1233);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_ROUTE_DETAIL", 1234);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_RECORDED_ACTIVITIES", 1235);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_MAX_ACTIVITIES", 1236);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_DISCONTINUITY_COUNT", 1237);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_ROUTE_ACCUMULATION", 1238);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_ROUTE_DELIVERY", 1239);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_OPERATION_TYPE", 1240);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_BACKOUT_COUNT", 1241);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_COMP_CODE", 1242);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_ENCODING", 1243);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_EXPIRY", 1244);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_FEEDBACK", 1245);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_MSG_FLAGS", 1247);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_MSG_LENGTH", 1248);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_MSG_TYPE", 1249);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_OFFSET", 1250);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_ORIGINAL_LENGTH", 1251);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_PERSISTENCE", 1252);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_PRIORITY", 1253);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_REASON_CODE", 1254);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_REPORT", 1255);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_VERSION", 1256);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_UNRECORDED_ACTIVITIES", 1257);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_MONITORING", 1258);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_ROUTE_FORWARDING", 1259);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SERVICE_STATUS", 1260);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_Q_TYPES", 1261);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_USER_ID_SUPPORT", 1262);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_INTERFACE_VERSION", 1263);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_AUTH_SERVICE_ATTRS", 1264);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_USAGE_EXPAND_TYPE", 1265);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_CLUSTER_CACHE", 1266);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_DB2_BLOB_TASKS", 1267);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_WLM_INT_UNITS", 1268);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_TOPIC_ATTRS", 1269);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_PUBSUB_PROPERTIES", 1271);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_DESTINATION_CLASS", 1273);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_DURABLE_SUBSCRIPTION", 1274);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SUBSCRIPTION_SCOPE", 1275);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_VARIABLE_USER_ID", 1277);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_REQUEST_ONLY", 1280);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_PUB_PRIORITY", 1283);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SUB_ATTRS", 1287);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_WILDCARD_SCHEMA", 1288);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SUB_TYPE", 1289);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_MESSAGE_COUNT", 1290);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_Q_MGR_PUBSUB", 1291);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_Q_MGR_VERSION", 1292);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SUB_STATUS_ATTRS", 1294);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_TOPIC_STATUS", 1295);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_TOPIC_SUB", 1296);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_TOPIC_PUB", 1297);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_RETAINED_PUBLICATION", 1300);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_TOPIC_STATUS_ATTRS", 1301);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_TOPIC_STATUS_TYPE", 1302);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SUB_OPTIONS", 1303);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_PUBLISH_COUNT", 1304);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CLEAR_TYPE", 1305);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CLEAR_SCOPE", 1306);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SUB_LEVEL", 1307);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_ASYNC_STATE", 1308);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SUB_SUMMARY", 1309);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_OBSOLETE_MSGS", 1310);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_PUBSUB_STATUS", 1311);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_PS_STATUS_TYPE", 1314);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_PUBSUB_STATUS_ATTRS", 1318);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SELECTOR_TYPE", 1321);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_LOG_COMPRESSION", 1322);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_GROUPUR_CHECK_ID", 1323);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_MULC_CAPTURE", 1324);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_PERMIT_STANDBY", 1325);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_OPERATION_MODE", 1326);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_COMM_INFO_ATTRS", 1327);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CF_SMDS_BLOCK_SIZE", 1328);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CF_SMDS_EXPAND", 1329);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_USAGE_FREE_BUFF", 1330);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_USAGE_FREE_BUFF_PERC", 1331);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CF_STRUC_ACCESS", 1332);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CF_STATUS_SMDS", 1333);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SMDS_ATTRS", 1334);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_USAGE_SMDS", 1335);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_USAGE_BLOCK_SIZE", 1336);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_USAGE_DATA_BLOCKS", 1337);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_USAGE_EMPTY_BUFFERS", 1338);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_USAGE_INUSE_BUFFERS", 1339);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_USAGE_LOWEST_FREE", 1340);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_USAGE_OFFLOAD_MSGS", 1341);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_USAGE_READS_SAVED", 1342);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_USAGE_SAVED_BUFFERS", 1343);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_USAGE_TOTAL_BLOCKS", 1344);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_USAGE_USED_BLOCKS", 1345);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_USAGE_USED_RATE", 1346);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_USAGE_WAIT_RATE", 1347);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SMDS_OPENMODE", 1348);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SMDS_STATUS", 1349);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SMDS_AVAIL", 1350);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_MCAST_REL_INDICATOR", 1351);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CHLAUTH_TYPE", 1352);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_MQXR_DIAGNOSTICS_TYPE", 1354);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CHLAUTH_ATTRS", 1355);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_OPERATION_ID", 1356);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_API_CALLER_TYPE", 1357);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_API_ENVIRONMENT", 1358);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_TRACE_DETAIL", 1359);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_HOBJ", 1360);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CALL_TYPE", 1361);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_MQCB_OPERATION", 1362);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_MQCB_TYPE", 1363);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_MQCB_OPTIONS", 1364);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CLOSE_OPTIONS", 1365);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CTL_OPERATION", 1366);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_GET_OPTIONS", 1367);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_RECS_PRESENT", 1368);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_KNOWN_DEST_COUNT", 1369);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_UNKNOWN_DEST_COUNT", 1370);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_INVALID_DEST_COUNT", 1371);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_RESOLVED_TYPE", 1372);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_PUT_OPTIONS", 1373);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_BUFFER_LENGTH", 1374);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_TRACE_DATA_LENGTH", 1375);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SMDS_EXPANDST", 1376);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_STRUC_LENGTH", 1377);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_ITEM_COUNT", 1378);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_EXPIRY_TIME", 1379);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CONNECT_TIME", 1380);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_DISCONNECT_TIME", 1381);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_HSUB", 1382);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SUBRQ_OPTIONS", 1383);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_XA_RMID", 1384);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_XA_FLAGS", 1385);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_XA_RETCODE", 1386);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_XA_HANDLE", 1387);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_XA_RETVAL", 1388);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_STATUS_TYPE", 1389);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_XA_COUNT", 1390);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SELECTOR_COUNT", 1391);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SELECTORS", 1392);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_INTATTR_COUNT", 1393);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_INT_ATTRS", 1394);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SUBRQ_ACTION", 1395);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_NUM_PUBS", 1396);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_POINTER_SIZE", 1397);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_REMOVE_AUTHREC", 1398);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_XR_ATTRS", 1399);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_APPL_FUNCTION_TYPE", 1400);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_AMQP_ATTRS", 1401);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_EXPORT_TYPE", 1402);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_EXPORT_ATTRS", 1403);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSTEM_OBJECTS", 1404);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CONNECTION_SWAP", 1405);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_AMQP_DIAGNOSTICS_TYPE", 1406);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_BUFFER_POOL_LOCATION", 1408);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_LDAP_CONNECTION_STATUS", 1409);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_MAX_ACE_POOL", 1410);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_PAGECLAS", 1411);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_AUTH_REC_TYPE", 1412);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_MAX_CONC_OFFLOADS", 1413);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_ZHYPERWRITE", 1414);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_Q_MGR_STATUS_LOG", 1415);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_ARCHIVE_LOG_SIZE", 1416);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_MEDIA_LOG_SIZE", 1417);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_RESTART_LOG_SIZE", 1418);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_REUSABLE_LOG_SIZE", 1419);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_LOG_IN_USE", 1420);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_LOG_UTILIZATION", 1421);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_LOG_REDUCTION", 1422);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_IGNORE_STATE", 1423);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_MOVABLE_APPL_COUNT", 1424);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_APPL_INFO_ATTRS", 1425);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_APPL_MOVABLE", 1426);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_REMOTE_QMGR_ACTIVE", 1427);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_APPL_INFO_TYPE", 1428);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_APPL_INFO_APPL", 1429);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_APPL_INFO_QMGR", 1430);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_APPL_INFO_LOCAL", 1431);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_APPL_IMMOVABLE_COUNT", 1432);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_BALANCED", 1433);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_BALSTATE", 1434);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_APPL_IMMOVABLE_REASON", 1435);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_DS_ENCRYPTED", 1436);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CUR_Q_FILE_SIZE", 1437);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_CUR_MAX_FILE_SIZE", 1438);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_BALANCING_TYPE", 1439);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_BALANCING_OPTIONS", 1440);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_BALANCING_TIMEOUT", 1441);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_SMF_STAT_TIME_SECS", 1442);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_SMF_ACCT_TIME_MINS", 1443);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_SYSP_SMF_ACCT_TIME_SECS", 1444);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACF_LAST_USED", 1444);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFACCESS_ENABLED", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFACCESS_SUSPENDED", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFACCESS_DISABLED", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQS_OPENMODE_NONE", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQS_OPENMODE_READONLY", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQS_OPENMODE_UPDATE", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQS_OPENMODE_RECOVERY", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQS_STATUS_CLOSED", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQS_STATUS_CLOSING", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQS_STATUS_OPENING", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQS_STATUS_OPEN", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQS_STATUS_NOTENABLED", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQS_STATUS_ALLOCFAIL", 5);
            ibmConstantAccessorsMap.put("CMQCFC.MQS_STATUS_OPENFAIL", 6);
            ibmConstantAccessorsMap.put("CMQCFC.MQS_STATUS_STGFAIL", 7);
            ibmConstantAccessorsMap.put("CMQCFC.MQS_STATUS_DATAFAIL", 8);
            ibmConstantAccessorsMap.put("CMQCFC.MQS_AVAIL_NORMAL", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQS_AVAIL_ERROR", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQS_AVAIL_STOPPED", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQBPLOCATION_BELOW", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQBPLOCATION_ABOVE", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQBPLOCATION_SWITCHING_ABOVE", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQBPLOCATION_SWITCHING_BELOW", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQPAGECLAS_4KB", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQPAGECLAS_FIXED4KB", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQS_EXPANDST_NORMAL", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQS_EXPANDST_FAILED", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQS_EXPANDST_MAXIMUM", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQUSAGE_SMDS_AVAILABLE", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQUSAGE_SMDS_NO_DATA", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_FIRST", 1501);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_XMIT_PROTOCOL_TYPE", 1501);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_BATCH_SIZE", 1502);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_DISC_INTERVAL", 1503);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_SHORT_TIMER", 1504);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_SHORT_RETRY", 1505);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_LONG_TIMER", 1506);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_LONG_RETRY", 1507);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_PUT_AUTHORITY", 1508);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_SEQUENCE_NUMBER_WRAP", 1509);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_MAX_MSG_LENGTH", 1510);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_CHANNEL_TYPE", 1511);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_DATA_COUNT", 1512);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_NAME_COUNT", 1513);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_MSG_SEQUENCE_NUMBER", 1514);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_DATA_CONVERSION", 1515);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_IN_DOUBT", 1516);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_MCA_TYPE", 1517);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_SESSION_COUNT", 1518);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_ADAPTER", 1519);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_COMMAND_COUNT", 1520);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_SOCKET", 1521);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_PORT", 1522);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_CHANNEL_INSTANCE_TYPE", 1523);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_CHANNEL_INSTANCE_ATTRS", 1524);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_CHANNEL_ERROR_DATA", 1525);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_CHANNEL_TABLE", 1526);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_CHANNEL_STATUS", 1527);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_INDOUBT_STATUS", 1528);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_LAST_SEQ_NUMBER", 1529);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_LAST_SEQUENCE_NUMBER", 1529);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_CURRENT_MSGS", 1531);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_CURRENT_SEQ_NUMBER", 1532);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_CURRENT_SEQUENCE_NUMBER", 1532);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_SSL_RETURN_CODE", 1533);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_MSGS", 1534);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_BYTES_SENT", 1535);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_BYTES_RCVD", 1536);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_BYTES_RECEIVED", 1536);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_BATCHES", 1537);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_BUFFERS_SENT", 1538);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_BUFFERS_RCVD", 1539);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_BUFFERS_RECEIVED", 1539);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_LONG_RETRIES_LEFT", 1540);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_SHORT_RETRIES_LEFT", 1541);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_MCA_STATUS", 1542);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_STOP_REQUESTED", 1543);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_MR_COUNT", 1544);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_MR_INTERVAL", 1545);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_NPM_SPEED", 1562);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_HB_INTERVAL", 1563);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_BATCH_INTERVAL", 1564);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_NETWORK_PRIORITY", 1565);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_KEEP_ALIVE_INTERVAL", 1566);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_BATCH_HB", 1567);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_SSL_CLIENT_AUTH", 1568);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_ALLOC_RETRY", 1570);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_ALLOC_FAST_TIMER", 1571);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_ALLOC_SLOW_TIMER", 1572);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_DISC_RETRY", 1573);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_PORT_NUMBER", 1574);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_HDR_COMPRESSION", 1575);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_MSG_COMPRESSION", 1576);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_CLWL_CHANNEL_RANK", 1577);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_CLWL_CHANNEL_PRIORITY", 1578);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_CLWL_CHANNEL_WEIGHT", 1579);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_CHANNEL_DISP", 1580);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_INBOUND_DISP", 1581);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_CHANNEL_TYPES", 1582);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_ADAPS_STARTED", 1583);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_ADAPS_MAX", 1584);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_DISPS_STARTED", 1585);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_DISPS_MAX", 1586);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_SSLTASKS_STARTED", 1587);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_SSLTASKS_MAX", 1588);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_CURRENT_CHL", 1589);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_CURRENT_CHL_MAX", 1590);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_CURRENT_CHL_TCP", 1591);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_CURRENT_CHL_LU62", 1592);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_ACTIVE_CHL", 1593);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_ACTIVE_CHL_MAX", 1594);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_ACTIVE_CHL_PAUSED", 1595);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_ACTIVE_CHL_STARTED", 1596);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_ACTIVE_CHL_STOPPED", 1597);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_ACTIVE_CHL_RETRY", 1598);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_LISTENER_STATUS", 1599);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_SHARED_CHL_RESTART", 1600);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_LISTENER_CONTROL", 1601);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_BACKLOG", 1602);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_XMITQ_TIME_INDICATOR", 1604);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_NETWORK_TIME_INDICATOR", 1605);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_EXIT_TIME_INDICATOR", 1606);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_BATCH_SIZE_INDICATOR", 1607);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_XMITQ_MSGS_AVAILABLE", 1608);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_CHANNEL_SUBSTATE", 1609);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_SSL_KEY_RESETS", 1610);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_COMPRESSION_RATE", 1611);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_COMPRESSION_TIME", 1612);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_MAX_XMIT_SIZE", 1613);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_DEF_CHANNEL_DISP", 1614);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_SHARING_CONVERSATIONS", 1615);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_MAX_SHARING_CONVS", 1616);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_CURRENT_SHARING_CONVS", 1617);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_MAX_INSTANCES", 1618);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_MAX_INSTS_PER_CLIENT", 1619);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_CLIENT_CHANNEL_WEIGHT", 1620);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_CONNECTION_AFFINITY", 1621);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_AUTH_INFO_TYPES", 1622);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_RESET_REQUESTED", 1623);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_BATCH_DATA_LIMIT", 1624);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_MSG_HISTORY", 1625);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_MULTICAST_PROPERTIES", 1626);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_NEW_SUBSCRIBER_HISTORY", 1627);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_MC_HB_INTERVAL", 1628);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_USE_CLIENT_ID", 1629);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_MQTT_KEEP_ALIVE", 1630);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_IN_DOUBT_IN", 1631);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_IN_DOUBT_OUT", 1632);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_MSGS_SENT", 1633);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_MSGS_RECEIVED", 1634);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_MSGS_RCVD", 1634);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_PENDING_OUT", 1635);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_AVAILABLE_CIPHERSPECS", 1636);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_MATCH", 1637);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_USER_SOURCE", 1638);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_WARNING", 1639);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_DEF_RECONNECT", 1640);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_CHANNEL_SUMMARY_ATTRS", 1642);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_PROTOCOL", 1643);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_AMQP_KEEP_ALIVE", 1644);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_SECURITY_PROTOCOL", 1645);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_SPL_PROTECTION", 1646);
            ibmConstantAccessorsMap.put("CMQCFC.MQIACH_LAST_USED", 1646);
            ibmConstantAccessorsMap.put("CMQCFC.MQCAMO_FIRST", 2701);
            ibmConstantAccessorsMap.put("CMQCFC.MQCAMO_CLOSE_DATE", 2701);
            ibmConstantAccessorsMap.put("CMQCFC.MQCAMO_CLOSE_TIME", 2702);
            ibmConstantAccessorsMap.put("CMQCFC.MQCAMO_CONN_DATE", 2703);
            ibmConstantAccessorsMap.put("CMQCFC.MQCAMO_CONN_TIME", 2704);
            ibmConstantAccessorsMap.put("CMQCFC.MQCAMO_DISC_DATE", 2705);
            ibmConstantAccessorsMap.put("CMQCFC.MQCAMO_DISC_TIME", 2706);
            ibmConstantAccessorsMap.put("CMQCFC.MQCAMO_END_DATE", 2707);
            ibmConstantAccessorsMap.put("CMQCFC.MQCAMO_END_TIME", 2708);
            ibmConstantAccessorsMap.put("CMQCFC.MQCAMO_OPEN_DATE", 2709);
            ibmConstantAccessorsMap.put("CMQCFC.MQCAMO_OPEN_TIME", 2710);
            ibmConstantAccessorsMap.put("CMQCFC.MQCAMO_START_DATE", 2711);
            ibmConstantAccessorsMap.put("CMQCFC.MQCAMO_START_TIME", 2712);
            ibmConstantAccessorsMap.put("CMQCFC.MQCAMO_MONITOR_CLASS", 2713);
            ibmConstantAccessorsMap.put("CMQCFC.MQCAMO_MONITOR_TYPE", 2714);
            ibmConstantAccessorsMap.put("CMQCFC.MQCAMO_MONITOR_DESC", 2715);
            ibmConstantAccessorsMap.put("CMQCFC.MQCAMO_LAST_USED", 2715);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_FIRST", 3001);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_FROM_Q_NAME", 3001);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_TO_Q_NAME", 3002);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_FROM_PROCESS_NAME", 3003);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_TO_PROCESS_NAME", 3004);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_FROM_NAMELIST_NAME", 3005);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_TO_NAMELIST_NAME", 3006);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_FROM_CHANNEL_NAME", 3007);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_TO_CHANNEL_NAME", 3008);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_FROM_AUTH_INFO_NAME", 3009);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_TO_AUTH_INFO_NAME", 3010);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_Q_NAMES", 3011);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_PROCESS_NAMES", 3012);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_NAMELIST_NAMES", 3013);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_ESCAPE_TEXT", 3014);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_LOCAL_Q_NAMES", 3015);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_MODEL_Q_NAMES", 3016);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_ALIAS_Q_NAMES", 3017);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_REMOTE_Q_NAMES", 3018);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SENDER_CHANNEL_NAMES", 3019);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SERVER_CHANNEL_NAMES", 3020);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_REQUESTER_CHANNEL_NAMES", 3021);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_RECEIVER_CHANNEL_NAMES", 3022);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_OBJECT_Q_MGR_NAME", 3023);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_APPL_NAME", 3024);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_USER_IDENTIFIER", 3025);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_AUX_ERROR_DATA_STR_1", 3026);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_AUX_ERROR_DATA_STR_2", 3027);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_AUX_ERROR_DATA_STR_3", 3028);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_BRIDGE_NAME", 3029);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_STREAM_NAME", 3030);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_TOPIC", 3031);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_PARENT_Q_MGR_NAME", 3032);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_CORREL_ID", 3033);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_PUBLISH_TIMESTAMP", 3034);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_STRING_DATA", 3035);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SUPPORTED_STREAM_NAME", 3036);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_REG_TOPIC", 3037);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_REG_TIME", 3038);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_REG_USER_ID", 3039);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_CHILD_Q_MGR_NAME", 3040);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_REG_STREAM_NAME", 3041);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_REG_Q_MGR_NAME", 3042);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_REG_Q_NAME", 3043);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_REG_CORREL_ID", 3044);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_EVENT_USER_ID", 3045);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_OBJECT_NAME", 3046);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_EVENT_Q_MGR", 3047);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_AUTH_INFO_NAMES", 3048);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_EVENT_APPL_IDENTITY", 3049);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_EVENT_APPL_NAME", 3050);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_EVENT_APPL_ORIGIN", 3051);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SUBSCRIPTION_NAME", 3052);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_REG_SUB_NAME", 3053);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SUBSCRIPTION_IDENTITY", 3054);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_REG_SUB_IDENTITY", 3055);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SUBSCRIPTION_USER_DATA", 3056);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_REG_SUB_USER_DATA", 3057);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_APPL_TAG", 3058);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_DATA_SET_NAME", 3059);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_UOW_START_DATE", 3060);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_UOW_START_TIME", 3061);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_UOW_LOG_START_DATE", 3062);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_UOW_LOG_START_TIME", 3063);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_UOW_LOG_EXTENT_NAME", 3064);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_PRINCIPAL_ENTITY_NAMES", 3065);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_GROUP_ENTITY_NAMES", 3066);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_AUTH_PROFILE_NAME", 3067);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_ENTITY_NAME", 3068);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SERVICE_COMPONENT", 3069);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_RESPONSE_Q_MGR_NAME", 3070);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_CURRENT_LOG_EXTENT_NAME", 3071);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_RESTART_LOG_EXTENT_NAME", 3072);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_MEDIA_LOG_EXTENT_NAME", 3073);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_LOG_PATH", 3074);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_COMMAND_MQSC", 3075);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_Q_MGR_CPF", 3076);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_USAGE_LOG_RBA", 3078);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_USAGE_LOG_LRSN", 3079);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_COMMAND_SCOPE", 3080);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_ASID", 3081);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_PSB_NAME", 3082);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_PST_ID", 3083);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_TASK_NUMBER", 3084);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_TRANSACTION_ID", 3085);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_Q_MGR_UOW_ID", 3086);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_ORIGIN_NAME", 3088);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_ENV_INFO", 3089);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SECURITY_PROFILE", 3090);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_CONFIGURATION_DATE", 3091);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_CONFIGURATION_TIME", 3092);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_FROM_CF_STRUC_NAME", 3093);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_TO_CF_STRUC_NAME", 3094);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_CF_STRUC_NAMES", 3095);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_FAIL_DATE", 3096);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_FAIL_TIME", 3097);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_BACKUP_DATE", 3098);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_BACKUP_TIME", 3099);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SYSTEM_NAME", 3100);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_CF_STRUC_BACKUP_START", 3101);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_CF_STRUC_BACKUP_END", 3102);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_CF_STRUC_LOG_Q_MGRS", 3103);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_FROM_STORAGE_CLASS", 3104);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_TO_STORAGE_CLASS", 3105);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_STORAGE_CLASS_NAMES", 3106);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_DSG_NAME", 3108);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_DB2_NAME", 3109);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SYSP_CMD_USER_ID", 3110);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SYSP_OTMA_GROUP", 3111);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SYSP_OTMA_MEMBER", 3112);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SYSP_OTMA_DRU_EXIT", 3113);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SYSP_OTMA_TPIPE_PFX", 3114);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SYSP_ARCHIVE_PFX1", 3115);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SYSP_ARCHIVE_UNIT1", 3116);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SYSP_LOG_CORREL_ID", 3117);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SYSP_UNIT_VOLSER", 3118);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SYSP_Q_MGR_TIME", 3119);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SYSP_Q_MGR_DATE", 3120);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SYSP_Q_MGR_RBA", 3121);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SYSP_LOG_RBA", 3122);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SYSP_SERVICE", 3123);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_FROM_LISTENER_NAME", 3124);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_TO_LISTENER_NAME", 3125);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_FROM_SERVICE_NAME", 3126);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_TO_SERVICE_NAME", 3127);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_LAST_PUT_DATE", 3128);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_LAST_PUT_TIME", 3129);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_LAST_GET_DATE", 3130);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_LAST_GET_TIME", 3131);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_OPERATION_DATE", 3132);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_OPERATION_TIME", 3133);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_ACTIVITY_DESC", 3134);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_APPL_IDENTITY_DATA", 3135);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_APPL_ORIGIN_DATA", 3136);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_PUT_DATE", 3137);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_PUT_TIME", 3138);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_REPLY_TO_Q", 3139);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_REPLY_TO_Q_MGR", 3140);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_RESOLVED_Q_NAME", 3141);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_STRUC_ID", 3142);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_VALUE_NAME", 3143);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SERVICE_START_DATE", 3144);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SERVICE_START_TIME", 3145);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SYSP_OFFLINE_RBA", 3146);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SYSP_ARCHIVE_PFX2", 3147);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SYSP_ARCHIVE_UNIT2", 3148);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_TO_TOPIC_NAME", 3149);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_FROM_TOPIC_NAME", 3150);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_TOPIC_NAMES", 3151);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SUB_NAME", 3152);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_DESTINATION_Q_MGR", 3153);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_DESTINATION", 3154);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SUB_USER_ID", 3156);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SUB_USER_DATA", 3159);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SUB_SELECTOR", 3160);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_LAST_PUB_DATE", 3161);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_LAST_PUB_TIME", 3162);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_FROM_SUB_NAME", 3163);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_TO_SUB_NAME", 3164);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_LAST_MSG_TIME", 3167);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_LAST_MSG_DATE", 3168);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SUBSCRIPTION_POINT", 3169);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_FILTER", 3170);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_NONE", 3171);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_ADMIN_TOPIC_NAMES", 3172);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_ROUTING_FINGER_PRINT", 3173);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_APPL_DESC", 3174);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_Q_MGR_START_DATE", 3175);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_Q_MGR_START_TIME", 3176);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_FROM_COMM_INFO_NAME", 3177);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_TO_COMM_INFO_NAME", 3178);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_CF_OFFLOAD_SIZE1", 3179);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_CF_OFFLOAD_SIZE2", 3180);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_CF_OFFLOAD_SIZE3", 3181);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_CF_SMDS_GENERIC_NAME", 3182);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_CF_SMDS", 3183);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_RECOVERY_DATE", 3184);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_RECOVERY_TIME", 3185);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_CF_SMDSCONN", 3186);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_CF_STRUC_NAME", 3187);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_ALTERNATE_USERID", 3188);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_CHAR_ATTRS", 3189);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_DYNAMIC_Q_NAME", 3190);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_HOST_NAME", 3191);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_MQCB_NAME", 3192);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_OBJECT_STRING", 3193);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_RESOLVED_LOCAL_Q_MGR", 3194);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_RESOLVED_LOCAL_Q_NAME", 3195);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_RESOLVED_OBJECT_STRING", 3196);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_RESOLVED_Q_MGR", 3197);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_SELECTION_STRING", 3198);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_XA_INFO", 3199);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_APPL_FUNCTION", 3200);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_XQH_REMOTE_Q_NAME", 3201);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_XQH_REMOTE_Q_MGR", 3202);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_XQH_PUT_TIME", 3203);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_XQH_PUT_DATE", 3204);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_EXCL_OPERATOR_MESSAGES", 3205);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_CSP_USER_IDENTIFIER", 3206);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_AMQP_CLIENT_ID", 3207);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_ARCHIVE_LOG_EXTENT_NAME", 3208);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_APPL_IMMOVABLE_DATE", 3209);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_APPL_IMMOVABLE_TIME", 3210);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_NHA_INSTANCE_NAME", 3211);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACF_LAST_USED", 3211);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_FIRST", 3501);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_CHANNEL_NAME", 3501);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_DESC", 3502);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_MODE_NAME", 3503);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_TP_NAME", 3504);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_XMIT_Q_NAME", 3505);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_CONNECTION_NAME", 3506);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_MCA_NAME", 3507);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_SEC_EXIT_NAME", 3508);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_MSG_EXIT_NAME", 3509);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_SEND_EXIT_NAME", 3510);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_RCV_EXIT_NAME", 3511);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_CHANNEL_NAMES", 3512);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_SEC_EXIT_USER_DATA", 3513);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_MSG_EXIT_USER_DATA", 3514);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_SEND_EXIT_USER_DATA", 3515);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_RCV_EXIT_USER_DATA", 3516);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_USER_ID", 3517);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_PASSWORD", 3518);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_LOCAL_ADDRESS", 3520);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_LOCAL_NAME", 3521);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_LAST_MSG_TIME", 3524);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_LAST_MSG_DATE", 3525);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_MCA_USER_ID", 3527);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_CHANNEL_START_TIME", 3528);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_CHANNEL_START_DATE", 3529);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_MCA_JOB_NAME", 3530);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_LAST_LUWID", 3531);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_CURRENT_LUWID", 3532);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_FORMAT_NAME", 3533);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_MR_EXIT_NAME", 3534);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_MR_EXIT_USER_DATA", 3535);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_SSL_CIPHER_SPEC", 3544);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_SSL_PEER_NAME", 3545);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_SSL_HANDSHAKE_STAGE", 3546);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_SSL_SHORT_PEER_NAME", 3547);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_REMOTE_APPL_TAG", 3548);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_SSL_CERT_USER_ID", 3549);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_SSL_CERT_ISSUER_NAME", 3550);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_LU_NAME", 3551);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_IP_ADDRESS", 3552);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_TCP_NAME", 3553);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_LISTENER_NAME", 3554);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_LISTENER_DESC", 3555);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_LISTENER_START_DATE", 3556);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_LISTENER_START_TIME", 3557);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_SSL_KEY_RESET_DATE", 3558);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_SSL_KEY_RESET_TIME", 3559);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_REMOTE_VERSION", 3560);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_REMOTE_PRODUCT", 3561);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_GROUP_ADDRESS", 3562);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_JAAS_CONFIG", 3563);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_CLIENT_ID", 3564);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_SSL_KEY_PASSPHRASE", 3565);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_CONNECTION_NAME_LIST", 3566);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_CLIENT_USER_ID", 3567);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_MCA_USER_ID_LIST", 3568);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_SSL_CIPHER_SUITE", 3569);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_WEBCONTENT_PATH", 3570);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_TOPIC_ROOT", 3571);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_TEMPORARY_MODEL_Q", 3572);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_TEMPORARY_Q_PREFIX", 3573);
            ibmConstantAccessorsMap.put("CMQCFC.MQCACH_LAST_USED", 3573);
            ibmConstantAccessorsMap.put("CMQCFC.MQGACF_FIRST", 8001);
            ibmConstantAccessorsMap.put("CMQCFC.MQGACF_COMMAND_CONTEXT", 8001);
            ibmConstantAccessorsMap.put("CMQCFC.MQGACF_COMMAND_DATA", 8002);
            ibmConstantAccessorsMap.put("CMQCFC.MQGACF_TRACE_ROUTE", 8003);
            ibmConstantAccessorsMap.put("CMQCFC.MQGACF_OPERATION", 8004);
            ibmConstantAccessorsMap.put("CMQCFC.MQGACF_ACTIVITY", 8005);
            ibmConstantAccessorsMap.put("CMQCFC.MQGACF_EMBEDDED_MQMD", 8006);
            ibmConstantAccessorsMap.put("CMQCFC.MQGACF_MESSAGE", 8007);
            ibmConstantAccessorsMap.put("CMQCFC.MQGACF_MQMD", 8008);
            ibmConstantAccessorsMap.put("CMQCFC.MQGACF_VALUE_NAMING", 8009);
            ibmConstantAccessorsMap.put("CMQCFC.MQGACF_Q_ACCOUNTING_DATA", 8010);
            ibmConstantAccessorsMap.put("CMQCFC.MQGACF_Q_STATISTICS_DATA", 8011);
            ibmConstantAccessorsMap.put("CMQCFC.MQGACF_CHL_STATISTICS_DATA", 8012);
            ibmConstantAccessorsMap.put("CMQCFC.MQGACF_ACTIVITY_TRACE", 8013);
            ibmConstantAccessorsMap.put("CMQCFC.MQGACF_APP_DIST_LIST", 8014);
            ibmConstantAccessorsMap.put("CMQCFC.MQGACF_MONITOR_CLASS", 8015);
            ibmConstantAccessorsMap.put("CMQCFC.MQGACF_MONITOR_TYPE", 8016);
            ibmConstantAccessorsMap.put("CMQCFC.MQGACF_MONITOR_ELEMENT", 8017);
            ibmConstantAccessorsMap.put("CMQCFC.MQGACF_APPL_STATUS", 8018);
            ibmConstantAccessorsMap.put("CMQCFC.MQGACF_CHANGED_APPLS", 8019);
            ibmConstantAccessorsMap.put("CMQCFC.MQGACF_ALL_APPLS", 8020);
            ibmConstantAccessorsMap.put("CMQCFC.MQGACF_APPL_BALANCE", 8021);
            ibmConstantAccessorsMap.put("CMQCFC.MQGACF_LAST_USED", 8021);
            ibmConstantAccessorsMap.put("CMQCFC.MQACT_FORCE_REMOVE", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQACT_ADVANCE_LOG", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQACT_COLLECT_STATISTICS", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQACT_PUBSUB", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQACT_ADD", 5);
            ibmConstantAccessorsMap.put("CMQCFC.MQACT_REPLACE", 6);
            ibmConstantAccessorsMap.put("CMQCFC.MQACT_REMOVE", 7);
            ibmConstantAccessorsMap.put("CMQCFC.MQACT_REMOVEALL", 8);
            ibmConstantAccessorsMap.put("CMQCFC.MQACT_FAIL", 9);
            ibmConstantAccessorsMap.put("CMQCFC.MQACT_REDUCE_LOG", 10);
            ibmConstantAccessorsMap.put("CMQCFC.MQACT_ARCHIVE_LOG", 11);
            ibmConstantAccessorsMap.put("CMQCFC.MQIS_NO", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQIS_YES", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQAPPL_IMMOVABLE", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQAPPL_MOVABLE", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQACTIVE_NO", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQACTIVE_YES", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQBALANCED_NO", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQBALANCED_YES", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQBALANCED_NOT_APPLICABLE", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQBALANCED_UNKNOWN", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQBALSTATE_NOT_APPLICABLE", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQBALSTATE_LOW", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQBALSTATE_OK", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQBALSTATE_HIGH", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQBALSTATE_UNKNOWN", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQIMMREASON_NONE", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQIMMREASON_NOT_CLIENT", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQIMMREASON_NOT_RECONNECTABLE", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQIMMREASON_MOVING", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQIMMREASON_APPLNAME_CHANGED", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQIMMREASON_IN_TRANSACTION", 5);
            ibmConstantAccessorsMap.put("CMQCFC.MQIMMREASON_AWAITS_REPLY", 6);
            ibmConstantAccessorsMap.put("CMQCFC.MQIMMREASON_NO_REDIRECT", 7);
            ibmConstantAccessorsMap.put("CMQCFC.MQAS_NONE", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQAS_STARTED", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQAS_START_WAIT", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQAS_STOPPED", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQAS_SUSPENDED", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQAS_SUSPENDED_TEMPORARY", 5);
            ibmConstantAccessorsMap.put("CMQCFC.MQAS_ACTIVE", 6);
            ibmConstantAccessorsMap.put("CMQCFC.MQAS_INACTIVE", 7);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTH_NONE", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTH_ALT_USER_AUTHORITY", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTH_BROWSE", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTH_CHANGE", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTH_CLEAR", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTH_CONNECT", 5);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTH_CREATE", 6);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTH_DELETE", 7);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTH_DISPLAY", 8);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTH_INPUT", 9);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTH_INQUIRE", 10);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTH_OUTPUT", 11);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTH_PASS_ALL_CONTEXT", 12);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTH_PASS_IDENTITY_CONTEXT", 13);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTH_SET", 14);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTH_SET_ALL_CONTEXT", 15);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTH_SET_IDENTITY_CONTEXT", 16);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTH_CONTROL", 17);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTH_CONTROL_EXTENDED", 18);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTH_PUBLISH", 19);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTH_SUBSCRIBE", 20);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTH_RESUME", 21);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTH_SYSTEM", 22);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTH_ALL", -1);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTH_ALL_ADMIN", -2);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTH_ALL_MQI", -3);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTHOPT_ENTITY_EXPLICIT", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTHOPT_ENTITY_SET", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTHOPT_NAME_EXPLICIT", 16);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTHOPT_NAME_ALL_MATCHING", 32);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTHOPT_NAME_AS_WILDCARD", 64);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTHOPT_CUMULATIVE", 256);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTHOPT_EXCLUDE_TEMP", 512);
            ibmConstantAccessorsMap.put("CMQCFC.MQBT_OTMA", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFO_REFRESH_REPOSITORY_YES", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFO_REFRESH_REPOSITORY_NO", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFO_REMOVE_QUEUES_YES", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFO_REMOVE_QUEUES_NO", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQCAUT_ALL", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQCAUT_BLOCKUSER", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQCAUT_BLOCKADDR", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQCAUT_SSLPEERMAP", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQCAUT_ADDRESSMAP", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQCAUT_USERMAP", 5);
            ibmConstantAccessorsMap.put("CMQCFC.MQCAUT_QMGRMAP", 6);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFSTATUS_NOT_FOUND", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFSTATUS_ACTIVE", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFSTATUS_IN_RECOVER", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFSTATUS_IN_BACKUP", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFSTATUS_FAILED", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFSTATUS_NONE", 5);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFSTATUS_UNKNOWN", 6);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFSTATUS_RECOVERED", 7);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFSTATUS_EMPTY", 8);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFSTATUS_NEW", 9);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFSTATUS_ADMIN_INCOMPLETE", 20);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFSTATUS_NEVER_USED", 21);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFSTATUS_NO_BACKUP", 22);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFSTATUS_NOT_FAILED", 23);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFSTATUS_NOT_RECOVERABLE", 24);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFSTATUS_XES_ERROR", 25);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFTYPE_APPL", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFTYPE_ADMIN", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHIDS_NOT_INDOUBT", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHIDS_INDOUBT", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHLD_ALL", -1);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHLD_DEFAULT", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHLD_SHARED", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHLD_PRIVATE", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHLD_FIXSHARED", 5);
            ibmConstantAccessorsMap.put("CMQCFC.MQUCI_YES", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQUCI_NO", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHS_INACTIVE", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHS_BINDING", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHS_STARTING", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHS_RUNNING", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHS_STOPPING", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHS_RETRYING", 5);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHS_STOPPED", 6);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHS_REQUESTING", 7);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHS_PAUSED", 8);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHS_DISCONNECTED", 9);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHS_INITIALIZING", 13);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHS_SWITCHING", 14);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHSSTATE_OTHER", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHSSTATE_END_OF_BATCH", 100);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHSSTATE_SENDING", 200);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHSSTATE_RECEIVING", 300);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHSSTATE_SERIALIZING", 400);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHSSTATE_RESYNCHING", 500);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHSSTATE_HEARTBEATING", 600);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHSSTATE_IN_SCYEXIT", 700);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHSSTATE_IN_RCVEXIT", 800);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHSSTATE_IN_SENDEXIT", 900);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHSSTATE_IN_MSGEXIT", 1000);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHSSTATE_IN_MREXIT", 1100);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHSSTATE_IN_CHADEXIT", 1200);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHSSTATE_NET_CONNECTING", 1250);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHSSTATE_SSL_HANDSHAKING", 1300);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHSSTATE_NAME_SERVER", 1400);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHSSTATE_IN_MQPUT", 1500);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHSSTATE_IN_MQGET", 1600);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHSSTATE_IN_MQI_CALL", 1700);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHSSTATE_COMPRESSING", 1800);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHSH_RESTART_NO", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHSH_RESTART_YES", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHSR_STOP_NOT_REQUESTED", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHSR_STOP_REQUESTED", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHRR_RESET_NOT_REQUESTED", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHTAB_Q_MGR", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHTAB_CLNTCONN", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQCLRS_LOCAL", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQCLRS_GLOBAL", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQCLRT_RETAINED", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMDI_CMDSCOPE_ACCEPTED", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMDI_CMDSCOPE_GENERATED", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMDI_CMDSCOPE_COMPLETED", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMDI_QSG_DISP_COMPLETED", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMDI_COMMAND_ACCEPTED", 5);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMDI_CLUSTER_REQUEST_QUEUED", 6);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMDI_CHANNEL_INIT_STARTED", 7);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMDI_RECOVER_STARTED", 11);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMDI_BACKUP_STARTED", 12);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMDI_RECOVER_COMPLETED", 13);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMDI_SEC_TIMER_ZERO", 14);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMDI_REFRESH_CONFIGURATION", 16);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMDI_SEC_SIGNOFF_ERROR", 17);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMDI_IMS_BRIDGE_SUSPENDED", 18);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMDI_DB2_SUSPENDED", 19);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMDI_DB2_OBSOLETE_MSGS", 20);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMDI_SEC_UPPERCASE", 21);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMDI_SEC_MIXEDCASE", 22);
            ibmConstantAccessorsMap.put("CMQCFC.MQDISCONNECT_NORMAL", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQDISCONNECT_IMPLICIT", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQDISCONNECT_Q_MGR", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQET_MQSC", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQEVO_OTHER", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQEVO_CONSOLE", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQEVO_INIT", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQEVO_MSG", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQEVO_MQSET", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQEVO_INTERNAL", 5);
            ibmConstantAccessorsMap.put("CMQCFC.MQEVO_MQSUB", 6);
            ibmConstantAccessorsMap.put("CMQCFC.MQEVO_CTLMSG", 7);
            ibmConstantAccessorsMap.put("CMQCFC.MQEVO_REST", 8);
            ibmConstantAccessorsMap.put("CMQCFC.MQEVR_DISABLED", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQEVR_ENABLED", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQEVR_EXCEPTION", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQEVR_NO_DISPLAY", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQEVR_API_ONLY", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQEVR_ADMIN_ONLY", 5);
            ibmConstantAccessorsMap.put("CMQCFC.MQEVR_USER_ONLY", 6);
            ibmConstantAccessorsMap.put("CMQCFC.MQFC_YES", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQFC_NO", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQHSTATE_INACTIVE", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQHSTATE_ACTIVE", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQINBD_Q_MGR", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQINBD_GROUP", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQIDO_COMMIT", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQIDO_BACKOUT", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQMATCH_GENERIC", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQMATCH_RUNCHECK", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQMATCH_EXACT", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQMATCH_ALL", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQMCAS_STOPPED", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQMCAS_RUNNING", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQMODE_FORCE", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQMODE_QUIESCE", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQMODE_TERMINATE", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQMLP_TOLERATE_UNPROTECTED_NO", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQMLP_TOLERATE_UNPROTECTED_YES", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQMLP_ENCRYPTION_ALG_NONE", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQMLP_ENCRYPTION_ALG_RC2", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQMLP_ENCRYPTION_ALG_DES", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQMLP_ENCRYPTION_ALG_3DES", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQMLP_ENCRYPTION_ALG_AES128", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQMLP_ENCRYPTION_ALG_AES256", 5);
            ibmConstantAccessorsMap.put("CMQCFC.MQMLP_SIGN_ALG_NONE", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQMLP_SIGN_ALG_MD5", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQMLP_SIGN_ALG_SHA1", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQMLP_SIGN_ALG_SHA224", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQMLP_SIGN_ALG_SHA256", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQMLP_SIGN_ALG_SHA384", 5);
            ibmConstantAccessorsMap.put("CMQCFC.MQMLP_SIGN_ALG_SHA512", 6);
            ibmConstantAccessorsMap.put("CMQCFC.MQPO_YES", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQPO_NO", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQPSCT_NONE", -1);
            ibmConstantAccessorsMap.put("CMQCFC.MQPSST_ALL", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQPSST_LOCAL", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQPSST_PARENT", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQPSST_CHILD", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQPS_STATUS_INACTIVE", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQPS_STATUS_STARTING", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQPS_STATUS_STOPPING", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQPS_STATUS_ACTIVE", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQPS_STATUS_COMPAT", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQPS_STATUS_ERROR", 5);
            ibmConstantAccessorsMap.put("CMQCFC.MQPS_STATUS_REFUSED", 6);
            ibmConstantAccessorsMap.put("CMQCFC.MQQMDT_EXPLICIT_CLUSTER_SENDER", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQQMDT_AUTO_CLUSTER_SENDER", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQQMDT_AUTO_EXP_CLUSTER_SENDER", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQQMDT_CLUSTER_RECEIVER", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQQMFAC_IMS_BRIDGE", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQQMFAC_DB2", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQQMSTA_STARTING", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQQMSTA_RUNNING", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQQMSTA_QUIESCING", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQQMSTA_STANDBY", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQQMT_NORMAL", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQQMT_REPOSITORY", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQQO_YES", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQQO_NO", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQQSIE_NONE", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQQSIE_HIGH", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQQSIE_OK", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQQSOT_ALL", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQQSOT_INPUT", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQQSOT_OUTPUT", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQQSGS_UNKNOWN", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQQSGS_CREATED", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQQSGS_ACTIVE", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQQSGS_INACTIVE", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQQSGS_FAILED", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQQSGS_PENDING", 5);
            ibmConstantAccessorsMap.put("CMQCFC.MQQSO_NO", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQQSO_YES", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQQSO_SHARED", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQQSO_EXCLUSIVE", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQQSUM_YES", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQQSUM_NO", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQRAR_YES", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQRAR_NO", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQRP_YES", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQRP_NO", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_CONN_NOT_AUTHORIZED", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_OPEN_NOT_AUTHORIZED", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_CLOSE_NOT_AUTHORIZED", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_CMD_NOT_AUTHORIZED", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_Q_MGR_STOPPING", 5);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_Q_MGR_QUIESCING", 6);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_CHANNEL_STOPPED_OK", 7);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_CHANNEL_STOPPED_ERROR", 8);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_CHANNEL_STOPPED_RETRY", 9);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_CHANNEL_STOPPED_DISABLED", 10);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_BRIDGE_STOPPED_OK", 11);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_BRIDGE_STOPPED_ERROR", 12);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_SSL_HANDSHAKE_ERROR", 13);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_SSL_CIPHER_SPEC_ERROR", 14);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_SSL_CLIENT_AUTH_ERROR", 15);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_SSL_PEER_NAME_ERROR", 16);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_SUB_NOT_AUTHORIZED", 17);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_SUB_DEST_NOT_AUTHORIZED", 18);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_SSL_UNKNOWN_REVOCATION", 19);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_SYS_CONN_NOT_AUTHORIZED", 20);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_CHANNEL_BLOCKED_ADDRESS", 21);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_CHANNEL_BLOCKED_USERID", 22);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_CHANNEL_BLOCKED_NOACCESS", 23);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_MAX_ACTIVE_CHANNELS", 24);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_MAX_CHANNELS", 25);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_SVRCONN_INST_LIMIT", 26);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_CLIENT_INST_LIMIT", 27);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_CAF_NOT_INSTALLED", 28);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_CSP_NOT_AUTHORIZED", 29);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_FAILOVER_PERMITTED", 30);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_FAILOVER_NOT_PERMITTED", 31);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_STANDBY_ACTIVATED", 32);
            ibmConstantAccessorsMap.put("CMQCFC.MQRQ_REPLICA_ACTIVATED", 33);
            ibmConstantAccessorsMap.put("CMQCFC.MQRT_CONFIGURATION", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQRT_EXPIRY", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQRT_NSPROC", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQRT_PROXYSUB", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQRT_SUB_CONFIGURATION", 5);
            ibmConstantAccessorsMap.put("CMQCFC.MQSCO_Q_MGR", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQSCO_CELL", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECITEM_ALL", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECITEM_MQADMIN", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECITEM_MQNLIST", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECITEM_MQPROC", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECITEM_MQQUEUE", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECITEM_MQCONN", 5);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECITEM_MQCMDS", 6);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECITEM_MXADMIN", 7);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECITEM_MXNLIST", 8);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECITEM_MXPROC", 9);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECITEM_MXQUEUE", 10);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECITEM_MXTOPIC", 11);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECSW_PROCESS", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECSW_NAMELIST", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECSW_Q", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECSW_TOPIC", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECSW_CONTEXT", 6);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECSW_ALTERNATE_USER", 7);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECSW_COMMAND", 8);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECSW_CONNECTION", 9);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECSW_SUBSYSTEM", 10);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECSW_COMMAND_RESOURCES", 11);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECSW_Q_MGR", 15);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECSW_QSG", 16);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECSW_OFF_FOUND", 21);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECSW_ON_FOUND", 22);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECSW_OFF_NOT_FOUND", 23);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECSW_ON_NOT_FOUND", 24);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECSW_OFF_ERROR", 25);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECSW_ON_OVERRIDDEN", 26);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECTYPE_AUTHSERV", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECTYPE_SSL", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECTYPE_CLASSES", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECTYPE_CONNAUTH", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHK_OPTIONAL", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHK_NONE", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHK_REQUIRED_ADMIN", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHK_REQUIRED", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHK_AS_Q_MGR", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQADPCTX_NO", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQADPCTX_YES", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECCOMM_NO", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECCOMM_YES", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQSECCOMM_ANON", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQLDAP_AUTHORMD_OS", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQLDAP_AUTHORMD_SEARCHGRP", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQLDAP_AUTHORMD_SEARCHUSR", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQLDAP_AUTHORMD_SRCHGRPSN", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQLDAP_NESTGRP_NO", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQLDAP_NESTGRP_YES", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTHENTICATE_OS", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQAUTHENTICATE_PAM", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQLDAPC_INACTIVE", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQLDAPC_CONNECTED", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQLDAPC_ERROR", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQSELTYPE_NONE", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQSELTYPE_STANDARD", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQSELTYPE_EXTENDED", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHLA_DISABLED", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQCHLA_ENABLED", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQRDNS_ENABLED", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQRDNS_DISABLED", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQCLROUTE_DIRECT", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQCLROUTE_TOPIC_HOST", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQCLROUTE_NONE", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQCLST_ACTIVE", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQCLST_PENDING", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQCLST_INVALID", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQCLST_ERROR", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQCLXQ_SCTQ", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQCLXQ_CHANNEL", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQSUS_YES", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQSUS_NO", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQSYNCPOINT_YES", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQSYNCPOINT_IFPER", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQSYSP_NO", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQSYSP_YES", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQSYSP_EXTENDED", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQSYSP_TYPE_INITIAL", 10);
            ibmConstantAccessorsMap.put("CMQCFC.MQSYSP_TYPE_SET", 11);
            ibmConstantAccessorsMap.put("CMQCFC.MQSYSP_TYPE_LOG_COPY", 12);
            ibmConstantAccessorsMap.put("CMQCFC.MQSYSP_TYPE_LOG_STATUS", 13);
            ibmConstantAccessorsMap.put("CMQCFC.MQSYSP_TYPE_ARCHIVE_TAPE", 14);
            ibmConstantAccessorsMap.put("CMQCFC.MQSYSP_ALLOC_BLK", 20);
            ibmConstantAccessorsMap.put("CMQCFC.MQSYSP_ALLOC_TRK", 21);
            ibmConstantAccessorsMap.put("CMQCFC.MQSYSP_ALLOC_CYL", 22);
            ibmConstantAccessorsMap.put("CMQCFC.MQSYSP_STATUS_BUSY", 30);
            ibmConstantAccessorsMap.put("CMQCFC.MQSYSP_STATUS_PREMOUNT", 31);
            ibmConstantAccessorsMap.put("CMQCFC.MQSYSP_STATUS_AVAILABLE", 32);
            ibmConstantAccessorsMap.put("CMQCFC.MQSYSP_STATUS_UNKNOWN", 33);
            ibmConstantAccessorsMap.put("CMQCFC.MQSYSP_STATUS_ALLOC_ARCHIVE", 34);
            ibmConstantAccessorsMap.put("CMQCFC.MQSYSP_STATUS_COPYING_BSDS", 35);
            ibmConstantAccessorsMap.put("CMQCFC.MQSYSP_STATUS_COPYING_LOG", 36);
            ibmConstantAccessorsMap.put("CMQCFC.MQEXT_ALL", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQEXT_OBJECT", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQEXT_AUTHORITY", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQEXTATTRS_ALL", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQEXTATTRS_NONDEF", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQSYSOBJ_YES", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQSYSOBJ_NO", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQSUBTYPE_API", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQSUBTYPE_ADMIN", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQSUBTYPE_PROXY", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQSUBTYPE_ALL", -1);
            ibmConstantAccessorsMap.put("CMQCFC.MQSUBTYPE_USER", -2);
            ibmConstantAccessorsMap.put("CMQCFC.MQDOPT_RESOLVED", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQDOPT_DEFINED", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQTIME_UNIT_MINS", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQTIME_UNIT_SECS", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQUIDSUPP_NO", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQUIDSUPP_YES", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQUNDELIVERED_NORMAL", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQUNDELIVERED_SAFE", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQUNDELIVERED_DISCARD", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQUNDELIVERED_KEEP", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQUOWST_NONE", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQUOWST_ACTIVE", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQUOWST_PREPARED", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQUOWST_UNRESOLVED", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQUOWT_Q_MGR", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQUOWT_CICS", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQUOWT_RRS", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQUOWT_IMS", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQUOWT_XA", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQUSAGE_PS_AVAILABLE", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQUSAGE_PS_DEFINED", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQUSAGE_PS_OFFLINE", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQUSAGE_PS_NOT_DEFINED", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQUSAGE_PS_SUSPENDED", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQUSAGE_EXPAND_USER", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQUSAGE_EXPAND_SYSTEM", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQUSAGE_EXPAND_NONE", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQUSAGE_DS_OLDEST_ACTIVE_UOW", 10);
            ibmConstantAccessorsMap.put("CMQCFC.MQUSAGE_DS_OLDEST_PS_RECOVERY", 11);
            ibmConstantAccessorsMap.put("CMQCFC.MQUSAGE_DS_OLDEST_CF_RECOVERY", 12);
            ibmConstantAccessorsMap.put("CMQCFC.MQMCP_REPLY", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQMCP_USER", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQMCP_NONE", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQMCP_ALL", -1);
            ibmConstantAccessorsMap.put("CMQCFC.MQMCP_COMPAT", -2);
            ibmConstantAccessorsMap.put("CMQCFC.MQNSH_NONE", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQNSH_ALL", -1);
            ibmConstantAccessorsMap.put("CMQCFC.MQLR_ONE", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQLR_AUTO", -1);
            ibmConstantAccessorsMap.put("CMQCFC.MQLR_MAX", -2);
            ibmConstantAccessorsMap.put("CMQCFC.MQOPER_SYSTEM_FIRST", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQOPER_UNKNOWN", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQOPER_BROWSE", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQOPER_DISCARD", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQOPER_GET", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQOPER_PUT", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQOPER_PUT_REPLY", 5);
            ibmConstantAccessorsMap.put("CMQCFC.MQOPER_PUT_REPORT", 6);
            ibmConstantAccessorsMap.put("CMQCFC.MQOPER_RECEIVE", 7);
            ibmConstantAccessorsMap.put("CMQCFC.MQOPER_SEND", 8);
            ibmConstantAccessorsMap.put("CMQCFC.MQOPER_TRANSFORM", 9);
            ibmConstantAccessorsMap.put("CMQCFC.MQOPER_PUBLISH", 10);
            ibmConstantAccessorsMap.put("CMQCFC.MQOPER_EXCLUDED_PUBLISH", 11);
            ibmConstantAccessorsMap.put("CMQCFC.MQOPER_DISCARDED_PUBLISH", 12);
            ibmConstantAccessorsMap.put("CMQCFC.MQOPER_SYSTEM_LAST", 65535);
            ibmConstantAccessorsMap.put("CMQCFC.MQOPER_APPL_FIRST", 65536);
            ibmConstantAccessorsMap.put("CMQCFC.MQOPER_APPL_LAST", 999999999);
            ibmConstantAccessorsMap.put("CMQCFC.MQROUTE_UNLIMITED_ACTIVITIES", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQROUTE_DETAIL_LOW", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQROUTE_DETAIL_MEDIUM", 8);
            ibmConstantAccessorsMap.put("CMQCFC.MQROUTE_DETAIL_HIGH", 32);
            ibmConstantAccessorsMap.put("CMQCFC.MQROUTE_FORWARD_ALL", 256);
            ibmConstantAccessorsMap.put("CMQCFC.MQROUTE_FORWARD_IF_SUPPORTED", 512);
            ibmConstantAccessorsMap.put("CMQCFC.MQROUTE_FORWARD_REJ_UNSUP_MASK", -65536);
            ibmConstantAccessorsMap.put("CMQCFC.MQROUTE_DELIVER_YES", 4096);
            ibmConstantAccessorsMap.put("CMQCFC.MQROUTE_DELIVER_NO", 8192);
            ibmConstantAccessorsMap.put("CMQCFC.MQROUTE_DELIVER_REJ_UNSUP_MASK", -65536);
            ibmConstantAccessorsMap.put("CMQCFC.MQROUTE_ACCUMULATE_NONE", 65539);
            ibmConstantAccessorsMap.put("CMQCFC.MQROUTE_ACCUMULATE_IN_MSG", 65540);
            ibmConstantAccessorsMap.put("CMQCFC.MQROUTE_ACCUMULATE_AND_REPLY", 65541);
            ibmConstantAccessorsMap.put("CMQCFC.MQDELO_NONE", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQDELO_LOCAL", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQPUBO_NONE", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQPUBO_CORREL_ID_AS_IDENTITY", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQPUBO_RETAIN_PUBLICATION", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQPUBO_OTHER_SUBSCRIBERS_ONLY", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQPUBO_NO_REGISTRATION", 8);
            ibmConstantAccessorsMap.put("CMQCFC.MQPUBO_IS_RETAINED_PUBLICATION", 16);
            ibmConstantAccessorsMap.put("CMQCFC.MQREGO_NONE", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQREGO_CORREL_ID_AS_IDENTITY", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQREGO_ANONYMOUS", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQREGO_LOCAL", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQREGO_DIRECT_REQUESTS", 8);
            ibmConstantAccessorsMap.put("CMQCFC.MQREGO_NEW_PUBLICATIONS_ONLY", 16);
            ibmConstantAccessorsMap.put("CMQCFC.MQREGO_PUBLISH_ON_REQUEST_ONLY", 32);
            ibmConstantAccessorsMap.put("CMQCFC.MQREGO_DEREGISTER_ALL", 64);
            ibmConstantAccessorsMap.put("CMQCFC.MQREGO_INCLUDE_STREAM_NAME", 128);
            ibmConstantAccessorsMap.put("CMQCFC.MQREGO_INFORM_IF_RETAINED", 256);
            ibmConstantAccessorsMap.put("CMQCFC.MQREGO_DUPLICATES_OK", 512);
            ibmConstantAccessorsMap.put("CMQCFC.MQREGO_NON_PERSISTENT", 1024);
            ibmConstantAccessorsMap.put("CMQCFC.MQREGO_PERSISTENT", 2048);
            ibmConstantAccessorsMap.put("CMQCFC.MQREGO_PERSISTENT_AS_PUBLISH", 4096);
            ibmConstantAccessorsMap.put("CMQCFC.MQREGO_PERSISTENT_AS_Q", 8192);
            ibmConstantAccessorsMap.put("CMQCFC.MQREGO_ADD_NAME", 16384);
            ibmConstantAccessorsMap.put("CMQCFC.MQREGO_NO_ALTERATION", 32768);
            ibmConstantAccessorsMap.put("CMQCFC.MQREGO_FULL_RESPONSE", 65536);
            ibmConstantAccessorsMap.put("CMQCFC.MQREGO_JOIN_SHARED", 131072);
            ibmConstantAccessorsMap.put("CMQCFC.MQREGO_JOIN_EXCLUSIVE", 262144);
            ibmConstantAccessorsMap.put("CMQCFC.MQREGO_LEAVE_ONLY", 524288);
            ibmConstantAccessorsMap.put("CMQCFC.MQREGO_VARIABLE_USER_ID", 1048576);
            ibmConstantAccessorsMap.put("CMQCFC.MQREGO_LOCKED", 2097152);
            ibmConstantAccessorsMap.put("CMQCFC.MQUA_FIRST", 65536);
            ibmConstantAccessorsMap.put("CMQCFC.MQUA_LAST", 999999999);
            ibmConstantAccessorsMap.put("CMQCFC.MQGUR_DISABLED", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQGUR_ENABLED", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQMULC_STANDARD", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQMULC_REFINED", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQSTDBY_NOT_PERMITTED", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQSTDBY_PERMITTED", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFH_STRUC_LENGTH", 36);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFH_VERSION_1", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFH_VERSION_2", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFH_VERSION_3", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFH_CURRENT_VERSION", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_NONE", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CHANGE_Q_MGR", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_Q_MGR", 2);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CHANGE_PROCESS", 3);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_COPY_PROCESS", 4);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CREATE_PROCESS", 5);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_DELETE_PROCESS", 6);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_PROCESS", 7);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CHANGE_Q", 8);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CLEAR_Q", 9);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_COPY_Q", 10);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CREATE_Q", 11);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_DELETE_Q", 12);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_Q", 13);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_REFRESH_Q_MGR", 16);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_RESET_Q_STATS", 17);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_Q_NAMES", 18);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_PROCESS_NAMES", 19);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_CHANNEL_NAMES", 20);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CHANGE_CHANNEL", 21);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_COPY_CHANNEL", 22);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CREATE_CHANNEL", 23);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_DELETE_CHANNEL", 24);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_CHANNEL", 25);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_PING_CHANNEL", 26);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_RESET_CHANNEL", 27);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_START_CHANNEL", 28);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_STOP_CHANNEL", 29);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_START_CHANNEL_INIT", 30);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_START_CHANNEL_LISTENER", 31);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CHANGE_NAMELIST", 32);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_COPY_NAMELIST", 33);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CREATE_NAMELIST", 34);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_DELETE_NAMELIST", 35);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_NAMELIST", 36);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_NAMELIST_NAMES", 37);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_ESCAPE", 38);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_RESOLVE_CHANNEL", 39);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_PING_Q_MGR", 40);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_Q_STATUS", 41);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_CHANNEL_STATUS", 42);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CONFIG_EVENT", 43);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_Q_MGR_EVENT", 44);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_PERFM_EVENT", 45);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CHANNEL_EVENT", 46);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_DELETE_PUBLICATION", 60);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_DEREGISTER_PUBLISHER", 61);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_DEREGISTER_SUBSCRIBER", 62);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_PUBLISH", 63);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_REGISTER_PUBLISHER", 64);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_REGISTER_SUBSCRIBER", 65);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_REQUEST_UPDATE", 66);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_BROKER_INTERNAL", 67);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_ACTIVITY_MSG", 69);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_CLUSTER_Q_MGR", 70);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_RESUME_Q_MGR_CLUSTER", 71);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_SUSPEND_Q_MGR_CLUSTER", 72);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_REFRESH_CLUSTER", 73);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_RESET_CLUSTER", 74);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_TRACE_ROUTE", 75);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_REFRESH_SECURITY", 78);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CHANGE_AUTH_INFO", 79);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_COPY_AUTH_INFO", 80);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CREATE_AUTH_INFO", 81);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_DELETE_AUTH_INFO", 82);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_AUTH_INFO", 83);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_AUTH_INFO_NAMES", 84);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_CONNECTION", 85);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_STOP_CONNECTION", 86);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_AUTH_RECS", 87);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_ENTITY_AUTH", 88);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_DELETE_AUTH_REC", 89);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_SET_AUTH_REC", 90);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_LOGGER_EVENT", 91);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_RESET_Q_MGR", 92);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CHANGE_LISTENER", 93);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_COPY_LISTENER", 94);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CREATE_LISTENER", 95);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_DELETE_LISTENER", 96);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_LISTENER", 97);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_LISTENER_STATUS", 98);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_COMMAND_EVENT", 99);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CHANGE_SECURITY", 100);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CHANGE_CF_STRUC", 101);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CHANGE_STG_CLASS", 102);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CHANGE_TRACE", 103);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_ARCHIVE_LOG", 104);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_BACKUP_CF_STRUC", 105);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CREATE_BUFFER_POOL", 106);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CREATE_PAGE_SET", 107);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CREATE_CF_STRUC", 108);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CREATE_STG_CLASS", 109);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_COPY_CF_STRUC", 110);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_COPY_STG_CLASS", 111);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_DELETE_CF_STRUC", 112);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_DELETE_STG_CLASS", 113);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_ARCHIVE", 114);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_CF_STRUC", 115);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_CF_STRUC_STATUS", 116);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_CMD_SERVER", 117);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_CHANNEL_INIT", 118);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_QSG", 119);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_LOG", 120);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_SECURITY", 121);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_STG_CLASS", 122);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_SYSTEM", 123);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_THREAD", 124);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_TRACE", 125);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_USAGE", 126);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_MOVE_Q", 127);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_RECOVER_BSDS", 128);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_RECOVER_CF_STRUC", 129);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_RESET_TPIPE", 130);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_RESOLVE_INDOUBT", 131);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_RESUME_Q_MGR", 132);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_REVERIFY_SECURITY", 133);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_SET_ARCHIVE", 134);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_SET_LOG", 136);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_SET_SYSTEM", 137);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_START_CMD_SERVER", 138);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_START_Q_MGR", 139);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_START_TRACE", 140);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_STOP_CHANNEL_INIT", 141);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_STOP_CHANNEL_LISTENER", 142);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_STOP_CMD_SERVER", 143);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_STOP_Q_MGR", 144);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_STOP_TRACE", 145);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_SUSPEND_Q_MGR", 146);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_CF_STRUC_NAMES", 147);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_STG_CLASS_NAMES", 148);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CHANGE_SERVICE", 149);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_COPY_SERVICE", 150);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CREATE_SERVICE", 151);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_DELETE_SERVICE", 152);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_SERVICE", 153);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_SERVICE_STATUS", 154);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_START_SERVICE", 155);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_STOP_SERVICE", 156);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_DELETE_BUFFER_POOL", 157);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_DELETE_PAGE_SET", 158);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CHANGE_BUFFER_POOL", 159);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CHANGE_PAGE_SET", 160);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_Q_MGR_STATUS", 161);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CREATE_LOG", 162);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_STATISTICS_MQI", 164);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_STATISTICS_Q", 165);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_STATISTICS_CHANNEL", 166);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_ACCOUNTING_MQI", 167);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_ACCOUNTING_Q", 168);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_AUTH_SERVICE", 169);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CHANGE_TOPIC", 170);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_COPY_TOPIC", 171);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CREATE_TOPIC", 172);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_DELETE_TOPIC", 173);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_TOPIC", 174);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_TOPIC_NAMES", 175);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_SUBSCRIPTION", 176);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CREATE_SUBSCRIPTION", 177);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CHANGE_SUBSCRIPTION", 178);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_DELETE_SUBSCRIPTION", 179);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_COPY_SUBSCRIPTION", 181);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_SUB_STATUS", 182);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_TOPIC_STATUS", 183);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CLEAR_TOPIC_STRING", 184);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_PUBSUB_STATUS", 185);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_SMDS", 186);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CHANGE_SMDS", 187);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_RESET_SMDS", 188);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CREATE_COMM_INFO", 190);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_COMM_INFO", 191);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CHANGE_COMM_INFO", 192);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_COPY_COMM_INFO", 193);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_DELETE_COMM_INFO", 194);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_PURGE_CHANNEL", 195);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_MQXR_DIAGNOSTICS", 196);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_START_SMDSCONN", 197);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_STOP_SMDSCONN", 198);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_SMDSCONN", 199);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_MQXR_STATUS", 200);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_START_CLIENT_TRACE", 201);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_STOP_CLIENT_TRACE", 202);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_SET_CHLAUTH_REC", 203);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_CHLAUTH_RECS", 204);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_PROT_POLICY", 205);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CREATE_PROT_POLICY", 206);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_DELETE_PROT_POLICY", 207);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_CHANGE_PROT_POLICY", 208);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_SET_PROT_POLICY", 208);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_ACTIVITY_TRACE", 209);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_RESET_CF_STRUC", 213);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_XR_CAPABILITY", 214);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_AMQP_CAPABILITY", 216);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_AMQP_DIAGNOSTICS", 217);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INTER_Q_MGR_STATUS", 218);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INTER_Q_MGR_BALANCE", 219);
            ibmConstantAccessorsMap.put("CMQCFC.MQCMD_INQUIRE_APPL_STATUS", 220);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFC_LAST", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFC_NOT_LAST", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFH_TYPE_ERROR", 3001);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFH_LENGTH_ERROR", 3002);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFH_VERSION_ERROR", 3003);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFH_MSG_SEQ_NUMBER_ERR", 3004);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFH_CONTROL_ERROR", 3005);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFH_PARM_COUNT_ERROR", 3006);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFH_COMMAND_ERROR", 3007);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_COMMAND_FAILED", 3008);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFIN_LENGTH_ERROR", 3009);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFST_LENGTH_ERROR", 3010);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFST_STRING_LENGTH_ERR", 3011);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_FORCE_VALUE_ERROR", 3012);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_STRUCTURE_TYPE_ERROR", 3013);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFIN_PARM_ID_ERROR", 3014);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFST_PARM_ID_ERROR", 3015);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_MSG_LENGTH_ERROR", 3016);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFIN_DUPLICATE_PARM", 3017);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFST_DUPLICATE_PARM", 3018);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_PARM_COUNT_TOO_SMALL", 3019);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_PARM_COUNT_TOO_BIG", 3020);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_Q_ALREADY_IN_CELL", 3021);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_Q_TYPE_ERROR", 3022);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_MD_FORMAT_ERROR", 3023);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFSL_LENGTH_ERROR", 3024);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_REPLACE_VALUE_ERROR", 3025);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFIL_DUPLICATE_VALUE", 3026);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFIL_COUNT_ERROR", 3027);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFIL_LENGTH_ERROR", 3028);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_QUIESCE_VALUE_ERROR", 3029);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_MODE_VALUE_ERROR", 3029);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_MSG_SEQ_NUMBER_ERROR", 3030);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_PING_DATA_COUNT_ERROR", 3031);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_PING_DATA_COMPARE_ERROR", 3032);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFSL_PARM_ID_ERROR", 3033);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHANNEL_TYPE_ERROR", 3034);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_PARM_SEQUENCE_ERROR", 3035);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_XMIT_PROTOCOL_TYPE_ERR", 3036);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_BATCH_SIZE_ERROR", 3037);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_DISC_INT_ERROR", 3038);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SHORT_RETRY_ERROR", 3039);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SHORT_TIMER_ERROR", 3040);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_LONG_RETRY_ERROR", 3041);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_LONG_TIMER_ERROR", 3042);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SEQ_NUMBER_WRAP_ERROR", 3043);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_MAX_MSG_LENGTH_ERROR", 3044);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_PUT_AUTH_ERROR", 3045);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_PURGE_VALUE_ERROR", 3046);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFIL_PARM_ID_ERROR", 3047);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_MSG_TRUNCATED", 3048);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CCSID_ERROR", 3049);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_ENCODING_ERROR", 3050);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_QUEUES_VALUE_ERROR", 3051);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_DATA_CONV_VALUE_ERROR", 3052);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_INDOUBT_VALUE_ERROR", 3053);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_ESCAPE_TYPE_ERROR", 3054);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_REPOS_VALUE_ERROR", 3055);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHANNEL_TABLE_ERROR", 3062);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_MCA_TYPE_ERROR", 3063);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHL_INST_TYPE_ERROR", 3064);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHL_STATUS_NOT_FOUND", 3065);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFSL_DUPLICATE_PARM", 3066);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFSL_TOTAL_LENGTH_ERROR", 3067);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFSL_COUNT_ERROR", 3068);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFSL_STRING_LENGTH_ERR", 3069);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_BROKER_DELETED", 3070);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_STREAM_ERROR", 3071);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_TOPIC_ERROR", 3072);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_NOT_REGISTERED", 3073);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_Q_MGR_NAME_ERROR", 3074);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_INCORRECT_STREAM", 3075);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_Q_NAME_ERROR", 3076);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_NO_RETAINED_MSG", 3077);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_DUPLICATE_IDENTITY", 3078);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_INCORRECT_Q", 3079);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CORREL_ID_ERROR", 3080);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_NOT_AUTHORIZED", 3081);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_UNKNOWN_STREAM", 3082);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_REG_OPTIONS_ERROR", 3083);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_PUB_OPTIONS_ERROR", 3084);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_UNKNOWN_BROKER", 3085);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_Q_MGR_CCSID_ERROR", 3086);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_DEL_OPTIONS_ERROR", 3087);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CLUSTER_NAME_CONFLICT", 3088);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_REPOS_NAME_CONFLICT", 3089);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CLUSTER_Q_USAGE_ERROR", 3090);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_ACTION_VALUE_ERROR", 3091);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_COMMS_LIBRARY_ERROR", 3092);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_NETBIOS_NAME_ERROR", 3093);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_BROKER_COMMAND_FAILED", 3094);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFST_CONFLICTING_PARM", 3095);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_PATH_NOT_VALID", 3096);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_PARM_SYNTAX_ERROR", 3097);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_PWD_LENGTH_ERROR", 3098);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_FILTER_ERROR", 3150);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_WRONG_USER", 3151);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_DUPLICATE_SUBSCRIPTION", 3152);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SUB_NAME_ERROR", 3153);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SUB_IDENTITY_ERROR", 3154);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SUBSCRIPTION_IN_USE", 3155);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SUBSCRIPTION_LOCKED", 3156);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_ALREADY_JOINED", 3157);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_OBJECT_IN_USE", 3160);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_UNKNOWN_FILE_NAME", 3161);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_FILE_NOT_AVAILABLE", 3162);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_DISC_RETRY_ERROR", 3163);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_ALLOC_RETRY_ERROR", 3164);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_ALLOC_SLOW_TIMER_ERROR", 3165);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_ALLOC_FAST_TIMER_ERROR", 3166);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_PORT_NUMBER_ERROR", 3167);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHL_SYSTEM_NOT_ACTIVE", 3168);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_ENTITY_NAME_MISSING", 3169);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_PROFILE_NAME_ERROR", 3170);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_AUTH_VALUE_ERROR", 3171);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_AUTH_VALUE_MISSING", 3172);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_OBJECT_TYPE_MISSING", 3173);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CONNECTION_ID_ERROR", 3174);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_LOG_TYPE_ERROR", 3175);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_PROGRAM_NOT_AVAILABLE", 3176);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_PROGRAM_AUTH_FAILED", 3177);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_NONE_FOUND", 3200);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SECURITY_SWITCH_OFF", 3201);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SECURITY_REFRESH_FAILED", 3202);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_PARM_CONFLICT", 3203);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_COMMAND_INHIBITED", 3204);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_OBJECT_BEING_DELETED", 3205);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_STORAGE_CLASS_IN_USE", 3207);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_OBJECT_NAME_RESTRICTED", 3208);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_OBJECT_LIMIT_EXCEEDED", 3209);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_OBJECT_OPEN_FORCE", 3210);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_DISPOSITION_CONFLICT", 3211);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_Q_MGR_NOT_IN_QSG", 3212);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_ATTR_VALUE_FIXED", 3213);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_NAMELIST_ERROR", 3215);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_NO_CHANNEL_INITIATOR", 3217);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHANNEL_INITIATOR_ERROR", 3218);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_COMMAND_LEVEL_CONFLICT", 3222);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_Q_ATTR_CONFLICT", 3223);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_EVENTS_DISABLED", 3224);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_COMMAND_SCOPE_ERROR", 3225);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_COMMAND_REPLY_ERROR", 3226);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_FUNCTION_RESTRICTED", 3227);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_PARM_MISSING", 3228);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_PARM_VALUE_ERROR", 3229);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_COMMAND_LENGTH_ERROR", 3230);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_COMMAND_ORIGIN_ERROR", 3231);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_LISTENER_CONFLICT", 3232);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_LISTENER_STARTED", 3233);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_LISTENER_STOPPED", 3234);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHANNEL_ERROR", 3235);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CF_STRUC_ERROR", 3236);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_UNKNOWN_USER_ID", 3237);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_UNEXPECTED_ERROR", 3238);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_NO_XCF_PARTNER", 3239);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFGR_PARM_ID_ERROR", 3240);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFIF_LENGTH_ERROR", 3241);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFIF_OPERATOR_ERROR", 3242);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFIF_PARM_ID_ERROR", 3243);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFSF_FILTER_VAL_LEN_ERR", 3244);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFSF_LENGTH_ERROR", 3245);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFSF_OPERATOR_ERROR", 3246);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFSF_PARM_ID_ERROR", 3247);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_TOO_MANY_FILTERS", 3248);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_LISTENER_RUNNING", 3249);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_LSTR_STATUS_NOT_FOUND", 3250);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SERVICE_RUNNING", 3251);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SERV_STATUS_NOT_FOUND", 3252);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SERVICE_STOPPED", 3253);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFBS_DUPLICATE_PARM", 3254);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFBS_LENGTH_ERROR", 3255);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFBS_PARM_ID_ERROR", 3256);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFBS_STRING_LENGTH_ERR", 3257);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFGR_LENGTH_ERROR", 3258);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFGR_PARM_COUNT_ERROR", 3259);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CONN_NOT_STOPPED", 3260);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SERVICE_REQUEST_PENDING", 3261);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_NO_START_CMD", 3262);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_NO_STOP_CMD", 3263);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFBF_LENGTH_ERROR", 3264);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFBF_PARM_ID_ERROR", 3265);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFBF_OPERATOR_ERROR", 3266);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFBF_FILTER_VAL_LEN_ERR", 3267);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_LISTENER_STILL_ACTIVE", 3268);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_DEF_XMIT_Q_CLUS_ERROR", 3269);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_TOPICSTR_ALREADY_EXISTS", 3300);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SHARING_CONVS_ERROR", 3301);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SHARING_CONVS_TYPE", 3302);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SECURITY_CASE_CONFLICT", 3303);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_TOPIC_TYPE_ERROR", 3305);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_MAX_INSTANCES_ERROR", 3306);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_MAX_INSTS_PER_CLNT_ERR", 3307);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_TOPIC_STRING_NOT_FOUND", 3308);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SUBSCRIPTION_POINT_ERR", 3309);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SUB_ALREADY_EXISTS", 3311);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_UNKNOWN_OBJECT_NAME", 3312);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_REMOTE_Q_NAME_ERROR", 3313);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_DURABILITY_NOT_ALLOWED", 3314);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_HOBJ_ERROR", 3315);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_DEST_NAME_ERROR", 3316);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_INVALID_DESTINATION", 3317);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_PUBSUB_INHIBITED", 3318);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_GROUPUR_CHECKS_FAILED", 3319);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_COMM_INFO_TYPE_ERROR", 3320);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_USE_CLIENT_ID_ERROR", 3321);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CLIENT_ID_NOT_FOUND", 3322);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CLIENT_ID_ERROR", 3323);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_PORT_IN_USE", 3324);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SSL_ALT_PROVIDER_REQD", 3325);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHLAUTH_TYPE_ERROR", 3326);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHLAUTH_ACTION_ERROR", 3327);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_POLICY_NOT_FOUND", 3328);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_ENCRYPTION_ALG_ERROR", 3329);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SIGNATURE_ALG_ERROR", 3330);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_TOLERATION_POL_ERROR", 3331);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_POLICY_VERSION_ERROR", 3332);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_RECIPIENT_DN_MISSING", 3333);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_POLICY_NAME_MISSING", 3334);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHLAUTH_USERSRC_ERROR", 3335);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_WRONG_CHLAUTH_TYPE", 3336);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHLAUTH_ALREADY_EXISTS", 3337);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHLAUTH_NOT_FOUND", 3338);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_WRONG_CHLAUTH_ACTION", 3339);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_WRONG_CHLAUTH_USERSRC", 3340);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHLAUTH_WARN_ERROR", 3341);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_WRONG_CHLAUTH_MATCH", 3342);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_IPADDR_RANGE_CONFLICT", 3343);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHLAUTH_MAX_EXCEEDED", 3344);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_IPADDR_ERROR", 3345);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_ADDRESS_ERROR", 3345);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_IPADDR_RANGE_ERROR", 3346);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_PROFILE_NAME_MISSING", 3347);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHLAUTH_CLNTUSER_ERROR", 3348);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHLAUTH_NAME_ERROR", 3349);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHLAUTH_RUNCHECK_ERROR", 3350);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CF_STRUC_ALREADY_FAILED", 3351);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CFCONLOS_CHECKS_FAILED", 3352);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SUITE_B_ERROR", 3353);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHANNEL_NOT_STARTED", 3354);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CUSTOM_ERROR", 3355);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_BACKLOG_OUT_OF_RANGE", 3356);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHLAUTH_DISABLED", 3357);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SMDS_REQUIRES_DSGROUP", 3358);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_PSCLUS_DISABLED_TOPDEF", 3359);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_PSCLUS_TOPIC_EXISTS", 3360);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SSL_CIPHER_SUITE_ERROR", 3361);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SOCKET_ERROR", 3362);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CLUS_XMIT_Q_USAGE_ERROR", 3363);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CERT_VAL_POLICY_ERROR", 3364);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_INVALID_PROTOCOL", 3365);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_REVDNS_DISABLED", 3366);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CLROUTE_NOT_ALTERABLE", 3367);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CLUSTER_TOPIC_CONFLICT", 3368);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_DEFCLXQ_MODEL_Q_ERROR", 3369);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHLAUTH_CHKCLI_ERROR", 3370);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CERT_LABEL_NOT_ALLOWED", 3371);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_Q_MGR_ATTR_CONFLICT", 3372);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_ENTITY_TYPE_MISSING", 3373);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CLWL_EXIT_NAME_ERROR", 3374);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SERVICE_NAME_ERROR", 3375);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_REMOTE_CHL_TYPE_ERROR", 3376);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_TOPIC_RESTRICTED", 3377);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CURRENT_LOG_EXTENT", 3378);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_LOG_EXTENT_NOT_FOUND", 3379);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_LOG_NOT_REDUCED", 3380);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_LOG_EXTENT_ERROR", 3381);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_ACCESS_BLOCKED", 3382);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_PS_REQUIRED_MQUC", 3383);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_STREAMQ_DEST_NOT_SUPP", 3384);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_STREAMQ_DEST_CONFLICT", 3385);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_STREAMQ_NOT_SUPPORTED", 3386);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_STREAMQ_CONFLICT", 3387);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_OBJECT_ALREADY_EXISTS", 4001);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_OBJECT_WRONG_TYPE", 4002);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_LIKE_OBJECT_WRONG_TYPE", 4003);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_OBJECT_OPEN", 4004);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_ATTR_VALUE_ERROR", 4005);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_UNKNOWN_Q_MGR", 4006);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_Q_WRONG_TYPE", 4007);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_OBJECT_NAME_ERROR", 4008);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_ALLOCATE_FAILED", 4009);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_HOST_NOT_AVAILABLE", 4010);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CONFIGURATION_ERROR", 4011);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CONNECTION_REFUSED", 4012);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_ENTRY_ERROR", 4013);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SEND_FAILED", 4014);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_RECEIVED_DATA_ERROR", 4015);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_RECEIVE_FAILED", 4016);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CONNECTION_CLOSED", 4017);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_NO_STORAGE", 4018);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_NO_COMMS_MANAGER", 4019);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_LISTENER_NOT_STARTED", 4020);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_BIND_FAILED", 4024);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHANNEL_INDOUBT", 4025);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_MQCONN_FAILED", 4026);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_MQOPEN_FAILED", 4027);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_MQGET_FAILED", 4028);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_MQPUT_FAILED", 4029);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_PING_ERROR", 4030);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHANNEL_IN_USE", 4031);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHANNEL_NOT_FOUND", 4032);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_UNKNOWN_REMOTE_CHANNEL", 4033);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_REMOTE_QM_UNAVAILABLE", 4034);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_REMOTE_QM_TERMINATING", 4035);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_MQINQ_FAILED", 4036);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_NOT_XMIT_Q", 4037);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHANNEL_DISABLED", 4038);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_USER_EXIT_NOT_AVAILABLE", 4039);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_COMMIT_FAILED", 4040);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_WRONG_CHANNEL_TYPE", 4041);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHANNEL_ALREADY_EXISTS", 4042);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_DATA_TOO_LARGE", 4043);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHANNEL_NAME_ERROR", 4044);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_XMIT_Q_NAME_ERROR", 4045);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_MCA_NAME_ERROR", 4047);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SEND_EXIT_NAME_ERROR", 4048);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SEC_EXIT_NAME_ERROR", 4049);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_MSG_EXIT_NAME_ERROR", 4050);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_RCV_EXIT_NAME_ERROR", 4051);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_XMIT_Q_NAME_WRONG_TYPE", 4052);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_MCA_NAME_WRONG_TYPE", 4053);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_DISC_INT_WRONG_TYPE", 4054);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SHORT_RETRY_WRONG_TYPE", 4055);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SHORT_TIMER_WRONG_TYPE", 4056);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_LONG_RETRY_WRONG_TYPE", 4057);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_LONG_TIMER_WRONG_TYPE", 4058);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_PUT_AUTH_WRONG_TYPE", 4059);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_KEEP_ALIVE_INT_ERROR", 4060);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_MISSING_CONN_NAME", 4061);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CONN_NAME_ERROR", 4062);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_MQSET_FAILED", 4063);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHANNEL_NOT_ACTIVE", 4064);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_TERMINATED_BY_SEC_EXIT", 4065);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_DYNAMIC_Q_SCOPE_ERROR", 4067);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CELL_DIR_NOT_AVAILABLE", 4068);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_MR_COUNT_ERROR", 4069);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_MR_COUNT_WRONG_TYPE", 4070);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_MR_EXIT_NAME_ERROR", 4071);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_MR_EXIT_NAME_WRONG_TYPE", 4072);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_MR_INTERVAL_ERROR", 4073);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_MR_INTERVAL_WRONG_TYPE", 4074);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_NPM_SPEED_ERROR", 4075);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_NPM_SPEED_WRONG_TYPE", 4076);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_HB_INTERVAL_ERROR", 4077);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_HB_INTERVAL_WRONG_TYPE", 4078);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHAD_ERROR", 4079);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHAD_WRONG_TYPE", 4080);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHAD_EVENT_ERROR", 4081);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHAD_EVENT_WRONG_TYPE", 4082);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHAD_EXIT_ERROR", 4083);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHAD_EXIT_WRONG_TYPE", 4084);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SUPPRESSED_BY_EXIT", 4085);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_BATCH_INT_ERROR", 4086);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_BATCH_INT_WRONG_TYPE", 4087);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_NET_PRIORITY_ERROR", 4088);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_NET_PRIORITY_WRONG_TYPE", 4089);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_CHANNEL_CLOSED", 4090);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_Q_STATUS_NOT_FOUND", 4091);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SSL_CIPHER_SPEC_ERROR", 4092);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SSL_PEER_NAME_ERROR", 4093);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_SSL_CLIENT_AUTH_ERROR", 4094);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_RETAINED_NOT_SUPPORTED", 4095);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_KWD_VALUE_WRONG_TYPE", 4096);
            ibmConstantAccessorsMap.put("CMQCFC.MQRCCF_APPL_STATUS_NOT_FOUND", 4097);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFBF_STRUC_LENGTH_FIXED", 20);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFBS_STRUC_LENGTH_FIXED", 16);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFGR_STRUC_LENGTH", 16);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFIF_STRUC_LENGTH", 20);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFIL_STRUC_LENGTH_FIXED", 16);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFIL64_STRUC_LENGTH_FIXED", 16);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFIN_STRUC_LENGTH", 16);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFIN64_STRUC_LENGTH", 24);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFSF_STRUC_LENGTH_FIXED", 24);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFSL_STRUC_LENGTH_FIXED", 24);
            ibmConstantAccessorsMap.put("CMQCFC.MQCFST_STRUC_LENGTH_FIXED", 20);
            ibmConstantAccessorsMap.put("CMQCFC.MQEPH_STRUC_LENGTH_FIXED", 68);
            ibmConstantAccessorsMap.put("CMQCFC.MQEPH_VERSION_1", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQEPH_CURRENT_VERSION", 1);
            ibmConstantAccessorsMap.put("CMQCFC.MQEPH_LENGTH_1", 68);
            ibmConstantAccessorsMap.put("CMQCFC.MQEPH_CURRENT_LENGTH", 68);
            ibmConstantAccessorsMap.put("CMQCFC.MQEPH_NONE", 0);
            ibmConstantAccessorsMap.put("CMQCFC.MQEPH_CCSID_EMBEDDED", 1);
        }
    }

    private static void init_2() {
        if( ibmMQEXTMap == null ) {
            ibmMQEXTMap = new HashMap<>();
            ibmMQEXTMap.put( 0, "MQEXT_ALL");
            ibmMQEXTMap.put( 1, "MQEXT_OBJECT");
            ibmMQEXTMap.put( 2, "MQEXT_AUTHORITY");
        }
        if( ibmMQUOWTMap == null ) {
            ibmMQUOWTMap = new HashMap<>();
            ibmMQUOWTMap.put( 0, "MQUOWT_Q_MGR");
            ibmMQUOWTMap.put( 1, "MQUOWT_CICS");
            ibmMQUOWTMap.put( 2, "MQUOWT_RRS");
            ibmMQUOWTMap.put( 3, "MQUOWT_IMS");
            ibmMQUOWTMap.put( 4, "MQUOWT_XA");
        }
        if( ibmMQCFTYPEMap == null ) {
            ibmMQCFTYPEMap = new HashMap<>();
            ibmMQCFTYPEMap.put( 0, "MQCFTYPE_APPL");
            ibmMQCFTYPEMap.put( 1, "MQCFTYPE_ADMIN");
        }
        if( ibmMQRDNSMap == null ) {
            ibmMQRDNSMap = new HashMap<>();
            ibmMQRDNSMap.put( 0, "MQRDNS_ENABLED");
            ibmMQRDNSMap.put( 1, "MQRDNS_DISABLED");
        }
        if( ibmMQRTMap == null ) {
            ibmMQRTMap = new HashMap<>();
            ibmMQRTMap.put( 1, "MQRT_CONFIGURATION");
            ibmMQRTMap.put( 2, "MQRT_EXPIRY");
            ibmMQRTMap.put( 3, "MQRT_NSPROC");
            ibmMQRTMap.put( 4, "MQRT_PROXYSUB");
            ibmMQRTMap.put( 5, "MQRT_SUB_CONFIGURATION");
        }
        if( ibmMQCLXQMap == null ) {
            ibmMQCLXQMap = new HashMap<>();
            ibmMQCLXQMap.put( 0, "MQCLXQ_SCTQ");
            ibmMQCLXQMap.put( 1, "MQCLXQ_CHANNEL");
        }
        if( ibmMQCHLAMap == null ) {
            ibmMQCHLAMap = new HashMap<>();
            ibmMQCHLAMap.put( 0, "MQCHLA_DISABLED");
            ibmMQCHLAMap.put( 1, "MQCHLA_ENABLED");
        }
        if( ibmMQEXTATTRSMap == null ) {
            ibmMQEXTATTRSMap = new HashMap<>();
            ibmMQEXTATTRSMap.put( 0, "MQEXTATTRS_ALL");
            ibmMQEXTATTRSMap.put( 1, "MQEXTATTRS_NONDEF");
        }
        if( ibmMQSUBTYPEMap == null ) {
            ibmMQSUBTYPEMap = new HashMap<>();
            ibmMQSUBTYPEMap.put( 1, "MQSUBTYPE_API");
            ibmMQSUBTYPEMap.put( 2, "MQSUBTYPE_ADMIN");
            ibmMQSUBTYPEMap.put( 3, "MQSUBTYPE_PROXY");
        }
        if( ibmMQCHKMap == null ) {
            ibmMQCHKMap = new HashMap<>();
            ibmMQCHKMap.put( 0, "MQCHK_OPTIONAL");
            ibmMQCHKMap.put( 1, "MQCHK_NONE");
            ibmMQCHKMap.put( 2, "MQCHK_REQUIRED_ADMIN");
            ibmMQCHKMap.put( 3, "MQCHK_REQUIRED");
            ibmMQCHKMap.put( 4, "MQCHK_AS_Q_MGR");
        }
        if( ibmMQCFSTMap == null ) {
            ibmMQCFSTMap = new HashMap<>();
            ibmMQCFSTMap.put( 20, "MQCFST_STRUC_LENGTH_FIXED");
        }
        if( ibmMQCLRSMap == null ) {
            ibmMQCLRSMap = new HashMap<>();
            ibmMQCLRSMap.put( 1, "MQCLRS_LOCAL");
            ibmMQCLRSMap.put( 2, "MQCLRS_GLOBAL");
        }
        if( ibmMQACTMap == null ) {
            ibmMQACTMap = new HashMap<>();
            ibmMQACTMap.put( 1, "MQACT_FORCE_REMOVE");
            ibmMQACTMap.put( 2, "MQACT_ADVANCE_LOG");
            ibmMQACTMap.put( 3, "MQACT_COLLECT_STATISTICS");
            ibmMQACTMap.put( 4, "MQACT_PUBSUB");
            ibmMQACTMap.put( 5, "MQACT_ADD");
            ibmMQACTMap.put( 6, "MQACT_REPLACE");
            ibmMQACTMap.put( 7, "MQACT_REMOVE");
            ibmMQACTMap.put( 8, "MQACT_REMOVEALL");
            ibmMQACTMap.put( 9, "MQACT_FAIL");
        }
        if( ibmMQRQMap == null ) {
            ibmMQRQMap = new HashMap<>();
            ibmMQRQMap.put( 1, "MQRQ_CONN_NOT_AUTHORIZED");
            ibmMQRQMap.put( 2, "MQRQ_OPEN_NOT_AUTHORIZED");
            ibmMQRQMap.put( 3, "MQRQ_CLOSE_NOT_AUTHORIZED");
            ibmMQRQMap.put( 4, "MQRQ_CMD_NOT_AUTHORIZED");
            ibmMQRQMap.put( 5, "MQRQ_Q_MGR_STOPPING");
            ibmMQRQMap.put( 6, "MQRQ_Q_MGR_QUIESCING");
            ibmMQRQMap.put( 7, "MQRQ_CHANNEL_STOPPED_OK");
            ibmMQRQMap.put( 8, "MQRQ_CHANNEL_STOPPED_ERROR");
            ibmMQRQMap.put( 9, "MQRQ_CHANNEL_STOPPED_RETRY");
            ibmMQRQMap.put( 10, "MQRQ_CHANNEL_STOPPED_DISABLED");
            ibmMQRQMap.put( 11, "MQRQ_BRIDGE_STOPPED_OK");
            ibmMQRQMap.put( 12, "MQRQ_BRIDGE_STOPPED_ERROR");
            ibmMQRQMap.put( 13, "MQRQ_SSL_HANDSHAKE_ERROR");
            ibmMQRQMap.put( 14, "MQRQ_SSL_CIPHER_SPEC_ERROR");
            ibmMQRQMap.put( 15, "MQRQ_SSL_CLIENT_AUTH_ERROR");
            ibmMQRQMap.put( 16, "MQRQ_SSL_PEER_NAME_ERROR");
            ibmMQRQMap.put( 17, "MQRQ_SUB_NOT_AUTHORIZED");
            ibmMQRQMap.put( 18, "MQRQ_SUB_DEST_NOT_AUTHORIZED");
            ibmMQRQMap.put( 19, "MQRQ_SSL_UNKNOWN_REVOCATION");
            ibmMQRQMap.put( 20, "MQRQ_SYS_CONN_NOT_AUTHORIZED");
            ibmMQRQMap.put( 21, "MQRQ_CHANNEL_BLOCKED_ADDRESS");
            ibmMQRQMap.put( 22, "MQRQ_CHANNEL_BLOCKED_USERID");
            ibmMQRQMap.put( 23, "MQRQ_CHANNEL_BLOCKED_NOACCESS");
            ibmMQRQMap.put( 24, "MQRQ_MAX_ACTIVE_CHANNELS");
            ibmMQRQMap.put( 25, "MQRQ_MAX_CHANNELS");
            ibmMQRQMap.put( 26, "MQRQ_SVRCONN_INST_LIMIT");
            ibmMQRQMap.put( 27, "MQRQ_CLIENT_INST_LIMIT");
            ibmMQRQMap.put( 28, "MQRQ_CAF_NOT_INSTALLED");
            ibmMQRQMap.put( 29, "MQRQ_CSP_NOT_AUTHORIZED");
        }
        if( ibmMQSYSPMap == null ) {
            ibmMQSYSPMap = new HashMap<>();
            ibmMQSYSPMap.put( 0, "MQSYSP_NO");
            ibmMQSYSPMap.put( 1, "MQSYSP_YES");
            ibmMQSYSPMap.put( 2, "MQSYSP_EXTENDED");
            ibmMQSYSPMap.put( 10, "MQSYSP_TYPE_INITIAL");
            ibmMQSYSPMap.put( 11, "MQSYSP_TYPE_SET");
            ibmMQSYSPMap.put( 12, "MQSYSP_TYPE_LOG_COPY");
            ibmMQSYSPMap.put( 13, "MQSYSP_TYPE_LOG_STATUS");
            ibmMQSYSPMap.put( 14, "MQSYSP_TYPE_ARCHIVE_TAPE");
            ibmMQSYSPMap.put( 20, "MQSYSP_ALLOC_BLK");
            ibmMQSYSPMap.put( 21, "MQSYSP_ALLOC_TRK");
            ibmMQSYSPMap.put( 22, "MQSYSP_ALLOC_CYL");
            ibmMQSYSPMap.put( 30, "MQSYSP_STATUS_BUSY");
            ibmMQSYSPMap.put( 31, "MQSYSP_STATUS_PREMOUNT");
            ibmMQSYSPMap.put( 32, "MQSYSP_STATUS_AVAILABLE");
            ibmMQSYSPMap.put( 33, "MQSYSP_STATUS_UNKNOWN");
            ibmMQSYSPMap.put( 34, "MQSYSP_STATUS_ALLOC_ARCHIVE");
            ibmMQSYSPMap.put( 35, "MQSYSP_STATUS_COPYING_BSDS");
            ibmMQSYSPMap.put( 36, "MQSYSP_STATUS_COPYING_LOG");
        }
        if( ibmMQQSOMap == null ) {
            ibmMQQSOMap = new HashMap<>();
            ibmMQQSOMap.put( 0, "MQQSO_NO");
            ibmMQQSOMap.put( 1, "MQQSO_YES");
            ibmMQQSOMap.put( 1, "MQQSO_SHARED");
            ibmMQQSOMap.put( 2, "MQQSO_EXCLUSIVE");
        }
        if( ibmMQIAMOMap == null ) {
            ibmMQIAMOMap = new HashMap<>();
            ibmMQIAMOMap.put( 701, "MQIAMO_FIRST");
            ibmMQIAMOMap.put( 702, "MQIAMO_AVG_BATCH_SIZE");
            ibmMQIAMOMap.put( 703, "MQIAMO_AVG_Q_TIME");
            ibmMQIAMOMap.put( 704, "MQIAMO_BACKOUTS");
            ibmMQIAMOMap.put( 705, "MQIAMO_BROWSES");
            ibmMQIAMOMap.put( 706, "MQIAMO_BROWSE_MAX_BYTES");
            ibmMQIAMOMap.put( 707, "MQIAMO_BROWSE_MIN_BYTES");
            ibmMQIAMOMap.put( 708, "MQIAMO_BROWSES_FAILED");
            ibmMQIAMOMap.put( 709, "MQIAMO_CLOSES");
            ibmMQIAMOMap.put( 710, "MQIAMO_COMMITS");
            ibmMQIAMOMap.put( 711, "MQIAMO_COMMITS_FAILED");
            ibmMQIAMOMap.put( 712, "MQIAMO_CONNS");
            ibmMQIAMOMap.put( 713, "MQIAMO_CONNS_MAX");
            ibmMQIAMOMap.put( 714, "MQIAMO_DISCS");
            ibmMQIAMOMap.put( 715, "MQIAMO_DISCS_IMPLICIT");
            ibmMQIAMOMap.put( 716, "MQIAMO_DISC_TYPE");
            ibmMQIAMOMap.put( 717, "MQIAMO_EXIT_TIME_AVG");
            ibmMQIAMOMap.put( 718, "MQIAMO_EXIT_TIME_MAX");
            ibmMQIAMOMap.put( 719, "MQIAMO_EXIT_TIME_MIN");
            ibmMQIAMOMap.put( 720, "MQIAMO_FULL_BATCHES");
            ibmMQIAMOMap.put( 721, "MQIAMO_GENERATED_MSGS");
            ibmMQIAMOMap.put( 722, "MQIAMO_GETS");
            ibmMQIAMOMap.put( 723, "MQIAMO_GET_MAX_BYTES");
            ibmMQIAMOMap.put( 724, "MQIAMO_GET_MIN_BYTES");
            ibmMQIAMOMap.put( 725, "MQIAMO_GETS_FAILED");
            ibmMQIAMOMap.put( 726, "MQIAMO_INCOMPLETE_BATCHES");
            ibmMQIAMOMap.put( 727, "MQIAMO_INQS");
            ibmMQIAMOMap.put( 728, "MQIAMO_MSGS");
            ibmMQIAMOMap.put( 729, "MQIAMO_NET_TIME_AVG");
            ibmMQIAMOMap.put( 730, "MQIAMO_NET_TIME_MAX");
            ibmMQIAMOMap.put( 731, "MQIAMO_NET_TIME_MIN");
            ibmMQIAMOMap.put( 732, "MQIAMO_OBJECT_COUNT");
            ibmMQIAMOMap.put( 733, "MQIAMO_OPENS");
            ibmMQIAMOMap.put( 734, "MQIAMO_PUT1S");
            ibmMQIAMOMap.put( 735, "MQIAMO_PUTS");
            ibmMQIAMOMap.put( 736, "MQIAMO_PUT_MAX_BYTES");
            ibmMQIAMOMap.put( 737, "MQIAMO_PUT_MIN_BYTES");
            ibmMQIAMOMap.put( 738, "MQIAMO_PUT_RETRIES");
            ibmMQIAMOMap.put( 739, "MQIAMO_Q_MAX_DEPTH");
            ibmMQIAMOMap.put( 740, "MQIAMO_Q_MIN_DEPTH");
            ibmMQIAMOMap.put( 741, "MQIAMO_Q_TIME_AVG");
            ibmMQIAMOMap.put( 742, "MQIAMO_Q_TIME_MAX");
            ibmMQIAMOMap.put( 743, "MQIAMO_Q_TIME_MIN");
            ibmMQIAMOMap.put( 744, "MQIAMO_SETS");
            ibmMQIAMOMap.put( 749, "MQIAMO_CONNS_FAILED");
            ibmMQIAMOMap.put( 751, "MQIAMO_OPENS_FAILED");
            ibmMQIAMOMap.put( 752, "MQIAMO_INQS_FAILED");
            ibmMQIAMOMap.put( 753, "MQIAMO_SETS_FAILED");
            ibmMQIAMOMap.put( 754, "MQIAMO_PUTS_FAILED");
            ibmMQIAMOMap.put( 755, "MQIAMO_PUT1S_FAILED");
            ibmMQIAMOMap.put( 757, "MQIAMO_CLOSES_FAILED");
            ibmMQIAMOMap.put( 758, "MQIAMO_MSGS_EXPIRED");
            ibmMQIAMOMap.put( 759, "MQIAMO_MSGS_NOT_QUEUED");
            ibmMQIAMOMap.put( 760, "MQIAMO_MSGS_PURGED");
            ibmMQIAMOMap.put( 764, "MQIAMO_SUBS_DUR");
            ibmMQIAMOMap.put( 765, "MQIAMO_SUBS_NDUR");
            ibmMQIAMOMap.put( 766, "MQIAMO_SUBS_FAILED");
            ibmMQIAMOMap.put( 767, "MQIAMO_SUBRQS");
            ibmMQIAMOMap.put( 768, "MQIAMO_SUBRQS_FAILED");
            ibmMQIAMOMap.put( 769, "MQIAMO_CBS");
            ibmMQIAMOMap.put( 770, "MQIAMO_CBS_FAILED");
            ibmMQIAMOMap.put( 771, "MQIAMO_CTLS");
            ibmMQIAMOMap.put( 772, "MQIAMO_CTLS_FAILED");
            ibmMQIAMOMap.put( 773, "MQIAMO_STATS");
            ibmMQIAMOMap.put( 774, "MQIAMO_STATS_FAILED");
            ibmMQIAMOMap.put( 775, "MQIAMO_SUB_DUR_HIGHWATER");
            ibmMQIAMOMap.put( 776, "MQIAMO_SUB_DUR_LOWWATER");
            ibmMQIAMOMap.put( 777, "MQIAMO_SUB_NDUR_HIGHWATER");
            ibmMQIAMOMap.put( 778, "MQIAMO_SUB_NDUR_LOWWATER");
            ibmMQIAMOMap.put( 779, "MQIAMO_TOPIC_PUTS");
            ibmMQIAMOMap.put( 780, "MQIAMO_TOPIC_PUTS_FAILED");
            ibmMQIAMOMap.put( 781, "MQIAMO_TOPIC_PUT1S");
            ibmMQIAMOMap.put( 782, "MQIAMO_TOPIC_PUT1S_FAILED");
            ibmMQIAMOMap.put( 784, "MQIAMO_PUBLISH_MSG_COUNT");
            ibmMQIAMOMap.put( 786, "MQIAMO_UNSUBS_DUR");
            ibmMQIAMOMap.put( 787, "MQIAMO_UNSUBS_NDUR");
            ibmMQIAMOMap.put( 788, "MQIAMO_UNSUBS_FAILED");
            ibmMQIAMOMap.put( 789, "MQIAMO_INTERVAL");
            ibmMQIAMOMap.put( 790, "MQIAMO_MSGS_SENT");
            ibmMQIAMOMap.put( 791, "MQIAMO_BYTES_SENT");
            ibmMQIAMOMap.put( 792, "MQIAMO_REPAIR_BYTES");
            ibmMQIAMOMap.put( 793, "MQIAMO_FEEDBACK_MODE");
            ibmMQIAMOMap.put( 794, "MQIAMO_RELIABILITY_TYPE");
            ibmMQIAMOMap.put( 795, "MQIAMO_LATE_JOIN_MARK");
            ibmMQIAMOMap.put( 796, "MQIAMO_NACKS_RCVD");
            ibmMQIAMOMap.put( 797, "MQIAMO_REPAIR_PKTS");
            ibmMQIAMOMap.put( 798, "MQIAMO_HISTORY_PKTS");
            ibmMQIAMOMap.put( 799, "MQIAMO_PENDING_PKTS");
            ibmMQIAMOMap.put( 800, "MQIAMO_PKT_RATE");
            ibmMQIAMOMap.put( 801, "MQIAMO_MCAST_XMIT_RATE");
            ibmMQIAMOMap.put( 802, "MQIAMO_MCAST_BATCH_TIME");
            ibmMQIAMOMap.put( 803, "MQIAMO_MCAST_HEARTBEAT");
            ibmMQIAMOMap.put( 804, "MQIAMO_DEST_DATA_PORT");
            ibmMQIAMOMap.put( 805, "MQIAMO_DEST_REPAIR_PORT");
            ibmMQIAMOMap.put( 806, "MQIAMO_ACKS_RCVD");
            ibmMQIAMOMap.put( 807, "MQIAMO_ACTIVE_ACKERS");
            ibmMQIAMOMap.put( 808, "MQIAMO_PKTS_SENT");
            ibmMQIAMOMap.put( 809, "MQIAMO_TOTAL_REPAIR_PKTS");
            ibmMQIAMOMap.put( 810, "MQIAMO_TOTAL_PKTS_SENT");
            ibmMQIAMOMap.put( 811, "MQIAMO_TOTAL_MSGS_SENT");
            ibmMQIAMOMap.put( 812, "MQIAMO_TOTAL_BYTES_SENT");
            ibmMQIAMOMap.put( 813, "MQIAMO_NUM_STREAMS");
            ibmMQIAMOMap.put( 814, "MQIAMO_ACK_FEEDBACK");
            ibmMQIAMOMap.put( 815, "MQIAMO_NACK_FEEDBACK");
            ibmMQIAMOMap.put( 816, "MQIAMO_PKTS_LOST");
            ibmMQIAMOMap.put( 817, "MQIAMO_MSGS_RCVD");
            ibmMQIAMOMap.put( 818, "MQIAMO_MSG_BYTES_RCVD");
            ibmMQIAMOMap.put( 819, "MQIAMO_MSGS_DELIVERED");
            ibmMQIAMOMap.put( 820, "MQIAMO_PKTS_PROCESSED");
            ibmMQIAMOMap.put( 821, "MQIAMO_PKTS_DELIVERED");
            ibmMQIAMOMap.put( 822, "MQIAMO_PKTS_DROPPED");
            ibmMQIAMOMap.put( 823, "MQIAMO_PKTS_DUPLICATED");
            ibmMQIAMOMap.put( 824, "MQIAMO_NACKS_CREATED");
            ibmMQIAMOMap.put( 825, "MQIAMO_NACK_PKTS_SENT");
            ibmMQIAMOMap.put( 826, "MQIAMO_REPAIR_PKTS_RQSTD");
            ibmMQIAMOMap.put( 827, "MQIAMO_REPAIR_PKTS_RCVD");
            ibmMQIAMOMap.put( 828, "MQIAMO_PKTS_REPAIRED");
            ibmMQIAMOMap.put( 829, "MQIAMO_TOTAL_MSGS_RCVD");
            ibmMQIAMOMap.put( 830, "MQIAMO_TOTAL_MSG_BYTES_RCVD");
            ibmMQIAMOMap.put( 831, "MQIAMO_TOTAL_REPAIR_PKTS_RCVD");
            ibmMQIAMOMap.put( 832, "MQIAMO_TOTAL_REPAIR_PKTS_RQSTD");
            ibmMQIAMOMap.put( 833, "MQIAMO_TOTAL_MSGS_PROCESSED");
            ibmMQIAMOMap.put( 834, "MQIAMO_TOTAL_MSGS_SELECTED");
            ibmMQIAMOMap.put( 835, "MQIAMO_TOTAL_MSGS_EXPIRED");
            ibmMQIAMOMap.put( 836, "MQIAMO_TOTAL_MSGS_DELIVERED");
            ibmMQIAMOMap.put( 837, "MQIAMO_TOTAL_MSGS_RETURNED");
            ibmMQIAMOMap.put( 837, "MQIAMO_LAST_USED");
        }
        if( ibmMQSECITEMMap == null ) {
            ibmMQSECITEMMap = new HashMap<>();
            ibmMQSECITEMMap.put( 0, "MQSECITEM_ALL");
            ibmMQSECITEMMap.put( 1, "MQSECITEM_MQADMIN");
            ibmMQSECITEMMap.put( 2, "MQSECITEM_MQNLIST");
            ibmMQSECITEMMap.put( 3, "MQSECITEM_MQPROC");
            ibmMQSECITEMMap.put( 4, "MQSECITEM_MQQUEUE");
            ibmMQSECITEMMap.put( 5, "MQSECITEM_MQCONN");
            ibmMQSECITEMMap.put( 6, "MQSECITEM_MQCMDS");
            ibmMQSECITEMMap.put( 7, "MQSECITEM_MXADMIN");
            ibmMQSECITEMMap.put( 8, "MQSECITEM_MXNLIST");
            ibmMQSECITEMMap.put( 9, "MQSECITEM_MXPROC");
            ibmMQSECITEMMap.put( 10, "MQSECITEM_MXQUEUE");
            ibmMQSECITEMMap.put( 11, "MQSECITEM_MXTOPIC");
        }
        if( ibmMQCHRRMap == null ) {
            ibmMQCHRRMap = new HashMap<>();
            ibmMQCHRRMap.put( 0, "MQCHRR_RESET_NOT_REQUESTED");
        }
        if( ibmMQCHLDMap == null ) {
            ibmMQCHLDMap = new HashMap<>();
            ibmMQCHLDMap.put( 1, "MQCHLD_DEFAULT");
            ibmMQCHLDMap.put( 2, "MQCHLD_SHARED");
            ibmMQCHLDMap.put( 4, "MQCHLD_PRIVATE");
            ibmMQCHLDMap.put( 5, "MQCHLD_FIXSHARED");
        }
        if( ibmMQCHIDSMap == null ) {
            ibmMQCHIDSMap = new HashMap<>();
            ibmMQCHIDSMap.put( 0, "MQCHIDS_NOT_INDOUBT");
            ibmMQCHIDSMap.put( 1, "MQCHIDS_INDOUBT");
        }
        if( ibmMQCFHMap == null ) {
            ibmMQCFHMap = new HashMap<>();
            ibmMQCFHMap.put( 36, "MQCFH_STRUC_LENGTH");
            ibmMQCFHMap.put( 1, "MQCFH_VERSION_1");
            ibmMQCFHMap.put( 2, "MQCFH_VERSION_2");
            ibmMQCFHMap.put( 3, "MQCFH_VERSION_3");
            ibmMQCFHMap.put( 3, "MQCFH_CURRENT_VERSION");
        }
        if( ibmMQCFBFMap == null ) {
            ibmMQCFBFMap = new HashMap<>();
            ibmMQCFBFMap.put( 20, "MQCFBF_STRUC_LENGTH_FIXED");
        }
        if( ibmMQSUSMap == null ) {
            ibmMQSUSMap = new HashMap<>();
            ibmMQSUSMap.put( 1, "MQSUS_YES");
            ibmMQSUSMap.put( 0, "MQSUS_NO");
        }
        if( ibmMQPOMap == null ) {
            ibmMQPOMap = new HashMap<>();
            ibmMQPOMap.put( 1, "MQPO_YES");
            ibmMQPOMap.put( 0, "MQPO_NO");
        }
        if( ibmMQCHSHMap == null ) {
            ibmMQCHSHMap = new HashMap<>();
            ibmMQCHSHMap.put( 0, "MQCHSH_RESTART_NO");
            ibmMQCHSHMap.put( 1, "MQCHSH_RESTART_YES");
        }
        if( ibmMQPSSTMap == null ) {
            ibmMQPSSTMap = new HashMap<>();
            ibmMQPSSTMap.put( 0, "MQPSST_ALL");
            ibmMQPSSTMap.put( 1, "MQPSST_LOCAL");
            ibmMQPSSTMap.put( 2, "MQPSST_PARENT");
            ibmMQPSSTMap.put( 3, "MQPSST_CHILD");
        }
        if( ibmMQCLSTMap == null ) {
            ibmMQCLSTMap = new HashMap<>();
            ibmMQCLSTMap.put( 0, "MQCLST_ACTIVE");
            ibmMQCLSTMap.put( 1, "MQCLST_PENDING");
            ibmMQCLSTMap.put( 2, "MQCLST_INVALID");
            ibmMQCLSTMap.put( 3, "MQCLST_ERROR");
        }
        if( ibmMQCACFMap == null ) {
            ibmMQCACFMap = new HashMap<>();
            ibmMQCACFMap.put( 3001, "MQCACF_FIRST");
            ibmMQCACFMap.put( 3001, "MQCACF_FROM_Q_NAME");
            ibmMQCACFMap.put( 3002, "MQCACF_TO_Q_NAME");
            ibmMQCACFMap.put( 3003, "MQCACF_FROM_PROCESS_NAME");
            ibmMQCACFMap.put( 3004, "MQCACF_TO_PROCESS_NAME");
            ibmMQCACFMap.put( 3005, "MQCACF_FROM_NAMELIST_NAME");
            ibmMQCACFMap.put( 3006, "MQCACF_TO_NAMELIST_NAME");
            ibmMQCACFMap.put( 3007, "MQCACF_FROM_CHANNEL_NAME");
            ibmMQCACFMap.put( 3008, "MQCACF_TO_CHANNEL_NAME");
            ibmMQCACFMap.put( 3009, "MQCACF_FROM_AUTH_INFO_NAME");
            ibmMQCACFMap.put( 3010, "MQCACF_TO_AUTH_INFO_NAME");
            ibmMQCACFMap.put( 3011, "MQCACF_Q_NAMES");
            ibmMQCACFMap.put( 3012, "MQCACF_PROCESS_NAMES");
            ibmMQCACFMap.put( 3013, "MQCACF_NAMELIST_NAMES");
            ibmMQCACFMap.put( 3014, "MQCACF_ESCAPE_TEXT");
            ibmMQCACFMap.put( 3015, "MQCACF_LOCAL_Q_NAMES");
            ibmMQCACFMap.put( 3016, "MQCACF_MODEL_Q_NAMES");
            ibmMQCACFMap.put( 3017, "MQCACF_ALIAS_Q_NAMES");
            ibmMQCACFMap.put( 3018, "MQCACF_REMOTE_Q_NAMES");
            ibmMQCACFMap.put( 3019, "MQCACF_SENDER_CHANNEL_NAMES");
            ibmMQCACFMap.put( 3020, "MQCACF_SERVER_CHANNEL_NAMES");
            ibmMQCACFMap.put( 3021, "MQCACF_REQUESTER_CHANNEL_NAMES");
            ibmMQCACFMap.put( 3022, "MQCACF_RECEIVER_CHANNEL_NAMES");
            ibmMQCACFMap.put( 3023, "MQCACF_OBJECT_Q_MGR_NAME");
            ibmMQCACFMap.put( 3024, "MQCACF_APPL_NAME");
            ibmMQCACFMap.put( 3025, "MQCACF_USER_IDENTIFIER");
            ibmMQCACFMap.put( 3026, "MQCACF_AUX_ERROR_DATA_STR_1");
            ibmMQCACFMap.put( 3027, "MQCACF_AUX_ERROR_DATA_STR_2");
            ibmMQCACFMap.put( 3028, "MQCACF_AUX_ERROR_DATA_STR_3");
            ibmMQCACFMap.put( 3029, "MQCACF_BRIDGE_NAME");
            ibmMQCACFMap.put( 3030, "MQCACF_STREAM_NAME");
            ibmMQCACFMap.put( 3031, "MQCACF_TOPIC");
            ibmMQCACFMap.put( 3032, "MQCACF_PARENT_Q_MGR_NAME");
            ibmMQCACFMap.put( 3033, "MQCACF_CORREL_ID");
            ibmMQCACFMap.put( 3034, "MQCACF_PUBLISH_TIMESTAMP");
            ibmMQCACFMap.put( 3035, "MQCACF_STRING_DATA");
            ibmMQCACFMap.put( 3036, "MQCACF_SUPPORTED_STREAM_NAME");
            ibmMQCACFMap.put( 3037, "MQCACF_REG_TOPIC");
            ibmMQCACFMap.put( 3038, "MQCACF_REG_TIME");
            ibmMQCACFMap.put( 3039, "MQCACF_REG_USER_ID");
            ibmMQCACFMap.put( 3040, "MQCACF_CHILD_Q_MGR_NAME");
            ibmMQCACFMap.put( 3041, "MQCACF_REG_STREAM_NAME");
            ibmMQCACFMap.put( 3042, "MQCACF_REG_Q_MGR_NAME");
            ibmMQCACFMap.put( 3043, "MQCACF_REG_Q_NAME");
            ibmMQCACFMap.put( 3044, "MQCACF_REG_CORREL_ID");
            ibmMQCACFMap.put( 3045, "MQCACF_EVENT_USER_ID");
            ibmMQCACFMap.put( 3046, "MQCACF_OBJECT_NAME");
            ibmMQCACFMap.put( 3047, "MQCACF_EVENT_Q_MGR");
            ibmMQCACFMap.put( 3048, "MQCACF_AUTH_INFO_NAMES");
            ibmMQCACFMap.put( 3049, "MQCACF_EVENT_APPL_IDENTITY");
            ibmMQCACFMap.put( 3050, "MQCACF_EVENT_APPL_NAME");
            ibmMQCACFMap.put( 3051, "MQCACF_EVENT_APPL_ORIGIN");
            ibmMQCACFMap.put( 3052, "MQCACF_SUBSCRIPTION_NAME");
            ibmMQCACFMap.put( 3053, "MQCACF_REG_SUB_NAME");
            ibmMQCACFMap.put( 3054, "MQCACF_SUBSCRIPTION_IDENTITY");
            ibmMQCACFMap.put( 3055, "MQCACF_REG_SUB_IDENTITY");
            ibmMQCACFMap.put( 3056, "MQCACF_SUBSCRIPTION_USER_DATA");
            ibmMQCACFMap.put( 3057, "MQCACF_REG_SUB_USER_DATA");
            ibmMQCACFMap.put( 3058, "MQCACF_APPL_TAG");
            ibmMQCACFMap.put( 3059, "MQCACF_DATA_SET_NAME");
            ibmMQCACFMap.put( 3060, "MQCACF_UOW_START_DATE");
            ibmMQCACFMap.put( 3061, "MQCACF_UOW_START_TIME");
            ibmMQCACFMap.put( 3062, "MQCACF_UOW_LOG_START_DATE");
            ibmMQCACFMap.put( 3063, "MQCACF_UOW_LOG_START_TIME");
            ibmMQCACFMap.put( 3064, "MQCACF_UOW_LOG_EXTENT_NAME");
            ibmMQCACFMap.put( 3065, "MQCACF_PRINCIPAL_ENTITY_NAMES");
            ibmMQCACFMap.put( 3066, "MQCACF_GROUP_ENTITY_NAMES");
            ibmMQCACFMap.put( 3067, "MQCACF_AUTH_PROFILE_NAME");
            ibmMQCACFMap.put( 3068, "MQCACF_ENTITY_NAME");
            ibmMQCACFMap.put( 3069, "MQCACF_SERVICE_COMPONENT");
            ibmMQCACFMap.put( 3070, "MQCACF_RESPONSE_Q_MGR_NAME");
            ibmMQCACFMap.put( 3071, "MQCACF_CURRENT_LOG_EXTENT_NAME");
            ibmMQCACFMap.put( 3072, "MQCACF_RESTART_LOG_EXTENT_NAME");
            ibmMQCACFMap.put( 3073, "MQCACF_MEDIA_LOG_EXTENT_NAME");
            ibmMQCACFMap.put( 3074, "MQCACF_LOG_PATH");
            ibmMQCACFMap.put( 3075, "MQCACF_COMMAND_MQSC");
            ibmMQCACFMap.put( 3076, "MQCACF_Q_MGR_CPF");
            ibmMQCACFMap.put( 3078, "MQCACF_USAGE_LOG_RBA");
            ibmMQCACFMap.put( 3079, "MQCACF_USAGE_LOG_LRSN");
            ibmMQCACFMap.put( 3080, "MQCACF_COMMAND_SCOPE");
            ibmMQCACFMap.put( 3081, "MQCACF_ASID");
            ibmMQCACFMap.put( 3082, "MQCACF_PSB_NAME");
            ibmMQCACFMap.put( 3083, "MQCACF_PST_ID");
            ibmMQCACFMap.put( 3084, "MQCACF_TASK_NUMBER");
            ibmMQCACFMap.put( 3085, "MQCACF_TRANSACTION_ID");
            ibmMQCACFMap.put( 3086, "MQCACF_Q_MGR_UOW_ID");
            ibmMQCACFMap.put( 3088, "MQCACF_ORIGIN_NAME");
            ibmMQCACFMap.put( 3089, "MQCACF_ENV_INFO");
            ibmMQCACFMap.put( 3090, "MQCACF_SECURITY_PROFILE");
            ibmMQCACFMap.put( 3091, "MQCACF_CONFIGURATION_DATE");
            ibmMQCACFMap.put( 3092, "MQCACF_CONFIGURATION_TIME");
            ibmMQCACFMap.put( 3093, "MQCACF_FROM_CF_STRUC_NAME");
            ibmMQCACFMap.put( 3094, "MQCACF_TO_CF_STRUC_NAME");
            ibmMQCACFMap.put( 3095, "MQCACF_CF_STRUC_NAMES");
            ibmMQCACFMap.put( 3096, "MQCACF_FAIL_DATE");
            ibmMQCACFMap.put( 3097, "MQCACF_FAIL_TIME");
            ibmMQCACFMap.put( 3098, "MQCACF_BACKUP_DATE");
            ibmMQCACFMap.put( 3099, "MQCACF_BACKUP_TIME");
            ibmMQCACFMap.put( 3100, "MQCACF_SYSTEM_NAME");
            ibmMQCACFMap.put( 3101, "MQCACF_CF_STRUC_BACKUP_START");
            ibmMQCACFMap.put( 3102, "MQCACF_CF_STRUC_BACKUP_END");
            ibmMQCACFMap.put( 3103, "MQCACF_CF_STRUC_LOG_Q_MGRS");
            ibmMQCACFMap.put( 3104, "MQCACF_FROM_STORAGE_CLASS");
            ibmMQCACFMap.put( 3105, "MQCACF_TO_STORAGE_CLASS");
            ibmMQCACFMap.put( 3106, "MQCACF_STORAGE_CLASS_NAMES");
            ibmMQCACFMap.put( 3108, "MQCACF_DSG_NAME");
            ibmMQCACFMap.put( 3109, "MQCACF_DB2_NAME");
            ibmMQCACFMap.put( 3110, "MQCACF_SYSP_CMD_USER_ID");
            ibmMQCACFMap.put( 3111, "MQCACF_SYSP_OTMA_GROUP");
            ibmMQCACFMap.put( 3112, "MQCACF_SYSP_OTMA_MEMBER");
            ibmMQCACFMap.put( 3113, "MQCACF_SYSP_OTMA_DRU_EXIT");
            ibmMQCACFMap.put( 3114, "MQCACF_SYSP_OTMA_TPIPE_PFX");
            ibmMQCACFMap.put( 3115, "MQCACF_SYSP_ARCHIVE_PFX1");
            ibmMQCACFMap.put( 3116, "MQCACF_SYSP_ARCHIVE_UNIT1");
            ibmMQCACFMap.put( 3117, "MQCACF_SYSP_LOG_CORREL_ID");
            ibmMQCACFMap.put( 3118, "MQCACF_SYSP_UNIT_VOLSER");
            ibmMQCACFMap.put( 3119, "MQCACF_SYSP_Q_MGR_TIME");
            ibmMQCACFMap.put( 3120, "MQCACF_SYSP_Q_MGR_DATE");
            ibmMQCACFMap.put( 3121, "MQCACF_SYSP_Q_MGR_RBA");
            ibmMQCACFMap.put( 3122, "MQCACF_SYSP_LOG_RBA");
            ibmMQCACFMap.put( 3123, "MQCACF_SYSP_SERVICE");
            ibmMQCACFMap.put( 3124, "MQCACF_FROM_LISTENER_NAME");
            ibmMQCACFMap.put( 3125, "MQCACF_TO_LISTENER_NAME");
            ibmMQCACFMap.put( 3126, "MQCACF_FROM_SERVICE_NAME");
            ibmMQCACFMap.put( 3127, "MQCACF_TO_SERVICE_NAME");
            ibmMQCACFMap.put( 3128, "MQCACF_LAST_PUT_DATE");
            ibmMQCACFMap.put( 3129, "MQCACF_LAST_PUT_TIME");
            ibmMQCACFMap.put( 3130, "MQCACF_LAST_GET_DATE");
            ibmMQCACFMap.put( 3131, "MQCACF_LAST_GET_TIME");
            ibmMQCACFMap.put( 3132, "MQCACF_OPERATION_DATE");
            ibmMQCACFMap.put( 3133, "MQCACF_OPERATION_TIME");
            ibmMQCACFMap.put( 3134, "MQCACF_ACTIVITY_DESC");
            ibmMQCACFMap.put( 3135, "MQCACF_APPL_IDENTITY_DATA");
            ibmMQCACFMap.put( 3136, "MQCACF_APPL_ORIGIN_DATA");
            ibmMQCACFMap.put( 3137, "MQCACF_PUT_DATE");
            ibmMQCACFMap.put( 3138, "MQCACF_PUT_TIME");
            ibmMQCACFMap.put( 3139, "MQCACF_REPLY_TO_Q");
            ibmMQCACFMap.put( 3140, "MQCACF_REPLY_TO_Q_MGR");
            ibmMQCACFMap.put( 3141, "MQCACF_RESOLVED_Q_NAME");
            ibmMQCACFMap.put( 3142, "MQCACF_STRUC_ID");
            ibmMQCACFMap.put( 3143, "MQCACF_VALUE_NAME");
            ibmMQCACFMap.put( 3144, "MQCACF_SERVICE_START_DATE");
            ibmMQCACFMap.put( 3145, "MQCACF_SERVICE_START_TIME");
            ibmMQCACFMap.put( 3146, "MQCACF_SYSP_OFFLINE_RBA");
            ibmMQCACFMap.put( 3147, "MQCACF_SYSP_ARCHIVE_PFX2");
            ibmMQCACFMap.put( 3148, "MQCACF_SYSP_ARCHIVE_UNIT2");
            ibmMQCACFMap.put( 3149, "MQCACF_TO_TOPIC_NAME");
            ibmMQCACFMap.put( 3150, "MQCACF_FROM_TOPIC_NAME");
            ibmMQCACFMap.put( 3151, "MQCACF_TOPIC_NAMES");
            ibmMQCACFMap.put( 3152, "MQCACF_SUB_NAME");
            ibmMQCACFMap.put( 3153, "MQCACF_DESTINATION_Q_MGR");
            ibmMQCACFMap.put( 3154, "MQCACF_DESTINATION");
            ibmMQCACFMap.put( 3156, "MQCACF_SUB_USER_ID");
            ibmMQCACFMap.put( 3159, "MQCACF_SUB_USER_DATA");
            ibmMQCACFMap.put( 3160, "MQCACF_SUB_SELECTOR");
            ibmMQCACFMap.put( 3161, "MQCACF_LAST_PUB_DATE");
            ibmMQCACFMap.put( 3162, "MQCACF_LAST_PUB_TIME");
            ibmMQCACFMap.put( 3163, "MQCACF_FROM_SUB_NAME");
            ibmMQCACFMap.put( 3164, "MQCACF_TO_SUB_NAME");
            ibmMQCACFMap.put( 3167, "MQCACF_LAST_MSG_TIME");
            ibmMQCACFMap.put( 3168, "MQCACF_LAST_MSG_DATE");
            ibmMQCACFMap.put( 3169, "MQCACF_SUBSCRIPTION_POINT");
            ibmMQCACFMap.put( 3170, "MQCACF_FILTER");
            ibmMQCACFMap.put( 3171, "MQCACF_NONE");
            ibmMQCACFMap.put( 3172, "MQCACF_ADMIN_TOPIC_NAMES");
            ibmMQCACFMap.put( 3173, "MQCACF_ROUTING_FINGER_PRINT");
            ibmMQCACFMap.put( 3174, "MQCACF_APPL_DESC");
            ibmMQCACFMap.put( 3175, "MQCACF_Q_MGR_START_DATE");
            ibmMQCACFMap.put( 3176, "MQCACF_Q_MGR_START_TIME");
            ibmMQCACFMap.put( 3177, "MQCACF_FROM_COMM_INFO_NAME");
            ibmMQCACFMap.put( 3178, "MQCACF_TO_COMM_INFO_NAME");
            ibmMQCACFMap.put( 3179, "MQCACF_CF_OFFLOAD_SIZE1");
            ibmMQCACFMap.put( 3180, "MQCACF_CF_OFFLOAD_SIZE2");
            ibmMQCACFMap.put( 3181, "MQCACF_CF_OFFLOAD_SIZE3");
            ibmMQCACFMap.put( 3182, "MQCACF_CF_SMDS_GENERIC_NAME");
            ibmMQCACFMap.put( 3183, "MQCACF_CF_SMDS");
            ibmMQCACFMap.put( 3184, "MQCACF_RECOVERY_DATE");
            ibmMQCACFMap.put( 3185, "MQCACF_RECOVERY_TIME");
            ibmMQCACFMap.put( 3186, "MQCACF_CF_SMDSCONN");
            ibmMQCACFMap.put( 3187, "MQCACF_CF_STRUC_NAME");
            ibmMQCACFMap.put( 3188, "MQCACF_ALTERNATE_USERID");
            ibmMQCACFMap.put( 3189, "MQCACF_CHAR_ATTRS");
            ibmMQCACFMap.put( 3190, "MQCACF_DYNAMIC_Q_NAME");
            ibmMQCACFMap.put( 3191, "MQCACF_HOST_NAME");
            ibmMQCACFMap.put( 3192, "MQCACF_MQCB_NAME");
            ibmMQCACFMap.put( 3193, "MQCACF_OBJECT_STRING");
            ibmMQCACFMap.put( 3194, "MQCACF_RESOLVED_LOCAL_Q_MGR");
            ibmMQCACFMap.put( 3195, "MQCACF_RESOLVED_LOCAL_Q_NAME");
            ibmMQCACFMap.put( 3196, "MQCACF_RESOLVED_OBJECT_STRING");
            ibmMQCACFMap.put( 3197, "MQCACF_RESOLVED_Q_MGR");
            ibmMQCACFMap.put( 3198, "MQCACF_SELECTION_STRING");
            ibmMQCACFMap.put( 3199, "MQCACF_XA_INFO");
            ibmMQCACFMap.put( 3200, "MQCACF_APPL_FUNCTION");
            ibmMQCACFMap.put( 3201, "MQCACF_XQH_REMOTE_Q_NAME");
            ibmMQCACFMap.put( 3202, "MQCACF_XQH_REMOTE_Q_MGR");
            ibmMQCACFMap.put( 3203, "MQCACF_XQH_PUT_TIME");
            ibmMQCACFMap.put( 3204, "MQCACF_XQH_PUT_DATE");
            ibmMQCACFMap.put( 3205, "MQCACF_EXCL_OPERATOR_MESSAGES");
            ibmMQCACFMap.put( 3206, "MQCACF_CSP_USER_IDENTIFIER");
            ibmMQCACFMap.put( 3206, "MQCACF_LAST_USED");
        }
        if( ibmMQSECCOMMMap == null ) {
            ibmMQSECCOMMMap = new HashMap<>();
            ibmMQSECCOMMMap.put( 0, "MQSECCOMM_NO");
            ibmMQSECCOMMMap.put( 1, "MQSECCOMM_YES");
            ibmMQSECCOMMMap.put( 2, "MQSECCOMM_ANON");
        }
        if( ibmMQQSOTMap == null ) {
            ibmMQQSOTMap = new HashMap<>();
            ibmMQQSOTMap.put( 1, "MQQSOT_ALL");
            ibmMQQSOTMap.put( 2, "MQQSOT_INPUT");
            ibmMQQSOTMap.put( 3, "MQQSOT_OUTPUT");
        }
        if( ibmMQCHSSTATEMap == null ) {
            ibmMQCHSSTATEMap = new HashMap<>();
            ibmMQCHSSTATEMap.put( 0, "MQCHSSTATE_OTHER");
            ibmMQCHSSTATEMap.put( 100, "MQCHSSTATE_END_OF_BATCH");
            ibmMQCHSSTATEMap.put( 200, "MQCHSSTATE_SENDING");
            ibmMQCHSSTATEMap.put( 300, "MQCHSSTATE_RECEIVING");
            ibmMQCHSSTATEMap.put( 400, "MQCHSSTATE_SERIALIZING");
            ibmMQCHSSTATEMap.put( 500, "MQCHSSTATE_RESYNCHING");
            ibmMQCHSSTATEMap.put( 600, "MQCHSSTATE_HEARTBEATING");
            ibmMQCHSSTATEMap.put( 700, "MQCHSSTATE_IN_SCYEXIT");
            ibmMQCHSSTATEMap.put( 800, "MQCHSSTATE_IN_RCVEXIT");
            ibmMQCHSSTATEMap.put( 900, "MQCHSSTATE_IN_SENDEXIT");
            ibmMQCHSSTATEMap.put( 1000, "MQCHSSTATE_IN_MSGEXIT");
            ibmMQCHSSTATEMap.put( 1100, "MQCHSSTATE_IN_MREXIT");
            ibmMQCHSSTATEMap.put( 1200, "MQCHSSTATE_IN_CHADEXIT");
            ibmMQCHSSTATEMap.put( 1250, "MQCHSSTATE_NET_CONNECTING");
            ibmMQCHSSTATEMap.put( 1300, "MQCHSSTATE_SSL_HANDSHAKING");
            ibmMQCHSSTATEMap.put( 1400, "MQCHSSTATE_NAME_SERVER");
            ibmMQCHSSTATEMap.put( 1500, "MQCHSSTATE_IN_MQPUT");
            ibmMQCHSSTATEMap.put( 1600, "MQCHSSTATE_IN_MQGET");
            ibmMQCHSSTATEMap.put( 1700, "MQCHSSTATE_IN_MQI_CALL");
            ibmMQCHSSTATEMap.put( 1800, "MQCHSSTATE_COMPRESSING");
        }
        if( ibmMQHSTATEMap == null ) {
            ibmMQHSTATEMap = new HashMap<>();
            ibmMQHSTATEMap.put( 0, "MQHSTATE_INACTIVE");
            ibmMQHSTATEMap.put( 1, "MQHSTATE_ACTIVE");
        }
        if( ibmMQPSMap == null ) {
            ibmMQPSMap = new HashMap<>();
            ibmMQPSMap.put( 0, "MQPS_STATUS_INACTIVE");
            ibmMQPSMap.put( 1, "MQPS_STATUS_STARTING");
            ibmMQPSMap.put( 2, "MQPS_STATUS_STOPPING");
            ibmMQPSMap.put( 3, "MQPS_STATUS_ACTIVE");
            ibmMQPSMap.put( 4, "MQPS_STATUS_COMPAT");
            ibmMQPSMap.put( 5, "MQPS_STATUS_ERROR");
            ibmMQPSMap.put( 6, "MQPS_STATUS_REFUSED");
        }
        if( ibmMQQMFACMap == null ) {
            ibmMQQMFACMap = new HashMap<>();
            ibmMQQMFACMap.put( 1, "MQQMFAC_IMS_BRIDGE");
            ibmMQQMFACMap.put( 2, "MQQMFAC_DB2");
        }
        if( ibmMQSMap == null ) {
            ibmMQSMap = new HashMap<>();
            ibmMQSMap.put( 0, "MQS_OPENMODE_NONE");
            ibmMQSMap.put( 1, "MQS_OPENMODE_READONLY");
            ibmMQSMap.put( 2, "MQS_OPENMODE_UPDATE");
            ibmMQSMap.put( 3, "MQS_OPENMODE_RECOVERY");
            ibmMQSMap.put( 0, "MQS_STATUS_CLOSED");
            ibmMQSMap.put( 1, "MQS_STATUS_CLOSING");
            ibmMQSMap.put( 2, "MQS_STATUS_OPENING");
            ibmMQSMap.put( 3, "MQS_STATUS_OPEN");
            ibmMQSMap.put( 4, "MQS_STATUS_NOTENABLED");
            ibmMQSMap.put( 5, "MQS_STATUS_ALLOCFAIL");
            ibmMQSMap.put( 6, "MQS_STATUS_OPENFAIL");
            ibmMQSMap.put( 7, "MQS_STATUS_STGFAIL");
            ibmMQSMap.put( 8, "MQS_STATUS_DATAFAIL");
            ibmMQSMap.put( 0, "MQS_AVAIL_NORMAL");
            ibmMQSMap.put( 1, "MQS_AVAIL_ERROR");
            ibmMQSMap.put( 2, "MQS_AVAIL_STOPPED");
            ibmMQSMap.put( 0, "MQS_EXPANDST_NORMAL");
            ibmMQSMap.put( 1, "MQS_EXPANDST_FAILED");
            ibmMQSMap.put( 2, "MQS_EXPANDST_MAXIMUM");
        }
        if( ibmMQGACFMap == null ) {
            ibmMQGACFMap = new HashMap<>();
            ibmMQGACFMap.put( 8001, "MQGACF_FIRST");
            ibmMQGACFMap.put( 8001, "MQGACF_COMMAND_CONTEXT");
            ibmMQGACFMap.put( 8002, "MQGACF_COMMAND_DATA");
            ibmMQGACFMap.put( 8003, "MQGACF_TRACE_ROUTE");
            ibmMQGACFMap.put( 8004, "MQGACF_OPERATION");
            ibmMQGACFMap.put( 8005, "MQGACF_ACTIVITY");
            ibmMQGACFMap.put( 8006, "MQGACF_EMBEDDED_MQMD");
            ibmMQGACFMap.put( 8007, "MQGACF_MESSAGE");
            ibmMQGACFMap.put( 8008, "MQGACF_MQMD");
            ibmMQGACFMap.put( 8009, "MQGACF_VALUE_NAMING");
            ibmMQGACFMap.put( 8010, "MQGACF_Q_ACCOUNTING_DATA");
            ibmMQGACFMap.put( 8011, "MQGACF_Q_STATISTICS_DATA");
            ibmMQGACFMap.put( 8012, "MQGACF_CHL_STATISTICS_DATA");
            ibmMQGACFMap.put( 8013, "MQGACF_ACTIVITY_TRACE");
            ibmMQGACFMap.put( 8014, "MQGACF_APP_DIST_LIST");
            ibmMQGACFMap.put( 8014, "MQGACF_LAST_USED");
        }
        if( ibmMQRPMap == null ) {
            ibmMQRPMap = new HashMap<>();
            ibmMQRPMap.put( 1, "MQRP_YES");
            ibmMQRPMap.put( 0, "MQRP_NO");
        }
        if( ibmMQBACFMap == null ) {
            ibmMQBACFMap = new HashMap<>();
            ibmMQBACFMap.put( 7001, "MQBACF_FIRST");
            ibmMQBACFMap.put( 7001, "MQBACF_EVENT_ACCOUNTING_TOKEN");
            ibmMQBACFMap.put( 7002, "MQBACF_EVENT_SECURITY_ID");
            ibmMQBACFMap.put( 7003, "MQBACF_RESPONSE_SET");
            ibmMQBACFMap.put( 7004, "MQBACF_RESPONSE_ID");
            ibmMQBACFMap.put( 7005, "MQBACF_EXTERNAL_UOW_ID");
            ibmMQBACFMap.put( 7006, "MQBACF_CONNECTION_ID");
            ibmMQBACFMap.put( 7007, "MQBACF_GENERIC_CONNECTION_ID");
            ibmMQBACFMap.put( 7008, "MQBACF_ORIGIN_UOW_ID");
            ibmMQBACFMap.put( 7009, "MQBACF_Q_MGR_UOW_ID");
            ibmMQBACFMap.put( 7010, "MQBACF_ACCOUNTING_TOKEN");
            ibmMQBACFMap.put( 7011, "MQBACF_CORREL_ID");
            ibmMQBACFMap.put( 7012, "MQBACF_GROUP_ID");
            ibmMQBACFMap.put( 7013, "MQBACF_MSG_ID");
            ibmMQBACFMap.put( 7014, "MQBACF_CF_LEID");
            ibmMQBACFMap.put( 7015, "MQBACF_DESTINATION_CORREL_ID");
            ibmMQBACFMap.put( 7016, "MQBACF_SUB_ID");
            ibmMQBACFMap.put( 7019, "MQBACF_ALTERNATE_SECURITYID");
            ibmMQBACFMap.put( 7020, "MQBACF_MESSAGE_DATA");
            ibmMQBACFMap.put( 7021, "MQBACF_MQBO_STRUCT");
            ibmMQBACFMap.put( 7022, "MQBACF_MQCB_FUNCTION");
            ibmMQBACFMap.put( 7023, "MQBACF_MQCBC_STRUCT");
            ibmMQBACFMap.put( 7024, "MQBACF_MQCBD_STRUCT");
            ibmMQBACFMap.put( 7025, "MQBACF_MQCD_STRUCT");
            ibmMQBACFMap.put( 7026, "MQBACF_MQCNO_STRUCT");
            ibmMQBACFMap.put( 7027, "MQBACF_MQGMO_STRUCT");
            ibmMQBACFMap.put( 7028, "MQBACF_MQMD_STRUCT");
            ibmMQBACFMap.put( 7029, "MQBACF_MQPMO_STRUCT");
            ibmMQBACFMap.put( 7030, "MQBACF_MQSD_STRUCT");
            ibmMQBACFMap.put( 7031, "MQBACF_MQSTS_STRUCT");
            ibmMQBACFMap.put( 7032, "MQBACF_SUB_CORREL_ID");
            ibmMQBACFMap.put( 7033, "MQBACF_XA_XID");
            ibmMQBACFMap.put( 7034, "MQBACF_XQH_CORREL_ID");
            ibmMQBACFMap.put( 7035, "MQBACF_XQH_MSG_ID");
            ibmMQBACFMap.put( 7035, "MQBACF_LAST_USED");
        }
        if( ibmMQSYNCPOINTMap == null ) {
            ibmMQSYNCPOINTMap = new HashMap<>();
            ibmMQSYNCPOINTMap.put( 0, "MQSYNCPOINT_YES");
            ibmMQSYNCPOINTMap.put( 1, "MQSYNCPOINT_IFPER");
        }
        if( ibmMQSECTYPEMap == null ) {
            ibmMQSECTYPEMap = new HashMap<>();
            ibmMQSECTYPEMap.put( 1, "MQSECTYPE_AUTHSERV");
            ibmMQSECTYPEMap.put( 2, "MQSECTYPE_SSL");
            ibmMQSECTYPEMap.put( 3, "MQSECTYPE_CLASSES");
            ibmMQSECTYPEMap.put( 4, "MQSECTYPE_CONNAUTH");
        }
        if( ibmMQMLPMap == null ) {
            ibmMQMLPMap = new HashMap<>();
            ibmMQMLPMap.put( 0, "MQMLP_TOLERATE_UNPROTECTED_NO");
            ibmMQMLPMap.put( 1, "MQMLP_TOLERATE_UNPROTECTED_YES");
            ibmMQMLPMap.put( 0, "MQMLP_ENCRYPTION_ALG_NONE");
            ibmMQMLPMap.put( 1, "MQMLP_ENCRYPTION_ALG_RC2");
            ibmMQMLPMap.put( 2, "MQMLP_ENCRYPTION_ALG_DES");
            ibmMQMLPMap.put( 3, "MQMLP_ENCRYPTION_ALG_3DES");
            ibmMQMLPMap.put( 4, "MQMLP_ENCRYPTION_ALG_AES128");
            ibmMQMLPMap.put( 5, "MQMLP_ENCRYPTION_ALG_AES256");
            ibmMQMLPMap.put( 0, "MQMLP_SIGN_ALG_NONE");
            ibmMQMLPMap.put( 1, "MQMLP_SIGN_ALG_MD5");
            ibmMQMLPMap.put( 2, "MQMLP_SIGN_ALG_SHA1");
            ibmMQMLPMap.put( 3, "MQMLP_SIGN_ALG_SHA224");
            ibmMQMLPMap.put( 4, "MQMLP_SIGN_ALG_SHA256");
            ibmMQMLPMap.put( 5, "MQMLP_SIGN_ALG_SHA384");
            ibmMQMLPMap.put( 6, "MQMLP_SIGN_ALG_SHA512");
        }
        if( ibmMQLDAPMap == null ) {
            ibmMQLDAPMap = new HashMap<>();
            ibmMQLDAPMap.put( 0, "MQLDAP_AUTHORMD_OS");
            ibmMQLDAPMap.put( 1, "MQLDAP_AUTHORMD_SEARCHGRP");
            ibmMQLDAPMap.put( 2, "MQLDAP_AUTHORMD_SEARCHUSR");
            ibmMQLDAPMap.put( 0, "MQLDAP_NESTGRP_NO");
            ibmMQLDAPMap.put( 1, "MQLDAP_NESTGRP_YES");
        }
        if( ibmMQDISCONNECTMap == null ) {
            ibmMQDISCONNECTMap = new HashMap<>();
            ibmMQDISCONNECTMap.put( 0, "MQDISCONNECT_NORMAL");
            ibmMQDISCONNECTMap.put( 1, "MQDISCONNECT_IMPLICIT");
            ibmMQDISCONNECTMap.put( 2, "MQDISCONNECT_Q_MGR");
        }
        if( ibmMQRARMap == null ) {
            ibmMQRARMap = new HashMap<>();
            ibmMQRARMap.put( 1, "MQRAR_YES");
            ibmMQRARMap.put( 0, "MQRAR_NO");
        }
        if( ibmMQCFBSMap == null ) {
            ibmMQCFBSMap = new HashMap<>();
            ibmMQCFBSMap.put( 16, "MQCFBS_STRUC_LENGTH_FIXED");
        }
        if( ibmMQBPLOCATIONMap == null ) {
            ibmMQBPLOCATIONMap = new HashMap<>();
            ibmMQBPLOCATIONMap.put( 0, "MQBPLOCATION_BELOW");
            ibmMQBPLOCATIONMap.put( 1, "MQBPLOCATION_ABOVE");
            ibmMQBPLOCATIONMap.put( 2, "MQBPLOCATION_SWITCHING_ABOVE");
            ibmMQBPLOCATIONMap.put( 3, "MQBPLOCATION_SWITCHING_BELOW");
        }
        if( ibmMQLDAPCMap == null ) {
            ibmMQLDAPCMap = new HashMap<>();
            ibmMQLDAPCMap.put( 0, "MQLDAPC_INACTIVE");
            ibmMQLDAPCMap.put( 1, "MQLDAPC_CONNECTED");
            ibmMQLDAPCMap.put( 2, "MQLDAPC_ERROR");
        }
        if( ibmMQMCPMap == null ) {
            ibmMQMCPMap = new HashMap<>();
            ibmMQMCPMap.put( 2, "MQMCP_REPLY");
            ibmMQMCPMap.put( 1, "MQMCP_USER");
            ibmMQMCPMap.put( 0, "MQMCP_NONE");
        }
        if( ibmMQCAUTMap == null ) {
            ibmMQCAUTMap = new HashMap<>();
            ibmMQCAUTMap.put( 0, "MQCAUT_ALL");
            ibmMQCAUTMap.put( 1, "MQCAUT_BLOCKUSER");
            ibmMQCAUTMap.put( 2, "MQCAUT_BLOCKADDR");
            ibmMQCAUTMap.put( 3, "MQCAUT_SSLPEERMAP");
            ibmMQCAUTMap.put( 4, "MQCAUT_ADDRESSMAP");
            ibmMQCAUTMap.put( 5, "MQCAUT_USERMAP");
            ibmMQCAUTMap.put( 6, "MQCAUT_QMGRMAP");
        }
        if( ibmMQCACHMap == null ) {
            ibmMQCACHMap = new HashMap<>();
            ibmMQCACHMap.put( 3501, "MQCACH_FIRST");
            ibmMQCACHMap.put( 3501, "MQCACH_CHANNEL_NAME");
            ibmMQCACHMap.put( 3502, "MQCACH_DESC");
            ibmMQCACHMap.put( 3503, "MQCACH_MODE_NAME");
            ibmMQCACHMap.put( 3504, "MQCACH_TP_NAME");
            ibmMQCACHMap.put( 3505, "MQCACH_XMIT_Q_NAME");
            ibmMQCACHMap.put( 3506, "MQCACH_CONNECTION_NAME");
            ibmMQCACHMap.put( 3507, "MQCACH_MCA_NAME");
            ibmMQCACHMap.put( 3508, "MQCACH_SEC_EXIT_NAME");
            ibmMQCACHMap.put( 3509, "MQCACH_MSG_EXIT_NAME");
            ibmMQCACHMap.put( 3510, "MQCACH_SEND_EXIT_NAME");
            ibmMQCACHMap.put( 3511, "MQCACH_RCV_EXIT_NAME");
            ibmMQCACHMap.put( 3512, "MQCACH_CHANNEL_NAMES");
            ibmMQCACHMap.put( 3513, "MQCACH_SEC_EXIT_USER_DATA");
            ibmMQCACHMap.put( 3514, "MQCACH_MSG_EXIT_USER_DATA");
            ibmMQCACHMap.put( 3515, "MQCACH_SEND_EXIT_USER_DATA");
            ibmMQCACHMap.put( 3516, "MQCACH_RCV_EXIT_USER_DATA");
            ibmMQCACHMap.put( 3517, "MQCACH_USER_ID");
            ibmMQCACHMap.put( 3518, "MQCACH_PASSWORD");
            ibmMQCACHMap.put( 3520, "MQCACH_LOCAL_ADDRESS");
            ibmMQCACHMap.put( 3521, "MQCACH_LOCAL_NAME");
            ibmMQCACHMap.put( 3524, "MQCACH_LAST_MSG_TIME");
            ibmMQCACHMap.put( 3525, "MQCACH_LAST_MSG_DATE");
            ibmMQCACHMap.put( 3527, "MQCACH_MCA_USER_ID");
            ibmMQCACHMap.put( 3528, "MQCACH_CHANNEL_START_TIME");
            ibmMQCACHMap.put( 3529, "MQCACH_CHANNEL_START_DATE");
            ibmMQCACHMap.put( 3530, "MQCACH_MCA_JOB_NAME");
            ibmMQCACHMap.put( 3531, "MQCACH_LAST_LUWID");
            ibmMQCACHMap.put( 3532, "MQCACH_CURRENT_LUWID");
            ibmMQCACHMap.put( 3533, "MQCACH_FORMAT_NAME");
            ibmMQCACHMap.put( 3534, "MQCACH_MR_EXIT_NAME");
            ibmMQCACHMap.put( 3535, "MQCACH_MR_EXIT_USER_DATA");
            ibmMQCACHMap.put( 3544, "MQCACH_SSL_CIPHER_SPEC");
            ibmMQCACHMap.put( 3545, "MQCACH_SSL_PEER_NAME");
            ibmMQCACHMap.put( 3546, "MQCACH_SSL_HANDSHAKE_STAGE");
            ibmMQCACHMap.put( 3547, "MQCACH_SSL_SHORT_PEER_NAME");
            ibmMQCACHMap.put( 3548, "MQCACH_REMOTE_APPL_TAG");
            ibmMQCACHMap.put( 3549, "MQCACH_SSL_CERT_USER_ID");
            ibmMQCACHMap.put( 3550, "MQCACH_SSL_CERT_ISSUER_NAME");
            ibmMQCACHMap.put( 3551, "MQCACH_LU_NAME");
            ibmMQCACHMap.put( 3552, "MQCACH_IP_ADDRESS");
            ibmMQCACHMap.put( 3553, "MQCACH_TCP_NAME");
            ibmMQCACHMap.put( 3554, "MQCACH_LISTENER_NAME");
            ibmMQCACHMap.put( 3555, "MQCACH_LISTENER_DESC");
            ibmMQCACHMap.put( 3556, "MQCACH_LISTENER_START_DATE");
            ibmMQCACHMap.put( 3557, "MQCACH_LISTENER_START_TIME");
            ibmMQCACHMap.put( 3558, "MQCACH_SSL_KEY_RESET_DATE");
            ibmMQCACHMap.put( 3559, "MQCACH_SSL_KEY_RESET_TIME");
            ibmMQCACHMap.put( 3560, "MQCACH_REMOTE_VERSION");
            ibmMQCACHMap.put( 3561, "MQCACH_REMOTE_PRODUCT");
            ibmMQCACHMap.put( 3562, "MQCACH_GROUP_ADDRESS");
            ibmMQCACHMap.put( 3563, "MQCACH_JAAS_CONFIG");
            ibmMQCACHMap.put( 3564, "MQCACH_CLIENT_ID");
            ibmMQCACHMap.put( 3565, "MQCACH_SSL_KEY_PASSPHRASE");
            ibmMQCACHMap.put( 3566, "MQCACH_CONNECTION_NAME_LIST");
            ibmMQCACHMap.put( 3567, "MQCACH_CLIENT_USER_ID");
            ibmMQCACHMap.put( 3568, "MQCACH_MCA_USER_ID_LIST");
            ibmMQCACHMap.put( 3569, "MQCACH_SSL_CIPHER_SUITE");
            ibmMQCACHMap.put( 3570, "MQCACH_WEBCONTENT_PATH");
            ibmMQCACHMap.put( 3570, "MQCACH_LAST_USED");
        }
        if( ibmMQCFINMap == null ) {
            ibmMQCFINMap = new HashMap<>();
            ibmMQCFINMap.put( 16, "MQCFIN_STRUC_LENGTH");
        }
        if( ibmMQSECSWMap == null ) {
            ibmMQSECSWMap = new HashMap<>();
            ibmMQSECSWMap.put( 1, "MQSECSW_PROCESS");
            ibmMQSECSWMap.put( 2, "MQSECSW_NAMELIST");
            ibmMQSECSWMap.put( 3, "MQSECSW_Q");
            ibmMQSECSWMap.put( 4, "MQSECSW_TOPIC");
            ibmMQSECSWMap.put( 6, "MQSECSW_CONTEXT");
            ibmMQSECSWMap.put( 7, "MQSECSW_ALTERNATE_USER");
            ibmMQSECSWMap.put( 8, "MQSECSW_COMMAND");
            ibmMQSECSWMap.put( 9, "MQSECSW_CONNECTION");
            ibmMQSECSWMap.put( 10, "MQSECSW_SUBSYSTEM");
            ibmMQSECSWMap.put( 11, "MQSECSW_COMMAND_RESOURCES");
            ibmMQSECSWMap.put( 15, "MQSECSW_Q_MGR");
            ibmMQSECSWMap.put( 16, "MQSECSW_QSG");
            ibmMQSECSWMap.put( 21, "MQSECSW_OFF_FOUND");
            ibmMQSECSWMap.put( 22, "MQSECSW_ON_FOUND");
            ibmMQSECSWMap.put( 23, "MQSECSW_OFF_NOT_FOUND");
            ibmMQSECSWMap.put( 24, "MQSECSW_ON_NOT_FOUND");
            ibmMQSECSWMap.put( 25, "MQSECSW_OFF_ERROR");
            ibmMQSECSWMap.put( 26, "MQSECSW_ON_OVERRIDDEN");
        }
        if( ibmMQSELTYPEMap == null ) {
            ibmMQSELTYPEMap = new HashMap<>();
            ibmMQSELTYPEMap.put( 0, "MQSELTYPE_NONE");
            ibmMQSELTYPEMap.put( 1, "MQSELTYPE_STANDARD");
            ibmMQSELTYPEMap.put( 2, "MQSELTYPE_EXTENDED");
        }
        if( ibmMQASMap == null ) {
            ibmMQASMap = new HashMap<>();
            ibmMQASMap.put( 0, "MQAS_NONE");
            ibmMQASMap.put( 1, "MQAS_STARTED");
            ibmMQASMap.put( 2, "MQAS_START_WAIT");
            ibmMQASMap.put( 3, "MQAS_STOPPED");
            ibmMQASMap.put( 4, "MQAS_SUSPENDED");
            ibmMQASMap.put( 5, "MQAS_SUSPENDED_TEMPORARY");
            ibmMQASMap.put( 6, "MQAS_ACTIVE");
            ibmMQASMap.put( 7, "MQAS_INACTIVE");
        }
        if( ibmMQTIMEMap == null ) {
            ibmMQTIMEMap = new HashMap<>();
            ibmMQTIMEMap.put( 0, "MQTIME_UNIT_MINS");
            ibmMQTIMEMap.put( 1, "MQTIME_UNIT_SECS");
        }
        if( ibmMQCFOPMap == null ) {
            ibmMQCFOPMap = new HashMap<>();
            ibmMQCFOPMap.put( 1, "MQCFOP_LESS");
            ibmMQCFOPMap.put( 2, "MQCFOP_EQUAL");
            ibmMQCFOPMap.put( 4, "MQCFOP_GREATER");
            ibmMQCFOPMap.put( 6, "MQCFOP_NOT_LESS");
            ibmMQCFOPMap.put( 5, "MQCFOP_NOT_EQUAL");
            ibmMQCFOPMap.put( 3, "MQCFOP_NOT_GREATER");
            ibmMQCFOPMap.put( 18, "MQCFOP_LIKE");
            ibmMQCFOPMap.put( 21, "MQCFOP_NOT_LIKE");
            ibmMQCFOPMap.put( 10, "MQCFOP_CONTAINS");
            ibmMQCFOPMap.put( 13, "MQCFOP_EXCLUDES");
            ibmMQCFOPMap.put( 26, "MQCFOP_CONTAINS_GEN");
            ibmMQCFOPMap.put( 29, "MQCFOP_EXCLUDES_GEN");
        }
        if( ibmMQQMTMap == null ) {
            ibmMQQMTMap = new HashMap<>();
            ibmMQQMTMap.put( 0, "MQQMT_NORMAL");
            ibmMQQMTMap.put( 1, "MQQMT_REPOSITORY");
        }
        if( ibmMQQMSTAMap == null ) {
            ibmMQQMSTAMap = new HashMap<>();
            ibmMQQMSTAMap.put( 1, "MQQMSTA_STARTING");
            ibmMQQMSTAMap.put( 2, "MQQMSTA_RUNNING");
            ibmMQQMSTAMap.put( 3, "MQQMSTA_QUIESCING");
            ibmMQQMSTAMap.put( 4, "MQQMSTA_STANDBY");
        }
        if( ibmMQCMDMap == null ) {
            ibmMQCMDMap = new HashMap<>();
            ibmMQCMDMap.put( 0, "MQCMD_NONE");
            ibmMQCMDMap.put( 1, "MQCMD_CHANGE_Q_MGR");
            ibmMQCMDMap.put( 2, "MQCMD_INQUIRE_Q_MGR");
            ibmMQCMDMap.put( 3, "MQCMD_CHANGE_PROCESS");
            ibmMQCMDMap.put( 4, "MQCMD_COPY_PROCESS");
            ibmMQCMDMap.put( 5, "MQCMD_CREATE_PROCESS");
            ibmMQCMDMap.put( 6, "MQCMD_DELETE_PROCESS");
            ibmMQCMDMap.put( 7, "MQCMD_INQUIRE_PROCESS");
            ibmMQCMDMap.put( 8, "MQCMD_CHANGE_Q");
            ibmMQCMDMap.put( 9, "MQCMD_CLEAR_Q");
            ibmMQCMDMap.put( 10, "MQCMD_COPY_Q");
            ibmMQCMDMap.put( 11, "MQCMD_CREATE_Q");
            ibmMQCMDMap.put( 12, "MQCMD_DELETE_Q");
            ibmMQCMDMap.put( 13, "MQCMD_INQUIRE_Q");
            ibmMQCMDMap.put( 16, "MQCMD_REFRESH_Q_MGR");
            ibmMQCMDMap.put( 17, "MQCMD_RESET_Q_STATS");
            ibmMQCMDMap.put( 18, "MQCMD_INQUIRE_Q_NAMES");
            ibmMQCMDMap.put( 19, "MQCMD_INQUIRE_PROCESS_NAMES");
            ibmMQCMDMap.put( 20, "MQCMD_INQUIRE_CHANNEL_NAMES");
            ibmMQCMDMap.put( 21, "MQCMD_CHANGE_CHANNEL");
            ibmMQCMDMap.put( 22, "MQCMD_COPY_CHANNEL");
            ibmMQCMDMap.put( 23, "MQCMD_CREATE_CHANNEL");
            ibmMQCMDMap.put( 24, "MQCMD_DELETE_CHANNEL");
            ibmMQCMDMap.put( 25, "MQCMD_INQUIRE_CHANNEL");
            ibmMQCMDMap.put( 26, "MQCMD_PING_CHANNEL");
            ibmMQCMDMap.put( 27, "MQCMD_RESET_CHANNEL");
            ibmMQCMDMap.put( 28, "MQCMD_START_CHANNEL");
            ibmMQCMDMap.put( 29, "MQCMD_STOP_CHANNEL");
            ibmMQCMDMap.put( 30, "MQCMD_START_CHANNEL_INIT");
            ibmMQCMDMap.put( 31, "MQCMD_START_CHANNEL_LISTENER");
            ibmMQCMDMap.put( 32, "MQCMD_CHANGE_NAMELIST");
            ibmMQCMDMap.put( 33, "MQCMD_COPY_NAMELIST");
            ibmMQCMDMap.put( 34, "MQCMD_CREATE_NAMELIST");
            ibmMQCMDMap.put( 35, "MQCMD_DELETE_NAMELIST");
            ibmMQCMDMap.put( 36, "MQCMD_INQUIRE_NAMELIST");
            ibmMQCMDMap.put( 37, "MQCMD_INQUIRE_NAMELIST_NAMES");
            ibmMQCMDMap.put( 38, "MQCMD_ESCAPE");
            ibmMQCMDMap.put( 39, "MQCMD_RESOLVE_CHANNEL");
            ibmMQCMDMap.put( 40, "MQCMD_PING_Q_MGR");
            ibmMQCMDMap.put( 41, "MQCMD_INQUIRE_Q_STATUS");
            ibmMQCMDMap.put( 42, "MQCMD_INQUIRE_CHANNEL_STATUS");
            ibmMQCMDMap.put( 43, "MQCMD_CONFIG_EVENT");
            ibmMQCMDMap.put( 44, "MQCMD_Q_MGR_EVENT");
            ibmMQCMDMap.put( 45, "MQCMD_PERFM_EVENT");
            ibmMQCMDMap.put( 46, "MQCMD_CHANNEL_EVENT");
            ibmMQCMDMap.put( 60, "MQCMD_DELETE_PUBLICATION");
            ibmMQCMDMap.put( 61, "MQCMD_DEREGISTER_PUBLISHER");
            ibmMQCMDMap.put( 62, "MQCMD_DEREGISTER_SUBSCRIBER");
            ibmMQCMDMap.put( 63, "MQCMD_PUBLISH");
            ibmMQCMDMap.put( 64, "MQCMD_REGISTER_PUBLISHER");
            ibmMQCMDMap.put( 65, "MQCMD_REGISTER_SUBSCRIBER");
            ibmMQCMDMap.put( 66, "MQCMD_REQUEST_UPDATE");
            ibmMQCMDMap.put( 67, "MQCMD_BROKER_INTERNAL");
            ibmMQCMDMap.put( 69, "MQCMD_ACTIVITY_MSG");
            ibmMQCMDMap.put( 70, "MQCMD_INQUIRE_CLUSTER_Q_MGR");
            ibmMQCMDMap.put( 71, "MQCMD_RESUME_Q_MGR_CLUSTER");
            ibmMQCMDMap.put( 72, "MQCMD_SUSPEND_Q_MGR_CLUSTER");
            ibmMQCMDMap.put( 73, "MQCMD_REFRESH_CLUSTER");
            ibmMQCMDMap.put( 74, "MQCMD_RESET_CLUSTER");
            ibmMQCMDMap.put( 75, "MQCMD_TRACE_ROUTE");
            ibmMQCMDMap.put( 78, "MQCMD_REFRESH_SECURITY");
            ibmMQCMDMap.put( 79, "MQCMD_CHANGE_AUTH_INFO");
            ibmMQCMDMap.put( 80, "MQCMD_COPY_AUTH_INFO");
            ibmMQCMDMap.put( 81, "MQCMD_CREATE_AUTH_INFO");
            ibmMQCMDMap.put( 82, "MQCMD_DELETE_AUTH_INFO");
            ibmMQCMDMap.put( 83, "MQCMD_INQUIRE_AUTH_INFO");
            ibmMQCMDMap.put( 84, "MQCMD_INQUIRE_AUTH_INFO_NAMES");
            ibmMQCMDMap.put( 85, "MQCMD_INQUIRE_CONNECTION");
            ibmMQCMDMap.put( 86, "MQCMD_STOP_CONNECTION");
            ibmMQCMDMap.put( 87, "MQCMD_INQUIRE_AUTH_RECS");
            ibmMQCMDMap.put( 88, "MQCMD_INQUIRE_ENTITY_AUTH");
            ibmMQCMDMap.put( 89, "MQCMD_DELETE_AUTH_REC");
            ibmMQCMDMap.put( 90, "MQCMD_SET_AUTH_REC");
            ibmMQCMDMap.put( 91, "MQCMD_LOGGER_EVENT");
            ibmMQCMDMap.put( 92, "MQCMD_RESET_Q_MGR");
            ibmMQCMDMap.put( 93, "MQCMD_CHANGE_LISTENER");
            ibmMQCMDMap.put( 94, "MQCMD_COPY_LISTENER");
            ibmMQCMDMap.put( 95, "MQCMD_CREATE_LISTENER");
            ibmMQCMDMap.put( 96, "MQCMD_DELETE_LISTENER");
            ibmMQCMDMap.put( 97, "MQCMD_INQUIRE_LISTENER");
            ibmMQCMDMap.put( 98, "MQCMD_INQUIRE_LISTENER_STATUS");
            ibmMQCMDMap.put( 99, "MQCMD_COMMAND_EVENT");
            ibmMQCMDMap.put( 100, "MQCMD_CHANGE_SECURITY");
            ibmMQCMDMap.put( 101, "MQCMD_CHANGE_CF_STRUC");
            ibmMQCMDMap.put( 102, "MQCMD_CHANGE_STG_CLASS");
            ibmMQCMDMap.put( 103, "MQCMD_CHANGE_TRACE");
            ibmMQCMDMap.put( 104, "MQCMD_ARCHIVE_LOG");
            ibmMQCMDMap.put( 105, "MQCMD_BACKUP_CF_STRUC");
            ibmMQCMDMap.put( 106, "MQCMD_CREATE_BUFFER_POOL");
            ibmMQCMDMap.put( 107, "MQCMD_CREATE_PAGE_SET");
            ibmMQCMDMap.put( 108, "MQCMD_CREATE_CF_STRUC");
            ibmMQCMDMap.put( 109, "MQCMD_CREATE_STG_CLASS");
            ibmMQCMDMap.put( 110, "MQCMD_COPY_CF_STRUC");
            ibmMQCMDMap.put( 111, "MQCMD_COPY_STG_CLASS");
            ibmMQCMDMap.put( 112, "MQCMD_DELETE_CF_STRUC");
            ibmMQCMDMap.put( 113, "MQCMD_DELETE_STG_CLASS");
            ibmMQCMDMap.put( 114, "MQCMD_INQUIRE_ARCHIVE");
            ibmMQCMDMap.put( 115, "MQCMD_INQUIRE_CF_STRUC");
            ibmMQCMDMap.put( 116, "MQCMD_INQUIRE_CF_STRUC_STATUS");
            ibmMQCMDMap.put( 117, "MQCMD_INQUIRE_CMD_SERVER");
            ibmMQCMDMap.put( 118, "MQCMD_INQUIRE_CHANNEL_INIT");
            ibmMQCMDMap.put( 119, "MQCMD_INQUIRE_QSG");
            ibmMQCMDMap.put( 120, "MQCMD_INQUIRE_LOG");
            ibmMQCMDMap.put( 121, "MQCMD_INQUIRE_SECURITY");
            ibmMQCMDMap.put( 122, "MQCMD_INQUIRE_STG_CLASS");
            ibmMQCMDMap.put( 123, "MQCMD_INQUIRE_SYSTEM");
            ibmMQCMDMap.put( 124, "MQCMD_INQUIRE_THREAD");
            ibmMQCMDMap.put( 125, "MQCMD_INQUIRE_TRACE");
            ibmMQCMDMap.put( 126, "MQCMD_INQUIRE_USAGE");
            ibmMQCMDMap.put( 127, "MQCMD_MOVE_Q");
            ibmMQCMDMap.put( 128, "MQCMD_RECOVER_BSDS");
            ibmMQCMDMap.put( 129, "MQCMD_RECOVER_CF_STRUC");
            ibmMQCMDMap.put( 130, "MQCMD_RESET_TPIPE");
            ibmMQCMDMap.put( 131, "MQCMD_RESOLVE_INDOUBT");
            ibmMQCMDMap.put( 132, "MQCMD_RESUME_Q_MGR");
            ibmMQCMDMap.put( 133, "MQCMD_REVERIFY_SECURITY");
            ibmMQCMDMap.put( 134, "MQCMD_SET_ARCHIVE");
            ibmMQCMDMap.put( 136, "MQCMD_SET_LOG");
            ibmMQCMDMap.put( 137, "MQCMD_SET_SYSTEM");
            ibmMQCMDMap.put( 138, "MQCMD_START_CMD_SERVER");
            ibmMQCMDMap.put( 139, "MQCMD_START_Q_MGR");
            ibmMQCMDMap.put( 140, "MQCMD_START_TRACE");
            ibmMQCMDMap.put( 141, "MQCMD_STOP_CHANNEL_INIT");
            ibmMQCMDMap.put( 142, "MQCMD_STOP_CHANNEL_LISTENER");
            ibmMQCMDMap.put( 143, "MQCMD_STOP_CMD_SERVER");
            ibmMQCMDMap.put( 144, "MQCMD_STOP_Q_MGR");
            ibmMQCMDMap.put( 145, "MQCMD_STOP_TRACE");
            ibmMQCMDMap.put( 146, "MQCMD_SUSPEND_Q_MGR");
            ibmMQCMDMap.put( 147, "MQCMD_INQUIRE_CF_STRUC_NAMES");
            ibmMQCMDMap.put( 148, "MQCMD_INQUIRE_STG_CLASS_NAMES");
            ibmMQCMDMap.put( 149, "MQCMD_CHANGE_SERVICE");
            ibmMQCMDMap.put( 150, "MQCMD_COPY_SERVICE");
            ibmMQCMDMap.put( 151, "MQCMD_CREATE_SERVICE");
            ibmMQCMDMap.put( 152, "MQCMD_DELETE_SERVICE");
            ibmMQCMDMap.put( 153, "MQCMD_INQUIRE_SERVICE");
            ibmMQCMDMap.put( 154, "MQCMD_INQUIRE_SERVICE_STATUS");
            ibmMQCMDMap.put( 155, "MQCMD_START_SERVICE");
            ibmMQCMDMap.put( 156, "MQCMD_STOP_SERVICE");
            ibmMQCMDMap.put( 157, "MQCMD_DELETE_BUFFER_POOL");
            ibmMQCMDMap.put( 158, "MQCMD_DELETE_PAGE_SET");
            ibmMQCMDMap.put( 159, "MQCMD_CHANGE_BUFFER_POOL");
            ibmMQCMDMap.put( 160, "MQCMD_CHANGE_PAGE_SET");
            ibmMQCMDMap.put( 161, "MQCMD_INQUIRE_Q_MGR_STATUS");
            ibmMQCMDMap.put( 162, "MQCMD_CREATE_LOG");
            ibmMQCMDMap.put( 164, "MQCMD_STATISTICS_MQI");
            ibmMQCMDMap.put( 165, "MQCMD_STATISTICS_Q");
            ibmMQCMDMap.put( 166, "MQCMD_STATISTICS_CHANNEL");
            ibmMQCMDMap.put( 167, "MQCMD_ACCOUNTING_MQI");
            ibmMQCMDMap.put( 168, "MQCMD_ACCOUNTING_Q");
            ibmMQCMDMap.put( 169, "MQCMD_INQUIRE_AUTH_SERVICE");
            ibmMQCMDMap.put( 170, "MQCMD_CHANGE_TOPIC");
            ibmMQCMDMap.put( 171, "MQCMD_COPY_TOPIC");
            ibmMQCMDMap.put( 172, "MQCMD_CREATE_TOPIC");
            ibmMQCMDMap.put( 173, "MQCMD_DELETE_TOPIC");
            ibmMQCMDMap.put( 174, "MQCMD_INQUIRE_TOPIC");
            ibmMQCMDMap.put( 175, "MQCMD_INQUIRE_TOPIC_NAMES");
            ibmMQCMDMap.put( 176, "MQCMD_INQUIRE_SUBSCRIPTION");
            ibmMQCMDMap.put( 177, "MQCMD_CREATE_SUBSCRIPTION");
            ibmMQCMDMap.put( 178, "MQCMD_CHANGE_SUBSCRIPTION");
            ibmMQCMDMap.put( 179, "MQCMD_DELETE_SUBSCRIPTION");
            ibmMQCMDMap.put( 181, "MQCMD_COPY_SUBSCRIPTION");
            ibmMQCMDMap.put( 182, "MQCMD_INQUIRE_SUB_STATUS");
            ibmMQCMDMap.put( 183, "MQCMD_INQUIRE_TOPIC_STATUS");
            ibmMQCMDMap.put( 184, "MQCMD_CLEAR_TOPIC_STRING");
            ibmMQCMDMap.put( 185, "MQCMD_INQUIRE_PUBSUB_STATUS");
            ibmMQCMDMap.put( 186, "MQCMD_INQUIRE_SMDS");
            ibmMQCMDMap.put( 187, "MQCMD_CHANGE_SMDS");
            ibmMQCMDMap.put( 188, "MQCMD_RESET_SMDS");
            ibmMQCMDMap.put( 190, "MQCMD_CREATE_COMM_INFO");
            ibmMQCMDMap.put( 191, "MQCMD_INQUIRE_COMM_INFO");
            ibmMQCMDMap.put( 192, "MQCMD_CHANGE_COMM_INFO");
            ibmMQCMDMap.put( 193, "MQCMD_COPY_COMM_INFO");
            ibmMQCMDMap.put( 194, "MQCMD_DELETE_COMM_INFO");
            ibmMQCMDMap.put( 195, "MQCMD_PURGE_CHANNEL");
            ibmMQCMDMap.put( 196, "MQCMD_MQXR_DIAGNOSTICS");
            ibmMQCMDMap.put( 197, "MQCMD_START_SMDSCONN");
            ibmMQCMDMap.put( 198, "MQCMD_STOP_SMDSCONN");
            ibmMQCMDMap.put( 199, "MQCMD_INQUIRE_SMDSCONN");
            ibmMQCMDMap.put( 200, "MQCMD_INQUIRE_MQXR_STATUS");
            ibmMQCMDMap.put( 201, "MQCMD_START_CLIENT_TRACE");
            ibmMQCMDMap.put( 202, "MQCMD_STOP_CLIENT_TRACE");
            ibmMQCMDMap.put( 203, "MQCMD_SET_CHLAUTH_REC");
            ibmMQCMDMap.put( 204, "MQCMD_INQUIRE_CHLAUTH_RECS");
            ibmMQCMDMap.put( 205, "MQCMD_INQUIRE_PROT_POLICY");
            ibmMQCMDMap.put( 206, "MQCMD_CREATE_PROT_POLICY");
            ibmMQCMDMap.put( 207, "MQCMD_DELETE_PROT_POLICY");
            ibmMQCMDMap.put( 208, "MQCMD_CHANGE_PROT_POLICY");
            ibmMQCMDMap.put( 208, "MQCMD_SET_PROT_POLICY");
            ibmMQCMDMap.put( 209, "MQCMD_ACTIVITY_TRACE");
            ibmMQCMDMap.put( 213, "MQCMD_RESET_CF_STRUC");
            ibmMQCMDMap.put( 214, "MQCMD_INQUIRE_XR_CAPABILITY");
        }
        if( ibmMQRCCFMap == null ) {
            ibmMQRCCFMap = new HashMap<>();
            ibmMQRCCFMap.put( 3001, "MQRCCF_CFH_TYPE_ERROR");
            ibmMQRCCFMap.put( 3002, "MQRCCF_CFH_LENGTH_ERROR");
            ibmMQRCCFMap.put( 3003, "MQRCCF_CFH_VERSION_ERROR");
            ibmMQRCCFMap.put( 3004, "MQRCCF_CFH_MSG_SEQ_NUMBER_ERR");
            ibmMQRCCFMap.put( 3005, "MQRCCF_CFH_CONTROL_ERROR");
            ibmMQRCCFMap.put( 3006, "MQRCCF_CFH_PARM_COUNT_ERROR");
            ibmMQRCCFMap.put( 3007, "MQRCCF_CFH_COMMAND_ERROR");
            ibmMQRCCFMap.put( 3008, "MQRCCF_COMMAND_FAILED");
            ibmMQRCCFMap.put( 3009, "MQRCCF_CFIN_LENGTH_ERROR");
            ibmMQRCCFMap.put( 3010, "MQRCCF_CFST_LENGTH_ERROR");
            ibmMQRCCFMap.put( 3011, "MQRCCF_CFST_STRING_LENGTH_ERR");
            ibmMQRCCFMap.put( 3012, "MQRCCF_FORCE_VALUE_ERROR");
            ibmMQRCCFMap.put( 3013, "MQRCCF_STRUCTURE_TYPE_ERROR");
            ibmMQRCCFMap.put( 3014, "MQRCCF_CFIN_PARM_ID_ERROR");
            ibmMQRCCFMap.put( 3015, "MQRCCF_CFST_PARM_ID_ERROR");
            ibmMQRCCFMap.put( 3016, "MQRCCF_MSG_LENGTH_ERROR");
            ibmMQRCCFMap.put( 3017, "MQRCCF_CFIN_DUPLICATE_PARM");
            ibmMQRCCFMap.put( 3018, "MQRCCF_CFST_DUPLICATE_PARM");
            ibmMQRCCFMap.put( 3019, "MQRCCF_PARM_COUNT_TOO_SMALL");
            ibmMQRCCFMap.put( 3020, "MQRCCF_PARM_COUNT_TOO_BIG");
            ibmMQRCCFMap.put( 3021, "MQRCCF_Q_ALREADY_IN_CELL");
            ibmMQRCCFMap.put( 3022, "MQRCCF_Q_TYPE_ERROR");
            ibmMQRCCFMap.put( 3023, "MQRCCF_MD_FORMAT_ERROR");
            ibmMQRCCFMap.put( 3024, "MQRCCF_CFSL_LENGTH_ERROR");
            ibmMQRCCFMap.put( 3025, "MQRCCF_REPLACE_VALUE_ERROR");
            ibmMQRCCFMap.put( 3026, "MQRCCF_CFIL_DUPLICATE_VALUE");
            ibmMQRCCFMap.put( 3027, "MQRCCF_CFIL_COUNT_ERROR");
            ibmMQRCCFMap.put( 3028, "MQRCCF_CFIL_LENGTH_ERROR");
            ibmMQRCCFMap.put( 3029, "MQRCCF_QUIESCE_VALUE_ERROR");
            ibmMQRCCFMap.put( 3029, "MQRCCF_MODE_VALUE_ERROR");
            ibmMQRCCFMap.put( 3030, "MQRCCF_MSG_SEQ_NUMBER_ERROR");
            ibmMQRCCFMap.put( 3031, "MQRCCF_PING_DATA_COUNT_ERROR");
            ibmMQRCCFMap.put( 3032, "MQRCCF_PING_DATA_COMPARE_ERROR");
            ibmMQRCCFMap.put( 3033, "MQRCCF_CFSL_PARM_ID_ERROR");
            ibmMQRCCFMap.put( 3034, "MQRCCF_CHANNEL_TYPE_ERROR");
            ibmMQRCCFMap.put( 3035, "MQRCCF_PARM_SEQUENCE_ERROR");
            ibmMQRCCFMap.put( 3036, "MQRCCF_XMIT_PROTOCOL_TYPE_ERR");
            ibmMQRCCFMap.put( 3037, "MQRCCF_BATCH_SIZE_ERROR");
            ibmMQRCCFMap.put( 3038, "MQRCCF_DISC_INT_ERROR");
            ibmMQRCCFMap.put( 3039, "MQRCCF_SHORT_RETRY_ERROR");
            ibmMQRCCFMap.put( 3040, "MQRCCF_SHORT_TIMER_ERROR");
            ibmMQRCCFMap.put( 3041, "MQRCCF_LONG_RETRY_ERROR");
            ibmMQRCCFMap.put( 3042, "MQRCCF_LONG_TIMER_ERROR");
            ibmMQRCCFMap.put( 3043, "MQRCCF_SEQ_NUMBER_WRAP_ERROR");
            ibmMQRCCFMap.put( 3044, "MQRCCF_MAX_MSG_LENGTH_ERROR");
            ibmMQRCCFMap.put( 3045, "MQRCCF_PUT_AUTH_ERROR");
            ibmMQRCCFMap.put( 3046, "MQRCCF_PURGE_VALUE_ERROR");
            ibmMQRCCFMap.put( 3047, "MQRCCF_CFIL_PARM_ID_ERROR");
            ibmMQRCCFMap.put( 3048, "MQRCCF_MSG_TRUNCATED");
            ibmMQRCCFMap.put( 3049, "MQRCCF_CCSID_ERROR");
            ibmMQRCCFMap.put( 3050, "MQRCCF_ENCODING_ERROR");
            ibmMQRCCFMap.put( 3051, "MQRCCF_QUEUES_VALUE_ERROR");
            ibmMQRCCFMap.put( 3052, "MQRCCF_DATA_CONV_VALUE_ERROR");
            ibmMQRCCFMap.put( 3053, "MQRCCF_INDOUBT_VALUE_ERROR");
            ibmMQRCCFMap.put( 3054, "MQRCCF_ESCAPE_TYPE_ERROR");
            ibmMQRCCFMap.put( 3055, "MQRCCF_REPOS_VALUE_ERROR");
            ibmMQRCCFMap.put( 3062, "MQRCCF_CHANNEL_TABLE_ERROR");
            ibmMQRCCFMap.put( 3063, "MQRCCF_MCA_TYPE_ERROR");
            ibmMQRCCFMap.put( 3064, "MQRCCF_CHL_INST_TYPE_ERROR");
            ibmMQRCCFMap.put( 3065, "MQRCCF_CHL_STATUS_NOT_FOUND");
            ibmMQRCCFMap.put( 3066, "MQRCCF_CFSL_DUPLICATE_PARM");
            ibmMQRCCFMap.put( 3067, "MQRCCF_CFSL_TOTAL_LENGTH_ERROR");
            ibmMQRCCFMap.put( 3068, "MQRCCF_CFSL_COUNT_ERROR");
            ibmMQRCCFMap.put( 3069, "MQRCCF_CFSL_STRING_LENGTH_ERR");
            ibmMQRCCFMap.put( 3070, "MQRCCF_BROKER_DELETED");
            ibmMQRCCFMap.put( 3071, "MQRCCF_STREAM_ERROR");
            ibmMQRCCFMap.put( 3072, "MQRCCF_TOPIC_ERROR");
            ibmMQRCCFMap.put( 3073, "MQRCCF_NOT_REGISTERED");
            ibmMQRCCFMap.put( 3074, "MQRCCF_Q_MGR_NAME_ERROR");
            ibmMQRCCFMap.put( 3075, "MQRCCF_INCORRECT_STREAM");
            ibmMQRCCFMap.put( 3076, "MQRCCF_Q_NAME_ERROR");
            ibmMQRCCFMap.put( 3077, "MQRCCF_NO_RETAINED_MSG");
            ibmMQRCCFMap.put( 3078, "MQRCCF_DUPLICATE_IDENTITY");
            ibmMQRCCFMap.put( 3079, "MQRCCF_INCORRECT_Q");
            ibmMQRCCFMap.put( 3080, "MQRCCF_CORREL_ID_ERROR");
            ibmMQRCCFMap.put( 3081, "MQRCCF_NOT_AUTHORIZED");
            ibmMQRCCFMap.put( 3082, "MQRCCF_UNKNOWN_STREAM");
            ibmMQRCCFMap.put( 3083, "MQRCCF_REG_OPTIONS_ERROR");
            ibmMQRCCFMap.put( 3084, "MQRCCF_PUB_OPTIONS_ERROR");
            ibmMQRCCFMap.put( 3085, "MQRCCF_UNKNOWN_BROKER");
            ibmMQRCCFMap.put( 3086, "MQRCCF_Q_MGR_CCSID_ERROR");
            ibmMQRCCFMap.put( 3087, "MQRCCF_DEL_OPTIONS_ERROR");
            ibmMQRCCFMap.put( 3088, "MQRCCF_CLUSTER_NAME_CONFLICT");
            ibmMQRCCFMap.put( 3089, "MQRCCF_REPOS_NAME_CONFLICT");
            ibmMQRCCFMap.put( 3090, "MQRCCF_CLUSTER_Q_USAGE_ERROR");
            ibmMQRCCFMap.put( 3091, "MQRCCF_ACTION_VALUE_ERROR");
            ibmMQRCCFMap.put( 3092, "MQRCCF_COMMS_LIBRARY_ERROR");
            ibmMQRCCFMap.put( 3093, "MQRCCF_NETBIOS_NAME_ERROR");
            ibmMQRCCFMap.put( 3094, "MQRCCF_BROKER_COMMAND_FAILED");
            ibmMQRCCFMap.put( 3095, "MQRCCF_CFST_CONFLICTING_PARM");
            ibmMQRCCFMap.put( 3096, "MQRCCF_PATH_NOT_VALID");
            ibmMQRCCFMap.put( 3097, "MQRCCF_PARM_SYNTAX_ERROR");
            ibmMQRCCFMap.put( 3098, "MQRCCF_PWD_LENGTH_ERROR");
            ibmMQRCCFMap.put( 3150, "MQRCCF_FILTER_ERROR");
            ibmMQRCCFMap.put( 3151, "MQRCCF_WRONG_USER");
            ibmMQRCCFMap.put( 3152, "MQRCCF_DUPLICATE_SUBSCRIPTION");
            ibmMQRCCFMap.put( 3153, "MQRCCF_SUB_NAME_ERROR");
            ibmMQRCCFMap.put( 3154, "MQRCCF_SUB_IDENTITY_ERROR");
            ibmMQRCCFMap.put( 3155, "MQRCCF_SUBSCRIPTION_IN_USE");
            ibmMQRCCFMap.put( 3156, "MQRCCF_SUBSCRIPTION_LOCKED");
            ibmMQRCCFMap.put( 3157, "MQRCCF_ALREADY_JOINED");
            ibmMQRCCFMap.put( 3160, "MQRCCF_OBJECT_IN_USE");
            ibmMQRCCFMap.put( 3161, "MQRCCF_UNKNOWN_FILE_NAME");
            ibmMQRCCFMap.put( 3162, "MQRCCF_FILE_NOT_AVAILABLE");
            ibmMQRCCFMap.put( 3163, "MQRCCF_DISC_RETRY_ERROR");
            ibmMQRCCFMap.put( 3164, "MQRCCF_ALLOC_RETRY_ERROR");
            ibmMQRCCFMap.put( 3165, "MQRCCF_ALLOC_SLOW_TIMER_ERROR");
            ibmMQRCCFMap.put( 3166, "MQRCCF_ALLOC_FAST_TIMER_ERROR");
            ibmMQRCCFMap.put( 3167, "MQRCCF_PORT_NUMBER_ERROR");
            ibmMQRCCFMap.put( 3168, "MQRCCF_CHL_SYSTEM_NOT_ACTIVE");
            ibmMQRCCFMap.put( 3169, "MQRCCF_ENTITY_NAME_MISSING");
            ibmMQRCCFMap.put( 3170, "MQRCCF_PROFILE_NAME_ERROR");
            ibmMQRCCFMap.put( 3171, "MQRCCF_AUTH_VALUE_ERROR");
            ibmMQRCCFMap.put( 3172, "MQRCCF_AUTH_VALUE_MISSING");
            ibmMQRCCFMap.put( 3173, "MQRCCF_OBJECT_TYPE_MISSING");
            ibmMQRCCFMap.put( 3174, "MQRCCF_CONNECTION_ID_ERROR");
            ibmMQRCCFMap.put( 3175, "MQRCCF_LOG_TYPE_ERROR");
            ibmMQRCCFMap.put( 3176, "MQRCCF_PROGRAM_NOT_AVAILABLE");
            ibmMQRCCFMap.put( 3177, "MQRCCF_PROGRAM_AUTH_FAILED");
            ibmMQRCCFMap.put( 3200, "MQRCCF_NONE_FOUND");
            ibmMQRCCFMap.put( 3201, "MQRCCF_SECURITY_SWITCH_OFF");
            ibmMQRCCFMap.put( 3202, "MQRCCF_SECURITY_REFRESH_FAILED");
            ibmMQRCCFMap.put( 3203, "MQRCCF_PARM_CONFLICT");
            ibmMQRCCFMap.put( 3204, "MQRCCF_COMMAND_INHIBITED");
            ibmMQRCCFMap.put( 3205, "MQRCCF_OBJECT_BEING_DELETED");
            ibmMQRCCFMap.put( 3207, "MQRCCF_STORAGE_CLASS_IN_USE");
            ibmMQRCCFMap.put( 3208, "MQRCCF_OBJECT_NAME_RESTRICTED");
            ibmMQRCCFMap.put( 3209, "MQRCCF_OBJECT_LIMIT_EXCEEDED");
            ibmMQRCCFMap.put( 3210, "MQRCCF_OBJECT_OPEN_FORCE");
            ibmMQRCCFMap.put( 3211, "MQRCCF_DISPOSITION_CONFLICT");
            ibmMQRCCFMap.put( 3212, "MQRCCF_Q_MGR_NOT_IN_QSG");
            ibmMQRCCFMap.put( 3213, "MQRCCF_ATTR_VALUE_FIXED");
            ibmMQRCCFMap.put( 3215, "MQRCCF_NAMELIST_ERROR");
            ibmMQRCCFMap.put( 3217, "MQRCCF_NO_CHANNEL_INITIATOR");
            ibmMQRCCFMap.put( 3218, "MQRCCF_CHANNEL_INITIATOR_ERROR");
            ibmMQRCCFMap.put( 3222, "MQRCCF_COMMAND_LEVEL_CONFLICT");
            ibmMQRCCFMap.put( 3223, "MQRCCF_Q_ATTR_CONFLICT");
            ibmMQRCCFMap.put( 3224, "MQRCCF_EVENTS_DISABLED");
            ibmMQRCCFMap.put( 3225, "MQRCCF_COMMAND_SCOPE_ERROR");
            ibmMQRCCFMap.put( 3226, "MQRCCF_COMMAND_REPLY_ERROR");
            ibmMQRCCFMap.put( 3227, "MQRCCF_FUNCTION_RESTRICTED");
            ibmMQRCCFMap.put( 3228, "MQRCCF_PARM_MISSING");
            ibmMQRCCFMap.put( 3229, "MQRCCF_PARM_VALUE_ERROR");
            ibmMQRCCFMap.put( 3230, "MQRCCF_COMMAND_LENGTH_ERROR");
            ibmMQRCCFMap.put( 3231, "MQRCCF_COMMAND_ORIGIN_ERROR");
            ibmMQRCCFMap.put( 3232, "MQRCCF_LISTENER_CONFLICT");
            ibmMQRCCFMap.put( 3233, "MQRCCF_LISTENER_STARTED");
            ibmMQRCCFMap.put( 3234, "MQRCCF_LISTENER_STOPPED");
            ibmMQRCCFMap.put( 3235, "MQRCCF_CHANNEL_ERROR");
            ibmMQRCCFMap.put( 3236, "MQRCCF_CF_STRUC_ERROR");
            ibmMQRCCFMap.put( 3237, "MQRCCF_UNKNOWN_USER_ID");
            ibmMQRCCFMap.put( 3238, "MQRCCF_UNEXPECTED_ERROR");
            ibmMQRCCFMap.put( 3239, "MQRCCF_NO_XCF_PARTNER");
            ibmMQRCCFMap.put( 3240, "MQRCCF_CFGR_PARM_ID_ERROR");
            ibmMQRCCFMap.put( 3241, "MQRCCF_CFIF_LENGTH_ERROR");
            ibmMQRCCFMap.put( 3242, "MQRCCF_CFIF_OPERATOR_ERROR");
            ibmMQRCCFMap.put( 3243, "MQRCCF_CFIF_PARM_ID_ERROR");
            ibmMQRCCFMap.put( 3244, "MQRCCF_CFSF_FILTER_VAL_LEN_ERR");
            ibmMQRCCFMap.put( 3245, "MQRCCF_CFSF_LENGTH_ERROR");
            ibmMQRCCFMap.put( 3246, "MQRCCF_CFSF_OPERATOR_ERROR");
            ibmMQRCCFMap.put( 3247, "MQRCCF_CFSF_PARM_ID_ERROR");
            ibmMQRCCFMap.put( 3248, "MQRCCF_TOO_MANY_FILTERS");
            ibmMQRCCFMap.put( 3249, "MQRCCF_LISTENER_RUNNING");
            ibmMQRCCFMap.put( 3250, "MQRCCF_LSTR_STATUS_NOT_FOUND");
            ibmMQRCCFMap.put( 3251, "MQRCCF_SERVICE_RUNNING");
            ibmMQRCCFMap.put( 3252, "MQRCCF_SERV_STATUS_NOT_FOUND");
            ibmMQRCCFMap.put( 3253, "MQRCCF_SERVICE_STOPPED");
            ibmMQRCCFMap.put( 3254, "MQRCCF_CFBS_DUPLICATE_PARM");
            ibmMQRCCFMap.put( 3255, "MQRCCF_CFBS_LENGTH_ERROR");
            ibmMQRCCFMap.put( 3256, "MQRCCF_CFBS_PARM_ID_ERROR");
            ibmMQRCCFMap.put( 3257, "MQRCCF_CFBS_STRING_LENGTH_ERR");
            ibmMQRCCFMap.put( 3258, "MQRCCF_CFGR_LENGTH_ERROR");
            ibmMQRCCFMap.put( 3259, "MQRCCF_CFGR_PARM_COUNT_ERROR");
            ibmMQRCCFMap.put( 3260, "MQRCCF_CONN_NOT_STOPPED");
            ibmMQRCCFMap.put( 3261, "MQRCCF_SERVICE_REQUEST_PENDING");
            ibmMQRCCFMap.put( 3262, "MQRCCF_NO_START_CMD");
            ibmMQRCCFMap.put( 3263, "MQRCCF_NO_STOP_CMD");
            ibmMQRCCFMap.put( 3264, "MQRCCF_CFBF_LENGTH_ERROR");
            ibmMQRCCFMap.put( 3265, "MQRCCF_CFBF_PARM_ID_ERROR");
            ibmMQRCCFMap.put( 3266, "MQRCCF_CFBF_OPERATOR_ERROR");
            ibmMQRCCFMap.put( 3267, "MQRCCF_CFBF_FILTER_VAL_LEN_ERR");
            ibmMQRCCFMap.put( 3268, "MQRCCF_LISTENER_STILL_ACTIVE");
            ibmMQRCCFMap.put( 3269, "MQRCCF_DEF_XMIT_Q_CLUS_ERROR");
            ibmMQRCCFMap.put( 3300, "MQRCCF_TOPICSTR_ALREADY_EXISTS");
            ibmMQRCCFMap.put( 3301, "MQRCCF_SHARING_CONVS_ERROR");
            ibmMQRCCFMap.put( 3302, "MQRCCF_SHARING_CONVS_TYPE");
            ibmMQRCCFMap.put( 3303, "MQRCCF_SECURITY_CASE_CONFLICT");
            ibmMQRCCFMap.put( 3305, "MQRCCF_TOPIC_TYPE_ERROR");
            ibmMQRCCFMap.put( 3306, "MQRCCF_MAX_INSTANCES_ERROR");
            ibmMQRCCFMap.put( 3307, "MQRCCF_MAX_INSTS_PER_CLNT_ERR");
            ibmMQRCCFMap.put( 3308, "MQRCCF_TOPIC_STRING_NOT_FOUND");
            ibmMQRCCFMap.put( 3309, "MQRCCF_SUBSCRIPTION_POINT_ERR");
            ibmMQRCCFMap.put( 3311, "MQRCCF_SUB_ALREADY_EXISTS");
            ibmMQRCCFMap.put( 3312, "MQRCCF_UNKNOWN_OBJECT_NAME");
            ibmMQRCCFMap.put( 3313, "MQRCCF_REMOTE_Q_NAME_ERROR");
            ibmMQRCCFMap.put( 3314, "MQRCCF_DURABILITY_NOT_ALLOWED");
            ibmMQRCCFMap.put( 3315, "MQRCCF_HOBJ_ERROR");
            ibmMQRCCFMap.put( 3316, "MQRCCF_DEST_NAME_ERROR");
            ibmMQRCCFMap.put( 3317, "MQRCCF_INVALID_DESTINATION");
            ibmMQRCCFMap.put( 3318, "MQRCCF_PUBSUB_INHIBITED");
            ibmMQRCCFMap.put( 3319, "MQRCCF_GROUPUR_CHECKS_FAILED");
            ibmMQRCCFMap.put( 3320, "MQRCCF_COMM_INFO_TYPE_ERROR");
            ibmMQRCCFMap.put( 3321, "MQRCCF_USE_CLIENT_ID_ERROR");
            ibmMQRCCFMap.put( 3322, "MQRCCF_CLIENT_ID_NOT_FOUND");
            ibmMQRCCFMap.put( 3323, "MQRCCF_CLIENT_ID_ERROR");
            ibmMQRCCFMap.put( 3324, "MQRCCF_PORT_IN_USE");
            ibmMQRCCFMap.put( 3325, "MQRCCF_SSL_ALT_PROVIDER_REQD");
            ibmMQRCCFMap.put( 3326, "MQRCCF_CHLAUTH_TYPE_ERROR");
            ibmMQRCCFMap.put( 3327, "MQRCCF_CHLAUTH_ACTION_ERROR");
            ibmMQRCCFMap.put( 3328, "MQRCCF_POLICY_NOT_FOUND");
            ibmMQRCCFMap.put( 3329, "MQRCCF_ENCRYPTION_ALG_ERROR");
            ibmMQRCCFMap.put( 3330, "MQRCCF_SIGNATURE_ALG_ERROR");
            ibmMQRCCFMap.put( 3331, "MQRCCF_TOLERATION_POL_ERROR");
            ibmMQRCCFMap.put( 3332, "MQRCCF_POLICY_VERSION_ERROR");
            ibmMQRCCFMap.put( 3333, "MQRCCF_RECIPIENT_DN_MISSING");
            ibmMQRCCFMap.put( 3334, "MQRCCF_POLICY_NAME_MISSING");
            ibmMQRCCFMap.put( 3335, "MQRCCF_CHLAUTH_USERSRC_ERROR");
            ibmMQRCCFMap.put( 3336, "MQRCCF_WRONG_CHLAUTH_TYPE");
            ibmMQRCCFMap.put( 3337, "MQRCCF_CHLAUTH_ALREADY_EXISTS");
            ibmMQRCCFMap.put( 3338, "MQRCCF_CHLAUTH_NOT_FOUND");
            ibmMQRCCFMap.put( 3339, "MQRCCF_WRONG_CHLAUTH_ACTION");
            ibmMQRCCFMap.put( 3340, "MQRCCF_WRONG_CHLAUTH_USERSRC");
            ibmMQRCCFMap.put( 3341, "MQRCCF_CHLAUTH_WARN_ERROR");
            ibmMQRCCFMap.put( 3342, "MQRCCF_WRONG_CHLAUTH_MATCH");
            ibmMQRCCFMap.put( 3343, "MQRCCF_IPADDR_RANGE_CONFLICT");
            ibmMQRCCFMap.put( 3344, "MQRCCF_CHLAUTH_MAX_EXCEEDED");
            ibmMQRCCFMap.put( 3345, "MQRCCF_IPADDR_ERROR");
            ibmMQRCCFMap.put( 3345, "MQRCCF_ADDRESS_ERROR");
            ibmMQRCCFMap.put( 3346, "MQRCCF_IPADDR_RANGE_ERROR");
            ibmMQRCCFMap.put( 3347, "MQRCCF_PROFILE_NAME_MISSING");
            ibmMQRCCFMap.put( 3348, "MQRCCF_CHLAUTH_CLNTUSER_ERROR");
            ibmMQRCCFMap.put( 3349, "MQRCCF_CHLAUTH_NAME_ERROR");
            ibmMQRCCFMap.put( 3350, "MQRCCF_CHLAUTH_RUNCHECK_ERROR");
            ibmMQRCCFMap.put( 3351, "MQRCCF_CF_STRUC_ALREADY_FAILED");
            ibmMQRCCFMap.put( 3352, "MQRCCF_CFCONLOS_CHECKS_FAILED");
            ibmMQRCCFMap.put( 3353, "MQRCCF_SUITE_B_ERROR");
            ibmMQRCCFMap.put( 3354, "MQRCCF_CHANNEL_NOT_STARTED");
            ibmMQRCCFMap.put( 3355, "MQRCCF_CUSTOM_ERROR");
            ibmMQRCCFMap.put( 3356, "MQRCCF_BACKLOG_OUT_OF_RANGE");
            ibmMQRCCFMap.put( 3357, "MQRCCF_CHLAUTH_DISABLED");
            ibmMQRCCFMap.put( 3358, "MQRCCF_SMDS_REQUIRES_DSGROUP");
            ibmMQRCCFMap.put( 3359, "MQRCCF_PSCLUS_DISABLED_TOPDEF");
            ibmMQRCCFMap.put( 3360, "MQRCCF_PSCLUS_TOPIC_EXISTS");
            ibmMQRCCFMap.put( 3361, "MQRCCF_SSL_CIPHER_SUITE_ERROR");
            ibmMQRCCFMap.put( 3362, "MQRCCF_SOCKET_ERROR");
            ibmMQRCCFMap.put( 3363, "MQRCCF_CLUS_XMIT_Q_USAGE_ERROR");
            ibmMQRCCFMap.put( 3364, "MQRCCF_CERT_VAL_POLICY_ERROR");
            ibmMQRCCFMap.put( 3366, "MQRCCF_REVDNS_DISABLED");
            ibmMQRCCFMap.put( 3367, "MQRCCF_CLROUTE_NOT_ALTERABLE");
            ibmMQRCCFMap.put( 3368, "MQRCCF_CLUSTER_TOPIC_CONFLICT");
            ibmMQRCCFMap.put( 3369, "MQRCCF_DEFCLXQ_MODEL_Q_ERROR");
            ibmMQRCCFMap.put( 3370, "MQRCCF_CHLAUTH_CHKCLI_ERROR");
            ibmMQRCCFMap.put( 3371, "MQRCCF_CERT_LABEL_NOT_ALLOWED");
            ibmMQRCCFMap.put( 3372, "MQRCCF_Q_MGR_ATTR_CONFLICT");
            ibmMQRCCFMap.put( 3373, "MQRCCF_ENTITY_TYPE_MISSING");
            ibmMQRCCFMap.put( 4001, "MQRCCF_OBJECT_ALREADY_EXISTS");
            ibmMQRCCFMap.put( 4002, "MQRCCF_OBJECT_WRONG_TYPE");
            ibmMQRCCFMap.put( 4003, "MQRCCF_LIKE_OBJECT_WRONG_TYPE");
            ibmMQRCCFMap.put( 4004, "MQRCCF_OBJECT_OPEN");
            ibmMQRCCFMap.put( 4005, "MQRCCF_ATTR_VALUE_ERROR");
            ibmMQRCCFMap.put( 4006, "MQRCCF_UNKNOWN_Q_MGR");
            ibmMQRCCFMap.put( 4007, "MQRCCF_Q_WRONG_TYPE");
            ibmMQRCCFMap.put( 4008, "MQRCCF_OBJECT_NAME_ERROR");
            ibmMQRCCFMap.put( 4009, "MQRCCF_ALLOCATE_FAILED");
            ibmMQRCCFMap.put( 4010, "MQRCCF_HOST_NOT_AVAILABLE");
            ibmMQRCCFMap.put( 4011, "MQRCCF_CONFIGURATION_ERROR");
            ibmMQRCCFMap.put( 4012, "MQRCCF_CONNECTION_REFUSED");
            ibmMQRCCFMap.put( 4013, "MQRCCF_ENTRY_ERROR");
            ibmMQRCCFMap.put( 4014, "MQRCCF_SEND_FAILED");
            ibmMQRCCFMap.put( 4015, "MQRCCF_RECEIVED_DATA_ERROR");
            ibmMQRCCFMap.put( 4016, "MQRCCF_RECEIVE_FAILED");
            ibmMQRCCFMap.put( 4017, "MQRCCF_CONNECTION_CLOSED");
            ibmMQRCCFMap.put( 4018, "MQRCCF_NO_STORAGE");
            ibmMQRCCFMap.put( 4019, "MQRCCF_NO_COMMS_MANAGER");
            ibmMQRCCFMap.put( 4020, "MQRCCF_LISTENER_NOT_STARTED");
            ibmMQRCCFMap.put( 4024, "MQRCCF_BIND_FAILED");
            ibmMQRCCFMap.put( 4025, "MQRCCF_CHANNEL_INDOUBT");
            ibmMQRCCFMap.put( 4026, "MQRCCF_MQCONN_FAILED");
            ibmMQRCCFMap.put( 4027, "MQRCCF_MQOPEN_FAILED");
            ibmMQRCCFMap.put( 4028, "MQRCCF_MQGET_FAILED");
            ibmMQRCCFMap.put( 4029, "MQRCCF_MQPUT_FAILED");
            ibmMQRCCFMap.put( 4030, "MQRCCF_PING_ERROR");
            ibmMQRCCFMap.put( 4031, "MQRCCF_CHANNEL_IN_USE");
            ibmMQRCCFMap.put( 4032, "MQRCCF_CHANNEL_NOT_FOUND");
            ibmMQRCCFMap.put( 4033, "MQRCCF_UNKNOWN_REMOTE_CHANNEL");
            ibmMQRCCFMap.put( 4034, "MQRCCF_REMOTE_QM_UNAVAILABLE");
            ibmMQRCCFMap.put( 4035, "MQRCCF_REMOTE_QM_TERMINATING");
            ibmMQRCCFMap.put( 4036, "MQRCCF_MQINQ_FAILED");
            ibmMQRCCFMap.put( 4037, "MQRCCF_NOT_XMIT_Q");
            ibmMQRCCFMap.put( 4038, "MQRCCF_CHANNEL_DISABLED");
            ibmMQRCCFMap.put( 4039, "MQRCCF_USER_EXIT_NOT_AVAILABLE");
            ibmMQRCCFMap.put( 4040, "MQRCCF_COMMIT_FAILED");
            ibmMQRCCFMap.put( 4041, "MQRCCF_WRONG_CHANNEL_TYPE");
            ibmMQRCCFMap.put( 4042, "MQRCCF_CHANNEL_ALREADY_EXISTS");
            ibmMQRCCFMap.put( 4043, "MQRCCF_DATA_TOO_LARGE");
            ibmMQRCCFMap.put( 4044, "MQRCCF_CHANNEL_NAME_ERROR");
            ibmMQRCCFMap.put( 4045, "MQRCCF_XMIT_Q_NAME_ERROR");
            ibmMQRCCFMap.put( 4047, "MQRCCF_MCA_NAME_ERROR");
            ibmMQRCCFMap.put( 4048, "MQRCCF_SEND_EXIT_NAME_ERROR");
            ibmMQRCCFMap.put( 4049, "MQRCCF_SEC_EXIT_NAME_ERROR");
            ibmMQRCCFMap.put( 4050, "MQRCCF_MSG_EXIT_NAME_ERROR");
            ibmMQRCCFMap.put( 4051, "MQRCCF_RCV_EXIT_NAME_ERROR");
            ibmMQRCCFMap.put( 4052, "MQRCCF_XMIT_Q_NAME_WRONG_TYPE");
            ibmMQRCCFMap.put( 4053, "MQRCCF_MCA_NAME_WRONG_TYPE");
            ibmMQRCCFMap.put( 4054, "MQRCCF_DISC_INT_WRONG_TYPE");
            ibmMQRCCFMap.put( 4055, "MQRCCF_SHORT_RETRY_WRONG_TYPE");
            ibmMQRCCFMap.put( 4056, "MQRCCF_SHORT_TIMER_WRONG_TYPE");
            ibmMQRCCFMap.put( 4057, "MQRCCF_LONG_RETRY_WRONG_TYPE");
            ibmMQRCCFMap.put( 4058, "MQRCCF_LONG_TIMER_WRONG_TYPE");
            ibmMQRCCFMap.put( 4059, "MQRCCF_PUT_AUTH_WRONG_TYPE");
            ibmMQRCCFMap.put( 4060, "MQRCCF_KEEP_ALIVE_INT_ERROR");
            ibmMQRCCFMap.put( 4061, "MQRCCF_MISSING_CONN_NAME");
            ibmMQRCCFMap.put( 4062, "MQRCCF_CONN_NAME_ERROR");
            ibmMQRCCFMap.put( 4063, "MQRCCF_MQSET_FAILED");
            ibmMQRCCFMap.put( 4064, "MQRCCF_CHANNEL_NOT_ACTIVE");
            ibmMQRCCFMap.put( 4065, "MQRCCF_TERMINATED_BY_SEC_EXIT");
            ibmMQRCCFMap.put( 4067, "MQRCCF_DYNAMIC_Q_SCOPE_ERROR");
            ibmMQRCCFMap.put( 4068, "MQRCCF_CELL_DIR_NOT_AVAILABLE");
            ibmMQRCCFMap.put( 4069, "MQRCCF_MR_COUNT_ERROR");
            ibmMQRCCFMap.put( 4070, "MQRCCF_MR_COUNT_WRONG_TYPE");
            ibmMQRCCFMap.put( 4071, "MQRCCF_MR_EXIT_NAME_ERROR");
            ibmMQRCCFMap.put( 4072, "MQRCCF_MR_EXIT_NAME_WRONG_TYPE");
            ibmMQRCCFMap.put( 4073, "MQRCCF_MR_INTERVAL_ERROR");
            ibmMQRCCFMap.put( 4074, "MQRCCF_MR_INTERVAL_WRONG_TYPE");
            ibmMQRCCFMap.put( 4075, "MQRCCF_NPM_SPEED_ERROR");
            ibmMQRCCFMap.put( 4076, "MQRCCF_NPM_SPEED_WRONG_TYPE");
            ibmMQRCCFMap.put( 4077, "MQRCCF_HB_INTERVAL_ERROR");
            ibmMQRCCFMap.put( 4078, "MQRCCF_HB_INTERVAL_WRONG_TYPE");
            ibmMQRCCFMap.put( 4079, "MQRCCF_CHAD_ERROR");
            ibmMQRCCFMap.put( 4080, "MQRCCF_CHAD_WRONG_TYPE");
            ibmMQRCCFMap.put( 4081, "MQRCCF_CHAD_EVENT_ERROR");
            ibmMQRCCFMap.put( 4082, "MQRCCF_CHAD_EVENT_WRONG_TYPE");
            ibmMQRCCFMap.put( 4083, "MQRCCF_CHAD_EXIT_ERROR");
            ibmMQRCCFMap.put( 4084, "MQRCCF_CHAD_EXIT_WRONG_TYPE");
            ibmMQRCCFMap.put( 4085, "MQRCCF_SUPPRESSED_BY_EXIT");
            ibmMQRCCFMap.put( 4086, "MQRCCF_BATCH_INT_ERROR");
            ibmMQRCCFMap.put( 4087, "MQRCCF_BATCH_INT_WRONG_TYPE");
            ibmMQRCCFMap.put( 4088, "MQRCCF_NET_PRIORITY_ERROR");
            ibmMQRCCFMap.put( 4089, "MQRCCF_NET_PRIORITY_WRONG_TYPE");
            ibmMQRCCFMap.put( 4090, "MQRCCF_CHANNEL_CLOSED");
            ibmMQRCCFMap.put( 4091, "MQRCCF_Q_STATUS_NOT_FOUND");
            ibmMQRCCFMap.put( 4092, "MQRCCF_SSL_CIPHER_SPEC_ERROR");
            ibmMQRCCFMap.put( 4093, "MQRCCF_SSL_PEER_NAME_ERROR");
            ibmMQRCCFMap.put( 4094, "MQRCCF_SSL_CLIENT_AUTH_ERROR");
            ibmMQRCCFMap.put( 4095, "MQRCCF_RETAINED_NOT_SUPPORTED");
        }
        if( ibmMQCLRTMap == null ) {
            ibmMQCLRTMap = new HashMap<>();
            ibmMQCLRTMap.put( 1, "MQCLRT_RETAINED");
        }
        if( ibmMQCFILMap == null ) {
            ibmMQCFILMap = new HashMap<>();
            ibmMQCFILMap.put( 16, "MQCFIL_STRUC_LENGTH_FIXED");
        }
        if( ibmMQEVOMap == null ) {
            ibmMQEVOMap = new HashMap<>();
            ibmMQEVOMap.put( 0, "MQEVO_OTHER");
            ibmMQEVOMap.put( 1, "MQEVO_CONSOLE");
            ibmMQEVOMap.put( 2, "MQEVO_INIT");
            ibmMQEVOMap.put( 3, "MQEVO_MSG");
            ibmMQEVOMap.put( 4, "MQEVO_MQSET");
            ibmMQEVOMap.put( 5, "MQEVO_INTERNAL");
            ibmMQEVOMap.put( 6, "MQEVO_MQSUB");
            ibmMQEVOMap.put( 7, "MQEVO_CTLMSG");
        }
        if( ibmMQMap == null ) {
            ibmMQMap = new HashMap<>();
            ibmMQMap.put( 36, "MQ_ARCHIVE_PFX_LENGTH");
            ibmMQMap.put( 8, "MQ_ARCHIVE_UNIT_LENGTH");
            ibmMQMap.put( 4, "MQ_ASID_LENGTH");
            ibmMQMap.put( 48, "MQ_AUTH_PROFILE_NAME_LENGTH");
            ibmMQMap.put( 12, "MQ_CF_LEID_LENGTH");
            ibmMQMap.put( 32768, "MQ_COMMAND_MQSC_LENGTH");
            ibmMQMap.put( 44, "MQ_DATA_SET_NAME_LENGTH");
            ibmMQMap.put( 4, "MQ_DB2_NAME_LENGTH");
            ibmMQMap.put( 8, "MQ_DSG_NAME_LENGTH");
            ibmMQMap.put( 64, "MQ_ENTITY_NAME_LENGTH");
            ibmMQMap.put( 96, "MQ_ENV_INFO_LENGTH");
            ibmMQMap.put( 264, "MQ_GROUP_ADDRESS_LENGTH");
            ibmMQMap.put( 48, "MQ_IP_ADDRESS_LENGTH");
            ibmMQMap.put( 8, "MQ_LOG_CORREL_ID_LENGTH");
            ibmMQMap.put( 24, "MQ_LOG_EXTENT_NAME_LENGTH");
            ibmMQMap.put( 1024, "MQ_LOG_PATH_LENGTH");
            ibmMQMap.put( 12, "MQ_LRSN_LENGTH");
            ibmMQMap.put( 8, "MQ_ORIGIN_NAME_LENGTH");
            ibmMQMap.put( 8, "MQ_PSB_NAME_LENGTH");
            ibmMQMap.put( 8, "MQ_PST_ID_LENGTH");
            ibmMQMap.put( 4, "MQ_Q_MGR_CPF_LENGTH");
            ibmMQMap.put( 24, "MQ_RESPONSE_ID_LENGTH");
            ibmMQMap.put( 16, "MQ_RBA_LENGTH");
            ibmMQMap.put( 40, "MQ_SECURITY_PROFILE_LENGTH");
            ibmMQMap.put( 48, "MQ_SERVICE_COMPONENT_LENGTH");
            ibmMQMap.put( 10240, "MQ_SUB_NAME_LENGTH");
            ibmMQMap.put( 32, "MQ_SYSP_SERVICE_LENGTH");
            ibmMQMap.put( 8, "MQ_SYSTEM_NAME_LENGTH");
            ibmMQMap.put( 8, "MQ_TASK_NUMBER_LENGTH");
            ibmMQMap.put( 4, "MQ_TPIPE_PFX_LENGTH");
            ibmMQMap.put( 256, "MQ_UOW_ID_LENGTH");
            ibmMQMap.put( 10240, "MQ_USER_DATA_LENGTH");
            ibmMQMap.put( 6, "MQ_VOLSER_LENGTH");
            ibmMQMap.put( 4, "MQ_REMOTE_PRODUCT_LENGTH");
            ibmMQMap.put( 8, "MQ_REMOTE_VERSION_LENGTH");
        }
        if( ibmMQIDOMap == null ) {
            ibmMQIDOMap = new HashMap<>();
            ibmMQIDOMap.put( 1, "MQIDO_COMMIT");
            ibmMQIDOMap.put( 2, "MQIDO_BACKOUT");
        }
        if( ibmMQCFOMap == null ) {
            ibmMQCFOMap = new HashMap<>();
            ibmMQCFOMap.put( 1, "MQCFO_REFRESH_REPOSITORY_YES");
            ibmMQCFOMap.put( 0, "MQCFO_REFRESH_REPOSITORY_NO");
            ibmMQCFOMap.put( 1, "MQCFO_REMOVE_QUEUES_YES");
            ibmMQCFOMap.put( 0, "MQCFO_REMOVE_QUEUES_NO");
        }
        if( ibmMQCAMOMap == null ) {
            ibmMQCAMOMap = new HashMap<>();
            ibmMQCAMOMap.put( 2701, "MQCAMO_FIRST");
            ibmMQCAMOMap.put( 2701, "MQCAMO_CLOSE_DATE");
            ibmMQCAMOMap.put( 2702, "MQCAMO_CLOSE_TIME");
            ibmMQCAMOMap.put( 2703, "MQCAMO_CONN_DATE");
            ibmMQCAMOMap.put( 2704, "MQCAMO_CONN_TIME");
            ibmMQCAMOMap.put( 2705, "MQCAMO_DISC_DATE");
            ibmMQCAMOMap.put( 2706, "MQCAMO_DISC_TIME");
            ibmMQCAMOMap.put( 2707, "MQCAMO_END_DATE");
            ibmMQCAMOMap.put( 2708, "MQCAMO_END_TIME");
            ibmMQCAMOMap.put( 2709, "MQCAMO_OPEN_DATE");
            ibmMQCAMOMap.put( 2710, "MQCAMO_OPEN_TIME");
            ibmMQCAMOMap.put( 2711, "MQCAMO_START_DATE");
            ibmMQCAMOMap.put( 2712, "MQCAMO_START_TIME");
            ibmMQCAMOMap.put( 2712, "MQCAMO_LAST_USED");
        }
        if( ibmMQNSHMap == null ) {
            ibmMQNSHMap = new HashMap<>();
            ibmMQNSHMap.put( 0, "MQNSH_NONE");
        }
        if( ibmMQCHSRMap == null ) {
            ibmMQCHSRMap = new HashMap<>();
            ibmMQCHSRMap.put( 0, "MQCHSR_STOP_NOT_REQUESTED");
            ibmMQCHSRMap.put( 1, "MQCHSR_STOP_REQUESTED");
        }
        if( ibmMQSCOMap == null ) {
            ibmMQSCOMap = new HashMap<>();
            ibmMQSCOMap.put( 1, "MQSCO_Q_MGR");
            ibmMQSCOMap.put( 2, "MQSCO_CELL");
        }
        if( ibmMQPAGECLASMap == null ) {
            ibmMQPAGECLASMap = new HashMap<>();
            ibmMQPAGECLASMap.put( 0, "MQPAGECLAS_4KB");
            ibmMQPAGECLASMap.put( 1, "MQPAGECLAS_FIXED4KB");
        }
        if( ibmMQCFIFMap == null ) {
            ibmMQCFIFMap = new HashMap<>();
            ibmMQCFIFMap.put( 20, "MQCFIF_STRUC_LENGTH");
        }
        if( ibmMQIACFMap == null ) {
            ibmMQIACFMap = new HashMap<>();
            ibmMQIACFMap.put( 1001, "MQIACF_FIRST");
            ibmMQIACFMap.put( 1001, "MQIACF_Q_MGR_ATTRS");
            ibmMQIACFMap.put( 1002, "MQIACF_Q_ATTRS");
            ibmMQIACFMap.put( 1003, "MQIACF_PROCESS_ATTRS");
            ibmMQIACFMap.put( 1004, "MQIACF_NAMELIST_ATTRS");
            ibmMQIACFMap.put( 1005, "MQIACF_FORCE");
            ibmMQIACFMap.put( 1006, "MQIACF_REPLACE");
            ibmMQIACFMap.put( 1007, "MQIACF_PURGE");
            ibmMQIACFMap.put( 1008, "MQIACF_QUIESCE");
            ibmMQIACFMap.put( 1008, "MQIACF_MODE");
            ibmMQIACFMap.put( 1009, "MQIACF_ALL");
            ibmMQIACFMap.put( 1010, "MQIACF_EVENT_APPL_TYPE");
            ibmMQIACFMap.put( 1011, "MQIACF_EVENT_ORIGIN");
            ibmMQIACFMap.put( 1012, "MQIACF_PARAMETER_ID");
            ibmMQIACFMap.put( 1013, "MQIACF_ERROR_ID");
            ibmMQIACFMap.put( 1013, "MQIACF_ERROR_IDENTIFIER");
            ibmMQIACFMap.put( 1014, "MQIACF_SELECTOR");
            ibmMQIACFMap.put( 1015, "MQIACF_CHANNEL_ATTRS");
            ibmMQIACFMap.put( 1016, "MQIACF_OBJECT_TYPE");
            ibmMQIACFMap.put( 1017, "MQIACF_ESCAPE_TYPE");
            ibmMQIACFMap.put( 1018, "MQIACF_ERROR_OFFSET");
            ibmMQIACFMap.put( 1019, "MQIACF_AUTH_INFO_ATTRS");
            ibmMQIACFMap.put( 1020, "MQIACF_REASON_QUALIFIER");
            ibmMQIACFMap.put( 1021, "MQIACF_COMMAND");
            ibmMQIACFMap.put( 1022, "MQIACF_OPEN_OPTIONS");
            ibmMQIACFMap.put( 1023, "MQIACF_OPEN_TYPE");
            ibmMQIACFMap.put( 1024, "MQIACF_PROCESS_ID");
            ibmMQIACFMap.put( 1025, "MQIACF_THREAD_ID");
            ibmMQIACFMap.put( 1026, "MQIACF_Q_STATUS_ATTRS");
            ibmMQIACFMap.put( 1027, "MQIACF_UNCOMMITTED_MSGS");
            ibmMQIACFMap.put( 1028, "MQIACF_HANDLE_STATE");
            ibmMQIACFMap.put( 1070, "MQIACF_AUX_ERROR_DATA_INT_1");
            ibmMQIACFMap.put( 1071, "MQIACF_AUX_ERROR_DATA_INT_2");
            ibmMQIACFMap.put( 1072, "MQIACF_CONV_REASON_CODE");
            ibmMQIACFMap.put( 1073, "MQIACF_BRIDGE_TYPE");
            ibmMQIACFMap.put( 1074, "MQIACF_INQUIRY");
            ibmMQIACFMap.put( 1075, "MQIACF_WAIT_INTERVAL");
            ibmMQIACFMap.put( 1076, "MQIACF_OPTIONS");
            ibmMQIACFMap.put( 1077, "MQIACF_BROKER_OPTIONS");
            ibmMQIACFMap.put( 1078, "MQIACF_REFRESH_TYPE");
            ibmMQIACFMap.put( 1079, "MQIACF_SEQUENCE_NUMBER");
            ibmMQIACFMap.put( 1080, "MQIACF_INTEGER_DATA");
            ibmMQIACFMap.put( 1081, "MQIACF_REGISTRATION_OPTIONS");
            ibmMQIACFMap.put( 1082, "MQIACF_PUBLICATION_OPTIONS");
            ibmMQIACFMap.put( 1083, "MQIACF_CLUSTER_INFO");
            ibmMQIACFMap.put( 1084, "MQIACF_Q_MGR_DEFINITION_TYPE");
            ibmMQIACFMap.put( 1085, "MQIACF_Q_MGR_TYPE");
            ibmMQIACFMap.put( 1086, "MQIACF_ACTION");
            ibmMQIACFMap.put( 1087, "MQIACF_SUSPEND");
            ibmMQIACFMap.put( 1088, "MQIACF_BROKER_COUNT");
            ibmMQIACFMap.put( 1089, "MQIACF_APPL_COUNT");
            ibmMQIACFMap.put( 1090, "MQIACF_ANONYMOUS_COUNT");
            ibmMQIACFMap.put( 1091, "MQIACF_REG_REG_OPTIONS");
            ibmMQIACFMap.put( 1092, "MQIACF_DELETE_OPTIONS");
            ibmMQIACFMap.put( 1093, "MQIACF_CLUSTER_Q_MGR_ATTRS");
            ibmMQIACFMap.put( 1094, "MQIACF_REFRESH_INTERVAL");
            ibmMQIACFMap.put( 1095, "MQIACF_REFRESH_REPOSITORY");
            ibmMQIACFMap.put( 1096, "MQIACF_REMOVE_QUEUES");
            ibmMQIACFMap.put( 1098, "MQIACF_OPEN_INPUT_TYPE");
            ibmMQIACFMap.put( 1099, "MQIACF_OPEN_OUTPUT");
            ibmMQIACFMap.put( 1100, "MQIACF_OPEN_SET");
            ibmMQIACFMap.put( 1101, "MQIACF_OPEN_INQUIRE");
            ibmMQIACFMap.put( 1102, "MQIACF_OPEN_BROWSE");
            ibmMQIACFMap.put( 1103, "MQIACF_Q_STATUS_TYPE");
            ibmMQIACFMap.put( 1104, "MQIACF_Q_HANDLE");
            ibmMQIACFMap.put( 1105, "MQIACF_Q_STATUS");
            ibmMQIACFMap.put( 1106, "MQIACF_SECURITY_TYPE");
            ibmMQIACFMap.put( 1107, "MQIACF_CONNECTION_ATTRS");
            ibmMQIACFMap.put( 1108, "MQIACF_CONNECT_OPTIONS");
            ibmMQIACFMap.put( 1110, "MQIACF_CONN_INFO_TYPE");
            ibmMQIACFMap.put( 1111, "MQIACF_CONN_INFO_CONN");
            ibmMQIACFMap.put( 1112, "MQIACF_CONN_INFO_HANDLE");
            ibmMQIACFMap.put( 1113, "MQIACF_CONN_INFO_ALL");
            ibmMQIACFMap.put( 1114, "MQIACF_AUTH_PROFILE_ATTRS");
            ibmMQIACFMap.put( 1115, "MQIACF_AUTHORIZATION_LIST");
            ibmMQIACFMap.put( 1116, "MQIACF_AUTH_ADD_AUTHS");
            ibmMQIACFMap.put( 1117, "MQIACF_AUTH_REMOVE_AUTHS");
            ibmMQIACFMap.put( 1118, "MQIACF_ENTITY_TYPE");
            ibmMQIACFMap.put( 1120, "MQIACF_COMMAND_INFO");
            ibmMQIACFMap.put( 1121, "MQIACF_CMDSCOPE_Q_MGR_COUNT");
            ibmMQIACFMap.put( 1122, "MQIACF_Q_MGR_SYSTEM");
            ibmMQIACFMap.put( 1123, "MQIACF_Q_MGR_EVENT");
            ibmMQIACFMap.put( 1124, "MQIACF_Q_MGR_DQM");
            ibmMQIACFMap.put( 1125, "MQIACF_Q_MGR_CLUSTER");
            ibmMQIACFMap.put( 1126, "MQIACF_QSG_DISPS");
            ibmMQIACFMap.put( 1128, "MQIACF_UOW_STATE");
            ibmMQIACFMap.put( 1129, "MQIACF_SECURITY_ITEM");
            ibmMQIACFMap.put( 1130, "MQIACF_CF_STRUC_STATUS");
            ibmMQIACFMap.put( 1132, "MQIACF_UOW_TYPE");
            ibmMQIACFMap.put( 1133, "MQIACF_CF_STRUC_ATTRS");
            ibmMQIACFMap.put( 1134, "MQIACF_EXCLUDE_INTERVAL");
            ibmMQIACFMap.put( 1135, "MQIACF_CF_STATUS_TYPE");
            ibmMQIACFMap.put( 1136, "MQIACF_CF_STATUS_SUMMARY");
            ibmMQIACFMap.put( 1137, "MQIACF_CF_STATUS_CONNECT");
            ibmMQIACFMap.put( 1138, "MQIACF_CF_STATUS_BACKUP");
            ibmMQIACFMap.put( 1139, "MQIACF_CF_STRUC_TYPE");
            ibmMQIACFMap.put( 1140, "MQIACF_CF_STRUC_SIZE_MAX");
            ibmMQIACFMap.put( 1141, "MQIACF_CF_STRUC_SIZE_USED");
            ibmMQIACFMap.put( 1142, "MQIACF_CF_STRUC_ENTRIES_MAX");
            ibmMQIACFMap.put( 1143, "MQIACF_CF_STRUC_ENTRIES_USED");
            ibmMQIACFMap.put( 1144, "MQIACF_CF_STRUC_BACKUP_SIZE");
            ibmMQIACFMap.put( 1145, "MQIACF_MOVE_TYPE");
            ibmMQIACFMap.put( 1146, "MQIACF_MOVE_TYPE_MOVE");
            ibmMQIACFMap.put( 1147, "MQIACF_MOVE_TYPE_ADD");
            ibmMQIACFMap.put( 1148, "MQIACF_Q_MGR_NUMBER");
            ibmMQIACFMap.put( 1149, "MQIACF_Q_MGR_STATUS");
            ibmMQIACFMap.put( 1150, "MQIACF_DB2_CONN_STATUS");
            ibmMQIACFMap.put( 1151, "MQIACF_SECURITY_ATTRS");
            ibmMQIACFMap.put( 1152, "MQIACF_SECURITY_TIMEOUT");
            ibmMQIACFMap.put( 1153, "MQIACF_SECURITY_INTERVAL");
            ibmMQIACFMap.put( 1154, "MQIACF_SECURITY_SWITCH");
            ibmMQIACFMap.put( 1155, "MQIACF_SECURITY_SETTING");
            ibmMQIACFMap.put( 1156, "MQIACF_STORAGE_CLASS_ATTRS");
            ibmMQIACFMap.put( 1157, "MQIACF_USAGE_TYPE");
            ibmMQIACFMap.put( 1158, "MQIACF_BUFFER_POOL_ID");
            ibmMQIACFMap.put( 1159, "MQIACF_USAGE_TOTAL_PAGES");
            ibmMQIACFMap.put( 1160, "MQIACF_USAGE_UNUSED_PAGES");
            ibmMQIACFMap.put( 1161, "MQIACF_USAGE_PERSIST_PAGES");
            ibmMQIACFMap.put( 1162, "MQIACF_USAGE_NONPERSIST_PAGES");
            ibmMQIACFMap.put( 1163, "MQIACF_USAGE_RESTART_EXTENTS");
            ibmMQIACFMap.put( 1164, "MQIACF_USAGE_EXPAND_COUNT");
            ibmMQIACFMap.put( 1165, "MQIACF_PAGESET_STATUS");
            ibmMQIACFMap.put( 1166, "MQIACF_USAGE_TOTAL_BUFFERS");
            ibmMQIACFMap.put( 1167, "MQIACF_USAGE_DATA_SET_TYPE");
            ibmMQIACFMap.put( 1168, "MQIACF_USAGE_PAGESET");
            ibmMQIACFMap.put( 1169, "MQIACF_USAGE_DATA_SET");
            ibmMQIACFMap.put( 1170, "MQIACF_USAGE_BUFFER_POOL");
            ibmMQIACFMap.put( 1171, "MQIACF_MOVE_COUNT");
            ibmMQIACFMap.put( 1172, "MQIACF_EXPIRY_Q_COUNT");
            ibmMQIACFMap.put( 1173, "MQIACF_CONFIGURATION_OBJECTS");
            ibmMQIACFMap.put( 1174, "MQIACF_CONFIGURATION_EVENTS");
            ibmMQIACFMap.put( 1175, "MQIACF_SYSP_TYPE");
            ibmMQIACFMap.put( 1176, "MQIACF_SYSP_DEALLOC_INTERVAL");
            ibmMQIACFMap.put( 1177, "MQIACF_SYSP_MAX_ARCHIVE");
            ibmMQIACFMap.put( 1178, "MQIACF_SYSP_MAX_READ_TAPES");
            ibmMQIACFMap.put( 1179, "MQIACF_SYSP_IN_BUFFER_SIZE");
            ibmMQIACFMap.put( 1180, "MQIACF_SYSP_OUT_BUFFER_SIZE");
            ibmMQIACFMap.put( 1181, "MQIACF_SYSP_OUT_BUFFER_COUNT");
            ibmMQIACFMap.put( 1182, "MQIACF_SYSP_ARCHIVE");
            ibmMQIACFMap.put( 1183, "MQIACF_SYSP_DUAL_ACTIVE");
            ibmMQIACFMap.put( 1184, "MQIACF_SYSP_DUAL_ARCHIVE");
            ibmMQIACFMap.put( 1185, "MQIACF_SYSP_DUAL_BSDS");
            ibmMQIACFMap.put( 1186, "MQIACF_SYSP_MAX_CONNS");
            ibmMQIACFMap.put( 1187, "MQIACF_SYSP_MAX_CONNS_FORE");
            ibmMQIACFMap.put( 1188, "MQIACF_SYSP_MAX_CONNS_BACK");
            ibmMQIACFMap.put( 1189, "MQIACF_SYSP_EXIT_INTERVAL");
            ibmMQIACFMap.put( 1190, "MQIACF_SYSP_EXIT_TASKS");
            ibmMQIACFMap.put( 1191, "MQIACF_SYSP_CHKPOINT_COUNT");
            ibmMQIACFMap.put( 1192, "MQIACF_SYSP_OTMA_INTERVAL");
            ibmMQIACFMap.put( 1193, "MQIACF_SYSP_Q_INDEX_DEFER");
            ibmMQIACFMap.put( 1194, "MQIACF_SYSP_DB2_TASKS");
            ibmMQIACFMap.put( 1195, "MQIACF_SYSP_RESLEVEL_AUDIT");
            ibmMQIACFMap.put( 1196, "MQIACF_SYSP_ROUTING_CODE");
            ibmMQIACFMap.put( 1197, "MQIACF_SYSP_SMF_ACCOUNTING");
            ibmMQIACFMap.put( 1198, "MQIACF_SYSP_SMF_STATS");
            ibmMQIACFMap.put( 1199, "MQIACF_SYSP_SMF_INTERVAL");
            ibmMQIACFMap.put( 1200, "MQIACF_SYSP_TRACE_CLASS");
            ibmMQIACFMap.put( 1201, "MQIACF_SYSP_TRACE_SIZE");
            ibmMQIACFMap.put( 1202, "MQIACF_SYSP_WLM_INTERVAL");
            ibmMQIACFMap.put( 1203, "MQIACF_SYSP_ALLOC_UNIT");
            ibmMQIACFMap.put( 1204, "MQIACF_SYSP_ARCHIVE_RETAIN");
            ibmMQIACFMap.put( 1205, "MQIACF_SYSP_ARCHIVE_WTOR");
            ibmMQIACFMap.put( 1206, "MQIACF_SYSP_BLOCK_SIZE");
            ibmMQIACFMap.put( 1207, "MQIACF_SYSP_CATALOG");
            ibmMQIACFMap.put( 1208, "MQIACF_SYSP_COMPACT");
            ibmMQIACFMap.put( 1209, "MQIACF_SYSP_ALLOC_PRIMARY");
            ibmMQIACFMap.put( 1210, "MQIACF_SYSP_ALLOC_SECONDARY");
            ibmMQIACFMap.put( 1211, "MQIACF_SYSP_PROTECT");
            ibmMQIACFMap.put( 1212, "MQIACF_SYSP_QUIESCE_INTERVAL");
            ibmMQIACFMap.put( 1213, "MQIACF_SYSP_TIMESTAMP");
            ibmMQIACFMap.put( 1214, "MQIACF_SYSP_UNIT_ADDRESS");
            ibmMQIACFMap.put( 1215, "MQIACF_SYSP_UNIT_STATUS");
            ibmMQIACFMap.put( 1216, "MQIACF_SYSP_LOG_COPY");
            ibmMQIACFMap.put( 1217, "MQIACF_SYSP_LOG_USED");
            ibmMQIACFMap.put( 1218, "MQIACF_SYSP_LOG_SUSPEND");
            ibmMQIACFMap.put( 1219, "MQIACF_SYSP_OFFLOAD_STATUS");
            ibmMQIACFMap.put( 1220, "MQIACF_SYSP_TOTAL_LOGS");
            ibmMQIACFMap.put( 1221, "MQIACF_SYSP_FULL_LOGS");
            ibmMQIACFMap.put( 1222, "MQIACF_LISTENER_ATTRS");
            ibmMQIACFMap.put( 1223, "MQIACF_LISTENER_STATUS_ATTRS");
            ibmMQIACFMap.put( 1224, "MQIACF_SERVICE_ATTRS");
            ibmMQIACFMap.put( 1225, "MQIACF_SERVICE_STATUS_ATTRS");
            ibmMQIACFMap.put( 1226, "MQIACF_Q_TIME_INDICATOR");
            ibmMQIACFMap.put( 1227, "MQIACF_OLDEST_MSG_AGE");
            ibmMQIACFMap.put( 1228, "MQIACF_AUTH_OPTIONS");
            ibmMQIACFMap.put( 1229, "MQIACF_Q_MGR_STATUS_ATTRS");
            ibmMQIACFMap.put( 1230, "MQIACF_CONNECTION_COUNT");
            ibmMQIACFMap.put( 1231, "MQIACF_Q_MGR_FACILITY");
            ibmMQIACFMap.put( 1232, "MQIACF_CHINIT_STATUS");
            ibmMQIACFMap.put( 1233, "MQIACF_CMD_SERVER_STATUS");
            ibmMQIACFMap.put( 1234, "MQIACF_ROUTE_DETAIL");
            ibmMQIACFMap.put( 1235, "MQIACF_RECORDED_ACTIVITIES");
            ibmMQIACFMap.put( 1236, "MQIACF_MAX_ACTIVITIES");
            ibmMQIACFMap.put( 1237, "MQIACF_DISCONTINUITY_COUNT");
            ibmMQIACFMap.put( 1238, "MQIACF_ROUTE_ACCUMULATION");
            ibmMQIACFMap.put( 1239, "MQIACF_ROUTE_DELIVERY");
            ibmMQIACFMap.put( 1240, "MQIACF_OPERATION_TYPE");
            ibmMQIACFMap.put( 1241, "MQIACF_BACKOUT_COUNT");
            ibmMQIACFMap.put( 1242, "MQIACF_COMP_CODE");
            ibmMQIACFMap.put( 1243, "MQIACF_ENCODING");
            ibmMQIACFMap.put( 1244, "MQIACF_EXPIRY");
            ibmMQIACFMap.put( 1245, "MQIACF_FEEDBACK");
            ibmMQIACFMap.put( 1247, "MQIACF_MSG_FLAGS");
            ibmMQIACFMap.put( 1248, "MQIACF_MSG_LENGTH");
            ibmMQIACFMap.put( 1249, "MQIACF_MSG_TYPE");
            ibmMQIACFMap.put( 1250, "MQIACF_OFFSET");
            ibmMQIACFMap.put( 1251, "MQIACF_ORIGINAL_LENGTH");
            ibmMQIACFMap.put( 1252, "MQIACF_PERSISTENCE");
            ibmMQIACFMap.put( 1253, "MQIACF_PRIORITY");
            ibmMQIACFMap.put( 1254, "MQIACF_REASON_CODE");
            ibmMQIACFMap.put( 1255, "MQIACF_REPORT");
            ibmMQIACFMap.put( 1256, "MQIACF_VERSION");
            ibmMQIACFMap.put( 1257, "MQIACF_UNRECORDED_ACTIVITIES");
            ibmMQIACFMap.put( 1258, "MQIACF_MONITORING");
            ibmMQIACFMap.put( 1259, "MQIACF_ROUTE_FORWARDING");
            ibmMQIACFMap.put( 1260, "MQIACF_SERVICE_STATUS");
            ibmMQIACFMap.put( 1261, "MQIACF_Q_TYPES");
            ibmMQIACFMap.put( 1262, "MQIACF_USER_ID_SUPPORT");
            ibmMQIACFMap.put( 1263, "MQIACF_INTERFACE_VERSION");
            ibmMQIACFMap.put( 1264, "MQIACF_AUTH_SERVICE_ATTRS");
            ibmMQIACFMap.put( 1265, "MQIACF_USAGE_EXPAND_TYPE");
            ibmMQIACFMap.put( 1266, "MQIACF_SYSP_CLUSTER_CACHE");
            ibmMQIACFMap.put( 1267, "MQIACF_SYSP_DB2_BLOB_TASKS");
            ibmMQIACFMap.put( 1268, "MQIACF_SYSP_WLM_INT_UNITS");
            ibmMQIACFMap.put( 1269, "MQIACF_TOPIC_ATTRS");
            ibmMQIACFMap.put( 1271, "MQIACF_PUBSUB_PROPERTIES");
            ibmMQIACFMap.put( 1273, "MQIACF_DESTINATION_CLASS");
            ibmMQIACFMap.put( 1274, "MQIACF_DURABLE_SUBSCRIPTION");
            ibmMQIACFMap.put( 1275, "MQIACF_SUBSCRIPTION_SCOPE");
            ibmMQIACFMap.put( 1277, "MQIACF_VARIABLE_USER_ID");
            ibmMQIACFMap.put( 1280, "MQIACF_REQUEST_ONLY");
            ibmMQIACFMap.put( 1283, "MQIACF_PUB_PRIORITY");
            ibmMQIACFMap.put( 1287, "MQIACF_SUB_ATTRS");
            ibmMQIACFMap.put( 1288, "MQIACF_WILDCARD_SCHEMA");
            ibmMQIACFMap.put( 1289, "MQIACF_SUB_TYPE");
            ibmMQIACFMap.put( 1290, "MQIACF_MESSAGE_COUNT");
            ibmMQIACFMap.put( 1291, "MQIACF_Q_MGR_PUBSUB");
            ibmMQIACFMap.put( 1292, "MQIACF_Q_MGR_VERSION");
            ibmMQIACFMap.put( 1294, "MQIACF_SUB_STATUS_ATTRS");
            ibmMQIACFMap.put( 1295, "MQIACF_TOPIC_STATUS");
            ibmMQIACFMap.put( 1296, "MQIACF_TOPIC_SUB");
            ibmMQIACFMap.put( 1297, "MQIACF_TOPIC_PUB");
            ibmMQIACFMap.put( 1300, "MQIACF_RETAINED_PUBLICATION");
            ibmMQIACFMap.put( 1301, "MQIACF_TOPIC_STATUS_ATTRS");
            ibmMQIACFMap.put( 1302, "MQIACF_TOPIC_STATUS_TYPE");
            ibmMQIACFMap.put( 1303, "MQIACF_SUB_OPTIONS");
            ibmMQIACFMap.put( 1304, "MQIACF_PUBLISH_COUNT");
            ibmMQIACFMap.put( 1305, "MQIACF_CLEAR_TYPE");
            ibmMQIACFMap.put( 1306, "MQIACF_CLEAR_SCOPE");
            ibmMQIACFMap.put( 1307, "MQIACF_SUB_LEVEL");
            ibmMQIACFMap.put( 1308, "MQIACF_ASYNC_STATE");
            ibmMQIACFMap.put( 1309, "MQIACF_SUB_SUMMARY");
            ibmMQIACFMap.put( 1310, "MQIACF_OBSOLETE_MSGS");
            ibmMQIACFMap.put( 1311, "MQIACF_PUBSUB_STATUS");
            ibmMQIACFMap.put( 1314, "MQIACF_PS_STATUS_TYPE");
            ibmMQIACFMap.put( 1318, "MQIACF_PUBSUB_STATUS_ATTRS");
            ibmMQIACFMap.put( 1321, "MQIACF_SELECTOR_TYPE");
            ibmMQIACFMap.put( 1322, "MQIACF_LOG_COMPRESSION");
            ibmMQIACFMap.put( 1323, "MQIACF_GROUPUR_CHECK_ID");
            ibmMQIACFMap.put( 1324, "MQIACF_MULC_CAPTURE");
            ibmMQIACFMap.put( 1325, "MQIACF_PERMIT_STANDBY");
            ibmMQIACFMap.put( 1326, "MQIACF_OPERATION_MODE");
            ibmMQIACFMap.put( 1327, "MQIACF_COMM_INFO_ATTRS");
            ibmMQIACFMap.put( 1328, "MQIACF_CF_SMDS_BLOCK_SIZE");
            ibmMQIACFMap.put( 1329, "MQIACF_CF_SMDS_EXPAND");
            ibmMQIACFMap.put( 1330, "MQIACF_USAGE_FREE_BUFF");
            ibmMQIACFMap.put( 1331, "MQIACF_USAGE_FREE_BUFF_PERC");
            ibmMQIACFMap.put( 1332, "MQIACF_CF_STRUC_ACCESS");
            ibmMQIACFMap.put( 1333, "MQIACF_CF_STATUS_SMDS");
            ibmMQIACFMap.put( 1334, "MQIACF_SMDS_ATTRS");
            ibmMQIACFMap.put( 1335, "MQIACF_USAGE_SMDS");
            ibmMQIACFMap.put( 1336, "MQIACF_USAGE_BLOCK_SIZE");
            ibmMQIACFMap.put( 1337, "MQIACF_USAGE_DATA_BLOCKS");
            ibmMQIACFMap.put( 1338, "MQIACF_USAGE_EMPTY_BUFFERS");
            ibmMQIACFMap.put( 1339, "MQIACF_USAGE_INUSE_BUFFERS");
            ibmMQIACFMap.put( 1340, "MQIACF_USAGE_LOWEST_FREE");
            ibmMQIACFMap.put( 1341, "MQIACF_USAGE_OFFLOAD_MSGS");
            ibmMQIACFMap.put( 1342, "MQIACF_USAGE_READS_SAVED");
            ibmMQIACFMap.put( 1343, "MQIACF_USAGE_SAVED_BUFFERS");
            ibmMQIACFMap.put( 1344, "MQIACF_USAGE_TOTAL_BLOCKS");
            ibmMQIACFMap.put( 1345, "MQIACF_USAGE_USED_BLOCKS");
            ibmMQIACFMap.put( 1346, "MQIACF_USAGE_USED_RATE");
            ibmMQIACFMap.put( 1347, "MQIACF_USAGE_WAIT_RATE");
            ibmMQIACFMap.put( 1348, "MQIACF_SMDS_OPENMODE");
            ibmMQIACFMap.put( 1349, "MQIACF_SMDS_STATUS");
            ibmMQIACFMap.put( 1350, "MQIACF_SMDS_AVAIL");
            ibmMQIACFMap.put( 1351, "MQIACF_MCAST_REL_INDICATOR");
            ibmMQIACFMap.put( 1352, "MQIACF_CHLAUTH_TYPE");
            ibmMQIACFMap.put( 1354, "MQIACF_MQXR_DIAGNOSTICS_TYPE");
            ibmMQIACFMap.put( 1355, "MQIACF_CHLAUTH_ATTRS");
            ibmMQIACFMap.put( 1356, "MQIACF_OPERATION_ID");
            ibmMQIACFMap.put( 1357, "MQIACF_API_CALLER_TYPE");
            ibmMQIACFMap.put( 1358, "MQIACF_API_ENVIRONMENT");
            ibmMQIACFMap.put( 1359, "MQIACF_TRACE_DETAIL");
            ibmMQIACFMap.put( 1360, "MQIACF_HOBJ");
            ibmMQIACFMap.put( 1361, "MQIACF_CALL_TYPE");
            ibmMQIACFMap.put( 1362, "MQIACF_MQCB_OPERATION");
            ibmMQIACFMap.put( 1363, "MQIACF_MQCB_TYPE");
            ibmMQIACFMap.put( 1364, "MQIACF_MQCB_OPTIONS");
            ibmMQIACFMap.put( 1365, "MQIACF_CLOSE_OPTIONS");
            ibmMQIACFMap.put( 1366, "MQIACF_CTL_OPERATION");
            ibmMQIACFMap.put( 1367, "MQIACF_GET_OPTIONS");
            ibmMQIACFMap.put( 1368, "MQIACF_RECS_PRESENT");
            ibmMQIACFMap.put( 1369, "MQIACF_KNOWN_DEST_COUNT");
            ibmMQIACFMap.put( 1370, "MQIACF_UNKNOWN_DEST_COUNT");
            ibmMQIACFMap.put( 1371, "MQIACF_INVALID_DEST_COUNT");
            ibmMQIACFMap.put( 1372, "MQIACF_RESOLVED_TYPE");
            ibmMQIACFMap.put( 1373, "MQIACF_PUT_OPTIONS");
            ibmMQIACFMap.put( 1374, "MQIACF_BUFFER_LENGTH");
            ibmMQIACFMap.put( 1375, "MQIACF_TRACE_DATA_LENGTH");
            ibmMQIACFMap.put( 1376, "MQIACF_SMDS_EXPANDST");
            ibmMQIACFMap.put( 1377, "MQIACF_STRUC_LENGTH");
            ibmMQIACFMap.put( 1378, "MQIACF_ITEM_COUNT");
            ibmMQIACFMap.put( 1379, "MQIACF_EXPIRY_TIME");
            ibmMQIACFMap.put( 1380, "MQIACF_CONNECT_TIME");
            ibmMQIACFMap.put( 1381, "MQIACF_DISCONNECT_TIME");
            ibmMQIACFMap.put( 1382, "MQIACF_HSUB");
            ibmMQIACFMap.put( 1383, "MQIACF_SUBRQ_OPTIONS");
            ibmMQIACFMap.put( 1384, "MQIACF_XA_RMID");
            ibmMQIACFMap.put( 1385, "MQIACF_XA_FLAGS");
            ibmMQIACFMap.put( 1386, "MQIACF_XA_RETCODE");
            ibmMQIACFMap.put( 1387, "MQIACF_XA_HANDLE");
            ibmMQIACFMap.put( 1388, "MQIACF_XA_RETVAL");
            ibmMQIACFMap.put( 1389, "MQIACF_STATUS_TYPE");
            ibmMQIACFMap.put( 1390, "MQIACF_XA_COUNT");
            ibmMQIACFMap.put( 1391, "MQIACF_SELECTOR_COUNT");
            ibmMQIACFMap.put( 1392, "MQIACF_SELECTORS");
            ibmMQIACFMap.put( 1393, "MQIACF_INTATTR_COUNT");
            ibmMQIACFMap.put( 1394, "MQIACF_INT_ATTRS");
            ibmMQIACFMap.put( 1395, "MQIACF_SUBRQ_ACTION");
            ibmMQIACFMap.put( 1396, "MQIACF_NUM_PUBS");
            ibmMQIACFMap.put( 1397, "MQIACF_POINTER_SIZE");
            ibmMQIACFMap.put( 1398, "MQIACF_REMOVE_AUTHREC");
            ibmMQIACFMap.put( 1399, "MQIACF_XR_ATTRS");
            ibmMQIACFMap.put( 1400, "MQIACF_APPL_FUNCTION_TYPE");
            ibmMQIACFMap.put( 1402, "MQIACF_EXPORT_TYPE");
            ibmMQIACFMap.put( 1403, "MQIACF_EXPORT_ATTRS");
            ibmMQIACFMap.put( 1404, "MQIACF_SYSTEM_OBJECTS");
            ibmMQIACFMap.put( 1405, "MQIACF_CONNECTION_SWAP");
            ibmMQIACFMap.put( 1408, "MQIACF_BUFFER_POOL_LOCATION");
            ibmMQIACFMap.put( 1409, "MQIACF_LDAP_CONNECTION_STATUS");
            ibmMQIACFMap.put( 1411, "MQIACF_PAGECLAS");
            ibmMQIACFMap.put( 1411, "MQIACF_LAST_USED");
        }
        if( ibmMQQSIEMap == null ) {
            ibmMQQSIEMap = new HashMap<>();
            ibmMQQSIEMap.put( 0, "MQQSIE_NONE");
            ibmMQQSIEMap.put( 1, "MQQSIE_HIGH");
            ibmMQQSIEMap.put( 2, "MQQSIE_OK");
        }
        if( ibmMQFCMap == null ) {
            ibmMQFCMap = new HashMap<>();
            ibmMQFCMap.put( 1, "MQFC_YES");
            ibmMQFCMap.put( 0, "MQFC_NO");
        }
        if( ibmMQCFTMap == null ) {
            ibmMQCFTMap = new HashMap<>();
            ibmMQCFTMap.put( 0, "MQCFT_NONE");
            ibmMQCFTMap.put( 1, "MQCFT_COMMAND");
            ibmMQCFTMap.put( 2, "MQCFT_RESPONSE");
            ibmMQCFTMap.put( 3, "MQCFT_INTEGER");
            ibmMQCFTMap.put( 4, "MQCFT_STRING");
            ibmMQCFTMap.put( 5, "MQCFT_INTEGER_LIST");
            ibmMQCFTMap.put( 6, "MQCFT_STRING_LIST");
            ibmMQCFTMap.put( 7, "MQCFT_EVENT");
            ibmMQCFTMap.put( 8, "MQCFT_USER");
            ibmMQCFTMap.put( 9, "MQCFT_BYTE_STRING");
            ibmMQCFTMap.put( 10, "MQCFT_TRACE_ROUTE");
            ibmMQCFTMap.put( 12, "MQCFT_REPORT");
            ibmMQCFTMap.put( 13, "MQCFT_INTEGER_FILTER");
            ibmMQCFTMap.put( 14, "MQCFT_STRING_FILTER");
            ibmMQCFTMap.put( 15, "MQCFT_BYTE_STRING_FILTER");
            ibmMQCFTMap.put( 16, "MQCFT_COMMAND_XR");
            ibmMQCFTMap.put( 17, "MQCFT_XR_MSG");
            ibmMQCFTMap.put( 18, "MQCFT_XR_ITEM");
            ibmMQCFTMap.put( 19, "MQCFT_XR_SUMMARY");
            ibmMQCFTMap.put( 20, "MQCFT_GROUP");
            ibmMQCFTMap.put( 21, "MQCFT_STATISTICS");
            ibmMQCFTMap.put( 22, "MQCFT_ACCOUNTING");
            ibmMQCFTMap.put( 23, "MQCFT_INTEGER64");
            ibmMQCFTMap.put( 25, "MQCFT_INTEGER64_LIST");
            ibmMQCFTMap.put( 26, "MQCFT_APP_ACTIVITY");
        }
        if( ibmnoneMap == null ) {
            ibmnoneMap = new HashMap<>();
            ibmnoneMap.put( 16, "MQCFIL64_STRUC_LENGTH_FIXED");
            ibmnoneMap.put( 24, "MQCFIN64_STRUC_LENGTH");
            ibmnoneMap.put( 703, "MQIAMO64_AVG_Q_TIME");
            ibmnoneMap.put( 741, "MQIAMO64_Q_TIME_AVG");
            ibmnoneMap.put( 742, "MQIAMO64_Q_TIME_MAX");
            ibmnoneMap.put( 743, "MQIAMO64_Q_TIME_MIN");
            ibmnoneMap.put( 745, "MQIAMO64_BROWSE_BYTES");
            ibmnoneMap.put( 746, "MQIAMO64_BYTES");
            ibmnoneMap.put( 747, "MQIAMO64_GET_BYTES");
            ibmnoneMap.put( 748, "MQIAMO64_PUT_BYTES");
            ibmnoneMap.put( 783, "MQIAMO64_TOPIC_PUT_BYTES");
            ibmnoneMap.put( 785, "MQIAMO64_PUBLISH_MSG_BYTES");
            ibmnoneMap.put( 838, "MQIAMO64_HIGHRES_TIME");
        }
        if( ibmMQCLROUTEMap == null ) {
            ibmMQCLROUTEMap = new HashMap<>();
            ibmMQCLROUTEMap.put( 0, "MQCLROUTE_DIRECT");
            ibmMQCLROUTEMap.put( 1, "MQCLROUTE_TOPIC_HOST");
            ibmMQCLROUTEMap.put( 2, "MQCLROUTE_NONE");
        }
        if( ibmMQMULCMap == null ) {
            ibmMQMULCMap = new HashMap<>();
            ibmMQMULCMap.put( 0, "MQMULC_STANDARD");
            ibmMQMULCMap.put( 1, "MQMULC_REFINED");
        }
        if( ibmMQROUTEMap == null ) {
            ibmMQROUTEMap = new HashMap<>();
            ibmMQROUTEMap.put( 0, "MQROUTE_UNLIMITED_ACTIVITIES");
        }
        if( ibmMQCHTABMap == null ) {
            ibmMQCHTABMap = new HashMap<>();
            ibmMQCHTABMap.put( 1, "MQCHTAB_Q_MGR");
            ibmMQCHTABMap.put( 2, "MQCHTAB_CLNTCONN");
        }
        if( ibmMQCFACCESSMap == null ) {
            ibmMQCFACCESSMap = new HashMap<>();
            ibmMQCFACCESSMap.put( 0, "MQCFACCESS_ENABLED");
            ibmMQCFACCESSMap.put( 1, "MQCFACCESS_SUSPENDED");
            ibmMQCFACCESSMap.put( 2, "MQCFACCESS_DISABLED");
        }
        if( ibmMQUAMap == null ) {
            ibmMQUAMap = new HashMap<>();
            ibmMQUAMap.put( 65536, "MQUA_FIRST");
            ibmMQUAMap.put( 999999999, "MQUA_LAST");
        }
        if( ibmMQBTMap == null ) {
            ibmMQBTMap = new HashMap<>();
            ibmMQBTMap.put( 1, "MQBT_OTMA");
        }
        if( ibmMQMCASMap == null ) {
            ibmMQMCASMap = new HashMap<>();
            ibmMQMCASMap.put( 0, "MQMCAS_STOPPED");
            ibmMQMCASMap.put( 3, "MQMCAS_RUNNING");
        }
        if( ibmMQETMap == null ) {
            ibmMQETMap = new HashMap<>();
            ibmMQETMap.put( 1, "MQET_MQSC");
        }
        if( ibmMQAUTHMap == null ) {
            ibmMQAUTHMap = new HashMap<>();
            ibmMQAUTHMap.put( 0, "MQAUTH_NONE");
            ibmMQAUTHMap.put( 1, "MQAUTH_ALT_USER_AUTHORITY");
            ibmMQAUTHMap.put( 2, "MQAUTH_BROWSE");
            ibmMQAUTHMap.put( 3, "MQAUTH_CHANGE");
            ibmMQAUTHMap.put( 4, "MQAUTH_CLEAR");
            ibmMQAUTHMap.put( 5, "MQAUTH_CONNECT");
            ibmMQAUTHMap.put( 6, "MQAUTH_CREATE");
            ibmMQAUTHMap.put( 7, "MQAUTH_DELETE");
            ibmMQAUTHMap.put( 8, "MQAUTH_DISPLAY");
            ibmMQAUTHMap.put( 9, "MQAUTH_INPUT");
            ibmMQAUTHMap.put( 10, "MQAUTH_INQUIRE");
            ibmMQAUTHMap.put( 11, "MQAUTH_OUTPUT");
            ibmMQAUTHMap.put( 12, "MQAUTH_PASS_ALL_CONTEXT");
            ibmMQAUTHMap.put( 13, "MQAUTH_PASS_IDENTITY_CONTEXT");
            ibmMQAUTHMap.put( 14, "MQAUTH_SET");
            ibmMQAUTHMap.put( 15, "MQAUTH_SET_ALL_CONTEXT");
            ibmMQAUTHMap.put( 16, "MQAUTH_SET_IDENTITY_CONTEXT");
            ibmMQAUTHMap.put( 17, "MQAUTH_CONTROL");
            ibmMQAUTHMap.put( 18, "MQAUTH_CONTROL_EXTENDED");
            ibmMQAUTHMap.put( 19, "MQAUTH_PUBLISH");
            ibmMQAUTHMap.put( 20, "MQAUTH_SUBSCRIBE");
            ibmMQAUTHMap.put( 21, "MQAUTH_RESUME");
            ibmMQAUTHMap.put( 22, "MQAUTH_SYSTEM");
        }
        if( ibmMQEPHMap == null ) {
            ibmMQEPHMap = new HashMap<>();
            ibmMQEPHMap.put( 68, "MQEPH_STRUC_LENGTH_FIXED");
            ibmMQEPHMap.put( 1, "MQEPH_VERSION_1");
            ibmMQEPHMap.put( 1, "MQEPH_CURRENT_VERSION");
            ibmMQEPHMap.put( 68, "MQEPH_LENGTH_1");
            ibmMQEPHMap.put( 68, "MQEPH_CURRENT_LENGTH");
        }
        if( ibmMQCMDIMap == null ) {
            ibmMQCMDIMap = new HashMap<>();
            ibmMQCMDIMap.put( 1, "MQCMDI_CMDSCOPE_ACCEPTED");
            ibmMQCMDIMap.put( 2, "MQCMDI_CMDSCOPE_GENERATED");
            ibmMQCMDIMap.put( 3, "MQCMDI_CMDSCOPE_COMPLETED");
            ibmMQCMDIMap.put( 4, "MQCMDI_QSG_DISP_COMPLETED");
            ibmMQCMDIMap.put( 5, "MQCMDI_COMMAND_ACCEPTED");
            ibmMQCMDIMap.put( 6, "MQCMDI_CLUSTER_REQUEST_QUEUED");
            ibmMQCMDIMap.put( 7, "MQCMDI_CHANNEL_INIT_STARTED");
            ibmMQCMDIMap.put( 11, "MQCMDI_RECOVER_STARTED");
            ibmMQCMDIMap.put( 12, "MQCMDI_BACKUP_STARTED");
            ibmMQCMDIMap.put( 13, "MQCMDI_RECOVER_COMPLETED");
            ibmMQCMDIMap.put( 14, "MQCMDI_SEC_TIMER_ZERO");
            ibmMQCMDIMap.put( 16, "MQCMDI_REFRESH_CONFIGURATION");
            ibmMQCMDIMap.put( 17, "MQCMDI_SEC_SIGNOFF_ERROR");
            ibmMQCMDIMap.put( 18, "MQCMDI_IMS_BRIDGE_SUSPENDED");
            ibmMQCMDIMap.put( 19, "MQCMDI_DB2_SUSPENDED");
            ibmMQCMDIMap.put( 20, "MQCMDI_DB2_OBSOLETE_MSGS");
            ibmMQCMDIMap.put( 21, "MQCMDI_SEC_UPPERCASE");
            ibmMQCMDIMap.put( 22, "MQCMDI_SEC_MIXEDCASE");
        }
        if( ibmMQSTDBYMap == null ) {
            ibmMQSTDBYMap = new HashMap<>();
            ibmMQSTDBYMap.put( 0, "MQSTDBY_NOT_PERMITTED");
            ibmMQSTDBYMap.put( 1, "MQSTDBY_PERMITTED");
        }
        if( ibmMQQOMap == null ) {
            ibmMQQOMap = new HashMap<>();
            ibmMQQOMap.put( 1, "MQQO_YES");
            ibmMQQOMap.put( 0, "MQQO_NO");
        }
        if( ibmMQUCIMap == null ) {
            ibmMQUCIMap = new HashMap<>();
            ibmMQUCIMap.put( 1, "MQUCI_YES");
            ibmMQUCIMap.put( 0, "MQUCI_NO");
        }
        if( ibmMQIACHMap == null ) {
            ibmMQIACHMap = new HashMap<>();
            ibmMQIACHMap.put( 1501, "MQIACH_FIRST");
            ibmMQIACHMap.put( 1501, "MQIACH_XMIT_PROTOCOL_TYPE");
            ibmMQIACHMap.put( 1502, "MQIACH_BATCH_SIZE");
            ibmMQIACHMap.put( 1503, "MQIACH_DISC_INTERVAL");
            ibmMQIACHMap.put( 1504, "MQIACH_SHORT_TIMER");
            ibmMQIACHMap.put( 1505, "MQIACH_SHORT_RETRY");
            ibmMQIACHMap.put( 1506, "MQIACH_LONG_TIMER");
            ibmMQIACHMap.put( 1507, "MQIACH_LONG_RETRY");
            ibmMQIACHMap.put( 1508, "MQIACH_PUT_AUTHORITY");
            ibmMQIACHMap.put( 1509, "MQIACH_SEQUENCE_NUMBER_WRAP");
            ibmMQIACHMap.put( 1510, "MQIACH_MAX_MSG_LENGTH");
            ibmMQIACHMap.put( 1511, "MQIACH_CHANNEL_TYPE");
            ibmMQIACHMap.put( 1512, "MQIACH_DATA_COUNT");
            ibmMQIACHMap.put( 1513, "MQIACH_NAME_COUNT");
            ibmMQIACHMap.put( 1514, "MQIACH_MSG_SEQUENCE_NUMBER");
            ibmMQIACHMap.put( 1515, "MQIACH_DATA_CONVERSION");
            ibmMQIACHMap.put( 1516, "MQIACH_IN_DOUBT");
            ibmMQIACHMap.put( 1517, "MQIACH_MCA_TYPE");
            ibmMQIACHMap.put( 1518, "MQIACH_SESSION_COUNT");
            ibmMQIACHMap.put( 1519, "MQIACH_ADAPTER");
            ibmMQIACHMap.put( 1520, "MQIACH_COMMAND_COUNT");
            ibmMQIACHMap.put( 1521, "MQIACH_SOCKET");
            ibmMQIACHMap.put( 1522, "MQIACH_PORT");
            ibmMQIACHMap.put( 1523, "MQIACH_CHANNEL_INSTANCE_TYPE");
            ibmMQIACHMap.put( 1524, "MQIACH_CHANNEL_INSTANCE_ATTRS");
            ibmMQIACHMap.put( 1525, "MQIACH_CHANNEL_ERROR_DATA");
            ibmMQIACHMap.put( 1526, "MQIACH_CHANNEL_TABLE");
            ibmMQIACHMap.put( 1527, "MQIACH_CHANNEL_STATUS");
            ibmMQIACHMap.put( 1528, "MQIACH_INDOUBT_STATUS");
            ibmMQIACHMap.put( 1529, "MQIACH_LAST_SEQ_NUMBER");
            ibmMQIACHMap.put( 1529, "MQIACH_LAST_SEQUENCE_NUMBER");
            ibmMQIACHMap.put( 1531, "MQIACH_CURRENT_MSGS");
            ibmMQIACHMap.put( 1532, "MQIACH_CURRENT_SEQ_NUMBER");
            ibmMQIACHMap.put( 1532, "MQIACH_CURRENT_SEQUENCE_NUMBER");
            ibmMQIACHMap.put( 1533, "MQIACH_SSL_RETURN_CODE");
            ibmMQIACHMap.put( 1534, "MQIACH_MSGS");
            ibmMQIACHMap.put( 1535, "MQIACH_BYTES_SENT");
            ibmMQIACHMap.put( 1536, "MQIACH_BYTES_RCVD");
            ibmMQIACHMap.put( 1536, "MQIACH_BYTES_RECEIVED");
            ibmMQIACHMap.put( 1537, "MQIACH_BATCHES");
            ibmMQIACHMap.put( 1538, "MQIACH_BUFFERS_SENT");
            ibmMQIACHMap.put( 1539, "MQIACH_BUFFERS_RCVD");
            ibmMQIACHMap.put( 1539, "MQIACH_BUFFERS_RECEIVED");
            ibmMQIACHMap.put( 1540, "MQIACH_LONG_RETRIES_LEFT");
            ibmMQIACHMap.put( 1541, "MQIACH_SHORT_RETRIES_LEFT");
            ibmMQIACHMap.put( 1542, "MQIACH_MCA_STATUS");
            ibmMQIACHMap.put( 1543, "MQIACH_STOP_REQUESTED");
            ibmMQIACHMap.put( 1544, "MQIACH_MR_COUNT");
            ibmMQIACHMap.put( 1545, "MQIACH_MR_INTERVAL");
            ibmMQIACHMap.put( 1562, "MQIACH_NPM_SPEED");
            ibmMQIACHMap.put( 1563, "MQIACH_HB_INTERVAL");
            ibmMQIACHMap.put( 1564, "MQIACH_BATCH_INTERVAL");
            ibmMQIACHMap.put( 1565, "MQIACH_NETWORK_PRIORITY");
            ibmMQIACHMap.put( 1566, "MQIACH_KEEP_ALIVE_INTERVAL");
            ibmMQIACHMap.put( 1567, "MQIACH_BATCH_HB");
            ibmMQIACHMap.put( 1568, "MQIACH_SSL_CLIENT_AUTH");
            ibmMQIACHMap.put( 1570, "MQIACH_ALLOC_RETRY");
            ibmMQIACHMap.put( 1571, "MQIACH_ALLOC_FAST_TIMER");
            ibmMQIACHMap.put( 1572, "MQIACH_ALLOC_SLOW_TIMER");
            ibmMQIACHMap.put( 1573, "MQIACH_DISC_RETRY");
            ibmMQIACHMap.put( 1574, "MQIACH_PORT_NUMBER");
            ibmMQIACHMap.put( 1575, "MQIACH_HDR_COMPRESSION");
            ibmMQIACHMap.put( 1576, "MQIACH_MSG_COMPRESSION");
            ibmMQIACHMap.put( 1577, "MQIACH_CLWL_CHANNEL_RANK");
            ibmMQIACHMap.put( 1578, "MQIACH_CLWL_CHANNEL_PRIORITY");
            ibmMQIACHMap.put( 1579, "MQIACH_CLWL_CHANNEL_WEIGHT");
            ibmMQIACHMap.put( 1580, "MQIACH_CHANNEL_DISP");
            ibmMQIACHMap.put( 1581, "MQIACH_INBOUND_DISP");
            ibmMQIACHMap.put( 1582, "MQIACH_CHANNEL_TYPES");
            ibmMQIACHMap.put( 1583, "MQIACH_ADAPS_STARTED");
            ibmMQIACHMap.put( 1584, "MQIACH_ADAPS_MAX");
            ibmMQIACHMap.put( 1585, "MQIACH_DISPS_STARTED");
            ibmMQIACHMap.put( 1586, "MQIACH_DISPS_MAX");
            ibmMQIACHMap.put( 1587, "MQIACH_SSLTASKS_STARTED");
            ibmMQIACHMap.put( 1588, "MQIACH_SSLTASKS_MAX");
            ibmMQIACHMap.put( 1589, "MQIACH_CURRENT_CHL");
            ibmMQIACHMap.put( 1590, "MQIACH_CURRENT_CHL_MAX");
            ibmMQIACHMap.put( 1591, "MQIACH_CURRENT_CHL_TCP");
            ibmMQIACHMap.put( 1592, "MQIACH_CURRENT_CHL_LU62");
            ibmMQIACHMap.put( 1593, "MQIACH_ACTIVE_CHL");
            ibmMQIACHMap.put( 1594, "MQIACH_ACTIVE_CHL_MAX");
            ibmMQIACHMap.put( 1595, "MQIACH_ACTIVE_CHL_PAUSED");
            ibmMQIACHMap.put( 1596, "MQIACH_ACTIVE_CHL_STARTED");
            ibmMQIACHMap.put( 1597, "MQIACH_ACTIVE_CHL_STOPPED");
            ibmMQIACHMap.put( 1598, "MQIACH_ACTIVE_CHL_RETRY");
            ibmMQIACHMap.put( 1599, "MQIACH_LISTENER_STATUS");
            ibmMQIACHMap.put( 1600, "MQIACH_SHARED_CHL_RESTART");
            ibmMQIACHMap.put( 1601, "MQIACH_LISTENER_CONTROL");
            ibmMQIACHMap.put( 1602, "MQIACH_BACKLOG");
            ibmMQIACHMap.put( 1604, "MQIACH_XMITQ_TIME_INDICATOR");
            ibmMQIACHMap.put( 1605, "MQIACH_NETWORK_TIME_INDICATOR");
            ibmMQIACHMap.put( 1606, "MQIACH_EXIT_TIME_INDICATOR");
            ibmMQIACHMap.put( 1607, "MQIACH_BATCH_SIZE_INDICATOR");
            ibmMQIACHMap.put( 1608, "MQIACH_XMITQ_MSGS_AVAILABLE");
            ibmMQIACHMap.put( 1609, "MQIACH_CHANNEL_SUBSTATE");
            ibmMQIACHMap.put( 1610, "MQIACH_SSL_KEY_RESETS");
            ibmMQIACHMap.put( 1611, "MQIACH_COMPRESSION_RATE");
            ibmMQIACHMap.put( 1612, "MQIACH_COMPRESSION_TIME");
            ibmMQIACHMap.put( 1613, "MQIACH_MAX_XMIT_SIZE");
            ibmMQIACHMap.put( 1614, "MQIACH_DEF_CHANNEL_DISP");
            ibmMQIACHMap.put( 1615, "MQIACH_SHARING_CONVERSATIONS");
            ibmMQIACHMap.put( 1616, "MQIACH_MAX_SHARING_CONVS");
            ibmMQIACHMap.put( 1617, "MQIACH_CURRENT_SHARING_CONVS");
            ibmMQIACHMap.put( 1618, "MQIACH_MAX_INSTANCES");
            ibmMQIACHMap.put( 1619, "MQIACH_MAX_INSTS_PER_CLIENT");
            ibmMQIACHMap.put( 1620, "MQIACH_CLIENT_CHANNEL_WEIGHT");
            ibmMQIACHMap.put( 1621, "MQIACH_CONNECTION_AFFINITY");
            ibmMQIACHMap.put( 1623, "MQIACH_RESET_REQUESTED");
            ibmMQIACHMap.put( 1624, "MQIACH_BATCH_DATA_LIMIT");
            ibmMQIACHMap.put( 1625, "MQIACH_MSG_HISTORY");
            ibmMQIACHMap.put( 1626, "MQIACH_MULTICAST_PROPERTIES");
            ibmMQIACHMap.put( 1627, "MQIACH_NEW_SUBSCRIBER_HISTORY");
            ibmMQIACHMap.put( 1628, "MQIACH_MC_HB_INTERVAL");
            ibmMQIACHMap.put( 1629, "MQIACH_USE_CLIENT_ID");
            ibmMQIACHMap.put( 1630, "MQIACH_MQTT_KEEP_ALIVE");
            ibmMQIACHMap.put( 1631, "MQIACH_IN_DOUBT_IN");
            ibmMQIACHMap.put( 1632, "MQIACH_IN_DOUBT_OUT");
            ibmMQIACHMap.put( 1633, "MQIACH_MSGS_SENT");
            ibmMQIACHMap.put( 1634, "MQIACH_MSGS_RECEIVED");
            ibmMQIACHMap.put( 1634, "MQIACH_MSGS_RCVD");
            ibmMQIACHMap.put( 1635, "MQIACH_PENDING_OUT");
            ibmMQIACHMap.put( 1636, "MQIACH_AVAILABLE_CIPHERSPECS");
            ibmMQIACHMap.put( 1637, "MQIACH_MATCH");
            ibmMQIACHMap.put( 1638, "MQIACH_USER_SOURCE");
            ibmMQIACHMap.put( 1639, "MQIACH_WARNING");
            ibmMQIACHMap.put( 1640, "MQIACH_DEF_RECONNECT");
            ibmMQIACHMap.put( 1642, "MQIACH_CHANNEL_SUMMARY_ATTRS");
            ibmMQIACHMap.put( 1642, "MQIACH_LAST_USED");
        }
        if( ibmMQCFCMap == null ) {
            ibmMQCFCMap = new HashMap<>();
            ibmMQCFCMap.put( 1, "MQCFC_LAST");
            ibmMQCFCMap.put( 0, "MQCFC_NOT_LAST");
        }
        if( ibmMQEVRMap == null ) {
            ibmMQEVRMap = new HashMap<>();
            ibmMQEVRMap.put( 0, "MQEVR_DISABLED");
            ibmMQEVRMap.put( 1, "MQEVR_ENABLED");
            ibmMQEVRMap.put( 2, "MQEVR_EXCEPTION");
            ibmMQEVRMap.put( 3, "MQEVR_NO_DISPLAY");
            ibmMQEVRMap.put( 4, "MQEVR_API_ONLY");
            ibmMQEVRMap.put( 5, "MQEVR_ADMIN_ONLY");
            ibmMQEVRMap.put( 6, "MQEVR_USER_ONLY");
        }
        if( ibmMQQSUMMap == null ) {
            ibmMQQSUMMap = new HashMap<>();
            ibmMQQSUMMap.put( 1, "MQQSUM_YES");
            ibmMQQSUMMap.put( 0, "MQQSUM_NO");
        }
        if( ibmMQCFGRMap == null ) {
            ibmMQCFGRMap = new HashMap<>();
            ibmMQCFGRMap.put( 16, "MQCFGR_STRUC_LENGTH");
        }
        if( ibmMQADPCTXMap == null ) {
            ibmMQADPCTXMap = new HashMap<>();
            ibmMQADPCTXMap.put( 0, "MQADPCTX_NO");
            ibmMQADPCTXMap.put( 1, "MQADPCTX_YES");
        }
        if( ibmMQQSGSMap == null ) {
            ibmMQQSGSMap = new HashMap<>();
            ibmMQQSGSMap.put( 0, "MQQSGS_UNKNOWN");
            ibmMQQSGSMap.put( 1, "MQQSGS_CREATED");
            ibmMQQSGSMap.put( 2, "MQQSGS_ACTIVE");
            ibmMQQSGSMap.put( 3, "MQQSGS_INACTIVE");
            ibmMQQSGSMap.put( 4, "MQQSGS_FAILED");
            ibmMQQSGSMap.put( 5, "MQQSGS_PENDING");
        }
        if( ibmMQUSAGEMap == null ) {
            ibmMQUSAGEMap = new HashMap<>();
            ibmMQUSAGEMap.put( 0, "MQUSAGE_SMDS_AVAILABLE");
            ibmMQUSAGEMap.put( 1, "MQUSAGE_SMDS_NO_DATA");
            ibmMQUSAGEMap.put( 0, "MQUSAGE_PS_AVAILABLE");
            ibmMQUSAGEMap.put( 1, "MQUSAGE_PS_DEFINED");
            ibmMQUSAGEMap.put( 2, "MQUSAGE_PS_OFFLINE");
            ibmMQUSAGEMap.put( 3, "MQUSAGE_PS_NOT_DEFINED");
            ibmMQUSAGEMap.put( 4, "MQUSAGE_PS_SUSPENDED");
            ibmMQUSAGEMap.put( 1, "MQUSAGE_EXPAND_USER");
            ibmMQUSAGEMap.put( 2, "MQUSAGE_EXPAND_SYSTEM");
            ibmMQUSAGEMap.put( 3, "MQUSAGE_EXPAND_NONE");
            ibmMQUSAGEMap.put( 10, "MQUSAGE_DS_OLDEST_ACTIVE_UOW");
            ibmMQUSAGEMap.put( 11, "MQUSAGE_DS_OLDEST_PS_RECOVERY");
            ibmMQUSAGEMap.put( 12, "MQUSAGE_DS_OLDEST_CF_RECOVERY");
        }
        if( ibmMQCFSFMap == null ) {
            ibmMQCFSFMap = new HashMap<>();
            ibmMQCFSFMap.put( 24, "MQCFSF_STRUC_LENGTH_FIXED");
        }
        if( ibmMQUOWSTMap == null ) {
            ibmMQUOWSTMap = new HashMap<>();
            ibmMQUOWSTMap.put( 0, "MQUOWST_NONE");
            ibmMQUOWSTMap.put( 1, "MQUOWST_ACTIVE");
            ibmMQUOWSTMap.put( 2, "MQUOWST_PREPARED");
            ibmMQUOWSTMap.put( 3, "MQUOWST_UNRESOLVED");
        }
        if( ibmMQGURMap == null ) {
            ibmMQGURMap = new HashMap<>();
            ibmMQGURMap.put( 0, "MQGUR_DISABLED");
            ibmMQGURMap.put( 1, "MQGUR_ENABLED");
        }
        if( ibmMQINBDMap == null ) {
            ibmMQINBDMap = new HashMap<>();
            ibmMQINBDMap.put( 0, "MQINBD_Q_MGR");
            ibmMQINBDMap.put( 3, "MQINBD_GROUP");
        }
        if( ibmMQDOPTMap == null ) {
            ibmMQDOPTMap = new HashMap<>();
            ibmMQDOPTMap.put( 0, "MQDOPT_RESOLVED");
            ibmMQDOPTMap.put( 1, "MQDOPT_DEFINED");
        }
        if( ibmMQMODEMap == null ) {
            ibmMQMODEMap = new HashMap<>();
            ibmMQMODEMap.put( 0, "MQMODE_FORCE");
            ibmMQMODEMap.put( 1, "MQMODE_QUIESCE");
            ibmMQMODEMap.put( 2, "MQMODE_TERMINATE");
        }
        if( ibmMQOPMODEMap == null ) {
            ibmMQOPMODEMap = new HashMap<>();
            ibmMQOPMODEMap.put( 0, "MQOPMODE_COMPAT");
            ibmMQOPMODEMap.put( 1, "MQOPMODE_NEW_FUNCTION");
        }
        if( ibmMQMATCHMap == null ) {
            ibmMQMATCHMap = new HashMap<>();
            ibmMQMATCHMap.put( 0, "MQMATCH_GENERIC");
            ibmMQMATCHMap.put( 1, "MQMATCH_RUNCHECK");
            ibmMQMATCHMap.put( 2, "MQMATCH_EXACT");
            ibmMQMATCHMap.put( 3, "MQMATCH_ALL");
        }
        if( ibmMQUIDSUPPMap == null ) {
            ibmMQUIDSUPPMap = new HashMap<>();
            ibmMQUIDSUPPMap.put( 0, "MQUIDSUPP_NO");
            ibmMQUIDSUPPMap.put( 1, "MQUIDSUPP_YES");
        }
        if( ibmMQSYSOBJMap == null ) {
            ibmMQSYSOBJMap = new HashMap<>();
            ibmMQSYSOBJMap.put( 0, "MQSYSOBJ_YES");
            ibmMQSYSOBJMap.put( 1, "MQSYSOBJ_NO");
        }
        if( ibmMQCFSLMap == null ) {
            ibmMQCFSLMap = new HashMap<>();
            ibmMQCFSLMap.put( 24, "MQCFSL_STRUC_LENGTH_FIXED");
        }
        if( ibmMQOPERMap == null ) {
            ibmMQOPERMap = new HashMap<>();
            ibmMQOPERMap.put( 0, "MQOPER_SYSTEM_FIRST");
            ibmMQOPERMap.put( 0, "MQOPER_UNKNOWN");
            ibmMQOPERMap.put( 1, "MQOPER_BROWSE");
            ibmMQOPERMap.put( 2, "MQOPER_DISCARD");
            ibmMQOPERMap.put( 3, "MQOPER_GET");
            ibmMQOPERMap.put( 4, "MQOPER_PUT");
            ibmMQOPERMap.put( 5, "MQOPER_PUT_REPLY");
            ibmMQOPERMap.put( 6, "MQOPER_PUT_REPORT");
            ibmMQOPERMap.put( 7, "MQOPER_RECEIVE");
            ibmMQOPERMap.put( 8, "MQOPER_SEND");
            ibmMQOPERMap.put( 9, "MQOPER_TRANSFORM");
            ibmMQOPERMap.put( 10, "MQOPER_PUBLISH");
            ibmMQOPERMap.put( 11, "MQOPER_EXCLUDED_PUBLISH");
            ibmMQOPERMap.put( 12, "MQOPER_DISCARDED_PUBLISH");
            ibmMQOPERMap.put( 65535, "MQOPER_SYSTEM_LAST");
            ibmMQOPERMap.put( 65536, "MQOPER_APPL_FIRST");
            ibmMQOPERMap.put( 999999999, "MQOPER_APPL_LAST");
        }
        if( ibmMQQMDTMap == null ) {
            ibmMQQMDTMap = new HashMap<>();
            ibmMQQMDTMap.put( 1, "MQQMDT_EXPLICIT_CLUSTER_SENDER");
            ibmMQQMDTMap.put( 2, "MQQMDT_AUTO_CLUSTER_SENDER");
            ibmMQQMDTMap.put( 4, "MQQMDT_AUTO_EXP_CLUSTER_SENDER");
            ibmMQQMDTMap.put( 3, "MQQMDT_CLUSTER_RECEIVER");
        }
        if( ibmMQUNDELIVEREDMap == null ) {
            ibmMQUNDELIVEREDMap = new HashMap<>();
            ibmMQUNDELIVEREDMap.put( 0, "MQUNDELIVERED_NORMAL");
            ibmMQUNDELIVEREDMap.put( 1, "MQUNDELIVERED_SAFE");
            ibmMQUNDELIVEREDMap.put( 2, "MQUNDELIVERED_DISCARD");
            ibmMQUNDELIVEREDMap.put( 3, "MQUNDELIVERED_KEEP");
        }
        if( ibmMQCFSTATUSMap == null ) {
            ibmMQCFSTATUSMap = new HashMap<>();
            ibmMQCFSTATUSMap.put( 0, "MQCFSTATUS_NOT_FOUND");
            ibmMQCFSTATUSMap.put( 1, "MQCFSTATUS_ACTIVE");
            ibmMQCFSTATUSMap.put( 2, "MQCFSTATUS_IN_RECOVER");
            ibmMQCFSTATUSMap.put( 3, "MQCFSTATUS_IN_BACKUP");
            ibmMQCFSTATUSMap.put( 4, "MQCFSTATUS_FAILED");
            ibmMQCFSTATUSMap.put( 5, "MQCFSTATUS_NONE");
            ibmMQCFSTATUSMap.put( 6, "MQCFSTATUS_UNKNOWN");
            ibmMQCFSTATUSMap.put( 7, "MQCFSTATUS_RECOVERED");
            ibmMQCFSTATUSMap.put( 8, "MQCFSTATUS_EMPTY");
            ibmMQCFSTATUSMap.put( 9, "MQCFSTATUS_NEW");
            ibmMQCFSTATUSMap.put( 20, "MQCFSTATUS_ADMIN_INCOMPLETE");
            ibmMQCFSTATUSMap.put( 21, "MQCFSTATUS_NEVER_USED");
            ibmMQCFSTATUSMap.put( 22, "MQCFSTATUS_NO_BACKUP");
            ibmMQCFSTATUSMap.put( 23, "MQCFSTATUS_NOT_FAILED");
            ibmMQCFSTATUSMap.put( 24, "MQCFSTATUS_NOT_RECOVERABLE");
            ibmMQCFSTATUSMap.put( 25, "MQCFSTATUS_XES_ERROR");
        }
        if( ibmMQCHSMap == null ) {
            ibmMQCHSMap = new HashMap<>();
            ibmMQCHSMap.put( 0, "MQCHS_INACTIVE");
            ibmMQCHSMap.put( 1, "MQCHS_BINDING");
            ibmMQCHSMap.put( 2, "MQCHS_STARTING");
            ibmMQCHSMap.put( 3, "MQCHS_RUNNING");
            ibmMQCHSMap.put( 4, "MQCHS_STOPPING");
            ibmMQCHSMap.put( 5, "MQCHS_RETRYING");
            ibmMQCHSMap.put( 6, "MQCHS_STOPPED");
            ibmMQCHSMap.put( 7, "MQCHS_REQUESTING");
            ibmMQCHSMap.put( 8, "MQCHS_PAUSED");
            ibmMQCHSMap.put( 9, "MQCHS_DISCONNECTED");
            ibmMQCHSMap.put( 13, "MQCHS_INITIALIZING");
            ibmMQCHSMap.put( 14, "MQCHS_SWITCHING");
        }
    }

    private static void init_theLast() {
        if( ibmCommandMap == null ) {
            ibmCommandMap = new HashMap<>();
            ibmCommandMap.put("CMQC.MQCA_Q_NAME", 2016 );
        }
        if( ibmMQIAMap == null ) {
            ibmMQIAMap = new HashMap<>();
            ibmMQIAMap.put( "MQIA_ACCOUNTING_CONN_OVERRIDE", 136);
            ibmMQIAMap.put( "MQIA_ACCOUNTING_INTERVAL", 135);
            ibmMQIAMap.put( "MQIA_ACCOUNTING_MQI", 133);
            ibmMQIAMap.put( "MQIA_ACCOUNTING_Q", 134);
            ibmMQIAMap.put( "MQIA_ACTIVE_CHANNELS", 100);
            ibmMQIAMap.put( "MQIA_ACTIVITY_CONN_OVERRIDE", 239);
            ibmMQIAMap.put( "MQIA_ACTIVITY_RECORDING", 138);
            ibmMQIAMap.put( "MQIA_ACTIVITY_TRACE", 240);
            ibmMQIAMap.put( "MQIA_ADOPTNEWMCA_CHECK", 102);
            ibmMQIAMap.put( "MQIA_ADOPTNEWMCA_INTERVAL", 104);
            ibmMQIAMap.put( "MQIA_ADOPTNEWMCA_TYPE", 103);
            ibmMQIAMap.put( "MQIA_ADOPT_CONTEXT", 260);
            ibmMQIAMap.put( "MQIA_APPL_TYPE", 1);
            ibmMQIAMap.put( "MQIA_ARCHIVE", 60);
            ibmMQIAMap.put( "MQIA_AUTHENTICATION_FAIL_DELAY", 259);
            ibmMQIAMap.put( "MQIA_AUTHORITY_EVENT", 47);
            ibmMQIAMap.put( "MQIA_AUTH_INFO_TYPE", 66);
            ibmMQIAMap.put( "MQIA_AUTO_REORGANIZATION", 173);
            ibmMQIAMap.put( "MQIA_AUTO_REORG_INTERVAL", 174);
            ibmMQIAMap.put( "MQIA_BACKOUT_THRESHOLD", 22);
            ibmMQIAMap.put( "MQIA_BASE_TYPE", 193);
            ibmMQIAMap.put( "MQIA_BATCH_INTERFACE_AUTO", 86);
            ibmMQIAMap.put( "MQIA_BRIDGE_EVENT", 74);
            ibmMQIAMap.put( "MQIA_CERT_VAL_POLICY", 252);
            ibmMQIAMap.put( "MQIA_CF_CFCONLOS", 246);
            ibmMQIAMap.put( "MQIA_CF_LEVEL", 70);
            ibmMQIAMap.put( "MQIA_CF_OFFLDUSE", 229);
            ibmMQIAMap.put( "MQIA_CF_OFFLOAD", 224);
            ibmMQIAMap.put( "MQIA_CF_OFFLOAD_THRESHOLD1", 225);
            ibmMQIAMap.put( "MQIA_CF_OFFLOAD_THRESHOLD2", 226);
            ibmMQIAMap.put( "MQIA_CF_OFFLOAD_THRESHOLD3", 227);
            ibmMQIAMap.put( "MQIA_CF_RECAUTO", 244);
            ibmMQIAMap.put( "MQIA_CF_RECOVER", 71);
            ibmMQIAMap.put( "MQIA_CF_SMDS_BUFFERS", 228);
            ibmMQIAMap.put( "MQIA_CHANNEL_AUTO_DEF", 55);
            ibmMQIAMap.put( "MQIA_CHANNEL_AUTO_DEF_EVENT", 56);
            ibmMQIAMap.put( "MQIA_CHANNEL_EVENT", 73);
            ibmMQIAMap.put( "MQIA_CHECK_CLIENT_BINDING", 258);
            ibmMQIAMap.put( "MQIA_CHECK_LOCAL_BINDING", 257);
            ibmMQIAMap.put( "MQIA_CHINIT_ADAPTERS", 101);
            ibmMQIAMap.put( "MQIA_CHINIT_CONTROL", 119);
            ibmMQIAMap.put( "MQIA_CHINIT_DISPATCHERS", 105);
            ibmMQIAMap.put( "MQIA_CHINIT_TRACE_AUTO_START", 117);
            ibmMQIAMap.put( "MQIA_CHINIT_TRACE_TABLE_SIZE", 118);
            ibmMQIAMap.put( "MQIA_CHLAUTH_RECORDS", 248);
            ibmMQIAMap.put( "MQIA_CLUSTER_OBJECT_STATE", 256);
            ibmMQIAMap.put( "MQIA_CLUSTER_PUB_ROUTE", 255);
            ibmMQIAMap.put( "MQIA_CLUSTER_Q_TYPE", 59);
            ibmMQIAMap.put( "MQIA_CLUSTER_WORKLOAD_LENGTH", 58);
            ibmMQIAMap.put( "MQIA_CLWL_MRU_CHANNELS", 97);
            ibmMQIAMap.put( "MQIA_CLWL_Q_PRIORITY", 96);
            ibmMQIAMap.put( "MQIA_CLWL_Q_RANK", 95);
            ibmMQIAMap.put( "MQIA_CLWL_USEQ", 98);
            ibmMQIAMap.put( "MQIA_CMD_SERVER_AUTO", 87);
            ibmMQIAMap.put( "MQIA_CMD_SERVER_CONTROL", 120);
            ibmMQIAMap.put( "MQIA_CMD_SERVER_CONVERT_MSG", 88);
            ibmMQIAMap.put( "MQIA_CMD_SERVER_DLQ_MSG", 89);
            ibmMQIAMap.put( "MQIA_CODED_CHAR_SET_ID", 2);
            ibmMQIAMap.put( "MQIA_COMMAND_EVENT", 99);
            ibmMQIAMap.put( "MQIA_COMMAND_LEVEL", 31);
            ibmMQIAMap.put( "MQIA_COMM_EVENT", 232);
            ibmMQIAMap.put( "MQIA_COMM_INFO_TYPE", 223);
            ibmMQIAMap.put( "MQIA_CONFIGURATION_EVENT", 51);
            ibmMQIAMap.put( "MQIA_CPI_LEVEL", 27);
            ibmMQIAMap.put( "MQIA_CURRENT_Q_DEPTH", 3);
            ibmMQIAMap.put( "MQIA_DEFINITION_TYPE", 7);
            ibmMQIAMap.put( "MQIA_DEF_BIND", 61);
            ibmMQIAMap.put( "MQIA_DEF_CLUSTER_XMIT_Q_TYPE", 250);
            ibmMQIAMap.put( "MQIA_DEF_INPUT_OPEN_OPTION", 4);
            ibmMQIAMap.put( "MQIA_DEF_PERSISTENCE", 5);
            ibmMQIAMap.put( "MQIA_DEF_PRIORITY", 6);
            ibmMQIAMap.put( "MQIA_DEF_PUT_RESPONSE_TYPE", 184);
            ibmMQIAMap.put( "MQIA_DEF_READ_AHEAD", 188);
            ibmMQIAMap.put( "MQIA_DISPLAY_TYPE", 262);
            ibmMQIAMap.put( "MQIA_DIST_LISTS", 34);
            ibmMQIAMap.put( "MQIA_DNS_WLM", 106);
            ibmMQIAMap.put( "MQIA_DURABLE_SUB", 175);
            ibmMQIAMap.put( "MQIA_ENCRYPTION_ALGORITHM", 237);
            ibmMQIAMap.put( "MQIA_EXPIRY_INTERVAL", 39);
            ibmMQIAMap.put( "MQIA_FIRST", 1);
            ibmMQIAMap.put( "MQIA_GROUP_UR", 221);
            ibmMQIAMap.put( "MQIA_HARDEN_GET_BACKOUT", 8);
            ibmMQIAMap.put( "MQIA_HIGH_Q_DEPTH", 36);
            ibmMQIAMap.put( "MQIA_IGQ_PUT_AUTHORITY", 65);
            ibmMQIAMap.put( "MQIA_INDEX_TYPE", 57);
            ibmMQIAMap.put( "MQIA_INHIBIT_EVENT", 48);
            ibmMQIAMap.put( "MQIA_INHIBIT_GET", 9);
            ibmMQIAMap.put( "MQIA_INHIBIT_PUB", 181);
            ibmMQIAMap.put( "MQIA_INHIBIT_PUT", 10);
            ibmMQIAMap.put( "MQIA_INHIBIT_SUB", 182);
            ibmMQIAMap.put( "MQIA_INTRA_GROUP_QUEUING", 64);
            ibmMQIAMap.put( "MQIA_IP_ADDRESS_VERSION", 93);
            ibmMQIAMap.put( "MQIA_LAST", 2000);
            ibmMQIAMap.put( "MQIA_LAST_USED", 264);
            ibmMQIAMap.put( "MQIA_LDAP_AUTHORMD", 263);
            ibmMQIAMap.put( "MQIA_LDAP_NESTGRP", 264);
            ibmMQIAMap.put( "MQIA_LDAP_SECURE_COMM", 261);
            ibmMQIAMap.put( "MQIA_LISTENER_PORT_NUMBER", 85);
            ibmMQIAMap.put( "MQIA_LISTENER_TIMER", 107);
            ibmMQIAMap.put( "MQIA_LOCAL_EVENT", 49);
            ibmMQIAMap.put( "MQIA_LOGGER_EVENT", 94);
            ibmMQIAMap.put( "MQIA_LU62_CHANNELS", 108);
            ibmMQIAMap.put( "MQIA_MASTER_ADMIN", 186);
            ibmMQIAMap.put( "MQIA_MAX_CHANNELS", 109);
            ibmMQIAMap.put( "MQIA_MAX_CLIENTS", 172);
            ibmMQIAMap.put( "MQIA_MAX_GLOBAL_LOCKS", 83);
            ibmMQIAMap.put( "MQIA_MAX_HANDLES", 11);
            ibmMQIAMap.put( "MQIA_MAX_LOCAL_LOCKS", 84);
            ibmMQIAMap.put( "MQIA_MAX_MSG_LENGTH", 13);
            ibmMQIAMap.put( "MQIA_MAX_OPEN_Q", 80);
            ibmMQIAMap.put( "MQIA_MAX_PRIORITY", 14);
            ibmMQIAMap.put( "MQIA_MAX_PROPERTIES_LENGTH", 192);
            ibmMQIAMap.put( "MQIA_MAX_Q_DEPTH", 15);
            ibmMQIAMap.put( "MQIA_MAX_Q_TRIGGERS", 90);
            ibmMQIAMap.put( "MQIA_MAX_RECOVERY_TASKS", 171);
            ibmMQIAMap.put( "MQIA_MAX_RESPONSES", 230);
            ibmMQIAMap.put( "MQIA_MAX_UNCOMMITTED_MSGS", 33);
            ibmMQIAMap.put( "MQIA_MCAST_BRIDGE", 233);
            ibmMQIAMap.put( "MQIA_MONITORING_AUTO_CLUSSDR", 124);
            ibmMQIAMap.put( "MQIA_MONITORING_CHANNEL", 122);
            ibmMQIAMap.put( "MQIA_MONITORING_Q", 123);
            ibmMQIAMap.put( "MQIA_MONITOR_INTERVAL", 81);
            ibmMQIAMap.put( "MQIA_MSG_DELIVERY_SEQUENCE", 16);
            ibmMQIAMap.put( "MQIA_MSG_DEQ_COUNT", 38);
            ibmMQIAMap.put( "MQIA_MSG_ENQ_COUNT", 37);
            ibmMQIAMap.put( "MQIA_MSG_MARK_BROWSE_INTERVAL", 68);
            ibmMQIAMap.put( "MQIA_MULTICAST", 176);
            ibmMQIAMap.put( "MQIA_NAMELIST_TYPE", 72);
            ibmMQIAMap.put( "MQIA_NAME_COUNT", 19);
            ibmMQIAMap.put( "MQIA_NPM_CLASS", 78);
            ibmMQIAMap.put( "MQIA_NPM_DELIVERY", 196);
            ibmMQIAMap.put( "MQIA_OPEN_INPUT_COUNT", 17);
            ibmMQIAMap.put( "MQIA_OPEN_OUTPUT_COUNT", 18);
            ibmMQIAMap.put( "MQIA_OUTBOUND_PORT_MAX", 140);
            ibmMQIAMap.put( "MQIA_OUTBOUND_PORT_MIN", 110);
            ibmMQIAMap.put( "MQIA_PAGESET_ID", 62);
            ibmMQIAMap.put( "MQIA_PERFORMANCE_EVENT", 53);
            ibmMQIAMap.put( "MQIA_PLATFORM", 32);
            ibmMQIAMap.put( "MQIA_PM_DELIVERY", 195);
            ibmMQIAMap.put( "MQIA_POLICY_VERSION", 238);
            ibmMQIAMap.put( "MQIA_PROPERTY_CONTROL", 190);
            ibmMQIAMap.put( "MQIA_PROT_POLICY_CAPABILITY", 251);
            ibmMQIAMap.put( "MQIA_PROXY_SUB", 199);
            ibmMQIAMap.put( "MQIA_PUBSUB_CLUSTER", 249);
            ibmMQIAMap.put( "MQIA_PUBSUB_MAXMSG_RETRY_COUNT", 206);
            ibmMQIAMap.put( "MQIA_PUBSUB_MODE", 187);
            ibmMQIAMap.put( "MQIA_PUBSUB_NP_MSG", 203);
            ibmMQIAMap.put( "MQIA_PUBSUB_NP_RESP", 205);
            ibmMQIAMap.put( "MQIA_PUBSUB_SYNC_PT", 207);
            ibmMQIAMap.put( "MQIA_PUB_COUNT", 215);
            ibmMQIAMap.put( "MQIA_PUB_SCOPE", 219);
            ibmMQIAMap.put( "MQIA_QMGR_CFCONLOS", 245);
            ibmMQIAMap.put( "MQIA_QMOPT_CONS_COMMS_MSGS", 155);
            ibmMQIAMap.put( "MQIA_QMOPT_CONS_CRITICAL_MSGS", 154);
            ibmMQIAMap.put( "MQIA_QMOPT_CONS_ERROR_MSGS", 153);
            ibmMQIAMap.put( "MQIA_QMOPT_CONS_INFO_MSGS", 151);
            ibmMQIAMap.put( "MQIA_QMOPT_CONS_REORG_MSGS", 156);
            ibmMQIAMap.put( "MQIA_QMOPT_CONS_SYSTEM_MSGS", 157);
            ibmMQIAMap.put( "MQIA_QMOPT_CONS_WARNING_MSGS", 152);
            ibmMQIAMap.put( "MQIA_QMOPT_CSMT_ON_ERROR", 150);
            ibmMQIAMap.put( "MQIA_QMOPT_INTERNAL_DUMP", 170);
            ibmMQIAMap.put( "MQIA_QMOPT_LOG_COMMS_MSGS", 162);
            ibmMQIAMap.put( "MQIA_QMOPT_LOG_CRITICAL_MSGS", 161);
            ibmMQIAMap.put( "MQIA_QMOPT_LOG_ERROR_MSGS", 160);
            ibmMQIAMap.put( "MQIA_QMOPT_LOG_INFO_MSGS", 158);
            ibmMQIAMap.put( "MQIA_QMOPT_LOG_REORG_MSGS", 163);
            ibmMQIAMap.put( "MQIA_QMOPT_LOG_SYSTEM_MSGS", 164);
            ibmMQIAMap.put( "MQIA_QMOPT_LOG_WARNING_MSGS", 159);
            ibmMQIAMap.put( "MQIA_QMOPT_TRACE_COMMS", 166);
            ibmMQIAMap.put( "MQIA_QMOPT_TRACE_CONVERSION", 168);
            ibmMQIAMap.put( "MQIA_QMOPT_TRACE_MQI_CALLS", 165);
            ibmMQIAMap.put( "MQIA_QMOPT_TRACE_REORG", 167);
            ibmMQIAMap.put( "MQIA_QMOPT_TRACE_SYSTEM", 169);
            ibmMQIAMap.put( "MQIA_QSG_DISP", 63);
            ibmMQIAMap.put( "MQIA_Q_DEPTH_HIGH_EVENT", 43);
            ibmMQIAMap.put( "MQIA_Q_DEPTH_HIGH_LIMIT", 40);
            ibmMQIAMap.put( "MQIA_Q_DEPTH_LOW_EVENT", 44);
            ibmMQIAMap.put( "MQIA_Q_DEPTH_LOW_LIMIT", 41);
            ibmMQIAMap.put( "MQIA_Q_DEPTH_MAX_EVENT", 42);
            ibmMQIAMap.put( "MQIA_Q_SERVICE_INTERVAL", 54);
            ibmMQIAMap.put( "MQIA_Q_SERVICE_INTERVAL_EVENT", 46);
            ibmMQIAMap.put( "MQIA_Q_TYPE", 20);
            ibmMQIAMap.put( "MQIA_Q_USERS", 82);
            ibmMQIAMap.put( "MQIA_READ_AHEAD", 189);
            ibmMQIAMap.put( "MQIA_RECEIVE_TIMEOUT", 111);
            ibmMQIAMap.put( "MQIA_RECEIVE_TIMEOUT_MIN", 113);
            ibmMQIAMap.put( "MQIA_RECEIVE_TIMEOUT_TYPE", 112);
            ibmMQIAMap.put( "MQIA_REMOTE_EVENT", 50);
            ibmMQIAMap.put( "MQIA_RESPONSE_RESTART_POINT", 231);
            ibmMQIAMap.put( "MQIA_RETENTION_INTERVAL", 21);
            ibmMQIAMap.put( "MQIA_REVERSE_DNS_LOOKUP", 254);
            ibmMQIAMap.put( "MQIA_SCOPE", 45);
            ibmMQIAMap.put( "MQIA_SECURITY_CASE", 141);
            ibmMQIAMap.put( "MQIA_SERVICE_CONTROL", 139);
            ibmMQIAMap.put( "MQIA_SERVICE_TYPE", 121);
            ibmMQIAMap.put( "MQIA_SHAREABILITY", 23);
            ibmMQIAMap.put( "MQIA_SHARED_Q_Q_MGR_NAME", 77);
            ibmMQIAMap.put( "MQIA_SIGNATURE_ALGORITHM", 236);
            ibmMQIAMap.put( "MQIA_SSL_EVENT", 75);
            ibmMQIAMap.put( "MQIA_SSL_FIPS_REQUIRED", 92);
            ibmMQIAMap.put( "MQIA_SSL_RESET_COUNT", 76);
            ibmMQIAMap.put( "MQIA_SSL_TASKS", 69);
            ibmMQIAMap.put( "MQIA_START_STOP_EVENT", 52);
            ibmMQIAMap.put( "MQIA_STATISTICS_AUTO_CLUSSDR", 130);
            ibmMQIAMap.put( "MQIA_STATISTICS_CHANNEL", 129);
            ibmMQIAMap.put( "MQIA_STATISTICS_INTERVAL", 131);
            ibmMQIAMap.put( "MQIA_STATISTICS_MQI", 127);
            ibmMQIAMap.put( "MQIA_STATISTICS_Q", 128);
            ibmMQIAMap.put( "MQIA_SUB_CONFIGURATION_EVENT", 242);
            ibmMQIAMap.put( "MQIA_SUB_COUNT", 204);
            ibmMQIAMap.put( "MQIA_SUB_SCOPE", 218);
            ibmMQIAMap.put( "MQIA_SUITE_B_STRENGTH", 247);
            ibmMQIAMap.put( "MQIA_SYNCPOINT", 30);
            ibmMQIAMap.put( "MQIA_TCP_CHANNELS", 114);
            ibmMQIAMap.put( "MQIA_TCP_KEEP_ALIVE", 115);
            ibmMQIAMap.put( "MQIA_TCP_STACK_TYPE", 116);
            ibmMQIAMap.put( "MQIA_TIME_SINCE_RESET", 35);
            ibmMQIAMap.put( "MQIA_TOLERATE_UNPROTECTED", 235);
            ibmMQIAMap.put( "MQIA_TOPIC_DEF_PERSISTENCE", 185);
            ibmMQIAMap.put( "MQIA_TOPIC_NODE_COUNT", 253);
            ibmMQIAMap.put( "MQIA_TOPIC_TYPE", 208);
            ibmMQIAMap.put( "MQIA_TRACE_ROUTE_RECORDING", 137);
            ibmMQIAMap.put( "MQIA_TREE_LIFE_TIME", 183);
            ibmMQIAMap.put( "MQIA_TRIGGER_CONTROL", 24);
            ibmMQIAMap.put( "MQIA_TRIGGER_DEPTH", 29);
            ibmMQIAMap.put( "MQIA_TRIGGER_INTERVAL", 25);
            ibmMQIAMap.put( "MQIA_TRIGGER_MSG_PRIORITY", 26);
            ibmMQIAMap.put( "MQIA_TRIGGER_RESTART", 91);
            ibmMQIAMap.put( "MQIA_TRIGGER_TYPE", 28);
            ibmMQIAMap.put( "MQIA_UR_DISP", 222);
            ibmMQIAMap.put( "MQIA_USAGE", 12);
            ibmMQIAMap.put( "MQIA_USER_LIST", 2000);
            ibmMQIAMap.put( "MQIA_USE_DEAD_LETTER_Q", 234);
            ibmMQIAMap.put( "MQIA_WILDCARD_OPERATION", 216);
            ibmMQIAMap.put( "MQIA_XR_CAPABILITY", 243);
        }

    }

    public static String getMQEXTString(Integer num) { initializeConstants(); return ibmMQEXTMap.get(num); }
    public static String getMQUOWTString(Integer num) { initializeConstants(); return ibmMQUOWTMap.get(num); }
    public static String getMQCFTYPEString(Integer num) { initializeConstants(); return ibmMQCFTYPEMap.get(num); }
    public static String getMQRDNSString(Integer num) { initializeConstants(); return ibmMQRDNSMap.get(num); }
    public static String getMQRTString(Integer num) { initializeConstants(); return ibmMQRTMap.get(num); }
    public static String getMQCLXQString(Integer num) { initializeConstants(); return ibmMQCLXQMap.get(num); }
    public static String getMQCHLAString(Integer num) { initializeConstants(); return ibmMQCHLAMap.get(num); }
    public static String getMQEXTATTRSString(Integer num) { initializeConstants(); return ibmMQEXTATTRSMap.get(num); }
    public static String getMQSUBTYPEString(Integer num) { initializeConstants(); return ibmMQSUBTYPEMap.get(num); }
    public static String getMQCHKString(Integer num) { initializeConstants(); return ibmMQCHKMap.get(num); }
    public static String getMQCFSTString(Integer num) { initializeConstants(); return ibmMQCFSTMap.get(num); }
    public static String getMQCLRSString(Integer num) { initializeConstants(); return ibmMQCLRSMap.get(num); }
    public static String getMQACTString(Integer num) { initializeConstants(); return ibmMQACTMap.get(num); }
    public static String getMQRQString(Integer num) { initializeConstants(); return ibmMQRQMap.get(num); }
    public static String getMQSYSPString(Integer num) { initializeConstants(); return ibmMQSYSPMap.get(num); }
    public static String getMQQSOString(Integer num) { initializeConstants(); return ibmMQQSOMap.get(num); }
    public static String getMQIAMOString(Integer num) { initializeConstants(); return ibmMQIAMOMap.get(num); }
    public static String getMQSECITEMString(Integer num) { initializeConstants(); return ibmMQSECITEMMap.get(num); }
    public static String getMQCHRRString(Integer num) { initializeConstants(); return ibmMQCHRRMap.get(num); }
    public static String getMQCHLDString(Integer num) { initializeConstants(); return ibmMQCHLDMap.get(num); }
    public static String getMQCHIDSString(Integer num) { initializeConstants(); return ibmMQCHIDSMap.get(num); }
    public static String getMQCFHString(Integer num) { initializeConstants(); return ibmMQCFHMap.get(num); }
    public static String getMQCFBFString(Integer num) { initializeConstants(); return ibmMQCFBFMap.get(num); }
    public static String getMQSUSString(Integer num) { initializeConstants(); return ibmMQSUSMap.get(num); }
    public static String getMQPOString(Integer num) { initializeConstants(); return ibmMQPOMap.get(num); }
    public static String getMQCHSHString(Integer num) { initializeConstants(); return ibmMQCHSHMap.get(num); }
    public static String getMQPSSTString(Integer num) { initializeConstants(); return ibmMQPSSTMap.get(num); }
    public static String getMQCLSTString(Integer num) { initializeConstants(); return ibmMQCLSTMap.get(num); }
    public static String getMQCACFString(Integer num) { initializeConstants(); return ibmMQCACFMap.get(num); }
    public static String getMQSECCOMMString(Integer num) { initializeConstants(); return ibmMQSECCOMMMap.get(num); }
    public static String getMQQSOTString(Integer num) { initializeConstants(); return ibmMQQSOTMap.get(num); }
    public static String getMQCHSSTATEString(Integer num) { initializeConstants(); return ibmMQCHSSTATEMap.get(num); }
    public static String getMQHSTATEString(Integer num) { initializeConstants(); return ibmMQHSTATEMap.get(num); }
    public static String getMQPSString(Integer num) { initializeConstants(); return ibmMQPSMap.get(num); }
    public static String getMQQMFACString(Integer num) { initializeConstants(); return ibmMQQMFACMap.get(num); }
    public static String getMQSString(Integer num) { initializeConstants(); return ibmMQSMap.get(num); }
    public static String getMQGACFString(Integer num) { initializeConstants(); return ibmMQGACFMap.get(num); }
    public static String getMQRPString(Integer num) { initializeConstants(); return ibmMQRPMap.get(num); }
    public static String getMQBACFString(Integer num) { initializeConstants(); return ibmMQBACFMap.get(num); }
    public static String getMQSYNCPOINTString(Integer num) { initializeConstants(); return ibmMQSYNCPOINTMap.get(num); }
    public static String getMQSECTYPEString(Integer num) { initializeConstants(); return ibmMQSECTYPEMap.get(num); }
    public static String getMQMLPString(Integer num) { initializeConstants(); return ibmMQMLPMap.get(num); }
    public static String getMQLDAPString(Integer num) { initializeConstants(); return ibmMQLDAPMap.get(num); }
    public static String getMQDISCONNECTString(Integer num) { initializeConstants(); return ibmMQDISCONNECTMap.get(num); }
    public static String getMQRARString(Integer num) { initializeConstants(); return ibmMQRARMap.get(num); }
    public static String getMQCFBSString(Integer num) { initializeConstants(); return ibmMQCFBSMap.get(num); }
    public static String getMQBPLOCATIONString(Integer num) { initializeConstants(); return ibmMQBPLOCATIONMap.get(num); }
    public static String getMQLDAPCString(Integer num) { initializeConstants(); return ibmMQLDAPCMap.get(num); }
    public static String getMQMCPString(Integer num) { initializeConstants(); return ibmMQMCPMap.get(num); }
    public static String getMQCAUTString(Integer num) { initializeConstants(); return ibmMQCAUTMap.get(num); }
    public static String getMQCACHString(Integer num) { initializeConstants(); return ibmMQCACHMap.get(num); }
    public static String getMQCFINString(Integer num) { initializeConstants(); return ibmMQCFINMap.get(num); }
    public static String getMQSECSWString(Integer num) { initializeConstants(); return ibmMQSECSWMap.get(num); }
    public static String getMQSELTYPEString(Integer num) { initializeConstants(); return ibmMQSELTYPEMap.get(num); }
    public static String getMQASString(Integer num) { initializeConstants(); return ibmMQASMap.get(num); }
    public static String getMQTIMEString(Integer num) { initializeConstants(); return ibmMQTIMEMap.get(num); }
    public static String getMQCFOPString(Integer num) { initializeConstants(); return ibmMQCFOPMap.get(num); }
    public static String getMQQMTString(Integer num) { initializeConstants(); return ibmMQQMTMap.get(num); }
    public static String getMQQMSTAString(Integer num) { initializeConstants(); return ibmMQQMSTAMap.get(num); }
    public static String getMQCMDString(Integer num) { initializeConstants(); return ibmMQCMDMap.get(num); }
    public static String getMQRCCFString(Integer num) { initializeConstants(); return ibmMQRCCFMap.get(num); }
    public static String getMQCLRTString(Integer num) { initializeConstants(); return ibmMQCLRTMap.get(num); }
    public static String getMQCFILString(Integer num) { initializeConstants(); return ibmMQCFILMap.get(num); }
    public static String getMQEVOString(Integer num) { initializeConstants(); return ibmMQEVOMap.get(num); }
    public static String getMQString(Integer num) { initializeConstants(); return ibmMQMap.get(num); }
    public static String getMQIDOString(Integer num) { initializeConstants(); return ibmMQIDOMap.get(num); }
    public static String getMQCFOString(Integer num) { initializeConstants(); return ibmMQCFOMap.get(num); }
    public static String getMQCAMOString(Integer num) { initializeConstants(); return ibmMQCAMOMap.get(num); }
    public static String getMQNSHString(Integer num) { initializeConstants(); return ibmMQNSHMap.get(num); }
    public static String getMQCHSRString(Integer num) { initializeConstants(); return ibmMQCHSRMap.get(num); }
    public static String getMQSCOString(Integer num) { initializeConstants(); return ibmMQSCOMap.get(num); }
    public static String getMQPAGECLASString(Integer num) { initializeConstants(); return ibmMQPAGECLASMap.get(num); }
    public static String getMQCFIFString(Integer num) { initializeConstants(); return ibmMQCFIFMap.get(num); }
    public static String getMQIACFString(Integer num) { initializeConstants(); return ibmMQIACFMap.get(num); }
    public static String getMQQSIEString(Integer num) { initializeConstants(); return ibmMQQSIEMap.get(num); }
    public static String getMQFCString(Integer num) { initializeConstants(); return ibmMQFCMap.get(num); }
    public static String getMQCFTString(Integer num) { initializeConstants(); return ibmMQCFTMap.get(num); }
    public static String getnoneString(Integer num) { initializeConstants(); return ibmnoneMap.get(num); }
    public static String getMQCLROUTEString(Integer num) { initializeConstants(); return ibmMQCLROUTEMap.get(num); }
    public static String getMQMULCString(Integer num) { initializeConstants(); return ibmMQMULCMap.get(num); }
    public static String getMQROUTEString(Integer num) { initializeConstants(); return ibmMQROUTEMap.get(num); }
    public static String getMQCHTABString(Integer num) { initializeConstants(); return ibmMQCHTABMap.get(num); }
    public static String getMQCFACCESSString(Integer num) { initializeConstants(); return ibmMQCFACCESSMap.get(num); }
    public static String getMQUAString(Integer num) { initializeConstants(); return ibmMQUAMap.get(num); }
    public static String getMQBTString(Integer num) { initializeConstants(); return ibmMQBTMap.get(num); }
    public static String getMQMCASString(Integer num) { initializeConstants(); return ibmMQMCASMap.get(num); }
    public static String getMQETString(Integer num) { initializeConstants(); return ibmMQETMap.get(num); }
    public static String getMQAUTHString(Integer num) { initializeConstants(); return ibmMQAUTHMap.get(num); }
    public static String getMQEPHString(Integer num) { initializeConstants(); return ibmMQEPHMap.get(num); }
    public static String getMQCMDIString(Integer num) { initializeConstants(); return ibmMQCMDIMap.get(num); }
    public static String getMQSTDBYString(Integer num) { initializeConstants(); return ibmMQSTDBYMap.get(num); }
    public static String getMQQOString(Integer num) { initializeConstants(); return ibmMQQOMap.get(num); }
    public static String getMQUCIString(Integer num) { initializeConstants(); return ibmMQUCIMap.get(num); }
    public static String getMQIACHString(Integer num) { initializeConstants(); return ibmMQIACHMap.get(num); }
    public static String getMQCFCString(Integer num) { initializeConstants(); return ibmMQCFCMap.get(num); }
    public static String getMQEVRString(Integer num) { initializeConstants(); return ibmMQEVRMap.get(num); }
    public static String getMQQSUMString(Integer num) { initializeConstants(); return ibmMQQSUMMap.get(num); }
    public static String getMQCFGRString(Integer num) { initializeConstants(); return ibmMQCFGRMap.get(num); }
    public static String getMQADPCTXString(Integer num) { initializeConstants(); return ibmMQADPCTXMap.get(num); }
    public static String getMQQSGSString(Integer num) { initializeConstants(); return ibmMQQSGSMap.get(num); }
    public static String getMQUSAGEString(Integer num) { initializeConstants(); return ibmMQUSAGEMap.get(num); }
    public static String getMQCFSFString(Integer num) { initializeConstants(); return ibmMQCFSFMap.get(num); }
    public static String getMQUOWSTString(Integer num) { initializeConstants(); return ibmMQUOWSTMap.get(num); }
    public static String getMQGURString(Integer num) { initializeConstants(); return ibmMQGURMap.get(num); }
    public static String getMQINBDString(Integer num) { initializeConstants(); return ibmMQINBDMap.get(num); }
    public static String getMQDOPTString(Integer num) { initializeConstants(); return ibmMQDOPTMap.get(num); }
    public static String getMQMODEString(Integer num) { initializeConstants(); return ibmMQMODEMap.get(num); }
    public static String getMQOPMODEString(Integer num) { initializeConstants(); return ibmMQOPMODEMap.get(num); }
    public static String getMQMATCHString(Integer num) { initializeConstants(); return ibmMQMATCHMap.get(num); }
    public static String getMQUIDSUPPString(Integer num) { initializeConstants(); return ibmMQUIDSUPPMap.get(num); }
    public static String getMQSYSOBJString(Integer num) { initializeConstants(); return ibmMQSYSOBJMap.get(num); }
    public static String getMQCFSLString(Integer num) { initializeConstants(); return ibmMQCFSLMap.get(num); }
    public static String getMQOPERString(Integer num) { initializeConstants(); return ibmMQOPERMap.get(num); }
    public static String getMQQMDTString(Integer num) { initializeConstants(); return ibmMQQMDTMap.get(num); }
    public static String getMQUNDELIVEREDString(Integer num) { initializeConstants(); return ibmMQUNDELIVEREDMap.get(num); }
    public static String getMQCFSTATUSString(Integer num) { initializeConstants(); return ibmMQCFSTATUSMap.get(num); }
    public static String getMQCHSString(Integer num) { initializeConstants(); return ibmMQCHSMap.get(num); }
    public static Integer getMQIAValue(String name) { initializeConstants(); return ibmMQIAMap.get(name); }
}
