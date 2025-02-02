package ru.pestrikov.catservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.pestrikov.catservice.domain.model.Cat;
import ru.pestrikov.catservice.domain.model.resource.CatColor;
import ru.pestrikov.catservice.domain.model.Owner;
import ru.pestrikov.catservice.mapper.EntityMapper;
import ru.pestrikov.catservice.mapper.EntityMapperImpl;
import ru.pestrikov.catservice.resource.domain.CatResponse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

@SpringBootTest(classes = EntityMapperImpl.class)
@ExtendWith(SpringExtension.class)
public class MapperTest {

    @Autowired
    private EntityMapper entityMapper;

    @Test
    public void givenCatToCatResponse_whenMaps_thenCorrect() {
        // given
        Owner owner = new Owner(
                UUID.randomUUID(),
                "testOwner",
                LocalDate.of(2000, 1, 29),
                new ArrayList<>() {});

        Cat cat = new Cat(
                UUID.randomUUID(),
                "leftCat",
                LocalDate.of(2024,1,10),
                CatColor.WHITE,
                owner,
                new ArrayList<>() {});

        // when
        var catResponse = entityMapper.toCatResponse(cat);

        // then
        Assertions.assertEquals(catResponse.getName(), cat.getName());
        Assertions.assertEquals(catResponse.getId(), cat.getId());
        Assertions.assertEquals(catResponse.getBirthDate(), cat.getBirthDate());
        Assertions.assertEquals(catResponse.getColor(), cat.getColor());
        Assertions.assertEquals(catResponse.getOwnerName(), cat.getOwner().getName());
    }

    @Test
    public void givenCatResponseToCat_whenMaps_thenCorrect() {
        // given
        CatResponse catResponse = new CatResponse();
        catResponse.setId(UUID.randomUUID());
        catResponse.setName("testCat");
        catResponse.setBirthDate(LocalDate.of(2000, 1, 29));
        catResponse.setColor(CatColor.BLACK);

        // when
        var cat = entityMapper.fromCatResponse(catResponse);

        // then
        Assertions.assertEquals(cat.getName(), catResponse.getName());
        Assertions.assertEquals(cat.getId(), catResponse.getId());
        Assertions.assertEquals(cat.getBirthDate(), catResponse.getBirthDate());
        Assertions.assertEquals(cat.getColor(), catResponse.getColor());
    }
}
