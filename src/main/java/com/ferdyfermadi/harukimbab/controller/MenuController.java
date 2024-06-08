package com.ferdyfermadi.harukimbab.controller;

import com.ferdyfermadi.harukimbab.model.constant.APIUrl;
import com.ferdyfermadi.harukimbab.model.constant.ResponseMessage;
import com.ferdyfermadi.harukimbab.model.dto.request.CreateMenuRequest;
import com.ferdyfermadi.harukimbab.model.dto.request.PagingRequest;
import com.ferdyfermadi.harukimbab.model.dto.request.SearchMenuRequest;
import com.ferdyfermadi.harukimbab.model.dto.request.UpdateMenuRequest;
import com.ferdyfermadi.harukimbab.model.dto.response.CommonResponse;
import com.ferdyfermadi.harukimbab.model.dto.response.PagingResponse;
import com.ferdyfermadi.harukimbab.model.entity.Menu;
import com.ferdyfermadi.harukimbab.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.MENU_API)
public class MenuController {

    private final MenuService menuService;
    
    @PostMapping
    public ResponseEntity<CommonResponse<Menu>> createNewMenu(@RequestBody CreateMenuRequest menuRequest) {
        Menu newMenu = menuService.create(menuRequest);

        CommonResponse<Menu> response = CommonResponse.<Menu>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_SAVE_DATA)
                .data(newMenu)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping(APIUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<Menu>> getMenuById(@PathVariable String id) {
        Menu menu = menuService.getById(id);

        CommonResponse<Menu> response = CommonResponse.<Menu>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(menu)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Menu>>> getAllMenu(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction
    ){
        PagingRequest pagingRequest = PagingRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .build();

        SearchMenuRequest menuRequest = SearchMenuRequest.builder().build();
        Page<Menu> allMenu = menuService.getAll(pagingRequest, menuRequest);

        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(allMenu.getTotalPages())
                .totalElements(allMenu.getTotalElements())
                .page(allMenu.getPageable().getPageNumber() + 1)
                .size(allMenu.getPageable().getPageSize())
                .hasNext(allMenu.hasNext())
                .hasPrevious(allMenu.hasPrevious())
                .build();

        CommonResponse<List<Menu>> response = CommonResponse.<List<Menu>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(allMenu.getContent())
                .paging(pagingResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Menu>> updateMenu(@RequestBody UpdateMenuRequest menuRequest){

        Menu menu = menuService.update(menuRequest);

        CommonResponse<Menu> response = CommonResponse.<Menu>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_UPDATE_DATA)
                .data(menu)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = APIUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<Menu>> deletedMenuById(@PathVariable String id){
        menuService.deletedById(id);

        CommonResponse<Menu> response = CommonResponse.<Menu>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_DELETE_DATA + id)
                .build();

        return ResponseEntity.ok(response);
    }
}
