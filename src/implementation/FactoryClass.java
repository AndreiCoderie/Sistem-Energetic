package implementation;

public final class FactoryClass {
    /**
     * constructor simplu
     */
    private FactoryClass() {

    }

    private static FactoryClass instace = null;

    /**
     * singleteon
     */

    public static FactoryClass getInstance() {
        if (instace == null) {
            instace = new FactoryClass();
        }
        return instace;
    }

    /**
     * creearea unei entitati pe design factory
     */

    public Common createEntity(final Common entity, final long id, final long initialBudget,
                               final long monthlyIncome, final long contractLnegth,
                               final long initialInfrastructureBudger,
                               final long initialProductionCost,
                               final long energyNeededKW,
                               final String producerStrategy,
                               final String energyType,
                               final long maxDistributors,
                               final double priceKW,
                               final long energyPerDistr) {
        if (entity instanceof Consummers) {
            return new Consummers(id, initialBudget, monthlyIncome);
        }
        if (entity instanceof Distributors) {
            return new Distributors(id, contractLnegth, initialBudget, initialInfrastructureBudger,
                    initialProductionCost,
                    energyNeededKW, producerStrategy);
        }
        if (entity instanceof Producers) {
            return new Producers(id, energyType, maxDistributors, priceKW, energyPerDistr);
        }
        return null;
    }

}
