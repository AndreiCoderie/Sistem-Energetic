package implementation;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputLoader {
    private final String inputPath;

    /**
     * constructor pentru inputLoade
     */

    public InputLoader(final String inputPath) {
        this.inputPath = inputPath;
    }

    /**
     * functia readData care citeste datele
     */

    public Input readData() {
        JSONParser jsonParser = new JSONParser();
        Long numberOfTurns = Long.valueOf(0);
        List<Common> consummersList = new ArrayList<>();
        List<Common> distributorsList = new ArrayList<>();
        List<MonthlyUpdates> monthlyUpdate = new ArrayList<>();
        List<Common> producerList = new ArrayList<>();
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(inputPath));
            numberOfTurns = (Long) jsonObject.get("numberOfTurns");
            JSONObject initialData = (JSONObject) jsonObject.get("initialData");
            JSONArray jsonMonthly = (JSONArray) jsonObject.get("monthlyUpdates");
            JSONArray jsonConsummers = (JSONArray) initialData.get("consumers");
            JSONArray jsonDistributors = (JSONArray) initialData.get("distributors");
            JSONArray jsonProducers = (JSONArray) initialData.get("producers");

            FactoryClass factory = FactoryClass.getInstance();

            if (jsonMonthly != null) {
                for (Object jsonObj : jsonMonthly) {
                    JSONObject monthly = (JSONObject) jsonObj;
                    ArrayList<Consummers> newConsummer = new ArrayList<>();
                    ArrayList<CostChanges> newCosts = new ArrayList<>();
                    ArrayList<Producers> newProd = new ArrayList<>();
                    JSONArray newConsummers = (JSONArray) monthly.get("newConsumers");
                    if (newConsummers != null) {
                        for (Object newCons : newConsummers) {
                            Common consumerFact = factory.createEntity(new Consummers(),
                                    (Long) ((JSONObject) newCons).get("id"),
                                    (Long) ((JSONObject) newCons).get("initialBudget"),
                                    (Long) ((JSONObject) newCons).get("monthlyIncome"),
                                    0, 0, 0, 0, "", "", 0, 0, 0);
                            newConsummer.add((Consummers) consumerFact);
                        }
                    }
                    JSONArray costsChanges = (JSONArray) monthly.get("distributorChanges");
                    if (costsChanges != null) {
                        for (Object newCost : costsChanges) {
                            newCosts.add(new CostChanges((long) ((JSONObject) newCost).get("id"),
                                    (Long) ((JSONObject) newCost).get("infrastructureCost")));
                        }
                    }
                    JSONArray prodChanges = (JSONArray) monthly.get("producerChanges");
                    if (prodChanges != null) {
                        for (Object updateProd : prodChanges) {
                            newProd.add(new Producers((long) ((JSONObject) updateProd).get("id"),
                                    (Long) ((JSONObject) updateProd).get("energyPerDistributor")));
                        }
                    }
                    monthlyUpdate.add(new MonthlyUpdates(newConsummer, newCosts, newProd));
                }
            }

            if (jsonConsummers != null) {
                for (Object jsonConsummer : jsonConsummers) {

                    Common consumerFact = factory.createEntity(new Consummers(),
                            (Long) ((JSONObject) jsonConsummer).get("id"),
                            (Long) ((JSONObject) jsonConsummer).get("initialBudget"),
                            (Long) ((JSONObject) jsonConsummer).get("monthlyIncome"),
                            0, 0, 0,
                            0, "", "", 0, 0, 0);
                    consummersList.add(consumerFact);
                }
            }

            if (jsonDistributors != null) {
                for (Object jsonDistribuitor : jsonDistributors) {

                    Common consumerFact = factory.createEntity(new Distributors(), (Long)
                                    ((JSONObject) jsonDistribuitor).get("id"),
                            (Long) ((JSONObject) jsonDistribuitor).get("initialBudget"),
                            0,
                            (Long) ((JSONObject) jsonDistribuitor).get("contractLength"),
                            (Long) ((JSONObject) jsonDistribuitor).get("initialInfrastructureCost"),
                            0,
                            (Long) ((JSONObject) jsonDistribuitor).get("energyNeededKW"),
                            (String) ((JSONObject) jsonDistribuitor).get("producerStrategy"), "", 0,
                            0, 0);
                    distributorsList.add(consumerFact);
                }
            }
            if (jsonProducers != null) {
                for (Object jsonProducer : jsonProducers) {

                    Common produccerFact = factory.createEntity(new Producers(),
                            (Long) ((JSONObject) jsonProducer).get("id"),
                            0,
                            0,
                            0,
                            0,
                            0, 0,
                            "",
                            (String) ((JSONObject) jsonProducer).get("energyType"),
                            (Long) ((JSONObject) jsonProducer).get("maxDistributors"),
                            (Double) ((JSONObject) jsonProducer).get("priceKW"),
                            (Long) ((JSONObject) jsonProducer).get("energyPerDistributor"));
                    producerList.add(produccerFact);
                }
            }
            if (jsonConsummers == null) {
                consummersList = null;
            }
            if (jsonDistributors == null) {
                distributorsList = null;
            }
            if (jsonProducers == null) {
                producerList = null;
            }


        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return new Input(numberOfTurns, consummersList, distributorsList, producerList,
                monthlyUpdate);
    }

}
