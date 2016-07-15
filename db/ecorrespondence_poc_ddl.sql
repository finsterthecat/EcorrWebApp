/*==============================================================*/
/* DBMS name:      ORACLE Version 10g                           */
/* Created on:     07/14/2016 5:14:30 PM                        */
/*==============================================================*/


alter table INCOMING_CORRESPONDENCE
   drop constraint FK_INCOMING_IS_PRIMAR_CLIENT_S;

alter table INCOMING_CORRESPONDENCE
   drop constraint FK_INCOMING_RESPONDS__SINGLE_C;

alter table NOTE
   drop constraint FK_NOTE_HAS_SINGLE_C;

alter table PRINCIPAL_ORG_ROLE
   drop constraint FK_PRINCIPA_ASSIGNED__SEC_ROLE;

alter table PRINCIPAL_ORG_ROLE
   drop constraint FK_PRINCIPA_HAS_USER;

alter table SINGLE_CORRESPONDENCE
   drop constraint FK_SINGLE_C_IS_PRIMAR_CLIENT_S;

drop table CLIENT_SNAPSHOT cascade constraints;

drop index IS_PRIMARY_CLIENT_FOR_FK;

drop table INCOMING_CORRESPONDENCE cascade constraints;

drop index HAS_FK2;

drop table NOTE cascade constraints;

drop index ASSIGNED_TO_FK;

drop index HAS_FK;

drop table PRINCIPAL_ORG_ROLE cascade constraints;

drop table SEC_ROLE cascade constraints;

drop index IS_PRIMARY_CLIENT_FOR_FK2;

drop table SINGLE_CORRESPONDENCE cascade constraints;

drop table "USER" cascade constraints;

/*==============================================================*/
/* Table: CLIENT_SNAPSHOT                                       */
/*==============================================================*/
create table CLIENT_SNAPSHOT  (
   ID                   INTEGER                         not null,
   HONORIFIC_TITLE      VARCHAR2(200),
   FIRST_NAME           VARCHAR2(200)                   not null,
   MIDDLE_NAME          VARCHAR2(200),
   LAST_NAME            VARCHAR2(200)                   not null,
   EMAIL_TYPE_CODE      VARCHAR2(20)                   
      constraint CKC_EMAIL_TYPE_CODE_CLIENT_S check (EMAIL_TYPE_CODE is null or (EMAIL_TYPE_CODE in ('WORK','PERSONAL'))),
   EMAIL_ADDRESS        VARCHAR2(100),
   PHONE_TYPE_CODE      VARCHAR2(20)                   
      constraint CKC_PHONE_TYPE_CODE_CLIENT_S check (PHONE_TYPE_CODE is null or (PHONE_TYPE_CODE in ('HOME','WORK','MOBILE','MAIN'))),
   AREA_CODE            VARCHAR2(3),
   LOCAL_NUMBER         VARCHAR2(10),
   EXTENSION            VARCHAR2(5),
   POSTAL_ADDRESS_TYPE_CODE VARCHAR2(20)                   
      constraint CKC_POSTAL_ADDRESS_TY_CLIENT_S check (POSTAL_ADDRESS_TYPE_CODE is null or (POSTAL_ADDRESS_TYPE_CODE in ('STREET','MAILING'))),
   STREET_ADDRESS_LINE1 VARCHAR2(100),
   STREET_ADDRESS_LINE2 VARCHAR2(100),
   PROVINCE_CODE        VARCHAR2(2)                    
      constraint CKC_PROVINCE_CODE_CLIENT_S check (PROVINCE_CODE is null or (PROVINCE_CODE in ('PE','NF','NS','NB','PQ','ON','MB','SK','AB','BC','NU','YT','NT','<Val16>'))),
   POSTAL_CODE          VARCHAR2(20),
   COUNTRY_CODE         VARCHAR2(3),
   constraint PK_CLIENT_SNAPSHOT primary key (ID)
);

comment on column CLIENT_SNAPSHOT.ID is
'Primary id.';

comment on column CLIENT_SNAPSHOT.HONORIFIC_TITLE is
'Honorific title ';

comment on column CLIENT_SNAPSHOT.FIRST_NAME is
'First name';

comment on column CLIENT_SNAPSHOT.MIDDLE_NAME is
'Middle name';

comment on column CLIENT_SNAPSHOT.LAST_NAME is
'Last name';

comment on column CLIENT_SNAPSHOT.EMAIL_TYPE_CODE is
'Email type: Work or Personal';

comment on column CLIENT_SNAPSHOT.EMAIL_ADDRESS is
'Email address string';

comment on column CLIENT_SNAPSHOT.PHONE_TYPE_CODE is
'Type of phone';

comment on column CLIENT_SNAPSHOT.AREA_CODE is
'Telephone Area Code';

comment on column CLIENT_SNAPSHOT.LOCAL_NUMBER is
'The phone number. All non-digits are stripped out.';

comment on column CLIENT_SNAPSHOT.EXTENSION is
'Telephone Extension number';

comment on column CLIENT_SNAPSHOT.POSTAL_ADDRESS_TYPE_CODE is
'Type of Postal Address';

comment on column CLIENT_SNAPSHOT.STREET_ADDRESS_LINE1 is
'Street address line 1.';

comment on column CLIENT_SNAPSHOT.STREET_ADDRESS_LINE2 is
'Street Address Line 2.';

comment on column CLIENT_SNAPSHOT.PROVINCE_CODE is
'Province';

comment on column CLIENT_SNAPSHOT.POSTAL_CODE is
'Postal Code
';

comment on column CLIENT_SNAPSHOT.COUNTRY_CODE is
'Country';

/*==============================================================*/
/* Table: INCOMING_CORRESPONDENCE                               */
/*==============================================================*/
create table INCOMING_CORRESPONDENCE  (
   ID                   INTEGER                         not null,
   CLIENT_SNAPSHOT_ID   INTEGER,
   SINGLE_CORRESPONDENCE_ID INTEGER,
   DOCUMENT_DATE        DATE                            not null,
   RECEIVED_TIME        TIMESTAMP                       not null,
   ENTERED_TIME         TIMESTAMP                       not null,
   INCOMING_CHANNEL     VARCHAR2(20)                    not null
      constraint CKC_INCOMING_CHANNEL_INCOMING check (INCOMING_CHANNEL in ('EMAIL','WEBFORM','POST','FAXDIGITAL','FAXPHYSICAL','PHONE','WALKIN','HANDDELIVERED','INDIRECT','REFERRAL')),
   RECIPIENT_TYPE_CODE  VARCHAR2(20)                    not null
      constraint CKC_RECIPIENT_TYPE_CO_INCOMING check (RECIPIENT_TYPE_CODE in ('PREMIER','MINISTER','ASSOCMINISTER','DEPMINISTER','ASSOCDEPMINISTER','ASSISTDEPMINISTER','PARLASSIST','PREMIERSTAFF','MINISTERSTAFF','DIRECTOR','MANAGER','MINISTRYSTAFF','MINISTRY') and RECIPIENT_TYPE_CODE = upper(RECIPIENT_TYPE_CODE)),
   SUBJECT              VARCHAR2(1000),
   INCOMING_STATUS_CODE VARCHAR2(20)                   
      constraint CKC_INCOMING_STATUS_C_INCOMING check (INCOMING_STATUS_CODE is null or (INCOMING_STATUS_CODE in ('UNASSIGNED','ASSIGNED','NORESPONSE'))),
   constraint PK_INCOMING_CORRESPONDENCE primary key (ID)
);

comment on table INCOMING_CORRESPONDENCE is
'The documents sent to the Ontario Government that may require response.

Incoming Correspondence captures all the documents received at one time. Each attachment is a separate document. If it is an email, the email body is a separate docment.';

comment on column INCOMING_CORRESPONDENCE.CLIENT_SNAPSHOT_ID is
'Primary id.';

comment on column INCOMING_CORRESPONDENCE.DOCUMENT_DATE is
'The date on the document.';

comment on column INCOMING_CORRESPONDENCE.RECEIVED_TIME is
'The time this was received.';

comment on column INCOMING_CORRESPONDENCE.ENTERED_TIME is
'The time the correspondence was entered into the system.';

comment on column INCOMING_CORRESPONDENCE.INCOMING_CHANNEL is
'The channel on which this was received. Values include Email, Web Form, Post, Phone.';

comment on column INCOMING_CORRESPONDENCE.RECIPIENT_TYPE_CODE is
'The type of recipient. Values include Premier, Minister, Associate Minister, and Director.';

comment on column INCOMING_CORRESPONDENCE.SUBJECT is
'The subject of the correspondence.';

/*==============================================================*/
/* Index: IS_PRIMARY_CLIENT_FOR_FK                              */
/*==============================================================*/
create index IS_PRIMARY_CLIENT_FOR_FK on INCOMING_CORRESPONDENCE (
   CLIENT_SNAPSHOT_ID ASC
);

/*==============================================================*/
/* Table: NOTE                                                  */
/*==============================================================*/
create table NOTE  (
   ID                   INTEGER                         not null,
   SINGLE_CORRESPONDENCE_ID INTEGER                         not null,
   NOTE_TYPE_CODE       VARCHAR2(20)                    not null
      constraint CKC_NOTE_TYPE_CODE_NOTE check (NOTE_TYPE_CODE in ('INSTRUCTION','APPROVER','REVIEWER')),
   NOTE                 VARCHAR2(4000)                  not null,
   CREATED_BY           VARCHAR2(100)                   not null,
   CREATED_TIME         TIMESTAMP                       not null,
   constraint PK_NOTE primary key (ID)
);

comment on table NOTE is
'A piece of multi-line text';

comment on column NOTE.ID is
'Primary identifier
';

comment on column NOTE.NOTE_TYPE_CODE is
'The type of note:
INSTRUCTION
APPROVER
REVIEWER
';

comment on column NOTE.NOTE is
'A note on the external reference.';

comment on column NOTE.CREATED_BY is
'Who created this?';

comment on column NOTE.CREATED_TIME is
'Created when?';

/*==============================================================*/
/* Index: HAS_FK2                                               */
/*==============================================================*/
create index HAS_FK2 on NOTE (
   SINGLE_CORRESPONDENCE_ID ASC
);

/*==============================================================*/
/* Table: PRINCIPAL_ORG_ROLE                                    */
/*==============================================================*/
create table PRINCIPAL_ORG_ROLE  (
   ID                   INTEGER                         not null,
   PRINCIPAL_ID         INTEGER                         not null,
   SEC_ROLE_ID          INTEGER                         not null,
   constraint PK_PRINCIPAL_ORG_ROLE primary key (ID)
);

/*==============================================================*/
/* Index: HAS_FK                                                */
/*==============================================================*/
create index HAS_FK on PRINCIPAL_ORG_ROLE (
   PRINCIPAL_ID ASC
);

/*==============================================================*/
/* Index: ASSIGNED_TO_FK                                        */
/*==============================================================*/
create index ASSIGNED_TO_FK on PRINCIPAL_ORG_ROLE (
   SEC_ROLE_ID ASC
);

/*==============================================================*/
/* Table: SEC_ROLE                                              */
/*==============================================================*/
create table SEC_ROLE  (
   ID                   INTEGER                         not null,
   SEC_ROLE_CODE        VARCHAR2(20)                    not null,
   SEC_ROLE_TYPE        VARCHAR2(20)                    not null
      constraint CKC_SEC_ROLE_TYPE_SEC_ROLE check (SEC_ROLE_TYPE in ('ASSIGNMENT','SECURITY-ROLE')),
   constraint PK_SEC_ROLE primary key (ID)
);

comment on column SEC_ROLE.SEC_ROLE_CODE is
'So far there can be:
CU_WRITER
CU_LEAD
CU_COORDINATOR
MO_POLICYSPECIALIST
MIN_SYSADMIN
TRANSLATOR
SYSADMIN

More can be added as needs dictate.';

/*==============================================================*/
/* Table: SINGLE_CORRESPONDENCE                                 */
/*==============================================================*/
create table SINGLE_CORRESPONDENCE  (
   ID                   INTEGER                         not null,
   CLIENT_SNAPSHOT_ID   INTEGER,
   CASE_NUMBER          VARCHAR2(30)                    not null,
   CASE_STATUS_CODE     VARCHAR2(20)                    not null
      constraint CKC_CASE_STATUS_CODE_SINGLE_C check (CASE_STATUS_CODE in ('ACTIVE','CLOSED')),
   CLOSED_DATE          DATE,
   DUE_DATE             DATE                            not null,
   SENSITIVE_FLAG       SMALLINT                        not null,
   CONTENTIOUS_FLAG     SMALLINT                        not null,
   CREATE_TIME          TIMESTAMP                       not null,
   constraint PK_SINGLE_CORRESPONDENCE primary key (ID)
);

comment on column SINGLE_CORRESPONDENCE.CLIENT_SNAPSHOT_ID is
'Primary id.';

comment on column SINGLE_CORRESPONDENCE.CASE_NUMBER is
'External value that uniquely identifies a case.';

comment on column SINGLE_CORRESPONDENCE.CASE_STATUS_CODE is
'Case Status';

comment on column SINGLE_CORRESPONDENCE.CLOSED_DATE is
'Date on which case was closed.';

comment on column SINGLE_CORRESPONDENCE.DUE_DATE is
'The date this is due to be completed.';

comment on column SINGLE_CORRESPONDENCE.SENSITIVE_FLAG is
'Incoming content contains sensitive or personal information.';

comment on column SINGLE_CORRESPONDENCE.CONTENTIOUS_FLAG is
'Flag indicating if this case has any contentious content and/or client indications.';

comment on column SINGLE_CORRESPONDENCE.CREATE_TIME is
'Date case was created.';

/*==============================================================*/
/* Index: IS_PRIMARY_CLIENT_FOR_FK2                             */
/*==============================================================*/
create index IS_PRIMARY_CLIENT_FOR_FK2 on SINGLE_CORRESPONDENCE (
   CLIENT_SNAPSHOT_ID ASC
);

/*==============================================================*/
/* Table: "USER"                                                */
/*==============================================================*/
create table "USER"  (
   PRINCIPAL_ID         INTEGER                         not null,
   USER_NAME            VARCHAR2(100)                   not null,
   FIRST_NAME           VARCHAR2(200)                   not null,
   MIDDLE_NAME          VARCHAR2(200),
   LAST_NAME            VARCHAR2(200)                   not null,
   constraint PK_USER primary key (PRINCIPAL_ID)
);

comment on column "USER".USER_NAME is
'Name of the user.';

comment on column "USER".FIRST_NAME is
'First name';

comment on column "USER".MIDDLE_NAME is
'Middle name';

comment on column "USER".LAST_NAME is
'Last name';

alter table INCOMING_CORRESPONDENCE
   add constraint FK_INCOMING_IS_PRIMAR_CLIENT_S foreign key (CLIENT_SNAPSHOT_ID)
      references CLIENT_SNAPSHOT (ID);

alter table INCOMING_CORRESPONDENCE
   add constraint FK_INCOMING_RESPONDS__SINGLE_C foreign key (SINGLE_CORRESPONDENCE_ID)
      references SINGLE_CORRESPONDENCE (ID);

alter table NOTE
   add constraint FK_NOTE_HAS_SINGLE_C foreign key (SINGLE_CORRESPONDENCE_ID)
      references SINGLE_CORRESPONDENCE (ID);

alter table PRINCIPAL_ORG_ROLE
   add constraint FK_PRINCIPA_ASSIGNED__SEC_ROLE foreign key (SEC_ROLE_ID)
      references SEC_ROLE (ID);

alter table PRINCIPAL_ORG_ROLE
   add constraint FK_PRINCIPA_HAS_USER foreign key (PRINCIPAL_ID)
      references "USER" (PRINCIPAL_ID);

alter table SINGLE_CORRESPONDENCE
   add constraint FK_SINGLE_C_IS_PRIMAR_CLIENT_S foreign key (CLIENT_SNAPSHOT_ID)
      references CLIENT_SNAPSHOT (ID);

