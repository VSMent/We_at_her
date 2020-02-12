package pr.eleks.we_at_her.services.api;

import org.springframework.stereotype.Service;
import pr.eleks.we_at_her.exceptions.UnknownServiceNameException;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApiServiceFactory {

    private List<ApiService> services;

    private static final Map<String, ApiService> myServiceCache = new HashMap<>();

    public ApiServiceFactory(List<ApiService> services) {
        this.services = services;
    }

    @PostConstruct
    public void initApiServiceCache() {
        for (ApiService service : services) {
            myServiceCache.put(service.getName(), service);
        }
    }

    public static ApiService getService(String name) throws UnknownServiceNameException {
        ApiService service = myServiceCache.get(name);
        if (service == null) throw new UnknownServiceNameException(name);
        return service;
    }
}
