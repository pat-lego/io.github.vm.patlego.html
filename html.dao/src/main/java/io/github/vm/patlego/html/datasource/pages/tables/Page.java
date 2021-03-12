package io.github.vm.patlego.html.datasource.pages.tables;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Entity(name = "pages")
@Table(name = "pages", schema = "patlegovm")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Page {

    @Id
    @Column(name = "page_id", nullable = false, unique = true)
    private String id;

    @Column(name = "page_name", nullable = false, unique = true)
    private String name;

    @Type(type = "jsonb")
    @Column(name = "data", columnDefinition = "JSONB", nullable = false)
    private Context data;

    @Column(name = "creation_dt")
    private LocalDateTime created;

    @Column(name = "updated_dt")
    private LocalDateTime updated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Context getData() {
        return data;
    }

    public void setData(Context data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
