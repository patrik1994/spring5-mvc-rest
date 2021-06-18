package guru.springfamework.services;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;

import java.util.List;

public interface VendorService {
    VendorListDTO getAllVendors();
    VendorDTO getVendorById(Long id);

    VendorDTO createNewVendor(VendorDTO vendorDTO);
    VendorDTO patchVendor(long id, VendorDTO vendorDTO);

    VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO);

    void deleteVendorById(Long id);
}
