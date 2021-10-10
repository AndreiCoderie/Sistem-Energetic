package implementation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GreenStrategy implements Strategy {

    /**
     *
     * metoda de alegera a producatorului in strategia green
     *
     */

    @Override
    public List<Producers> chooseProduccer(Distributors distribuitor, List<Producers> producers) {
        List<Producers> rightProducers = new ArrayList<>(); // lista cu producatorii care
        List<Producers> notRightProducers = new ArrayList<>();
        // au renewable
        List<Producers> finalList = new ArrayList<>();
        for (int j = 0; j < producers.size(); j++) {
            if (producers.get(j).getEnergyType().equals("WIND")
                    || producers.get(j).getEnergyType().equals("SOLAR")
                    || producers.get(j).getEnergyType().equals("HYDRO")) {
                rightProducers.add(producers.get(j));
            } else {
                notRightProducers.add(producers.get(j));
            }
        }
        List<Producers> priceSorted = rightProducers.stream()
                .sorted(Comparator.comparing(Producers::getPriceKW)
                        .thenComparing(Producers::getEnergyPerDistributor)
                        .thenComparing(Producers::getId))
                .collect(Collectors.toList());
        List<Producers> notRightpriceSorted = notRightProducers.stream()
                .sorted(Comparator.comparing(Producers::getPriceKW)
                        .thenComparing(Producers::getEnergyPerDistributor)
                        .thenComparing(Producers::getId))
                .collect(Collectors.toList());
        double sum = 0;
        int counter = 0;
        for (int i = 0; i < priceSorted.size(); i++) {
            if (distribuitor.getEnergyNeededKW() > sum) {
                sum += (double) priceSorted.get(i).getEnergyPerDistributor();
                counter++;
            } else {
                break;
            }
        }
        for (int i = 0; i < counter; i++) {
            finalList.add(priceSorted.get(i));
        }
        if (sum < distribuitor.getEnergyNeededKW()) {
            int counter2 = 0;
            for (int i = 0; i < notRightpriceSorted.size(); i++) {
                if (distribuitor.getEnergyNeededKW() > sum) {
                    sum += (double) notRightpriceSorted.get(i).getEnergyPerDistributor();
                    counter2++;
                } else {
                    break;
                }
            }
            for (int i = 0; i < counter2; i++) {
                finalList.add(notRightpriceSorted.get(i));
            }
        }
        //fa la fel si pt celelalte 2
        return finalList;
    }
}
