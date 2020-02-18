package pr.eleks.we_at_her.services.api.impl;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import pr.eleks.we_at_her.exceptions.PropertyNotFoundException;
import pr.eleks.we_at_her.services.api.ApiService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public abstract class AbstractApiServiceImpl implements ApiService {

    protected Map<String, String> defaultValues;
    protected String apiPrefix = "wApis.";
    protected UriComponentsBuilder uriBuilder;
    protected Environment env;


    @Override
    public void prepareParameters(String latitude, String longitude, String lang, String units) throws PropertyNotFoundException {
        // Check parameters
        defaultValues = new HashMap<>();
        defaultValues.put("latitude", latitude.equals("")
                ? Optional.ofNullable(env.getProperty("city.Ternopil.lat"))
                .orElseThrow(() -> new PropertyNotFoundException("city.Ternopil.lat"))
                : latitude);
        defaultValues.put("longitude", longitude.equals("")
                ? Optional.ofNullable(env.getProperty("city.Ternopil.lon"))
                .orElseThrow(() -> new PropertyNotFoundException("city.Ternopil.lon"))
                : longitude);
        defaultValues.put("lang", lang.equals("")
                ? Optional.ofNullable(env.getProperty(apiPrefix + getName() + ".lang"))
                .orElseThrow(() -> new PropertyNotFoundException(apiPrefix + getName() + ".lang"))
                : lang);
        defaultValues.put("units", units.equals("")
                ? Optional.ofNullable(env.getProperty(apiPrefix + getName() + ".units"))
                .orElseThrow(() -> new PropertyNotFoundException(apiPrefix + getName() + ".units"))
                : units);

    }

    @Override
    public void prepareBaseUrl() throws PropertyNotFoundException {
        uriBuilder = UriComponentsBuilder
                .fromUriString(
                        Optional
                                .ofNullable(env.getProperty(apiPrefix + getName() + ".baseUrl"))
                                .orElseThrow(() -> new PropertyNotFoundException(apiPrefix + getName() + ".baseUrl")))
                .pathSegment(
                        Optional
                                .ofNullable(env.getProperty(apiPrefix + getName() + ".request"))
                                .orElseThrow(() -> new PropertyNotFoundException(apiPrefix + getName() + ".request")));
    }

}
