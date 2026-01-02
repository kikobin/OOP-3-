public class Sculpture extends Artwork {
    private String material;



    public Sculpture(int id, String title, int year, double price, Artist artist, String material) {
        super(id, title, year, price, artist);
        this.material = material;
    }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    @Override
    public String getType() {
        return "Sculpture";
    }

    @Override
    public String toString() {
        return super.toString().replace("}", ", material='" + material + "'}");
    }
}