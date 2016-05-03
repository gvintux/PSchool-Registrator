package me.fyret.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class GitRepository
{

    public GitRepository(String user)
    {
        this.user = user;
        Properties props = new Properties();
        try (FileInputStream propXml = new FileInputStream("/GitRepository/props.xml"))
        {
            props.loadFromXML(propXml);
            this.path = props.getProperty("repository_path", null) + File.pathSeparator + user;
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public void createRepository(String name) throws IOException, InterruptedException
    {
        File repo = new File(path, name + ".git");
        if (!repo.exists())
        {
            repo.mkdir();
            String[] cmdArray =
            {
                "git", "init", "--bare", "--shared=all"
            };
            Process process = Runtime.getRuntime().exec(cmdArray, null, repo);
            int result = process.waitFor();
        }
    }

    public void deleteRepository(String name)
    {
        File repo = new File(path, name);
        if (repo.exists())
        {
            repo.delete();
        }
    }

    public ArrayList<String> getRepositories()
    {
        ArrayList<String> list = new ArrayList<>();

        return list;
    }

    private final String user;
    private String path;

}
