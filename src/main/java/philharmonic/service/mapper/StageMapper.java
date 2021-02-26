package philharmonic.service.mapper;

import org.springframework.stereotype.Component;
import philharmonic.model.Stage;
import philharmonic.model.dto.request.StageRequestDto;
import philharmonic.model.dto.response.StageResponseDto;

@Component
public class StageMapper implements RequestDtoMapper<Stage, StageRequestDto>,
        ResponseDtoMapper<StageResponseDto, Stage> {
    @Override
    public StageResponseDto toDto(Stage stage) {
        StageResponseDto stageResponseDto = new StageResponseDto();
        stageResponseDto.setStageId(stage.getId());
        stageResponseDto.setStageCapacity(stage.getCapacity());
        stageResponseDto.setStageDescription(stage.getDescription());
        return stageResponseDto;
    }

    @Override
    public Stage toEntity(StageRequestDto stageRequestDto) {
        Stage stage = new Stage();
        stage.setDescription(stageRequestDto.getDescription());
        stage.setCapacity(stageRequestDto.getCapacity());
        return stage;
    }
}
