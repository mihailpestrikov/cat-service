package ru.pestrikov.catservice.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.pestrikov.catservice.domain.in.application.OwnerService;
import ru.pestrikov.catservice.exception.Response;
import ru.pestrikov.catservice.mapper.EntityMapper;
import ru.pestrikov.catservice.resource.domain.CatResponse;
import ru.pestrikov.catservice.resource.domain.OwnerRequest;
import ru.pestrikov.catservice.resource.domain.OwnerResponse;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OwnerController {
    private final EntityMapper entityMapper;
    private final OwnerService ownerService;

    @GetMapping("/api/v1/owners/{id}")
    @PreAuthorize("authentication.principal.owner.id == #id")
    public ResponseEntity<OwnerResponse> getOwner(@PathVariable("id") UUID id) {
        var owner = ownerService.findById(id);
        return ResponseEntity.ok(entityMapper.toOwnerResponse(owner));
    }

    @PostMapping("/api/v1/owners")
    public ResponseEntity<OwnerResponse> createOwner(@RequestBody OwnerRequest ownerRequest, UriComponentsBuilder uriComponentsBuilder) {
        var owner = ownerService.create(entityMapper.fromOwnerRequest(ownerRequest));
        var location = uriComponentsBuilder.path("/api/v1/owners/{id}")
                .buildAndExpand(owner.getId())
                .toUri();
        return ResponseEntity.created(location).body(entityMapper.toOwnerResponse(owner));
    }

    @PatchMapping("/api/v1/owners/{id}")
    @PreAuthorize("authentication.principal.owner.id == #id")
    public ResponseEntity<OwnerResponse> updateOwner(@PathVariable("id") UUID id, @RequestBody OwnerRequest ownerRequest) {
        var owner = ownerService.update(id, entityMapper.fromOwnerRequest(ownerRequest));
        return ResponseEntity.ok(entityMapper.toOwnerResponse(owner));
    }

    @DeleteMapping("/api/v1/owners/{id}")
    @PreAuthorize("authentication.principal.owner.id == #id")
    public ResponseEntity<Response> deleteOwner(@PathVariable("id") UUID id) {
        ownerService.deletedById(id);
        return ResponseEntity.ok(new Response("Owner was deleted successfully"));
    }

    @PatchMapping("/api/v1/owners/{ownerId}/make_ownership/{catId}")
    @PreAuthorize("authentication.principal.owner.id == #ownerId")
    public ResponseEntity<Response> makeOwnership(@PathVariable("ownerId") UUID ownerId, @PathVariable("catId") UUID catId, UriComponentsBuilder uriComponentsBuilder) {
        ownerService.makeOwnership(ownerId, catId);
        var location = uriComponentsBuilder.path("/api/v1/owners/{ownerId}/ownership/{catId}")
                .buildAndExpand(ownerId, catId)
                .toUri();
        return ResponseEntity.created(location).body(new Response("Ownership established successfully"));
    }

    @PatchMapping("/api/v1/owners/{ownerId}/remove_ownership/{catId}")
    @PreAuthorize("authentication.principal.owner.id == #ownerId")
    public ResponseEntity<Response> removeOwnership(@PathVariable("ownerId") UUID ownerId, @PathVariable("catId") UUID catId) {
        ownerService.removeOwnership(ownerId, catId);
        return ResponseEntity.ok(new Response("Ownership removed successfully"));
    }

    @GetMapping("/api/v1/owners/{id}/get_cats")
    @PreAuthorize("authentication.principal.owner.id == #id")
    public ResponseEntity<List<CatResponse>> getCats(@PathVariable("id") UUID id) {
        var owner = ownerService.findById(id);
        var cats = owner.getCats();

        return ResponseEntity.ok(cats.stream()
                .map(entityMapper::toCatResponse)
                .collect(Collectors.toList()));
    }
}
