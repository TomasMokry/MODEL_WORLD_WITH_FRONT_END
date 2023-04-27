package com.engeto.modelWorldFE.service;

import com.engeto.modelWorldFE.model.Item;
import com.engeto.modelWorldFE.repository.ModelWorldFeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ModelWorldFeService {
    @Autowired
    ModelWorldFeRepository modelWorldFeRepository;

    public Item updateItem(Long id, Model model){
        return modelWorldFeRepository.loadItemById(id);
    }

    public Item getItemById(Long id) throws Exception {
        Item item = modelWorldFeRepository.loadItemById(id);
        if (item == null){
            throw new Exception("Id not found");
        }
        return item;
    }


}
