package com.flink.ireview.service;

import com.flink.ireview.model.entity.AdminUser;
import com.flink.ireview.model.network.Header;
import com.flink.ireview.model.network.request.AdminUserApiRequest;
import com.flink.ireview.model.network.response.AdminUserApiResponse;
import com.flink.ireview.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//로직 서비스에서는 직접 코딩해야한다.
@Service
public class AdminUserLogicService extends BaseService<AdminUserApiRequest, AdminUserApiResponse, AdminUser> {


    @Override
    public Header<AdminUserApiResponse> create(Header<AdminUserApiRequest> request) {
        AdminUserApiRequest body = request.getData();
        AdminUser adminUser = AdminUser.builder()
                .account(body.getAccount())
                .password(body.getPassword())
                .email(body.getEmail())
                .phoneNumber(body.getPhoneNumber())
                .status(body.getStatus())
                .role(body.getRole())
                .lastLoginAt(body.getLastLoginAt())
                .loginFailCount(body.getLonginFailCount())
                .passwordUpdatedAt(body.getPasswordUpdatedAt())
                .registeredAt(body.getRegisteredAt())
                .unregisteredAt(body.getUnregisteredAt())
                .loginIp(body.getLoginIp())
                .totalLogin(body.getTotalLogin())
                .rangkingGrade(body.getRangkingGrade())
                .rangkingPoint(body.getRangkingPoint())
                .createdAt(body.getCreatedAt())
                .createdBy(body.getCreatedBy())
                .updatedAt(body.getUpdatedAt())
                .updatedBy(body.getUpdatedBy())
                .build();

        AdminUser newAdminUser = baseRepository.save(adminUser);
        return Header.OK(response(newAdminUser));
    }

    @Override
    public Header<AdminUserApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(adminUser -> response(adminUser))
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<AdminUserApiResponse> update(Header<AdminUserApiRequest> request) {
        AdminUserApiRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(entityAdminUser->{
                    entityAdminUser.setAccount(body.getAccount())
                            .setPassword(body.getPassword())
                            .setEmail(body.getEmail())
                            .setPhoneNumber(body.getPhoneNumber())
                            .setStatus(body.getStatus())
                            .setRole(body.getRole())
                            .setLastLoginAt(body.getLastLoginAt())
                            .setLoginFailCount(body.getLonginFailCount())
                            .setPasswordUpdatedAt(body.getPasswordUpdatedAt())
                            .setRegisteredAt(body.getRegisteredAt())
                            .setUnregisteredAt(body.getUnregisteredAt())
                            .setLoginIp(body.getLoginIp())
                            .setTotalLogin(body.getTotalLogin())
                            .setRangkingPoint(body.getRangkingPoint())
                            .setRangkingGrade(body.getRangkingGrade())
                            .setCreatedAt(body.getCreatedAt())
                            .setCreatedBy(body.getCreatedBy())
                            .setUpdatedAt(body.getUpdatedAt())
                            .setUpdatedBy(body.getUpdatedBy());

                    return entityAdminUser;
                })
                .map(newEntityUser->baseRepository.save(newEntityUser))
                .map(adminUser -> response(adminUser))
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(adminUser -> {
                    baseRepository.delete(adminUser);
                    return Header.OK();
                })
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Override
    public Header<List<AdminUserApiResponse>> search(Pageable pageable) {
        Page<AdminUser> adminUsers = adminUserRepository.findAll(pageable);
        List<AdminUserApiResponse> adminUserApiResponseList = adminUsers.stream()
                .map(adminUser -> response(adminUser))
                .collect(Collectors.toList());

        return Header.OK(adminUserApiResponseList);
    }

    private AdminUserApiResponse response(AdminUser adminUser){

        AdminUserApiResponse body = AdminUserApiResponse.builder()
                .account(adminUser.getAccount())
                .password(adminUser.getPassword())
                .email(adminUser.getEmail())
                .phoneNumber(adminUser.getPhoneNumber())
                .status(adminUser.getStatus())
                .role(adminUser.getRole())
                .lastLoginAt(adminUser.getLastLoginAt())
                .longinFailCount(adminUser.getLoginFailCount())
                .passwordUpdatedAt(adminUser.getPasswordUpdatedAt())
                .registeredAt(adminUser.getRegisteredAt())
                .unregisteredAt(adminUser.getUnregisteredAt())
                .loginIp(adminUser.getLoginIp())
                .totalLogin(adminUser.getTotalLogin())
                .rangkingGrade(adminUser.getRangkingGrade())
                .rangkingPoint(adminUser.getRangkingPoint())
                .createdAt(adminUser.getCreatedAt())
                .createdBy(adminUser.getCreatedBy())
                .updatedAt(adminUser.getUpdatedAt())
                .updatedBy(adminUser.getUpdatedBy())
                .build();

        return body;
    }
}
