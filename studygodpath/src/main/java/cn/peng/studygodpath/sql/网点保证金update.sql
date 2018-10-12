drop procedure if exists updateDeposit;
/*更新网点保证金数据  DECLARE */
create procedure updateDeposit()
begin
	declare siteId BIGINT;
	declare hasPrice double;
	declare applyPrice double;
	declare returnPrice double;
	declare zore double default 0;

	-- 遍历数据结束标志
	declare done int default false;
	
	declare site_Cur cursor for select user_id from t_accessory_deposit where user_type = "2";
	

	-- 将结束标志绑定到游标
	declare continue HANDLER for not found set done = true;
	
	open site_Cur;
	
		read_loop: LOOP
			-- 提取游标里的数据，这里只有一个，多个的话也一样；
			FETCH site_Cur into siteId;
			-- 声明结束的时候
			IF done THEN
				LEAVE read_loop;
			END IF;
			/*统计网点库存总金额*/
			select ifnull(sum(ROUND((ss.accessory_in_price * ss.amount),2)),0) into hasPrice from  warehouse_site_storage ss 
				where ss.delete_flag = "N"and ss.site_id = siteId;
			/*统计网点申请总金额*/
			select ifnull(sum(round((wa.accessory_total_count * wa.accessory_total_price),2)),0) into applyPrice from warehouse_apply wa 
				where wa.warehouse_apply_status in ("B50", "C30","C50","D50") and wa.delete_flag = "N" and site_id = siteId and global_transaction_rollback is null;
			/*统计网点返件总金额*/
			select ifnull(sum(ROUND((rp.accessory_in_price * rp.accessory_count),2)),0) into returnPrice from warehouse_return_part rp 
				where rp.delete_flag = "N" and rp.warehouse_return_part_status in ("待返件","已返件") and rp.site_id = siteId;
			
			update t_accessory_deposit ad set ad.freeze_limit = hasPrice + applyPrice + returnPrice where ad.user_id = siteId;
			update t_accessory_deposit ad set ad.available_limit = ad.deposit_limit - ad.freeze_limit where ad.user_id = siteId;
			
		END LOOP;
	close site_Cur;
end;

call updateDeposit();
drop procedure updateDeposit;  /*删除临时存储过程*/