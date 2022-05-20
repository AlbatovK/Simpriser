package com.albatros.simspriser;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimpleSpringApplication.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class SpringApplicationTests {


}
