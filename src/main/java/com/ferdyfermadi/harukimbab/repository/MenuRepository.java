package com.ferdyfermadi.harukimbab.repository;

import com.ferdyfermadi.harukimbab.model.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MenuRepository extends JpaRepository<Menu, String>, JpaSpecificationExecutor<Menu> {
}
