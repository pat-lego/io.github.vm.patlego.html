package io.github.vm.patlego.html.core.pages.manager;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.google.gson.Gson;

import org.osgi.service.component.ComponentException;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import io.github.vm.patlego.html.core.pages.PageManager;
import io.github.vm.patlego.html.core.pages.exceptions.PageException;
import io.github.vm.patlego.html.core.pages.utills.PageGsonUtil;
import io.github.vm.patlego.html.datasource.pages.PageDS;
import io.github.vm.patlego.html.datasource.pages.tables.Page;

@Component(service = PageManager.class, immediate = true)
public class SimplePageManger implements PageManager {

    @Reference
    private PageDS pageDS;

    @Override
    public List<Page> getPages() throws PageException {
        try {
            return this.pageDS.getPages();
        } catch(Exception e) {
            throw new ComponentException(e);
        }
        
    }

    @Override
    public Page getPage(String id) {
        try {
            if (id == null || id.isEmpty()) {
                throw new IllegalArgumentException();
            }

            return this.pageDS.getPage(id);
        } catch(Exception e) {
            throw new ComponentException(e);
        }
        
    }

    @Override
    public Page deletePage(String id) throws PageException {
        try {
            if (id == null || id.isEmpty()) {
                throw new IllegalArgumentException("Cannot delete a page with a null or empty ID");
            }

            return this.pageDS.deletePage(id);
        } catch (Exception e) {
            throw new PageException(e);
        }
    }

    @Override
    public Page createPage(InputStream in) throws PageException {
        try {
            if (in ==  null) {
                throw new IllegalArgumentException("Canot create a page with a null object reference");
            }
            Gson gson = new PageGsonUtil().getGson();

            Page page = gson.fromJson(new InputStreamReader(in), Page.class);
            return this.pageDS.createPage(page);

        } catch (Exception e) {
            throw new PageException(e);
        }
    }

    @Override
    public Page updatePage(InputStream in) throws PageException {
        try {
            if (in == null) {
                throw new IllegalArgumentException("Cannot update a page with a null object reference");
            }
            Gson gson = new PageGsonUtil().getGson();

            Page page = gson.fromJson(new InputStreamReader(in), Page.class);
            return this.pageDS.updatePage(page);
        }  catch (Exception e) {
            throw new PageException(e);
        }
    }
    
}
