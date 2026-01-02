import java.util.Objects;

public abstract class Artwork {
    private int id;
    private String title;
    private int year;
    private double price;
    private Artist artist;

    public Artwork(int id, String title, int year, double price, Artist artist) {
        this.id = id;
        setTitle(title);
        setYear(year);
        setPrice(price);
        setArtist(artist);
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public int getYear() { return year; }
    public double getPrice() { return price; }
    public Artist getArtist() { return artist; }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) return;
        this.title = title;
    }



    public void setYear(int year) {
        if (year < 1000 || year > 2100) return;
        this.year = year;
    }

    public void setPrice(double price) {
        if (price < 0) return;
        this.price = price;
    }

    public void setArtist(Artist artist) {
        if (artist == null) return;
        this.artist = artist;
    }

    public abstract String getType();

    @Override
    public String toString() {
        String artistName = (artist == null) ? "null" : artist.getFullName();
        return getType() + "{id=" + id +
                ", title='" + title + "'" +
                ", year=" + year +
                ", price=" + price +
                ", artist='" + artistName + "'" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artwork)) return false;
        Artwork artwork = (Artwork) o;
        return id == artwork.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}