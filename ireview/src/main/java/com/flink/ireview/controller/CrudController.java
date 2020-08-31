package com.flink.ireview.controller;

import com.flink.ireview.ifs.CrudInterface;
import com.flink.ireview.model.network.Header;
import com.flink.ireview.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
public abstract class CrudController<Req,Res,Entity> implements CrudInterface<Req,Res> {

    @Autowired(required = false)
    BaseService<Req,Res,Entity> baseService;

    //여기서 search는 검색을 위한 서치가 아닌,
    @GetMapping("")
    public Header<List<Res>> Search(@PageableDefault(sort="id",direction = Sort.Direction.ASC,size=15)Pageable pageable){
        return baseService.search(pageable);
    }

    @Override
    public Header<List<Res>> search(Pageable pageable) {
        return baseService.search(pageable);
    }

    @Override
    @PostMapping("")
    public Header<Res> create(@RequestBody Header<Req> request) {

        return baseService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<Res> read(@PathVariable Long id) {
        return baseService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<Res> update(@RequestBody Header<Req> request) {
        return baseService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return baseService.delete(id);
    }

}
