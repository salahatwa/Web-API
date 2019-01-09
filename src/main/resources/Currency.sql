CREATE TABLE `authority` (
`id` bigint(20) NOT NULL,
`authority_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
PRIMARY KEY (`id`) 
)
ENGINE=MyISAM
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `authority_seq` (
`next_val` bigint(20) NULL DEFAULT NULL
)
ENGINE=MyISAM
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `mc40200` (
`currnidx` int(11) NOT NULL,
`crncydsc` varchar(31) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`crncydsca` varchar(61) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`curncyid` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`curtext_3` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`curtexta_3` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`crncysym` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`curtext_1` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`curtexta_1` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`dex_row_ts` datetime NULL DEFAULT NULL,
`decsymbl` smallint(6) NULL DEFAULT NULL,
`inclspac` bit(1) NULL DEFAULT NULL,
`negsymbl` smallint(6) NULL DEFAULT NULL,
`ngsmampc` smallint(6) NULL DEFAULT NULL,
`thoussym` smallint(6) NULL DEFAULT NULL,
`curtext_2` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`curtexta_2` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
PRIMARY KEY (`currnidx`) ,
INDEX `DEX_ROW_ID` (`currnidx`)
)
ENGINE=MyISAM
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `users` (
`id` bigint(20) NOT NULL,
`lastlogin` datetime NULL DEFAULT NULL,
`address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`enabled` bit(1) NULL DEFAULT NULL,
`lastpasswordresetdate` datetime NULL DEFAULT NULL,
`newpassword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`oldpassword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`website` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
PRIMARY KEY (`id`) ,
UNIQUE INDEX `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
)
ENGINE=MyISAM
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `user_authority` (
`user_id` bigint(20) NOT NULL,
`authority_id` bigint(20) NOT NULL,
INDEX `FKgvxjs381k6f48d5d2yi11uh89` (`authority_id`),
INDEX `FKhi46vu7680y1hwvmnnuh4cybx` (`user_id`)
)
ENGINE=MyISAM
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `user_seq` (
`next_val` bigint(20) NULL DEFAULT NULL
)
ENGINE=MyISAM
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;

