package academy.devdojo.repository;

import academy.devdojo.domain.Anime;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class AnimeData {
    private final ArrayList<Anime> animeList = new ArrayList<>();

    {
        Anime dbz = new Anime(1L, "DBZ", LocalDateTime.now());
        Anime pokemon = new Anime(2L, "Pokemon", LocalDateTime.now());
        Anime pokemon2 = new Anime(3L, "Pokemon", LocalDateTime.now());
        Anime onePiece = new Anime(4L, "One Piece", LocalDateTime.now());
        Anime naruto = new Anime(5L, "Naruto", LocalDateTime.now());
        animeList.addAll(List.of(dbz, pokemon, pokemon2, onePiece, naruto));
    }

}
