package com.basiccrud;

import com.basiccrud.controller.BasicController;
import com.basiccrud.mapper.BasicMapper;
import com.basiccrud.service.BasicService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles
public class BasicControllerTest {
    @InjectMocks
    private BasicController basicController;
    @Mock
    private BasicMapper basicMapper;
    @Mock
    private BasicService basicService;

    @Test
    void whenFindByIdThenReturnSuccess(){

    }
}
