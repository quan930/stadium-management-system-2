package app.mrquan.service;

import app.mrquan.pojo.Personnel;

public interface ILoginService {
    /**
     * 根据id 返回用户 pojo
     * @param id 用户id
     * @return 有返回用户对象 没有返回 null
     */
    Personnel login(String id);
}
