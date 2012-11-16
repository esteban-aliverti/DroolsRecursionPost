/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilesteban.drools.recursion;

import java.util.LinkedHashMap;
import java.util.Map;
import junit.framework.Assert;
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
public class Case9 extends BaseTest {
    
    @Test
    public void doTest(){
        
        Map<Resource, ResourceType> resources = new LinkedHashMap<Resource, ResourceType>();
        resources.put(ResourceFactory.newClassPathResource("rules/case9.drl"), ResourceType.DRL);
        
        //compile resource allowing 'property specific' facts
        KnowledgeBase kbase = this.createKbase(this.compileResources(resources, true));
        final StatefulKnowledgeSession ksession = this.createKsession(kbase);
        
        Customer customer = new Customer("John", 7);
        customer.setChildrenCount(4);
        ksession.insert(customer);
        
        ksession.fireAllRules();
        
        Assert.assertEquals(customer.getDiscount(), 0.25, 0.000001);
        
    }
    
}
