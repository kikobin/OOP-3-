import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArtGallery gallery = new ArtGallery("Astana Art Gallery");

        gallery.addArtist(new Artist(1, "Aruzhan K.", "Kazakhstan"));
        gallery.addArtist(new Artist(2, "Dias N.", "Kazakhstan"));

        gallery.addArtwork(new Painting(101, "Sunset Steppe", 2022, 350000, gallery.findArtistById(1), "Oil"));
        gallery.addArtwork(new Sculpture(201, "Iron Wind", 2020, 550000, gallery.findArtistById(2), "Metal"));

        while (true) {
            System.out.println();
            System.out.println("=== " + gallery.getName() + " ===");
            System.out.println("1) List artists");
            System.out.println("2) List artworks");
            System.out.println("3) Add artist");
            System.out.println("4) Add artwork");
            System.out.println("5) Search artwork by title");
            System.out.println("6) Filter artworks");
            System.out.println("7) Sort artworks");
            System.out.println("8) Remove artwork by id");
            System.out.println("0) Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 0) break;

            if (choice == 1) {
                if (gallery.getArtists().isEmpty()) System.out.println("No artists.");
                else for (Artist a : gallery.getArtists()) System.out.println(a);
            }

            else if (choice == 2) {
                if (gallery.getArtworks().isEmpty()) System.out.println("No artworks.");
                else for (Artwork a : gallery.getArtworks()) System.out.println(a);
            }

            else if (choice == 3) {
                System.out.print("Artist id: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Full name: ");
                String name = sc.nextLine();
                System.out.print("Country: ");
                String country = sc.nextLine();

                boolean ok = gallery.addArtist(new Artist(id, name, country));
                System.out.println(ok ? "Artist added." : "Not added (duplicate id).");
            }

            else if (choice == 4) {
                System.out.print("Artwork id: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Title: ");
                String title = sc.nextLine();
                System.out.print("Year: ");
                int year = sc.nextInt();
                sc.nextLine();
                System.out.print("Price: ");
                double price = sc.nextDouble();
                sc.nextLine();
                System.out.print("Artist id: ");
                int artistId = sc.nextInt();
                sc.nextLine();

                Artist artist = gallery.findArtistById(artistId);
                if (artist == null) {
                    System.out.println("Artist not found. Add artist first.");
                    continue;
                }

                System.out.print("Type (painting/sculpture): ");
                String type = sc.nextLine().trim().toLowerCase();

                if (type.equals("painting")) {
                    System.out.print("Technique: ");
                    String tech = sc.nextLine();
                    boolean ok = gallery.addArtwork(new Painting(id, title, year, price, artist, tech));
                    System.out.println(ok ? "Artwork added." : "Not added (duplicate id).");
                } else if (type.equals("sculpture")) {
                    System.out.print("Material: ");
                    String material = sc.nextLine();
                    boolean ok = gallery.addArtwork(new Sculpture(id, title, year, price, artist, material));
                    System.out.println(ok ? "Artwork added." : "Not added (duplicate id).");
                } else {
                    System.out.println("Unknown type.");
                }
            }

            else if (choice == 5) {
                System.out.print("Keyword: ");
                String k = sc.nextLine();
                List<Artwork> res = gallery.searchByTitle(k);
                if (res.isEmpty()) System.out.println("No matches.");
                else for (Artwork a : res) System.out.println(a);
            }

            else if (choice == 6) {
                System.out.println("1) By artist id");
                System.out.println("2) By type");
                System.out.println("3) By max price");
                System.out.println("4) By year range");
                System.out.print("Choose filter: ");
                int f = sc.nextInt();
                sc.nextLine();

                List<Artwork> res;

                if (f == 1) {
                    System.out.print("Artist id: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    res = gallery.filterByArtistId(id);
                } else if (f == 2) {
                    System.out.print("Type (painting/sculpture): ");
                    String t = sc.nextLine();
                    res = gallery.filterByType(t);
                } else if (f == 3) {
                    System.out.print("Max price: ");
                    double max = sc.nextDouble();
                    sc.nextLine();
                    res = gallery.filterByPriceMax(max);
                } else if (f == 4) {
                    System.out.print("From year: ");
                    int from = sc.nextInt();
                    sc.nextLine();
                    System.out.print("To year: ");
                    int to = sc.nextInt();
                    sc.nextLine();
                    res = gallery.filterByYearRange(from, to);
                } else {
                    System.out.println("Unknown filter.");
                    continue;
                }

                if (res.isEmpty()) System.out.println("No matches.");
                else for (Artwork a : res) System.out.println(a);
            }

            else if (choice == 7) {
                System.out.println("1) Sort by title");
                System.out.println("2) Sort by year");
                System.out.println("3) Sort by price");
                System.out.print("Choose sort: ");
                int s = sc.nextInt();
                sc.nextLine();

                if (s == 1) gallery.sortByTitleAsc();
                else if (s == 2) gallery.sortByYearAsc();
                else if (s == 3) gallery.sortByPriceAsc();
                else System.out.println("Unknown sort.");

                System.out.println("Sorted.");
            }

            else if (choice == 8) {
                System.out.print("Artwork id: ");
                int id = sc.nextInt();
                sc.nextLine();
                boolean ok = gallery.removeArtworkById(id);
                System.out.println(ok ? "Removed." : "Not found.");
            }

            else {
                System.out.println("Unknown option.");
            }
        }

        System.out.println("Bye.");
        sc.close();
    }
}