package pr.eleks.we_at_her.exceptions

class ApiFieldNotFoundException(
        fieldName: String
) : Exception("Field $fieldName was not found.")