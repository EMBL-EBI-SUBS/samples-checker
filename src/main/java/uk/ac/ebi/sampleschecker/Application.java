package uk.ac.ebi.sampleschecker;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import uk.ac.ebi.sampleschecker.model.SolrSample;
import uk.ac.ebi.sampleschecker.repository.SampleSolrRepository;
import uk.ac.ebi.subs.validation.Origin;
import uk.ac.ebi.subs.validation.ValidationMessage;
import uk.ac.ebi.subs.validation.ValidationResult;
import uk.ac.ebi.subs.validation.checklist.Attribute;
import uk.ac.ebi.subs.validation.checklist.AttributeImpl;
import uk.ac.ebi.subs.validation.checklist.ChecklistValidator;
import uk.ac.ebi.subs.validation.checklist.ChecklistValidatorFactory;
import uk.ac.ebi.subs.validation.checklist.ChecklistValidatorFactoryImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Application implements ApplicationRunner{
    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private SampleSolrRepository repository;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        log.info("-- Entering application");
        final ChecklistValidatorFactory checklistValidatorFactory = ChecklistValidatorFactory.newInstance(ChecklistValidatorFactoryImpl.class);
        HashMap<String,ChecklistValidator> checklistValidatorHashMap = new HashMap<>();
        HashMap<String,Integer> checklistValidatorSuccessCountMap = new HashMap<>();
        HashMap<String,Integer> checklistValidatorFailedCountMap = new HashMap<>();

        for (String checklistId : checklistValidatorFactory.getChecklistIDSet() ) {
            final ChecklistValidator checklistValidator = checklistValidatorFactory.createChecklistValidator(checklistId);
            log.info("Created checklist validator for checklist id " + checklistValidator.getId() + " name " + checklistValidator.getName());
            checklistValidatorHashMap.put(checklistId,checklistValidator);
            checklistValidatorSuccessCountMap.put(checklistId,0);
            checklistValidatorFailedCountMap.put(checklistId,0);
        }

        log.info("Created a validator for " + checklistValidatorHashMap.size() + " checklists ");

        for (SolrSample solrSample : repository.findAll()) {
            final ValidationResult validationResult = new ValidationResult();
            final Map<String, List<String>> characteristicsText = solrSample.getCharacteristicsText();
            List<Attribute> attributeList = new ArrayList<>();
            for (String tag : characteristicsText.keySet()) {
                for (String value : characteristicsText.get(tag)) {
                    Attribute attribute = new AttributeImpl(tag,value,null,solrSample.getId());
                    attributeList.add(attribute);
                }
            }

            for (String checklistId : checklistValidatorHashMap.keySet()) {
                final ChecklistValidator checklistValidator = checklistValidatorHashMap.get(checklistId);
                if (checklistValidator.validate(attributeList, validationResult)) {
                    checklistValidatorSuccessCountMap.get(checklistId);
                    log.info("Sample " + solrSample.getId() + " validate with checklist " + checklistId);
                } else {
                    log.info("Sample " + solrSample.getId() + " fails to validate with checklist " + checklistId);
                }
                for (ValidationMessage<Origin> validationMessage : validationResult.getMessages()) {
                    log.trace(validationMessage.getMessage());
                }

            }

        }

        log.info("-- Exiting application");
    }
}
