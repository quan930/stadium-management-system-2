package app.mrquan.dao;

import app.mrquan.pojo.Order;

import java.util.List;

public interface IOrderDAO {
    /**
     * 查询指定用户id的待使用订单
     * @param id 用户id
     * @return 返回order 集合 没有size为0
     */
    List<Order> selectOrderByUser(String id);

    /**
     * 查询指定用户id的全部订单
     * @param id 用户id
     * @return 返回order 集合 没有size为0
     */
    List<Order> selectOrderByUserAll(String id);

    /**
     * 查询场馆 全部订单
     * @param stadium 场馆名称
     * @return 返回order 集合 没有size为0
     */
    List<Order> selectOrderByStadiumAll(String stadium);

    /**
     * 添加多个订单 返回预定情况
     * @param orders 要增加的 订单集合
     * @return  成功size=0 失败返回失败的订单 编号及失败原因
     */
    String add(List<Order> orders);

    /**
     * 数据更新 取消订单
     * @param number 需要更新的order 编号
     * @return 成功返回1 失败返回0
     */
    int update(String number);
}