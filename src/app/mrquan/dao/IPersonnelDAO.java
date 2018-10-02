package app.mrquan.dao;

import app.mrquan.pojo.Personnel;

import java.sql.SQLException;

public interface IPersonnelDAO {
    /**
     * 根据id返回pojo 对象
     * @param id 需要查询的id
     * @return 存在返回personnel对象 否则返回null
     */
    Personnel selectPersonnelById(String id);

    /**
     * 数据增加
     * @param pojo 要增加的Personnel
     * @return 成功返回1失败返回0
     */
//    int insert(Personnel pojo);

    /**
     * 数据更新
     * @param pojo 需要更新的Personnel
     * @return 成功返回1 失败返回0
     */
    int update (Personnel pojo);
}
