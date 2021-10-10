package implementation;

import java.util.ArrayList;
import java.util.List;

public class MonthlyStats {
    private int month;
    private List<Integer> distributorsIds = new ArrayList<>();

    public MonthlyStats(int k) {
        this.month = k;
    }

    /**
     *
     * getter pentru month
     *
     */

    public int getMonth() {
        return month;
    }
    /**
     *
     * setter pentru month
     *
     */

    public void setMonth(int month) {
        this.month = month;
    }

    /**
     *
     * getter pentru distIds
     *
     */
    public List<Integer> getDistributorsIds() {
        return distributorsIds;
    }

    /**
     *
     * metoda de adaugare in lista de id a distribuitorilor
     *
     */
    public void addDistributorsIds(Integer distributorsIds) {
        this.distributorsIds.add(distributorsIds);
    }

    public MonthlyStats(int month, List<Integer> distributorsIds) {
        this.month = month;
        this.distributorsIds = distributorsIds;
    }

    public MonthlyStats() {
    }
}
