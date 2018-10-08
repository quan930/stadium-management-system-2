package app.mrquan.selvect;

import app.mrquan.factory.ServiceFactory;
import app.mrquan.util.DataProcessing;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/image")
public class ImageServlet extends javax.servlet.http.HttpServlet {
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String id = request.getParameter("id");
        Byte[] bytes =(Byte[])request.getSession().getAttribute(id);
        byte[] bytes1= DataProcessing.toPrimitives(bytes);
        response.setContentType("image/jpeg");
        OutputStream outs = response.getOutputStream();
        outs.write(bytes1);
        outs.flush();
        outs.close();
    }
}
