package models;

public class Clan {
    private long id;
    private String name;
    private Integer goldSum;
    private String typesOfEarning;
    private String causeOfLoss;

    public Clan() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGoldSum() {
        return goldSum;
    }

    public void setGoldSum(Integer goldSum) {
        this.goldSum = goldSum;
    }

    public String getTypesOfEarning() {
        return typesOfEarning;
    }

    public void setTypesOfEarning(String typesOfEarning) {
        this.typesOfEarning = typesOfEarning;
    }

    public String getCauseOfLoss() {
        return causeOfLoss;
    }

    public void setCauseOfLoss(String causeOfLoss) {
        this.causeOfLoss = causeOfLoss;
    }
}
