package com.mac.service;

import com.mac.mapper.mac.UserMapper;
import com.mac.mapper.win.CountryMapper;
import com.mac.model.mac.User;
import com.mac.model.win.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * <p>description：</p>
 * <p>copyright： copyright(C)2016-2099</p>
 * <p>Life is short,we need passion</p>
 * <p>Summary： </p>
 * <p>instructions： </p>
 * Date 2020-01-17</p>
 * Author mac
 *
 * @version 1.0
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CountryMapper countryMapper;


    @Transactional
    public int addUser1AndUser2() {
        User user = new User();
        user.setUserName("tom");
        user.setPassword("123456");
        int num1 = userMapper.insert(user);

        Country country = new Country();
        country.setCountryName("china");
        country.setCountryCode("+86");
        int num2 = countryMapper.insert(country);

        return num1 + num2;
    }
}
