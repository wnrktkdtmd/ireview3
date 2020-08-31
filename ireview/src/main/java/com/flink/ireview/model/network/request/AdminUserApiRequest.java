package com.flink.ireview.model.network.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminUserApiRequest {
    private Long id;
    private String account;
    private String password;
    private String email;
    private String phoneNumber;
    private String status;
    private String role;
    private LocalDateTime lastLoginAt;
    private int longinFailCount;
    private LocalDateTime passwordUpdatedAt;
    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
    private String loginIp;
    private Integer totalLogin;
    private Integer rangkingPoint;
    private Integer rangkingGrade;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

}
