package com.albatros.simspriser;

import com.albatros.simspriser.domain.Quiz;
import com.albatros.simspriser.service.QuizService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimpleSpringApplication.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class SpringApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private QuizService service;

	@Test
	public void database_Enabled() throws Exception {
		List<Quiz> res = service.getQuizzes();
		assert res.size() > 0;
	}
}
