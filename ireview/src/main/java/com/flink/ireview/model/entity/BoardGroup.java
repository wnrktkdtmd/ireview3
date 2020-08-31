package com.flink.ireview.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = {"user","searchList"})
@Builder
@Accessors(chain = true)
public class BoardGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    //private Long user_id;
    //private Long board_id;



    //BoardGroup N:1 User
   @ManyToOne
   private User user;

   @OneToMany(fetch = FetchType.LAZY,mappedBy = "searchGroup")
   private List<Search> searchList;


}
