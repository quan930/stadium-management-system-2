package app.mrquan.factory;

import app.mrquan.dao.*;
import app.mrquan.dao.impl.*;

public class DAOFactory {//656
    public static ISiteDAO getISiteDAOInstance(){
        return new SiteDAOImpl();
    }

    public static IPersonnelDAO gitIPersonnelDAOInstance(){
        return new PersonnelDAOImpl();
    }

    public static IOrderDAO gitIOrderDAOInstance(){
        return new OrderDAOImpl();
    }
}
