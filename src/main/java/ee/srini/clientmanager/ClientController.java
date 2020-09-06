package ee.srini.clientmanager;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ee.srini.clientmanager.model.Client;
import ee.srini.clientmanager.model.Country;
import ee.srini.clientmanager.model.User;
import ee.srini.clientmanager.service.ClientService;
import ee.srini.clientmanager.service.CountryService;

/**
 * Controller for client related actions like adding and changing.
 *
 * @author Andro Põlluste
 */
@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private ClientService clientService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/add")
    public String addEditView(Model model) {
        model.addAttribute("client", new Client());
        addCountriesToModel(model);
        return "client.html";
    }

    @GetMapping("/edit")
    public String editView(Model model, @RequestParam Long id, @AuthenticationPrincipal User user) {
        Client client = clientService.findById(id, user.getId())
                .orElseThrow(() -> new RuntimeException("Client not found exception"));
        model.addAttribute("client", client);
        addCountriesToModel(model);
        return "client.html";
    }

    @PostMapping("/save")
    public String save(@Valid Client client, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model,
            @AuthenticationPrincipal User user) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("client", client);
            model.addAttribute("org.springframework.validation.BindingResult.ticket", bindingResult);
            addCountriesToModel(model);
            return "client.html";
        } else {
            client.setUserId(user.getId());
            clientService.save(client);
            redirectAttributes.addFlashAttribute("successMessage", "Client saved successfully");
            return "redirect:/";
        }
    }

    //////////////////////
    // PRIVATE METHODS
    //////////////////////

    private void addCountriesToModel(Model model) {
        List<Country> countries = countryService.findCountries();
        model.addAttribute("countries", countries);
    }

}
