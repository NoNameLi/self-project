-- 工单基本数据
---- 当日下单量
select count(*) from weixiu_order o where o.create_datetime > '2018-01-01 00:00' and o.create_datetime < '2018-01-01 23:59:59' 	and o.delete_flag = "N" and o.mirage_flag = "N" and o.new_mirage_flag ="N";
---- 当日完工量
select count(*) from weixiu_order o where o.order_finish_datetime > '2018-01-01 00:00' and o.order_finish_datetime < '2018-01-01 253.59.59' and o.delete_flag = "N" and o.mirage_flag = "N" and o.new_mirage_flag ="N";
---- 当日封单量

---- 当日直营数量
select count(*) from weixiu_order o where o.create_datetime > '2018-01-01 00:00' and o.create_datetime < '2018-01-01 23:59:59' and o.order_business_type ='new_direct_business' and o.delete_flag = "N" and o.mirage_flag = "N" and o.new_mirage_flag ="N";
---- 当日直营工单完工量
select count(*) from weixiu_order o where o.order_finish_datetime > '2018-01-01 00:00' and o.order_finish_datetime < '2018-01-01 253.59.59' and o.order_business_type ='new_direct_business' and o.delete_flag = "N" and o.mirage_flag = "N" and o.new_mirage_flag ="N";


--直营工单调度情况
---- 当日转直营工单数据
select count(sh.order_id) from weixiu_order_status_history sh left join weixiu_order o on sh.order_id =o.order_id where o.order_business_type = "new_direct_business" and new_order_status  between 0 and 10 and create_datetime between '2018-02-01 09:30:15' and '2018-02-03 09:30:15' and o.delete_flag = "N" and o.mirage_flag = "N" and o.new_mirage_flag ="N";
---- 当日自动派单工单数据(分配网点的操作者为 系统)
select count(sh.order_id) from weixiu_order o left join weixiu_order_status_history sh on sh.order_id =o.order_id and sh.new_order_status = 20 where o.order_business_type = "new_direct_business" and sh.operator_id = 1 and create_datetime between '2018-02-01 09:30:15' and '2018-02-03 09:30:15' and o.delete_flag = "N" and o.mirage_flag = "N" and o.new_mirage_flag ="N";
---- 当日自动派单工单数据(分配师傅的操作者为 系统)
select count(sh.order_id) from weixiu_order o left join weixiu_order_status_history sh on sh.order_id =o.order_id and sh.new_order_status = 30 where o.order_business_type = "new_direct_business" and sh.operator_id = 1 and create_datetime between '2018-02-01 09:30:15' and '2018-02-03 09:30:15' and o.delete_flag = "N" and o.mirage_flag = "N" and o.new_mirage_flag ="N";
---- 当日接单师傅数
select count(sh.order_id) from weixiu_order o left join weixiu_order_status_history sh on sh.order_id =o.order_id and sh.new_order_status = 31 where o.order_business_type = "new_direct_business" and create_datetime between '2018-02-01 09:30:15' and '2018-02-03 09:30:15' and o.delete_flag = "N" and o.mirage_flag = "N" and o.new_mirage_flag ="N";
---- 等日退单师傅数据
select count(sh.order_id) from weixiu_order o left join weixiu_order_status_history sh on sh.order_id =o.order_id and sh.new_order_status = 21 where o.order_business_type = "new_direct_business" and create_datetime between '2018-02-01 09:30:15' and '2018-02-03 09:30:15' and o.delete_flag = "N" and o.mirage_flag = "N" and o.new_mirage_flag ="N";
---- 当日直营工单完工量
select count(sh.order_id) from weixiu_order o left join weixiu_order_status_history sh on sh.order_id =o.order_id and sh.new_order_status in (80,81) where o.order_business_type = "new_direct_business" and create_datetime between '2018-02-01 09:30:15' and '2018-02-03 09:30:15' and o.delete_flag = "N" and o.mirage_flag = "N" and o.new_mirage_flag ="N";


-- 服务质量
---- 每月安装24H完工率
select *,concat(round(finish / total * 100,2),'','%') from(select count(timestampdiff(SECOND, o.create_datetime , o.order_finish_datetime) < 24 * 60 * 60 or null) finish , count(*) total from weixiu_order o where o.order_service_type = '安装' and o.create_datetime > '2018-01-01 00:00' and o.create_datetime < '2018-01-31 23:59:59' and o.delete_flag = "N" and o.mirage_flag = "N" and o.new_mirage_flag ="N") temp
---- 每月维修24H完工率
select *,concat(round(finish / total * 100,2),'','%') from(select count(timestampdiff(SECOND, o.create_datetime , o.order_finish_datetime) < 24 * 60 * 60 or null) finish , count(*) total from weixiu_order o where o.order_service_type = '维修' and o.create_datetime > '2018-01-01 00:00' and o.create_datetime < '2018-01-31 23:59:59' and o.delete_flag = "N" and o.mirage_flag = "N" and o.new_mirage_flag ="N") temp
---- 日安装完工率
select *,concat(round(finish / total * 100,2),'','%') from(select count(timestampdiff(SECOND, o.create_datetime , o.order_finish_datetime) < 24 * 60 * 60 or null) finish , count(*) total from weixiu_order o where o.order_service_type = '安装' and o.create_datetime > '2018-01-01 00:00' and o.create_datetime < '2018-01-01 23:59:59' and o.delete_flag = "N" and o.mirage_flag = "N" and o.new_mirage_flag ="N") temp
---- 日维修完工率
select *,concat(round(finish / total * 100,2),'','%') from(select count(timestampdiff(SECOND, o.create_datetime , o.order_finish_datetime) < 24 * 60 * 60 or null) finish , count(*) total from weixiu_order o where o.order_service_type = '维修' and o.create_datetime > '2018-01-01 00:00' and o.create_datetime < '2018-01-01 23:59:59' and o.delete_flag = "N" and o.mirage_flag = "N" and o.new_mirage_flag ="N") temp

-- 延误工单情况
select  o.order_id,o.order_number, lb.brand_name,null,o.create_datetime,o.order_finish_datetime,max(ot.create_datetime),ot.order_trace_remark from weixiu_order o left join weixiu_lianbao_brand lb on o.lianbao_brand_id = lb.brand_id left join weixiu_order_trace ot on o.order_id = ot.order_id where timestampdiff(second, o.create_datetime, o.order_finish_datetime) > 5 * 24 * 60 * 60 and o.delete_flag = "N" and o.mirage_flag = "N" and o.new_mirage_flag = "N"  and o.order_status between 0 and 81 group by o.order_idorder by o.order_number desc

---- 当日封单情况



-- sql1 月平均完成工时（从201601 到201802）
select DATE_FORMAT(o.create_datetime, '%Y-%m'),sum( timestampdiff(HOUR, o.create_datetime , o.order_finish_datetime))/count(DATE_FORMAT(o.order_finish_datetime, '%Y-%m') =  DATE_FORMAT(o.create_datetime, '%Y-%m') or null) 
from weixiu_order o where o.create_datetime > '2016-01-01 00:00:00' and o.create_datetime < '2018-02-01 00:00:00' and  o.delete_flag = "N" and (o.order_status = 80 or o.order_status = 81) group by DATE_FORMAT(o.create_datetime, '%Y-%m');

-- sql 月接单师傅总数，这个月平均日均工单量
select DATE_FORMAT(o.create_datetime, '%Y-%m'),count(*)/day(LAST_DAY(DATE_FORMAT(o.create_datetime, '%Y-%m-01'))) from weixiu_order o
left join weixiu_order_status_history osh on o.order_id = osh.order_id and osh.new_order_status = 31
where osh.order_status_history_id is not null and o.create_datetime > '2016-01-01 00:00:00' and o.create_datetime < '2018-02-01 00:00:00' and  o.delete_flag = "N" 
group by DATE_FORMAT(o.create_datetime, '%Y-%m');


-- 接单数超过10的师傅
select t2.time,count(t2.shifu)
from(
    SELECT COUNT(t.order_id) AS con, t.maintain_worker_id AS shifu, DATE_FORMAT(t.create_datetime, '%Y-%m') AS TIME
	FROM weixiu_order t
	WHERE t.delete_flag = 'N' AND t.test_flag = 'N' AND t.from_maintain_site_id IS NULL
	GROUP BY  DATE_FORMAT(t.create_datetime, '%Y-%m'),t.maintain_worker_id)t2
where t2.con > 10
group by t2.time


-- CREATE TABLE num (i int);

-- INSERT INTO num (i) VALUES (0), (1), (2), (3), (4), (5), (6), (7), (8), (9);

-- select adddate('2012-09-01', numlist.id) as 'date' from (SELECT n1.i + n10.i*10 + n100.i*100 AS id FROM num n1 cross join num as n10 cross join num as n100) as numlist where adddate('2012-09-01', numlist.id) <= '2012-09-10';




set @i = -1;  
set @sql = repeat(" select 1 union all",-datediff('2016-01-01','2017-12-31')+1);  
set @sql = left(@sql,length(@sql)-length(" union all"));  
set @sql = concat("select date_add('2021-01-01',interval @i:=@i+1 day) as date from (",@sql,") as tmp");  
prepare stmt from @sql;  
  
execute stmt  




set @i = -1;  
set @sql = repeat(" select 1 union all",TIMESTAMPDIFF(month,'2016-01-01','2017-12-31')+1);  
set @sql = left(@sql,length(@sql)-length(" union all"));  
set @sql = concat("select date_add('2016-01-01',interval @i:=@i+1 month) as date from (",@sql,") as tmp");  
prepare stmt from @sql;  
  
execute stmt  


-- ****************
--
-- left(@sql,length(@sql)) 函数返回字符串s开始的最左边n个字符。
--
-- ****************


select AVG(timestampdiff(HOUR, t.create_datetime , t.order_finish_datetime)) "平均完工时间（小时）" from weixiu_order t where  t.delete_flag = "N" and t.order_status in (80,81) 


-- 网点完工

SELECT
	ms.maintain_site_name '网点名称',
	sp.mobile '网点电话',
	count( t.order_id ) '完成工单量',
	count( t.in_guarantee_period = 'Y' OR NULL ) '保内完成工单量',
	count( t.in_guarantee_period != 'Y' OR NULL ) '保外完成工单量',
	count( t.in_guarantee_period = 'Y' AND timestampdiff( HOUR, t.create_datetime, t.order_finish_datetime ) < 24 OR NULL ) '保内24小时完成工单量',
	count(
	t.in_guarantee_period != 'Y' 
	AND timestampdiff( HOUR, t.create_datetime, t.order_finish_datetime ) > 24 
	AND timestampdiff( HOUR, t.create_datetime, t.order_finish_datetime ) < 48 
OR NULL 
	) '保外24-48小时完成工单量' 
FROM
	weixiu_order t
	LEFT JOIN weixiu_maintain_site ms ON t.maintain_site_id = ms.maintain_site_id
	LEFT JOIN sso_person_v_sso_base sp ON sp.id = ms.maintain_site_id 
WHERE
	t.delete_flag = "N" 
	AND t.mirage_flag = "N" 
	AND t.order_status IN ( 80, 81 ) 
	AND t.order_finish_datetime > "2018-04-02 00:00:000" 
	AND t.order_finish_datetime < "2018-04-09 00:00:000" 
GROUP BY
	ms.maintain_site_id
	
	
	select count(*)from (
select get_area_name(wc.contact_city),count(*) from weixiuhui.weixiu_order  t
left join weixiuhui.weixiu_contact wc on wc.contact_id = t.contact_id
GROUP BY wc.contact_city
order by count(*) desc) temp
	
	