package se.kth.iv1350.pos.test.model;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.integration.DTO.ItemDTO;
import se.kth.iv1350.pos.model.Item;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    @Test
    void itemReflectsDtoFields(){
        ItemDTO dto = new ItemDTO("test00", "item test", "desc", 0, 0);
        Item item = new Item(dto);

        assertEquals("test00", item.getId());
        assertEquals("item test", item.getName());
        assertEquals(0, item.getPrice());
        assertEquals("desc", item.getDescription());
        assertEquals(0, item.getVatRate());
    }
}
