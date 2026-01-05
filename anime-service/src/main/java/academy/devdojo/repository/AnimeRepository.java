package academy.devdojo.repository;

import academy.devdojo.domain.Anime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AnimeRepository {
    private final AnimeData animeData;

    public List<Anime> list() {
        return animeData.getAnimeList();
    }

    public Optional<Anime> findById(Long id) {
        return animeData.getAnimeList().stream().filter(anime -> anime.getId().equals(id)).findFirst();
    }

    public List<Anime> filterByName(String name) {
        return animeData.getAnimeList().stream().filter(anime -> anime.getName().equalsIgnoreCase(name)).toList();
    }
    public Optional<Anime> findByName(String name) {
        return animeData.getAnimeList().stream().filter(anime -> anime.getName().equalsIgnoreCase(name)).findFirst();
    }

    public Anime save(Anime anime) {
        animeData.getAnimeList().add(anime);
        return anime;
    }

    public void delete(Anime anime) {
        animeData.getAnimeList().remove(anime);
    }

    public void update(Anime anime) {
        delete(anime);
        save(anime);
    }
}