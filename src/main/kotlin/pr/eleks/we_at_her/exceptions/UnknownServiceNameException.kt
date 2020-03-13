package pr.eleks.we_at_her.exceptions

class UnknownServiceNameException(serviceName: String) : Exception("Unknown service name: $serviceName")