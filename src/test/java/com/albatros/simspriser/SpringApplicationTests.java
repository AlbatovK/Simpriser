package com.albatros.simspriser;

import com.albatros.simspriser.domain.Quiz;
import com.albatros.simspriser.rest.dto.QuizDto;
import com.albatros.simspriser.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimpleSpringApplication.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class SpringApplicationTests {

	@Autowired
	private QuizService service;

	@Test
	public void getAll_enabled() throws Exception {
		List<Quiz> last = service.getQuizzes();
		assert last != null;

		for (Quiz q : last) {
			service.deleteQuiz(q);
		}

		int test_id_1 = 2;
		String test_name_1 = "TEST_NAME_1";
		Quiz q_1 = Quiz.builder().id(test_id_1).name(test_name_1).questions(null).topics(null).build();

		int test_id_2 = 3;
		String test_name_2 = "TEST_NAME_2";
		Quiz q_2 = Quiz.builder().id(test_id_2).name(test_name_2).questions(null).topics(null).build();

		int test_id_3 = 4;
		String test_name_3 = "TEST_NAME_3";
		Quiz q_3 = Quiz.builder().id(test_id_3).name(test_name_3).questions(null).topics(null).build();

		List<Quiz> added = new ArrayList<>();
		added.add(q_1);
		added.add(q_2);
		added.add(q_3);

		for (Quiz q : added) {
			service.saveQuiz(q);
		}

		List<Quiz> nw = service.getQuizzes();
		assertThat(nw.size()).isEqualTo(added.size());
		assertThat(added).containsExactlyInAnyOrderElementsOf(nw);

		for (Quiz q : added) {
			service.deleteQuiz(q);
		}

		int mustEmpty = service.getQuizzes().size();
		assertThat(mustEmpty).isEqualTo(0);

		for (Quiz q : last) {
			service.saveQuiz(q);
		}
	}

	@Test
	public void saving_Enabled() throws Exception {
		int test_id = 1;
		String test_name = "TEST_NAME";
		Quiz q = Quiz.builder().id(test_id).name(test_name).questions(null).topics(null).build();

		int old = service.getQuizzes().size();
		service.saveQuiz(q);
		int nw = service.getQuizzes().size();
		assertThat(old).isEqualTo(nw - 1);

		boolean containsAdded = service.getQuizzes().contains(q);
		assertThat(containsAdded).isTrue();

		service.deleteQuiz(q);

		boolean containsDeleted = service.getQuizzes().contains(q);
		assertThat(containsDeleted).isFalse();
	}

	@Test
	public void deleting_Enabled() throws Exception {
		List<Quiz> got = service.getQuizzes();
		assert got != null;

		int size_first = got.size();

		for (Quiz q : got) {
			service.deleteQuiz(q);
		}

		int size_second = service.getQuizzes().size();
		assertThat(size_second).isEqualTo(0);

		for (Quiz q : got) {
			service.saveQuiz(q);
		}

		int size_third = service.getQuizzes().size();
		assertThat(size_third).isEqualTo(size_first);
	}

	@Test
	public void database_Enabled() throws Exception {
		List<Quiz> res = service.getQuizzes();
		assert res != null;
	}

	@Test
	public void dto_Test() {
		int test_id = 1;
		String test_name = "TEST_NAME";
		Quiz q = Quiz.builder()
				.id(test_id)
				.name(test_name)
				.questions(Collections.emptyList())
				.topics(Collections.emptyList())
				.build();
		QuizDto dto = QuizDto.toDto(q);
		assertThat(QuizDto.toDomainObject(dto)).isEqualTo(q);
	}
}
