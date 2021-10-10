package implementation;

import java.util.List;

public class Input {
    private final Long numberOfTurns;

    private final List<Common> consummersList;

    private final List<Common> distributorsList;

    private final List<MonthlyUpdates> monthlyUpdates;

    /**
     *
     * getter pentru produccerList
     *
     */

    public List<Common> getProduccerList() {
        return produccerList;
    }

    private final List<Common> produccerList;

    /**
     * getter pentru numarul de runde
     */

    public Long getNumberOfTurns() {
        return numberOfTurns;
    }

    /**
     * getter pentru lista de consumatori
     */

    public List<Common> getConsummersList() {
        return consummersList;
    }

    /**
     * getter pentru lista de distribuitori
     */

    public List<Common> getDistributorsList() {
        return distributorsList;
    }


    /**
     * override la to string
     */

    @Override
    public String toString() {
        return "Input{"
                + "numberOfTurns=" + numberOfTurns
                + ", consummersList=" + consummersList
                + ", distributorsList=" + distributorsList
                + ", monthlyUpdates=" + monthlyUpdates + '}';
    }

    /**
     * getter pentru lista de schimbari lunare
     */

    public List<MonthlyUpdates> getMonthlyUpdates() {
        return monthlyUpdates;
    }

    /**
     * constructor i clasa input
     */

    public Input(final Long numberOfTurns, final List<Common> consummersList,
                 final List<Common> distributorsList, final List<Common> produccerList,
                 final List<MonthlyUpdates> monthlyUpdates1) {
        this.numberOfTurns = numberOfTurns;
        this.consummersList = consummersList;
        this.distributorsList = distributorsList;
        this.produccerList = produccerList;
        this.monthlyUpdates = monthlyUpdates1;
    }
}
