package dag.hjem.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dag.hjem.ruter.api.RuterApi;

public abstract class Service {
    protected RuterApi ruterApi;
    protected ExecutorService executorService;
    protected Collector collector;

    public Service(Collector collector, int nThreads) {
        this.collector = collector;
        this.ruterApi = new RuterApi();
        this.executorService = Executors.newFixedThreadPool(nThreads);
    }


}
