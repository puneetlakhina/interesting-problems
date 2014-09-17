package io;

import static java.nio.file.StandardWatchEventKinds.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class TailFileTillClose {
    /**
     * Tail a file till close and return the contents
     *
     * @param f
     * @return
     */
    public static String tailFileTillClose(File f) throws IOException {

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            char[] charBuff = new char[1024];
            StringBuilder readContent = new StringBuilder();
            int n = 0;
            boolean eofReached = false;
            while (!eofReached) {
                n = br.read(charBuff);
                if (n <= 0) {
                    waitForModification(f);
                    n = 0;
                } else {
                    eofReached = (charBuff[n - 1] == 'e');
                    readContent.append(new String(charBuff, 0, n));
                }
            }
            return readContent.toString();
        } finally {
            if (br != null) {
                br.close();
            }
        }

    }

    private static void waitForModification(File f) throws IOException {
        System.out.println("Waiting for modification");
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path dir = f.getParentFile().toPath();
        dir.register(watchService, ENTRY_MODIFY);
        WatchKey key = null;
        modifyloop: while (true) {
            try {
                key = watchService.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    if (kind != ENTRY_MODIFY) {
                        continue;
                    }
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    File filename = new File(dir.toFile(), ev.context().toString());
                    if (filename.getAbsolutePath().equals(f.getAbsolutePath())) {
                        break modifyloop;
                    }
                }
            } catch (Exception e) {

            }
        }
        System.out.println("Modification detected");
    }
}
