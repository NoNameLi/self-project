drop procedure if exists updateOrderBillTmp;

create procedure updateOrderBillTmp()
begin
	declare billDatetime varchar(50);
	declare billId BIGINT;
	declare orderId BIGINT;
	declare orderNumber varchar(50);

	-- 遍历数据结束标志
	declare done int default false;

	-- 重复日期分组
	declare site_Cur cursor for select order_id, replace(replace(bill_datetime,"年",'-'),"月份",'') from weixiu_order_bill_tmp where order_number in ('OR20160105120829','OR20160112008147');
	
	-- 将结束标志绑定到游标
	declare continue HANDLER for not found set done = true;

	-- DECLARE t_error INTEGER DEFAULT 0;  
	-- DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET t_error=1;  
	
	open site_Cur;
		read_loop: LOOP
			-- 提取游标里的数据，这里只有一个，多个的话也一样；
			FETCH site_Cur into billId, billDatetime;
			-- 声明结束的时候
			IF done THEN
				LEAVE read_loop;
			END IF;
			-- 查询 一条weixiu_order中未使用的记录
			select order_id, order_number into orderId,orderNumber from weixiu_order where mirage_remark <> 'bill0421' and from_maintain_site_id is null and date_format(create_datetime,'%Y-%m') = billDatetime  LIMIT 1;
			set done = false;
			if orderId is null then
				select order_id, order_number into orderId,orderNumber from weixiu_order where mirage_remark <> 'bill0421' and from_maintain_site_id is null and date_format(create_datetime,'%Y-%m') < billDatetime  LIMIT 1;
			end if;

			-- START TRANSACTION; 
			-- 更新 bill  order
				update weixiu_order_bill_tmp set order_number_new = orderNumber where billId;
				update weixiu_order set mirage_remark = "bill0421" where order_id = orderId;
			-- COMMIT;  

		
		END LOOP;
	close site_Cur;
end;

call updateOrderBillTmp();
drop procedure updateOrderBillTmp;  


