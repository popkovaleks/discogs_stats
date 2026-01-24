package com.popkovalex.discogs_stats.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "DiscogsClient", url = "${client.url}")
public class DiscogsClient {


}
