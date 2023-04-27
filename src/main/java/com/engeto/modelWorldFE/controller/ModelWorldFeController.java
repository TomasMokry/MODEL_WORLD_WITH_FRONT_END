package com.engeto.modelWorldFE.controller;

import com.engeto.modelWorldFE.model.Item;
import com.engeto.modelWorldFE.repository.ModelWorldFeRepository;
import com.engeto.modelWorldFE.service.ModelWorldFeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/modelWorld")
public class ModelWorldFeController {
    @Autowired
    ModelWorldFeRepository modelWorldFeRepository;
    @Autowired
    ModelWorldFeService modelWorldFeService;

    @GetMapping(value = "/")
    public String viewHomePage(Model model){
        model.addAttribute("allItemsList", modelWorldFeRepository.loadAllAvailableItems());
        return "index";
    }

    @GetMapping(value = "/addNewForm")
    public String addNewItem(Model model){
        Item item = new Item();
        model.addAttribute("item", item);
        return "newitem";
    }

    @PostMapping(value ="/save")
    public String saveItem(@ModelAttribute("item") Item item){
        modelWorldFeRepository.saveItem(item);
        return "redirect:/modelWorld/";
    }

    @GetMapping(value = "/findItemForm")
    public String showOneItem(Model model){
        Item item = new Item();
        model.addAttribute("oneItem", item);
        return "findItem";
    }

    @GetMapping(value = "/find")
    public String viewOneItem(@ModelAttribute("oneItem")Item item, Model upModel){
        Long longId = (long) item.getId();
        upModel.addAttribute("upItem", modelWorldFeRepository.loadItemById(longId));
        return "indexOne";
    }

    @GetMapping(value = "/updateForm/{id}")
    public String updateForm(@PathVariable(value = "id") Long id, Model model){
        Item itemUpdated = modelWorldFeService.updateItem(id, model);
        model.addAttribute("item", itemUpdated);
        return "updateItem";
    }

    @PostMapping(value = "/update")
    public String updateItem(@ModelAttribute("item") Item item){
        modelWorldFeRepository.updateItem(item);
        return "redirect:/modelWorld/";
    }

    @GetMapping(value = "/deleteItems")
    public String deleteOutOfSaleItems(){
        modelWorldFeRepository.deleteOutOfSaleItems();
        return "redirect:/modelWorld/";
    }

    @GetMapping(value = "/deleteItem/{id}")
    public String deleteItemById(@PathVariable(value = "id") Long id){
        modelWorldFeRepository.deleteItemById(id);
        return "redirect:/modelWorld/";
    }
}
