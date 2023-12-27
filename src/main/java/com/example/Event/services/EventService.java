package com.example.Event.services;

import com.example.Event.entities.Event;
import com.example.Event.entities.Image;
import com.example.Event.entities.User;
import com.example.Event.repositories.EventRepository;
import com.example.Event.repositories.ImageRepository;
import com.example.Event.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class EventService {
    private final EventRepository eventRepository;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;

    public List<Event> listEvents(String title) {
        if (title != null && !title.isEmpty()) return eventRepository.findByTitle(title);
        return eventRepository.findAll();
    }
    @Transactional
    public void saveEvent(Principal principal, Event event, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        event.setUser(getUserByPrincipal(principal));
        if (file1.getSize() != 0) {
            Image image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            imageRepository.save(image1);
//            log.info("Saving new img {}", file1.getBytes());
            image1.setEvent(event);
        }
        if (file2.getSize() != 0) {
            Image image2 = toImageEntity(file2);
//            event.addImageToEvent(image2);
            imageRepository.save(image2);
            image2.setEvent(event);
//            log.info("Saving new img {}", file2.getBytes());
        }
        if (file3.getSize() != 0) {
            Image image3 = toImageEntity(file3);
//            event.addImageToEvent(image3);
            imageRepository.save(image3);
            image3.setEvent(event);
//            log.info("Saving new img {}", file3.getBytes());
        }
        log.info("Saving new Event. Title: {}; Author email: {}", event.getTitle(), event.getUser().getEmail());
        Event eventFromDb = eventRepository.save(event);
        try{
            eventFromDb.setPreviewImageId(eventFromDb.getImages().get(0).getId());
        } catch (Exception e) {

        }
        eventRepository.save(event);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    @Transactional
    public Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());

        //image.setBytes( file.getBytes());

//        Byte[] byteObjects = new Byte[file.getBytes().length];
//
//        int i = 0;
//
//        for (byte b : file.getBytes()){
//            byteObjects[i++] = b;
//        }

//        image.setBytes(byteObjects);
        return image;
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }
}
