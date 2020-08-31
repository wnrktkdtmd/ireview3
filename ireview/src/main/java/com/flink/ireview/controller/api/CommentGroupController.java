package com.flink.ireview.controller.api;

import com.flink.ireview.controller.CrudController;
import com.flink.ireview.model.entity.CommentGroup;
import com.flink.ireview.model.network.request.CommentGroupApiRequest;
import com.flink.ireview.model.network.response.CommentGroupApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/commentGroup")
public class CommentGroupController extends CrudController<CommentGroupApiRequest, CommentGroupApiResponse, CommentGroup> {
}
