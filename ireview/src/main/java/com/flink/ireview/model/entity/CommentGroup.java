package com.flink.ireview.model.entity;


import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
public class CommentGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private String userAccount;

    @ManyToOne
    private Board board;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "commentGroup")
    private List<Comment> commentList;
}
