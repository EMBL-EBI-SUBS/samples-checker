package uk.ac.ebi.sampleschecker.repository;

import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@EnableSolrRepositories(basePackages = {"uk.ac.ebi.sampleschecker"})
public class RepositoryConfiguration {
}
