import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "0000";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }



    public static boolean insertArtist(Artist a) {
        String sql = "INSERT INTO artists(id, full_name, country) VALUES (?, ?, ?)";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, a.getId());
            ps.setString(2, a.getFullName());
            ps.setString(3, a.getCountry());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("insertArtist error: " + e.getMessage());
            return false;
        }
    }

    public static List<Artist> getAllArtists() {
        List<Artist> res = new ArrayList<>();
        String sql = "SELECT id, full_name, country FROM artists ORDER BY id";
        try (Connection c = getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                res.add(new Artist(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("country")
                ));
            }
        } catch (SQLException e) {
            System.out.println("getAllArtists error: " + e.getMessage());
        }
        return res;
    }

    public static boolean updateArtistCountry(int artistId, String newCountry) {
        String sql = "UPDATE artists SET country=? WHERE id=?";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, newCountry);
            ps.setInt(2, artistId);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("updateArtistCountry error: " + e.getMessage());
            return false;
        }
    }

    public static boolean deleteArtist(int artistId) {
        String sql = "DELETE FROM artists WHERE id=?";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, artistId);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("deleteArtist error: " + e.getMessage());
            return false;
        }
    }


    public static boolean insertArtwork(Artwork a) {
        String sql = """
            INSERT INTO artworks(id, title, year, price, artist_id, type, technique, material)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, a.getId());
            ps.setString(2, a.getTitle());
            ps.setInt(3, a.getYear());
            ps.setDouble(4, a.getPrice());
            ps.setInt(5, a.getArtist().getId());

            String type = a.getType(); // "Painting" or "Sculpture"
            ps.setString(6, type);

            if (a instanceof Painting p) {
                ps.setString(7, p.getTechnique());
                ps.setNull(8, Types.VARCHAR);
            } else if (a instanceof Sculpture s) {
                ps.setNull(7, Types.VARCHAR);
                ps.setString(8, s.getMaterial());
            } else {
                throw new IllegalArgumentException("Unknown artwork subtype");
            }

            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("insertArtwork error: " + e.getMessage());
            return false;
        }
    }

    public static List<Artwork> getAllArtworks() {
        List<Artwork> res = new ArrayList<>();
        String sql = """
            SELECT aw.id, aw.title, aw.year, aw.price, aw.type, aw.technique, aw.material,
                   ar.id AS artist_id, ar.full_name, ar.country
            FROM artworks aw
            JOIN artists ar ON ar.id = aw.artist_id
            ORDER BY aw.id
            """;

        try (Connection c = getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Artist artist = new Artist(
                        rs.getInt("artist_id"),
                        rs.getString("full_name"),
                        rs.getString("country")
                );

                String type = rs.getString("type");
                if ("Painting".equals(type)) {
                    res.add(new Painting(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getInt("year"),
                            rs.getDouble("price"),
                            artist,
                            rs.getString("technique")
                    ));
                } else {
                    res.add(new Sculpture(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getInt("year"),
                            rs.getDouble("price"),
                            artist,
                            rs.getString("material")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("getAllArtworks error: " + e.getMessage());
        }
        return res;
    }

    public static boolean updateArtworkPrice(int artworkId, double newPrice) {
        String sql = "UPDATE artworks SET price=? WHERE id=?";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setDouble(1, newPrice);
            ps.setInt(2, artworkId);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("updateArtworkPrice error: " + e.getMessage());
            return false;
        }
    }

    public static boolean deleteArtwork(int artworkId) {
        String sql = "DELETE FROM artworks WHERE id=?";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, artworkId);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("deleteArtwork error: " + e.getMessage());
            return false;
        }
    }
}