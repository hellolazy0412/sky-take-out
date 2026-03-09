package com.sky.mapper;

import com.sky.entity.Employee;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    @Update("update employee set password = #{newPassword} where id = #{id}")
    void updatePasswordById(Long id, String newPassword);

    @Select("select password from employee where id = #{id}")
    String getPasswordById(Long id);

    @Update("update employee set status = #{status} where id = #{id}")
    void setStatusById(long id, Integer status);

    /**
     * 插入员工数据
     * @param employee
     */
    @Insert("insert into employee (username, name, password, phone, sex, id_number, create_time, update_time, create_user, update_user, status) " +
            "values (#{username}, #{name}, #{password}, #{phone}, #{sex}, #{idNumber}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser}, #{status})")
    void insert(Employee employee);
}
