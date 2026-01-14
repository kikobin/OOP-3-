public class Main {
    public static void main(String[] args) {


        Database.insertArtist(new Artist(1, "Aruzhan K.", "Kazakhstan"));
        Database.insertArtist(new Artist(2, "Dias N.", "Kazakhstan"));

        Artist a1 = new Artist(1, "Aruzhan K.", "Kazakhstan");
        Artist a2 = new Artist(2, "Dias N.", "Kazakhstan");

        Database.insertArtwork(new Painting(101, "Sunset Steppe", 2022, 350000, a1, "Oil"));
        Database.insertArtwork(new Sculpture(201, "Iron Wind", 2020, 550000, a2, "Metal"));


        System.out.println("Artists from DB:");
        for (Artist a : Database.getAllArtists()) System.out.println(a);

        System.out.println("\nArtworks from DB:");
        for (Artwork aw : Database.getAllArtworks()) System.out.println(aw);


        Database.updateArtistCountry(2, "Italy");
        Database.updateArtworkPrice(101, 399999);


        Database.deleteArtwork(201);



        System.out.println("\nAfter update/delete:");
        for (Artist a : Database.getAllArtists()) System.out.println(a);
        for (Artwork aw : Database.getAllArtworks()) System.out.println(aw);
    }
}