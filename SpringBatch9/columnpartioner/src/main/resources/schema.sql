--
--
--DROP TABLE IF EXISTS `batch_job_execution`;
--CREATE TABLE `batch_job_execution` (
--  `JOB_EXECUTION_ID` bigint NOT NULL,
--  `VERSION` bigint DEFAULT NULL,
--  `JOB_INSTANCE_ID` bigint NOT NULL,
--  `CREATE_TIME` datetime(6) NOT NULL,
--  `START_TIME` datetime(6) DEFAULT NULL,
--  `END_TIME` datetime(6) DEFAULT NULL,
--  `STATUS` varchar(10) DEFAULT NULL,
--  `EXIT_CODE` varchar(2500) DEFAULT NULL,
--  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
--  `LAST_UPDATED` datetime(6) DEFAULT NULL,
--  `JOB_CONFIGURATION_LOCATION` varchar(2500) DEFAULT NULL,
--  PRIMARY KEY (`JOB_EXECUTION_ID`),
--  KEY `JOB_INST_EXEC_FK` (`JOB_INSTANCE_ID`),
--  CONSTRAINT `JOB_INST_EXEC_FK` FOREIGN KEY (`JOB_INSTANCE_ID`) REFERENCES `batch_job_instance` (`JOB_INSTANCE_ID`)
--);
----
---- Table structure for table `batch_job_execution_context`
----
--
--DROP TABLE IF EXISTS `batch_job_execution_context`;
--CREATE TABLE `batch_job_execution_context` (
--  `JOB_EXECUTION_ID` bigint NOT NULL,
--  `SHORT_CONTEXT` varchar(2500) NOT NULL,
--  `SERIALIZED_CONTEXT` text,
--  PRIMARY KEY (`JOB_EXECUTION_ID`),
--  CONSTRAINT `JOB_EXEC_CTX_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`)
--);
--
----
---- Table structure for table `batch_job_execution_params`
----
--
--DROP TABLE IF EXISTS `batch_job_execution_params`;
--CREATE TABLE `batch_job_execution_params` (
--  `JOB_EXECUTION_ID` bigint NOT NULL,
--  `TYPE_CD` varchar(6) NOT NULL,
--  `KEY_NAME` varchar(100) NOT NULL,
--  `STRING_VAL` varchar(250) DEFAULT NULL,
--  `DATE_VAL` datetime(6) DEFAULT NULL,
--  `LONG_VAL` bigint DEFAULT NULL,
--  `DOUBLE_VAL` double DEFAULT NULL,
--  `IDENTIFYING` char(1) NOT NULL,
--  KEY `JOB_EXEC_PARAMS_FK` (`JOB_EXECUTION_ID`),
--  CONSTRAINT `JOB_EXEC_PARAMS_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`)
--);
--
----
---- Table structure for table `batch_job_execution_seq`
----
--
--DROP TABLE IF EXISTS `batch_job_execution_seq`;
--CREATE TABLE `batch_job_execution_seq` (
--  `ID` bigint NOT NULL,
--  `UNIQUE_KEY` char(1) NOT NULL,
--  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
--);
--
----
---- Table structure for table `batch_job_instance`
----
--
--DROP TABLE IF EXISTS `batch_job_instance`;
--CREATE TABLE `batch_job_instance` (
--  `JOB_INSTANCE_ID` bigint NOT NULL,
--  `VERSION` bigint DEFAULT NULL,
--  `JOB_NAME` varchar(100) NOT NULL,
--  `JOB_KEY` varchar(32) NOT NULL,
--  PRIMARY KEY (`JOB_INSTANCE_ID`),
--  UNIQUE KEY `JOB_INST_UN` (`JOB_NAME`,`JOB_KEY`)
--);
--
----
---- Table structure for table `batch_job_seq`
----
--
--DROP TABLE IF EXISTS `batch_job_seq`;
--
--CREATE TABLE `batch_job_seq` (
--  `ID` bigint NOT NULL,
--  `UNIQUE_KEY` char(1) NOT NULL,
--  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
--) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--
----
---- Table structure for table `batch_step_execution`
----
--
--DROP TABLE IF EXISTS `batch_step_execution`;
--CREATE TABLE `batch_step_execution` (
--  `STEP_EXECUTION_ID` bigint NOT NULL,
--  `VERSION` bigint NOT NULL,
--  `STEP_NAME` varchar(100) NOT NULL,
--  `JOB_EXECUTION_ID` bigint NOT NULL,
--  `START_TIME` datetime(6) NOT NULL,
--  `END_TIME` datetime(6) DEFAULT NULL,
--  `STATUS` varchar(10) DEFAULT NULL,
--  `COMMIT_COUNT` bigint DEFAULT NULL,
--  `READ_COUNT` bigint DEFAULT NULL,
--  `FILTER_COUNT` bigint DEFAULT NULL,
--  `WRITE_COUNT` bigint DEFAULT NULL,
--  `READ_SKIP_COUNT` bigint DEFAULT NULL,
--  `WRITE_SKIP_COUNT` bigint DEFAULT NULL,
--  `PROCESS_SKIP_COUNT` bigint DEFAULT NULL,
--  `ROLLBACK_COUNT` bigint DEFAULT NULL,
--  `EXIT_CODE` varchar(2500) DEFAULT NULL,
--  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
--  `LAST_UPDATED` datetime(6) DEFAULT NULL,
--  PRIMARY KEY (`STEP_EXECUTION_ID`),
--  KEY `JOB_EXEC_STEP_FK` (`JOB_EXECUTION_ID`),
--  CONSTRAINT `JOB_EXEC_STEP_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`)
--) ;
--
----
---- Table structure for table `batch_step_execution_context`
----
--
--DROP TABLE IF EXISTS `batch_step_execution_context`;
--CREATE TABLE `batch_step_execution_context` (
--  `STEP_EXECUTION_ID` bigint NOT NULL,
--  `SHORT_CONTEXT` varchar(2500) NOT NULL,
--  `SERIALIZED_CONTEXT` text,
--  PRIMARY KEY (`STEP_EXECUTION_ID`),
--  CONSTRAINT `STEP_EXEC_CTX_FK` FOREIGN KEY (`STEP_EXECUTION_ID`) REFERENCES `batch_step_execution` (`STEP_EXECUTION_ID`)
--);
--
----
---- Table structure for table `batch_step_execution_seq`
----
--
--DROP TABLE IF EXISTS `batch_step_execution_seq`;
--CREATE TABLE `batch_step_execution_seq` (
--  `ID` bigint NOT NULL,
--  `UNIQUE_KEY` char(1) NOT NULL,
--  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
--);

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
CREATE TABLE `transactions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dte` varchar(256) NOT NULL,
  `county` varchar(256) NOT NULL,
  `area` varchar(256) NOT NULL,
  `number` varchar(256) NOT NULL,
  `ttl_area` varchar(256) NOT NULL,
  `avg_area` varchar(256) NOT NULL,
  `ttl_trans_amt` varchar(256) NOT NULL,
  `min_trans_amt` varchar(256) NOT NULL,
  `max_trans_amt` varchar(256) NOT NULL,
  `unit_price_min` varchar(256) NOT NULL,
  `unit_price_max` varchar(256) NOT NULL,
  `unit_price_med` varchar(256) NOT NULL,
  `unit_price_avg` varchar(256) NOT NULL,
  `unit_price_std` varchar(256) NOT NULL,
  `mnth` varchar(256) NOT NULL,
  `yer` varchar(256) NOT NULL,
  `indx` varchar(256) NOT NULL,
  PRIMARY KEY (`id`)
) ;
