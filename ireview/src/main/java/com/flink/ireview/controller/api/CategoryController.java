package com.flink.ireview.controller.api;

import com.flink.ireview.controller.CrudController;
import com.flink.ireview.model.entity.Category;
import com.flink.ireview.model.network.request.CategoryApiRequest;
import com.flink.ireview.model.network.response.CategoryApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryController extends CrudController<CategoryApiRequest, CategoryApiResponse, Category> {


}
