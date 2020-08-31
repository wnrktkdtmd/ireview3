package com.flink.ireview.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
//@ToString(exclude={"boardGroupList","user"})
@Builder
@Accessors(chain = true)
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String gender;
    private String account;
    private String password;
    private String email;
    private String name ;
    private String nickName;
    private String phoneNumber;
    private String status;
    private String role;
    private LocalDateTime lastLoginAt;
    private int loginFailCount;
    private LocalDateTime passwordUpdatedAt;
    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
    private String loginIp;
    private Integer totalLogin;
    private Integer rangkingPoint;
    private Integer rangkingGrade;
    private int birthYy;
    private int birthMm;
    private int birthDd;

    private Long interest1;
    private Long interest2;
    private Long interest3;
    private Long interest4;
    private Long interest5;

    private String sumnailImage;

    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;

    //user를 기준으로 보드와 연결시킨다.
    //User BoardGroup Board를 연결
    // User와 BoardGroup 는 1:N 관계

//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
//    private List<BoardGroup> boardGroupList;


    //BoardGroup과 Board는 N:1관계로 ㅇ녀결



}