package com.basiccrud.controller;

import com.basiccrud.entity.BasicEntity;
import com.basiccrud.mapper.BasicMapper;
import com.basiccrud.request.BasicRequest;
import com.basiccrud.response.BasicResponse;
import com.basiccrud.service.BasicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/unittest")
public class BasicController {
    private final BasicMapper basicMapper;
    private final BasicService basicService;

    private static final String ID = "x-id";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BasicResponse post(@Valid @RequestBody BasicRequest basicRequest,
                                @RequestHeader(ID) Long id){
        BasicEntity entity = basicService.save(basicMapper.toEntity(basicRequest,
                id));
        return basicMapper.toResponse(entity);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path="{/id}", method = RequestMethod.GET)
    public BasicResponse getId(@PathVariable Long id){
        BasicEntity entity = basicService.getById(id);
        return basicMapper.toResponse(entity);
    }

    @GetMapping("name/{name}")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path="{/name}", method = RequestMethod.GET)
    public BasicResponse getName(@PathVariable String name){
        BasicEntity entity = basicService.getByName(name);
        return basicMapper.toResponse(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteId(@PathVariable Long id){
        basicService.deleteById(id);
    }

}
