package com.flink.ireview.controller.api;

import com.flink.ireview.controller.CrudController;
import com.flink.ireview.model.entity.Search;
import com.flink.ireview.model.network.request.SearchApiRequest;
import com.flink.ireview.model.network.response.SearchApiResponse;
import com.flink.ireview.service.SearchLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/search")
public class SearchController extends CrudController<SearchApiRequest, SearchApiResponse, Search> {

}
