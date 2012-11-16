/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilesteban.drools.recursion;

import java.util.LinkedHashMap;
import java.util.Map;
import org.drools.KnowledgeBase;
import org.drools.builder.ResourceType;
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Test;

/**
 *
 * @author esteban
 */
public class Case1 extends BaseTest {
    
    @Test(timeout=5000)
    public void doTest(){
        
        Map<Resource, ResourceType> resources = new LinkedHashMap<Resource, ResourceType>();
        resources.put(ResourceFactory.newClassPathResource("rules/case1.drl"), ResourceType.DRL);
        
        KnowledgeBase kbase = this.createKbase(this.compileResources(resources));
        final StatefulKnowledgeSession ksession = this.createKsession(kbase);
        
        Customer customer = new Customer("John", 4);
        ksession.insert(customer);
        
        ksession.fireAllRules();
        
    }
    
}
