package servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by qjm253 on 2016/12/20 0020.
 */
@WebServlet(name = "login", urlPatterns = "/login",
            initParams = {
                    @WebInitParam(name = "driver" ,value = "com.mysql.jdbc.Driver"),
                    @WebInitParam(name = "url", value = "jdbc:mysql://localhost:3030/webdb?useUnicode=true&characterEncoding=utf-8&useSSL=false"),
                    @WebInitParam(name = "user", value = "root"),
                    @WebInitParam(name = "pass", value = "123456")
            })
public class LoginServlet extends HttpServlet{

    //响应客户端请求的方法
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String errMsg = "";
        //Servlet本身并不输出响应到客户端，因此必须将请求转发
        RequestDispatcher rd;
        ServletConfig config = getServletConfig();
        //获取请求参数
        String username = req.getParameter("username");
        String password= req.getParameter("password");

        try {
            //Servlet本身，并不执行任何的业务逻辑处理，它调用JavaBean处理用户请求
            MyDAO myDAO = new MyDAO(config.getInitParameter("driver"), config.getInitParameter("url"), config.getInitParameter("user"),
                    config.getInitParameter("pass"));
            //查询结果集
            ResultSet rs = myDAO.query("SELECT password FROM tbl_user "
                    + "WHERE username = ?", username);
            if(rs.next()){      //查询到结果则遍历
                //用户名和密码匹配
                if(rs.getString("password").equals(password)){
                    //获取session对象
                    HttpSession session = req.getSession(true);
                    //设置session属性，跟踪用户会话状态
                    session.setAttribute("name", username);
                    //获取转发对象
                    rd = req.getRequestDispatcher("/index.jsp");
                    //转发请求
                    rd.forward(req, resp);
                }else{
                    errMsg += "用户名和密码不匹配";
                }
            }else{
                //用户名不存在时
                errMsg += "您的用户名不存在";
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        //如果出错转发到重新登录
        if(errMsg != null && !errMsg.equals("")){
            rd = req.getRequestDispatcher("/login.jsp");
            req.setAttribute("err", errMsg);
            rd.forward(req, resp);
        }
    }
}
