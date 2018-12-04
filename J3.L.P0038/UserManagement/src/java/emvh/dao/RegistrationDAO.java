/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.dao;

import emvh.connection.MyConnection;
import emvh.dto.Role;
import emvh.dto.User;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class RegistrationDAO implements Serializable {

    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    public void closConnection() throws SQLException {
        try {
            if (conn != null) {
                conn.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String checkLogin(String username, String password) throws SQLException {
        String role = "failed";
        try {
            String sql = "select r.roleName from tblUsers u, tblRoles r where u.userID = ? and u.password = ? and u.roleID = r.roleID";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if (rs.next()) {
                role = rs.getString("roleName");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closConnection();
        }
        return role;
    }

    public List<User> getAllUser() throws SQLException {
        List<User> list = new ArrayList<>();
        try {
            String sql = "select u.userID, u.firstName, u.lastName, u.email, r.roleName\n"
                    + "from tblUsers u, tblRoles r\n"
                    + "where u.roleID = r.roleID";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                String userID = rs.getString("userID");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String role = rs.getString("roleName");
                list.add(new User(userID, firstName, lastName, email, role));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closConnection();
        }
        return list;
    }

    public List<Role> getAllRole() throws SQLException {
        List<Role> list = new ArrayList();
        try {
            String sql = "select roleID, roleName from tblRoles";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                String roleID = rs.getString("roleID");
                String roleName = rs.getString("roleName");
                list.add(new Role(roleID, roleName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closConnection();
        }
        return list;
    }

    public List<User> searchLikeName(String name) throws SQLException {
        List<User> list = new ArrayList<>();
        try {
            String sql = "select u.userID, u.firstName, u.lastName, u.email, r.roleName\n"
                    + "from tblUsers u, tblRoles r\n"
                    + "where u.roleID = r.roleID and \n"
                    + "(u.firstName like ? or u.lastName like ?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + name + "%");
            preStm.setString(2, "%" + name + "%");
            rs = preStm.executeQuery();
            while (rs.next()) {
                String userID = rs.getString("userID");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String role = rs.getString("roleName");
                list.add(new User(userID, firstName, lastName, email, role));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closConnection();
        }
        return list;
    }
    
    public List<User> searchLikeNameWithRole(String searchName, String searchRole) throws SQLException {
        List<User> list = new ArrayList<>();
        try {
            String sql = "select u.userID, u.firstName, u.lastName, u.email, r.roleName\n"
                    + "from tblUsers u, tblRoles r\n"
                    + "where u.roleID = r.roleID and u.roleID like ? and \n"
                    + "(u.firstName like ? or u.lastName like ?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, searchRole);
            preStm.setString(2, "%" + searchName + "%");
            preStm.setString(3, "%" + searchName + "%");
            rs = preStm.executeQuery();
            while (rs.next()) {
                String userID = rs.getString("userID");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String role = rs.getString("roleName");
                list.add(new User(userID, firstName, lastName, email, role));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closConnection();
        }
        return list;
    }
    
    public Integer countAllUser() throws SQLException {
        int count = -1;
        try {
            String sql = "select count(*) as total from tblUsers";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            if (rs.next()){
                count = rs.getInt("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closConnection();
        }
        return count;
    }
    
    public Map<String, Integer> countRole() throws SQLException {
        Map<String, Integer> map = new HashMap<>();
        try {
            String sql = "select roleID, count(roleID) as total from tblUsers group by roleID";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()){
                map.put(rs.getString("roleID"), rs.getInt("total"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closConnection();
        }
        
        return map;
    }
}
