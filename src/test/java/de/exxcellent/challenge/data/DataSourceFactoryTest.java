package de.exxcellent.challenge.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sun.net.httpserver.HttpServer;

/**
 * Test class for the DataSourceFactory class. The {@code --url} case is
 * exercised against a throwaway local {@link HttpServer} (built into the
 * JDK) instead of a real network service.
 * @author Philipp Kochendörfer <philipp.kochendoerfer@outlook.com>
 */
class DataSourceFactoryTest {

    private static final String TINY_VALID_CSV = "src/test/resources/de/exxcellent/challenge/tiny_valid.csv";

    private HttpServer httpServer;

    @BeforeEach
    void startHttpServer() throws IOException {
        httpServer = HttpServer.create(new InetSocketAddress("localhost", 0), 0);
        httpServer.createContext("/tiny_valid.csv", exchange -> {
            byte[] body = Files.readAllBytes(Path.of(TINY_VALID_CSV));
            exchange.sendResponseHeaders(200, body.length);
            try (OutputStream responseBody = exchange.getResponseBody()) {
                responseBody.write(body);
            }
        });
        httpServer.start();
    }

    @AfterEach
    void stopHttpServer() {
        httpServer.stop(0);
    }

    @Test
    void open_file_readsFileContent() throws IOException {
        try (InputStream inputStream = DataSourceFactory.open("--file", TINY_VALID_CSV)) {
            assertEquals(Files.readString(Path.of(TINY_VALID_CSV)),
                    new String(inputStream.readAllBytes(), StandardCharsets.UTF_8));
        }
    }

    @Test
    void open_url_readsUrlContent() throws IOException {
        String url = "http://localhost:" + httpServer.getAddress().getPort() + "/tiny_valid.csv";

        try (InputStream inputStream = DataSourceFactory.open("--url", url)) {
            assertEquals(Files.readString(Path.of(TINY_VALID_CSV)),
                    new String(inputStream.readAllBytes(), StandardCharsets.UTF_8));
        }
    }

    @Test
    void open_missingFile_throwsFileNotFoundException() {
        assertThrows(FileNotFoundException.class, () -> DataSourceFactory.open("--file", "does/not/exist.csv"));
    }

    @Test
    void open_unknownSourceType_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> DataSourceFactory.open("--ftp", TINY_VALID_CSV));
    }
}
