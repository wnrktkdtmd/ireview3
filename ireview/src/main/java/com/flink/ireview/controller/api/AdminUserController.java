package com.flink.ireview.controller.api;

import com.flink.ireview.controller.CrudController;
import com.flink.ireview.model.entity.AdminUser;
import com.flink.ireview.model.network.request.AdminUserApiRequest;
import com.flink.ireview.model.network.response.AdminUserApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/adminUser") //api/item
public class AdminUserController extends CrudController<AdminUserApiRequest, AdminUserApiResponse, AdminUser> {


}
