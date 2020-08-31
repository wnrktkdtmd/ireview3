package com.flink.ireview.service;

import com.flink.ireview.model.entity.Board;
import com.flink.ireview.model.network.Header;
import com.flink.ireview.model.network.request.BoardApiRequest;
import com.flink.ireview.model.network.response.BoardApiResponse;
import com.flink.ireview.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardLogicService extends BaseService<BoardApiRequest, BoardApiResponse, Board> {

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public Header<BoardApiResponse> create(Header<BoardApiRequest> request) {
        BoardApiRequest body = request.getData();

        Board board = Board.builder()
                .categoryId(body.getCategoryId())
                .title(body.getTitle())
                .contentString(body.getContentString())
                .userAccount(body.getUserAccount())
                //.userId(body.getUserId())
                .userNickname(body.getUserNickname())
                .totalView(body.getTotalView())
                .totalRecommend(body.getTotalRecommend())
                .totalComment(body.getTotalComment())
                .manageBoard(body.isManageBoard())
                .image1(body.getImage1())
                .image2(body.getImage2())
                .image3(body.getImage3())
                .image4(body.getImage4())
                .image5(body.getImage5())
                .image6(body.getImage6())
                .image7(body.getImage7())
                .image8(body.getImage8())
                .scrapCount(body.getScrapCount())
                .build();
        Board newBoard = baseRepository.save(board);
        return Header.OK(response(newBoard));

    }

    @Override
    public Header<BoardApiResponse> read(Long id) {
        return baseRepository.findById(id).
                map(board->response(board))
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터 없음"));    }

    @Override
    public Header<BoardApiResponse> update(Header<BoardApiRequest> request) {
        BoardApiRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(board->{
                    board.setCategoryId(body.getCategoryId())
                            .setTitle(body.getTitle())
                            .setContentString(body.getContentString())
                            .setUserAccount(body.getUserAccount())
                            //.setUserId(body.getUserId())
                            .setUserNickname(body.getUserNickname())
                            .setTotalView(body.getTotalView())
                            .setTotalRecommend(body.getTotalRecommend())
                            .setTotalComment(body.getTotalComment())
                            .setManageBoard(body.isManageBoard())
                            .setImage1(body.getImage1())
                            .setImage2(body.getImage2())
                            .setImage3(body.getImage3())
                            .setImage4(body.getImage4())
                            .setImage5(body.getImage5())
                            .setImage6(body.getImage6())
                            .setImage7(body.getImage7())
                            .setImage8(body.getImage8())

                            .setScrapCount(body.getScrapCount());

                    return board;
                })
                .map(newEntityBoard->baseRepository.save(newEntityBoard))
                .map(board->response(board))
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(board -> {
                    baseRepository.delete(board);
                    return Header.OK();
                })
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<List<BoardApiResponse>> search(Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);
        List<BoardApiResponse> boardApiResponsesList = boards.stream()
                .map(board->response(board))
                .collect(Collectors.toList());
        //List<UserApiResponse>
        //Header<List<UserApiResponse>>
        return Header.OK(boardApiResponsesList);
    }




    private BoardApiResponse response(Board board){
        BoardApiResponse body = BoardApiResponse.builder()
                .categoryId(board.getCategoryId())
                .title(board.getTitle())
                .contentString(board.getContentString())
                .userAccount(board.getUserAccount())
                //.userId(board.getUserId())
                .userNickname(board.getUserNickname())
                .totalView(board.getTotalView())
                .totalRecommend(board.getTotalRecommend())
                .totalComment(board.getTotalComment())
                .manageBoard(board.isManageBoard())
                .image1(board.getImage1())
                .image2(board.getImage2())
                .image3(board.getImage3())
                .image4(board.getImage4())
                .image5(board.getImage5())
                .image6(board.getImage6())
                .image7(board.getImage7())
                .image8(board.getImage8())
                .scrapCount(board.getScrapCount())

                .build();

        return body;
    }

    @Transactional
    public List<BoardApiResponse> searchPosts(String keyword){
        List<Board> boards = boardRepository.findByTitleContaining(keyword);
        List<BoardApiResponse> boardApiResponseList = new ArrayList<>();

        if(boards.isEmpty())
            return boardApiResponseList;

        for(Board board : boards){
            boardApiResponseList.add(this.convertEntityToApiResponse(board));
        }

        return boardApiResponseList;

    }

    private BoardApiResponse convertEntityToApiResponse(Board board){
        return BoardApiResponse.builder()
                .id(board.getId())
                .categoryId(board.getCategoryId())
                .title(board.getTitle())
                .contentString(board.getContentString())
                .userAccount(board.getUserAccount())
                .userNickname(board.getUserNickname())
                .totalView(board.getTotalView())
                .totalRecommend(board.getTotalRecommend())
                .totalComment(board.getTotalComment())
                .manageBoard(board.isManageBoard())
                .image1(board.getImage1())
                .image2(board.getImage2())
                .image3(board.getImage3())
                .image4(board.getImage4())
                .image5(board.getImage5())
                .image6(board.getImage6())
                .image7(board.getImage7())
                .image8(board.getImage8())
                .scrapCount(board.getScrapCount())
                .build();
    }

}