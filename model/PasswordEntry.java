package model;

public class PasswordEntry {
    private String id;
    private String website;
    private String username;
    private String password;
    private String category;
    private String notes;

    public PasswordEntry (
        String id,
        String website,
        String username,
        String password,
        String category,
        String notes) {

            this.id = id;
            this.website = website;
            this.username = username;
            this.password = password;
            this.category = category;
            this.notes = notes;
        }

        public  String getId() {
            return id;
        }

        public String getWebsite() {
            return website;
        }

         public String getUsername() {
            return username;
        }

         public String getPassword() {
            return password;
        }

         public String getCategory() {
            return category;
        }

         public String getNotes() {
            return notes;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }
    

}
