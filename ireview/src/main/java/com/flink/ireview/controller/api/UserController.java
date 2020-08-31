package com.flink.ireview.controller.api;

import com.flink.ireview.controller.CrudController;
import com.flink.ireview.model.entity.User;
import com.flink.ireview.model.network.request.UserApiRequest;
import com.flink.ireview.model.network.response.UserApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController  extends CrudController<UserApiRequest, UserApiResponse, User> {
}
