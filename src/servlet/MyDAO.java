package servlet;

import java.sql.*;

/**
 * Created by qjm253 on 2016/12/20 0020.
 */
public class MyDAO {
    private Connection conn;
    private String driver;
    private String url;
    private String username;
    private String password;

    public MyDAO(){

    }

    public MyDAO(String driver, String url, String username, String password) {
        System.out.println(driver);
        System.out.println(url);
        System.out.println(username);
        System.out.println(password);
        this.driver = driver;
        this.username = username;
        this.password = password;
        this.url = url;
    }

    /**
     * 获取数据库连接
     * @return  返回JDBC的一个Connection对象
     */
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        if(conn == null){
            //
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        }
        return conn;
    }

    /**
     * 插入记录
     * @param sql       插入要执行的sql（含占位符）
     * @param args      用于sql语句中替代占位符
     * @return  返回是否插入成功
     */
    public boolean insert(String sql, Object... args) throws SQLException, ClassNotFoundException {
        PreparedStatement pst = getConnection().prepareStatement(sql);
        for(int i = 0; i < args.length; i++){
            pst.setObject(i + 1, args[i]);      //PreparedStatement是从下标为1开始的
        }
        return pst.executeUpdate() == 1;
    }

    /**
     * 执行查询操作
     * @param sql
     * @param args
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ResultSet query(String sql, Object... args) throws SQLException, ClassNotFoundException {
        PreparedStatement pst = getConnection().prepareStatement(sql);
        for(int i = 0; i < args.length; i++){
            pst.setObject(i + 1, args[i]);
        }
        return pst.executeQuery();
    }

    /**
     * 执行修改操作
     * @param sql
     * @param args
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void modify(String sql, Object... args) throws SQLException, ClassNotFoundException {
        PreparedStatement pst = getConnection().prepareStatement(sql);
        for(int i = 0; i < args.length; i++){
            pst.setObject(i + 1, args[i]);
        }
        pst.executeUpdate();
        pst.close();        //手动立即关闭，保证数据修改的及时性
    }


    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
