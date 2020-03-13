package pr.eleks.we_at_her.services.api.impl

import org.springframework.core.env.Environment
import org.springframework.stereotype.Service
import org.springframework.web.util.UriBuilder
import org.springframework.web.util.UriComponentsBuilder
import pr.eleks.we_at_her.exceptions.PropertyNotFoundException
import pr.eleks.we_at_her.services.api.ApiService
import java.util.*

@Service
abstract class AbstractApiServiceImpl : ApiService {

    protected lateinit var defaultValues: MutableMap<String, String>
    protected lateinit var uriBuilder: UriComponentsBuilder
    protected lateinit var env: Environment
    protected val apiPrefix: String = "wApis."

    override fun prepareParameters(latitude: String?, longitude: String?, lang: String?, units: String?) {
        // Check parameters
        defaultValues = HashMap()
        defaultValues["latitude"] = if (latitude.isNullOrBlank()) {
            env.getProperty("city.Ternopil.lat")
                    ?: throw PropertyNotFoundException("city.Ternopil.lat")
        } else latitude
        defaultValues["longitude"] = if (longitude.isNullOrBlank()) {
            env.getProperty("city.Ternopil.lon")
                    ?: throw PropertyNotFoundException("city.Ternopil.lon")
        } else longitude
        defaultValues["lang"] = if (lang.isNullOrBlank()) {
            env.getProperty(apiPrefix + getName() + ".lang")
                    ?: throw PropertyNotFoundException(apiPrefix + getName() + ".lang")
        } else lang
        defaultValues["units"] = if (units.isNullOrBlank()) {
            env.getProperty(apiPrefix + getName() + ".units")
                    ?: throw PropertyNotFoundException(apiPrefix + getName() + ".units")
        } else units
    }

    @Throws(PropertyNotFoundException::class)
    override fun prepareBaseUrl() {
        uriBuilder = UriComponentsBuilder
                .fromUriString(
                        env.getProperty(apiPrefix + getName() + ".baseUrl")
                                ?: throw PropertyNotFoundException(apiPrefix + getName() + ".baseUrl"))
                .pathSegment(
                        env.getProperty(apiPrefix + getName() + ".request")
                                ?: throw PropertyNotFoundException(apiPrefix + getName() + ".request"))
    }
}
