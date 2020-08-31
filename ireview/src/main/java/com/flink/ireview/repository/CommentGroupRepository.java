package com.flink.ireview.repository;

import com.flink.ireview.model.entity.AdminUser;
import com.flink.ireview.model.entity.CommentGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentGroupRepository extends JpaRepository<CommentGroup,Long>{
}
