package com.example.materialme;

/**
 * Data model for each row of the RecyclerView
 */

public class Sport {
    // Member variables representing the title and information about the sport.
   private String title;
    private String info;
    private  int imageResource;


    /**
     * Constructor for the Sport data model.
     *
     * @param title The name if the sport.
     * @param info Information about the sport.*/


    public Sport(String title, String info) {
        this.title = title;
        this.info = info;
    }

    public Sport(String title, String info, int imageResource) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
    }

    /**
     * Gets the title of the sport.
     *
     * @return The title of the sport.
     */

    public String getTitle() {
        return title;
    }

    /**
     * Gets the info about the sport.
     *
     * @return The info about the sport.
     */
    public String getInfo() {
        return info;
    }

    /**
     * Gets the image about the sport.
     *
     * @return The image about the sport.
     */
    public int getImageResource() {
        return imageResource;
    }
}
