package io.github.vm.patlego.html.datasource.components.tables;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;


@Entity(name = "components")
@Table(name = "components", schema = "patlegovm")
@TypeDef(
    name = "jsonb",
    typeClass = JsonBinaryType.class
)
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "component_id")
    private Long id;

    @Type(type = "jsonb")
    @Column(name = "context", columnDefinition = "JSONB", nullable = false)
    private Context context;

    @Column(name = "component_group", nullable = false)
    private String componentGroup;

    @Column(name = "creation_dt")
    private LocalDateTime created;

    @Column(name = "updated_dt")
    private LocalDateTime updated;

    public Long getId() {
        return id;
    }

    public String getComponentGroup() {
        return componentGroup;
    }

    public void setComponentGroup(String componentGroup) {
        this.componentGroup = componentGroup;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
