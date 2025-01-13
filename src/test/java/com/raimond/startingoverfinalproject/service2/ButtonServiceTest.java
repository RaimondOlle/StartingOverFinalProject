package com.raimond.startingoverfinalproject.service2;

import static org.junit.jupiter.api.Assertions.*;

import com.raimond.startingoverfinalproject.entity.Button;
import com.raimond.startingoverfinalproject.repository1.ButtonRepository;
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
class ButtonServiceTest {

	@Mock
	private ButtonRepository buttonRepository;
	@InjectMocks
	private ButtonService buttonService;

	private Button button;

	@BeforeEach
	void setUp() {
		button = new Button(
				1L,
				"Button1",
				(short) 1
		);
	}

	@Test
	@DisplayName("Saving button without any exceptions")
	void givenButton_whenSavingButton_thenReturnButton() {
		// given
		given(buttonRepository.findByLocationOnBoardId(button.getLocationOnBoardId())).willReturn(Optional.empty());
		given(buttonRepository.save(button)).willReturn(button);
		// when
		Button savedButton = buttonService.save(button);
		// then
		assertThat(savedButton).isNotNull();
		assertThat(savedButton.getLocationOnBoardId()).isEqualTo(button.getLocationOnBoardId());
	}

	@Test
	@DisplayName("Saving button with an exception")
	void givenButton_whenSaveButton_thenThrowException() {
		// given
		given(buttonRepository.findByLocationOnBoardId(button.getLocationOnBoardId())).willReturn(Optional.of(button));
		// when
		// then
		assertThrows(RuntimeException.class, () -> buttonService.save(button));
		verify(buttonRepository, never()).save(button);
	}

	@Test
	@DisplayName("Finding all buttons - positive scenario")
	void givenButton_whenFindingAllButtons_thenReturnList() {
		// given
		Button button1 = new Button(2L, "Button0", (short) 0);
		given(buttonRepository.findAll()).willReturn(List.of(button,button1));
		// when
		List<Button> buttonList = buttonService.findAll();
		// then
		assertThat(buttonList).isNotNull();
		assertThat(buttonList).hasSize(2);
	}

	@Test
	@DisplayName("Finding all buttons - negative scenario")
	void givenButton_whenFindingAllButtons_thenReturnEmptyList() {
		// given
		given(buttonRepository.findAll()).willReturn(Collections.emptyList());
		// when
		List<Button> buttonList = buttonService.findAll();
		// then
		assertThat(buttonList).isNotNull();
		assertThat(buttonList).isEmpty();
	}

	@Test
	@DisplayName("Finding button by it's id - positive scenario")
	void givenButton_whenFindingButtonById_thenReturnButton() {
		// given
		given(buttonRepository.findById(anyLong())).willReturn(Optional.of(button));
		// when
		Optional<Button> optionalButton = buttonService.findById(anyLong());
		// then
		assertThat(optionalButton).isNotNull();
		assertThat(optionalButton).isEqualTo(Optional.of(button));
	}

	@Test
	@DisplayName("Finding button by it's id - negative scenario")
	void givenButton_whenFindingButtonById_thenReturnEmpty() {
		// given
		given(buttonRepository.findById(anyLong())).willReturn(Optional.empty());
		// when
		Optional<Button> optionalButton = buttonService.findById(anyLong());
		// then
		assertThat(optionalButton).isNotNull();
		assertThat(optionalButton).isEmpty();
	}

	@Test
	@DisplayName("Updating a button")
	void givenButton_whenUpdatingButton_thenReturnButton() {
		// given
		given(buttonRepository.save(button)).willReturn(button);
		button.setLocationOnBoardId((short) 20);
		button.setName("Test");
		// when
		Button updatedButton = buttonService.update(button);
		// then
		assertThat(updatedButton.getLocationOnBoardId()).isEqualTo((short) 20);
		assertThat(updatedButton.getName()).isEqualTo("Test");
	}

	@Test
	@DisplayName("Deleting a button by it's id")
	void givenButton_whenDeletingButtonById_thenVerifyUsedOnce() {
		// given
		long buttonId = 1L;
		willDoNothing().given(buttonRepository).deleteById(buttonId);
		// when
		buttonService.deleteById(buttonId);
		// then
		verify(buttonRepository, times(1)).deleteById(buttonId);
	}
}