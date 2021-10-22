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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/create/")
public class PictureWeb {

    private PictureService pictureService;

    @Autowired
    public PictureWeb(PictureService pictureService) {
        this.pictureService = pictureService;
    }


    @PostMapping("")
    public ResponseEntity<Resource> createPicture(@RequestBody CreatePictureInput input) {
        ByteArrayResource responseFile = pictureService.createRequestedPicture(input);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition
                .builder("attachment")
                .filename("test.png")
                .build());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .headers(headers)
                .contentLength(responseFile.contentLength())
                .body(responseFile);
    }

    public PictureService getPictureService() {
        return pictureService;
    }

    public void setPictureService(PictureService pictureService) {
        this.pictureService = pictureService;
    }
}
