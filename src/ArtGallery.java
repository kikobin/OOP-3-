import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ArtGallery {
    private String name;
    private List<Artist> artists;
    private List<Artwork> artworks;

    public ArtGallery(String name) {
        this.name = name;
        this.artists = new ArrayList<>();
        this.artworks = new ArrayList<>();
    }

    public String getName() { return name; }
    public List<Artist> getArtists() { return artists; }
    public List<Artwork> getArtworks() { return artworks; }

    public boolean addArtist(Artist artist) {
        if (artist == null) return false;
        if (artists.contains(artist)) return false;
        artists.add(artist);
        return true;
    }

    public boolean addArtwork(Artwork artwork) {
        if (artwork == null) return false;
        if (artworks.contains(artwork)) return false;
        artworks.add(artwork);
        return true;
    }

    public Artist findArtistById(int id) {
        for (Artist a : artists) {
            if (a.getId() == id) return a;
        }
        return null;
    }

    public Artwork findArtworkById(int id) {
        for (Artwork a : artworks) {
            if (a.getId() == id) return a;
        }
        return null;
    }

    public List<Artwork> searchByTitle(String keyword) {
        List<Artwork> res = new ArrayList<>();
        if (keyword == null) return res;
        String k = keyword.toLowerCase();
        for (Artwork a : artworks) {
            if (a.getTitle() != null && a.getTitle().toLowerCase().contains(k)) {
                res.add(a);
            }
        }
        return res;
    }

    public List<Artwork> filterByArtistId(int artistId) {
        List<Artwork> res = new ArrayList<>();
        for (Artwork a : artworks) {
            if (a.getArtist() != null && a.getArtist().getId() == artistId) {
                res.add(a);
            }
        }
        return res;
    }



    public List<Artwork> filterByType(String type) {
        List<Artwork> res = new ArrayList<>();
        if (type == null) return res;
        String t = type.trim().toLowerCase();
        for (Artwork a : artworks) {
            if (a.getType().toLowerCase().equals(t)) {
                res.add(a);
            }
        }
        return res;
    }

    public List<Artwork> filterByPriceMax(double maxPrice) {
        List<Artwork> res = new ArrayList<>();
        for (Artwork a : artworks) {
            if (a.getPrice() <= maxPrice) {
                res.add(a);
            }
        }
        return res;
    }

    public List<Artwork> filterByYearRange(int from, int to) {
        List<Artwork> res = new ArrayList<>();
        for (Artwork a : artworks) {
            if (a.getYear() >= from && a.getYear() <= to) {
                res.add(a);
            }
        }
        return res;
    }

    public void sortByTitleAsc() {
        Collections.sort(artworks, Comparator.comparing(x -> x.getTitle() == null ? "" : x.getTitle().toLowerCase()));
    }

    public void sortByYearAsc() {
        Collections.sort(artworks, Comparator.comparingInt(Artwork::getYear));
    }

    public void sortByPriceAsc() {
        Collections.sort(artworks, Comparator.comparingDouble(Artwork::getPrice));
    }

    public boolean removeArtworkById(int id) {
        Artwork a = findArtworkById(id);
        if (a == null) return false;
        return artworks.remove(a);
    }
}