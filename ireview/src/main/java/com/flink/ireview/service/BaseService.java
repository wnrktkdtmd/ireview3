package com.flink.ireview.service;

import com.flink.ireview.ifs.CrudInterface;
import com.flink.ireview.ifs.CrudInterface;
import com.flink.ireview.model.network.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public abstract class BaseService<Req,Res,Entity> implements CrudInterface<Req,Res> {
    @Autowired(required = false)
    protected JpaRepository<Entity,Long> baseRepository;


}
