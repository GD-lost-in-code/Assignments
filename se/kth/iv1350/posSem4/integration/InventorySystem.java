package se.kth.iv1350.posSem4.integration;


import se.kth.iv1350.posSem4.integration.DTO.ItemDTO;
import se.kth.iv1350.posSem4.integration.exception.ItemNotFoundException;
import se.kth.iv1350.posSem4.integration.exception.DatabaseFailureException;
import java.util.HashMap;

/**
 * Simulates an external inventory system with a fixed catalog.
 */
public class InventorySystem {
    private static InventorySystem instance;
    private static final HashMap<String,ItemDTO> CATALOG = new HashMap<>();

    static {
        CATALOG.put("abc123", new ItemDTO("abc123", "BigWheel Oatmeal", "500g, whole grain oats, high fiber, gluten free", 29.90, 0.06));
        CATALOG.put("def456", new ItemDTO("def456", "YouGoGo Blueberry","240g, low sugar yoghurt, blueberry flavour", 14.90, 0.06));
    }

    public static synchronized InventorySystem getInstance(){
        if (instance == null) instance = new InventorySystem();
        return instance;
    }

    /**
     * Finds and returns an Item by its identifier.
     *
     * @param id the item identifier
     * @return the ItemDTO if found; otherwise null
     * @throws DatabaseFailureException 
     * @throws ItemNotFoundException 
     */
    public ItemDTO findItem(String id) throws DatabaseFailureException, ItemNotFoundException {
        if ("dbFail".equals(id)) throw new DatabaseFailureException(id);
        ItemDTO dto = CATALOG.get(id);
        if (dto == null) throw new ItemNotFoundException(id);
        return dto;
    }
}
