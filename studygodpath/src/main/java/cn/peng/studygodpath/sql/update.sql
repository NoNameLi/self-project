drop procedure if exists updateDeposit;
/*更新网点保证金数据  DECLARE */
create procedure updateDeposit()
begin
	declare siteId BIGINT;
	declare hasPrice double;
	declare applyPrice double;
	declare zore double default 0;

	-- 遍历数据结束标志
	declare done int default false;
	
	declare site_Cur cursor for select user_id from t_accessory_deposit ;
	

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
			select sum(ROUND((ss.accessory_in_price * ss.amount),2)) into hasPrice from  warehouse_site_storage ss where ss.site_id = siteId;
			/*统计网点申请总金额*/
			select sum(round((wa.accessory_total_count * wa.accessory_total_price),2)) into applyPrice from warehouse_apply wa 
			where wa.warehouse_apply_status in ("B50", "C30","C50","D50") and site_id = siteId;
			
			if hasPrice + applyPrice > zore then
				update t_accessory_deposit ad set ad.freeze_limit = hasPrice + applyPrice where ad.user_id = siteId;
				update t_accessory_deposit ad set ad.available_limit = ad.deposit_limit - ad.freeze_limit where ad.user_id = siteId;
			end if;
		END LOOP;
	close site_Cur;
end;

call updateDeposit();
drop procedure updateDeposit;  /*删除临时存储过程*/