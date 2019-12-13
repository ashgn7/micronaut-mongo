package micronaut.mongo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import lombok.extern.slf4j.Slf4j;
import micronaut.mongo.model.Vendor;
import micronaut.mongo.model.request.VendorRequest;
import micronaut.mongo.model.response.VendorResponse;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Slf4j
public class VendorServiceImpl implements VendorService {

    @Inject
    private MongoClient mongoClient;

    @Override
    public List<VendorResponse> getAllVendors() {
        log.info("Getting all vendors ===>>> " + LocalDateTime.now());
        MongoCollection<Vendor> mongoCollection = getCollection();
        log.info("Total records = > " + mongoCollection.countDocuments());
        ObjectMapper objectMapper = new ObjectMapper();
        List<VendorResponse> vendorList = new ArrayList<>();
        mongoCollection.find().map(d-> objectMapper.convertValue(d, VendorResponse.class)).into(vendorList);
        return vendorList;
    }

    @Override
    public void addVendor(VendorRequest vendorRequest) {
        MongoCollection<Vendor> mongoCollection = getCollection();
        Vendor vendor = Vendor.builder().name(vendorRequest.getName()).displayName(vendorRequest.getDisplayName())
                .description(vendorRequest.getDescription()).build();
        log.info("About to add a new vendor --- {} ", vendor);
        mongoCollection.insertOne(vendor);
        log.info("Inserted vendor into table {}", vendor.getName());
    }

    private MongoCollection<Vendor> getCollection() {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoCollection<Vendor> mongoCollection = mongoClient.getDatabase("micronaut-space")
                .withCodecRegistry(pojoCodecRegistry)
                .getCollection("vendor", Vendor.class);
        return mongoCollection;
    }

}
