package com.raimond.startingoverfinalproject.repository1;

import com.raimond.startingoverfinalproject.entity.Button;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ButtonRepositoryTest {
	@Autowired
	private ButtonRepository buttonRepository;
	@Autowired
	private EntityManager entityManager;
	private Button button;

	@BeforeEach
	void setUp() {
		buttonRepository.deleteAll();
		entityManager.createNativeQuery(
				"ALTER TABLE buttons ALTER COLUMN id RESTART WITH 1"
		).executeUpdate();

		button = new Button();
		button.setName("Button0");
	}

	@Test
	@DisplayName("Test for button saving method")
	void givenButton_whenSaveButton_thenReturnSavedButton() {
		// given
		// when
		Button savedButton = buttonRepository.save(button);
		// then
		assertThat(savedButton).isNotNull();
		assertThat(savedButton.getName()).isEqualTo("Button0");
		assertThat(savedButton.getId()).isNotNull();
		assertThat(savedButton.getId()).isEqualTo(1L);
	}

	@Test
	@DisplayName("Returning non empty list of buttons")
	void givenTwoButtons_whenFindAllButtons_thenListOfButtonsIsNotEmpty() {
		// given
		Button button1 = new Button("Button1", (short) 1);
		buttonRepository.save(button1);
		buttonRepository.save(button);
		// when
		List<Button> buttons = buttonRepository.findAll();
		// then
		assertThat(buttons).isNotNull();
	}

	@Test
	@DisplayName("Returning buttons")
	void givenTwoButtons_whenFindAllButtons_thenListOfButtons() {
		// given
		Button button1 = new Button("Button1", (short) 1);
		buttonRepository.save(button1);
		buttonRepository.save(button);
		// when
		List<Button> buttons = buttonRepository.findAll();
		// then
		assertThat(buttons.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("Find a button by it's id number")
	void givenButton_whenFindButtonById_thenReturnButton() {
		// given
		buttonRepository.save(button);
		// when
		Optional<Button> optionalButton = buttonRepository.findById(1L);
		// then
		assertThat(optionalButton.isPresent()).isTrue();
		assertThat(optionalButton.get()).isEqualTo(button);
	}

	@Test
	@DisplayName("Find a button by it's location on board id")
	void givenButton_whenFindButtonByLocationOnBoard_thenReturnButton() {
		// given
		button.setLocationOnBoardId((short) 0);
		buttonRepository.save(button);
		// when
		Optional<Button> optionalButton = buttonRepository.findByLocationOnBoardId(button.getLocationOnBoardId());
		// then
		assertThat(optionalButton.isPresent()).isTrue();
		assertThat(optionalButton.get()).isEqualTo(button);
	}

	@Test
	@DisplayName("Updating button fields")
	void givenButton_whenUpdateButton_thenReturnUpdatedButton() {
		// given
		buttonRepository.save(button);
		Button savedButton = buttonRepository.findById(button.getId()).get();
		savedButton.setName("Something else");
		savedButton.setLocationOnBoardId((short) 3);
		// when
		Button updatedButton = buttonRepository.save(savedButton);
		// then
		assertThat(updatedButton).isNotNull();
		assertThat(updatedButton.getName()).isEqualTo("Something else");
		assertThat(updatedButton.getLocationOnBoardId()).isEqualTo(savedButton.getLocationOnBoardId());
	}

	@Test
	@DisplayName("Find button by his first name and last name (JPQL)")
	void givenButton_whenFindButtonByNameAndLocationOnBoardJPQL_thenReturnButton() {
		// given
		buttonRepository.save(button);
		// when
		Button foundButton = buttonRepository.findByNameAndLocationOnBoardJPQL(button.getName(), button.getLocationOnBoardId()).get();

		// then
		assertThat(foundButton).isNotNull();
		assertThat(foundButton).isEqualTo(button);
	}

	@Test
	@DisplayName("Find employee by his first name and last name (native SQL)")
	void givenButton_whenFindButtonByNameAndLocationOnBoardNativeSQL_thenReturnButton() {
		// given
		buttonRepository.save(button);
		// when
		Button foundButton = buttonRepository.findByNameAndLocationOnBoardNativeSQL(button.getName(), button.getLocationOnBoardId()).get();

		// then
		assertThat(foundButton).isNotNull();
		assertThat(foundButton).isEqualTo(button);
	}
}