package implementation;

import java.util.List;

public interface Strategy {
    public List<Producers> chooseProduccer(Distributors distribuitor, List<Producers> producers);
}
