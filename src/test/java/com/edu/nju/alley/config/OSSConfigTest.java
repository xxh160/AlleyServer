package com.edu.nju.alley.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OSSConfigTest {

    private final OSSConfig.OSSInfo ossInfo;

    @Autowired
    public OSSConfigTest(OSSConfig.OSSInfo ossInfo) {
        this.ossInfo = ossInfo;
    }

    @Test
    public void beanTest() {
        assertEquals("LTAI5t724aEYK7wCPavU6b3A", ossInfo.getAccesskeyId());
    }

}
