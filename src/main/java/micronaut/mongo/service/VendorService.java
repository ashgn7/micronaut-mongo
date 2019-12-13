package micronaut.mongo.service;

import micronaut.mongo.model.request.VendorRequest;
import micronaut.mongo.model.response.VendorResponse;

import java.util.List;

public interface VendorService {

    List<VendorResponse> getAllVendors();

    void addVendor(VendorRequest vendorRequest);
}
