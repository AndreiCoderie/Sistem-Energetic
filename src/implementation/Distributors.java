package implementation;


import java.util.ArrayList;
import java.util.List;

public class Distributors extends Common {
    private Long id;
    private long contractLength;
    private Long initialBudget;
    private Long initialInfrastructureCost;
    private Long initialProductionCost;
    private List<Contract> contractsList = new ArrayList<>();

    /**
     *
     * getter pentru contractCost
     *
     */
    public long getContractCost() {
        return contractCost;
    }
    /**
     *
     * setter pentru contractCost
     *
     */

    public void setContractCost(long contractCost) {
        this.contractCost = contractCost;
    }

    private long contractCost;
    private boolean isBankrupt;
    private Long budget;
    private Long energyNeeded;
    private String producerStrategy;

    /**
     *
     * ovveride la getter pentru produceStrategy
     *
     */

    @Override
    public String getProducerStrategy() {
        return producerStrategy;
    }

    /**
     *
     * override la setter pentru energytype
     *
     */

    @Override
    public long getEnergyNeededKW() {
        return energyNeeded;
    }
    public Distributors(final Long id, final int contractLength, final Long initialBudget,
                        final Long initialInfrastructureCost, final Long initialProductionCost,
                        final boolean bankrupt) {
        this.id = id;
        this.contractLength = contractLength;
        this.initialBudget = initialBudget;
        this.initialInfrastructureCost = initialInfrastructureCost;
        this.initialProductionCost = initialProductionCost;
        this.isBankrupt = bankrupt;
        this.budget = initialBudget;
    }

    public Distributors(final Long id, final long contractLength, final Long initialBudget,
                        final Long initialInfrastructureCost, final Long productionCost,
                        final Long energyNeededKW,
                        final String producerStrategy) {
        this.id = id;
        this.contractLength = contractLength;
        this.initialBudget = initialBudget;
        this.initialInfrastructureCost = initialInfrastructureCost;
        this.initialProductionCost = productionCost;
        this.energyNeeded = energyNeededKW;
        this.producerStrategy = producerStrategy;
        this.budget = initialBudget;
    }

    /**
     *
     * setter pentru productionCost
     *
     */
    public void setProductionCost(Long initialProductionCost) {
        this.initialProductionCost = initialProductionCost;
    }

    /**
     * override la to string
     */
    @Override
    public String toString() {
        return "Distributors{"
                + "id=" + id
                + ", contractLength=" + contractLength
                + ", initialBudget=" + initialBudget
                + ", initialInfrastructureCost="
                + initialInfrastructureCost + ", initialProductionCost="
                + initialProductionCost + ", energyNeededKW="
                + energyNeeded + ", producerStrategy="
                + producerStrategy
                + ", contractsList=" + contractsList
                + ", isBankrupt=" + isBankrupt
                + ", budget=" + budget + '}';
    }

    public Distributors() {
    }

    public Distributors(final Long id, final Long contractLength, final Long initialBudget,
                        final Long initialInfrastructureCost,
                        final Long initialProductionCost) {
        this.id = id;
        this.contractLength = Math.toIntExact(contractLength);
        this.initialBudget = initialBudget;
        this.initialInfrastructureCost = initialInfrastructureCost;
        this.initialProductionCost = initialProductionCost;
        this.budget = initialBudget;
    }

    /**
     * getter pentru buget
     */


    public Long getBudget() {
        return budget;
    }

    /**
     * setter pentru buget
     */

    public void setBudget(final Long budget) {
        this.budget = budget;
    }

    /**
     * getter pentru bankrupt status
     */

    public boolean isBankrupt() {
        return isBankrupt;
    }

    /**
     * setter pentru bankrupt status
     */

    public void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    /**
     * getter pentru id
     */

    public long getId() {
        return id;
    }

    /**
     * setter pentru id
     */


    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * getter pentru lungimea contractului
     *
     * @return
     */

    public long getContractLength() {
        return contractLength;
    }

    /**
     * setter pentru lungimea contractului
     */

    public void setContractLength(final int contractLength) {
        this.contractLength = contractLength;
    }

    /**
     * getter pentru initial buget
     */

    public long getInitialBudget() {
        return initialBudget;
    }

    /**
     * setter pentru initial buget
     */

    public void setInitialBudget(final Long initialBudget) {
        this.initialBudget = initialBudget;
    }

    /**
     * getter pentru infrastructura
     */

    public long getInitialInfrastructureCost() {
        return initialInfrastructureCost;
    }

    /**
     * setter pt infrastructura
     */

    public void setInitialInfrastructureCost(final Long initialInfrastructureCost) {
        this.initialInfrastructureCost = initialInfrastructureCost;
    }

    /**
     * getter pentru production cost
     */


    /**
     * setter pentru production cost
     */


    /**
     * getter pentru lista de contracte
     */

    public List<Contract> getContractsList() {
        return contractsList;
    }

    /**
     * adder in lista de contracte a unui contract
     */

    public void addInContractsList(final Contract contract) {
        this.contractsList.add(contract);
    }


    /**
     * contorizeaza lunile ramase
     */

    public void counterOfRemainedMonths(final long ids) {
        for (Contract contract : this.contractsList) {
            if (contract.getConsumerId() == ids) {
                contract.setRemainedContractMonths(contract.getRemainedContractMonths() - 1);
            }
        }

    }

    /**
     * intoarce nr de luni ramase
     */


    public int remainedMonths(final long ids) {
        int remained = 0;
        for (Contract contract : this.contractsList) {
            if (contract.getConsumerId() == ids) {
                remained = contract.getRemainedContractMonths();
            }
        }
        return remained;
    }

    /**
     * scoate un contract din lista
     */

    public void removeContract(final long ids) {
        List<Contract> toRemove = new ArrayList<>();
        for (Contract contract : this.contractsList) {
            if (contract.getConsumerId() == ids) {
                toRemove.add(contract);
            }
        }
        this.contractsList.removeAll(toRemove);
    }

    /**
     * sterge lista de contracte
     */

    public void removeAllContracts() {
        this.contractsList.clear();
    }

    /**
     * setea noi costuri pentru distributor
     */

    public void setNewPricesForDistr(final Long infrastructure) {
        this.initialInfrastructureCost = infrastructure;
    }

    /**
     * veririfica daca un consumator exista in lista de contracte
     */

    public boolean checkIfConsummerExists(final long ids) {

        for (Contract contra : this.contractsList) {
            if (contra.getConsumerId() == ids) {
                return true;
            }
        }
        return false;
    }

    /**
     * calculeaza rata unui distribuitor luna asta
     */

    public Long rateCalculator() {
        long rate = 0;

        if (this.contractStatus != 0) {
            if (this.isBankrupt) {
                rate = 1000;
            } else {
                rate = (this.initialInfrastructureCost / this.contractStatus);
                rate += this.initialProductionCost + (0.2 * this.initialProductionCost);
            }
        } else {
            if (this.isBankrupt) {
                rate = 1000;
            } else {

                rate = (long) ((this.initialProductionCost * 0.2)
                        + this.initialInfrastructureCost + this.initialProductionCost);
            }
        }

        return rate;
    }

    /**
     * calculeaza bugetul unui distribuitor intr-o luna in care consumatorul a platit
     */

    public void distribBudgetInAPaiedMonth(final long ids, final boolean checker) {
        if (checker) {
            return;
        }
        for (Contract contract : this.contractsList) {
            if (contract.getConsumerId() == ids && contract.getRemainedContractMonths() >= 0) {
                this.budget += contract.getPrice();
                this.budget = this.budget - this.initialProductionCost;
            }
        }
    }

    /**
     * calculeaza bugetul unui distrib intr-o luna neplatita
     */

    public void distribBudgetInAUnpaiedMonth() {

        this.budget -= this.initialProductionCost;
    }

    private int contractStatus;

    /**
     * getter pentru contract status
     */


    public int getContractStatus() {
        return contractStatus;
    }

    /**
     * setter pentru contract status
     */


    public void setContractStatus(final int contractStatus) {
        this.contractStatus = contractStatus;
    }

    /**
     * bugetul final din luna respectiva a distribuitorului este aiba acum cand se scade
     * si infrastructura, nu am mai suprascris aceasta metoda, dand arguemnte = 0
     */
    @Override
    public void calculateBudget(final long ids, final long checker) {
        this.budget -= this.initialInfrastructureCost;
    }

}
