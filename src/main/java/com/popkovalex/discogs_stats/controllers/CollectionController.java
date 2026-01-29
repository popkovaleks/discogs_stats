package com.popkovalex.discogs_stats.controllers;

import com.popkovalex.discogs_stats.dto.CollectionDto;
import com.popkovalex.discogs_stats.dto.ReleaseResponseDto;
import com.popkovalex.discogs_stats.dto.ResponseFromDiscogsDto;
import com.popkovalex.discogs_stats.services.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/collection")
public class CollectionController {

    @Autowired
    CollectionService collectionService;

    @GetMapping
    public CollectionDto getCollection(@RequestParam String discogsUsername, @RequestParam int folderId) {
        return collectionService.getCollection(discogsUsername, folderId);


    }
}
