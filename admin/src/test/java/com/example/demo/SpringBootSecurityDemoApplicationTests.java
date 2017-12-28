package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class SpringBootSecurityDemoApplicationTests {

  //	@Test
  public void contextLoads() {
  }

  @Test
  public void generatePassword() {
    String password = "123123";
    System.out.println(new BCryptPasswordEncoder().encode(password));
  }

}
