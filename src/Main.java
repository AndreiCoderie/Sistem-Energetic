import com.fasterxml.jackson.databind.ObjectMapper;
import implementation.Consummers;
import implementation.Contract;
import implementation.Distributors;
import implementation.GreenStrategy;
import implementation.Input;
import implementation.InputLoader;
import implementation.MonthlyStats;
import implementation.MonthlyUpdates;
import implementation.PriceStrategy;
import implementation.Producers;
import implementation.QuantityStrategy;
import implementation.Strategy;
import org.json.simple.JSONArray;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {
    /**
     * metoda statica de baza folosita in Test
     */
    public static void main(final String[] args) throws IOException {
        InputLoader inputLoader = new InputLoader(args[0]);
        Input input = inputLoader.readData();
        List<Consummers> consummersList = new ArrayList<>();
        List<Distributors> distributorsList = new ArrayList<>();
        List<Producers> produccerList = new ArrayList<>();
        Long nrOfTurns = input.getNumberOfTurns();
        Map<Integer, MonthlyUpdates> changes = new LinkedHashMap<>();
        if (input.getConsummersList() != null) {
            for (int i = 0; i < input.getConsummersList().size(); i++) {
                consummersList.add(new Consummers(input.getConsummersList().get(i).getId(),
                        input.getConsummersList().get(i).getInitialBudget(),
                        input.getConsummersList().get(i).getMonthlyIncome()));
            }
        }
        if (input.getDistributorsList() != null) {
            for (int i = 0; i < input.getDistributorsList().size(); i++) {
                distributorsList.add(new Distributors(input.getDistributorsList().get(i).getId(),
                        (int) input.getDistributorsList().get(i).getContractLength(),
                        input.getDistributorsList().get(i).getInitialBudget(),
                        input.getDistributorsList().get(i).getInitialInfrastructureCost(),
                        input.getDistributorsList().get(i).getInitialProductionCost(),
                        input.getDistributorsList().get(i).getEnergyNeededKW(),
                        input.getDistributorsList().get(i).getProducerStrategy()));
            }
        }

        if (input.getProduccerList() != null) {
            for (int i = 0; i < input.getProduccerList().size(); i++) {
                produccerList.add(new Producers(input.getProduccerList().get(i).getId(),
                        input.getProduccerList().get(i).getEnergyType(),
                        input.getProduccerList().get(i).getMaxDistributors(),
                        input.getProduccerList().get(i).getPriceKW(),
                        input.getProduccerList().get(i).getEnergyPerDistributor()));
            }
        }
        if (input.getMonthlyUpdates().size() != 0) {
            for (int i = 0; i < input.getMonthlyUpdates().size(); i++) {
                changes.put(i, input.getMonthlyUpdates().get(i));
            }
        }


        long lastLowerPrice = 0;
        Map<Integer, ArrayList<Long>> mapWithRates = new LinkedHashMap<>();
        for (int k = 0; k <= nrOfTurns; k++) {

            if (k != 0) {
                if (changes.get(k - 1).getProducersChanges().size() != 0) {
                    for (int i = 0; i < produccerList.size(); i++) {
                        for (int j = 0; j < changes.get(k - 1).getProducersChanges().size(); j++) {
                            if (produccerList.get(i).getId()
                                    == changes.get(k - 1).getProducersChanges().get(j).getId()) {
                                produccerList.get(i).setEnergyPerDistributor(changes
                                        .get(k - 1).getProducersChanges().get(j)
                                        .getEnergyPerDistributor());
                            }
                        }
                    }
                }
            }


            ArrayList<Long> arrayWithPrices = new ArrayList<>();

            for (int i = 0; i < distributorsList.size(); i++) {
                Strategy strategy;
                boolean checker = true;
                if (distributorsList.get(i).getProducerStrategy().equals("GREEN")) {
                    strategy = new GreenStrategy();
                    List<Producers> greenList = strategy.chooseProduccer(distributorsList.get(i),
                            produccerList);
                    double sum = 0;
                    for (int j = 0; j < greenList.size(); j++) {
                        sum += greenList.get(j).getEnergyPerDistributor()
                                * greenList.get(j).getPriceKW();
                    }
                    distributorsList.get(i).setProductionCost((long) Math.floor(sum / 10));
                    if (k != 0) {
                        for (int m = 0; m < produccerList.size(); m++) {
                            MonthlyStats monthCalcs = new MonthlyStats(k);
                            for (int n = 0; n < greenList.size(); n++) {
                                if (produccerList.get(m).getId() == greenList.get(n).getId()) {
                                    monthCalcs.addDistributorsIds(
                                            (int) distributorsList.get(i).getId());
                                }
                            }
                            produccerList.get(m).addMonthlyList(monthCalcs);

                        }

                    }
                } else if (distributorsList.get(i).getProducerStrategy().equals("PRICE")) {
                    strategy = new PriceStrategy();
                    List<Producers> priceList = strategy.chooseProduccer(distributorsList.get(i),
                            produccerList);
                    double sum = 0;
                    for (int j = 0; j < priceList.size(); j++) {
                        sum += priceList.get(j).getEnergyPerDistributor()
                                * priceList.get(j).getPriceKW();
                    }
                    distributorsList.get(i).setProductionCost((long) Math.floor(sum / 10));
                    if (k != 0) {
                        for (int m = 0; m < produccerList.size(); m++) {
                            MonthlyStats monthCalcs = new MonthlyStats(k);
                            for (int n = 0; n < priceList.size(); n++) {
                                if (produccerList.get(m).getId() == priceList.get(n).getId()) {
                                    //MonthlyStats monthlyChecker = new MonthlyStats();
                                    monthCalcs.addDistributorsIds(
                                            (int) distributorsList.get(i).getId());
                                }
                            }
                            produccerList.get(m).addMonthlyList(monthCalcs);
                        }
                    }

                } else if (distributorsList.get(i).getProducerStrategy().equals("QUANTITY")) {
                    strategy = new QuantityStrategy();
                    List<Producers> quantityList = strategy.chooseProduccer(distributorsList.get(i),
                            produccerList);
                    double sum = 0;
                    for (int j = 0; j < quantityList.size(); j++) {
                        sum += quantityList.get(j).getEnergyPerDistributor()
                                * quantityList.get(j).getPriceKW();
                    }
                    distributorsList.get(i).setProductionCost((long) Math.floor(sum / 10));
                    if (k != 0) {
                        for (int m = 0; m < produccerList.size(); m++) {
                            MonthlyStats monthCalcs = new MonthlyStats(k);

                            for (int n = 0; n < quantityList.size(); n++) {
                                if (produccerList.get(m).getId() == quantityList.get(n).getId()) {
                                    monthCalcs.addDistributorsIds(
                                            (int) distributorsList.get(i).getId());
                                }
                            }
                            produccerList.get(m).addMonthlyList(monthCalcs);
                        }
                    }

                }
            }


            if (k != 0) {
                for (int i = 0; i < distributorsList.size(); i++) {
                    for (int m = 0; m < changes.get(k - 1).getChanges().size(); m++) {
                        if (distributorsList.get(i).getId()
                                == changes.get(k - 1).getChanges().get(m).getId()) {
                            distributorsList.get(i).setNewPricesForDistr(
                                    changes.get(k - 1).getChanges().get(m)
                                            .getInfrastructureCoste());
                        }


                    }

                    Long rate = distributorsList.get(i).rateCalculator();
                    arrayWithPrices.add(rate);
                    distributorsList.get(i).setContractCost((long) rate);
                }

            } else {
                for (int i = 0; i < distributorsList.size(); i++) {
                    Long rate = distributorsList.get(i).rateCalculator();
                    arrayWithPrices.add(rate);
                    distributorsList.get(i).setContractCost((long) rate);
                }
            }
            mapWithRates.put(k, arrayWithPrices);
            ArrayList<Integer> distribChecker = new ArrayList<>();
            long smallestPrice = 1000;
            for (int j = 0; j < distributorsList.size(); j++) {
                for (int i = 0; i < arrayWithPrices.size(); i++) {
                    if (arrayWithPrices.get(i) < smallestPrice) {
                        smallestPrice = arrayWithPrices.get(i);
                    }
                }
            }
            distribChecker.add(arrayWithPrices.indexOf((smallestPrice)));
            for (int j = 0; j < distributorsList.size(); j++) {
                for (int i = 0; i < consummersList.size(); i++) {
                    if (distributorsList.get(j)
                            .remainedMonths(consummersList.get(i).getId()) == 0) {
                        distributorsList.get(j)
                                .removeContract(consummersList.get(i).getId());
                        consummersList.get(i).setContractMonitor(false);
                        if (consummersList.get(i).isBankrupt()) {
                            consummersList.get(i)
                                    .addPastDistrib(distributorsList.get(j).getId());
                        }
                    }
                }
            }


            if (k != 0) {
                if (changes.get(k - 1).getConsummers().size() != 0) {

                    for (Consummers consum : changes.get(k - 1).getConsummers()) {
                        consummersList.add(new Consummers(consum.getId(),
                                consum.getInitialBudget(),
                                consum.getMonthlyIncome()));
                    }
                }

            }

            for (int j = 0; j < distributorsList.size(); j++) {
                if (distribChecker.get(0) == j) {
                    for (int i = 0; i < consummersList.size(); i++) {
                        distributorsList.get(j).getContractsList();
                        List<Long> actualPrices = new ArrayList<>();
                        actualPrices.addAll(mapWithRates.get(k));
                        Collections.sort(actualPrices);
                        Long lowerPrice = actualPrices.get(0);

                        if (!distributorsList.get(j)
                                .checkIfConsummerExists(consummersList.get(i).getId())
                                && !consummersList.get(i).isContractMonitor()
                                && !distributorsList.get(j).isBankrupt()) {

                            distributorsList.get(j).addInContractsList(
                                    new Contract(consummersList.get(i).getId(),
                                            lowerPrice,
                                            (int) distributorsList.get(j).getContractLength()));
                            consummersList.get(i).setContractMonitor(true);
                            if (consummersList.get(i).isBankrupt()
                                    && consummersList.get(i).getBudget() > lowerPrice) {
                                consummersList.get(i).setBankruptStatus(true);
                            }
                        }


                        if (distributorsList.get(j)
                                .checkIfConsummerExists(consummersList.get(i).getId())) {
                            for (Contract contract : distributorsList.get(j).getContractsList()) {
                                if (contract.getConsumerId() == consummersList.get(i).getId()) {
                                    consummersList.get(i)
                                            .calculateBudget(contract.getPrice(), k);
                                }
                            }
                            consummersList.get(i).setRemainedMonths(distributorsList.get(j)
                                    .remainedMonths(consummersList.get(i).getId()));

                            distributorsList.get(j)
                                    .counterOfRemainedMonths(consummersList.get(i).getId());
                            if (consummersList.get(i).getUnpaiedMonths().contains(k)) {
                                distributorsList.get(j).distribBudgetInAUnpaiedMonth();
                            } else {
                                if (distributorsList.get(j)
                                        .checkIfConsummerExists(consummersList.get(i).getId())) {
                                    distributorsList.get(j).distribBudgetInAPaiedMonth(
                                            consummersList.get(i).getId(),
                                            consummersList.get(i).isBankrupt());
                                }
                            }


                            if (consummersList.get(i).isBankrupt()
                                    && distributorsList.get(j).checkIfConsummerExists(
                                    consummersList.get(i).getId())) {
                                distributorsList.get(j)
                                        .removeContract(consummersList.get(i).getId());
                                consummersList.get(i).setContractMonitor(false);
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < consummersList.size(); i++) {
                        if (distributorsList.get(j).getContractsList().size() != 0) {

                            if (distributorsList.get(j)
                                    .checkIfConsummerExists(consummersList.get(i).getId())) {
                                for (Contract contract : distributorsList.get(j)
                                        .getContractsList()) {
                                    if (contract.getConsumerId() == consummersList.get(i).getId()) {
                                        consummersList.get(i)
                                                .calculateBudget(contract.getPrice(), k);
                                    }
                                }

                                consummersList.get(i).setContractMonitor(true);

                                consummersList.get(i).setRemainedMonths(distributorsList.get(j)
                                        .remainedMonths(consummersList.get(i).getId()));

                                distributorsList.get(j)
                                        .counterOfRemainedMonths(consummersList.get(i).getId());
                                if (consummersList.get(i).getUnpaiedMonths().contains(k)) {
                                    distributorsList.get(j).distribBudgetInAUnpaiedMonth();
                                } else {
                                    if (distributorsList.get(j)
                                            .checkIfConsummerExists(
                                                    consummersList.get(i).getId())) {
                                        distributorsList.get(j).distribBudgetInAPaiedMonth(
                                                consummersList.get(i).getId(),
                                                consummersList.get(i).isBankrupt());
                                    }
                                }


                                if (consummersList.get(i).isBankrupt()) {
                                    consummersList.get(i)
                                            .addPastDistrib(distributorsList.get(j).getId());
                                }

                                if (consummersList.get(i).isBankrupt()
                                        && distributorsList.get(j).checkIfConsummerExists(
                                        consummersList.get(i).getId())) {
                                    distributorsList.get(j)
                                            .removeContract(consummersList.get(i).getId());
                                    consummersList.get(i).setContractMonitor(false);
                                }
                            }
                        }
                    }
                }

                if (!distributorsList.get(j).isBankrupt()) {
                    distributorsList.get(j).calculateBudget(0, 0);
                }
                if (distributorsList.get(j).getBudget() < 0) {
                    distributorsList.get(j).setBankrupt(true);
                    distributorsList.get(j).removeAllContracts();
                }

            }
            for (int j = 0; j < distributorsList.size(); j++) {
                distributorsList.get(j)
                        .setContractStatus(distributorsList.get(j).getContractsList().size());
                for (int i = 0; i < consummersList.size(); i++) {
                    for (Contract contract : distributorsList.get(j).getContractsList()) {
                        if (contract.getConsumerId() == consummersList.get(i).getId()) {
                            lastLowerPrice = contract.getPrice();
                            consummersList.get(i).setLastPrice(lastLowerPrice);
                        }
                    }
                }
            }

        }


        JSONArray jsonConsummers = new JSONArray();


        for (int i = 0; i < consummersList.size(); i++) {
            Map consumersOutput = new LinkedHashMap();
            consumersOutput.put("id", consummersList.get(i).getId());
            consumersOutput.put("isBankrupt", consummersList.get(i).isBankrupt());
            consumersOutput.put("budget", consummersList.get(i).getBudget());
            jsonConsummers.add(consumersOutput);
        }
        JSONArray jsonDistrib = new JSONArray();
        for (int i = 0; i < distributorsList.size(); i++) {
            Map distribOutput = new LinkedHashMap();
            distribOutput.put("id", distributorsList.get(i).getId());
            distribOutput.put("energyNeededKW", distributorsList.get(i).getEnergyNeededKW());
            distribOutput.put("contractCost", distributorsList.get(i).getContractCost());
            distribOutput.put("budget", distributorsList.get(i).getBudget());
            distribOutput.put("producerStrategy", distributorsList.get(i).getProducerStrategy());
            distribOutput.put("isBankrupt", distributorsList.get(i).isBankrupt());
            distribOutput.put("contracts", distributorsList.get(i).getContractsList());
            jsonDistrib.add(distribOutput);
        }
        JSONArray jsonProduc = new JSONArray();
        for (int i = 0; i < produccerList.size(); i++) {
            Map produccersOutput = new LinkedHashMap();
            produccersOutput.put("id", produccerList.get(i).getId());
            produccersOutput.put("maxDistributors", produccerList.get(i).getMaxDistributors());
            produccersOutput.put("priceKW", produccerList.get(i).getPriceKW());
            produccersOutput.put("energyType", produccerList.get(i).getEnergyType());
            produccersOutput
                    .put("energyPerDistributor", produccerList.get(i).getEnergyPerDistributor());
            produccersOutput
                    .put("energyPerDistributor", produccerList.get(i).getEnergyPerDistributor());
            produccersOutput.put("monthlyStats", produccerList.get(i).getMonthlyList());
            jsonProduc.add(produccersOutput);
        }


        Map<String, JSONArray> output1 = new LinkedHashMap<>();
        output1.put("consumers", jsonConsummers);
        output1.put("distributors", jsonDistrib);
        output1.put("energyProducers", jsonProduc);
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(output1);


        try (FileWriter file = new FileWriter("results.out")) {
            file.write(result);
        } catch (IOException a) {
            a.printStackTrace();
        }
    }
}

