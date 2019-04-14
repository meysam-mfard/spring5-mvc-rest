package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.controllers.v1.VendorController;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class VendorServiceImplTest {

    @Mock
    VendorRepository vendorRepository;
    VendorMapper vendorMapper = VendorMapper.INSTANCE;
    VendorService vendorService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        vendorService = new VendorServiceImpl(vendorMapper, vendorRepository);
    }

    @Test
    public void testGetAllVendors() {
        //given
        Vendor vendor1 = new Vendor();
        vendor1.setId(1L);
        vendor1.setName("vendorName1");

        Vendor vendor2 = new Vendor();
        vendor2.setId(2L);
        vendor2.setName("vendorName2");

        when(vendorRepository.findAll()).thenReturn(Arrays.asList(vendor1, vendor2));

        //when
        List<VendorDTO> vendors = vendorService.getAllVendors();

        //then
        assertEquals(2, vendors.size());

    }

    @Test
    public void testGetVendorById() {
        //given
        Vendor vendor1 = new Vendor();
        vendor1.setId(1L);
        vendor1.setName("vendorName1");

        Vendor vendor2 = new Vendor();
        vendor2.setId(2L);
        vendor2.setName("vendorName2");

        when(vendorRepository.findById(anyLong())).thenReturn(java.util.Optional.of(vendor2));

        //when
        VendorDTO vendorDTO = vendorService.getVendorById(2L);

        //then
        assertEquals(vendor2.getName(), vendorDTO.getName());
        assertEquals(VendorController.BASE_URL+"/"+vendor2.getId(), vendorDTO.getVendor_url());
    }
}