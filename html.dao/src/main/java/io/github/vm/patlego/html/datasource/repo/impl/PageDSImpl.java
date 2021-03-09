package io.github.vm.patlego.html.datasource.repo.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.aries.jpa.template.EmFunction;
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

    @Override
    public Page createPage(Page page) {
        if (page == null || page.getId().isEmpty()) {
            throw new IllegalArgumentException("Cannot persist a null or empty id page");
        }

        this.jpaTemplate.tx(TransactionType.RequiresNew, emFunction -> emFunction.persist(page));
        return page;
    }

    @Override
    public Page updatePage(Page page) {
        if (page == null || page.getId().isEmpty()) {
            throw new IllegalArgumentException("Cannot update a null or undefined Page");
        }

        Page result = this.jpaTemplate.txExpr(TransactionType.RequiresNew, emFunction -> emFunction.find(Page.class, page.getId()));
        result.setCreated(page.getCreated());
        result.setData(page.getData());
        result.setUpdated(page.getUpdated());

        this.jpaTemplate.tx(TransactionType.RequiresNew, emFunction -> emFunction.merge(result));

        return result;
    }

    @Override
    public Page deletePage(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Cannot persist a null or empty ID page");
        }

        Page result = this.jpaTemplate.txExpr(TransactionType.RequiresNew, emFunction -> emFunction.find(Page.class, id));

        this.jpaTemplate.tx(TransactionType.RequiresNew, emFunction -> {
            if (emFunction.contains(result)) {
                emFunction.remove(result);
            } else {
                emFunction.remove(emFunction.merge(result));
            }
        });

        return result;
    }

}
