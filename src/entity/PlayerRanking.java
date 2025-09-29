package entity;


public class PlayerRanking {
    private int rank;
    private String name;
    private int totalScore;
    private String winAmount;

    public PlayerRanking(int rank, String name, int totalScore, String winAmount) {
        this.rank = rank;
        this.name = name;
        this.totalScore = totalScore;
        this.winAmount = winAmount;
    }

    public int getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public String getWinAmount() {
        return winAmount;
    }

    
}

