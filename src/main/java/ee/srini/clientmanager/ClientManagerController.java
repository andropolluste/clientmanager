package ee.srini.clientmanager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ee.srini.clientmanager.model.Client;
import ee.srini.clientmanager.model.User;
import ee.srini.clientmanager.service.ClientService;

/**
 * Controller for user main landing page
 *
 * @author Andro Põlluste
 */
@Controller
public class ClientManagerController {

    @Autowired
    private ClientService clientService;
    
    @GetMapping({"/", ""})
    public String index(Model model, @AuthenticationPrincipal User user) {
        List<Client> clients = clientService.findByUser(user.getId());
        model.addAttribute("clients", clients);
        return "index.html";
    }
}
