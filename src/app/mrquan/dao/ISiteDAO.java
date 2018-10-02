package app.mrquan.dao;

import app.mrquan.pojo.Site;

import java.util.List;

public interface ISiteDAO {
    /**
     * 根据id返回 pojo 对象
     * @param number 要查找对象的id
     * @return 返回pojo对象,没有返回null
     */
    Site selectSiteByNumber(String number);


}
