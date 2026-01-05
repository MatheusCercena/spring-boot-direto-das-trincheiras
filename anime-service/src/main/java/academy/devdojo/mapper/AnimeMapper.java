package academy.devdojo.mapper;

import academy.devdojo.domain.Anime;
import academy.devdojo.domain.Producer;
import academy.devdojo.request.AnimePostRequestBody;
import academy.devdojo.request.AnimePutRequestBody;
import academy.devdojo.response.AnimeResponseBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AnimeMapper {

    @Mapping(target = "id", expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(1, 1_000_000))")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    Anime toAnime(AnimePostRequestBody animePostRequestBody);

    Anime toAnime(AnimePutRequestBody animeRequestBody);

    AnimeResponseBody toResponseBody(Anime anime);

    List<AnimeResponseBody> toResponseBodyList(List<Anime> animes);

}
