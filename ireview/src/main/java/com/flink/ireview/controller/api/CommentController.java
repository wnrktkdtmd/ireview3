package com.flink.ireview.controller.api;

import com.flink.ireview.controller.CrudController;
import com.flink.ireview.model.entity.Comment;
import com.flink.ireview.model.network.request.CommentApiRequest;
import com.flink.ireview.model.network.response.CommentApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment")
public class CommentController extends CrudController<CommentApiRequest, CommentApiResponse, Comment> {
}
