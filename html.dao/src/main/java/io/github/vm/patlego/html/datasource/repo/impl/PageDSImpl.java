package io.github.vm.patlego.html.datasource.repo.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.aries.jpa.template.JpaTemplate;
import org.apache.aries.jpa.template.TransactionType;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import io.github.vm.patlego.html.datasource.repo.PageDS;
import io.github.vm.patlego.html.datasource.tables.Page;

@Component(service = PageDS.class, immediate = true)
public class PageDSImpl implements PageDS {

    @Reference(target = "(osgi.unit.name=karafdb-hibernate-html)")
    private JpaTemplate jpaTemplate;

    @Override
    public List<Page> getPages() {
        return this.jpaTemplate.txExpr(TransactionType.RequiresNew, emFunction -> {
            CriteriaQuery<Page> criteriaQueryPages = emFunction.getCriteriaBuilder().createQuery(Page.class);
            Root<Page> variableRoot = criteriaQueryPages.from(Page.class);

            criteriaQueryPages.select(variableRoot);
            return emFunction.createQuery(criteriaQueryPages).getResultList();
        });
    }

    @Override
    public Page getPage(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Cannot query a page with a null id");
        }
        return this.jpaTemplate.txExpr(TransactionType.RequiresNew, emFunction -> emFunction.find(Page.class, id));
    }

}
