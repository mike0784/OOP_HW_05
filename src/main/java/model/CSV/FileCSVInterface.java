package model.CSV;

import java.util.Dictionary;

public interface FileCSVInterface {
    public Dictionary<Integer, Dictionary> readAllLines();
    public Dictionary<String, String> readLine(Integer a);
    public void saveAllLines(Dictionary<Integer, Dictionary> array);
    public void saveLine();
    public void clearLines();
    public void deleteLines();
}
