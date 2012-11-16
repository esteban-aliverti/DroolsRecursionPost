/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilesteban.drools.recursion;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderConfiguration;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.builder.conf.PropertySpecificOption;
import org.drools.definition.KnowledgePackage;
import org.drools.event.rule.ActivationCancelledEvent;
import org.drools.event.rule.ActivationCreatedEvent;
import org.drools.event.rule.DefaultAgendaEventListener;
import org.drools.io.Resource;
import org.drools.runtime.StatefulKnowledgeSession;

/**
 *
 * @author esteban
 */
public class BaseTest {

    protected Collection<KnowledgePackage> compileResources(Map<Resource, ResourceType> resources) {
        return this.compileResources(resources, false);
    }
    
    protected Collection<KnowledgePackage> compileResources(Map<Resource, ResourceType> resources, boolean enablePropertySpecificFacts) {
        KnowledgeBuilderConfiguration conf = KnowledgeBuilderFactory.newKnowledgeBuilderConfiguration();
        
        if (enablePropertySpecificFacts){
            conf.setOption(PropertySpecificOption.ALLOWED);
        } else{
            conf.setOption(PropertySpecificOption.DISABLED);
        }
        
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder(conf);
        for (Map.Entry<Resource, ResourceType> entry : resources.entrySet()) {
            kbuilder.add(entry.getKey(), entry.getValue());
            if (kbuilder.hasErrors()) {
                Logger.getLogger(Case1.class.getName()).log(Level.SEVERE, "Compilation Errors in {0}", entry.getKey());
                Iterator<KnowledgeBuilderError> iterator = kbuilder.getErrors().iterator();
                while (iterator.hasNext()) {
                    KnowledgeBuilderError knowledgeBuilderError = iterator.next();
                    Logger.getLogger(Case1.class.getName()).log(Level.SEVERE, knowledgeBuilderError.getMessage());
                    System.out.println(knowledgeBuilderError.getMessage());
                }
                throw new IllegalStateException("Compilation Errors");
            }
        }
        return kbuilder.getKnowledgePackages();
    }

    protected KnowledgeBase createKbase(Collection<KnowledgePackage> knowledgePackages) {
        KnowledgeBaseConfiguration conf = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase(conf);
        kbase.addKnowledgePackages(knowledgePackages);
        return kbase;
    }

    protected StatefulKnowledgeSession createKsession(KnowledgeBase kbase) {
        
        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
        
        //configure listeners to print out agenda information
        ksession.addEventListener(new DefaultAgendaEventListener() {
            @Override
            public void activationCreated(ActivationCreatedEvent event) {
                System.out.println("Activation created for " + event.getActivation().getRule().getName());
            }

            @Override
            public void activationCancelled(ActivationCancelledEvent event) {
                System.out.println("Activation cancelled for " + event.getActivation().getRule().getName());
            }
        });
        
        
        
        return ksession;
    }
    
}
