package com.raimond.startingoverfinalproject.repository1;

import com.raimond.startingoverfinalproject.entity.Button;
import com.raimond.startingoverfinalproject.entity.Page;
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
class PageRepositoryTest {

	@Autowired
	private PageRepository pageRepository;
	@Autowired
	private EntityManager entityManager;
	private Page page;

	@BeforeEach
	void setUp() {
		pageRepository.deleteAll();
		entityManager.createNativeQuery(
				"ALTER TABLE pages ALTER COLUMN id RESTART WITH 1"
		).executeUpdate();

		page = new Page();
		page.setTitle("main");
	}

	@Test
	@DisplayName("Test for page saving method")
	void givenPage_whenSaving_thenReturnSavedPage() {
		// given
		// when
		Page savedPage = pageRepository.save(page);
		// then
		assertThat(savedPage).isNotNull();
		assertThat(savedPage.getId()).isEqualTo(1L);
		assertThat(savedPage.getTitle()).isEqualTo("main");
	}

	@Test
	@DisplayName("Test returning non empty list of pages")
	void givenTwoPages_whenFindAll_thenReturnListOfPagesIsNotEmpty() {
		// given
		Page page1 = new Page();
		page1.setTitle("page1");
		pageRepository.save(page);
		pageRepository.save(page1);
		// when
		List<Page> pages = pageRepository.findAll();
		// then
		assertThat(pages).isNotNull();
	}

	@Test
	@DisplayName("Returning pages")
	void givenTwoPages_whenFindAll_thenReturnListOfPages() {
		// given
		Page page1 = new Page();
		page1.setTitle("page1");
		pageRepository.save(page);
		pageRepository.save(page1);
		// when
		List<Page> pages = pageRepository.findAll();
		// then
		assertThat(pages.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("Find a page by it's id number")
	void givenPage_whenFindPageById_thenReturnPage() {
		// given
		pageRepository.save(page);
		// when
		Optional<Page> optionalPage = pageRepository.findById(1L);
		// then
		assertThat(optionalPage.isPresent()).isTrue();
		assertThat(optionalPage.get()).isEqualTo(page);
	}

	@Test
	@DisplayName("Find a page by it's title")
	void givenPage_whenFindPageByTitle_thenReturnPage() {
		// given
		page.setTitle("page1");
		pageRepository.save(page);
		// when
		Optional<Page> optionalPage = pageRepository.findByTitle(page.getTitle());
		// then
		assertThat(optionalPage.isPresent()).isTrue();
		assertThat(optionalPage.get()).isEqualTo(page);
	}

	@Test
	@DisplayName("Updating page fields")
	void givenPage_whenUpdatePage_thenReturnUpdatedPage() {
		// given
		pageRepository.save(page);
		Page savedPage = pageRepository.findById(page.getId()).get();
		savedPage.setTitle("page2");
		// when
		Page updatedPage = pageRepository.save(savedPage);
		// then
		assertThat(updatedPage).isNotNull();
		assertThat(updatedPage.getTitle()).isEqualTo("page2");
	}

	@Test
	@DisplayName("Find page by it's title(JPQL)")
	void givenPage_whenFindByTitleJPQL_thenReturnPage() {
		// given
		pageRepository.save(page);
		// when
		Page foundPage = pageRepository.findByTitleJPQL(page.getTitle()).get();
		// then
		assertThat(foundPage).isNotNull();
		assertThat(foundPage.getTitle()).isEqualTo("main");
		assertThat(foundPage).isEqualTo(page);
	}

	@Test
	@DisplayName("Find page by it's title(native SQL)")
	void givenPage_whenFindPageByTitleNativeSQL_thenReturnPage() {
		// given
		pageRepository.save(page);
		// when
		Page foundPage = pageRepository.findByTitleNativeSQL(page.getTitle()).get();
		// then
		assertThat(foundPage).isNotNull();
		assertThat(foundPage).isEqualTo(page);
	}

	@Test
	@DisplayName("Test saving page with a button")
	void givenPage_whenSaving_thenReturnSavedPageWithButton() {
		// given
		Button button = new Button();
		button.setName("button0");
		button.setLocationOnBoardId((short) 0);
		button.setPage(page);
		page.setButtonList(List.of(button));
		// when
		Page savedPage = pageRepository.save(page);
		// then
		assertThat(savedPage).isNotNull();
		assertThat(savedPage.getId()).isEqualTo(1L);
		assertThat(savedPage.getButtonList().size()).isEqualTo(1);
	}

	@Test
	@DisplayName("Test saving page with buttons")
	void givenButton_whenSaving_thenReturnSavedPageWithButtons() {
		// given
		Button button = new Button();
		button.setName("button0");
		button.setLocationOnBoardId((short) 0);
		button.setPage(page);
		Button button1 = new Button();
		button1.setName("button1");
		button1.setLocationOnBoardId((short) 1);
		button1.setPage(page);
		page.setButtonList(List.of(button,button1));
		// when
		Page savedPage = pageRepository.save(page);
		// then
		assertThat(savedPage).isNotNull();
		assertThat(savedPage.getButtonList().size()).isEqualTo(2);

	}

	@Test
	@DisplayName("Test find a button by it's id number in page")
	void givenPageAndButton_whenFindButtonById_thenReturnButton() {
		// given
		Button button = new Button();
		button.setName("button0");
		button.setLocationOnBoardId((short) 0);
		button.setPage(page);
		page.setButtonList(List.of(button));
		// when
		Page savedPage = pageRepository.save(page);
		Optional<Button> optionalButton = savedPage.getButtonList().stream().filter(b -> b.getId() == button.getId()).findFirst();
		// then
		assertThat(optionalButton.isPresent()).isTrue();
		assertThat(optionalButton.get()).isEqualTo(button);
		assertThat(savedPage.getButtonList().size()).isEqualTo(1);
	}

	@Test
	@DisplayName("Test find button by it's title in page")
	void givenPageAndButton_whenFindButtonByTitleInPage_thenReturnButton() {
		// given
		Button button = new Button();
		button.setName("button0");
		button.setLocationOnBoardId((short) 0);
		button.setPage(page);
		page.setButtonList(List.of(button));
		Page savedPage = pageRepository.save(page);
		// when
		Optional<Button> optionalButton = savedPage.getButtonList().stream().filter(button1 -> button1.getName().equals(button.getName())).findFirst();
		// then
		assertThat(optionalButton.isPresent()).isTrue();
		assertThat(optionalButton.get()).isEqualTo(button);
		assertThat(savedPage.getButtonList().size()).isEqualTo(1);
	}

	@Test
	@DisplayName("Test when updating button in page it updates page too")
	void givenPageAndButton_whenUpdatingButtonInPage_thenReturnUpdatedPage() {
		// given
		Button button = new Button();
		button.setName("button0");
		button.setLocationOnBoardId((short) 0);
		button.setPage(page);
		page.setButtonList(List.of(button));
		// when
		Page savedPage = pageRepository.save(page);
		button.setLocationOnBoardId((short) 1);
		button.setName("button1");
		// then
		assertThat(savedPage).isNotNull();
		assertThat(savedPage.getButtonList().stream().filter(b -> b.getId() == button.getId()).findFirst().get()).isEqualTo(button);
	}
}