package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;
import guru.springfamework.config.SwaggerConfig;
import guru.springfamework.services.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(tags = {SwaggerConfig.TAG_VENDOR_CONTROLLER})
@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {
    public static final String BASE_URL = "/api/v1/vendors";

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ApiOperation(value = "This will get a list of vendors.",
            notes = "These are some notes about the API in regards the getAllVendors function " +
                    "in VendorController.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getAllVendors() {

        return vendorService.getAllVendors();
    }

    @ApiOperation(value = "Get vendor by id")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorById(@PathVariable Long id) {

        return vendorService.getVendorById(id);
    }

    @ApiOperation(value = "Create a new vendor")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createVendor(@RequestBody VendorDTO vendorDTO) {
        return vendorService.createNewVendor(vendorDTO);
    }

    @ApiOperation(value = "Create or replace a vendor")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.saveVendorByDTO(id, vendorDTO);
    }

    @ApiOperation(value = "Update a vendor property")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.patchVendor(id, vendorDTO);
        //saveVendorByDTO
    }

    @ApiOperation(value = "Delete a vendor by id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendorById(id);
    }

}
