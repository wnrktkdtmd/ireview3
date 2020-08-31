package com.flink.ireview.service;

import com.flink.ireview.model.entity.BoardGroup;
import com.flink.ireview.model.network.Header;
import com.flink.ireview.model.network.request.BoardGroupApiRequest;
import com.flink.ireview.model.network.response.BoardGroupApiResponse;
import com.flink.ireview.repository.BoardGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardGroupLogicService extends BaseService<BoardGroupApiRequest, BoardGroupApiResponse, BoardGroup> {

    @Override
    public Header<BoardGroupApiResponse> create(Header<BoardGroupApiRequest> request) {
        BoardGroupApiRequest body = request.getData();

        BoardGroup boardGroup = BoardGroup.builder()
                .status(body.getStatus())
               // .user_id(body.getUser_id())
                //.board_id(body.getBoard_id())

                .build();

        BoardGroup newBoardGroup = baseRepository.save(boardGroup);
        return Header.OK(response(newBoardGroup));
    }

    @Override
    public Header<BoardGroupApiResponse> read(Long id) {
        return baseRepository.findById(id).
                map(boardGroup->response(boardGroup))
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<BoardGroupApiResponse> update(Header<BoardGroupApiRequest> request) {
        BoardGroupApiRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(boardGroup -> {
                    boardGroup.setStatus(body.getStatus())
                            //.setUser_id(body.getUser_id())
                            //.setBoard_id(body.getBoard_id())
                            ;
                    return boardGroup;
                })
                .map(newEntityBoardGroup->baseRepository.save(newEntityBoardGroup))
                .map(boardGroup->response(boardGroup))
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터 없음"));

    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(boardGroup -> {
                    baseRepository.delete(boardGroup);
                    return Header.OK();
                })
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Autowired
    private BoardGroupRepository boardGroupRepository;
    @Override
    public Header<List<BoardGroupApiResponse>> search(Pageable pageable) {
        Page<BoardGroup> boardGroups = boardGroupRepository.findAll(pageable);
        List<BoardGroupApiResponse> boardGroupApiResponsesList = boardGroups.stream()
                .map(boardGroup->response(boardGroup))
                .collect(Collectors.toList());
        //List<UserApiResponse>
        //Header<List<UserApiResponse>>
        return Header.OK(boardGroupApiResponsesList);
    }

    private BoardGroupApiResponse response (BoardGroup boardGroup){
        BoardGroupApiResponse body = BoardGroupApiResponse.builder()
                .status(boardGroup.getStatus())
                //.user_id(boardGroup.getUser_id())
                //.board_id(boardGroup.getBoard_id())

                .build();

        return body;
    }
}
