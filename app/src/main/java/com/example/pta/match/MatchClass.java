package com.example.pta.match;

public class MatchClass {

    private String id, name, category,candidateNo,date_time,totalPrize,perKill, entryFee,
            type, version,map,winnerPrice, runnerUpPrize1,runnerUpPrize2;

    public MatchClass() {}

    public MatchClass(String id, String name, String category, String candidateNo, String date_time,
                      String totalPrize, String perKill, String entryFee, String type, String version,
                      String map, String winnerPrice, String runnerUpPrize1, String runnerUpPrize2) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.candidateNo = candidateNo;
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

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getCandidateNo() {
        return candidateNo;
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
}


