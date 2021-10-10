package implementation;

public abstract class Common {
    private long id;
    private long initialBudget;
    private long monthlyIncome;
    private long initialProductionCost;
    private long contractLength;
    private long initialInfrastructureCost;
    private boolean bankrupt;
    private long energyNeededKW;
    private long maxDistributors;
    private double priceKW;
    private long energyPerDistributor;
    private String energyType;

    /**
     *
     * getter pentru energytype
     *
     */
    public String getEnergyType() {
        return energyType;
    }

    /**
     *
     * setter pentru energytype
     *
     */

    public void setEnergyType(String energyType) {
        this.energyType = energyType;
    }
    /**
     *
     * getter pentru maxDistributors
     *
     */
    public long getMaxDistributors() {
        return maxDistributors;
    }
    /**
     *
     * setter pentru maxDistributors
     *
     */

    public void setMaxDistributors(long maxDistributors) {
        this.maxDistributors = maxDistributors;
    }
    /**
     *
     * getter pentru price/kw
     *
     */

    public double getPriceKW() {
        return priceKW;
    }
    /**
     *
     * setter pentru price/kw
     *
     */

    public void setPriceKW(double priceKW) {
        this.priceKW = priceKW;
    }
    /**
     *
     * getter pentru energy/distributor
     *
     */

    public long getEnergyPerDistributor() {
        return energyPerDistributor;
    }
    /**
     *
     * setter pentru energy/distributor
     *
     */

    public void setEnergyPerDistributor(long energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }
    /**
     *
     * getter pentru energy neededKw
     *
     */

    public long getEnergyNeededKW() {
        return energyNeededKW;
    }

    /**
     *
     * setter pentru energyNeeded
     *
     */

    public void setEnergyNeededKW(long energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }
    /**
     *
     * getter pentru produccerStrategy
     *
     */

    public String getProducerStrategy() {
        return producerStrategy;
    }

    /**
     *
     * setter pentru producerStrategy
     *
     */
    public void setProducerStrategy(String producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    private String producerStrategy;

    /**
     * getter pentru id
     */

    public long getId() {
        return id;
    }

    /**
     * setter pentru id
     */

    public void setId(final long id) {
        this.id = id;
    }

    /**
     * getter pentru initial budget
     */

    public long getInitialBudget() {
        return initialBudget;
    }

    /**
     * setter pentru initial budget
     */

    public void setInitialBudget(final long initialBudget) {
        this.initialBudget = initialBudget;
    }

    /**
     * getter pentru incasari lunare
     */

    public long getMonthlyIncome() {
        return monthlyIncome;
    }

    /**
     * setter pentru incasari lunare
     */

    public void setMonthlyIncome(final long monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    /**
     * getter pentru costul initial de productie
     */

    public long getInitialProductionCost() {
        return initialProductionCost;
    }

    /**
     * setter pentru costul initial de productie
     */

    public void setInitialProductionCost(final long initialProductionCost) {
        this.initialProductionCost = initialProductionCost;
    }

    /**
     * getter pentru lungimea contractului
     */

    public long getContractLength() {
        return contractLength;
    }

    /**
     * setter pentru lungimea contractului
     */

    public void setContractLength(final long contractLength) {
        this.contractLength = contractLength;
    }

    /**
     * getter pentru costul initial al infrastructurii
     */

    public long getInitialInfrastructureCost() {
        return initialInfrastructureCost;
    }

    /**
     * setter pentru costul initial al infrastructurii
     */

    public void setInitialInfrastructureCost(final long initialInfrastructureCost) {
        this.initialInfrastructureCost = initialInfrastructureCost;
    }

    /**
     * getter pentru bankrup
     */

    public boolean isBankrupt() {
        return bankrupt;
    }

    /**
     * setter pentru bankrup
     */

    public void setBankrupt(final boolean bankrupt) {
        this.bankrupt = bankrupt;
    }

    /**
     * metoda de calcul al bugetului
     */

    abstract void calculateBudget(long ids, long k);
}
