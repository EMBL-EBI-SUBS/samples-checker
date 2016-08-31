package uk.ac.ebi.sampleschecker.repository;

import org.springframework.data.solr.repository.SolrRepository;
import org.springframework.stereotype.Component;
import uk.ac.ebi.sampleschecker.model.SolrSample;

import java.util.List;

@Component
public interface SampleSolrRepository extends SolrRepository<SolrSample, String> {

    List<SolrSample> findAll();

    SolrSample findOne(String id);

    List<SolrSample> findFirst10ByContentType(String contentType);
}
