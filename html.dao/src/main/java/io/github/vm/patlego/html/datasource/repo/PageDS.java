package io.github.vm.patlego.html.datasource.repo;

import java.util.List;
import io.github.vm.patlego.html.datasource.tables.Page;

public interface PageDS {

    public List<Page> getPages();

    public Page getPage(String id);

    public Page createPage(Page page);

    public Page updatePage(Page page);

    public Page deletePage(String id);
    
}
