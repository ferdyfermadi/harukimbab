package com.ferdyfermadi.harukimbab.service.impl;

import com.ferdyfermadi.harukimbab.model.dto.request.CreateMenuRequest;
import com.ferdyfermadi.harukimbab.model.dto.request.PagingRequest;
import com.ferdyfermadi.harukimbab.model.dto.request.SearchMenuRequest;
import com.ferdyfermadi.harukimbab.model.dto.request.UpdateMenuRequest;
import com.ferdyfermadi.harukimbab.model.entity.Menu;
import com.ferdyfermadi.harukimbab.repository.MenuRepository;
import com.ferdyfermadi.harukimbab.service.MenuService;
import com.ferdyfermadi.harukimbab.specification.MenuSpecification;
import com.ferdyfermadi.harukimbab.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final ValidationUtil validationUtil;

    @Override
    public Menu create(CreateMenuRequest menuRequest) {
        validationUtil.validate(menuRequest);
        Menu newMenu = Menu.builder()
                .name(menuRequest.getName())
                .price(menuRequest.getPrice())
                .build();
        return menuRepository.saveAndFlush(newMenu);
    }

    @Override
    public Menu getById(String id) {
        Optional<Menu> menuById = menuRepository.findById(id);
        if (menuById.isEmpty()) {
            throw new RuntimeException("Warehouse Not Found");
        }
        return menuById.get();
    }

    @Override
    public Page<Menu> getAll(PagingRequest pagingRequest, SearchMenuRequest menuRequest) {
        validationUtil.validate(pagingRequest);

        if (pagingRequest.getPage() <= 0) {
            pagingRequest.setPage(1);
        }

        String validSortBy;
        if ("name".equalsIgnoreCase(pagingRequest.getSortBy()) || "price".equalsIgnoreCase(pagingRequest.getSortBy()) || "stock".equalsIgnoreCase(pagingRequest.getSortBy())) {
            validSortBy = pagingRequest.getSortBy();
        } else {
            validSortBy = "name";
        }

        Sort sort = Sort.by(Sort.Direction.fromString(pagingRequest.getDirection()), validSortBy);

        Pageable pageable = PageRequest.of((pagingRequest.getPage() - 1), pagingRequest.getSize(), sort);

        Specification<Menu> menuSpecification = MenuSpecification.getSpecification(menuRequest);
        return menuRepository.findAll(menuSpecification, pageable);
    }

    @Override
    public Menu update(UpdateMenuRequest menuRequest) {
        validationUtil.validate(menuRequest);
        Menu menu = Menu.builder()
                .id(menuRequest.getId())
                .name(menuRequest.getName())
                .price(menuRequest.getPrice())
                .build();
        return menuRepository.saveAndFlush(menu);
    }

    @Override
    public void deletedById(String id) {
        Menu menu = getById(id);
        menuRepository.delete(menu);
    }
}
