/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author dbao0
 */
public class DetailMatch {

    private int id;
    private int count;
    private boolean isQuit;
    private String result;
    private int player1Id;
    private int player2Id;
    private int matchId;

    public DetailMatch(int id, int count, boolean isQuit, String result, int player1Id, int player2Id, int matchId) {
        this.id = id;
        this.count = count;
        this.isQuit = isQuit;
        this.result = result;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.matchId = matchId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isIsQuit() {
        return isQuit;
    }

    public void setIsQuit(boolean isQuit) {
        this.isQuit = isQuit;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getPlayer1Id() {
        return player1Id;
    }

    public void setPlayer1Id(int player1Id) {
        this.player1Id = player1Id;
    }

    public int getPlayer2Id() {
        return player2Id;
    }

    public void setPlayer2Id(int player2Id) {
        this.player2Id = player2Id;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }
}
