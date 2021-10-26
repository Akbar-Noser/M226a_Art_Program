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

    public byte[] getRequestedPicture(String fileName) {
        try {
            File file = new File("./src/main/java/ch/noseryoung/temppictures/" + fileName);
            byte[] resource = Files.readAllBytes(
                    Paths.get(file.getPath()));
            file.delete();
            return resource;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String createRequestedPicture(CreatePictureInput createPictureInput) {
        System.out.println("shades: " + createPictureInput.getAmountOfShades());
        System.out.println("color scheme: " + createPictureInput.getColorScheme());
        System.out.println("layout: " + createPictureInput.getLayout());
        System.out.println("stripe thickness: " + createPictureInput.getStripeThickness());
        System.out.println("even spacing: " + createPictureInput.isEvenSpacing());
        for (int i = 0; i < createPictureInput.getLayers().length; i++) {
            System.out.println("layer: " + createPictureInput.getLayers()[i]);
        }
        try {
            File file = controller.createDiamondPicture(createPictureInput);
            return file.getName();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
