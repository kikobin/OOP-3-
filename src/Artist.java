import java.util.Objects;



public class Artist {
    private int id;
    private String fullName;
    private String country;

    public Artist(int id, String fullName, String country) {
        this.id = id;
        this.fullName = fullName;
        this.country = country;
    }

    public int getId() { return id; }
    public String getFullName() { return fullName; }
    public String getCountry() { return country; }

    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setCountry(String country) { this.country = country; }

    @Override
    public String toString() {
        return "Artist{id=" + id + ", fullName='" + fullName + "', country='" + country + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist)) return false;
        Artist artist = (Artist) o;
        return id == artist.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}