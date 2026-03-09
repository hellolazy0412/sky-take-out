package com.sky.service.impl;

import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.PasswordEditDTO;
import com.sky.entity.Employee;
import com.sky.exception.AccountLockedException;
import com.sky.exception.AccountNotFoundException;
import com.sky.exception.PasswordErrorException;
import com.sky.mapper.EmployeeMapper;
import com.sky.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        // 对前端传过来的明文密码1进行md5加密处理
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        if (!password.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
    }

    /**
     * 修改员工密码
     *
     * @param passwordEditDTO 密码修改数据传输对象，包含：
     *                        empId - 员工 ID
     *                        oldPassword - 旧密码
     *                        newPassword - 新密码
     */
    @Override
    public void editPassword(PasswordEditDTO passwordEditDTO) {

        // 如果新旧密码相同，则不允许修改
        if (passwordEditDTO.getNewPassword().equals(passwordEditDTO.getOldPassword())){
            throw new PasswordErrorException(MessageConstant.PASSWORD_EDIT_FAILED);
        }

        // 旧密码比对，如果不一致，则不允许修改
        String realOldPassword = employeeMapper.getPasswordById(passwordEditDTO.getEmpId());
        String oldPassword = DigestUtils.md5DigestAsHex(passwordEditDTO.getOldPassword().getBytes());
        if(!realOldPassword.equals(oldPassword)){
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        // 新密码加密
        String newPassword = DigestUtils.md5DigestAsHex(passwordEditDTO.getNewPassword().getBytes());

        // 修改密码
        employeeMapper.updatePasswordById(passwordEditDTO.getEmpId(),newPassword);
    }

}
