package implementation;


import java.util.ArrayList;

public class Consummers extends Common {
    private long id;
    private Long initialBudget;
    private Long monthlyIncome;
    private boolean bankruptStatus;
    private Long budget;
    // ------------------

    private ArrayList<Integer> unpaiedMonths = new ArrayList<>();

    private ArrayList<Long> pastDistrib = new ArrayList<>();

    /**
     * getter pentru distribuitorii trecuti
     */

    public ArrayList<Long> getPastDistrib() {
        return pastDistrib;
    }

    /**
     * adder pentru distribuitorii trecuti
     */

    public void addPastDistrib(final Long pastDistribs) {
        this.pastDistrib.add(pastDistribs);
    }

    /**
     * getter pentru lunule neplatite
     */

    public ArrayList<Integer> getUnpaiedMonths() {
        return unpaiedMonths;
    }


    private int status;

    private int remainedMonths;

    /**
     * getter pentru lunile ramase
     */

    public void setRemainedMonths(final int remainedMonths) {
        this.remainedMonths = remainedMonths;
    }


    /**
     * override la to string pentru consumator
     */


    @Override
    public String toString() {
        return "Consummers{"
                + "id=" + id
                + ", initialBudget=" + initialBudget
                + ", monthlyIncome=" + monthlyIncome
                + ", bankruptStatus=" + bankruptStatus
                + ", budget=" + budget
                + '}';
    }

    /**
     * constructor pentru consumator
     */

    public Consummers() {
    }

    public Consummers(final long id, final Long initialBudget, final Long monthlyIncome) {
        this.id = id;
        this.monthlyIncome = monthlyIncome;
        this.initialBudget = initialBudget;
        this.budget = initialBudget;
    }


    private boolean contractMonitor;

    /**
     * vizualizeaza daca are un contract activ in momentul de fata
     */

    public boolean isContractMonitor() {
        return contractMonitor;
    }

    /**
     * seteaza pe true sau false daca are sau nu contract in mom actual
     */

    public void setContractMonitor(final boolean contractMonitor) {
        this.contractMonitor = contractMonitor;
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

    public void setId(final int id) {
        this.id = id;
    }

    /**
     * getter  pentru initial budget
     */

    public long getInitialBudget() {
        return initialBudget;
    }

    /**
     * setter pentru initial budget
     */

    public void setInitialBudget(final Long initialBudget) {
        this.initialBudget = initialBudget;
    }

    /**
     * getter pentru venit lunar
     */

    public long getMonthlyIncome() {
        return monthlyIncome;
    }

    /**
     * setter pentru venit lunar
     */
    public void setMonthlyIncome(final Long monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    /**
     * getter pentru bankrupt status
     */
    public boolean isBankrupt() {
        return bankruptStatus;
    }

    /**
     * setter pentru bankrupt status
     */

    public void setBankruptStatus(final boolean bankruptStatus) {
        this.bankruptStatus = bankruptStatus;
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


    private long lastPrice = 0;

    /**
     * seteaza pretul antecedent celui actual pt consumator
     */
    public void setLastPrice(final long lastPrice) {
        this.lastPrice = lastPrice;
    }

    /**
     * calcularea bugetului unui cons luna actuala
     */

    @Override
    public void calculateBudget(final long rate1, final long k) {
        if (this.status == 2) {
            this.bankruptStatus = true;
            return;
        }
        if (this.bankruptStatus) {
            return;
        }
        Long rate = rate1;
        if (this.status > 0) {
            rate = Math.round(Math.floor(1.2 * this.lastPrice + rate1));
        }
        this.budget += this.monthlyIncome;
        if (this.budget >= rate) {
            this.bankruptStatus = false;
            this.budget = this.budget - rate;
            this.status = 0;
        } else {
            this.unpaiedMonths.add((int) k);
            this.status++;
        }
        if (this.status == 2) {
            this.bankruptStatus = true;
            return;
        }
    }
}


