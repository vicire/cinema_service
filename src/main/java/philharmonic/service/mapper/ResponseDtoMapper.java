package philharmonic.service.mapper;

public interface ResponseDtoMapper<T, V> {
    T toDto(V entity);
}
