package com.flink.ireview.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.w3c.dom.Text;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = {"boardGroup","commentGroupList"})
@Builder
@Accessors(chain = true)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer categoryId;
    private String title;
    private String contentString;
    private String userAccount;
    //private Long userId;
    private String userNickname;
    private Integer totalView;
    private Integer totalRecommend;
    private Integer totalComment;
    private boolean manageBoard;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String image5;
    private String image6;
    private String image7;
    private String image8;
    private Long scrapCount;

    @ManyToOne
    private BoardGroup boardGroup;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "board")
    private List<CommentGroup> commentGroupList;
}
