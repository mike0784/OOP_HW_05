package model.CSV;

import java.io.*;
import java.util.*;

public class FileCSVOperation implements FileCSVInterface {
    private String fileName;
    private List<String> pattern = Arrays.asList("id", "name", "family", "number");


    public FileCSVOperation(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Dictionary<Integer, Dictionary> readAllLines() {
        Dictionary<Integer, Dictionary> result = new Hashtable<Integer, Dictionary>();
        Dictionary<String, String> line = new Hashtable();
        try{
            File file = new File(this.fileName);
            FileReader fr = new FileReader(file);
            BufferedReader csvReader = new BufferedReader(fr);
            String str = csvReader.readLine();
            String[] pat = str.split(";");
            int key = 0;
            while((str = csvReader.readLine()) != null)
            {
                String[] ss = str.split(";");
                for(int i = 0; i < pat.length; i++)
                {
                    line.put(pat[i], ss[i]);
                }
                result.put(key, line);
                key++;
            }
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Dictionary<String, String> readLine(Integer a) {
        Dictionary<String, String> line = new Hashtable();
        try {
            File file = new File(this.fileName);
            FileReader fr = new FileReader(file);
            BufferedReader csvReader = new BufferedReader(fr);
            String str = csvReader.readLine();
            String[] pat = str.split(";");
            int key = 0;
            while ((str = csvReader.readLine()) != null && a != key)
            {
                key++;
            }
            String[] ss = str.split(";");
            for(int i = 0; i < pat.length; i++)
            {
                line.put(pat[i], ss[i]);
            }

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    @Override
    public void saveAllLines(Dictionary<Integer, Dictionary> array) {
        try (FileWriter csvWriter = new FileWriter(fileName, false)) {
            Dictionary<String, String> line;
            for (int i = 0; i < array.size(); i++) {
                line = array.get(i);
                List<String> keys = (List<String>) line.keys();
                String str = new String();
                for(int j = 0; j < keys.size(); i++)
                {
                    str += line.get(keys.get(j)) + ";";
                }
                csvWriter.write(str + "\n");
            }
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void saveLine() {

    }

    @Override
    public void clearLines() {

    }

    @Override
    public void deleteLines() {

    }
}
