package dev.evgeni.peopleapi.web;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import dev.evgeni.peopleapi.error.MissingFileUploadException;
import dev.evgeni.peopleapi.model.Photo;
import dev.evgeni.peopleapi.repository.PhotoPagingRepository;
import dev.evgeni.peopleapi.repository.PhotoRepository;
import dev.evgeni.peopleapi.web.dto.PersonApiPage;

@RestController
@RequestMapping(value = "/photo")
public class PhotoController {

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private PhotoPagingRepository photoPagingRepository;

    // get photo by id

    @GetMapping(value = "/{id}")
    public ResponseEntity<byte[]> getPhotoById(@PathVariable Long id) {
        Photo photo = photoRepository.findById(id).get();

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, photo.getContentType())
                .body(photo.getContent());
    }

    // get list of all photos (only id and description)
    @GetMapping(value = "")
    public PersonApiPage<Photo> getAllPhotos(
            @RequestParam(required = false, defaultValue = "0") Integer page) {

        Page<Photo> allPhotos = photoPagingRepository.findAll(PageRequest.of(page, 10));

        return new PersonApiPage<>(allPhotos);
    }


    // upload photo
    @PostMapping(value = "")
    public Photo uploadPhoto(
            @RequestParam(name = "photo", required = false) MultipartFile photoFile)
            throws IOException {

        if (photoFile == null) {
            throw new MissingFileUploadException("photo", Photo.class.getName());
        }
        Photo photo = Photo.builder().description("").content(photoFile.getBytes())
                .contentType(photoFile.getContentType()).build();

        return photoRepository.save(photo);
    }

}
