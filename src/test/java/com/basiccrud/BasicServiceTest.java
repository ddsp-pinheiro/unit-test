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
import org.springframework.dao.DuplicateKeyException;
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
    void ShouldCreateANewEntity(){
        //Arrange
        var mockBasicEntity =  new BasicEntity(
                1L,
                "Dani"
        );
        Mockito.when(mockBasicRepository.save(any())).thenReturn(mockBasicEntity);

        //Act
        BasicEntity reponse = basicService.save(mockBasicEntity);

        //Assert
        assertNotNull(reponse);
        assertEquals(BasicEntity.class,reponse.getClass());
        assertEquals(mockBasicEntity.getId(),reponse.getId());
        assertEquals(mockBasicEntity.getName(),reponse.getName());
    }
    @Test
    void ShouldNotCreateEntityAreadyExists(){
        var mockBasicEntity =  new BasicEntity(
                1L,
                "Dani"
        );
        Mockito.when(mockBasicRepository.findById(anyLong())).thenReturn(Optional.of(mockBasicEntity));

        //Act
        try {
            basicService.save(mockBasicEntity);
        }catch (Exception ex){
            assertEquals(DuplicateKeyException.class, ex.getClass());
        }

    }
    @Test
    void ShouldReturnById(){
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

    @Test
    void ShouldReturnByName(){
        //Arrange
        var mockBasicEntity =  new BasicEntity(
                1L,
                "Dani"
        );
        Mockito.when(mockBasicRepository.findByName(anyString())).thenReturn(Optional.of(mockBasicEntity));

        //Act
        BasicEntity basicEntity = basicService.getByName("Dani");

        //Assert
        assertNotNull(basicEntity);
        assertEquals(basicEntity.getId(),1L);
        assertEquals(basicEntity.getName(),"Dani");
        assertEquals(basicEntity.getId(), mockBasicEntity.getId());
        assertEquals(basicEntity.getName(), mockBasicEntity.getName());
    }

    @Test
    void ShouldDeleteByI(){
        //Arrange
        Mockito.doNothing().when(mockBasicRepository).deleteById(anyLong());

        //Act
        basicService.deleteById(1L);

        //Assert
        Mockito.verify(mockBasicRepository, Mockito.times(1)).deleteById(anyLong());
    }
}
