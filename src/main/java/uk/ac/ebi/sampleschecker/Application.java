package uk.ac.ebi.sampleschecker;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import uk.ac.ebi.sampleschecker.model.SolrSample;
import uk.ac.ebi.sampleschecker.repository.SampleSolrRepository;

import java.util.List;

@Component
public class Application implements ApplicationRunner{
    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private SampleSolrRepository repository;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        log.info("-- Entering application");

        //System.out.println(repository.findAll());

        //System.out.println(repository.findOne("SAMEA3204256"));

        repository.findFirst10ByContentType("sample").forEach(System.out::println);

        log.info("-- Exiting application");
    }
}
