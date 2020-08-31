package com.flink.ireview.repository;

import com.flink.ireview.model.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


//검색기능 구현 위한 코드
@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {

    //Post-->Board로 바꿔준다.
    Page<Board> findAll(Pageable pageable);

    @Query (
        value = "SELECT p FROM Board p WHERE p.title Like %:title% OR p.title LIKE %:content%",
        countQuery = "SELECT COUNT(p.id) FROM Board p WHERE p.title LIKE %:title% OR p.title LIKE %:content%"
    )

    Page<Board> findAllSearch(String title, String content, Pageable pageable);
    List<Board> findByTitleContaining(String keyword);
}
