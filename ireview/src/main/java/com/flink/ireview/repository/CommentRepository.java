package com.flink.ireview.repository;

import com.flink.ireview.model.entity.Category;
import com.flink.ireview.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
