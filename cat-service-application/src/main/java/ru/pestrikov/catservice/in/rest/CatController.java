package ru.pestrikov.catservice.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.pestrikov.catservice.domain.in.application.CatService;
import ru.pestrikov.catservice.exception.Response;
import ru.pestrikov.catservice.mapper.EntityMapper;
import ru.pestrikov.catservice.resource.domain.CatRequest;
import ru.pestrikov.catservice.resource.domain.CatResponse;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CatController {
    private final EntityMapper entityMapper;
    private final CatService catService;

    @GetMapping("/api/v1/cats/{id}")
    @PostAuthorize("returnObject.body.ownerName == authentication.principal.owner.name")
    public ResponseEntity<CatResponse> getCat(@PathVariable("id") UUID id) {
        var cat = catService.findById(id);
        return ResponseEntity.ok(entityMapper.toCatResponse(cat));
    }

    @PostMapping("/api/v1/cats")
    public ResponseEntity<CatResponse> createCat(@RequestBody CatRequest catRequest, UriComponentsBuilder uriComponentsBuilder) {
        var cat = catService.create(entityMapper.fromCatRequest(catRequest));
        var location = uriComponentsBuilder.path("/api/v1/cats/{id}")
                .buildAndExpand(cat.getId())
                .toUri();
        return ResponseEntity.created(location).body(entityMapper.toCatResponse(cat));
    }

    @PatchMapping("/api/v1/cats/{id}")
    @PostAuthorize("returnObject.body.ownerName == authentication.principal.owner.name")
    public ResponseEntity<CatResponse> updateCat(@PathVariable("id") UUID id, @RequestBody CatRequest catRequest) {
        var cat = catService.update(id, entityMapper.fromCatRequest(catRequest));
        return ResponseEntity.ok(entityMapper.toCatResponse(cat));
    }

    @DeleteMapping("/api/v1/cats/{id}")
    @PostAuthorize("returnObject.body.ownerName == authentication.principal.owner.name")
    public ResponseEntity<CatResponse> deleteCat(@PathVariable("id") UUID  id) {
        var cat = catService.deletedById(id);
        return ResponseEntity.ok(entityMapper.toCatResponse(cat));
    }

    @PatchMapping("/api/v1/cats/{catId}/make_friendship/{friendId}")
    public ResponseEntity<Response> makeFriendship(@PathVariable("catId") UUID catId, @PathVariable("friendId") UUID friendId) {
        catService.makeFriendship(catId, friendId);
        return ResponseEntity.ok(new Response("Friendship established successfully"));
    }

    @PatchMapping("/api/v1/cats/{catId}/remove_friendship/{friendId}")
    public ResponseEntity<Response> removeFriendship(@PathVariable("catId") UUID catId, @PathVariable("friendId") UUID friendId) {
        catService.removeFriendship(catId, friendId);
        return ResponseEntity.ok(new Response("Friendship removed successfully"));
    }

    @GetMapping("/api/v1/cats/get_stray_cats")
    public ResponseEntity<List<CatResponse>> getStrayCats() {
        var cats = catService.getStrayCats();

        return ResponseEntity.ok(cats.stream()
                .map(entityMapper::toCatResponse)
                .collect(Collectors.toList()));
    }
}

