package demo.elastic.rag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("rag")
public class RagController {

    private final RagService ragService;

    @Autowired
    public RagController(RagService ragService) {
        this.ragService = ragService;
    }

    @PostMapping("/ingestPdf")
    public ResponseEntity ingestPDF(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload.");
        }
        try {
            // The ragService now needs to accept the MultipartFile or the file content
            // For simplicity, we'll pass the file directly.
            ragService.ingestPDF(file);

            return ResponseEntity.ok().body("PDF ingested successfully! File: " + file.getOriginalFilename());
        } catch (Exception e) {
            // Log the exception for debugging
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Failed to ingest PDF: " + e.getMessage());
        }
    }

    @PostMapping("/query")
    public ResponseEntity query(@RequestBody String question) {
        try {
            String response = ragService.queryLLM(question);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
