--Personnel对象 ID,密码,名字,性别,年龄,电话,邮箱,是否管理员,顾客余额,顾客区域,顾客爽约,管理员场馆,
select  personnel.id,password,name,sex,age,telephone,email,balance,district,num as abrogate,administrator,stadium from personnel left join
	(select count(*) as num,id from orders where cancel=true group by id) as a
	on personnel.id = a.id
--	where personnel.id='a00002'


--Site对象 场地编号,场地名称,场地所属区域,所属场馆,运动类型,运动简介,年龄上限,年龄下限,租金,订单数量,营业额
select b.number,name,district,stadium,motionType,motionProfile,ageUp,ageLow,rent,orderNum,orders.times*b.rent as turnover from
	(select  site.number,name,district,stadium,motionType,motionProfile,ageUp,ageLow,rent,orderNum from site left join
		(select count(*) as orderNum,siteNumber as number from orders
			where startTime > now() and (cancel = false or cancel is null)
			group by siteNumber) as orderNum
		on site.number = orderNum.number) as b
	left join
	(select sum(a.times) as times,siteNumber as number from
		(select (endTime-startTime)/10000 as times,siteNumber from orders
			where endTime < now() and (cancel = false or cancel is null)) as a
		group by siteNumber) as orders
	on orders.number = b.number
--	where b.number = 'aaa0001'


-- 年龄 余额 违约次数
select personnel.id,personnel.age,personnel.balance,ab.abrogate from personnel left join
	(select count(*) as abrogate,id from orders where cancel=true
	group by id) as ab
	on personnel.id = ab.id
-- 	where id = 'a00002'


-- 年龄 余额 违约次数 当前订单
select ap.age,ap.balance,ap.abrogate,num.orderNum from (select personnel.id,personnel.age,personnel.balance,ab.abrogate from personnel left join
		(select count(*) as abrogate,id from orders where cancel=true
		group by id) as ab
		on personnel.id = ab.id) as ap
			left join (select count(*) as orderNum,id from orders
			where startTime > now() and cancel is not true
			group by id) as num
		on ap.id = num.id
-- 		where ap.id = 'a00004'