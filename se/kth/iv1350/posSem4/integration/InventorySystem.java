package se.kth.iv1350.posSem4.integration;


import se.kth.iv1350.pos.integration.DTO.ItemDTO;
import java.util.HashMap;

/**
 * Simulates an external inventory system with a fixed catalog.
 */
public class InventorySystem {
    private static final HashMap<String,ItemDTO> CATALOG = new HashMap<>();

    static {
        CATALOG.put("abc123", new ItemDTO("abc123", "BigWheel Oatmeal", "500g, whole grain oats, high fiber, gluten free", 29.90, 0.06));
        CATALOG.put("def456", new ItemDTO("def456", "YouGoGo Blueberry","240g, low sugar yoghurt, blueberry flavour", 14.90, 0.06));
    }

    /**
     * Finds and returns an Item by its identifier.
     *
     * @param id the item identifier
     * @return the ItemDTO if found; otherwise null
     */
    public ItemDTO findItem(String id) {
        return CATALOG.get(id);
    }
}
