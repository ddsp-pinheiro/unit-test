package com.basiccrud;

import com.basiccrud.entity.BasicEntity;
import com.basiccrud.repository.BasicRepository;
import com.basiccrud.service.BasicService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles
public class BasicServiceTest {

    @Mock
   private BasicRepository mockBasicRepository;

   @InjectMocks
   private BasicService basicService;

    @Test
    void ShouldReturnId(){
        //Arrange
        var mockBasicEntity =  new BasicEntity(
                1L,
                "Dani"
        );
        Mockito.when(mockBasicRepository.findById(anyLong())).thenReturn(Optional.of(mockBasicEntity));

        //Act
        BasicEntity basicEntity = basicService.getById(1L);

        //Assert
        assertNotNull(basicEntity);
        assertEquals(basicEntity.getId(),1L);
        assertEquals(basicEntity.getName(),"Dani");
        assertEquals(basicEntity.getId(), mockBasicEntity.getId());
        assertEquals(basicEntity.getName(), mockBasicEntity.getName());
    }
}
