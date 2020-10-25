package com.example.pta.result;

public class ResultClass {

    private String id, name,matchId, category,date_time,totalPrize,perKill, entryFee,
            type, version,map,winnerPrice, runnerUpPrize1,runnerUpPrize2 ,runnerUpPrize3
            ,runnerUpPrize4,runnerUpPrize5,runnerUpPrize6;

    public ResultClass() {}

    public ResultClass(String id, String name, String matchId, String category, String date_time, String totalPrize,
                       String perKill, String entryFee, String type, String version, String map, String winnerPrice,
                       String runnerUpPrize1, String runnerUpPrize2, String runnerUpPrize3, String runnerUpPrize4,
                       String runnerUpPrize5, String runnerUpPrize6) {
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
        this.runnerUpPrize3 = runnerUpPrize3;
        this.runnerUpPrize4 = runnerUpPrize4;
        this.runnerUpPrize5 = runnerUpPrize5;
        this.runnerUpPrize6 = runnerUpPrize6;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMatchId() {
        return matchId;
    }

    public String getCategory() {
        return category;
    }

    public String getDate_time() {
        return date_time;
    }

    public String getTotalPrize() {
        return totalPrize;
    }

    public String getPerKill() {
        return perKill;
    }

    public String getEntryFee() {
        return entryFee;
    }

    public String getType() {
        return type;
    }

    public String getVersion() {
        return version;
    }

    public String getMap() {
        return map;
    }

    public String getWinnerPrice() {
        return winnerPrice;
    }

    public String getRunnerUpPrize1() {
        return runnerUpPrize1;
    }

    public String getRunnerUpPrize2() {
        return runnerUpPrize2;
    }

    public String getRunnerUpPrize3() {
        return runnerUpPrize3;
    }

    public String getRunnerUpPrize4() {
        return runnerUpPrize4;
    }

    public String getRunnerUpPrize5() {
        return runnerUpPrize5;
    }

    public String getRunnerUpPrize6() {
        return runnerUpPrize6;
    }
}
