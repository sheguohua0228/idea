package com.leyes.app.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.leyes.app.dto.statistics.ClothesOrderDto;
import com.leyes.app.dto.statistics.DailyAppActiveDto;
import com.leyes.app.dto.statistics.DailyRegisterDto;
import com.leyes.app.dto.statistics.NewUserOrderDto;
import com.leyes.app.dto.statistics.PhotoOrderDto;
import com.leyes.app.dto.statistics.PropertyInfoDto;
import com.leyes.app.dto.statistics.RechargeDto;
import com.leyes.app.dto.statistics.ShopOrderDto;
import com.leyes.app.dto.statistics.TransformationRateDto;
import com.leyes.app.message.StatisticsOrderMessage;

public interface StatisticsService {
	/**
	 * 
	* @Title: queryOrderStatistics 
	* @Description:查询关于订单的统计数据
	* @return
	* @return OrderStatisticsDto    
	* @throws
	 */
     public ClothesOrderDto queryClothesOrderStatistics(Date startTime,Date endTime);
     
     /**
      * 相片订单相关
     * @Title: queryPhotoOrderStatistics 
     * @Description: TODO
     * @param startTime
     * @param endTime
     * @return
     * @return OrderDto    
     * @throws
      */
     
     public PhotoOrderDto queryPhotoOrderStatistics(Date startTime,Date endTime);
     
     /**
      * 
     * @Title: queryShopOrderStatistics 
     * @Description: 值得买订单相关
     * @param startTime
     * @param endTime
     * @return
     * @return ShopOrderDto    
     * @throws
      */
     public ShopOrderDto queryShopOrderStatistics(Date startTime,Date endTime);
     
     /**
      * 
     * @Title: queryRegisterStatistics 
     * @Description: 注册量
     * @return
     * @return List<RegisterStatisticsDto>    
     * @throws
      */
     public DailyRegisterDto queryRegisterStatistics(Date startTime,Date endTime);
     /**
      * 
     * @Title: queryNewOrderStatistics 
     * @Description: 新下单用户量
     * @return
     * @return NewUserOrderDto    
     * @throws
      */
     public NewUserOrderDto queryNewOrderUseNumber(Date startTime,Date endTime,Integer type);
     /**
      * 
     * @Title: queryAppActiveNumber 
     * @Description: App使用量
     * @return
     * @return DailyAppActiveDto    
     * @throws
      */
     public DailyAppActiveDto queryAppActiveNumber(Date startTime,Date endTime);
     /**
      * select adddate('2012-09-01', numlist.id) as `date` from 
      * (SELECT n1.i + n10.i*10 + n100.i*100 AS id FROM num n1 cross join num as n10 cross join num as n100) 
      * as numlist where adddate('2012-09-01', numlist.id) <= '2012-09-10';</span>  
     * @Title: queryTransformationRate 
     * @Description: 转化率
     * @return
     * @return List<MemberTransformationRateDto>    
     * @throws
      */
     public TransformationRateDto queryTransformationRate(Date startTime,Date endTime);
     /**
      * 
     * @Title: queryRechargeMember 
     * @Description: 充值查询
     * @return
     * @return List<DailyRechargeDto>    
     * @throws
      */
     public RechargeDto queryRechargeInfo(Date startTime,Date endTime);
     /**
      * 
     * @Title: queryTripartite 
     * @Description: 物业统计
     * @return
     * @return List<DailyTripartiteDto>    
     * @throws
      */
     public  PropertyInfoDto queryPropertyInfo(Date startTime,Date endTime);
     
     /**
      * 
     * @Title: queryAdmin 
     * @Description: 管理员登录
     * @param username
     * @return
     * @return String    
     * @throws
      */
     public String queryAdmin(String username);
     
     /**
      * 
     * @Title: queryClothesOrderTable 
     * @Description: 
     * @param startTime
     * @param endTime
     * @return
     * @return List<Map<String,String>>    
     * @throws
      */
     public List<Map<String,Object>> queryClothesOrderTable(Date startTime,Date endTime);
     /**
      * 
     * @Title: queryPhotoOrderTable 
     * @Description: 
     * @param startTime
     * @param endTime
     * @return
     * @return List<Map<String,Object>>    
     * @throws
      */
     public List<Map<String,Object>> queryPhotoOrderTable(Date startTime,Date endTime);
     /**
      * 
     * @Title: queryShopOrderTable 
     * @Description: 
     * @param startTime
     * @param endTime
     * @return
     * @return List<Map<String,Object>>    
     * @throws
      */
     public List<Map<String,Object>> queryShopOrderTable(Date startTime,Date endTime);
     /**
      * 
     * @Title: queryOtherTable 
     * @Description: 
     * @param startTime
     * @param endTime
     * @return
     * @return List<Map<String,Object>>    
     * @throws
      */
     public List<Map<String,Object>> queryOtherTable(Date startTime,Date endTime);
     /**
      * 
     * @Title: saveOrder 
     * @Description: 保存订单（打印、洗衣、值得买）统计数据
     * @param statisticsOrderMessage
     * @return
     * @return boolean    
     * @throws
      */
     public boolean saveOrder(StatisticsOrderMessage statisticsOrderMessage)throws Exception;
     
}
