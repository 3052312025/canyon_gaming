package com.example.canyon_gaming;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class CanyonGamingApplicationTests {

    @Test
    void contextLoads() {
        Pattern pUsername = Pattern.compile("^[^\\u4e00-\\u9fa5]{3,16}$");
        Matcher pu = pUsername.matcher("2234ds");
        System.out.println(pu.matches());
    }


}
