package io.github.vm.patlego.html.core.pages;

import java.io.InputStream;
import java.util.List;

import io.github.vm.patlego.html.core.pages.exceptions.PageException;
import io.github.vm.patlego.html.datasource.pages.tables.Page;

public interface PageManager {

    public List<Page> getPages() throws PageException;

    public Page getPage(String id) throws PageException;

    public Page deletePage(String id) throws PageException;

    public Page createPage(InputStream page) throws PageException;

    public  Page updatePage(InputStream page) throws PageException;
    
}
