package com.flink.ireview.service;

import com.flink.ireview.model.entity.Comment;
import com.flink.ireview.model.network.Header;
import com.flink.ireview.model.network.request.CommentApiRequest;
import com.flink.ireview.model.network.response.CommentApiResponse;
import com.flink.ireview.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentLogicService extends BaseService<CommentApiRequest, CommentApiResponse, Comment> {

    @Override
    public Header<CommentApiResponse> create(Header<CommentApiRequest> request) {
        CommentApiRequest body = request.getData();
        Comment comment = Comment.builder()
                .status(body.getStatus())
                //.userId(body.getUserId())
                //.boardId(body.getBoardId())
                .commentUserNickname(body.getCommentUserNickname())
                /*.createdAt(body.getCreatedAt())
                .createdBy(body.getCreatedBy())
                .updatedAt(body.getUpdatedAt())
                .updatedBy(body.getUpdatedBy())*/
                .contentString(body.getContentString())
                .build();


        Comment newComment = baseRepository.save(comment);
        return Header.OK(response(newComment));
    }

    @Override
    public Header<CommentApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(comment->response(comment))
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<CommentApiResponse> update(Header<CommentApiRequest> request) {
        CommentApiRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(entityComment -> {
                    entityComment.setStatus(body.getStatus())
                           //.setUserId(body.getUserId())
                            //.setBoardId(body.getBoardId())
                            .setCommentUserNickname(body.getCommentUserNickname())
                            /*.setCreatedAt(body.getCreatedAt())
                            .setCreatedBy(body.getCreatedBy())
                            .setUpdatedAt(body.getUpdatedAt())
                            .setUpdatedBy(body.getUpdatedBy())*/
                            .setContentString(body.getContentString())
                            ;
                    return entityComment;
                })
                .map(newEntityComment->baseRepository.save(newEntityComment))
                .map(comment->response(comment))
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(comment -> {
                    baseRepository.delete(comment);
                    return Header.OK();
                })
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Header<List<CommentApiResponse>> search(Pageable pageable) {
       Page<Comment> comments = baseRepository.findAll(pageable);
        List<CommentApiResponse> commentApiResponseList = comments.stream()
                .map(comment->response(comment))
                .collect(Collectors.toList());

        return Header.OK(commentApiResponseList);

    }

    private CommentApiResponse response (Comment comment){
        CommentApiResponse body = CommentApiResponse.builder()
                .status(comment.getStatus())
               // .userId(comment.getUserId())
               // .boardId(comment.getBoardId())
                .commentUserNickname(comment.getCommentUserNickname())
                /*.createdAt(comment.getCreatedAt())
                .createdBy(comment.getCreatedBy())
                .updatedAt(comment.getUpdatedAt())
                .updatedBy(comment.getUpdatedBy())*/
                .contentString(comment.getContentString())
                .build();

        return body;
    }
}
