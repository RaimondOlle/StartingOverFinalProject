package com.raimond.startingoverfinalproject.repository1;

import com.raimond.startingoverfinalproject.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PageRepository extends JpaRepository<Page, Long> {

	Optional<Page> findByTitle(String title);

	@Query("SELECT p FROM Page as p WHERE p.title = ?1")
	Optional<Page> findByTitleJPQL(String title);

	@Query(value = "SELECT * FROM pages as p WHERE p.title = ?1", nativeQuery = true)
	Optional<Page> findByTitleNativeSQL(String title);
}
