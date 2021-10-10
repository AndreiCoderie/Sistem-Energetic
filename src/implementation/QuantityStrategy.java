package implementation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class QuantityStrategy implements Strategy {

    /**
     *
     * methoda de ales producatori pe strategia de cantitae
     *
     */
    @Override
    public List<Producers> chooseProduccer(Distributors distribuitor, List<Producers> producers) {
        List<Producers> priceSorted = producers.stream()
                .sorted(Comparator.comparing(Producers::getEnergyPerDistributor).reversed()
                        .thenComparing(Producers::getPriceKW)).collect(Collectors.toList());
        double sum = 0;
        int counter = 0;
        for (int i = 0; i < priceSorted.size(); i++) {
            if (distribuitor.getEnergyNeededKW() > sum) {
                sum += priceSorted.get(i).getEnergyPerDistributor();
                counter++;
            } else {
                break;
            }
        }
        List<Producers> finalList = new ArrayList<>();
        for (int i = 0; i < counter; i++) {
            finalList.add(priceSorted.get(i));
        }

        return finalList;
    }
}
