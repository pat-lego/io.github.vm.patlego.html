package io.github.vm.patlego.html.tables;

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
@TypeDef(
    name = "jsonb",
    typeClass = JsonBinaryType.class
)
public class Pages {
    
    @Id
    @Column(name = "page_id", nullable = false, unique = true)
    private String id;

    @Column(name = "page", columnDefinition = "TEXT", nullable = false)
    private String page;

    @Type(type = "jsonb")
    @Column(name = "data", columnDefinition = "jsonb", nullable = false)
    private String data;

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

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

    

}
