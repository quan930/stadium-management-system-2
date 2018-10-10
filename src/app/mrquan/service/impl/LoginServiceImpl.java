package app.mrquan.service.impl;

import app.mrquan.factory.DAOFactory;
import app.mrquan.pojo.Personnel;
import app.mrquan.service.ILoginService;

public class LoginServiceImpl implements ILoginService {
    @Override
    public Personnel login(String id) {
        return DAOFactory.getIPersonnelDAOInstance().selectPersonnelById(id);
    }
}