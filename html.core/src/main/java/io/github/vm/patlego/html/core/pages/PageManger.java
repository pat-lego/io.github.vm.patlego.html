package io.github.vm.patlego.html.core.pages;

import java.util.List;

import io.github.vm.patlego.html.core.pages.exceptions.PageException;
import io.github.vm.patlego.html.datasource.tables.Page;

public interface PageManger {

    public List<Page> getPages() throws PageException;

    public Page getPage(String id) throws PageException;

    public Page deletePage(String id) throws PageException;

    public Page createPage(Page page) throws PageException;

    public  Page updatePage(Page page) throws PageException;
    
}
