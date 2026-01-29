package com.popkovalex.discogs_stats.client;

import com.popkovalex.discogs_stats.dto.ResponseFromDiscogsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DiscogsClient", url = "${client.url}")
public interface DiscogsClient {

    @GetMapping("/users/{username}/collection/folders/{folderId}/releases")
    ResponseFromDiscogsDto getCollectionByUsername(@PathVariable String username, @PathVariable int folderId);

}
