package entity;

import java.time.LocalDateTime;

public class MatchHistory {
    private int matchId;          // mã trận
    private String opponentName;  // tên đối thủ
    private String result;        // WIN / LOSE / DRAW
    private int scoreChange;      // điểm thay đổi (+/- sau trận)
    private LocalDateTime time;   // thời gian diễn ra

    public MatchHistory(int matchId, String opponentName, String result, int scoreChange, LocalDateTime time) {
        this.matchId = matchId;
        this.opponentName = opponentName;
        this.result = result;
        this.scoreChange = scoreChange;
        this.time = time;
    }


    public int getMatchId() {
        return matchId;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public String getResult() {
        return result;
    }

    public int getScoreChange() {
        return scoreChange;
    }

    public LocalDateTime getTime() {
        return time;
    }

    
}