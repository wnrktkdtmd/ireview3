package com.flink.ireview.service;

import com.flink.ireview.model.entity.Comment;
import com.flink.ireview.model.entity.SearchGroup;
import com.flink.ireview.model.network.Header;
import com.flink.ireview.model.network.request.CommentApiRequest;
import com.flink.ireview.model.network.request.SearchGroupApiRequest;
import com.flink.ireview.model.network.response.CommentApiResponse;
import com.flink.ireview.model.network.response.SearchGroupApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchGroupLogicService extends BaseService<SearchGroupApiRequest, SearchGroupApiResponse, SearchGroup> {


    @Override
    public Header<SearchGroupApiResponse> create(Header<SearchGroupApiRequest> request) {
        SearchGroupApiRequest body = request.getData();
        SearchGroup searchGroup = SearchGroup.builder()
                .userAccount(body.getUserAccount())
                //.searchId(body.getSearchId())
                .totalSearch(body.getTotalSearch())
                /*.createdAt(body.getCreatedAt())
                .createdBy(body.getCreatedBy())
                .updatedAt(body.getUpdatedAt())
                .updatedBy(body.getUpdatedBy())*/
                .build();
        SearchGroup newSearchGroup = baseRepository.save(searchGroup);

        return Header.OK(response(newSearchGroup));
    }

    @Override
    public Header<SearchGroupApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(searchGroup -> response(searchGroup))
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터 발생"));
    }

    @Override
    public Header<SearchGroupApiResponse> update(Header<SearchGroupApiRequest> request) {
        SearchGroupApiRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(entitySearchGroup->{
                    entitySearchGroup.setUserAccount(body.getUserAccount())
                            //.setSearchId(body.getSearchId())
                            .setTotalSearch(body.getTotalSearch())
                            /*.setCreatedAt(body.getCreatedAt())
                            .setCreatedBy(body.getCreatedBy())
                            .setUpdatedAt(body.getUpdatedAt())
                            .setUpdatedBy(body.getUpdatedBy())*/
                    ;

                return entitySearchGroup;
                })
                .map(newEntitySearchGroup->baseRepository.save(newEntitySearchGroup))
                .map(searchGroup -> response(searchGroup))
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터 없음"));

    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(searchGroup -> {
                    baseRepository.delete(searchGroup);
                    return Header.OK();
                })
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<List<SearchGroupApiResponse>> search(Pageable pageable) {
        Page<SearchGroup> searchGroups = baseRepository.findAll(pageable);
        List<SearchGroupApiResponse> searchGroupApiResponseList = searchGroups.stream()
                .map(searchGroup -> response(searchGroup))
                .collect(Collectors.toList());

        return Header.OK(searchGroupApiResponseList);
    }

    private SearchGroupApiResponse response(SearchGroup searchGroup){

        SearchGroupApiResponse body = SearchGroupApiResponse.builder()
                .userAccount(searchGroup.getUserAccount())
                //.searchId(searchGroup.getSearchId())
                .totalSearch(searchGroup.getTotalSearch())
       /*         .createdAt(searchGroup.getCreatedAt())
                .createdBy(searchGroup.getCreatedBy())
                .updatedAt(searchGroup.getUpdatedAt())
                .updatedBy(searchGroup.getUpdatedBy())*/
                .build();

        return body;
    }
}
