package app.mrquan.selvect;

import app.mrquan.factory.ServiceFactory;
import app.mrquan.util.DataProcessing;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/image")
public class ImageServlet extends javax.servlet.http.HttpServlet {
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("asdadad");
        byte[] bytes = DataProcessing.toPrimitives(ServiceFactory.getIClientServiceInstance().listSiteByRent().get(0).getPicture());
        response.setContentType("image/jpeg");
        OutputStream outs = response.getOutputStream();
        outs.write(bytes);
        outs.flush();
        outs.close();
    }
}
