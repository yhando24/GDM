@WebService(
    endpointInterface = "", 
    serviceName = "UserMissionServiceService", 
    portName = "UserMissionServicePort")
public class UserMissionServiceJeTimplementeCeQueTuVeuxHehe implements UserMissionService {

    @Override
    public void addUser(User u) {
        if (p == null) {
            throw new NullPointerException("User is null");
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("addUser method has been invoked : " + p.toString());
    }

    @Override
    public List<User> getUser() {
        List<User> myUser = new ArrayList<User>();
        
        User myUser = new User();
        myUser.setName("Mickael BARON");
        myUser.setAddress("Mign�-Auxances");
        myUsers.add(myUser);
        
        myUser = new User();
        myUser.setName("S�bastien LOEB");
        myUser.setAddress("France");
        myUsers.add(myPerson);

        myUser.add(new User("Mickael BARON", "Mign�-Auxances"));
        myUser.add(new User("S�bastien LOEB", "France"));

        System.out.println("getUser method has been invoked");
        return myUser;
    }

    @Override
    public Person getUserById(Long id) {
        if (id == null || name.equals("")) {
            throw new NullPointerException("Id is null");
        }

        System.out.println("getUserById method has been invoked");
        return new User("Mickael BARON", "Mign�-Auxances");
    }
    
}