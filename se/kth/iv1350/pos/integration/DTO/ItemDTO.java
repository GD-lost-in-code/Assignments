package se.kth.iv1350.pos.integration.DTO;

/**
 * The following class carries the description, price and VAT rate of an item from the inventory system
 */

 public class ItemDTO {
 
    private final String id;
    private final String name;
    private final String description;
    private final double price;
    private final double vatRate;
    
    /**
     * Creates a new Item.
     *
     * @param id      the unique item identifier
     * @param name    the human-readable name of the item
     * @param descriprion detailed description of the item
     * @param price   the net price (excluding VAT) in SEK
     * @param vatRate the VAT rate as a decimal (e.g. 0.06 for 6%)
     */

    public ItemDTO(String id, String name, String description, double price, double vatRate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.vatRate = vatRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemDTO)) return false;
        return id.equals(((ItemDTO)o).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /** @return the item identifier. */
    public String getId() { return id; }

    /** @return the item identifier. */
    public String getName() { return name; }

    /** @return the item description. */
    public String getDescription() { return description; }

    /** @return the item’s net price. */
    public double getPrice() { return price; }

    /** @return the item’s VAT rate. */
    public double getVatRate() { return vatRate; }
 }