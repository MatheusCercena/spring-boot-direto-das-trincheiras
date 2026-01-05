package academy.devdojo.mapper;

import academy.devdojo.domain.Producer;
import academy.devdojo.request.ProducerPostRequestBody;
import academy.devdojo.request.ProducerPutRequestBody;
import academy.devdojo.response.ProducerResponseBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProducerMapper {

    @Mapping(target = "id", expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(1, 1_000_000))")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    Producer toProducer(ProducerPostRequestBody producerPostRequestBody);

    Producer toProducer(ProducerPutRequestBody producerRequestBody);

    ProducerResponseBody toResponseBody(Producer producer);

    List<ProducerResponseBody> toResponseBodyList(List<Producer> producers);

}
