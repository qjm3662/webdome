package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by qjm253 on 2016/12/17 0017.
 */
public class HelloWorld extends HttpServlet {

    //处理客户端的Get请求
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置http响应头的Content-Type字段
        resp.setContentType("text/html");       //因为在输出消息   里面含有html代码，所以要设置该响应头
        //获得用于输出消息的PrintWriter对象
        PrintWriter out = resp.getWriter();
        out.println("<b1>Hello World</b>");         //向客户端输出Hello World
    }
}
