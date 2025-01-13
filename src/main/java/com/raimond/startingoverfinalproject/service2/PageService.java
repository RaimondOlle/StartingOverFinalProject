package com.raimond.startingoverfinalproject.service2;

import com.raimond.startingoverfinalproject.entity.Page;
import com.raimond.startingoverfinalproject.repository1.PageRepository;

import java.util.List;
import java.util.Optional;

public class PageService {

	private final PageRepository pageRepository;

	public PageService(PageRepository pageRepository) {
		this.pageRepository = pageRepository;
	}

	public Page save(Page page) {
		Optional<Page> savedPage = pageRepository.findByTitle(page.getTitle());
		if (savedPage.isPresent()) {
			throw new RuntimeException("Title already exists " + page.getTitle());
		}
		return pageRepository.save(page);
	}

	public List<Page> findAll() {
		return pageRepository.findAll();
	}

	public Optional<Page> findById(long id) {
		return pageRepository.findById(id);
	}

	public Page update(Page page) {
		return pageRepository.save(page);
	}

	public void deleteById(long id) {
		pageRepository.deleteById(id);
	}
}
