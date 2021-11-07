import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void saveEnjoyersToFile(List<ProginEnjoyer> list, String fileName) throws IOException {
        var jsonString = JSON.toJSONString(list, true);
        if (!fileName.endsWith(".json")) fileName = fileName + ".json";
        Files.write(Path.of(fileName), Collections.singleton(jsonString));
    }

    public static List<ProginEnjoyer> getEnjoyersFromFile(String fileName) throws IOException {
        if (!fileName.endsWith(".json")) fileName = fileName + ".json";
        List<ProginEnjoyer> enjoyers = null;

        try (Stream<String> lines = Files.lines(Path.of(fileName))) {
            String content = lines.collect(Collectors.joining());
            enjoyers = JSON.parseArray(content, ProginEnjoyer.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return enjoyers;
    }

    public static void main(String[] args) throws Exception {
        ProginEnjoyer vlad = new ProginEnjoyer("vlad-info");
        vlad.showInfo();

        ProginEnjoyer polina = new ProginEnjoyer("polina-info");

        ProginEnjoyer danya = new ProginEnjoyer(
            "Danya",
            "Izotov",
            null,
            "9",
            Arrays.asList("Programming", "Games")
        );

        danya.saveToFile("danya-info");
//        danya.showInfo();

        ProginEnjoyer undefined = new ProginEnjoyer();
        undefined.showInfo();
        undefined.saveToFile("undefined-info");

        saveEnjoyersToFile(Arrays.asList(vlad, polina, danya, undefined), "enjoyers");
        var enjoyers = getEnjoyersFromFile("enjoyers");

        for (var enjoyer :
                enjoyers) {
            enjoyer.showInfo();
            System.out.println();
        }
    }
}
