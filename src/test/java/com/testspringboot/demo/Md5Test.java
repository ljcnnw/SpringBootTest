package com.testspringboot.demo;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

public class Md5Test {

    @Test
    public void testMd5() {
        String hashName = "md5";
        String pwd = "123456";


        Object object = new SimpleHash(hashName, pwd, null, 2);
        String str = (String)object;
        System.out.println(str);

    }
}
