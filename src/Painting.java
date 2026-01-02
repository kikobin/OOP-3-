public class Painting extends Artwork {
    private String technique;

    public Painting(int id, String title, int year, double price, Artist artist, String technique) {
        super(id, title, year, price, artist);
        this.technique = technique;
    }

    public String getTechnique() { return technique; }
    public void setTechnique(String technique) { this.technique = technique; }

    @Override
    public String getType() {
        return "Painting";
    }

    @Override
    public String toString() {
        return super.toString().replace("}", ", technique='" + technique + "'}");
    }
}