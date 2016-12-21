package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
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
 * Created by qjm3662 on 2016/12/20.
 */
@WebServlet(name = "register", urlPatterns = "/register",
        initParams = {
        @WebInitParam(name = "driver" ,value = "com.mysql.jdbc.Driver"),
        @WebInitParam(name = "url", value = "jdbc:mysql://localhost:3030/webdb?useUnicode=true&characterEncoding=utf-8&useSSL=false"),
        @WebInitParam(name = "user", value = "root"),
        @WebInitParam(name = "pass", value = "123456")
})
public class RegisterServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String errMsg = "";
        RequestDispatcher rd;
        ServletConfig config = getServletConfig();
        //获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            //查询结果集
            MyDAO myDAO = new MyDAO(config.getInitParameter("driver"), config.getInitParameter("url"), config.getInitParameter("user"),
                    config.getInitParameter("pass"));
            ResultSet rs = myDAO.query("SELECT password FROM tbl_user " +
                "WHERE username = ?", username);
            if(rs.next()){
                //Y用户已存在
                errMsg += "用户已存在";
            }else{
                //获取session对象
                HttpSession session = req.getSession(true);
                //设置session属性，跟踪用户会话状态
                session.setAttribute("name", username);
                //用户不存在则允许注册
                if(myDAO.insert("INSERT INTO tbl_user(username, password) VALUE(?, ?)", username, password)){
                    rd = req.getRequestDispatcher("index.jsp");
                    rd.forward(req, resp);
                }else{      //注册失败
                    errMsg += "注册失败，请重新注册";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //如果出错转发到重新登录
        if(errMsg != null && !errMsg.equals("")){
            rd = req.getRequestDispatcher("/register.jsp");
            req.setAttribute("err", errMsg);
            rd.forward(req, resp);
        }
    }
}
