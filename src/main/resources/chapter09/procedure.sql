/* 建立存储过程 */
DELIMITER //
CREATE PROCEDURE count_role(
    IN  p_role_name VARCHAR(20),
    OUT count_total INT
)
BEGIN
  SELECT count(*) INTO count_total FROM t_role WHERE role_name LIKE '%' || p_role_name || '%';
END//
DELIMITER ;


/* MySQL存储过程不支持返回多行结果集，可以考虑保存到临时表中，或者直接查询.
   只能在数据库里面遍历操作, 以下SQL不完整，不能用于测试 */
DELIMITER //
CREATE PROCEDURE find_role(
    IN  p_role_name VARCHAR(20),
    IN  p_start INT,
    IN  p_end INT
)
BEGIN
  DECLARE ref_cur CURSOR FOR SELECT count(*) INTO count_total FROM t_role WHERE role_name LIKE '%' || p_role_name || '%';

OPEN ref_cur

CLOSE ref_cur

END//
DELIMITER ;
