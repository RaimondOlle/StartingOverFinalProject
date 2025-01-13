package com.raimond.startingoverfinalproject.service2;

import com.raimond.startingoverfinalproject.entity.Button;
import com.raimond.startingoverfinalproject.repository1.ButtonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ButtonService {
	private final ButtonRepository buttonRepository;

	public ButtonService(ButtonRepository buttonRepository) {
		this.buttonRepository = buttonRepository;
	}

	public Button save(Button button) {
		Optional<Button> savedButton = buttonRepository.findByLocationOnBoardId(button.getLocationOnBoardId());
		if (savedButton.isPresent()) {
			throw new RuntimeException("Button already exists " + button.getLocationOnBoardId());
		}
		return buttonRepository.save(button);
	}

	public List<Button> findAll() {
		return buttonRepository.findAll();
	}

	public Optional<Button> findById(long l) {
		return buttonRepository.findById(l);
	}

	public Button update(Button button) {
		return buttonRepository.save(button);
	}

	public void deleteById(long buttonId) {
		buttonRepository.deleteById(buttonId);
	}
}
