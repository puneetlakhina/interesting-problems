package io;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;
public class TailFileTillCloseTest {

    private static class WriterTask implements Callable<String> {
        private final File fileToWriteTo;

        public WriterTask(File f) {
            this.fileToWriteTo = f;
        }
        @Override
        public String call() {
            StringBuilder writtenContent = new StringBuilder();
            FileWriter bw = null;
            try {
                Thread.sleep(100);
                bw = new FileWriter(fileToWriteTo);
                for(int i=0;i<10;i++) {
                    String toWrite = "__" + i;
                    bw.write(toWrite);
                    writtenContent.append(toWrite);
                    Thread.sleep(100);
                }
                bw.write('e');
                writtenContent.append('e');
            } catch (Exception e) {
                throw new RuntimeException();
            } finally {
                if(bw != null) {
                    try {
                        bw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return writtenContent.toString();
        }


    }
    @Test
    public void testTailFile() throws Exception {
        final File f = File.createTempFile("test", "tail");
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<String> writerFuture = executor.submit(new WriterTask(f));

        Future<String> readerFuture = executor.submit(new Callable<String>() {
            @Override
            public String call() throws IOException {
                return TailFileTillClose.tailFileTillClose(f);
            }
        });

        assertEquals(writerFuture.get(), readerFuture.get());
    }
}
