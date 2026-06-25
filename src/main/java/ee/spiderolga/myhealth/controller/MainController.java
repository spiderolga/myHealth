package ee.spiderolga.myhealth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Main controller for serving application pages.
 */
@Controller
public class MainController {
    
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    
    /**
     * Serve main application page (dashboard).
     * 
     * @return dashboard page view
     */
    @GetMapping("/")
    public String index() {
        logger.info("Accessing main application page");
        return "dashboard";
    }
    
    /**
     * Serve dashboard page.
     * 
     * @return dashboard page view
     */
    @GetMapping("/dashboard")
    public String dashboardPage() {
        logger.info("Accessing dashboard page");
        return "dashboard";
    }
}
