package ch.noseryoung.service;

import ch.noseryoung.inputformat.CreatePictureInput;
import ch.noseryoung.processing.Controller;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class PictureService {

    private Controller controller;

    public PictureService() {
        this.controller = new Controller();
    }

    public ByteArrayResource createRequestedPicture(CreatePictureInput createPictureInput) {
        try {
            File file = controller.createDiamondPicture(createPictureInput);
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(Paths.get(file.getPath())));
            file.delete();
            return resource;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
