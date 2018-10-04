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
     * 添加单一订单 返回预定情况
     * @param order 要增加的order对象
     * @return 成功返回 "success" 。。。。。。。。。。。。。。。
     */
    String add(Order order);

    /**
     * 数据更新 取消订单
     * @param order 需要更新的order 对象
     * @return 成功返回1 失败返回0
     */
    int update(Order order);
}
