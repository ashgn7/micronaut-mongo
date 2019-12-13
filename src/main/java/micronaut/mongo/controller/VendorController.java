package micronaut.mongo.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import lombok.extern.slf4j.Slf4j;
import micronaut.mongo.model.request.VendorRequest;
import micronaut.mongo.model.response.VendorResponse;
import micronaut.mongo.service.VendorService;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller("/vendors/v1")
public class VendorController {

    @Inject
    private VendorService vendorService;

    @Get(value = "/list_vendors", produces = MediaType.APPLICATION_JSON)
    public List<VendorResponse> getAllVendors() {
        log.info("Getting all existing vendors ");
        return vendorService.getAllVendors();
    }

    @Post(value = "/create_vendor", produces = MediaType.APPLICATION_JSON)
    public void createVendor(@Body VendorRequest vendorRequest) {
        log.info("Creating vendor with request {} at {}", vendorRequest, LocalDateTime.now());
        vendorService.addVendor(vendorRequest);
    }
}
