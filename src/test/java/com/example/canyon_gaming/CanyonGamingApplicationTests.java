package com.example.canyon_gaming;

import com.example.canyon_gaming.entity.Theme;
import com.example.canyon_gaming.mapper.ThemeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
@RequestMapping("/test")
class CanyonGamingApplicationTests {


    @Test
    void contextLoads() {
        Pattern pUsername = Pattern.compile("^[a-zA-Z0-9_-||\u4e00-\u9fa5]{3,16}$");
        Matcher pu = pUsername.matcher("斤斤计较");
        System.out.println(pu.matches());
    }
}
