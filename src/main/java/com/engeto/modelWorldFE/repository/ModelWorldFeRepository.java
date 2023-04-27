package com.engeto.modelWorldFE.repository;

import com.engeto.modelWorldFE.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ModelWorldFeRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Item> loadAllAvailableItems(){
        return jdbcTemplate.query("SELECT * FROM items", (rs,rowNum) -> new Item(
                rs.getInt("id"),
                rs.getInt("partNo"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getBoolean("isForSale"),
                rs.getBigDecimal("price")));
    }

    public void saveItem(Item newItem){
        jdbcTemplate.execute("INSERT INTO items(partNo, name, description, isForSale, price) VALUES("
                + newItem.getPartNo() + ",'"
                + newItem.getName() +"','"
                + newItem.getDescription() + "',"
                + newItem.getIsForSale() + ","
                + newItem.getPrice() + ")");
    }

    public Item loadItemById(int id){
        return jdbcTemplate.queryForObject("SELECT * FROM items WHERE id = ?", new Object[]{id}, (rs, rowNum) -> new Item(
                rs.getInt("id"),
                rs.getInt("partNo"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getBoolean("isForSale"),
                rs.getBigDecimal("price")
        ));
    }

    public Item updateItem(Item item){
        String query = "UPDATE items SET partNo = "
                + item.getPartNo() + ", name = '"
                + item.getName() +"', description = '"
                + item.getDescription() + "', price = "
                + item.getPrice() + " WHERE id = " + item.getId();
        jdbcTemplate.execute(query);
        return item;
    }

    public void deleteItemById(int id){
        String query = "DELETE FROM items WHERE id =" + id;
        jdbcTemplate.execute(query);
    }

    public void deleteOutOfSaleItems(){
        jdbcTemplate.execute("DELETE FROM items WHERE isForSale = false");
    }

}
