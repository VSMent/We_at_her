package pr.eleks.we_at_her.exceptions

class PropertyNotFoundException(propertyName: String) : Exception("Property $propertyName was not found")