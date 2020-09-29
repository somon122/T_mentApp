package com.example.pta.result;

public class ResultClass {

    private String id, name,matchId, category,date_time,totalPrize,perKill, entryFee,
            type, version,map,winnerPrice, runnerUpPrize1,runnerUpPrize2;

    public ResultClass() {}



    public ResultClass(String id, String name, String matchId, String category, String date_time,
                       String totalPrize, String perKill, String entryFee, String type, String version,
                       String map, String winnerPrice, String runnerUpPrize1, String runnerUpPrize2) {
        this.id = id;
        this.name = name;
        this.matchId = matchId;
        this.category = category;
        this.date_time = date_time;
        this.totalPrize = totalPrize;
        this.perKill = perKill;
        this.entryFee = entryFee;
        this.type = type;
        this.version = version;
        this.map = map;
        this.winnerPrice = winnerPrice;
        this.runnerUpPrize1 = runnerUpPrize1;
        this.runnerUpPrize2 = runnerUpPrize2;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getTotalPrize() {
        return totalPrize;
    }

    public void setTotalPrize(String totalPrize) {
        this.totalPrize = totalPrize;
    }

    public String getPerKill() {
        return perKill;
    }

    public void setPerKill(String perKill) {
        this.perKill = perKill;
    }

    public String getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(String entryFee) {
        this.entryFee = entryFee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getWinnerPrice() {
        return winnerPrice;
    }

    public void setWinnerPrice(String winnerPrice) {
        this.winnerPrice = winnerPrice;
    }

    public String getRunnerUpPrize1() {
        return runnerUpPrize1;
    }

    public void setRunnerUpPrize1(String runnerUpPrize1) {
        this.runnerUpPrize1 = runnerUpPrize1;
    }

    public String getRunnerUpPrize2() {
        return runnerUpPrize2;
    }

    public void setRunnerUpPrize2(String runnerUpPrize2) {
        this.runnerUpPrize2 = runnerUpPrize2;
    }
}
