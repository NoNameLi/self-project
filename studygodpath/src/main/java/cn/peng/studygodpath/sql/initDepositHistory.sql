drop procedure if exists initDepositHistory;
/*初始化网点保证金履历数据  */
create procedure initDepositHistory()
begin
	declare depositId BIGINT;
	declare depositPrice double;
	declare freezePrice double;
	declare availablePrice double;
	declare zore double default 0;

	-- 遍历数据结束标志
	declare done int default false;
	
	declare site_Cur cursor for select accessory_deposit_id,deposit_limit,freeze_limit,available_limit from t_accessory_deposit;
	-- 将结束标志绑定到游标
	declare continue HANDLER for not found set done = true;
	
	open site_Cur;
	
		read_loop: LOOP
			-- 提取游标里的数据，这里只有一个，多个的话也一样；
			FETCH site_Cur into depositId,depositPrice,freezePrice,availablePrice;
			-- 声明结束的时候
			IF done THEN
				LEAVE read_loop;
			END IF;
			
			INSERT INTO `t_accessory_deposit_history` VALUES (null, depositId, depositPrice, freezePrice, availablePrice,
				0, 0, 0, null, null, null, null, '配件保证金盘点', '系统管理员', now(), null, null, NULL, 'N', NULL);

		END LOOP;
	close site_Cur;
end;

call initDepositHistory();
drop procedure initDepositHistory;  /*删除临时存储过程*/