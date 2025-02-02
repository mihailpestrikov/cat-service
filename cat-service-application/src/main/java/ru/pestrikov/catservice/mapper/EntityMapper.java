package ru.pestrikov.catservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.pestrikov.catservice.domain.model.Cat;
import ru.pestrikov.catservice.domain.model.Owner;
import ru.pestrikov.catservice.resource.domain.CatRequest;
import ru.pestrikov.catservice.resource.domain.CatResponse;
import ru.pestrikov.catservice.resource.domain.OwnerRequest;
import ru.pestrikov.catservice.resource.domain.OwnerResponse;

@Mapper(componentModel = "spring")
public interface EntityMapper {
    @Mapping(target = "ownerName", source = "cat.owner.name")
    CatResponse toCatResponse(Cat cat);
    Cat fromCatResponse(CatResponse catResponse);

    CatRequest toCatRequest(Cat cat);
    Cat fromCatRequest(CatRequest catRequest);

    OwnerRequest toOwnerRequest(Owner owner);
    Owner fromOwnerRequest(OwnerRequest ownerRequest);

    OwnerResponse toOwnerResponse(Owner owner);
    Owner fromOwnerResponse(OwnerResponse ownerResponse);
}
