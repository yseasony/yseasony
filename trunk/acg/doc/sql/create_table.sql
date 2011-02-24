CREATE TABLE acg_authority (
  id   BIGINT   NOT NULL   AUTO_INCREMENT,
  name VARCHAR(255)   NOT NULL   UNIQUE,
    PRIMARY KEY ( id ))
ENGINE = INNODB;
CREATE TABLE acg_role (
  id   BIGINT   NOT NULL   AUTO_INCREMENT,
  name VARCHAR(255)   NOT NULL   UNIQUE,
    PRIMARY KEY ( id ))
ENGINE = INNODB;
CREATE TABLE acg_role_authority (
  role_id      BIGINT   NOT NULL,
  authority_id BIGINT   NOT NULL)
ENGINE = INNODB;
CREATE TABLE acg_user (
  id         BIGINT   NOT NULL   AUTO_INCREMENT,
  email      VARCHAR(255),
  login_name VARCHAR(255)   NOT NULL   UNIQUE,
  name       VARCHAR(255),
  PASSWORD   VARCHAR(255),
    PRIMARY KEY ( id ))
ENGINE = INNODB;
CREATE TABLE acg_user_role (
  user_id BIGINT   NOT NULL,
  role_id BIGINT   NOT NULL)
ENGINE = INNODB;
ALTER TABLE acg_role_authority
ADD CONSTRAINT fkae243466de3fb930 FOREIGN KEY( role_id) references acg_role(id);
ALTER TABLE acg_role_authority
ADD CONSTRAINT fkae2434663fe97564 FOREIGN KEY( authority_id) references acg_authority(id);
ALTER TABLE acg_user_role
ADD CONSTRAINT fkfe85cb3ede3fb930 FOREIGN KEY( role_id) references acg_role(id);
ALTER TABLE acg_user_role
ADD CONSTRAINT fkfe85cb3e836a7d10 FOREIGN KEY( user_id) references acg_user(id);
