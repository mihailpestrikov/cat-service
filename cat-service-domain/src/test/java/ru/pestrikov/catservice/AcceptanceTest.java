package ru.pestrikov.catservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.pestrikov.catservice.domain.CatServiceImpl;
import ru.pestrikov.catservice.domain.OwnerServiceImpl;
import ru.pestrikov.catservice.domain.model.Cat;
import ru.pestrikov.catservice.domain.model.resource.CatColor;
import ru.pestrikov.catservice.domain.model.Owner;
import ru.pestrikov.catservice.domain.out.infrastructure.CatRepository;
import ru.pestrikov.catservice.domain.out.infrastructure.OwnerRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AcceptanceTest {
    private OwnerRepository ownerRepository;
    private CatRepository catRepository;
    private CatServiceImpl testedCatService;
    private OwnerServiceImpl testedOwnerService;

    @BeforeEach
    void setUp() {
        ownerRepository = mock(OwnerRepository.class);
        catRepository = mock(CatRepository.class);

        testedOwnerService = new OwnerServiceImpl(ownerRepository, catRepository);
        testedCatService = new CatServiceImpl(catRepository);
    }

    @Test
    void shouldCreateOwner_thenSaveIt() {
        // given
        Owner owner = new Owner(
                UUID.randomUUID(),
                "testOwner",
                LocalDate.of(2000, 1, 29),
                new ArrayList<>() {});

        // when
        testedOwnerService.create(owner);

        // then
        verify(ownerRepository).save(any(Owner.class));
    }

    @Test
    void shouldCreateCat_thenSaveIt() {
        // given
        Owner owner = new Owner(
                UUID.randomUUID(),
                "testOwner",
                LocalDate.of(2000, 1, 29),
                new ArrayList<>() {});

        Cat cat = new Cat(
                UUID.randomUUID(),
                "testCat",
                LocalDate.of(2024,1,10),
                CatColor.WHITE,
                owner,
                new ArrayList<>() {});

        // when
        testedCatService.create(cat);

        // then
        verify(catRepository).save(any(Cat.class));
    }

    @Test
    void shouldCreateTwoCats_thenMakeThemFriends() {
        // given
        Owner owner = new Owner(
                UUID.randomUUID(),
                "testOwner",
                LocalDate.of(2000, 1, 29),
                new ArrayList<>() {});

        Cat leftCat = new Cat(
                UUID.randomUUID(),
                "leftCat",
                LocalDate.of(2024,1,10),
                CatColor.WHITE,
                owner,
                new ArrayList<>() {});

        Cat rightCat = new Cat(
                UUID.randomUUID(),
                "rightCat",
                LocalDate.of(2024,1,10),
                CatColor.WHITE,
                owner,
                new ArrayList<>() {});

        when(catRepository.findById(leftCat.getId())).thenReturn(Optional.of(leftCat));
        when(catRepository.findById(rightCat.getId())).thenReturn(Optional.of(rightCat));

        // when
        testedCatService.makeFriendship(leftCat.getId(), rightCat.getId());

        // then
        verify(catRepository, times(2)).save(any(Cat.class));
        Assertions.assertEquals(1, leftCat.getFriends().size());
        Assertions.assertEquals(1, rightCat.getFriends().size());
        Assertions.assertEquals(rightCat, leftCat.getFriends().get(0));
        Assertions.assertEquals(leftCat, rightCat.getFriends().get(0));
    }

    @Test
    void shouldCreateCat_thenOwnerGetsThatCat() {
        // given
        Owner owner = new Owner(
                UUID.randomUUID(),
                "testOwner",
                LocalDate.of(2000, 1, 29),
                new ArrayList<>() {});

        Cat cat = new Cat(
                UUID.randomUUID(),
                "testCat",
                LocalDate.of(2024,1,10),
                CatColor.WHITE,
                owner,
                new ArrayList<>() {});

        when(catRepository.findById(cat.getId())).thenReturn(Optional.of(cat));
        when(ownerRepository.findById(owner.getId())).thenReturn(Optional.of(owner));

        // when
        testedOwnerService.makeOwnership(owner.getId(), cat.getId());

        // then
        verify(catRepository).save(any(Cat.class));
        verify(ownerRepository).save(any(Owner.class));
        Assertions.assertEquals(1, owner.getCats().size());
        Assertions.assertEquals(owner.getCats().get(0).getId(), cat.getId());
    }

    @Test
    void shouldCreateCatMakeThemFriends_thenRemoveFriendship() {
        // given
        Owner owner = new Owner(
                UUID.randomUUID(),
                "testOwner",
                LocalDate.of(2000, 1, 29),
                new ArrayList<>() {});

        Cat leftCat = new Cat(
                UUID.randomUUID(),
                "leftCat",
                LocalDate.of(2024,1,10),
                CatColor.WHITE,
                owner,
                new ArrayList<>() {});

        Cat rightCat = new Cat(
                UUID.randomUUID(),
                "rightCat",
                LocalDate.of(2024,1,10),
                CatColor.WHITE,
                owner,
                new ArrayList<>() {});

        when(catRepository.findById(leftCat.getId())).thenReturn(Optional.of(leftCat));
        when(catRepository.findById(rightCat.getId())).thenReturn(Optional.of(rightCat));
        when(ownerRepository.findById(owner.getId())).thenReturn(Optional.of(owner));

        // when & then
        testedCatService.makeFriendship(leftCat.getId(), rightCat.getId());
        Assertions.assertEquals(leftCat.getFriends().get(0).getId(), rightCat.getId());
        Assertions.assertEquals(rightCat.getFriends().get(0).getId(), leftCat.getId());

        testedCatService.removeFriendship(leftCat.getId(), rightCat.getId());
        Assertions.assertFalse(leftCat.getFriends().contains(rightCat));
        Assertions.assertFalse(rightCat.getFriends().contains(leftCat));
    }
}
