package academy.devdojo.repository;

import academy.devdojo.domain.Anime;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AnimeRepository {
    private final static ArrayList<Anime> animeList = new ArrayList<>();

    static {
        Anime dbz = new Anime(1L, "DBZ", LocalDateTime.now());
        Anime pokemon = new Anime(2L, "Pokemon", LocalDateTime.now());
        Anime pokemon2 = new Anime(3L, "Pokemon", LocalDateTime.now());
        Anime onePiece = new Anime(4L, "One Piece", LocalDateTime.now());
        Anime naruto = new Anime(5L, "Naruto", LocalDateTime.now());
        animeList.addAll(List.of(dbz, pokemon, pokemon2, onePiece, naruto));
    }

    public List<Anime> list() {
        return animeList;
    }

    public Optional<Anime> findById(Long id) {
        return animeList.stream().filter(anime -> anime.getId().equals(id)).findFirst();
    }

    public List<Anime> filterByName(String name) {
        return animeList.stream().filter(anime -> anime.getName().equalsIgnoreCase(name)).toList();
    }
    public Optional<Anime> findByName(String name) {
        return animeList.stream().filter(anime -> anime.getName().equalsIgnoreCase(name)).findFirst();
    }

    public void save(Anime anime) {
        animeList.add(anime);
    }

    public void delete(Anime anime) {
        animeList.remove(anime);
    }

    public void update(Anime anime) {
        delete(anime);
        save(anime);
    }
}