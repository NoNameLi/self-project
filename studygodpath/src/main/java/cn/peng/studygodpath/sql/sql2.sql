--sql1：年度工单量 151617 18 18预计（18预计用250w）
select DATE_FORMAT(o.create_datetime, '%Y'),count(*) from weixiu_order o where o.create_datetime < '2018-01-01' GROUP BY DATE_FORMAT(o.create_datetime, '%Y') union select '2018','2500000'


--sql2：工单类型分类
select a.order_service_type, CONCAT(ROUND(a.num / b.totalNum * 100, 2),'','%')  
from (select *, count(*) num  FROM weixiu_order o GROUP BY o.order_service_type) a , (SELECT COUNT(*) totalNum FROM weixiu_order) b

--top10品牌，工单量
SELECT lb.brand_name, count(*) num from weixiu_order o
LEFT JOIN  weixiu_lianbao_brand lb on o.lianbao_brand_id = lb.brand_id
group by lb.brand_id
order by num desc limit 0,10



--服务能力 省份
SELECT wms.maintain_site_id ,pca.name '省份', COUNT(wms.person_maintain_site = 'N' OR NULL) '企业网点',COUNT(wms.person_maintain_site = 'Y' OR NULL)'个人网点'
FROM weixiu_maintain_site wms
LEFT JOIN base_prov_city_area pca ON wms.maintain_site_province = pca.id
WHERE wms.delete_flag = "N" AND wms.maintain_site_cooperation = "Y" and wms.test_flag = "N" 
GROUP BY pca.id;


--品类统计 服务能力
SELECT wmc.category_name '品类', COUNT(wms.person_maintain_site = 'N' OR NULL) '企业网点', COUNT(wms.person_maintain_site = 'Y' OR NULL)'个人网点'
FROM weixiu_maintain_site_coverage wmsc
LEFT JOIN weixiu_maintain_site wms ON wmsc.maintain_site_id = wms.maintain_site_id
LEFT JOIN weixiu_master_category wmc ON wmc.category_id = wmsc.maintain_category_id
LEFT JOIN base_prov_city_area pca ON wms.maintain_site_province = pca.id
WHERE wms.delete_flag = "N" AND wms.maintain_site_cooperation = "Y" AND wms.test_flag = "N" and pca.name like "%%" and wms.maintain_site_service_type like "%%"
GROUP BY pca.id;

--按服务类型统计
SELECT '维修' AS '服务类型', COUNT(temp.person_maintain_site = 'N'  OR NULL) '企业网点', COUNT(temp.person_maintain_site = 'Y' OR NULL)'个人网点'
FROM(
SELECT wms.maintain_site_id, wms.person_maintain_site,wms.maintain_site_service_type
FROM weixiu_maintain_site wms
LEFT JOIN base_prov_city_area pca ON wms.maintain_site_city = pca.id
LEFT JOIN weixiu_maintain_site_coverage wmsc ON wms.maintain_site_id = wmsc.maintain_site_id
LEFT JOIN weixiu_master_category wmc ON wmc.category_id = wmsc.maintain_category_id
WHERE wms.maintain_site_service_type LIKE '%维修%' AND wms.delete_flag = 'N' AND wms.test_flag = "N" AND wms.maintain_site_cooperation = "Y" and pca.name like "%%" and wms.maintain_site_service_type like "%%"
GROUP BY wms.maintain_site_id) temp;



--sql1：工单城市分布
SELECT pca.name '城市', get_area_name(wc.contact_province)'省份', COUNT(*) '下单工单量', COUNT(wo.order_status IN (80,81) OR NULL)'完工工单量',null '工单流水'
FROM weixiu_order wo
LEFT JOIN weixiu_contact wc ON wo.contact_id = wc.contact_id
LEFT JOIN base_prov_city_area pca ON wc.contact_city = pca.id
WHERE wo.delete_flag = "N" and wo.test_flag = "N"
GROUP BY wc.contact_city;

--工单省份分布
SELECT get_area_name(wc.contact_province)'省份', COUNT(*) '下单工单量', COUNT(wo.order_status IN (80,81) OR NULL)'完工工单量',null '工单流水'
FROM weixiu_order wo
LEFT JOIN weixiu_contact wc ON wo.contact_id = wc.contact_id
LEFT JOIN base_prov_city_area pca ON wc.contact_province = pca.id
WHERE wo.delete_flag = "N" and wo.test_flag = "N"
GROUP BY wc.contact_province;

--服务能力
SELECT wms.maintain_site_id ,pca.name '城市', COUNT(wms.person_maintain_site = 'N' OR NULL) '企业网点',COUNT(wms.person_maintain_site = 'Y' OR NULL)'个人网点'
FROM weixiu_maintain_site wms
LEFT JOIN base_prov_city_area pca ON wms.maintain_site_city = pca.id
WHERE wms.delete_flag = "N" AND wms.maintain_site_cooperation = "Y" and wms.test_flag = "N" 
GROUP BY pca.id;


--品类统计 服务能力
SELECT wmc.category_name '品类', COUNT(wms.person_maintain_site = 'N' OR NULL) '企业网点', COUNT(wms.person_maintain_site = 'Y' OR NULL)'个人网点'
FROM weixiu_maintain_site_coverage wmsc
LEFT JOIN weixiu_maintain_site wms ON wmsc.maintain_site_id = wms.maintain_site_id
LEFT JOIN weixiu_master_category wmc ON wmc.category_id = wmsc.maintain_category_id
LEFT JOIN base_prov_city_area pca ON wms.maintain_site_city = pca.id
WHERE wms.delete_flag = "N" AND wms.maintain_site_cooperation = "Y" AND wms.test_flag = "N" and pca.name like "%%" and wms.maintain_site_service_type like "%%"
GROUP BY pca.id;

--按服务类型统计

SELECT '维修' AS '服务类型', COUNT(temp.person_maintain_site = 'N'  OR NULL) '企业网点', COUNT(temp.person_maintain_site = 'Y' OR NULL)'个人网点'
FROM(
SELECT wms.maintain_site_id, wms.person_maintain_site,wms.maintain_site_service_type
FROM weixiu_maintain_site wms
LEFT JOIN base_prov_city_area pca ON wms.maintain_site_city = pca.id
LEFT JOIN weixiu_maintain_site_coverage wmsc ON wms.maintain_site_id = wmsc.maintain_site_id
LEFT JOIN weixiu_master_category wmc ON wmc.category_id = wmsc.maintain_category_id
WHERE wms.maintain_site_service_type LIKE '%维修%' AND wms.delete_flag = 'N' AND wms.test_flag = "N" AND wms.maintain_site_cooperation = "Y"
GROUP BY wms.maintain_site_id) temp;