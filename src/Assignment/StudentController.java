/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ishani anushka
 */
public class StudentController {
   public static boolean addStudent(Student student) throws ClassNotFoundException, SQLException{
        Connection connection = DBConnection.getInstance().getConnection();        
        PreparedStatement stm = connection.prepareStatement("insert into student values(?,?,?,?)");
        stm.setObject(1, student.getId());
        stm.setObject(2, student.getName());
        stm.setObject(3, student.getAddress());
        stm.setObject(4, student.getContact());
        int res = stm.executeUpdate();
        return res>0;
    }
    public static Student searchStudent(int id) throws ClassNotFoundException, SQLException{
        ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("Select * From Student where id='"+id+"'");
        return rst.next()? new Student(id, rst.getString("name"),rst.getString("address"),rst.getString("contact")):null;
    }
    public static boolean deleteStudent(String id) throws ClassNotFoundException, SQLException{
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("delete From Student where id='"+id+"'")>0;
        /*DBConnection instance = DBConnection.getInstance();
        Connection connection = instance.getConnection();
        Statement stm = connection.createStatement();
        String SQL="Delete From Customer where id='"+id+"'";
        int res = stm.executeUpdate(SQL);
        return res>0;*/
    }
    public static boolean updateStudent(Student student) throws ClassNotFoundException, SQLException{
        Connection connection = DBConnection.getInstance().getConnection();        
        PreparedStatement stm = connection.prepareStatement("Update Student set name=?, address=?, contact=? where id=?");
        stm.setObject(1, student.getName());
        stm.setObject(2, student.getAddress());
        stm.setObject(3, student.getContact());
        stm.setObject(4, student.getId());
        return  stm.executeUpdate()>0;
    }
    public static ArrayList<Student> getAllStudent() throws ClassNotFoundException, SQLException{
        ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("Select * From Student");
        ArrayList <Student>studentList=new ArrayList<>();
        while(rst.next()){
            studentList.add(new Student(rst.getInt("id"), rst.getString("name"),rst.getString("address"),rst.getString("contact")));
        }
        return studentList;
    }

    
 
}
