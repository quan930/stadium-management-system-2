package app.mrquan.service;

import app.mrquan.pojo.Order;
import app.mrquan.pojo.Personnel;
import app.mrquan.pojo.Site;

import java.util.List;

public interface IAdminService {
    /**
     * 管理员 查询全部订单
     * @param stadium 场馆名称
     * @return 返回订单集合 没有size=0
     */
    List<Order> listOrder(String stadium);

    /**
     * 管理员 查询场地 根据预定量排序
     * @param stadium 场馆名称
     * @return 返回场地集合 没有size=0
     */
    List<Site> listSiteByReserve(String stadium);

    /**
     * 管理员 查询场地 根据营业额排序
     * @param stadium 场馆名称
     * @return 返回场地集合 没有size=0
     */
    List<Site> listSiteByTurnover(String stadium);

    /**
     * 更改顾客信息
     * @param personnel 更改后的顾客对象
     * @return 成功返回1 否则返回 0
     */
    int changePersonnel(Personnel personnel);

    /**
     * 添加场地
     * @param site 要添加的site 对象
     * @return 成功返回1 否则返回0
     */
    int addSite(Site site);
}
