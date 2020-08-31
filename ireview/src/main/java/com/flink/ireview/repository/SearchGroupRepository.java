package com.flink.ireview.repository;


import com.flink.ireview.model.entity.Search;
import com.flink.ireview.model.entity.SearchGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchGroupRepository  extends JpaRepository<SearchGroup,Long> {
}
