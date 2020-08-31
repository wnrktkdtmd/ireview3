package com.flink.ireview.service;

import com.flink.ireview.model.entity.Search;
import com.flink.ireview.model.network.Header;
import com.flink.ireview.model.network.request.SearchApiRequest;
import com.flink.ireview.model.network.response.SearchApiResponse;
import com.flink.ireview.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class SearchLogicService extends BaseService<SearchApiRequest, SearchApiResponse, Search> {

    @Override
    public Header<SearchApiResponse> create(Header<SearchApiRequest> request) {
        SearchApiRequest body = request.getData();
        Search search = Search.builder()
                .name(body.getName())
                .index(body.getIndex())
                .totalSearch(body.getTotalSearch())
                /*.createdAt(body.getCreatedAt())
                .createdBy(body.getCreatedBy())
                .updatedAt(body.getUpdatedAt())
                .updatedBy(body.getUpdatedBy())*/
                .build();

        Search newSearch = baseRepository.save(search);
        return Header.OK(response(newSearch));
    }

    @Override
    public Header<SearchApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(this::response)
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<SearchApiResponse> update(Header<SearchApiRequest> request) {
        SearchApiRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(search -> {
                    search.setName(body.getName())
                            .setIndex(body.getIndex())
                            .setTotalSearch(body.getTotalSearch())
                            /*.setCreatedAt(body.getCreatedAt())
                            .setCreatedBy(body.getCreatedBy())
                            .setUpdatedAt(body.getUpdatedAt())
                            .setUpdatedBy(body.getUpdatedBy())*/
                    ;

                    return search;
                })
                .map(newEntitySearch->baseRepository.save(newEntitySearch))
                .map(search->response(search))
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(search -> {
                    baseRepository.delete(search);
                    return Header.OK();
                })
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }


    @Autowired
    private SearchRepository searchRepository;
    @Override
    public Header<List<SearchApiResponse>> search(Pageable pageable) {
        Page<Search> searches = baseRepository.findAll(pageable);
        List<SearchApiResponse> searchApiResponsesList = searches.stream()
                .map(search->response(search))
                .collect(Collectors.toList());
        //List<UserApiResponse>
        //Header<List<UserApiResponse>>
        return Header.OK(searchApiResponsesList);
    }

    private SearchApiResponse response (Search search){
        SearchApiResponse body = SearchApiResponse.builder()
                .id(search.getId())
                .name(search.getName())
                .index(search.getIndex())
                .totalSearch(search.getTotalSearch())
                /*.createdAt(search.getCreatedAt())
                .createdBy(search.getCreatedBy())
                .updatedAt(search.getUpdatedAt())
                .updatedBy(search.getUpdatedBy())*/
                .build();

        return body;
    }

}
