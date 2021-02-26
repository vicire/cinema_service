package philharmonic.service.mapper;

public interface RequestDtoMapper<T, V> {
    T toEntity(V requestDto);
}
