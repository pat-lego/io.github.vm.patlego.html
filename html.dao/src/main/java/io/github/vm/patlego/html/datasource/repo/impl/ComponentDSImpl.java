package io.github.vm.patlego.html.datasource.repo.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.aries.jpa.template.JpaTemplate;
import org.apache.aries.jpa.template.TransactionType;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import io.github.vm.patlego.html.datasource.repo.ComponentDS;

@Component(service = ComponentDS.class, immediate = true)
public class ComponentDSImpl implements ComponentDS {

    @Reference(target = "(osgi.unit.name=karafdb-hibernate-html)")
    private JpaTemplate jpaTemplate;

    @Override
    public List<io.github.vm.patlego.html.datasource.tables.Component> getComponents() {
        return this.jpaTemplate.txExpr(TransactionType.RequiresNew, emFunction -> {
            CriteriaQuery<io.github.vm.patlego.html.datasource.tables.Component> criteriaQueryComponents = emFunction.getCriteriaBuilder().createQuery(io.github.vm.patlego.html.datasource.tables.Component.class);
            Root<io.github.vm.patlego.html.datasource.tables.Component> variableRoot = criteriaQueryComponents.from(io.github.vm.patlego.html.datasource.tables.Component.class);

            criteriaQueryComponents.select(variableRoot);
            return emFunction.createQuery(criteriaQueryComponents).getResultList();
        });
    }
    
}
