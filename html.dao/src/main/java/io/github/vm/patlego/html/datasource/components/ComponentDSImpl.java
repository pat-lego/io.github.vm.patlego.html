package io.github.vm.patlego.html.datasource.components;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.aries.jpa.template.JpaTemplate;
import org.apache.aries.jpa.template.TransactionType;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = ComponentDS.class, immediate = true)
public class ComponentDSImpl implements ComponentDS {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference(target = "(osgi.unit.name=karafdb-hibernate-html)")
    private JpaTemplate jpaTemplate;

    @Override
    public List<io.github.vm.patlego.html.datasource.components.tables.Component> getComponents() {
        return this.jpaTemplate.txExpr(TransactionType.RequiresNew, emFunction -> {
            CriteriaQuery<io.github.vm.patlego.html.datasource.components.tables.Component> criteriaQueryComponents = emFunction
                    .getCriteriaBuilder().createQuery(io.github.vm.patlego.html.datasource.components.tables.Component.class);
            Root<io.github.vm.patlego.html.datasource.components.tables.Component> variableRoot = criteriaQueryComponents
                    .from(io.github.vm.patlego.html.datasource.components.tables.Component.class);

            criteriaQueryComponents.select(variableRoot);
            return emFunction.createQuery(criteriaQueryComponents).getResultList();
        });
    }

    @Override
    public io.github.vm.patlego.html.datasource.components.tables.Component getComponent(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Cannot query for a negative or null component id");
        }
        return this.jpaTemplate.txExpr(TransactionType.RequiresNew,
                emFunction -> emFunction.find(io.github.vm.patlego.html.datasource.components.tables.Component.class, id));
    }

    @Override
    public io.github.vm.patlego.html.datasource.components.tables.Component insertComponent(
            io.github.vm.patlego.html.datasource.components.tables.Component component) {
        if (component == null) {
            throw new IllegalArgumentException("Cannot persist null or undefined component");
        }

        this.jpaTemplate.tx(TransactionType.RequiresNew, emFunction -> emFunction.persist(component));
        return component;
    }

    @Override
    public io.github.vm.patlego.html.datasource.components.tables.Component deleteComponent(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Cannot query for a null or negative ID in the database");
        }
        io.github.vm.patlego.html.datasource.components.tables.Component result = this.jpaTemplate.txExpr(
                TransactionType.RequiresNew,
                emFunction -> emFunction.find(io.github.vm.patlego.html.datasource.components.tables.Component.class, id));
        this.jpaTemplate.tx(emFunction -> {
            if (emFunction.contains(result)) {
                emFunction.remove(result);
            } else {
                io.github.vm.patlego.html.datasource.components.tables.Component component = emFunction.merge(result);
                emFunction.remove(component);
            }
        });
        return result;
    }

    @Override
    public io.github.vm.patlego.html.datasource.components.tables.Component updateComponent(
            io.github.vm.patlego.html.datasource.components.tables.Component component) {
        if (component == null) {
            throw new IllegalArgumentException("Cannot update null or undefined component");
        }

        io.github.vm.patlego.html.datasource.components.tables.Component result = this.jpaTemplate
                .txExpr(TransactionType.RequiresNew, emFunction -> emFunction
                        .find(io.github.vm.patlego.html.datasource.components.tables.Component.class, component.getId()));
        result.setComponentGroup(component.getComponentGroup());
        result.setContext(component.getContext());
        result.setCreated(component.getCreated());
        result.setUpdated(component.getUpdated());
        this.jpaTemplate.tx(TransactionType.RequiresNew, emFunction -> emFunction.merge(result));
        return result;

    }

}
