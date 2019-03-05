package com.xiaozhu.redpokcet.test;


import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.xiaozhu.repocket.ApplicationStarter.class, properties = "es.set.netty.runtime.available.processors=false")
@AutoConfigureMockMvc
@Slf4j
public class BaseApiTest {
}
