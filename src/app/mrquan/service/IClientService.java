package app.mrquan.service;

import app.mrquan.pojo.Order;
import app.mrquan.pojo.Personnel;
import app.mrquan.pojo.Site;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IClientService {
    /**
     * 顾客根据场地名字 查询场地
     * @param name 场地名字
     * @return 返回Site 对象集合 没有size=0
     */
    List<Site> findSiteByName(String name);

    /**
     * 顾客根据场馆 查询场地
     * @param stadium 场馆名字
     * @return 返回Site 对象集合 没有size=0
     */
    List<Site> findSiteByStadium(String stadium);

    /**
     * 顾客根据运动类别和区域 查询场地
     * @param motionType 运动类别
     * @param district  区域名字
     * @return 返回Site 对象集合 没有size=0
     */
    List<Site> findSiteByTypeAndDistrict(String motionType,String district);

    /**
     * 顾客根据是否预定 查询场地
     * @param yOrN 为true查询有预定的场地，反之则查询没有预定的场地
     * @return 返回Site 对象集合 没有size=0
     */
    List<Site> findSiteByReserve(boolean yOrN);

    /**
     * 顾客根据租金 排序所有场地
     * @return 返回Site 对象集合 没有size=0
     */
    List<Site> listSiteByRent();

    /**
     * 顾客根据预定量 排序所有场地
     * @return 返回Site 对象集合 没有size=0
     */
    List<Site> listSiteByReserve();

    /**
     * 根据用户 查询待使用订单
     * @param id 用户id
     * @return 返回Order对象集合，没有size为0
     */
    List<Order> findOrdersToBeUsed(String id);

    /**
     * 根据用户 查询全部订单
     * @param id 用户id
     * @return 返回Order对象集合，没有size为0
     */
    List<Order> listOrders(String id);

    /**
     * 用于前端初始化选择列表
     * @return map
     */
    Map<String,Set<String>> listSportsInit();

    /**
     * 更改顾客信息
     * @param personnel 更改后的顾客对象
     * @return 成功返回1 否则返回 0
     */
    int changePersonnel(Personnel personnel);

    /**
     * 取消订单
     * @param number 需要取消的订单编号
     * @return 成功返回true 否则返回false
     */
    int cancelReserve(String number);

    /**
     * 顾客预定
     * @param orders 需要预定的订单集合
     * @return  返回预定情况
     */
    String reserve(List<Order> orders);
}