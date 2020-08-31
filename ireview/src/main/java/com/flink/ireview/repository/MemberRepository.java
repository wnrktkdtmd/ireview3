package com.flink.ireview.repository;


import com.flink.ireview.model.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<UserInfo,Long> {
    UserInfo findByAccount(String account);
    UserInfo findByEmail(String email);
    UserInfo findByNickName(String nickName);

}
