import java.util.Objects;



public class Artist {
    private int id;
    private String fullName;
    private String country;
    private int birthYear;

    public Artist(int id, String fullName, String country,int birthYear) {
        this.id = id;
        this.fullName = fullName;
        this.country = country;
        this.birthYear = birthYear;
    }

    public int getId() { return id; }
    public String getFullName() { return fullName; }
    public String getCountry() { return country; }
    public int getBitrhYear() { return birthYear; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setCountry(String country) { this.country = country; }
    public void setBitrhYear(int bitrhYear) {this.birthYear = bitrhYear; }

    @Override
    public String toString() {
        return "Artist{id=" + id + ", fullName='" + fullName + "', country='" + country + "', birthYear=" + birthYear + "}";    }

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