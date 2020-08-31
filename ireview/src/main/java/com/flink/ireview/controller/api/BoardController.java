package com.flink.ireview.controller.api;

import com.flink.ireview.controller.CrudController;
import com.flink.ireview.model.entity.Board;
import com.flink.ireview.model.network.Header;
import com.flink.ireview.model.network.request.BoardApiRequest;
import com.flink.ireview.model.network.response.BoardApiResponse;
import com.flink.ireview.repository.BoardRepository;
import com.flink.ireview.service.BaseService;
import com.flink.ireview.service.BoardLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
public class BoardController extends CrudController<BoardApiRequest, BoardApiResponse, Board> {

    //함수를 컨트롤러에 구현해도 되는건가??


    @Autowired
    private BoardLogicService boardLogicService;
    @GetMapping("/search")
    public List<BoardApiResponse> search(@RequestParam(value="keyword")String keyword,Model model){
        List<BoardApiResponse> boardList = boardLogicService.searchPosts(keyword);
        model.addAttribute("boardList",boardList);
        return boardList;
    }

}
