package app.mrquan.factory;

import app.mrquan.service.IAdminService;
import app.mrquan.service.IClientService;
import app.mrquan.service.ILoginService;
import app.mrquan.service.impl.AdminServiceImpl;
import app.mrquan.service.impl.ClientServiceImpl;
import app.mrquan.service.impl.LoginServiceImpl;

public class ServiceFactory {
    public static IClientService getIClientServiceInstance(){
        return new ClientServiceImpl();
    }

    public static ILoginService getILoginServiceInstance(){
        return new LoginServiceImpl();
    }

    public static IAdminService getIAdminService(){
        return new AdminServiceImpl();
    }
}
