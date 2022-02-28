public class Employee {
    Integer id = 0;
    Integer role_id = 0;
    String f_name = "Sam";
    String l_name = "Sam";

    Employee(Integer id, Integer role_id, String f_name, String l_name){
        this.id = id;
        this.role_id = role_id;
        this.f_name = f_name;
        this.l_name = l_name;
    }

    @Override
    public String toString() {
        return String.format("id:%d role_id:%d f_name:%s l_name:%s", id, role_id, f_name, l_name);
    }
}
