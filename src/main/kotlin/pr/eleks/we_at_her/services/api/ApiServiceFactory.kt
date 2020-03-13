package pr.eleks.we_at_her.services.api;

import org.springframework.stereotype.Service
import pr.eleks.we_at_her.exceptions.UnknownServiceNameException
import javax.annotation.PostConstruct

@Service
class ApiServiceFactory(
        private val services: List<ApiService>
){
    companion object{
        private val serviceCache: MutableMap<String, ApiService> = HashMap()

        @Throws(UnknownServiceNameException::class)
        fun getService(name: String): ApiService {
            return serviceCache[name]
                    ?: throw UnknownServiceNameException(name)
        }
    }

    @PostConstruct
    fun initApiServiceCache() {
        for (service in services) {
            serviceCache[service.getName()] = service
        }
    }
}
