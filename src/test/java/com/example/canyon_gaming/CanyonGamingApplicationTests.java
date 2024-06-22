package com.example.canyon_gaming;


import com.example.canyon_gaming.controller.UserController;
import com.example.canyon_gaming.entity.User;
import com.example.canyon_gaming.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class) //运行器
public class CanyonGamingApplicationTests {

    @Resource
    private UserController userController;
    @Resource
    private UserServiceImpl userService;

    @Test
    public void loginTest1(){
        Map<String, Object> map = new HashMap<>();
        map.put("username","zkl");
        map.put("password","1234");
        userController.login(map);
    }

    @Test
    public void loginTest2(){
        Map<String, Object> map = new HashMap<>();
        map.put("username","");
        map.put("password","");
        userController.login(map);
    }

    @Test
    public void loginTest3(){
        Map<String, Object> map = new HashMap<>();
        map.put("username","zkl");
        map.put("password","");
        userController.login(map);
    }

    @Test
    public void registerTest1(){
        User user = new User();
        user.setUsername("zkl");
        user.setPassword("1234");
        user.setPhone("17820487661");
        userService.register(user);
    }

    @Test
    public void registerTest2(){
        User user = new User();
        user.setUsername("zengkangle");
        user.setPassword("1234");
        user.setPhone("17820487661");
        userService.register(user);
    }

    @Test
    public void registerTest3(){
        User user = new User();
        user.setUsername("");
        user.setPassword("1234");
        user.setPhone("17820487661");
        userService.register(user);
    }

    @Test
    public void registerTest4(){
        User user = new User();
        user.setUsername("zengkang");
        user.setPassword("");
        user.setPhone("17820487661");
        userService.register(user);
    }

    @Test
    public void registerTest5(){
        User user = new User();
        user.setUsername("zengkang");
        user.setPassword("1234");
        user.setPhone("110");
        userService.register(user);
    }
}
