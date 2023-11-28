package dev.evgeni.peopleapi.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import dev.evgeni.peopleapi.model.Photo;
import dev.evgeni.peopleapi.repository.PhotoRepository;

@RestController
@RequestMapping(value = "/photo")
public class PhotoController {

    @Autowired
    private PhotoRepository photoRepository;

    // get photo by id

    @GetMapping(value = "/{id}")
    public ResponseEntity<byte[]> getPhotoById(@PathVariable Long id) {
        Photo photo = photoRepository.findById(id).get();

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, photo.getContentType())
                .body(photo.getContent());
    }

    // get list of all photos (only id and description)
    @GetMapping(value = "")
    public List<Long> getAllPhotos() {
        List<Photo> allPhotos = (List<Photo>) photoRepository.findAll();

        List<Long> allPhotoIds = new ArrayList<>();

        for (Photo photo : allPhotos) {
            allPhotoIds.add(photo.getId());
        }

        return allPhotoIds;
    }


    // upload photo
    @PostMapping(value = "")
    public Photo uploadPhoto(@RequestParam(name = "photo") MultipartFile photoFile)
            throws IOException {
        Photo photo = Photo.builder().description("").content(photoFile.getBytes())
                .contentType(photoFile.getContentType()).build();

        return photoRepository.save(photo);
    }

}
