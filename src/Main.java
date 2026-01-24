import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println();
            System.out.println("1 - Add artist");
            System.out.println("2 - Show artists");
            System.out.println("3 - Update artist country");
            System.out.println("4 - Delete artist");
            System.out.println("5 - Add artwork");
            System.out.println("6 - Show artworks");
            System.out.println("7 - Update artwork price");
            System.out.println("8 - Delete artwork");
            System.out.println("9 - Show artists sorted by name (SQL ORDER BY)");
            System.out.println("10 - Show artworks sorted by price (SQL ORDER BY)");
            System.out.println("0 - Exit");
            System.out.print("Choose option: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> addArtist();
                case 2 -> showArtists();
                case 3 -> updateArtist();
                case 4 -> deleteArtist();
                case 5 -> addArtwork();
                case 6 -> showArtworks();
                case 7 -> updateArtworkPrice();
                case 8 -> deleteArtwork();
                case 9 -> showArtistsSorted();
                case 10 -> showArtworksSorted();
                case 0 -> {
                    System.out.println("Program finished");
                    return;
                }
                default -> System.out.println("Wrong option");
            }
        }
    }



    private static void addArtist() {
        System.out.print("Id: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Full name: ");
        String name = sc.nextLine();

        System.out.print("Country: ");
        String country = sc.nextLine();

        System.out.print("Birth year: ");
        int birthYear = Integer.parseInt(sc.nextLine());

        Database.insertArtist(new Artist(id, name, country,birthYear));
    }

    private static void showArtists() {
        List<Artist> artists = Database.getAllArtists();
        for (Artist a : artists) {
            System.out.println(a);
        }
    }

    private static void showArtistsSorted() {
        List<Artist> artists = Database.getArtistsSortedByName();
        for (Artist a : artists) {
            System.out.println(a);
        }
    }

    private static void updateArtist() {
        System.out.print("Artist id: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("New country: ");
        String country = sc.nextLine();

        Database.updateArtistCountry(id, country);
    }

    private static void deleteArtist() {
        System.out.print("Artist id: ");
        int id = Integer.parseInt(sc.nextLine());

        Database.deleteArtist(id);
    }



    private static void addArtwork() {
        System.out.println("1 - Painting");
        System.out.println("2 - Sculpture");
        System.out.print("Type: ");
        int type = Integer.parseInt(sc.nextLine());

        System.out.print("Id: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Title: ");
        String title = sc.nextLine();

        System.out.print("Year: ");
        int year = Integer.parseInt(sc.nextLine());

        System.out.print("Price: ");
        double price = Double.parseDouble(sc.nextLine());

        System.out.print("Artist id: ");
        int artistId = Integer.parseInt(sc.nextLine());

        Artist artist = Database.getArtistById(artistId);
        if (artist == null) {
            System.out.println("Artist not found");
            return;
        }

        if (type == 1) {
            System.out.print("Technique: ");
            String tech = sc.nextLine();
            Database.insertArtwork(new Painting(id, title, year, price, artist, tech));
        } else if (type == 2) {
            System.out.print("Material: ");
            String mat = sc.nextLine();
            Database.insertArtwork(new Sculpture(id, title, year, price, artist, mat));
        } else {
            System.out.println("Wrong type");
        }
    }

    private static void showArtworks() {
        List<Artwork> artworks = Database.getAllArtworks();
        for (Artwork a : artworks) {
            System.out.println(a);
        }
    }

    private static void showArtworksSorted() {
        List<Artwork> artworks = Database.getArtworksSortedByPriceDesc();
        for (Artwork a : artworks) {
            System.out.println(a);
        }
    }

    private static void updateArtworkPrice() {
        System.out.print("Artwork id: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("New price: ");
        double price = Double.parseDouble(sc.nextLine());

        Database.updateArtworkPrice(id, price);
    }

    private static void deleteArtwork() {
        System.out.print("Artwork id: ");
        int id = Integer.parseInt(sc.nextLine());

        Database.deleteArtwork(id);
    }
}