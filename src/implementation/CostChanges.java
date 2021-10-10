package implementation;

import java.util.Objects;

public class CostChanges {
    private Long id;
    private Long infrastructureCost;


    /**
     * constructori pentru schimbari lunare ale costului unui dist
     */

    public CostChanges(final Long id, final Long infrastructureCoste) {
        this.id = id;
        this.infrastructureCost = infrastructureCoste;
    }

    /**
     * override la to string
     */
    @Override
    public String toString() {
        return "CostChanges{"
                + "id=" + id
                + ", infrastructureCost="
                + infrastructureCost;
    }

    /**
     * override la equals
     */

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CostChanges)) {
            return false;
        }
        CostChanges that = (CostChanges) o;
        return Objects.equals(id, that.id)
                && Objects.equals(infrastructureCost, that.infrastructureCost);
    }

    /**
     * override la hashcode
     */

    @Override
    public int hashCode() {
        return Objects.hash(id, infrastructureCost);
    }

    /**
     * getter pentru id
     */

    public Long getId() {
        return this.id;
    }

    /**
     * getter pentru costul infrastructurii
     */

    public Long getInfrastructureCoste() {
        return this.infrastructureCost;
    }

    /**
     * getter pentru costul de productie
     */


}
