package com.flink.ireview.repository;

import com.flink.ireview.model.entity.BoardGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardGroupRepository extends JpaRepository<BoardGroup,Long> {
}


