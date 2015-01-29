package by.bsu.ino.carrent.command;

import by.bsu.ino.carrent.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class NoCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        String page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.INDEX_PAGE_PATH);
        request.getSession().invalidate();
        return page;        
    }    
}
