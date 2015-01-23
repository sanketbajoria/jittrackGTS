package com.jittrack.gts.web.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import java.math.BigDecimal;
import java.util.List;

import com.jittrack.gts.Application;
import com.jittrack.gts.domain.Account;
import com.jittrack.gts.repository.AccountRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static com.jittrack.gts.filter.core.SpecificationBuilder.filter;

/**
 * Test class for the AccountResource REST controller.
 *
 * @see GTSAccountResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class AccountResourceTest {

    private static final Integer DEFAULT_ACCOUNT_TYPE = 0;
    private static final Integer UPDATED_ACCOUNT_TYPE = 1;
    
    private static final Boolean DEFAULT_ALLOW_NOTIFY = false;
    private static final Boolean UPDATED_ALLOW_NOTIFY = true;
    private static final Boolean DEFAULT_ALLOW_WEB_SERVICE = false;
    private static final Boolean UPDATED_ALLOW_WEB_SERVICE = true;
    private static final Boolean DEFAULT_AUTO_ADD_DEVICES = false;
    private static final Boolean UPDATED_AUTO_ADD_DEVICES = true;
    private static final String DEFAULT_CONTACT_EMAIL = "SAMPLE_TEXT";
    private static final String UPDATED_CONTACT_EMAIL = "UPDATED_TEXT";
    
    private static final String DEFAULT_CONTACT_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_CONTACT_NAME = "UPDATED_TEXT";
    
    private static final String DEFAULT_CONTACT_PHONE = "SAMPLE_TEXT";
    private static final String UPDATED_CONTACT_PHONE = "UPDATED_TEXT";
    
    private static final Long DEFAULT_EXPIRATION_TIME = 0L;
    private static final Long UPDATED_EXPIRATION_TIME = 1L;
    
    private static final String DEFAULT_EMAIL_PROPERTIES = "SAMPLE_TEXT";
    private static final String UPDATED_EMAIL_PROPERTIES = "UPDATED_TEXT";
    
    private static final Integer DEFAULT_ECONOMY_UNITS = 0;
    private static final Integer UPDATED_ECONOMY_UNITS = 1;
    
    private static final Integer DEFAULT_DISTANCE_UNITS = 0;
    private static final Integer UPDATED_DISTANCE_UNITS = 1;
    
    private static final String DEFAULT_DISPLAY_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_DISPLAY_NAME = "UPDATED_TEXT";
    
    private static final String DEFAULT_DESCRIPTION = "SAMPLE_TEXT";
    private static final String UPDATED_DESCRIPTION = "UPDATED_TEXT";
    
    private static final String DEFAULT_DEFAULT_USER = "SAMPLE_TEXT";
    private static final String UPDATED_DEFAULT_USER = "UPDATED_TEXT";
    
    private static final Long DEFAULT_CREATION_TIME = 0L;
    private static final Long UPDATED_CREATION_TIME = 1L;
    
    private static final Integer DEFAULT_CURRENCY_UNITS = 0;
    private static final Integer UPDATED_CURRENCY_UNITS = 1;
    
    private static final String DEFAULT_DCS_PROPERTIES_ID = "SAMPLE_TEXT";
    private static final String UPDATED_DCS_PROPERTIES_ID = "UPDATED_TEXT";
    
    private static final BigDecimal DEFAULT_FUEL_COST_PER_LITER = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_FUEL_COST_PER_LITER = BigDecimal.ONE;
    
    private static final Integer DEFAULT_GEOCODER_MODE = 0;
    private static final Integer UPDATED_GEOCODER_MODE = 1;
    
    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;
    private static final Boolean DEFAULT_IS_BORDER_CROSSING = false;
    private static final Boolean UPDATED_IS_BORDER_CROSSING = true;
    private static final Long DEFAULT_LAST_LOGIN_TIME = 0L;
    private static final Long UPDATED_LAST_LOGIN_TIME = 1L;
    
    private static final Long DEFAULT_LAST_UPDATE_TIME = 0L;
    private static final Long UPDATED_LAST_UPDATE_TIME = 1L;
    
    private static final Integer DEFAULT_LAT_LON_FORMAT = 0;
    private static final Integer UPDATED_LAT_LON_FORMAT = 1;
    
    private static final Integer DEFAULT_MAXIMUM_DEVICES = 0;
    private static final Integer UPDATED_MAXIMUM_DEVICES = 1;
    
    private static final Integer DEFAULT_MAX_PING_COUNT = 0;
    private static final Integer UPDATED_MAX_PING_COUNT = 1;
    
    private static final String DEFAULT_NOTES = "SAMPLE_TEXT";
    private static final String UPDATED_NOTES = "UPDATED_TEXT";
    
    private static final String DEFAULT_NOTIFY_EMAIL = "SAMPLE_TEXT";
    private static final String UPDATED_NOTIFY_EMAIL = "UPDATED_TEXT";
    
    private static final Long DEFAULT_PASSWD_CHANGE_TIME = 0L;
    private static final Long UPDATED_PASSWD_CHANGE_TIME = 1L;
    
    private static final Long DEFAULT_PASSWD_QUERY_TIME = 0L;
    private static final Long UPDATED_PASSWD_QUERY_TIME = 1L;
    
    private static final String DEFAULT_PASSWORD = "SAMPLE_TEXT";
    private static final String UPDATED_PASSWORD = "UPDATED_TEXT";
    
    private static final Integer DEFAULT_PRESSURE_UNITS = 0;
    private static final Integer UPDATED_PRESSURE_UNITS = 1;
    
    private static final String DEFAULT_PRIVATE_LABEL_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_PRIVATE_LABEL_NAME = "UPDATED_TEXT";
    
    private static final Integer DEFAULT_RETAINED_EVENT_AGE = 0;
    private static final Integer UPDATED_RETAINED_EVENT_AGE = 1;
    
    private static final Boolean DEFAULT_SMS_ENABLED = false;
    private static final Boolean UPDATED_SMS_ENABLED = true;
    private static final String DEFAULT_SMS_PROPERTIES = "SAMPLE_TEXT";
    private static final String UPDATED_SMS_PROPERTIES = "UPDATED_TEXT";
    
    private static final Integer DEFAULT_SPEED_UNITS = 0;
    private static final Integer UPDATED_SPEED_UNITS = 1;
    
    private static final Integer DEFAULT_TEMPERATURE_UNITS = 0;
    private static final Integer UPDATED_TEMPERATURE_UNITS = 1;
    
    private static final String DEFAULT_TIME_ZONE = "SAMPLE_TEXT";
    private static final String UPDATED_TIME_ZONE = "UPDATED_TEXT";
    
    private static final Integer DEFAULT_TOTAL_PING_COUNT = 0;
    private static final Integer UPDATED_TOTAL_PING_COUNT = 1;
    
    private static final Integer DEFAULT_VOLUME_UNITS = 0;
    private static final Integer UPDATED_VOLUME_UNITS = 1;
    

    @Inject
    private AccountRepository accountRepository;

    private MockMvc restAccountMockMvc;

    private Account account;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        GTSAccountResource accountResource = new GTSAccountResource();
        ReflectionTestUtils.setField(accountResource, "accountRepository", accountRepository);
        this.restAccountMockMvc = MockMvcBuilders.standaloneSetup(accountResource).build();
    }

    @Before
    public void initTest() {
        account = new Account();
        account.setAccountType(DEFAULT_ACCOUNT_TYPE);
        account.setAllowNotify(DEFAULT_ALLOW_NOTIFY);
        account.setAllowWebService(DEFAULT_ALLOW_WEB_SERVICE);
        account.setAutoAddDevices(DEFAULT_AUTO_ADD_DEVICES);
        account.setContactEmail(DEFAULT_CONTACT_EMAIL);
        account.setContactName(DEFAULT_CONTACT_NAME);
        account.setContactPhone(DEFAULT_CONTACT_PHONE);
        account.setExpirationTime(DEFAULT_EXPIRATION_TIME);
        account.setEmailProperties(DEFAULT_EMAIL_PROPERTIES);
        account.setEconomyUnits(DEFAULT_ECONOMY_UNITS);
        account.setDistanceUnits(DEFAULT_DISTANCE_UNITS);
        account.setDisplayName(DEFAULT_DISPLAY_NAME);
        account.setDescription(DEFAULT_DESCRIPTION);
        account.setDefaultUser(DEFAULT_DEFAULT_USER);
        account.setCreationTime(DEFAULT_CREATION_TIME);
        account.setCurrencyUnits(DEFAULT_CURRENCY_UNITS);
        account.setDcsPropertiesID(DEFAULT_DCS_PROPERTIES_ID);
        account.setFuelCostPerLiter(DEFAULT_FUEL_COST_PER_LITER);
        account.setGeocoderMode(DEFAULT_GEOCODER_MODE);
        account.setIsActive(DEFAULT_IS_ACTIVE);
        account.setIsBorderCrossing(DEFAULT_IS_BORDER_CROSSING);
        account.setLastLoginTime(DEFAULT_LAST_LOGIN_TIME);
        account.setLastUpdateTime(DEFAULT_LAST_UPDATE_TIME);
        account.setLatLonFormat(DEFAULT_LAT_LON_FORMAT);
        account.setMaximumDevices(DEFAULT_MAXIMUM_DEVICES);
        account.setMaxPingCount(DEFAULT_MAX_PING_COUNT);
        account.setNotes(DEFAULT_NOTES);
        account.setNotifyEmail(DEFAULT_NOTIFY_EMAIL);
        account.setPasswdChangeTime(DEFAULT_PASSWD_CHANGE_TIME);
        account.setPasswdQueryTime(DEFAULT_PASSWD_QUERY_TIME);
        account.setPassword(DEFAULT_PASSWORD);
        account.setPressureUnits(DEFAULT_PRESSURE_UNITS);
        account.setPrivateLabelName(DEFAULT_PRIVATE_LABEL_NAME);
        account.setRetainedEventAge(DEFAULT_RETAINED_EVENT_AGE);
        account.setSmsEnabled(DEFAULT_SMS_ENABLED);
        account.setSmsProperties(DEFAULT_SMS_PROPERTIES);
        account.setSpeedUnits(DEFAULT_SPEED_UNITS);
        account.setTemperatureUnits(DEFAULT_TEMPERATURE_UNITS);
        account.setTimeZone(DEFAULT_TIME_ZONE);
        account.setTotalPingCount(DEFAULT_TOTAL_PING_COUNT);
        account.setVolumeUnits(DEFAULT_VOLUME_UNITS);
    }

    @Test
    @Transactional
    public void createAccount() throws Exception {
        // Validate the database is empty
        assertThat(accountRepository.findAll()).hasSize(0);

        // Create the Account
        restAccountMockMvc.perform(post("/app/rest/accounts")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(account)))
                .andExpect(status().isOk());

        // Validate the Account in the database
        List<Account> accounts = accountRepository.findAll();
        assertThat(accounts).hasSize(1);
        Account testAccount = accounts.iterator().next();
        assertThat(testAccount.getAccountType()).isEqualTo(DEFAULT_ACCOUNT_TYPE);
        assertThat(testAccount.getAllowNotify()).isEqualTo(DEFAULT_ALLOW_NOTIFY);
        assertThat(testAccount.getAllowWebService()).isEqualTo(DEFAULT_ALLOW_WEB_SERVICE);
        assertThat(testAccount.getAutoAddDevices()).isEqualTo(DEFAULT_AUTO_ADD_DEVICES);
        assertThat(testAccount.getContactEmail()).isEqualTo(DEFAULT_CONTACT_EMAIL);
        assertThat(testAccount.getContactName()).isEqualTo(DEFAULT_CONTACT_NAME);
        assertThat(testAccount.getContactPhone()).isEqualTo(DEFAULT_CONTACT_PHONE);
        assertThat(testAccount.getExpirationTime()).isEqualTo(DEFAULT_EXPIRATION_TIME);
        assertThat(testAccount.getEmailProperties()).isEqualTo(DEFAULT_EMAIL_PROPERTIES);
        assertThat(testAccount.getEconomyUnits()).isEqualTo(DEFAULT_ECONOMY_UNITS);
        assertThat(testAccount.getDistanceUnits()).isEqualTo(DEFAULT_DISTANCE_UNITS);
        assertThat(testAccount.getDisplayName()).isEqualTo(DEFAULT_DISPLAY_NAME);
        assertThat(testAccount.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAccount.getDefaultUser()).isEqualTo(DEFAULT_DEFAULT_USER);
        assertThat(testAccount.getCreationTime()).isEqualTo(DEFAULT_CREATION_TIME);
        assertThat(testAccount.getCurrencyUnits()).isEqualTo(DEFAULT_CURRENCY_UNITS);
        assertThat(testAccount.getDcsPropertiesID()).isEqualTo(DEFAULT_DCS_PROPERTIES_ID);
        assertThat(testAccount.getFuelCostPerLiter()).isEqualTo(DEFAULT_FUEL_COST_PER_LITER);
        assertThat(testAccount.getGeocoderMode()).isEqualTo(DEFAULT_GEOCODER_MODE);
        assertThat(testAccount.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testAccount.getIsBorderCrossing()).isEqualTo(DEFAULT_IS_BORDER_CROSSING);
        assertThat(testAccount.getLastLoginTime()).isEqualTo(DEFAULT_LAST_LOGIN_TIME);
        assertThat(testAccount.getLastUpdateTime()).isEqualTo(DEFAULT_LAST_UPDATE_TIME);
        assertThat(testAccount.getLatLonFormat()).isEqualTo(DEFAULT_LAT_LON_FORMAT);
        assertThat(testAccount.getMaximumDevices()).isEqualTo(DEFAULT_MAXIMUM_DEVICES);
        assertThat(testAccount.getMaxPingCount()).isEqualTo(DEFAULT_MAX_PING_COUNT);
        assertThat(testAccount.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testAccount.getNotifyEmail()).isEqualTo(DEFAULT_NOTIFY_EMAIL);
        assertThat(testAccount.getPasswdChangeTime()).isEqualTo(DEFAULT_PASSWD_CHANGE_TIME);
        assertThat(testAccount.getPasswdQueryTime()).isEqualTo(DEFAULT_PASSWD_QUERY_TIME);
        assertThat(testAccount.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testAccount.getPressureUnits()).isEqualTo(DEFAULT_PRESSURE_UNITS);
        assertThat(testAccount.getPrivateLabelName()).isEqualTo(DEFAULT_PRIVATE_LABEL_NAME);
        assertThat(testAccount.getRetainedEventAge()).isEqualTo(DEFAULT_RETAINED_EVENT_AGE);
        assertThat(testAccount.getSmsEnabled()).isEqualTo(DEFAULT_SMS_ENABLED);
        assertThat(testAccount.getSmsProperties()).isEqualTo(DEFAULT_SMS_PROPERTIES);
        assertThat(testAccount.getSpeedUnits()).isEqualTo(DEFAULT_SPEED_UNITS);
        assertThat(testAccount.getTemperatureUnits()).isEqualTo(DEFAULT_TEMPERATURE_UNITS);
        assertThat(testAccount.getTimeZone()).isEqualTo(DEFAULT_TIME_ZONE);
        assertThat(testAccount.getTotalPingCount()).isEqualTo(DEFAULT_TOTAL_PING_COUNT);
        assertThat(testAccount.getVolumeUnits()).isEqualTo(DEFAULT_VOLUME_UNITS);
        
        
        Specification<Account> spec = filter("contactName", DEFAULT_CONTACT_NAME);

        List<Account> users = accountRepository.findAll(spec);
        assertNotNull(users);
        assertEquals(1, users.size());
    }

    @Test
    @Transactional
    public void getAllAccounts() throws Exception {
        // Initialize the database
        accountRepository.saveAndFlush(account);

        // Get all the accounts
        restAccountMockMvc.perform(get("/app/rest/accounts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(account.getId().intValue()))
                .andExpect(jsonPath("$.[0].accountType").value(DEFAULT_ACCOUNT_TYPE))
                .andExpect(jsonPath("$.[0].allowNotify").value(DEFAULT_ALLOW_NOTIFY.booleanValue()))
                .andExpect(jsonPath("$.[0].allowWebService").value(DEFAULT_ALLOW_WEB_SERVICE.booleanValue()))
                .andExpect(jsonPath("$.[0].autoAddDevices").value(DEFAULT_AUTO_ADD_DEVICES.booleanValue()))
                .andExpect(jsonPath("$.[0].contactEmail").value(DEFAULT_CONTACT_EMAIL.toString()))
                .andExpect(jsonPath("$.[0].contactName").value(DEFAULT_CONTACT_NAME.toString()))
                .andExpect(jsonPath("$.[0].contactPhone").value(DEFAULT_CONTACT_PHONE.toString()))
                .andExpect(jsonPath("$.[0].expirationTime").value(DEFAULT_EXPIRATION_TIME.intValue()))
                .andExpect(jsonPath("$.[0].emailProperties").value(DEFAULT_EMAIL_PROPERTIES.toString()))
                .andExpect(jsonPath("$.[0].economyUnits").value(DEFAULT_ECONOMY_UNITS))
                .andExpect(jsonPath("$.[0].distanceUnits").value(DEFAULT_DISTANCE_UNITS))
                .andExpect(jsonPath("$.[0].displayName").value(DEFAULT_DISPLAY_NAME.toString()))
                .andExpect(jsonPath("$.[0].description").value(DEFAULT_DESCRIPTION.toString()))
                .andExpect(jsonPath("$.[0].defaultUser").value(DEFAULT_DEFAULT_USER.toString()))
                .andExpect(jsonPath("$.[0].creationTime").value(DEFAULT_CREATION_TIME.intValue()))
                .andExpect(jsonPath("$.[0].currencyUnits").value(DEFAULT_CURRENCY_UNITS))
                .andExpect(jsonPath("$.[0].dcsPropertiesID").value(DEFAULT_DCS_PROPERTIES_ID.toString()))
                .andExpect(jsonPath("$.[0].fuelCostPerLiter").value(DEFAULT_FUEL_COST_PER_LITER.intValue()))
                .andExpect(jsonPath("$.[0].geocoderMode").value(DEFAULT_GEOCODER_MODE))
                .andExpect(jsonPath("$.[0].isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
                .andExpect(jsonPath("$.[0].isBorderCrossing").value(DEFAULT_IS_BORDER_CROSSING.booleanValue()))
                .andExpect(jsonPath("$.[0].lastLoginTime").value(DEFAULT_LAST_LOGIN_TIME.intValue()))
                .andExpect(jsonPath("$.[0].lastUpdateTime").value(DEFAULT_LAST_UPDATE_TIME.intValue()))
                .andExpect(jsonPath("$.[0].latLonFormat").value(DEFAULT_LAT_LON_FORMAT))
                .andExpect(jsonPath("$.[0].maximumDevices").value(DEFAULT_MAXIMUM_DEVICES))
                .andExpect(jsonPath("$.[0].maxPingCount").value(DEFAULT_MAX_PING_COUNT))
                .andExpect(jsonPath("$.[0].notes").value(DEFAULT_NOTES.toString()))
                .andExpect(jsonPath("$.[0].notifyEmail").value(DEFAULT_NOTIFY_EMAIL.toString()))
                .andExpect(jsonPath("$.[0].passwdChangeTime").value(DEFAULT_PASSWD_CHANGE_TIME.intValue()))
                .andExpect(jsonPath("$.[0].passwdQueryTime").value(DEFAULT_PASSWD_QUERY_TIME.intValue()))
                .andExpect(jsonPath("$.[0].password").value(DEFAULT_PASSWORD.toString()))
                .andExpect(jsonPath("$.[0].pressureUnits").value(DEFAULT_PRESSURE_UNITS))
                .andExpect(jsonPath("$.[0].privateLabelName").value(DEFAULT_PRIVATE_LABEL_NAME.toString()))
                .andExpect(jsonPath("$.[0].retainedEventAge").value(DEFAULT_RETAINED_EVENT_AGE))
                .andExpect(jsonPath("$.[0].smsEnabled").value(DEFAULT_SMS_ENABLED.booleanValue()))
                .andExpect(jsonPath("$.[0].smsProperties").value(DEFAULT_SMS_PROPERTIES.toString()))
                .andExpect(jsonPath("$.[0].speedUnits").value(DEFAULT_SPEED_UNITS))
                .andExpect(jsonPath("$.[0].temperatureUnits").value(DEFAULT_TEMPERATURE_UNITS))
                .andExpect(jsonPath("$.[0].timeZone").value(DEFAULT_TIME_ZONE.toString()))
                .andExpect(jsonPath("$.[0].totalPingCount").value(DEFAULT_TOTAL_PING_COUNT))
                .andExpect(jsonPath("$.[0].volumeUnits").value(DEFAULT_VOLUME_UNITS));
    }

    @Test
    @Transactional
    public void getAccount() throws Exception {
        // Initialize the database
        accountRepository.saveAndFlush(account);

        // Get the account
        restAccountMockMvc.perform(get("/app/rest/accounts/{id}", account.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(account.getId().intValue()))
            .andExpect(jsonPath("$.accountType").value(DEFAULT_ACCOUNT_TYPE))
            .andExpect(jsonPath("$.allowNotify").value(DEFAULT_ALLOW_NOTIFY.booleanValue()))
            .andExpect(jsonPath("$.allowWebService").value(DEFAULT_ALLOW_WEB_SERVICE.booleanValue()))
            .andExpect(jsonPath("$.autoAddDevices").value(DEFAULT_AUTO_ADD_DEVICES.booleanValue()))
            .andExpect(jsonPath("$.contactEmail").value(DEFAULT_CONTACT_EMAIL.toString()))
            .andExpect(jsonPath("$.contactName").value(DEFAULT_CONTACT_NAME.toString()))
            .andExpect(jsonPath("$.contactPhone").value(DEFAULT_CONTACT_PHONE.toString()))
            .andExpect(jsonPath("$.expirationTime").value(DEFAULT_EXPIRATION_TIME.intValue()))
            .andExpect(jsonPath("$.emailProperties").value(DEFAULT_EMAIL_PROPERTIES.toString()))
            .andExpect(jsonPath("$.economyUnits").value(DEFAULT_ECONOMY_UNITS))
            .andExpect(jsonPath("$.distanceUnits").value(DEFAULT_DISTANCE_UNITS))
            .andExpect(jsonPath("$.displayName").value(DEFAULT_DISPLAY_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.defaultUser").value(DEFAULT_DEFAULT_USER.toString()))
            .andExpect(jsonPath("$.creationTime").value(DEFAULT_CREATION_TIME.intValue()))
            .andExpect(jsonPath("$.currencyUnits").value(DEFAULT_CURRENCY_UNITS))
            .andExpect(jsonPath("$.dcsPropertiesID").value(DEFAULT_DCS_PROPERTIES_ID.toString()))
            .andExpect(jsonPath("$.fuelCostPerLiter").value(DEFAULT_FUEL_COST_PER_LITER.intValue()))
            .andExpect(jsonPath("$.geocoderMode").value(DEFAULT_GEOCODER_MODE))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.isBorderCrossing").value(DEFAULT_IS_BORDER_CROSSING.booleanValue()))
            .andExpect(jsonPath("$.lastLoginTime").value(DEFAULT_LAST_LOGIN_TIME.intValue()))
            .andExpect(jsonPath("$.lastUpdateTime").value(DEFAULT_LAST_UPDATE_TIME.intValue()))
            .andExpect(jsonPath("$.latLonFormat").value(DEFAULT_LAT_LON_FORMAT))
            .andExpect(jsonPath("$.maximumDevices").value(DEFAULT_MAXIMUM_DEVICES))
            .andExpect(jsonPath("$.maxPingCount").value(DEFAULT_MAX_PING_COUNT))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES.toString()))
            .andExpect(jsonPath("$.notifyEmail").value(DEFAULT_NOTIFY_EMAIL.toString()))
            .andExpect(jsonPath("$.passwdChangeTime").value(DEFAULT_PASSWD_CHANGE_TIME.intValue()))
            .andExpect(jsonPath("$.passwdQueryTime").value(DEFAULT_PASSWD_QUERY_TIME.intValue()))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD.toString()))
            .andExpect(jsonPath("$.pressureUnits").value(DEFAULT_PRESSURE_UNITS))
            .andExpect(jsonPath("$.privateLabelName").value(DEFAULT_PRIVATE_LABEL_NAME.toString()))
            .andExpect(jsonPath("$.retainedEventAge").value(DEFAULT_RETAINED_EVENT_AGE))
            .andExpect(jsonPath("$.smsEnabled").value(DEFAULT_SMS_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.smsProperties").value(DEFAULT_SMS_PROPERTIES.toString()))
            .andExpect(jsonPath("$.speedUnits").value(DEFAULT_SPEED_UNITS))
            .andExpect(jsonPath("$.temperatureUnits").value(DEFAULT_TEMPERATURE_UNITS))
            .andExpect(jsonPath("$.timeZone").value(DEFAULT_TIME_ZONE.toString()))
            .andExpect(jsonPath("$.totalPingCount").value(DEFAULT_TOTAL_PING_COUNT))
            .andExpect(jsonPath("$.volumeUnits").value(DEFAULT_VOLUME_UNITS));
    }

    @Test
    @Transactional
    public void getNonExistingAccount() throws Exception {
        // Get the account
        restAccountMockMvc.perform(get("/app/rest/accounts/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAccount() throws Exception {
        // Initialize the database
        accountRepository.saveAndFlush(account);

        // Update the account
        account.setAccountType(UPDATED_ACCOUNT_TYPE);
        account.setAllowNotify(UPDATED_ALLOW_NOTIFY);
        account.setAllowWebService(UPDATED_ALLOW_WEB_SERVICE);
        account.setAutoAddDevices(UPDATED_AUTO_ADD_DEVICES);
        account.setContactEmail(UPDATED_CONTACT_EMAIL);
        account.setContactName(UPDATED_CONTACT_NAME);
        account.setContactPhone(UPDATED_CONTACT_PHONE);
        account.setExpirationTime(UPDATED_EXPIRATION_TIME);
        account.setEmailProperties(UPDATED_EMAIL_PROPERTIES);
        account.setEconomyUnits(UPDATED_ECONOMY_UNITS);
        account.setDistanceUnits(UPDATED_DISTANCE_UNITS);
        account.setDisplayName(UPDATED_DISPLAY_NAME);
        account.setDescription(UPDATED_DESCRIPTION);
        account.setDefaultUser(UPDATED_DEFAULT_USER);
        account.setCreationTime(UPDATED_CREATION_TIME);
        account.setCurrencyUnits(UPDATED_CURRENCY_UNITS);
        account.setDcsPropertiesID(UPDATED_DCS_PROPERTIES_ID);
        account.setFuelCostPerLiter(UPDATED_FUEL_COST_PER_LITER);
        account.setGeocoderMode(UPDATED_GEOCODER_MODE);
        account.setIsActive(UPDATED_IS_ACTIVE);
        account.setIsBorderCrossing(UPDATED_IS_BORDER_CROSSING);
        account.setLastLoginTime(UPDATED_LAST_LOGIN_TIME);
        account.setLastUpdateTime(UPDATED_LAST_UPDATE_TIME);
        account.setLatLonFormat(UPDATED_LAT_LON_FORMAT);
        account.setMaximumDevices(UPDATED_MAXIMUM_DEVICES);
        account.setMaxPingCount(UPDATED_MAX_PING_COUNT);
        account.setNotes(UPDATED_NOTES);
        account.setNotifyEmail(UPDATED_NOTIFY_EMAIL);
        account.setPasswdChangeTime(UPDATED_PASSWD_CHANGE_TIME);
        account.setPasswdQueryTime(UPDATED_PASSWD_QUERY_TIME);
        account.setPassword(UPDATED_PASSWORD);
        account.setPressureUnits(UPDATED_PRESSURE_UNITS);
        account.setPrivateLabelName(UPDATED_PRIVATE_LABEL_NAME);
        account.setRetainedEventAge(UPDATED_RETAINED_EVENT_AGE);
        account.setSmsEnabled(UPDATED_SMS_ENABLED);
        account.setSmsProperties(UPDATED_SMS_PROPERTIES);
        account.setSpeedUnits(UPDATED_SPEED_UNITS);
        account.setTemperatureUnits(UPDATED_TEMPERATURE_UNITS);
        account.setTimeZone(UPDATED_TIME_ZONE);
        account.setTotalPingCount(UPDATED_TOTAL_PING_COUNT);
        account.setVolumeUnits(UPDATED_VOLUME_UNITS);
        restAccountMockMvc.perform(post("/app/rest/accounts")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(account)))
                .andExpect(status().isOk());

        // Validate the Account in the database
        List<Account> accounts = accountRepository.findAll();
        assertThat(accounts).hasSize(1);
        Account testAccount = accounts.iterator().next();
        assertThat(testAccount.getAccountType()).isEqualTo(UPDATED_ACCOUNT_TYPE);
        assertThat(testAccount.getAllowNotify()).isEqualTo(UPDATED_ALLOW_NOTIFY);
        assertThat(testAccount.getAllowWebService()).isEqualTo(UPDATED_ALLOW_WEB_SERVICE);
        assertThat(testAccount.getAutoAddDevices()).isEqualTo(UPDATED_AUTO_ADD_DEVICES);
        assertThat(testAccount.getContactEmail()).isEqualTo(UPDATED_CONTACT_EMAIL);
        assertThat(testAccount.getContactName()).isEqualTo(UPDATED_CONTACT_NAME);
        assertThat(testAccount.getContactPhone()).isEqualTo(UPDATED_CONTACT_PHONE);
        assertThat(testAccount.getExpirationTime()).isEqualTo(UPDATED_EXPIRATION_TIME);
        assertThat(testAccount.getEmailProperties()).isEqualTo(UPDATED_EMAIL_PROPERTIES);
        assertThat(testAccount.getEconomyUnits()).isEqualTo(UPDATED_ECONOMY_UNITS);
        assertThat(testAccount.getDistanceUnits()).isEqualTo(UPDATED_DISTANCE_UNITS);
        assertThat(testAccount.getDisplayName()).isEqualTo(UPDATED_DISPLAY_NAME);
        assertThat(testAccount.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAccount.getDefaultUser()).isEqualTo(UPDATED_DEFAULT_USER);
        assertThat(testAccount.getCreationTime()).isEqualTo(UPDATED_CREATION_TIME);
        assertThat(testAccount.getCurrencyUnits()).isEqualTo(UPDATED_CURRENCY_UNITS);
        assertThat(testAccount.getDcsPropertiesID()).isEqualTo(UPDATED_DCS_PROPERTIES_ID);
        assertThat(testAccount.getFuelCostPerLiter()).isEqualTo(UPDATED_FUEL_COST_PER_LITER);
        assertThat(testAccount.getGeocoderMode()).isEqualTo(UPDATED_GEOCODER_MODE);
        assertThat(testAccount.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testAccount.getIsBorderCrossing()).isEqualTo(UPDATED_IS_BORDER_CROSSING);
        assertThat(testAccount.getLastLoginTime()).isEqualTo(UPDATED_LAST_LOGIN_TIME);
        assertThat(testAccount.getLastUpdateTime()).isEqualTo(UPDATED_LAST_UPDATE_TIME);
        assertThat(testAccount.getLatLonFormat()).isEqualTo(UPDATED_LAT_LON_FORMAT);
        assertThat(testAccount.getMaximumDevices()).isEqualTo(UPDATED_MAXIMUM_DEVICES);
        assertThat(testAccount.getMaxPingCount()).isEqualTo(UPDATED_MAX_PING_COUNT);
        assertThat(testAccount.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testAccount.getNotifyEmail()).isEqualTo(UPDATED_NOTIFY_EMAIL);
        assertThat(testAccount.getPasswdChangeTime()).isEqualTo(UPDATED_PASSWD_CHANGE_TIME);
        assertThat(testAccount.getPasswdQueryTime()).isEqualTo(UPDATED_PASSWD_QUERY_TIME);
        assertThat(testAccount.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testAccount.getPressureUnits()).isEqualTo(UPDATED_PRESSURE_UNITS);
        assertThat(testAccount.getPrivateLabelName()).isEqualTo(UPDATED_PRIVATE_LABEL_NAME);
        assertThat(testAccount.getRetainedEventAge()).isEqualTo(UPDATED_RETAINED_EVENT_AGE);
        assertThat(testAccount.getSmsEnabled()).isEqualTo(UPDATED_SMS_ENABLED);
        assertThat(testAccount.getSmsProperties()).isEqualTo(UPDATED_SMS_PROPERTIES);
        assertThat(testAccount.getSpeedUnits()).isEqualTo(UPDATED_SPEED_UNITS);
        assertThat(testAccount.getTemperatureUnits()).isEqualTo(UPDATED_TEMPERATURE_UNITS);
        assertThat(testAccount.getTimeZone()).isEqualTo(UPDATED_TIME_ZONE);
        assertThat(testAccount.getTotalPingCount()).isEqualTo(UPDATED_TOTAL_PING_COUNT);
        assertThat(testAccount.getVolumeUnits()).isEqualTo(UPDATED_VOLUME_UNITS);
    }
    
    
 	/*@Test
 	public void hasUsernameEqual() throws Exception {
         Specification<Account> spec = filter("contactName", DEFAULT_CONTACT_NAME);

         List<Account> users = accountRepository.findAll(spec);
         assertNotNull(users);
         assertEquals(1, users.size());
 	}*/

    @Test
    @Transactional
    public void deleteAccount() throws Exception {
        // Initialize the database
        accountRepository.saveAndFlush(account);

        // Get the account
        restAccountMockMvc.perform(delete("/app/rest/accounts/{id}", account.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Account> accounts = accountRepository.findAll();
        assertThat(accounts).hasSize(0);
    }
    
 
    
}
