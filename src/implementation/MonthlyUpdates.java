package implementation;

import java.util.ArrayList;

public class MonthlyUpdates {
    private ArrayList<Consummers> consummers = new ArrayList();
    private ArrayList<CostChanges> changes = new ArrayList<>();
    private ArrayList<Producers> producersChanges = new ArrayList<>();

    /**
     * override la to string
     */

    @Override
    public String toString() {
        return "MonthlyUpdates{"
                + "consummers=" + consummers
                + ", changes=" + changes
                + " , prdoucerChanges" + producersChanges
                + '}';
    }

    /**
     * constructor pentru update lunare
     */
    public MonthlyUpdates(final ArrayList<Consummers> consummers,
                          final ArrayList<CostChanges> changes,
                          final ArrayList<Producers> prodChanges) {
        this.consummers = consummers;
        this.changes = changes;
        this.producersChanges = prodChanges;
    }

    public MonthlyUpdates(final ArrayList<Consummers> consummers,
                          final ArrayList<CostChanges> changes) {
        this.consummers = consummers;
        this.changes = changes;
    }

    /**
     * getter pentru array de consumator
     */

    public ArrayList<Consummers> getConsummers() {
        return consummers;
    }

    /**
     * getter pentru array de Schimbar ale costului
     */

    public ArrayList<CostChanges> getChanges() {
        return changes;
    }

    /**
     *
     * getter pentru produccerChanges
     *
     */

    public ArrayList<Producers> getProducersChanges() {
        return producersChanges;
    }
}
