package org.database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.FileSystems;
import java.nio.file.FileAlreadyExistsException;

public final class DataManager {
    private static volatile DataManager data_mgr;
    private String home_dir;
    private String sys_os;
    private Path ccvertor_dir;
    private Path config_file;
    private Path crrncs_file;

    private DataManager() {
        this.home_dir = System.getProperty("user.home");
        this.sys_os = System.getProperty("os.name");

        if (
            this.sys_os.contains("linux") ||
            this.sys_os.contains("Linux")
        ) {
            this.ccvertor_dir = FileSystems
                .getDefault()
                .getPath(this.home_dir, ".local", "ccvertor");
            this.config_file = FileSystems
                .getDefault()
                .getPath(this.home_dir, ".local", "ccvertor", "Configurations");
            this.crrncs_file = FileSystems
                .getDefault()
                .getPath(
                    this.home_dir,
                    ".local",
                    "ccvertor",
                    "CurrencyConvertor"
                );
        }

        else if (
            this.sys_os.contains("windows") ||
            this.sys_os.contains("Windows")
        ) {
            this.ccvertor_dir = FileSystems
                .getDefault()
                .getPath(
                    this.home_dir,
                    "AppData",
                    "Local",
                    "ccvertor"
                );
            this.config_file = FileSystems
                .getDefault()
                .getPath(
                    this.home_dir,
                    "AppData",
                    "Local",
                    "ccvertor",
                    "Configurations"
                );
            this.crrncs_file = FileSystems
                .getDefault()
                .getPath(
                    this.home_dir,
                    "AppData",
                    "Local",
                    "ccvertor",
                    "CurrencyConvertor"
                );
        }
    }

    public static DataManager getInstance() {
        DataManager tmp = data_mgr;
        if (tmp != null) return tmp;

        synchronized(DataManager.class) {
            if (data_mgr == null) data_mgr = new DataManager();
            return data_mgr;
        }
    }

    public void ensureFiles() throws
        FileAlreadyExistsException,
        IOException,
        SecurityException,
        UnsupportedOperationException
    {
        if (!Files.exists(this.ccvertor_dir))
            Files.createDirectories(this.ccvertor_dir);
        
        if (!Files.exists(this.config_file))
            this.config_file = Files.createFile(this.config_file);

        if (!Files.exists(this.crrncs_file))
            this.crrncs_file = Files.createFile(this.crrncs_file);
    }

    public Path getConfigFile() {
        return this.config_file;
    }

    public Path getCurrenciesFile() {
        return this.crrncs_file;
    }
}
