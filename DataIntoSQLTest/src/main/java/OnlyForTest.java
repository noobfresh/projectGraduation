import java.util.HashMap;

public class OnlyForTest {

    public static void main(String[] args){

        HashMap<String, AvgTimeData> map = new HashMap<>();

        map.put("1", new AvgTimeData("11", "22", 1.0));
        map.put("2", new AvgTimeData("33", "44", 1.0));

        System.out.println(map.get("1").toString());
        System.out.println(map.get("2").toString());


        map.get("2").setDuration(2.0);

        System.out.println(map.get("1").toString());
        System.out.println(map.get("2").toString());
    }
}
