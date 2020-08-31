package com.flink.ireview.service;

import com.flink.ireview.model.entity.Comment;
import com.flink.ireview.model.entity.CommentGroup;
import com.flink.ireview.model.entity.SearchGroup;
import com.flink.ireview.model.network.Header;
import com.flink.ireview.model.network.request.CommentApiRequest;
import com.flink.ireview.model.network.request.CommentGroupApiRequest;
import com.flink.ireview.model.network.request.SearchGroupApiRequest;
import com.flink.ireview.model.network.response.CommentApiResponse;
import com.flink.ireview.model.network.response.CommentGroupApiResponse;
import com.flink.ireview.model.network.response.SearchGroupApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentGroupLogicService extends BaseService<CommentGroupApiRequest, CommentGroupApiResponse, CommentGroup>  {
    @Override
    public Header<CommentGroupApiResponse> create(Header<CommentGroupApiRequest> request) {
        CommentGroupApiRequest body = request.getData();
        CommentGroup commentGroup = CommentGroup.builder()
                .userAccount(body.getUserAccount())
                //.searchId(body.getSearchId())
                /*.createdAt(body.getCreatedAt())
                .createdBy(body.getCreatedBy())
                .updatedAt(body.getUpdatedAt())
                .updatedBy(body.getUpdatedBy())*/
                .build();
        CommentGroup newSearchGroup = baseRepository.save(commentGroup);

        return Header.OK(response(newSearchGroup));
    }

    @Override
    public Header<CommentGroupApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(commentGroup -> response(commentGroup))
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터 발생"));
    }

    @Override
    public Header<CommentGroupApiResponse> update(Header<CommentGroupApiRequest> request) {
        CommentGroupApiRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(entitySearchGroup->{
                    entitySearchGroup.setUserAccount(body.getUserAccount())
                            .setStatus(body.getStatus())
                            .setUserAccount(body.getUserAccount())
                            //.setSearchId(body.getSearchId())
                            //.setTotalSearch(body.getTotalSearch())
                            /*.setCreatedAt(body.getCreatedAt())
                            .setCreatedBy(body.getCreatedBy())
                            .setUpdatedAt(body.getUpdatedAt())
                            .setUpdatedBy(body.getUpdatedBy())*/
                    ;

                    return entitySearchGroup;
                })
                .map(newEntityCommentGroup->baseRepository.save(newEntityCommentGroup))
                .map(commentGroup -> response(commentGroup))
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터 없음"));

    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(commentGroup -> {
                    baseRepository.delete(commentGroup);
                    return Header.OK();
                })
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<List<CommentGroupApiResponse>> search(Pageable pageable) {
        Page<CommentGroup> commentGroups = baseRepository.findAll(pageable);
        List<CommentGroupApiResponse> commentGroupApiResponseList = commentGroups.stream()
                .map(commentGroup -> response(commentGroup))
                .collect(Collectors.toList());

        return Header.OK(commentGroupApiResponseList);    }

    private CommentGroupApiResponse response(CommentGroup commentGroup){

        CommentGroupApiResponse body = CommentGroupApiResponse.builder()
                .status(commentGroup.getStatus())
                .userAccount(commentGroup.getUserAccount())
                //.searchId(searchGroup.getSearchId())
                //.totalSearch(searchGroup.getTotalSearch())
                /*         .createdAt(searchGroup.getCreatedAt())
                         .createdBy(searchGroup.getCreatedBy())
                         .updatedAt(searchGroup.getUpdatedAt())
                         .updatedBy(searchGroup.getUpdatedBy())*/
                .build();

        return body;
    }

}
