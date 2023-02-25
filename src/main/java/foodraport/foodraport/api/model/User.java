package foodraport.foodraport.api.model;



public class User {

    private int id;
    private String name;;
    private String email;
    private String foods;

    public User(int id, String name, String email, String foods) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.foods = foods;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public String getName() {
        return name;
    }

    public void saveUser(User user) {
        user.saveUser(user);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getFoods() {
        return foods;
    }

    public void setFoods(String foods) {
        this.foods = foods;
    }

    public void addFood(String food) {
        this.foods = foods + food;
    }

    public String getFood(int id) {
        return foods;
    }
}