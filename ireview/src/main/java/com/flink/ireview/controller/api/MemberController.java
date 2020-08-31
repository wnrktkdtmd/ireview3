package com.flink.ireview.controller.api;

import com.flink.ireview.model.entity.UserInfo;
import com.flink.ireview.service.MemberLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    @Autowired
    MemberLogicService memberLogicService;

    @PostMapping("/signUp")
    public String signUp(@RequestParam("account") String account, @RequestParam("password") String password, @RequestParam("email") String email
            , @RequestParam("phoneNumber") String phoneNumber, @RequestParam("name") String name, @RequestParam("nickName") String nickName,
                         @RequestParam("loginIp") String loginIp, @RequestParam("birthYy") String birthYy, @RequestParam("birthMm") String birthMm
            , @RequestParam("birthDd") String birthDd, @RequestParam("gender") String gender, @RequestParam("interest1") String interest1, @RequestParam("interest2") String interest2
            , @RequestParam("interest3") String interest3, @RequestParam("interest4") String interest4, @RequestParam("interest5") String interest5
    ) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserInfo member = UserInfo.builder().account(account)
                .password(password)
                .email(email)
                .name(name)
                .nickName(nickName)
                .loginIp(loginIp)
                .phoneNumber(phoneNumber)
                .totalLogin(0)
                .gender(gender)
//                .loginFailCount(0)
                .status("playing")
                .role("user")
                .rangkingGrade(1)
                .rangkingPoint(0)
                .birthYy(Integer.parseInt(birthYy))
                .birthMm(Integer.parseInt(birthMm))
                .birthDd(Integer.parseInt(birthDd))
                .interest1(Long.parseLong(interest1))
                .interest2(Long.parseLong(interest2))
                .interest3(Long.parseLong(interest3))
                .interest4(Long.parseLong(interest4))
                .interest5(Long.parseLong(interest5))
                .sumnailImage(null).build();
//        System.out.println(member.getPassword());
//        member.setPassword(passwordEncoder.encode(member.getPassword()));
//        System.out.println(member.getPassword());
        System.out.println("----");
        return memberLogicService.signUp(member);
    }
    @RequestMapping(method = RequestMethod.GET , value = "/findId")
    public String findId(@RequestParam("name") String name , @RequestParam("email") String email){
        return null;
    }
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public void login(@RequestParam("username") String username, @RequestParam("password") String password) {
    }

    @RequestMapping(method = RequestMethod.GET, value = "/login/success")
    public @ResponseBody
    String loginSuccess() {
        return "성공";
    }

    @RequestMapping(method = RequestMethod.GET,value = "/login/failure")
    public String loginFail() {
        return "failure";
    }
    @RequestMapping(method = RequestMethod.POST, value = "/myInfo")
    public @ResponseBody
    ArrayList<UserInfo> myInfo(@RequestParam("username") String username) {
        ArrayList<UserInfo> list = new ArrayList<>();
        UserInfo member = memberLogicService.myInfo(username);
        list.add(member);
        if (member == null) {
            return null;
        } else {
            System.out.println("ddd" + member.getEmail());
            return list;
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/myInfo/check/Info")
    public @ResponseBody
    String checkInfo(@RequestParam("username") String username, @RequestParam("password") String password) {
    // 여기서 username 은 아이디를 의미
        UserInfo userInfo = memberLogicService.myInfo(username);
        if(password.equals("null")){
            System.out.println("비밀번호 null");
            if(userInfo==null){
                return "불일치";
            }
            if(userInfo.getAccount().equals(username)){
                return "일치";
            }else{
                System.out.println("userinfo null");
                return "불일치";
            }
        }
       else{
           if(userInfo.getPassword().equals(password)){
                return "일치";
            }else{
                return "불일치";
            }
        }
    }
//    @RequestMapping(method = RequestMethod.)
    @RequestMapping(method = RequestMethod.POST, value = "/myInfo/modify")
    public @ResponseBody
    ArrayList<UserInfo> myInfoModify(@RequestParam("id") String id, @RequestParam("account") String account, @RequestParam("password") String password, @RequestParam("email") String email
            , @RequestParam("phoneNumber") String phoneNumber, @RequestParam("name") String name, @RequestParam("nickName") String nickName,
                          @RequestParam("loginIp") String loginIp, @RequestParam("birthYy") String birthYy, @RequestParam("birthMm") String birthMm
            , @RequestParam("birthDd") String birthDd, @RequestParam("gender") String gender, @RequestParam("interest1") String interest1, @RequestParam("interest2") String interest2
            , @RequestParam("interest3") String interest3, @RequestParam("interest4") String interest4, @RequestParam("interest5") String interest5,
                                     @RequestParam("status") String status , @RequestParam("role") String role , @RequestParam("totalLoginAt") String totalLoginAt,
                                     @RequestParam("rangkingPoint") String rangkingPoint , @RequestParam("rangkingGrade") String rangkingGrade, @RequestParam("createdAt") String createdAt,
                                     @RequestParam("sumnailImage") String sumnailImage) {
        UserInfo temp = new UserInfo();
        temp.setPassword(password);
        temp.setEmail(email);
        temp.setPhoneNumber(phoneNumber);
        temp.setName(name);
        temp.setNickName(nickName);
        temp.setLoginIp(loginIp);
        temp.setBirthYy(Integer.parseInt(birthYy));
        temp.setBirthMm(Integer.parseInt(birthMm));
        temp.setBirthDd(Integer.parseInt(birthDd));
        temp.setGender(gender);
        temp.setInterest1(Long.parseLong(interest1));
        temp.setInterest2(Long.parseLong(interest2));
        temp.setInterest3(Long.parseLong(interest3));
        temp.setInterest4(Long.parseLong(interest4));
        temp.setInterest5(Long.parseLong(interest5));
        temp.setAccount(account);
        temp.setLoginIp(loginIp);
        temp.setRangkingGrade(Integer.parseInt(rangkingGrade));
        temp.setStatus(status);
        temp.setRangkingPoint(Integer.parseInt(rangkingPoint));
        temp.setRole(role);
        temp.setId(Long.parseLong(id));
        UserInfo userInfo = memberLogicService.myInfoModify(temp);
        if(userInfo!=null){
            ArrayList<UserInfo> list = new ArrayList<>();
            list.add(userInfo);
            return list;
        }else{
            return null;
        }
    }
@RequestMapping(method = RequestMethod.GET , value = "/logout/result")
    public @ResponseBody String logout(){
        System.out.println("성");
        return "성공";
}
@RequestMapping(method = RequestMethod.POST,value = "/check/nickname")
    public @ResponseBody String checkNickname(@RequestParam("nickname") String nickname){
        System.out.println("test"+nickname);
        String result = memberLogicService.checkNickName(nickname);
        if(result.equals("일치")){
            return "일치";
        }else{
            return "불일치";
        }
}
@RequestMapping(method = RequestMethod.POST, value = "/check/email")
    public @ResponseBody String checkEmail(@RequestParam("email") String email){
        return null;
}
}
