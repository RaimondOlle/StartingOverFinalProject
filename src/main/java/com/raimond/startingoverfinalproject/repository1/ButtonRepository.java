package com.raimond.startingoverfinalproject.repository1;

import com.raimond.startingoverfinalproject.entity.Button;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ButtonRepository extends JpaRepository<Button, Long> {

	Optional<Button> findByLocationOnBoardId(short locationOnBoardId);

	@Query("SELECT b FROM Button as b WHERE b.name = ?1 AND b.locationOnBoardId = ?2")
	Optional<Button> findByNameAndLocationOnBoardJPQL(String name, short locationOnBoardId);

	@Query(value = "SELECT * FROM buttons as b WHERE b.name = ?1 AND b.location_on_board_id = ?2", nativeQuery = true)
	Optional<Button> findByNameAndLocationOnBoardNativeSQL(String name, short locationOnBoardId);
}
