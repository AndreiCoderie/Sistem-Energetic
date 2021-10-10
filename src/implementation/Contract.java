package implementation;

public class Contract {
    private Long consumerId;
    private Long price;
    private int remainedContractMonths;

    /**
     * Override la ToString pentru a afisa un contract
     */

    @Override
    public String toString() {
        return "Contract{"
                + "consumerId="
                + consumerId
                + ", price=" + price
                + ", remainedContractMonths="
                + remainedContractMonths + '}';
    }


    /**
     * Constructor pentru clasa Contract
     */

    public Contract(final Long consumerId, final Long price, final int remainedContractMonths) {
        this.consumerId = consumerId;
        this.price = price;
        this.remainedContractMonths = remainedContractMonths;
    }

    /**
     * getter Pentru id
     */

    public Long getConsumerId() {
        return consumerId;
    }


    /**
     * setter pentru consummer id
     */
    public void setConsumerId(final Long consumerId) {
        this.consumerId = consumerId;
    }

    /**
     * getter pentru pretul contractului
     */

    public Long getPrice() {
        return price;
    }

    /**
     * setter pentru pretul contractului
     */

    public void setPrice(final Long price) {
        this.price = price;
    }

    /**
     * getter pentru lunile ramase
     */

    public int getRemainedContractMonths() {
        return remainedContractMonths;
    }

    /**
     * setter pentru lunile ramase
     */

    public void setRemainedContractMonths(final int remainedContractMonths) {
        this.remainedContractMonths = remainedContractMonths;
    }
}
