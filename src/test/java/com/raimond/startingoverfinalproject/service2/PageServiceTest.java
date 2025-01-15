package com.raimond.startingoverfinalproject.service2;

import com.raimond.startingoverfinalproject.entity.Button;
import com.raimond.startingoverfinalproject.entity.Page;
import com.raimond.startingoverfinalproject.repository1.ButtonRepository;
import com.raimond.startingoverfinalproject.repository1.PageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PageServiceTest {

	@Mock
	private PageRepository pageRepository;
	@Mock
	private ButtonRepository buttonRepository;

	@InjectMocks
	private PageService pageService;

	private Page page;
	private Button button1, button2;

	@BeforeEach
	void setUp() {
		page = new Page("Main Page");

		button1 = new Button("button1", (short) 1);
		button2 = new Button("button2", (short) 2);
	}

	@Test
	@DisplayName("Saving page without any exceptions")
	void givenPage_whenSavePage_thenReturnPage() {
		// given
		given(pageRepository.findByTitle(page.getTitle())).willReturn(Optional.empty());
		given(pageRepository.save(page)).willReturn(page);
		// when
		Page savedPage = pageService.save(page);
		// then
		assertThat(savedPage).isNotNull();
		assertThat(savedPage.getTitle()).isEqualTo(page.getTitle());
	}

	@Test
	@DisplayName("saving page with an exception")
	void givenPage_whenSavePage_thenThrowException() {
		// given
		given(pageRepository.findByTitle(page.getTitle())).willReturn(Optional.of(page));
		// when
		// then
		assertThrows(RuntimeException.class, () -> pageService.save(page));
		verify(pageRepository, never()).save(page);
	}

	@Test
	@DisplayName("Finding all pages - positive scenario")
	void givenPage_whenFindAllPages_thenReturnPages() {
		// given
		Page page1 = new Page("page1");
		given(pageRepository.findAll()).willReturn(List.of(page, page1));
		// when
		List<Page> pageList = pageService.findAll();
		// then
		assertThat(pageList).isNotNull();
		assertThat(pageList.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("Finding all pages - negative scenario")
	void givenPage_whenFindAllPages_thenReturnEmptyList() {
		// given
		given(pageRepository.findAll()).willReturn(Collections.emptyList());
		// when
		List<Page> pageList = pageService.findAll();
		// then
		assertThat(pageList).isNotNull();
		assertThat(pageList).isEmpty();
	}

	@Test
	@DisplayName("Finding page by it's id pos scenario")
	void givenPage_whenFindPageById_thenReturnPage() {
		// given
		given(pageRepository.findById(anyLong())).willReturn(Optional.of(page));
		// when
		Optional<Page> optionalPage = pageService.findById(anyLong());
		// then
		assertThat(optionalPage).isNotNull();
		assertThat(optionalPage).isEqualTo(Optional.of(page));
	}

	@Test
	@DisplayName("Finding page by it's id neg scenario")
	void givenPage_whenFindPageById_thenReturnOptionalOfEmpty() {
		// given
		given(pageRepository.findById(anyLong())).willReturn(Optional.empty());
		// when
		Optional<Page> optionalPage = pageService.findById(anyLong());
		// then
		assertThat(optionalPage).isNotNull();
		assertThat(optionalPage).isEmpty();
	}

	@Test
	@DisplayName("Updating a page")
	void givenPage_whenUpdatePage_thenReturnPage() {
		// given
		given(pageRepository.save(page)).willReturn(page);
		page.setTitle("New Title");
		// when
		Page updatedPage = pageService.update(page);
		// then
		assertThat(updatedPage.getTitle()).isEqualTo("New Title");
	}

	@Test
	@DisplayName("Deleting a page by it's id")
	void givenPage_whenDeleteById_thenVerifyUsedOnce() {
		// given
		long id = 1L;
		willDoNothing().given(pageRepository).deleteById(id);
		// when
		pageService.deleteById(id);
		// then
		verify(pageRepository, times(1)).deleteById(id);
	}
}