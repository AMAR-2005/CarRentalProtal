package com.examly.springapp;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.examly.springapp.model.Booking;
import com.examly.springapp.controller.BoatHouseController;
import com.examly.springapp.controller.BookingController;
import com.examly.springapp.controller.OwnerController;
import com.examly.springapp.controller.UserController;
import com.examly.springapp.model.BoatHouse;
import com.examly.springapp.model.Owner;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.OwnerRepository;
import com.examly.springapp.service.BookingService;
import com.examly.springapp.service.BoatHouseService;
import com.examly.springapp.service.OwnerService;
import com.examly.springapp.service.UserService;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.util.List;// Correct import for List

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.time.LocalDate;

@SpringBootTest(classes = SpringappApplication.class)
@AutoConfigureMockMvc
class ControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OwnerService ownerService;

    @Mock

    private OwnerRepository ownerRepository;

    @Mock
    private UserService userService;

    @Mock
    private BoatHouseService boatHouseService;

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private OwnerController ownerController;

    @InjectMocks
    private UserController userController;

    @InjectMocks
    private BoatHouseController boatHouseController;

    @InjectMocks
    private BookingController bookingController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(
                ownerController, userController, boatHouseController, bookingController).build();
    }

    // OwnerController Test Cases
    @Test
    void testGetAllOwners() throws Exception {
        mockMvc.perform(get("/api/owners"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateOwner() throws Exception {
        Owner owner = new Owner();
        owner.setName("John Doe");
        owner.setContactInfo("john.doe@example.com");

        when(ownerService.createOwner(any(Owner.class))).thenReturn(owner);

        mockMvc.perform(post("/api/owners")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"John Doe\", \"contactInfo\":\"john.doe@example.com\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetOwnerById() throws Exception {
        Owner owner = new Owner();
        owner.setId(1L);
        when(ownerService.getOwnerById(1L)).thenReturn(java.util.Optional.of(owner));

        mockMvc.perform(get("/api/owners/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteOwner() throws Exception {
        mockMvc.perform(delete("/api/owners/1"))
                .andExpect(status().isNoContent());
    }

    // UserController Test Cases
    @Test
    void testGetAllUsers() throws Exception {
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateUser() throws Exception {
        User user = new User();
        user.setName("Jane Doe");
        user.setEmail("jane.doe@example.com");

        when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Jane Doe\", \"email\":\"jane.doe@example.com\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetUserById() throws Exception {
        User user = new User();
        user.setId(1L);
        when(userService.getUserById(1L)).thenReturn(java.util.Optional.of(user));

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNoContent());
    }

    // BoatHouseController Test Cases
    @Test
    void testGetAllBoatHouses() throws Exception {
        mockMvc.perform(get("/api/boathouses"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateBoatHouse() throws Exception {
        BoatHouse boatHouse = new BoatHouse();
        boatHouse.setName("The Bay House");
        boatHouse.setLocation("Bay Area");
        boatHouse.setAmenities("Wi-Fi, AC, Pool");

        when(boatHouseService.createBoatHouse(any(BoatHouse.class))).thenReturn(boatHouse);

        mockMvc.perform(post("/api/boathouses")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"The Bay House\", \"location\":\"Bay Area\", \"amenities\":\"Wi-Fi, AC, Pool\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetBoatHouseById() throws Exception {
        BoatHouse boatHouse = new BoatHouse();
        boatHouse.setId(1L);
        when(boatHouseService.getBoatHouseById(1L)).thenReturn(java.util.Optional.of(boatHouse));

        mockMvc.perform(get("/api/boathouses/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteBoatHouse() throws Exception {
        mockMvc.perform(delete("/api/boathouses/1"))
                .andExpect(status().isOk());
    }

    // BookingController Test Cases
    @Test
    void testGetAllBookings() throws Exception {
        mockMvc.perform(get("/api/bookings"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetBookingById() throws Exception {
        Booking booking = new Booking();
        booking.setId(1L);
        when(bookingService.getBookingById(1L)).thenReturn(java.util.Optional.of(booking));

        mockMvc.perform(get("/api/bookings/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteBooking() throws Exception {
        mockMvc.perform(delete("/api/bookings/1"))
                .andExpect(status().isNoContent());
    }

    // ______________AOP______________________
    @Test
    void AOP_testAopFunctionality() throws Exception {
        // Trigger AOP by performing a GET request
        mockMvc.perform(get("/users"))
                .andDo(result -> System.out.println("Response: " + result.getResponse().getContentAsString()));

        // Check the console log for the "AOP: Method called" message
    }

    @Test
    void AOP_testAOPConfigFile() {
        // Path to the LoggingAspect class file
        String filePath = "src/main/java/com/examly/springapp/aspect/LoggingAspect.java";

        // Create a File object
        File file = new File(filePath);

        // Assert that the file exists and is a valid file
        assertTrue(file.exists() && file.isFile(), "LoggingAspect.java file should exist at the specified path.");
    }
//_________________LOG_______________
    @Test
    public void LOG_testLogFolderAndFileCreation() {
        String LOG_FOLDER_PATH = "logs";
        String LOG_FILE_PATH = "logs/application.log";
        // Check if the "logs" folder exists
        File logFolder = new File(LOG_FOLDER_PATH);
        assertTrue(logFolder.exists(), "Log folder should be created");

        // Check if the "application.log" file exists inside the "logs" folder
        File logFile = new File(LOG_FILE_PATH);
        assertTrue(logFile.exists(), "Log file should be created inside 'logs' folder");
    }
//_________________Pagination________________________
    @Test
    void PaginateSorting_testPaginateOwnersController() throws Exception {
        Path entityFilePath = Paths.get("src/main/java/com/examly/springapp/controller/OwnerController.java");
        String entityFileContent = Files.readString(entityFilePath);
        assertTrue(entityFileContent.contains("Page<Owner>"),
                "OwnerController should contain Page<Owner> for pagination");
    }

    @Test
    void testGetPaginatedAndSortedOwners() throws Exception {
        // Set up mock data
        Owner owner1 = new Owner(1L, "Owner A", "ownerA@example.com", null);
        Owner owner2 = new Owner(2L, "Owner B", "ownerB@example.com", null);
        Owner owner3 = new Owner(3L, "Owner C", "ownerC@example.com", null);

        // Wrap the mock data in a Page object (using PageImpl for simplicity)
        Page<Owner> ownerPage = new PageImpl<>(Arrays.asList(owner1, owner2, owner3),
                PageRequest.of(0, 3, Sort.by("name").ascending()), 3);

        // Mock the service call
        when(ownerService.getPaginatedOwners(0, 3, "name", "asc")).thenReturn(ownerPage);

        // Initialize the MockMvc object
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();

        // Perform the GET request and verify the response
        mockMvc.perform(get("/api/owners/paginated?page=0&size=3&sortBy=name&sortDir=asc"))
                .andExpect(status().isOk()); // Verify status is OK (200)

        // Verify that the ownerService method was called once
        verify(ownerService, times(1)).getPaginatedOwners(0, 3, "name", "asc");
    }
//____________JPQL______________________

    @Test
    public void CRUD_JPQL_testFindByName() {
        // Create and save owners
        Owner owner1 = new Owner();
        owner1.setName("John Doe");
        owner1.setContactInfo("1234567890"); // Use the correct setter if different
        owner1 = ownerRepository.save(owner1);

        Owner owner2 = new Owner();
        owner2.setName("Jane Smith");
        owner2.setContactInfo("0987654321");
        owner2 = ownerRepository.save(owner2);

        // Test finding Owners by name
        PageRequest pageRequest = PageRequest.of(0, 2, Sort.by(Sort.Direction.ASC, "name"));
        Page<Owner> result = ownerRepository.findByName("John", pageRequest);

        
    }

    @Test
    void testSwaggerConfigFileExists() throws Exception {
        // Path to the SwaggerConfig file
        Path filePath = Paths.get("src/main/java/com/examly/springapp/config/SwaggerConfig.java");

        // Assert that the file exists
        assertTrue(Files.exists(filePath), "SwaggerConfig.java file should exist");
    }

    @Test
    void testSwaggerConfigContainsConfigurationAnnotation() throws Exception {
        // Path to the SwaggerConfig file
        Path filePath = Paths.get("src/main/java/com/examly/springapp/config/SwaggerConfig.java");

        // Read the file content
        String fileContent = Files.readString(filePath);

        // Assert that the file contains @Configuration annotation
        assertTrue(fileContent.contains("@Configuration"), "SwaggerConfig.java should contain @Configuration annotation");
    }

    @Test
    void testSwaggerConfigContainsCustomOpenAPIMethod() throws Exception {
        // Path to the SwaggerConfig file
        Path filePath = Paths.get("src/main/java/com/examly/springapp/config/SwaggerConfig.java");

        // Read the file content
        String fileContent = Files.readString(filePath);

        // Assert that the file contains the customOpenAPI method
        assertTrue(fileContent.contains("public OpenAPI customOpenAPI()"), "SwaggerConfig.java should contain customOpenAPI method");
    }

    @Test
    void Mapping_testBoatHouseEntityHasManyToOneAnnotation() throws Exception {
        Path entityFilePath = Path.of("src/main/java/com/examly/springapp/model/BoatHouse.java");
        String entityFileContent = Files.readString(entityFilePath);
        assertTrue(entityFileContent.contains("@ManyToOne"), "BoatHouse entity should contain @ManyToOne annotation");
    }

    @Test
    void Mapping_testBoatHouseEntityHasOneToManyAnnotation() throws Exception {
        Path entityFilePath = Path.of("src/main/java/com/examly/springapp/model/BoatHouse.java");
        String entityFileContent = Files.readString(entityFilePath);
        assertTrue(entityFileContent.contains("@OneToMany"), "BoatHouse entity should contain @OneToMany annotation");
    }

    @Test
    void Mapping_testBoatHouseEntityHasJoinColumnAnnotation() throws Exception {
        Path entityFilePath = Path.of("src/main/java/com/examly/springapp/model/BoatHouse.java");
        String entityFileContent = Files.readString(entityFilePath);
        assertTrue(entityFileContent.contains("@JoinColumn"), "BoatHouse entity should contain @JoinColumn annotation");
    }

    @Test
    void Mapping_testBookingEntityHasManyToOneAnnotation() throws Exception {
        Path entityFilePath = Path.of("src/main/java/com/examly/springapp/model/Booking.java");
        String entityFileContent = Files.readString(entityFilePath);
        assertTrue(entityFileContent.contains("@ManyToOne"), "Booking entity should contain @ManyToOne annotation");
    }

    @Test
    void Mapping_testBookingEntityHasJoinColumnAnnotation() throws Exception {
        Path entityFilePath = Path.of("src/main/java/com/examly/springapp/model/Booking.java");
        String entityFileContent = Files.readString(entityFilePath);
        assertTrue(entityFileContent.contains("@JoinColumn"), "Booking entity should contain @JoinColumn annotation");
    }

    @Test
    void Mapping_testOwnerEntityHasOneToManyAnnotation() throws Exception {
        Path entityFilePath = Path.of("src/main/java/com/examly/springapp/model/Owner.java");
        String entityFileContent = Files.readString(entityFilePath);
        assertTrue(entityFileContent.contains("@OneToMany"), "Owner entity should contain @OneToMany annotation");
    }

    @Test
    void Mapping_testUserEntityHasOneToManyAnnotation() throws Exception {
        Path entityFilePath = Path.of("src/main/java/com/examly/springapp/model/User.java");
        String entityFileContent = Files.readString(entityFilePath);
        assertTrue(entityFileContent.contains("@OneToMany"), "User entity should contain @OneToMany annotation");
    }

    @Test
    void Mapping_testEntityHasEntityAnnotation() throws Exception {
        String[] entities = {"BoatHouse", "Booking", "Owner", "User"};
        for (String entity : entities) {
            Path entityFilePath = Path.of("src/main/java/com/examly/springapp/model/" + entity + ".java");
            String entityFileContent = Files.readString(entityFilePath);
            assertTrue(entityFileContent.contains("@Entity"), entity + " should contain @Entity annotation");
        }
    }

    @Test
    void Mapping_testEntityHasIdAnnotation() throws Exception {
        String[] entities = {"BoatHouse", "Booking", "Owner", "User"};
        for (String entity : entities) {
            Path entityFilePath = Path.of("src/main/java/com/examly/springapp/model/" + entity + ".java");
            String entityFileContent = Files.readString(entityFilePath);
            assertTrue(entityFileContent.contains("@Id"), entity + " should contain @Id annotation");
        }
    }

    @Test
    void testBoatHouseRepositoryPathAndContent() throws Exception {
        Path repoFilePath = Path.of("src/main/java/com/examly/springapp/repository/BoatHouseRepository.java");
        String repoFileContent = Files.readString(repoFilePath);

        // Check if file exists and contains required annotations and interface
        assertTrue(repoFileContent.contains("@Repository"), "BoatHouseRepository should contain @Repository annotation");
        assertTrue(repoFileContent.contains("JpaRepository<BoatHouse, Long>"), "BoatHouseRepository should extend JpaRepository<BoatHouse, Long>");
    }

    @Test
    void testBookingRepositoryPathAndContent() throws Exception {
        Path repoFilePath = Path.of("src/main/java/com/examly/springapp/repository/BookingRepository.java");
        String repoFileContent = Files.readString(repoFilePath);

        // Check if file exists and contains required annotations and interface
        assertTrue(repoFileContent.contains("@Repository"), "BookingRepository should contain @Repository annotation");
        assertTrue(repoFileContent.contains("JpaRepository<Booking, Long>"), "BookingRepository should extend JpaRepository<Booking, Long>");
    }

    @Test
    void testOwnerRepositoryPathAndContent() throws Exception {
        Path repoFilePath = Path.of("src/main/java/com/examly/springapp/repository/OwnerRepository.java");
        String repoFileContent = Files.readString(repoFilePath);

        // Check if file exists and contains required annotations and interface
        assertTrue(repoFileContent.contains("@Repository"), "OwnerRepository should contain @Repository annotation");
        assertTrue(repoFileContent.contains("JpaRepository<Owner, Long>"), "OwnerRepository should extend JpaRepository<Owner, Long>");
        assertTrue(repoFileContent.contains("Page<Owner> findAll(Pageable pageable);"), "OwnerRepository should have findAll with Pageable");
        assertTrue(repoFileContent.contains("@Query"), "OwnerRepository should contain @Query annotation for custom queries");
    }

    @Test
    void testUserRepositoryPathAndContent() throws Exception {
        Path repoFilePath = Path.of("src/main/java/com/examly/springapp/repository/UserRepository.java");
        String repoFileContent = Files.readString(repoFilePath);

        // Check if file exists and contains required annotations and interface
        assertTrue(repoFileContent.contains("@Repository"), "UserRepository should contain @Repository annotation");
        assertTrue(repoFileContent.contains("JpaRepository<User, Long>"), "UserRepository should extend JpaRepository<User, Long>");
    }

    @Test
    void testAllRepositoryFilesExist() throws Exception {
        String[] repositories = {
                "BoatHouseRepository",
                "BookingRepository",
                "OwnerRepository",
                "UserRepository"
        };

        for (String repo : repositories) {
            Path repoFilePath = Path.of("src/main/java/com/examly/springapp/repository/" + repo + ".java");
            assertTrue(Files.exists(repoFilePath), repo + ".java should exist in the repository folder");
        }
    }
}