package philharmonic.model.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ConcertSessionRequestDto {
    @NotNull
    private String concertTime;
    @NotNull
    @Min(1)
    private Long concertId;
    @NotNull
    @Min(1)
    private Long stageId;

    public String getConcertTime() {
        return concertTime;
    }

    public void setConcertTime(String concertTime) {
        this.concertTime = concertTime;
    }

    public Long getConcertId() {
        return concertId;
    }

    public void setConcertId(Long concertId) {
        this.concertId = concertId;
    }

    public Long getStageId() {
        return stageId;
    }

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }
}
