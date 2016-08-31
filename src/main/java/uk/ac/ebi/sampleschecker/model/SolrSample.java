package uk.ac.ebi.sampleschecker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.List;
import java.util.Map;

@SolrDocument(solrCoreName = "samples")
public class SolrSample {

    @Id @Field("accession") String id;
    @Field String accession;
    @Field String name;
    @Field String description;

    @Field("updatedate") String updateDate;
    @Field("releasedate") String releaseDate;

    @Field("sample_name_crt") List<String> sampleName;

    // collection of all characteristics as key/list of value pairs
    @JsonIgnore @Field("*_crt") Map<String, List<String>> characteristicsText;

    // collection of all external reference names
    @JsonIgnore @Field("external_references_name") List<String> externalReferencesNames;

    // group metadata
    @Field("sample_grp_accessions") List<String> groups;

    // submission metadata
    @Field("submission_acc") String submissionAccession;
    @Field("submission_title") String submissionTitle;

    @Field("content_type") String contentType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<String> getSampleName() {
        return sampleName;
    }

    public void setSampleName(List<String> sampleName) {
        this.sampleName = sampleName;
    }

    public Map<String, List<String>> getCharacteristicsText() {
        return characteristicsText;
    }

    public void setCharacteristicsText(Map<String, List<String>> characteristicsText) {
        this.characteristicsText = characteristicsText;
    }

    public List<String> getExternalReferencesNames() {
        return externalReferencesNames;
    }

    public void setExternalReferencesNames(List<String> externalReferencesNames) {
        this.externalReferencesNames = externalReferencesNames;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    public String getSubmissionAccession() {
        return submissionAccession;
    }

    public void setSubmissionAccession(String submissionAccession) {
        this.submissionAccession = submissionAccession;
    }

    public String getSubmissionTitle() {
        return submissionTitle;
    }

    public void setSubmissionTitle(String submissionTitle) {
        this.submissionTitle = submissionTitle;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return "SolrSample{" +
                "id='" + id + '\'' +
                ", accession='" + accession + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", sampleName=" + sampleName +
                ", characteristicsText=" + characteristicsText +
                ", externalReferencesNames=" + externalReferencesNames +
                ", groups=" + groups +
                ", submissionAccession='" + submissionAccession + '\'' +
                ", submissionTitle='" + submissionTitle + '\'' +
                '}';
    }
}
