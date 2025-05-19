package se.kth.iv1350.posSem4.model;

import se.kth.iv1350.pos.integration.DTO.ItemDTO;

/**
 * Represents an individual product with pricing and VAT information.
 */
public class Item {
    private final String id;
    private final String name;
    private final String description;
    private final double price;
    private final double vatRate;

    
    public Item(ItemDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.price = dto.getPrice();
        this.vatRate = dto.getVatRate();
    }

    /**
     * @return the item’s unique identifier
     */
    public String getId() {
        return id;
    }

    /**
     * @return the item’s name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the item’s description
     */
    public String getDescription(){
        return description;
    }

    /**
     * @return the item’s net price (excluding VAT)
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return the item’s VAT rate as a decimal
     */
    public double getVatRate() {
        return vatRate;
    }
}
