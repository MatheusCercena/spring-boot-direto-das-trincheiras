package academy.devdojo.repository;

import academy.devdojo.domain.Anime;
import academy.devdojo.domain.Anime;
import academy.devdojo.domain.Producer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AnimeRepositoryTest {
    @InjectMocks
    private AnimeRepository repository;

    @Mock
    private AnimeData animeData;
    private final ArrayList<Anime> animeList = new ArrayList<>();

    @BeforeEach
    void init() {
        Anime dbz = new Anime(1L, "DBZ", LocalDateTime.now());
        Anime pokemon = new Anime(2L, "Pokemon", LocalDateTime.now());
        Anime pokemon2 = new Anime(3L, "Pokemon", LocalDateTime.now());
        Anime onePiece = new Anime(4L, "One Piece", LocalDateTime.now());
        Anime naruto = new Anime(5L, "Naruto", LocalDateTime.now());
        animeList.addAll(List.of(dbz, pokemon, pokemon2, onePiece, naruto));
        BDDMockito.when(animeData.getAnimeList()).thenReturn(animeList);
    }

    @Test
    @DisplayName("list returns a list with all animes")
    void list_ReturnListWithAllProducers_WhenSuccessful() {
        List<Anime> list = repository.list();
        Assertions.assertThat(list).isNotNull().hasSameElementsAs(animeList);
    }

    @Test
    @DisplayName("findById returns a anime with given Id")
    void findById_ReturnAnimeWithGivenId_WhenSuccessful() {
        Anime expectedAnime = animeList.getFirst();
        Optional<Anime> anime = repository.findById(expectedAnime.getId());
        Assertions.assertThat(anime).isPresent().contains(expectedAnime);
    }

    @Test
    @DisplayName("findByName returns a anime with given name")
    void findByName_ReturnAnime_WhenSuccessful() {
        Anime expectedAnime = animeList.getFirst();
        Optional<Anime> anime = repository.findByName(expectedAnime.getName());
        Assertions.assertThat(anime).isPresent().contains(expectedAnime);
    }

    @Test
    @DisplayName("findByName returns list with all animeList with given name")
    void filterByName_ReturnAllFoundAnimesInList_WhenNameIsFound() {
        String name = "PokemonCompany";
        List<Anime> expectedAnimes = animeList.stream().filter(anime -> anime.getName().equals(name)).toList();
        List<Anime> animeListFound = repository.filterByName(name);
        Assertions.assertThat(animeListFound).containsAll(expectedAnimes).hasSize(expectedAnimes.size());
    }

    @Test
    @DisplayName("findByName returns an empty list when no anime with given name is found")
    void filterByName_ReturnEmptyList_WhenNameIsNotFound() {
        String name = "NameNotExistent";
        List<Anime> expectedAnimes = animeList.stream().filter(anime -> anime.getName().equals(name)).toList();
        List<Anime> animeListFound = repository.filterByName(name);
        Assertions.assertThat(animeListFound).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("save create a anime in repository")
    void save_CreateAnime_WhenSuccessful() {
        Anime animeToBeSaved = Anime.builder().id(99L).name("TestName").createdAt(LocalDateTime.now()).build();
        Anime animeSaved = repository.save(animeToBeSaved);
        Assertions.assertThat(animeSaved).isEqualTo(animeToBeSaved).hasNoNullFieldsOrProperties();
        Assertions.assertThat(animeList).contains(animeSaved);
    }

    @Test
    @DisplayName("delete removes a anime in repository")
    void delete_RemoveAnime_WhenSuccessful() {
        Anime animeToBeRemoved = animeList.getFirst();
        repository.delete(animeToBeRemoved);
        Assertions.assertThat(animeList).doesNotContain(animeToBeRemoved);
    }

    @Test
    @DisplayName("update removes a anime in repository")
    void update_AlterAnimeNameAn_WhenSuccessful() {
        Anime animeToBeUpdated = animeList.getFirst();
        repository.update(animeToBeUpdated);
        Assertions.assertThat(animeList).contains(animeToBeUpdated);
        Optional<Anime> animeFound = repository.findById(animeToBeUpdated.getId());
        Assertions.assertThat(animeFound).isPresent();
        Assertions.assertThat(animeFound.get().getName()).isEqualTo(animeToBeUpdated.getName());

    }
}