package com.example.pta.result;

public class ResultDetailsClass {

    int serialNo;
    String playerName,perKills,winAmount;

    public ResultDetailsClass() {
    }

    public ResultDetailsClass(int serialNo, String playerName, String perKills, String winAmount) {
        this.serialNo = serialNo;
        this.playerName = playerName;
        this.perKills = perKills;
        this.winAmount = winAmount;
    }

    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPerKills() {
        return perKills;
    }

    public void setPerKills(String perKills) {
        this.perKills = perKills;
    }

    public String getWinAmount() {
        return winAmount;
    }

    public void setWinAmount(String winAmount) {
        this.winAmount = winAmount;
    }
}
