CREATE TABLE pelindo.tbl_user (
  user_id int(11) NOT NULL AUTO_INCREMENT,
  nama_lengkap varchar(255) COLLATE utf8mb4_unicode_520_ci NOT NULL DEFAULT '',
  username varchar(255) CHARACTER SET latin1 NOT NULL,
  status char CHARACTER SET latin1 NOT NULL DEFAULT 'A',
  created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;