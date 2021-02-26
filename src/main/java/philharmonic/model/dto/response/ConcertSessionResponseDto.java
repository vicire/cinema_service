package philharmonic.model.dto.response;

public class ConcertSessionResponseDto {
    private Long concertSessionId;
    private String concertTitle;
    private String stageDescription;
    private String concertSessionTime;

    public Long getConcertSessionId() {
        return concertSessionId;
    }

    public void setConcertSessionId(Long concertSessionId) {
        this.concertSessionId = concertSessionId;
    }

    public String getConcertTitle() {
        return concertTitle;
    }

    public void setConcertTitle(String concertTitle) {
        this.concertTitle = concertTitle;
    }

    public String getStageDescription() {
        return stageDescription;
    }

    public void setStageDescription(String stageDescription) {
        this.stageDescription = stageDescription;
    }

    public String getConcertSessionTime() {
        return concertSessionTime;
    }

    public void setConcertSessionTime(String concertSessionTime) {
        this.concertSessionTime = concertSessionTime;
    }
}
