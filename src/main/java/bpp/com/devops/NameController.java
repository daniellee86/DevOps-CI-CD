package bpp.com.devops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NameController {

    private NameManager nameManager;

    @GetMapping("/")
    public String index(Model model) {
        return newName(model);
    }

    @GetMapping("/api/name")
    public String newName(Model model) {
        model.addAttribute("name", nameManager.createName());
        return "index";
    }

    @PostMapping("/api/submit/animal")
    public String submitAnimal(@RequestParam("textInput") String textInput, Model model) {
        model.addAttribute("textInput", textInput);
        this.nameManager.addAnimal(textInput);
        return "index";
    }

    @PostMapping("/api/submit/adjective")
    public String submitAdjective(@RequestParam("textInput") String textInput, Model model) {
        model.addAttribute("textInput", textInput);
        this.nameManager.addAdjective(textInput);
        return "index";
    }

    @Autowired
    public void setNameManager(NameManager nameManager) {
        this.nameManager = nameManager;
    }

}
