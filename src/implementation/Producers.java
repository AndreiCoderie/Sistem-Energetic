package implementation;

import java.util.ArrayList;
import java.util.List;

public class Producers extends Common {
    private long id;
    private String eneryType;
    private long maxDistributors;
    private double priceKW;
    private long energyPerDistributor;

    public Producers(long id, Long energyPerDistributor) {
        this.id = id;
        this.energyPerDistributor = energyPerDistributor;
    }
    private List<MonthlyStats> monthlyList = new ArrayList<>();
    /**
     *
     * getter pentru monthlyList
     *
     */

    public List<MonthlyStats> getMonthlyList() {
        return monthlyList;
    }

    /**
     *
     * setter pentru monthlyList
     *
     */
    public void addMonthlyList(MonthlyStats monthlyList) {
        this.monthlyList.add(monthlyList);
    }

    /**
     *
     * getter pentru id
     *
     */
    public long getId() {
        return id;
    }

    /**
     *
     * setter pentru id
     *
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     *
     * metoada goala din Common
     *
     */

    @Override
    void calculateBudget(long ids, long k) {

    }

    /**
     *
     * setter pentru monthlyList
     *
     */

    public String getEnergyType() {
        return eneryType;
    }

    /**
     *
     * getter pentru maxDistrib
     *
     */

    public long getMaxDistributors() {
        return maxDistributors;
    }

    /**
     *
     * setter pentru maxDis
     *
     */

    public void setMaxDistributors(long maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    /**
     *
     * getter pentru price/KW
     *
     */

    public double getPriceKW() {
        return priceKW;
    }

    /**
     *
     * setter pentru priceKW
     *
     */

    public void setPriceKW(double priceKW) {
        this.priceKW = priceKW;
    }

    /**
     *
     * getter pentru energy/perDistrib
     *
     */

    public long getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    /**
     *
     * setter pentru energy/Distrib
     *
     */

    public void setEnergyPerDistributor(long energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public Producers(long id, String eneryType, long maxDistributors, double priceKW,
                     long energyPerDistributor) {
        this.id = id;
        this.eneryType = eneryType;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyPerDistributor = energyPerDistributor;
    }

    /**
     *
     * toString method for Prod
     *
     */
    @Override
    public String toString() {
        return "Producers{"
                + "id=" + id
                + ", eneryType='" + eneryType + '\''
                + ", maxDistributors=" + maxDistributors
                + ", priceKW=" + priceKW
                + ", energyPerDistributor=" + energyPerDistributor
                + '}';
    }

    public Producers() {
    }

}
