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

    public Item updateItem(int id, Model model){
        Item item = modelWorldFeRepository.loadItemById(id);
        return item;
    }
}
