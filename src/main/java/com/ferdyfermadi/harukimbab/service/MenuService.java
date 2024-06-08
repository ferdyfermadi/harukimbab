package com.ferdyfermadi.harukimbab.service;

import com.ferdyfermadi.harukimbab.model.dto.request.CreateMenuRequest;
import com.ferdyfermadi.harukimbab.model.dto.request.PagingRequest;
import com.ferdyfermadi.harukimbab.model.dto.request.SearchMenuRequest;
import com.ferdyfermadi.harukimbab.model.dto.request.UpdateMenuRequest;
import com.ferdyfermadi.harukimbab.model.entity.Menu;
import org.springframework.data.domain.Page;

public interface MenuService {
    Menu create(CreateMenuRequest menuRequest);
    Menu getById(String id);
    Page<Menu> getAll(PagingRequest pagingRequest, SearchMenuRequest menuRequest);
    Menu update(UpdateMenuRequest menuRequest);
    void deletedById(String id);
}
