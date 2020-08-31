package com.flink.ireview.controller.api;

import com.flink.ireview.controller.CrudController;
import com.flink.ireview.model.entity.Board;
import com.flink.ireview.model.network.request.BoardApiRequest;
import com.flink.ireview.model.network.response.BoardApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/boardGroup")
public class BoardGroupController extends CrudController<BoardApiRequest, BoardApiResponse, Board> {
}
