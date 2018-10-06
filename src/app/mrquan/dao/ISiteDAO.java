package app.mrquan.dao;

import app.mrquan.pojo.Site;

import java.util.List;

public interface ISiteDAO {//查询全部测试
    /**
     * 根据 场地编号 返回 site 对象
     * @param number 要查找对象的id
     * @return 返回site对象,没有返回null
     */
    Site selectSiteByNumber(String number);

    /**
     * 根据场地名字返回 site对象集合
     * @param name 要查找对象的场地名字 name
     * @return 返回site对象集合,没有 size = 0
     */
    List<Site> selectSiteByName(String name);

    /**
     * 根据 场馆名字返回 site对象集合 按照预定量排序
     * @param stadium 要查找对象的场馆名字 stadium
     * @return 返回site对象集合,没有 size = 0
     */
    List<Site> selectSiteByStadium(String stadium);

    /**
     * 根据 场馆名字返回 site对象集合 按照营业额排序
     * @param stadium 要查找对象的场馆名字 stadium
     * @return 返回site对象集合,没有 size = 0
     */
    List<Site> selectSiteByStadiumTurnover(String stadium);

    /**
     * 根据 适合运动类型 和 区域 返回 site对象集合
     * @param type 要查找对象适合的运动类型 motionType
     * @param district 要查找对象的区域 district
     * @return 返回site对象集合,没有 size = 0
     */
    List<Site> selectSiteByTypeAndDistrict(String type,String district);

    /**
     * 根据是否有预定返回 site对象集合
     * @param yOrN 有预定 yOrN = true 没有预定 yOrN = false
     * @return 返回site对象集合,没有 size = 0
     */
    List<Site> selectSiteByReserveYOrN(boolean yOrN);

    /**
     * 根据租金从小到大排序 返回全部site 对象集合
     * @return site 集合,没 size = 0
     */
    List<Site> selectAllSiteByRent();

    /**
     * 根据预定量从大到小排序 返回全部site 对象集合
     * @return site 集合,没 size = 0
     */
    List<Site> selectAllSiteByOrderNumber();

    /**
     * 返回全部site 对象集合
     * @return site 集合,没 size = 0
     */
    List<Site> selectAllSite();

    /**
     * 添加场地
     * @param site 要添加的场地对象
     * @return 成功返回1 否则返回0
     */
    int insert(Site site);
}