package com.example.Album;

import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    /*@GetMapping("/album/{name}/{year}")
    public String getGreeting(@PathVariable String name, @PathVariable String year){
        return "Ungodly Hour";
    }*/

    @GetMapping("/album")
    public AlbumData getServerMessage(){
        return new AlbumData("Coloring Book", "Chance the Rapper", "2016");
    }

    @PostMapping("/album")
    public AlbumData receiveServerMessage(@RequestBody AlbumData album){
        return new AlbumData(album.getAlbum(), album.getArtist() ,  album.getYear());
    }



}
