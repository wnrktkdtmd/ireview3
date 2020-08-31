package com.flink.ireview.service;

import com.flink.ireview.model.entity.UserInfo;
import com.flink.ireview.model.network.Header;
import com.flink.ireview.model.network.request.UserApiRequest;
import com.flink.ireview.model.network.response.MemberApiResponse;
import com.flink.ireview.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberLogicService extends BaseService<UserApiRequest, MemberApiResponse, UserInfo> {
@Autowired
protected MemberRepository repository;
//public String checkPasswordService(String username){
//    UserInfo userInfo  = repository.findByAccount(username);
//    if(userInfo!=null){
//
//    }
//    re
//}
public String signUp(UserInfo user) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    UserInfo newUser = baseRepository.save(user);
    if (newUser != null){
        return "success";
    }else{
        return "fail";
    }
}
public String checkNickName(String nickname){
    UserInfo userInfo = repository.findByNickName(nickname);
    if(userInfo!=null){
        return "일치";
    }else{
        return "불일치";
    }
    }
    public UserInfo myInfo(String userName){
        UserInfo member = repository.findByAccount(userName);
        if(member==null){
        return null;
    }
    return member;
}

public UserInfo myInfoModify(UserInfo userInfo) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//    userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
   return repository.save(userInfo);
}

    @Override
    public Header<MemberApiResponse> create(Header<UserApiRequest> request) {
        UserApiRequest body = request.getData();

        UserInfo user = UserInfo.builder()
                .account(body.getAccount())
                .password(body.getPassword())
                .email(body.getEmail())
                .phoneNumber(body.getPhoneNumber())
                .status(body.getStatus())
                .role(body.getRole())
                .lastLoginAt(body.getLastLoginAt())
               // .loginFailCount(body.getLoginFailCount())
                .passwordUpdatedAt(body.getPasswordUpdatedAt())
                .registeredAt(body.getRegisteredAt())
                .unregisteredAt(body.getUnregisteredAt())
                .loginIp(body.getLoginIp())
                .totalLogin(body.getTotalLogin())
                .rangkingPoint(body.getRangkingPoint())
                .rangkingGrade(body.getRangkingGrade())
                .birthDd(body.getBirthDd())
                .birthMm(body.getBirthMm())
                .birthYy(body.getBirthYy())
                .interest1(body.getInterest1())
                .interest2(body.getInterest2())
                .interest3(body.getInterest3())
                .interest4(body.getInterest4())
                .interest5(body.getInterest5())
                .sumnailImage(body.getSumnailImage())
                .build();

        UserInfo newUser = baseRepository.save(user);
        return Header.OK(response(newUser));
    }
    @Override
    public Header<MemberApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(user->response(user))
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }
    @Override
    public Header<MemberApiResponse> update(Header<UserApiRequest> request) {
        UserApiRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(user -> {
                    user.setAccount(body.getAccount())
                            .setPassword(body.getPassword())
                            .setEmail(body.getEmail())
                            .setPhoneNumber(body.getPhoneNumber())
                            .setStatus(body.getStatus())
                            .setRole(body.getRole())
                            .setLastLoginAt(body.getLastLoginAt())
                            //.setLoginFailCount(body.getLoginFailCount())
                            .setPasswordUpdatedAt(body.getPasswordUpdatedAt())
                            .setRegisteredAt(body.getRegisteredAt())
                            .setUnregisteredAt(body.getUnregisteredAt())
                            .setLoginIp(body.getLoginIp())
                            .setTotalLogin(body.getTotalLogin())
                            .setRangkingPoint(body.getRangkingPoint())
                            .setRangkingGrade(body.getRangkingGrade())
                            .setBirthYy(body.getBirthYy())
                            .setBirthMm(body.getBirthMm())
                            .setBirthDd(body.getBirthDd())
                            .setInterest1(body.getInterest1())
                            .setInterest2(body.getInterest2())
                            .setInterest3(body.getInterest3())
                            .setInterest4(body.getInterest4())
                            .setInterest5(body.getInterest5())
                            .setSumnailImage(body.getSumnailImage())
//                            .setCreatedAt(body.getCreatedAt())
//                            .setCreatedBy(body.getCreatedBy())
//                            .setUpdatedAt(body.getUpdatedAt())
//                            .setUpdatedBy(body.getUpdatedBy());
                            ;
                return user;
                })
                .map(newEntityUser->baseRepository.save(newEntityUser))
                .map(user->response(user))
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(user -> {
                    baseRepository.delete(user);
                    return Header.OK();
                })
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Header<List<MemberApiResponse>> search(Pageable pageable) {
        Page<UserInfo> users = memberRepository.findAll(pageable);
        List<MemberApiResponse> userApiResponseList = users.stream()
                .map(user->response(user))
                .collect(Collectors.toList());
        //List<UserApiResponse>
        //Header<List<UserApiResponse>>
        return Header.OK(userApiResponseList);
    }

    private MemberApiResponse response (UserInfo user){
        MemberApiResponse body = MemberApiResponse.builder()
                .account(user.getAccount())
                .password(user.getPassword())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .role(user.getRole())
                .lastLoginAt(user.getLastLoginAt())
                .loginFailCount(user.getLoginFailCount())
                .passwordUpdatedAt(user.getPasswordUpdatedAt())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .loginIp(user.getLoginIp())
                .totalLogin(user.getTotalLogin())
                .rangkingPoint(user.getRangkingPoint())
                .rangkingGrade(user.getRangkingGrade())
                .birthYy(user.getBirthYy())
                .birthMm(user.getBirthMm())
                .birthDd(user.getBirthDd())
                .interest1(user.getInterest1())
                .interest2(user.getInterest2())
                .interest3(user.getInterest3())
                .interest4(user.getInterest4())
                .interest5(user.getInterest5())
                .sumnailImage(user.getSumnailImage())
//                .createdAt(user.getCreatedAt())
//                .createdBy(user.getCreatedBy())
//                .updatedAt(user.getUpdatedAt())
//                .updatedBy(user.getUpdatedBy())
                .build();
        return body;
    }

}
