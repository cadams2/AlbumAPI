package com.example.Album;

import com.example.Album.AlbumData;
import com.example.Album.AlbumRepository;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/albumdata")
public class AlbumController {

    @Autowired
    AlbumRepository repository;

    @GetMapping
    public List<AlbumData> readAllMessages(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public AlbumData readOneMessageById(@PathVariable UUID id){
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public AlbumData createMessage(@RequestBody AlbumData message){
        return repository.save(message);
    }

    @PutMapping("/{id}")
    public AlbumData updateMessage(@PathVariable UUID id, @RequestBody AlbumData albumStuff){
        AlbumData albumToEdit = repository.findById(id).orElse(null);

        if(albumToEdit == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Album Data not Found.");
        }

        albumToEdit.setAlbum(albumStuff.getAlbum());
        albumToEdit.setArtist(albumStuff.getArtist());
        albumToEdit.setYear(albumStuff.getYear());

        return repository.save(albumToEdit);
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable UUID id){
        if(repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

}