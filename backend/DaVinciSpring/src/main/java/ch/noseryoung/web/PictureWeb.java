package ch.noseryoung.web;


import ch.noseryoung.inputformat.CreatePictureInput;
import ch.noseryoung.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/create/")
public class PictureWeb {

    private PictureService pictureService;

    @Autowired
    public PictureWeb(PictureService pictureService) {
        this.pictureService = pictureService;
    }


    @GetMapping(value = "{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> createPicture(@PathVariable String fileName) {
        byte[] responseFile = pictureService.getRequestedPicture(fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition
                .builder("attachment")
                .filename("test.png")
                .build());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .headers(headers)
                .contentLength(responseFile.length)
                .body(responseFile);
    }

    @PostMapping(value = "")
    public ResponseEntity<String> createPicture(@RequestBody CreatePictureInput input) {
        return ResponseEntity.ok().body(pictureService.createRequestedPicture(input));

    }

    public PictureService getPictureService() {
        return pictureService;
    }

    public void setPictureService(PictureService pictureService) {
        this.pictureService = pictureService;
    }
}
