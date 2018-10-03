package app.mrquan.factory;

import app.mrquan.dao.IPersonnelDAO;
import app.mrquan.dao.ISiteDAO;
import app.mrquan.dao.impl.PersonnelDAOImpl;
import app.mrquan.dao.impl.SiteDAOImpl;

public class DAOFactory {
    public static ISiteDAO getISiteDAOInstance(){
        return new SiteDAOImpl();
    }
    public static IPersonnelDAO gitIPersonnelDAOInstance(){
        return new PersonnelDAOImpl();
    }
}
