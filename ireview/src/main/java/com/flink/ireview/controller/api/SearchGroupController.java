package com.flink.ireview.controller.api;

import com.flink.ireview.controller.CrudController;
import com.flink.ireview.model.entity.AdminUser;
import com.flink.ireview.model.entity.SearchGroup;
import com.flink.ireview.model.network.request.AdminUserApiRequest;
import com.flink.ireview.model.network.request.SearchGroupApiRequest;
import com.flink.ireview.model.network.response.AdminUserApiResponse;
import com.flink.ireview.model.network.response.SearchGroupApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/searchGroup") //api/item
public class SearchGroupController extends CrudController<SearchGroupApiRequest, SearchGroupApiResponse, SearchGroup> {


}
