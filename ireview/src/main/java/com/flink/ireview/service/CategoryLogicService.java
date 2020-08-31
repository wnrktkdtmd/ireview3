package com.flink.ireview.service;

import com.flink.ireview.model.entity.Category;
import com.flink.ireview.model.network.Header;
import com.flink.ireview.model.network.request.CategoryApiRequest;
import com.flink.ireview.model.network.response.CategoryApiResponse;
import com.flink.ireview.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryLogicService extends BaseService <CategoryApiRequest, CategoryApiResponse, Category> {

    @Override
    public Header<CategoryApiResponse> create(Header<CategoryApiRequest> request) {
        CategoryApiRequest body = request.getData();
                    Category category = Category.builder()
                            .type(body.getType())
                            .title(body.getTitle())
                            .totalItem(body.getTotalItem())
                            .rangkingNumber(body.getRangkingNumber())
                            .totalBoard(body.getTotalBoard())
                            .sumnailImage(body.getSumnailImage())
                            .categoryImage(body.getCategoryImage())
                            .createdAt(body.getCreatedAt())
                            .createdBy(body.getCreatedBy())
                            .updatedAt(body.getUpdatedAt())
                            .updatedBy(body.getUpdatedBy())

                            .build();

                    Category newCategory = baseRepository.save(category);
                    return Header.OK(response(newCategory));
    }

    @Override
    public Header<CategoryApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(category -> response(category))
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<CategoryApiResponse> update(Header<CategoryApiRequest> request) {
        CategoryApiRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(entitiyCategory -> {
                    entitiyCategory.setType(body.getType())
                            .setTitle(body.getTitle())

                            .setTotalItem(body.getTotalItem())
                            .setRangkingNumber(body.getRangkingNumber())
                            .setTotalBoard(body.getTotalBoard())
                            .setSumnailImage(body.getSumnailImage())
                            .setCategoryImage(body.getCategoryImage())
                            .setCreatedAt(body.getCreatedAt())
                            .setCreatedBy(body.getCreatedBy())
                            .setUpdatedAt(body.getUpdatedAt())
                            .setUpdatedBy(body.getUpdatedBy())
                    ;

                    return entitiyCategory;
                })
                .map(newEntityCategory->baseRepository.save(newEntityCategory))
                .map(category -> response(category))
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터 없음"));

    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(category -> {
                    baseRepository.delete(category);
                    return Header.OK();
                })
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Header<List<CategoryApiResponse>> search(Pageable pageable) {
       Page<Category> categories = baseRepository.findAll(pageable);
       List<CategoryApiResponse> categoryApiResponseList = categories.stream()
               .map(category -> response(category))
               .collect(Collectors.toList());

        return Header.OK(categoryApiResponseList);
    }

    private CategoryApiResponse response(Category category){

        CategoryApiResponse body = CategoryApiResponse.builder()
                .type(category.getType())
                .title(category.getTitle())
                .totalItem(category.getTotalItem())
                .rangkingNumber(category.getRangkingNumber())
                .totalBoard(category.getTotalBoard())
                .sumnailImage(category.getSumnailImage())
                .categoryImage(category.getCategoryImage())
                .createdAt(category.getCreatedAt())
                .createdBy(category.getCreatedBy())
                .updatedAt(category.getUpdatedAt())
                .updatedBy(category.getUpdatedBy())
                .build();

        return body;
    }
}
